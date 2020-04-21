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
}