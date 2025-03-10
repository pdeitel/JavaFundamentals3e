// Fig. 19.12: OpenAIUtilities.java
// Helper methods and record classes to used encapsulate calling
// the OpenAI APIs via the Simple-OpenAI Java library.
package deitel.openai;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.common.ResponseFormat;
import io.github.sashirestela.openai.common.ResponseFormat.JsonSchema;
import io.github.sashirestela.openai.common.audio.Voice;
import io.github.sashirestela.openai.common.content.ContentPart;
import io.github.sashirestela.openai.domain.audio.AudioResponseFormat;
import io.github.sashirestela.openai.domain.audio.SpeechRequest;
import io.github.sashirestela.openai.domain.audio.Transcription;
import io.github.sashirestela.openai.domain.audio.TranscriptionRequest;
import io.github.sashirestela.openai.domain.chat.Chat;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.domain.image.ImageRequest;
import io.github.sashirestela.openai.domain.moderation.Moderation;
import io.github.sashirestela.openai.domain.moderation.ModerationRequest;
import io.github.sashirestela.openai.support.Base64Util;

import java.io.FileOutputStream;
import java.net.URL;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class OpenAIUtilities {
   // record class used to simplify passing messages containing a role
   // and corresponding content to this class's chat and translate methods
   public record Message(String role, String content) {}

   // create a SimpleOpenAI client object
   private final static SimpleOpenAI openAI = SimpleOpenAI.builder()
      .apiKey(System.getenv("OPENAI_API_KEY")).build();

   // perform a chat completion
   public static String chat(String chatModel, List<Message> messages) {
      // start building a ChatRequest
      var chatRequest = ChatRequest.builder().model(chatModel);

      // add messages to the ChatRequest
      for (var currentMessage : makeChatMessages(messages)) {
         chatRequest = chatRequest.message(currentMessage);
      }

      var request = chatRequest.build(); // complete building the request

      // make the ChatRequest and get the Chat response
      var chatResponse = openAI.chatCompletions().create(request).join();

      return chatResponse.firstContent(); // return the response text
   }

   // utility method to convert our Message record objects into
   // SystemMessage, UserMessage and AssistantMessage objects
   private static List<ChatMessage> makeChatMessages(
      List<Message> messages) {
      // convert each Message record to a ChatMessage subclass object
      return messages.stream()
         .map(m -> switch(m.role().toLowerCase()) {
            case "system" ->
               ChatMessage.SystemMessage.of(m.content());
            case "user" -> ChatMessage.UserMessage.of(m.content());
            case "assistant" ->
               ChatMessage.AssistantMessage.of(m.content());
            default -> throw new IllegalArgumentException(
               "invalid role: " + m.role());
         })
         .toList();
   }

   // perform a chat completion
   public static String describeImage(
      String chatModel, String prompt, Path imagePath) {
      // start building a ChatRequest for accessible image descriptions
      var chatRequest = ChatRequest.builder()
         .model(chatModel)
         .message(ChatMessage.SystemMessage.of("""
            You are an expert at creating accessible descriptions
            of images per the World Wide Web Consortium's (W3C's) 
            guidelines. Given an image, explain it in detail for 
            people who are blind or have low vision."""))
         .message(ChatMessage.UserMessage.of(
            List.of(
               ContentPart.ContentPartText.of(prompt),
               ContentPart.ContentPartImageUrl.of(
                  ContentPart.ContentPartImageUrl.ImageUrl.of(
                     Base64Util.encode(imagePath.toString(),
                        Base64Util.MediaType.IMAGE))))))
         .build();

      // make the ChatRequest and get the Chat response
      var chatResponse =
         openAI.chatCompletions().create(chatRequest).join();

      return chatResponse.firstContent(); // return the response text
   }

   // perform a chat completion for translation
   public static String translate(
      String chatModel, String textToTranslate, String targetLanguage) {

      // build a ChatRequest to translate a String
      var chatRequest = ChatRequest.builder().model(chatModel)
         .message(ChatMessage.SystemMessage.of(String.format("""
            You are an expert in natural language translation.
            Translate the following text into %s.""", targetLanguage)))
         .message(ChatMessage.UserMessage.of(textToTranslate))
         .build();

      // create and submit the ChatRequest and wait for the Chat response
      Chat response = openAI.chatCompletions().create(chatRequest).join();

      return response.firstContent(); // return the translation
   }

   // record classes to define the structure of a JSON response
   // containing results of named entity recognition
   public record NamedEntity(String text, String tag) {}
   public record NamedEntities(List<NamedEntity> entities) {}

   // generic method that performs a chat completion and
   // returns structured output
   public static <T> T chatWithStructuredOutput(String chatModel,
      List<Message> messages, Class<T> jsonStructure) throws Exception {
      // set up JSON schema for structured output based on jsonStructure
      var schema = ResponseFormat.jsonSchema(JsonSchema.builder()
         .name(jsonStructure.getSimpleName()) // schema name
         .schemaClass(jsonStructure) // schema Java class
         .build()
      );

      // start building a ChatRequest that returns structured output
      var chatRequest = ChatRequest.builder()
         .model(chatModel)
         .responseFormat(schema);

      // add messages to the ChatRequest
      for (var currentMessage : makeChatMessages(messages)) {
         chatRequest = chatRequest.message(currentMessage);
      }

      var request = chatRequest.build(); // complete building the request

      // make the ChatRequest and get the Chat response
      Chat response = openAI.chatCompletions().create(request).join();

      // ObjectMapper for deserializing JSON into a NamedEntities object
      var mapper = new ObjectMapper();

      // return the structured response
      return mapper.readValue(response.firstContent(), jsonStructure);
   }

   // convert audio to text transcription
   public static String speechToText(String model, String audioPath) {
      // build a TranscriptionRequest
      var audioRequest = TranscriptionRequest.builder()
         .file(Paths.get(audioPath))
         .model(model)
         .build();

      // submit TranscriptionRequest and wait for Transcription
      Transcription transcription =
         openAI.audios().transcribe(audioRequest).join();
      return transcription.getText(); // return transcription text
   }

   // synthesize speech from text
   public static void textToSpeech(String model, String text,
      String voice, String filename) throws Exception {
      // build a SpeechRequest
      var speechRequest = SpeechRequest.builder()
         .model(model)
         .input(text)
         .voice(Voice.valueOf(voice.toUpperCase()))
         .build();

      // submit SpeechRequest and wait for audio, then save audio to file
      try (var audioStream = openAI.audios().speak(speechRequest).join();
           var audioFile = new FileOutputStream(filename)) {
         audioFile.write(audioStream.readAllBytes());
         System.out.printf("Wrote audio to %s%n", filename);
      }
   }

   // generate an image with Dall-E
   public static void image(
      String model, String prompt, Path imagePath) throws Exception {
      // build an ImageRequest with the specified model and prompt
      var imageRequest = ImageRequest.builder()
         .model(model)
         .prompt(prompt)
         .quality(ImageRequest.Quality.HD)
         .build();

      // submit ImageRequest and wait for image
      var imageResponse = openAI.images().create(imageRequest).join();

      // get image's URL
      String urlString = imageResponse.getFirst().getUrl();
      URL url = URI.create(urlString).toURL();

      // save image to PNG file
      try (var inputStream = url.openStream()) {
         Files.copy(inputStream, imagePath,
            StandardCopyOption.REPLACE_EXISTING);
         System.out.printf("Wrote image to %s%n", imagePath);
      }
   }

   // convert audio track of a video into closed captions
   public static String speechToVTT(String model, Path audioPath) {
      // build a TranscriptionRequest that returns JSON containing the
      // transcription segments and their start/end times
      var audioRequest = TranscriptionRequest.builder()
         .file(audioPath)
         .model(model)
         .responseFormat(AudioResponseFormat.VERBOSE_JSON)
         .build();

      // submit TranscriptionRequest and wait for Transcription
      Transcription transcription =
         openAI.audios().transcribe(audioRequest).join();

      // get the transcript's segments
      List<Transcription.Segment> segments = transcription.getSegments();

      // create a String representation of the transcription's
      // segments with their start and end times
      String transcriptionText = segments.stream()
         .map(segment -> String.format("Start: %f, End: %f, Text: %s",
            segment.getStart(), segment.getEnd(), segment.getText()))
         .collect(Collectors.joining("\n"));

      // issue a chat request to convert the audio
      // segment transcriptions into VTT format
      var request = ChatRequest.builder()
         .model("gpt-4o")
         .message(ChatMessage.SystemMessage.of("""
            You are a subtitle formatting assistant. Take the following
            transcription data and format it into VTT (WebVTT) format.
            Timestamps must have the hh:mm:ss.mmm format required by
            WebVTT. Include the required WEBVTT header at beginning of
            the file. Output the VTT content only."""))
         .message(ChatMessage.UserMessage.of(transcriptionText))
         .build();

      // make the ChatRequest and get the response
      var response = openAI.chatCompletions().create(request).join();

      // remove markdown code-block delimiters (```) at beginning
      // and end of response and return VTT-formatted captions
      var captions = response.firstContent();
      return captions.substring(3, captions.length() - 3);
   }

   // send text to OpenAI moderation API to check for offensive content;
   public static Moderation.ModerationResult checkPrompt(String prompt) {
      // build a ModerationRequest
      var moderationRequest = ModerationRequest.builder()
         .model("omni-moderation-latest") // OpenAI recommended model
         .input(List.of(prompt))
         .build();

      // submit the request and return the result
      var result = openAI.moderations().create(moderationRequest).join();
      return result.getResults().getFirst();
   }
}