import java.util.LinkedList;

/**
 * This is a math parsing tree that uses the mathNode package for the nodes.
 * @author kevinrobell
 *
 */
public class MathTree
{
   private mathNode.Expression rootNode = null;
   private StringScanner strScanner = new StringScanner();
   private mathNode.Factory nodeFactory = new mathNode.Factory();
   
   /**
    * Constructor that sets up the strScanner.
    */
   public MathTree() {
      //Set up StringScanner
      strScanner.skipWhitespace();
      //Includes all special characters except '-' which will be checked for in cleanStrList().
      char[] specialChars = {'(', ')', '+', '*', '/', '^'};
      strScanner.addSpecialChar(specialChars);
   }
   
   /**
    * Takes the statement for processing and building the tree.
    * @param mathStatement
    * @return Returns boolean based upon whether statement is valid and tree can be built.
    */
   public boolean init(String mathStatement)
   {
      LinkedList<String> strList = strScanner.scan(mathStatement);
      
      //strList = cleanStrList(strList);
      cleanStrList(strList);
      
      if(buildTree(strList))
      {
         if(rootNode.checkTree())
            return true;
         else
            System.out.println("Invalid: Unknown expression");
      }

      rootNode = null;
      return false;
   }
   
   /**
    * Cleans the list of string tokens created by the strScanner.
    * Specifically, it discerns between minus and subtraction symbols
    * and inserts multiplication symbols implied by parenthesis. It does
    * this by reference so nothing is returned.
    * @param strList
    */
   private void cleanStrList(LinkedList<String> strList)
   {
      //Create a new scanner for the negative sign '-'
      StringScanner negScanner = new StringScanner();
      negScanner.addSpecialChar('-');
      
      //Create list of operators
      String opStr = "+-*^/";
      
      String tempStr;
      LinkedList<String> newList = new LinkedList();
      
      for(int i = 0; i < strList.size(); i++)
      {
         tempStr = strList.get(i);
         
         //Parse strings with negative signs. Some will become negative signs others substraction.
         if(tempStr.length() > 1 && tempStr.contains("-"))
         {  
            newList = negScanner.scan(tempStr);
            
            if(newList.peekFirst() == "-")
            {
               newList.remove(0);
               newList.set(0, '-' + newList.peekFirst());
            }
            
            strList.remove(i);
            strList.addAll(i, newList);
            
            i += newList.size() - 1;
         }
         //Add implicit multiplication before/after parenthesis
         else if(tempStr == "(" && i > 0)
         {
            String prevStr = strList.get(i - 1);
            
            if(!opStr.contains(prevStr) || prevStr != "(")
            {
               strList.add(i, "*");
               i++;
            }
         }
         else if(tempStr == ")" && i < strList.size() - 1)
         {
            String nextStr = strList.get(i + 1);
            
            if(!opStr.contains(nextStr) || nextStr != ")")
            {
               strList.add(i + 1, "*");
               i++;
            }
         }
      }

   }
   
   /**
    * A basic tree building function that calls the main tree building function below.
    * Returns false if math statement had errors.
    * @param strTokens
    * @return A boolean indicating whether the tree was built correctly.
    */
   private boolean buildTree(LinkedList<String> strTokens)
   {
      rootNode = buildTree(strTokens, false);

      if(rootNode == null)
         return false;
      else 
         return true;
   }
   
   /**
    * Function that builds trees of math nodes. Returns null if empty or invalid.
    * @param strTokens
    * @param isParens
    * @return The root node of a new tree of math nodes.
    */
   private mathNode.Expression buildTree(LinkedList<String> strTokens, boolean isParens)
   {
      String token;
      mathNode.Expression rootNode = null;
      mathNode.Expression lastNode = null;
      mathNode.Expression newNode = null;
      
      while(strTokens.isEmpty() == false) 
      {
         token = strTokens.poll();
         
         //Handle closed parenthesis
         if(token == ")") 
         {
            if(isParens && rootNode == null)
            {
               System.out.println("Invalid: Empty parenthesis");
               return null;
            }
            else if(!isParens)
            {
               System.out.println("Invalid: Missing \"(\"");
               return null;
            }
            else
            {
               rootNode.setParens(true);
               return rootNode;
            }
         }
         
         //Handle open parenthesis
         if(token == "(")
         {
            /* Shouldn't be necessary because of cleanStrList() lines 88-98
            if(lastNode != null && !(lastNode instanceof mathNode.Operator))
            {
               lastNode = nodeFactory.buildNode('*');
               rootNode = insertNode(rootNode, lastNode);
            }
            */
            
            newNode = buildTree(strTokens, true);
            if(newNode == null) 
               return null;
            
            rootNode = insertNode(rootNode, newNode);
         }
         
         newNode = nodeFactory.buildNode(token);
         if(newNode == null)
         {
            System.out.println("Invalid: Unknown expression \"" + token + "\"");
            return null;
         } 
         else
            rootNode = insertNode(rootNode, newNode);
         
         if(rootNode == null)
            return null;
         else
            lastNode = newNode;
         
      }
      
      if(isParens)
      {
         System.out.println("Invalid: Missing \")\"");
         return null;
      }
      else
         return rootNode;
   }
   
   /**
    * Inserts the new node into the tree of the root node.
    * @param rootNode
    * @param newNode
    * @return Returns root node of tree after node is inserted.
    */
   private mathNode.Expression insertNode(mathNode.Expression rootNode, 
         mathNode.Expression newNode)
   {
      //If no root node, new node becomes the root node.
      if(rootNode == null) 
         return newNode;
      //If no new node, return tree without changes.
      else if(newNode == null)
         return rootNode;
      //Place operator node in tree according to precedence.
      else if(newNode instanceof mathNode.Operator && !newNode.isParens())
      {
         mathNode.Operator newOperator = (mathNode.Operator) newNode;
         mathNode.Operator parent;
         
         if(rootNode instanceof mathNode.Operator)
            parent = (mathNode.Operator) rootNode;
         else
         {
            newOperator.leftNode = rootNode;
            return newOperator;
         }
         
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
         
         if(parent.rightNode == null) 
         {
            System.out.println("Invalid: Missing value between two operators");
            return null;
         }
         else
         {
            newOperator.leftNode = parent.rightNode;
            parent.rightNode = newOperator;
            return rootNode;
         }
         
      }
      //Place Int, Dec, or parenthesis node in tree.
      else
      {
         mathNode.Operator parent;
         
         if(rootNode instanceof mathNode.Operator)
            parent = (mathNode.Operator) rootNode;
         else
         {
            System.out.println("Invalid: Missing operator between " + 
                  rootNode + " + " + newNode);
            return null;
         }
         
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
   
   /**
    * Calls recursive mathNode.Expression.calculate() method to find answer. If tree is empty,
    * it return null.
    */
   public Number solve() 
   { 
      if(rootNode == null)
         return null;
      else
         return rootNode.calculate(); 
   }
   
   /**
    * Calls recursive mathNode.Expression.toString() method to find answer. If tree is empty,
    * it return null.
    */
   public String toString()
   {
      if(rootNode == null) 
         return "";
      else
         return rootNode.toString();
   }
   
}
