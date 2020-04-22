package mathNode;

public class Dec extends Expression
{
   private double value;
   
   public Dec(double value) { this.value = value; }

   public Number calculate() { return value; }

   public String toString() { return Double.toString(value); }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      return (Dec) super.clone();
   }
}