package mathNodes;

public class Int extends Expression
{
   private int value;
   
   public Int(int value) { this.value = value; }

   public Number calculate() { return value; }

   public String toString() { return Integer.toString(value); }
}
