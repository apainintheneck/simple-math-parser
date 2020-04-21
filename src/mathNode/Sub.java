package mathNode;

public class Sub extends Operator
{
   public Sub() { precedence = 3; }
   
   public Number calculate()
   {
      Number leftNum = leftNode.calculate();
      Number rightNum = rightNode.calculate();
      
      if(leftNum instanceof Integer && rightNum instanceof Integer)
         return leftNum.intValue() - rightNum.intValue();
      
      return leftNum.doubleValue() - rightNum.doubleValue();
   }
   
   public String toString()
   {
      return leftNode.toString() + " - " + rightNode.toString();
   }
}
