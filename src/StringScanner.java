import java.util.HashSet;
import java.util.LinkedList;

/**
 * This classes breaks up strings based upon given special characters and delimeters.
 * @author kevinrobell
 *
 */
public class StringScanner
{
   private LinkedList<String> tokenList = new LinkedList();
   private HashSet<Character> delimSet = new HashSet(); //Set of delimiters
   private HashSet<Character> specCharSet = new HashSet(); //Set of special characters
   private boolean skipWhitespace = false;
   
   /**
    * Sets delimiter to skip all chars according to the Character.isWhitespace() method.
    */
   public void skipWhitespace() { skipWhitespace = true; }
   
   public void addDelimiter(char delim) { delimSet.add(delim); }
   public void addDelimiter(char[] delimArray) {
      for(char ch : delimArray)
         delimSet.add(ch);
   }
   
   /**
    * Add character that should become individual token when scanned.
    * @param specialChar 
    */
   public void addSpecialChar(char specialChar) { specCharSet.add(specialChar); }
   /**
    * Add array of characters that should become individual tokens when scanned.
    * @param specialCharArray
    */
   public void addSpecialChar(char[] specialCharArray) {
      for(char ch : specialCharArray)
         specCharSet.add(ch); 
   }
   
   /**
    * Scans string and breaks it up following the delimiters and special characters.
    * Remember that delimiters have precedence over special characters. If there are
    * any characters set as both, they will be interpreted as delimiters.
    * @param inputStr
    * @return LinkedList of Strings
    */
   public LinkedList<String> scan(String inputStr){
      tokenList.clear();
      
      char ch;
      String token = "";
      for(int i = 0; i < inputStr.length(); i++) {
         ch = inputStr.charAt(i);
         
         if(isDelim(ch)) {
            saveToken(token);
            token = "";
         } else if(specCharSet.contains(ch)) {
            saveToken(token);
            token = "";
         } else {
            token = token + ch;
         }
            
      }
      
      saveToken(token);
      
      return tokenList;
   }
   
   private boolean isDelim(char ch) {
      return (skipWhitespace && Character.isWhitespace(ch)) || delimSet.contains(ch);
   }
   
   private void saveToken(String token) {
      if(token.isEmpty() == false)
         tokenList.add(token);
   }
   
}
