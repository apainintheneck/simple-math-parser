package mathNode;

public class Div extends Operator
{
   public Div() { precedence = 2; }
   
   public Number calculate()
   {
      return leftNode.calculate().doubleValue() / rightNode.calculate().doubleValue();
   }
   
   public String toString()
   {
      return leftNode.toString() + " / " + rightNode.toString();
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      Div clone = (Div) super.clone();
      clone.leftNode = (Expression) this.leftNode.clone();
      clone.rightNode = (Expression) this.rightNode.clone();
      return clone;
   }
}
