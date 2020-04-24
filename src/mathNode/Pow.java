package mathNode;

/**
 * Node for exponent operator.
 * @author kevinrobell
 *
 */
public class Pow extends Operator
{
   public Pow() { precedence = 1; }
   
   public Number calculate()
   {
      return Math.pow(getLeftNode().calculate().doubleValue(), getRightNode().calculate().doubleValue());
   }
   
   public String toString()
   {
      String str = getLeftNode().toString() + " ^ " + getRightNode().toString();
      
      if(isParens())
         return '(' + str + ')';
      else
         return str;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      Pow clone = (Pow) super.clone();
      clone.setLeftNode((Expression) this.getLeftNode().clone());
      clone.setRightNode((Expression) this.getRightNode().clone());
      return clone;
   }
}