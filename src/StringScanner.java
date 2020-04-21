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
   
   public void skipWhitespace() { skipWhitespace = true; }
   
   public void addDelimiter(char delim) { delimSet.add(delim); }
   public void addDelimiter(char[] delimArray) {
      for(char ch : delimArray)
         delimSet.add(ch);
   }
   
   public void addSpecialChar(char specialChar) { specCharSet.add(specialChar); }
   public void addSpecialChar(char[] specialCharArray) {
      for(char ch : specialCharArray)
         specCharSet.add(ch); 
   }
   
   public LinkedList<String> scan(String inputStr){
      char ch;
      String token = "";
      for(int i = 0; i < inputStr.length(); i++) {
         ch = inputStr.charAt(i);
         
         if(skipWhitespace && Character.isWhitespace(ch)) {
            saveToken(token);
            token = "";
         } else
         if(delimSet.contains(ch)) {
            saveToken(token);
            token = "";
         }
         
         if()
            
      }
   }
   
   private boolean isDelim(char ch) {
      return (skipWhitespace && Character.isWhitespace(ch)) ? true : delimSet.contains(ch);
   }
   
   private void saveToken(String token) {
      if(token.isEmpty() == false)
         tokenList.add(token);
   }
   
}
