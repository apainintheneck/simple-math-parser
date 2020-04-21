package mathNodes;

public class Div extends Operator
{
   public Number calculate()
   {
      return leftNode.calculate().doubleValue() / rightNode.calculate().doubleValue();
   }
   
   public String toString()
   {
      return leftNode.toString() + " / " + rightNode.toString();
   }
}
