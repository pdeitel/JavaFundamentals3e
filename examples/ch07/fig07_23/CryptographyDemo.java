// Fig. 7.23: CryptographyDemo.java
// Encrypting and decrypting messages with AES.
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
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

         // encrypt the plaintext
         String ciphertext = encrypt(plaintext, secretKey);
         System.out.printf("%nEncrypted:%n%s%n", ciphertext);

         // decrypt the text
         System.out.printf("%nDecrypted:%n%s%n", 
            decrypt(ciphertext, secretKey));

         // decrypt ciphertext entered by the user
         System.out.println("\nEnter the ciphertext to decipher:");
         ciphertext = scanner.nextLine();
         System.out.printf("%nDecrypted:%n%s%n", 
            decrypt(ciphertext, secretKey));
      }
      catch (Exception ex) { 
         System.out.printf("""
            An exception occurred during encryption/decryption:
            %s%n
            """, ex);
      }
   }

   // Generate and return a SecretKey. Note: We are seeding a 
   // SecureRandom object for reproducibility so you can decipher this 
   // chapter's encrypted text. Don't do this in production code.
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