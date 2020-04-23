import java.util.Scanner;

/**
 * This is the main for this calculator app. It features the MathTree class for 
 * parsing and calculation.
 * @author kevinrobell
 *
 */
public class CalcApp
{
   static MathTree calcTree = new MathTree();
   
   public static void main(String[] args)
   {
      String mathExpression;
      
      if(args.length < 1)
      {
         Scanner in = new Scanner(System.in);
         String inputStr = "";
         
         while(inputStr.isBlank())
            inputStr = in.nextLine();
         
         mathExpression = inputStr;
         
         in.close();
      }
      else
      {
         mathExpression = args[0];
      }
      
      if(calcTree.init(mathExpression))
         System.out.println(calcTree.solve());
   }

}
