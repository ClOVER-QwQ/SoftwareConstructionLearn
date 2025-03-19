package chapter03;

import java.util.Random;

public abstract class Construction05_BinaryOperation {
	
	protected static final int UPPER=100;
	private int left_operand=0;
	private int right_operand=0;
	private char operator='+';
	private int value=0;
	
	protected void generateBinaryOperation(char anOperator) {
		int left;
		int right;
		int result;
		Random random=new Random();
		left=random.nextInt(UPPER+1);
		do {
			right=random.nextInt(UPPER+1);
			result=calculate(left,right);
		}while(!(checkingCalculation(result)));
		left_operand=left;
		right_operand=right;
		operator=anOperator;
		value=result;
	}
	
	abstract boolean checkingCalculation(int anInteger);
	abstract int calculate(int left,int right);
	
	
	
}
