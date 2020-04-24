package mathNode;

/**
 * Node for multiplication operator.
 * @author kevinrobell
 *
 */
public class Mult extends Operator
{
   public Mult() { precedence = 2; }
   
   public Number calculate()
   {
      Number leftNum = getLeftNode().calculate();
      Number rightNum = getRightNode().calculate();
      
      //If both numbers below it on the tree are integers, do integer multiplication.
      if(leftNum instanceof Integer && rightNum instanceof Integer)
         return leftNum.intValue() * rightNum.intValue();
      
      //Otherwise do decimal multiplication.
      return leftNum.doubleValue() * rightNum.doubleValue();
   }
   
   public String toString()
   {
      String str = getLeftNode().toString() + " * " + getRightNode().toString();
      
      if(isParens())
         return '(' + str + ')';
      else
         return str;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      Mult clone = (Mult) super.clone();
      clone.setLeftNode((Expression) this.getLeftNode().clone());
      clone.setRightNode((Expression) this.getRightNode().clone());
      return clone;
   }
}
