import java.util.LinkedList;

//A testing main for the StringScanner class
public class testStringScanner
{
   static StringScanner scanner = new StringScanner();
   
   public static void main(String[] args) 
   {
      //Set up scanner
      scanner.skipWhitespace();
      char[] specialChars = {'.', ',', '?', ';', '(', ')'};
      scanner.addSpecialChar(specialChars);
      
      String sentence1 = "This is a sentence that will never end; it ends here.";
      String sentence2 = "Once in a while, in my opinion, you must quit.";
      String sentence3 = "The end of world(if the world does end at all?) may be soon.";
      
      scanAndPrint(sentence1);
      scanAndPrint(sentence2);
      scanAndPrint(sentence3);

   }
   
   //This class prints the original string and then the scanned string.
   public static void scanAndPrint(String inputStr) 
   {
      
      System.out.println("\nOriginal string:\n" + inputStr);
      
      LinkedList<String> scannedStr = scanner.scan(inputStr);
      
      System.out.println("\nScanned string:\n");
      for(String str : scannedStr)
         System.out.println("> " + str);
      
      System.out.println("\n-------------------------\n");
   }

}
