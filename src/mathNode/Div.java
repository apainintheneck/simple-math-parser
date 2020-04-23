package mathNode;

public class Div extends Operator
{
   public Div() { precedence = 2; }
   
   public Number calculate()
   {
      return getLeftNode().calculate().doubleValue() / getRightNode().calculate().doubleValue();
   }
   
   public String toString()
   {
      String str = getLeftNode().toString() + " / " + getRightNode().toString();
      
      if(isParens())
         return '(' + str + ')';
      else
         return str;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      Div clone = (Div) super.clone();
      clone.setLeftNode((Expression) this.getLeftNode().clone());
      clone.setRightNode((Expression) this.getRightNode().clone());
      return clone;
   }
}
