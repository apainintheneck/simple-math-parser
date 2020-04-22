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
      rootNode = buildTree(strTokens, false);

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
         
         if(token == ")") 
         {
            if(isParens && rootNode == null)
            {
               System.out.println("Invalid: Empty parenthesis.");
               return null;
            }
            else
            {
               rootNode.setParens(true);
               return rootNode;
            }
         }
         
         if(token == "(")
         {
            if(lastNode != null && !(lastNode instanceof mathNode.Operator))
            {
               lastNode = nodeFactory.buildNode('*');
               rootNode = insertNode(rootNode, lastNode);
            }
            
            lastNode = buildTree(strTokens, true);
            rootNode = insertNode(rootNode, lastNode);
            
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
            rootNode = insertNode(rootNode, lastNode);
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
   
   private mathNode.Expression insertNode(mathNode.Expression rootNode, 
         mathNode.Expression newNode)
   {
      if(rootNode == null) 
         return newNode;
      else if(newNode == null)
         return rootNode;
      else if(newNode instanceof mathNode.Operator)
      {
         mathNode.Operator newOperator = (mathNode.Operator) newNode;
         
         mathNode.Operator parent = (mathNode.Operator) rootNode;
         
         if(parent.getPrecedence() <= newOperator.getPrecedence())
         {
            newOperator.leftNode = parent;
            return newOperator;
         }
         //Not sure about this part.
         while(parent.getPrecedence() > newOperator.getPrecedence() &&
               parent.rightNode != null && parent.rightNode instanceof mathNode.Operator)
         {
            parent = (mathNode.Operator) parent.rightNode;
         }
         
         newOperator.leftNode = parent.rightNode;
         parent.rightNode = newOperator;
         return rootNode;
         
      }
      else
      {
         mathNode.Operator parent = (mathNode.Operator) rootNode;
         
         while(parent.rightNode != null)
         {
            if(parent.rightNode instanceof mathNode.Operator)
               parent = (mathNode.Operator) parent.rightNode;
            else
            {
               System.out.println("Invalid: Missing operator between " + 
                     parent.rightNode + " + " + newNode);
               return null;
            }
         }
         
         parent.rightNode = newNode;
         return rootNode;
      }
   }
   
   public void calculate()
   {
      answer = rootNode.calculate();
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
