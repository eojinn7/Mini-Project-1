
import java.lang.String;

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
    int reps = (len / word.length()) + 1;
    String full = word.repeat(reps);

    String str = full.substring(0, len);
    char[] replicate = str.toCharArray();

    return replicate;
  }

  /*
   * encodes or decodes each letter of the plainTextArr array, depending on whether
   * the boolean isEncode is true or false
   */
  public static char[] cipherHelper(char[] replicate, char[] plainTextArr, 
  char[] result, int base, int len, boolean isEncode) {

    int en;

    for (int i = 0; i < len; i++) {
      int rep = (int) replicate[i] - base;
      int plain = (int) plainTextArr[i] - base;

      if (isEncode) {
        en = (rep + plain) % 26;
      }
      else {
        if (plain - rep >= 0) {
          en = (plain - rep) % 26;
        }
        else {
          en = 26 + plain - rep;
        }
      }
      
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

    int len = plainText.length();
    int base = (int) 'a';

    // call replicate function to replicate the keyword
    char[] plainTextArr = plainText.toCharArray();
    char[] replicate = new char[len];
    char[] result = new char[len];

    if (len != 0) {
      replicate = replicate(keyWord, len);
      result = cipherHelper(replicate, plainTextArr, result, base, len, isEncode);
    }
    else {
      result = plainTextArr;
    }

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

    pen.println(result);

  }
}
