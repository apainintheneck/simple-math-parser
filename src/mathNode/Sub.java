package mathNode;

/**
 * Node for subtraction operator.
 * @author kevinrobell
 *
 */
public class Sub extends Operator
{
   public Sub() { precedence = 3; }
   
   public Number calculate()
   {
      Number leftNum = getLeftNode().calculate();
      Number rightNum = getRightNode().calculate();
      
      //If both values below it are integers do integer subtraction.
      if(leftNum instanceof Integer && rightNum instanceof Integer)
         return leftNum.intValue() - rightNum.intValue();
      
      //Otherwise do double subtraction.
      return leftNum.doubleValue() - rightNum.doubleValue();
   }
   
   public String toString()
   {
      String str = getLeftNode().toString() + " - " + getRightNode().toString();
      
      if(isParens())
         return '(' + str + ')';
      else
         return str;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      Sub clone = (Sub) super.clone();
      clone.setLeftNode((Expression) this.getLeftNode().clone());
      clone.setRightNode((Expression) this.getRightNode().clone());
      return clone;
   }
}
