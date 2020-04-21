package mathNodes;

public class Pow extends Operator
{
   public Number calculate()
   {
      return Math.pow(leftNode.calculate().doubleValue(), rightNode.calculate().doubleValue());
   }
   
   public String toString()
   {
      return leftNode.toString() + "^" + rightNode.toString();
   }
}