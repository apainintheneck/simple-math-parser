package mathNode;

public abstract class Operator extends Expression
{
   public Expression leftNode = null;
   public Expression rightNode = null;
   protected int precedence;
   
   public int getPrecedence() { return precedence; }
   public void setParens(boolean bool) 
   { 
      parenthesis = bool; 
      
      if(parenthesis)
         precedence = 0;
   }
   public boolean checkTree() 
   { 
      if(leftNode == null || rightNode == null)
         return false;
      else
         return leftNode.checkTree() && rightNode.checkTree();
   }
}
