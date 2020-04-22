/**
 * This is the test main for MathTree class. It features the MathTree class for 
 * parsing and calculation.
 * @author kevinrobell
 *
 */
public class testMathTree
{
   static MathTree calcTree = new MathTree();
   
   public static void main(String[] args)
   {
      String[] simpleExpressions = new String[7];
      
      simpleExpressions[0] = "4 + 4";
      simpleExpressions[1] = "5 - 6";
      simpleExpressions[2] = "8 * 9";
      simpleExpressions[3] = "5 / 2";
      simpleExpressions[4] = "5 ^ 2";
      simpleExpressions[5] = "5 ^ *";
      simpleExpressions[6] = "5 7 2";
      
      System.out.println("---Simple Expressions---");
      for(String expr : simpleExpressions)
         calculateAndPrint(expr);
      
      String[] complexExpressions = new String[7];
      
      complexExpressions[0] = "4 + 4 - 6 * 2";
      complexExpressions[1] = "4 - 6 * 2^3";
      complexExpressions[2] = "4*4 - 6/2";
      complexExpressions[3] = "8^2 - 6 * 4";
      complexExpressions[4] = "7*8*5- 12";
      complexExpressions[5] = "12+4*7-14";
      complexExpressions[6] = "7-4+5/2";
      
      System.out.println("---Complex Expressions---");
      for(String expr : complexExpressions)
         calculateAndPrint(expr);
      
      String[] difficultExpressions = new String[7];
      
      difficultExpressions[0] = "5(6)";
      difficultExpressions[1] = "-5(-6)";
      difficultExpressions[2] = "7 - -7";
      difficultExpressions[3] = "(6 * 8) + 9";
      difficultExpressions[4] = "(6 + 8) 9";
      difficultExpressions[5] = ")5+6";
      difficultExpressions[6] = "--6 + 7";
      
      System.out.println("---Difficult Expressions---");
      for(String expr : difficultExpressions)
         calculateAndPrint(expr);
   }
   
   public static void calculateAndPrint(String expression)
   {
      System.out.println("Original: " + expression);
      
      if(calcTree.init(expression))
      {
         System.out.println("Print tree: " + calcTree);
         System.out.println("Solution: " + calcTree.solve());
      }
      
      System.out.println();
   }

}