package mathNode;

public class Factory
{
   public Expression buildNode(String token)
   {
      if(token.length() == 1)
      {
         switch(token.charAt(0))
         {
            case '+': return new Add();
            case '-': return new Sub();
            case '*': return new Mult();
            case '/': return new Div();
            case '^': return new Pow();
         }
      }
      
      try {
         int numInt = Integer.parseInt(token);
         return new Int(numInt);
      } catch(NumberFormatException e1) {
         try {
            double numDub = Double.parseDouble(token);
            return new Dec(numDub);
         } catch(NumberFormatException e2) {
            return null;
         }
      }
   }
}
