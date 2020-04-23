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
      
      //If there are no arguments, receive input from the console until you get an argument.
      if(args.length < 1)
      {
         Scanner in = new Scanner(System.in);
         String inputStr = "";
         
         while(inputStr.isBlank())
            inputStr = in.nextLine();
         
         mathExpression = inputStr;
         
         in.close();
      }
      //If there is an argument, parse the first one.
      else
      {
         mathExpression = args[0];
      }
      
      //Initialize the MathTree and print the solution if the expression is valid.
      if(calcTree.init(mathExpression))
         System.out.println(calcTree.solve());
   }

}
