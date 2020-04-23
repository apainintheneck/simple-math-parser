package mathNode;

public class Factory implements Cloneable
{
   public Expression buildNode(String token)
   {
      if(token.length() == 1)
      {
         Expression newNode = buildNode(token.charAt(0));
         if(newNode != null) { return newNode; }
      }
      
      try {
         int numInt = Integer.parseInt(token);
         return buildNode(numInt);
      } catch(NumberFormatException e1) {
         try {
            double numDub = Double.parseDouble(token);
            return buildNode(numDub);
         } catch(NumberFormatException e2) {
            return null;
         }
      }
   }
   
   public Expression buildNode(char ch) 
   {
      switch(ch)
      {
         case '+': return new Add();
         case '-': return new Sub();
         case '*': return new Mult();
         case '/': return new Div();
         case '^': return new Pow();
         default: return null;
      }
   }
   public Expression buildNode(int num) { return new Int(num); }
   public Expression buildNode(double num) { return new Dec(num); }
   public Object clone() throws CloneNotSupportedException
   {
      return (Factory) super.clone();
   }
}
