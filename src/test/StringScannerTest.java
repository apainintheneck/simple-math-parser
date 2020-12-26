package test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mathTree.StringScanner;

//Tests for the StringScanner.java class in the mathTree package.

class StringScannerTest
{
   public static StringScanner scanner;
   
   LinkedList<String> output;
   
   String sentence1 = "    This   is a    sentence  ";
   String sentence2 = "Welcome, home.";
   String sentence3 = "Just`another`sentence ";
   String sentence4 = "(if   the   world`does`end at all?)";
   
   @BeforeAll
   public static void setUp() 
   {  
      scanner = new StringScanner();
      
      //Set up scanner
      scanner.skipWhitespace();
      char[] specialChars = {'.', ',', '?', ';', '(', ')'};
      scanner.addSpecialChar(specialChars);
      scanner.addDelimiter('`');
   }

   @Test
   public void testSkipWhitespace()
   {
      output = scanner.scan(sentence1);
      
      assertEquals("This", output.pollFirst());
      assertEquals("is", output.pollFirst());
      assertEquals("a", output.pollFirst());
      assertEquals("sentence", output.pollFirst());
   }
   
   @Test
   public void testSpecialChar()
   {
      output = scanner.scan(sentence2);
      
      assertEquals("Welcome", output.pollFirst());
      assertEquals(",", output.pollFirst());
      assertEquals("home", output.pollFirst());
      assertEquals(".", output.pollFirst());
   }
   
   @Test
   public void testDelimeter()
   {
      output = scanner.scan(sentence3);
      
      assertEquals("Just", output.pollFirst());
      assertEquals("another", output.pollFirst());
      assertEquals("sentence", output.pollFirst());
   }
   
   @Test
   public void testAll()
   {
      output = scanner.scan(sentence4);
      
      assertEquals("(", output.pollFirst());
      assertEquals("if", output.pollFirst());
      assertEquals("the", output.pollFirst());
      assertEquals("world", output.pollFirst());
      assertEquals("does", output.pollFirst());
      assertEquals("end", output.pollFirst());
      assertEquals("at", output.pollFirst());
      assertEquals("all", output.pollFirst());
      assertEquals("?", output.pollFirst());
      assertEquals(")", output.pollFirst());
   }

}
