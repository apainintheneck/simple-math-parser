package mathNode;

public class Int extends Expression
{
   private int value;
   
   public Int(int value) { this.value = value; }

   public Number calculate() { return value; }

   public String toString() { return Integer.toString(value); }
   
   @Override
   public Object clone() throws CloneNotSupportedException
   {
      return (Int) super.clone();
   }

   @Override
   public boolean checkTree() { return true; }
   
}
