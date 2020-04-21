package mathNodes;

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
}
