package mathNode;

/**
 * Abstract expression node class.
 * @author kevinrobell
 *
 */
public abstract class Expression implements Cloneable
{
   protected boolean parenthesis = false; //bool to indicate parenthesis around this expression
   
   public void setParens(boolean bool) { parenthesis = bool; }
   public boolean isParens() { return parenthesis; }
   
   /**
    * The calculate expression works recursively down the tree.
    * @return Number The boxed value of Integer or Double based upon previous expression.
    */
   abstract public Number calculate();
   abstract public String toString();
   public Object clone() throws CloneNotSupportedException
   {
      return (Expression) super.clone();
   }
   
   /**
    * A recursive method to check if the tree is constructed correctly.
    * @return boolean
    */
   abstract public boolean checkTree();
}