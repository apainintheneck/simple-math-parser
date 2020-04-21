package mathNodes;

public abstract class Operator extends Expression
{
   public Expression leftNode = null;
   public Expression rightNode = null;
   private int precedence;
   
   public int getPrecedence() { return precedence; }
}
