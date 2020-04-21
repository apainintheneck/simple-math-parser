import java.util.LinkedList;

/**
 * This is a math parsing tree that uses the mathNode package for the nodes.
 * @author kevinrobell
 *
 */
public class MathTree
{
   private mathNode.Expression rootNode = null;
   private Number answer = null;
   private StringScanner strScanner;
   private mathNode.Factory nodeFactory = new mathNode.Factory();
   
   public MathTree() {
      //Set up StringScanner
      strScanner.skipWhitespace();
      char[] specialChars = {'(', ')', '+', '-', '*', '/', '^'};
      strScanner.addSpecialChar(specialChars);
   }
   
   public boolean init(String mathStatement)
   {
      answer = null;
      
      if(buildTree(strScanner.scan(mathStatement)))
         return true;
      else 
      {
         rootNode = null;
         return false;
      }
   }
   
   private boolean buildTree(LinkedList<String> strTokens)
   {
      rootNode =  buildTree(strTokens, false);

      if(rootNode == null)
         return false;
      else 
         return true;
   }
   
   private mathNode.Expression buildTree(LinkedList<String> strTokens, boolean isParens)
   {
      String token;
      mathNode.Expression rootNode = null;
      mathNode.Expression lastNode = null;
      
      while(strTokens.isEmpty() == false) 
      {
         token = strTokens.poll();
         
         if(isParens && token == ")" && rootNode != null) 
         {
            rootNode.setParens(true);
            return rootNode;
         }
         
         if((token == "-" || token == "+") && lastNode instanceof mathNode.Operator)
         {
            if(strTokens.isEmpty()) {
               System.out.println("Invalid: Missing expression after \"" + token + "\".");
               return null;
            }
            else if(token == "-")
               strTokens.set(0, "-" + strTokens.getFirst());
            
            continue;
         }
         
         if(token == "(")
         {
            if(lastNode != null && !(lastNode instanceof mathNode.Operator))
            {
               lastNode = nodeFactory.buildNode('*');
               percolateDown(rootNode, lastNode);
            }
            
            lastNode = buildTree(strTokens, true);
            percolateDown(rootNode, lastNode);
            
            if(lastNode == null) 
               return null;
         }
         
         mathNode.Expression newNode = nodeFactory.buildNode(token);
         if(newNode == null)
         {
            System.out.println("Invalid: Unknown expression \"" + token + "\"");
            return null;
         } 
         else
         {
            lastNode = nodeFactory.buildNode(token);
            percolateDown(rootNode, lastNode);
         }
         
      }
      
      if(isParens)
      {
         System.out.println("Invalid: Missing \")\".");
         return null;
      }
      else
         return rootNode;
   }
   
   private mathNode.Expression percolateDown(mathNode.Expression rootNode, 
         mathNode.Expression newNode)
   {
      if(rootNode == null) 
         return newNode;
      else if(newNode == null)
         return rootNode;
      else if(newNode instanceof mathNode.Operator)
      {
         mathNode.Operator newOperator = (mathNode.Operator) newNode;
      }
      else
      {
         
      }
   }
   
   private void rotateRight(mathNode.Expression parentNode)
   {
      
   }
   
   public boolean calculate()
   {
      
   }
   
   public Number getAnswer() { return answer; }
   
   public String toString()
   {
      if(rootNode == null) 
         return "";
      else
         return rootNode.toString();
   }
   
   
   
}
