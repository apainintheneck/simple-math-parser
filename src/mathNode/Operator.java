package mathNode;

public abstract class Operator extends Expression
{
   private Expression leftNode = null;
   private Expression rightNode = null;
   protected int precedence;
   
   public int getPrecedence() { return precedence; }
   
   public Expression getLeftNode() { return leftNode; }
   public Expression getRightNode() { return rightNode; }
   
   public void setLeftNode(Expression newNode) { leftNode = newNode; }
   public void setRightNode(Expression newNode) { rightNode = newNode; }
   
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
