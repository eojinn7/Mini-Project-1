import java.lang.String;

/* Takes */

public class CaesarCipherNew {

  /*
   * errorhandling for when user inputs incorrect number of command line arguments
   */
  public static void errorHandling(String[] args) {
    if (args.length != 2) {
      System.err.println ("Incorrect number of parameters ");
      System.exit(2);
    }       
  }

  /*
   * encodes the string str by saving it to an intArray and then converting to char data type, using 
   * a for loop iterating over the array made from the string
   */
  public static void encodeString(String str, int[] intArray, char[] encChar, int base, int n) {
    // for loop for every character of string
    for (int i = 0; i < str.length(); i++) {             
      // subtract it with int 'a' to work with arrays starting with index 0   
      int ch = str.charAt(i) - base; 
      int en;

      // using mod and addition to encode, and handling for when the summation is less than 0
      if ((ch + n) >= 0) {
        en = (ch + n) % 26; 
      } else {
        en = 26 + (ch + n); 
      }

      // save 
      intArray[i] = en;
      encChar[i] = (char) (en + base);
    }
  }


  public static void cipher(String str, boolean isEncode) {  
    java.io.PrintWriter pen;
    pen = new java.io.PrintWriter(System.out, true);

    int shifts = 26;
    int base = (int) 'a';

    int[] intArray = new int[str.length()];
    char[] encChar = new char[str.length()];

    if (isEncode) {
      for (int n = 0; n < shifts; n++) {                       
        pen.printf("n = %d: ", n);
        encodeString(str, intArray, encChar, base, n);
        String encString = new String(encChar);
        pen.println(encString);
      }
    }
    else {
      for (int n = shifts; n > 0; n--) {                        
        pen.printf("n = %d: ", 26 - n);
        encodeString(str, intArray, encChar, base, n);
        String encString = new String(encChar); 
        pen.println(encString);
      }
    }

  }


  public static void main(String[] args) {
    
    errorHandling(args);

    String str = args[1];
    boolean isEncode;

    if ("encode".equals(args[0])) {
      isEncode = true;
      cipher(str, isEncode);
    }

    else if ("decode".equals(args[0])) {
      isEncode = false;
      cipher(str, isEncode);
    }

    // instruction is neither encode nor decode error handling
    else { 
      System.err.println ("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    }

    
  }
}


