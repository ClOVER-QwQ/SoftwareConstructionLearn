package chapter03;

import java.util.Random;

public class Construction04_BinaryOperation {


	private static final int UPPER=100;
    //private static final int LOWER=0;
	
	private int left_operand=0;
	private int right_operand=0;
	private char operator='+';
	private int value=0;
	
	private void construct(int left,int right,char op) {
		
		left_operand=left;
		right_operand=right;
		operator=op;
		
		if(op=='+') {
			value=left+right;
		}
		else {
			value=left-right;
		}
		
	}
	
	public Construction04_BinaryOperation generateAdditionOperation() {
		
		Random random=new Random();
		
		int left;
		int right;
		int value;
		
		left=random.nextInt(UPPER+1);
		
		do {
			right=random.nextInt(UPPER+1);
			value=left+right;
		}while(value>UPPER);
		
		Construction04_BinaryOperation bop=new Construction04_BinaryOperation();
		bop.construct(left,right,'+');
		
		return bop;
	}
	
	public Construction04_BinaryOperation generateSubstractionOperation() {
		
		//²¹È«...
		
		Construction04_BinaryOperation bop=new Construction04_BinaryOperation();
		
		//²¹È«...
		
		return bop;
	}
	
	public Construction04_BinaryOperation generateBinaryOperation() {
		
		//...
		
		Construction04_BinaryOperation bop=new Construction04_BinaryOperation();
		
		//²¹È«...
		
		return bop;
	}
	
	public boolean equals(Construction04_BinaryOperation anOperation) {
		return left_operand==anOperation.getLeft_operand()&
			   right_operand==anOperation.getLeft_operand()&
			   operator==anOperation.getOperator();
			   
	}
	
	public String toOperationString() {
		
		return Integer.toString(left_operand)+operator+Integer.toString(right_operand);
		
	}
	
	public String asOperationString() {
		
		return Integer.toString(left_operand)+operator+Integer.toString(right_operand)+"=";
		
	}
	
	public String fullOperationString() {
		
		return Integer.toString(left_operand)+operator+Integer.toString(right_operand)+"="+Integer.toString(value);
		
	}

	public int getLeft_operand() {
		return left_operand;
	}

	public void setLeft_operand(int left_operand) {
		this.left_operand = left_operand;
	}

	public int getRight_operand() {
		return right_operand;
	}

	public void setRight_operand(int right_operand) {
		this.right_operand = right_operand;
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	
	
	
}
