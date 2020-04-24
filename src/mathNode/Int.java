package mathNode;

/**
 * Node that holds integer values.
 * @author kevinrobell
 *
 */
public class Int extends Expression
{
   private int value;
   
   public Int(int value) { this.value = value; }

   public Number calculate() { return value; }

   public String toString() 
   { 
      String str = Integer.toString(value); 
      
      if(isParens())
         return '(' + str + ')';
      else
         return str;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      return (Int) super.clone();
   }

   @Override
   public boolean checkTree() { return true; }
   
}
