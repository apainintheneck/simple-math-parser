package mathNode;

public class Pow extends Operator
{
   public Pow() { precedence = 1; }
   
   public Number calculate()
   {
      return Math.pow(leftNode.calculate().doubleValue(), rightNode.calculate().doubleValue());
   }
   
   public String toString()
   {
      return leftNode.toString() + "^" + rightNode.toString();
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      Pow clone = (Pow) super.clone();
      clone.leftNode = (Expression) this.leftNode.clone();
      clone.rightNode = (Expression) this.rightNode.clone();
      return clone;
   }
}