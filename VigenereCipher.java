import java.lang.String;

/*
 * This program looks at command line arguments (which should be three), handles errors, and 
 * returns the vigenere ciphered word after taking in two words, the plaintext and the keyword.
 * Repeats the keyword to fit the length of the plaintext, and adds the two ASCII characters (int values)
 * to form a new char. Both encodes and decodes depending on command line argument (user input).

 @author: Jinny Eo
 */

public class VigenereCipherNew {

  /*
   * error handling for cases where the number of command line 
   * arguments is insufficient
   */
  public static void errorHandling(String[] args) {
    if (args.length != 3) {
      System.err.println ("Incorrect number of parameters ");
      System.exit(2);
    }       
  }


  /*
   * takes in a string and an integer, and repeats the string
   * until its length reaches the integer number (in this case, the integer
   * represents the length of the plainText input)
   */
  public static char[] replicate(String word, int len) {
    // repeats the word depending on the length of the plaintext and keyword
    int reps = (len / word.length()) + 1;
    String full = word.repeat(reps);
    // cuts the formulated string so that it fits the length of the plaintext
    String str = full.substring(0, len);
    char[] replicate = str.toCharArray();

    return replicate;
  }


  /*
   * encodes or decodes each letter of the plainTextArr array by adding/subtracting
   * the ASCII values, depending on whether the boolean isEncode is true or false
   */
  public static char[] cipherHelper(char[] replicate, char[] plainTextArr, 
  char[] result, int base, int len, boolean isEncode) {
    int en;
    // for every character in the char array, encodes or decodes character
    for (int i = 0; i < len; i++) {
      // subtract base so that it aligns with the array
      int rep = (int) replicate[i] - base;
      int plain = (int) plainTextArr[i] - base;
      
      // if isEncode, then add the int values of the characters then mod 26 so 
      // it fits as a letter in the lowercase alphabet
      if (isEncode) {
        en = (rep + plain) % 26;
      }
      // if decoding, then first determine whether the subtracted char value is less than
      // 0, in order for it to wrap around successfully
      else {
        if (plain - rep >= 0) {
          en = (plain - rep) % 26;
        }
        else {
          en = 26 + plain - rep;
        }
      }
      // the encoded integer is added to the base value so that it fits in the lower
      // case alphabet
      result[i] = (char) (en + base);
    }

    return result;
  }



  /*  
   * encodes or decodes the plainText using keyWord depending on whether the
   * boolean isEncode is true or false, while initializing necessary arrays and values (
   * many variables)
   */
  public static String cipher(String plainText, String keyWord, boolean isEncode) {
    // initializes length and base, which is the number value of a
    int len = plainText.length();
    int base = (int) 'a';

    // call replicate function to replicate the keyword
    char[] plainTextArr = plainText.toCharArray();
    char[] replicate = new char[len];
    char[] result = new char[len];

    // if not empty string, calls necessary functions to encode each letter of plaintext
    if (len != 0) {
      replicate = replicate(keyWord, len);
      result = cipherHelper(replicate, plainTextArr, result, base, len, isEncode);
    }
    // if empty string, the result is the plaintext
    else {
      result = plainTextArr;
    }

    // save array result to string
    String resultString = new String(result);
    return resultString;
  }




  public static void main (String[] args) {
    java.io.PrintWriter pen;
    pen = new java.io.PrintWriter(System.out, true);

    errorHandling(args);

    // assign commands to easy variables
    String plainText = args[1];
    String keyWord = args[2];
    // declare boolean
    boolean isEncode;
    // initialize resultString
    String result = "";

    // looks at command line args and determine whether to encode or decode 
    if ("encode".equals(args[0])) {
      isEncode = true;
      result = cipher(plainText, keyWord, isEncode);
    }
    else if ("decode".equals(args[0])) {
      isEncode = false;
      result = cipher(plainText, keyWord, isEncode);
    }
    else {
      System.err.println ("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    }
    // print encoded string
    pen.println(result);
  }
  
}
