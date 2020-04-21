//A test main for the mathNode package
public class testMathNode
{
   public static mathNode.Factory nodeFactory = new mathNode.Factory();
   
   public static void main(String[] args)
   {
      mathNode.Operator[] opNodes = new mathNode.Operator[5];
      
      opNodes[0] = new mathNode.Add();
      opNodes[1] = new mathNode.Sub();
      opNodes[2] = new mathNode.Mult();
      opNodes[3] = new mathNode.Div();
      opNodes[4] = new mathNode.Pow();
      
      calculateAndPrint(opNodes, 6, 2);
      
      calculateAndPrint(opNodes, 4.5, 3);
      
   }
   
   public static void calculateAndPrint(mathNode.Operator[] opNodes, int num1, int num2)
   {
      mathNode.Expression leftNode = nodeFactory.buildNode(num1);
      mathNode.Expression rightNode = nodeFactory.buildNode(num2);
      
      for(mathNode.Operator op : opNodes)
      {
         op.leftNode = leftNode;
         op.rightNode = rightNode;
         System.out.println("--> " + op + " = " + op.calculate());
      }
   }
   
   public static void calculateAndPrint(mathNode.Operator[] opNodes, double num1, double num2)
   {
      mathNode.Expression leftNode = nodeFactory.buildNode(num1);
      mathNode.Expression rightNode = nodeFactory.buildNode(num2);
      
      for(mathNode.Operator op : opNodes)
      {
         op.leftNode = leftNode;
         op.rightNode = rightNode;
         System.out.println("--> " + op + " = " + op.calculate());
      }
   }

}
