// Fig. 11.11: CryptographyDemo.java
// Encrypting and decrypting messages with AES, and 
// using RSA to encrypt and decrypt the AES secret key.
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class CryptographyDemo {
   public static void main(String[] args) {
      var scanner = new Scanner(System.in);

      System.out.println("Enter the text to encrypt:");
      String plaintext = scanner.nextLine();

      System.out.println(
         "\nEnter the secret key seed (for reproducibility):");
      String seedString = scanner.nextLine();

      try { // encryption/decryption might throw exceptions
         // generate a reproducible AES key from the seedString
         SecretKey secretKey = generateKey(seedString);
         System.out.printf("%nSecretKey:%n%s%n", 
            Base64.getEncoder().encodeToString(secretKey.getEncoded()));

         // AES encrypt the plaintext
         String ciphertext = encrypt(plaintext, secretKey);
         System.out.printf("%nEncrypted message:%n%s%n", ciphertext);

         // generate RSA key pair -- normally done by message receiver
         var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
         keyPairGenerator.initialize(2048); // set key size to 2048 bits
         KeyPair rsaKeyPair = keyPairGenerator.generateKeyPair();

         // get RSA public and private keys -- recipient sends the  
         // public key to anyone who needs to send them something securely
         PublicKey publicKey = rsaKeyPair.getPublic();
         PrivateKey privateKey = rsaKeyPair.getPrivate();

         // encrypt the AES secret key with the RSA public key;
         // this would be sent to recipient with the encrypted message
         String encryptedAESKey = 
            encryptRSA(secretKey.getEncoded(), publicKey);
         System.out.printf(
            "%nRSA Encrypted AES SecretKey:%n%s%n", encryptedAESKey);

         // receiver decrypts AES secret key using RSA private key
         SecretKey decryptedSecretKey = 
            decryptRSA(encryptedAESKey, privateKey);
         System.out.printf("%nDecrypted AES SecretKey:%n%s%n", 
            Base64.getEncoder().encodeToString(
               decryptedSecretKey.getEncoded()));

         // AES decrypt the text
         System.out.printf("%nDecrypted:%n%s%n", 
            decrypt(ciphertext, decryptedSecretKey));
      }
      catch (Exception ex) { 
         System.out.printf("""
            An exception occurred during encryption/decryption:
            %s%n
            """, ex);
      }
   }

   // Generate and return a SecretKey. Note: We are seeding a 
   // SecureRandom object for reproducibility so you get the same results 
   // we show in this program's output. Don't do this in production code.
   public static SecretKey generateKey(String seed) throws Exception {
      // get a secure random number generator
      var random = SecureRandom.getInstance("SHA1PRNG");
      random.setSeed(seed.getBytes()); // for demo reproducibility

      // get an AES SecretKey generator
      var keyGen = KeyGenerator.getInstance("AES");
      keyGen.init(256, random); // set up for 256-bit keys
      return keyGen.generateKey(); // generate and return new SecretKey
   }

   // encrypt a String using AES
   public static String encrypt(
      String plaintext, SecretKey secretKey) throws Exception {

      var cipher = Cipher.getInstance("AES"); // get a Cipher object
      cipher.init(Cipher.ENCRYPT_MODE, secretKey); // configure to encrypt

      // encrypt the byte[] representation of plaintext
      byte[] encrypted = cipher.doFinal(plaintext.getBytes());

      // Base64 encode encrypted as a ciphertext String and return 
      return Base64.getEncoder().encodeToString(encrypted);
   }

   // decrypt an AES-encrypted String
   public static String decrypt(
      String ciphertext, SecretKey secretKey) throws Exception {

      var cipher = Cipher.getInstance("AES"); // get a Cipher object
      cipher.init(Cipher.DECRYPT_MODE, secretKey); // configure to decrypt

      // Base64 decode ciphertext to byte array  
      byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);

      // create plaintext String from ciphertextBytes and return it
      return new String(cipher.doFinal(ciphertextBytes));
   }
  
   // encrypt data using RSA public key -- the result of this method 
   // would be sent to the AES encrypted message's receiver
   public static String encryptRSA(byte[] data, PublicKey publicKey) 
      throws Exception {

      var cipher = Cipher.getInstance("RSA"); // create RSA Cipher object
      
      // initialize cipher in encryption mode with recipient's public key
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      
      // encrypt data (the AES key) using the RSA public key
      byte[] encryptedData = cipher.doFinal(data);
      
      // Base64 encode the encrypted AES key 
      return Base64.getEncoder().encodeToString(encryptedData);
   }

   // decrypt data using RSA private key and return the SecretKey
   public static SecretKey decryptRSA(
      String encryptedData, PrivateKey privateKey) throws Exception {

      var cipher = Cipher.getInstance("RSA"); // create RSA Cipher object
      
      // initialize cipher in decryption mode with recipient's private key
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      
      // decode the encrypted AES key from Base64 format and decrypt it
      byte[] decryptedData = 
         cipher.doFinal(Base64.getDecoder().decode(encryptedData));
      
      // convert decrypted byte array back to an AES SecretKey object 
      return new SecretKeySpec(
         decryptedData, 0, decryptedData.length, "AES");
   }
}

/*
 **************************************************************************
 * (C) Copyright 1992-2025 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 **************************************************************************
*/
