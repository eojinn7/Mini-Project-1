package MP1;
import java.lang.String;

public class VigenereCipher {

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

  public static void cipherHlper(char[] replicate, char[] keyWordArr, int base) {

  }

  /*
   * encodes or decodes the plainText using keyWord depending on whether the
   * boolean isEncode is true or false
   */
  public static String cipher(String plainText, String keyWord, boolean isEncode) {
    int len = plainText.length();
    int base = (int) 'a';

    // call replicate function to replicate the keyword
    char[] replicate = replicate(keyWord, len);
    char[] keyWordArr = keyWord.toCharArray();
    char[] result = new char[len];
    int en;
    
    if (isEncode) {
      for (int i = 0; i < len; i++) {
        int rep = replicate[i] - base;
        int keyw = keyWordArr[i] - base;
        if ((rep - keyw) >= 0) {
          en = (rep - keyw) % 26;
        } else {
          en = 26 + (rep - keyw);
        }
        result[i] = (char) en;
      }
    }

    else {
      for (int i = 0; i < len; i++) {
        int rep = replicate[i] - base;
        int keyw = keyWordArr[i] - base;
        if ((rep - keyw) >= 0) {
          en = (rep + keyw) % 26;
        } else {
          en = 26 + (rep + keyw);
        }
        result[i] = (char) en;
      }
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
