import java.lang.String;

/* Takes command line arguments to implement the Caesar cipher to a word and either decoding or 
 * encoding. Works by shifting each letter of the string down the order of the alphabet.
 * Uses multiple helper functions, such as errorHandling, to break down different parts of the 
 * problem. Doesn't return anything; prints out a singular encoded/decoded string on the terminal. Handles errors
 * when command line arguments are not of expected.

 @author: Jinny Eo
*/

public class CaesarCipher {

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
      } // if statement

      // save encoded value to arrays
      intArray[i] = en;
      encChar[i] = (char) (en + base);
    } // for loop
   
  } // encodeString(String str, int[] intArray, char[] encChar, int base, int n)

/*
 * does the main job of initializing different variables to set up the cipher, and also
 * providing the different paths of algorithms depending on whether the isEncode boolean
 * is true or false
 */
  public static void cipher(String str, boolean isEncode) {  
    java.io.PrintWriter pen;
    pen = new java.io.PrintWriter(System.out, true);
   
    // initialize values for easy reading
    int shifts = 26;
    int base = (int) 'a';
    // initialize arrays to save the characters (as char and as int) into
    int[] intArray = new int[str.length()];
    char[] encChar = new char[str.length()];

    // if statement for whether we are encoding or decoding
    if (isEncode) {
      for (int n = 0; n < shifts; n++) {                       
        pen.printf("n = %d: ", n);
        // call encodeString, which does the actual encoding of each character in string
        encodeString(str, intArray, encChar, base, n);
        String encString = new String(encChar);
        // print encoded string
        pen.println(encString);
      }
    }
    else { 
      // when we are decoding; we go down the value of n
      for (int n = shifts; n > 0; n--) {                        
        pen.printf("n = %d: ", 26 - n);
        // call encodeString
        encodeString(str, intArray, encChar, base, n);
        String encString = new String(encChar); 
        pen.println(encString);
      } // for loop
    } // if statement

  } // cipher(String str, boolean isEncode)


  public static void main(String[] args) {
    // call errorHandling function at the very start
    errorHandling(args);

    // initialize variables, assign commandline arguments to a variable
    String str = args[1];
    boolean isEncode;

    // assess user input with command
    if ("encode".equals(args[0])) {
      // set isEncode to true is user types encode
      isEncode = true;
      cipher(str, isEncode);
    }

    else if ("decode".equals(args[0])) {
      // set isEncode to false is user types decode
      isEncode = false;
      cipher(str, isEncode);
    }

    // instruction is neither encode nor decode error handling
    else { 
      System.err.println ("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    } // if statement series 
   
  } // main(String[] args)

  
}


