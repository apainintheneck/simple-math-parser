package mathNode;

public class Mult extends Operator
{
   public Mult() { precedence = 2; }
   
   public Number calculate()
   {
      Number leftNum = leftNode.calculate();
      Number rightNum = rightNode.calculate();
      
      if(leftNum instanceof Integer && rightNum instanceof Integer)
         return leftNum.intValue() * rightNum.intValue();
      
      return leftNum.doubleValue() * rightNum.doubleValue();
   }
   
   public String toString()
   {
      return leftNode.toString() + " * " + rightNode.toString();
   }
}
