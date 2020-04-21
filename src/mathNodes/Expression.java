package mathNodes;

/**
 * Abstract expression node class.
 * @author kevinrobell
 *
 */
public abstract class Expression implements Cloneable
{
   public boolean isParens = false; //bool for parenthesis around this expression
   
   /**
    * The calculate expression works recursively down the tree.
    * @return Number The boxed value of Integer or Double based upon previous expression.
    */
   abstract public Number calculate();
   abstract public String toString();
}