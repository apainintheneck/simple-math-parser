package mathNodes;

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
}
