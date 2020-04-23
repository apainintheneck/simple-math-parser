package mathNode;

public class Dec extends Expression
{
   private double value;
   
   public Dec(double value) { this.value = value; }

   public Number calculate() { return value; }

   public String toString() 
   { 
      String str = Double.toString(value); 
      
      if(isParens())
         return '(' + str + ')';
      else
         return str;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      return (Dec) super.clone();
   }
   
   @Override
   public boolean checkTree() { return true; }
}