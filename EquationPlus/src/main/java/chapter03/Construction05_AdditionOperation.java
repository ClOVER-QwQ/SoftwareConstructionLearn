package chapter03;

public class Construction05_AdditionOperation extends Construction05_BinaryOperation{

	Construction05_AdditionOperation(){
		generateBinaryOperation('+');
	}
	 public boolean checkingCalculation(int anInteger) {
		 
		 return anInteger<=UPPER;
		 
	 }
	
	 int calculate(int left,int right) {
		 return left+right;
	 }
	
	
}
