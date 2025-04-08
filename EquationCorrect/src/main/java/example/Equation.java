package example;

import java.util.Random;

//0�����������
public abstract class Equation {
	
	//1��������Ա��������ʽ���Ա����
	private final int MAXVALUE = 100;
	private final int MINVALUE = 0;
	private int leftOperand;
	private int rightOperand;
	private int operatedValue;
	private char operator;
	
	//2��������Ա������getter��setter����
	public int getLeftOperand() {
		return leftOperand;
	}
	public void setLeftOperand(int leftOperand) {
		this.leftOperand = leftOperand;
	}
	public int getRightOperand() {
		return rightOperand;
	}
	public void setRightOperand(int rightOperand) {
		this.rightOperand = rightOperand;
	}
	public int getOperatedValue() {
		return operatedValue;
	}
	public void setOperatedValue(int operatedValue) {
		this.operatedValue = operatedValue;
	}
	public char getOperator() {
		return operator;
	}
	public void setOperator(char operator) {
		this.operator = operator;
	}
	
	//3���������캯��������ʾд����Ĭ��Ϊ�޲��޷����幹�캯��
	
	//4��������Ա��������1������ָ����Χ�ڵ��������
	private int generateRandomInt(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}
	
	//4��������Ա��������2�����ֵ��ĳһ��Χ֮��
	private boolean isBetween(int value, int min, int max) {
		return value >= min && value <= max;
	}
	
	//4��������Ա��������3���ж�������ʽ�Ƿ���ͬ
	public boolean isEqual(Equation equation) {
		boolean b = false;
		if(equation.getOperator() != this.getOperator()) {
			b = false;
		}
		else {
			b = equation.getLeftOperand() == this.getLeftOperand() && equation.getRightOperand() == this.getRightOperand();
		}
		return b;
	}
	
	//4��������Ա��������4�������A+B������ʽ
	public String equationStyle01() {
		return "" + this.getLeftOperand() + this.getOperator() + this.getRightOperand();
	}
	
	//4��������Ա��������5�������A+B=������ʽ
	public String equationStyle02() {
		return equationStyle01()+"=";
	}
	
	//4��������Ա��������6�������A+B=C������ʽ
	public String equationStyle03() {
		return equationStyle02()+this.getOperatedValue();
	}
	
	//4��������Ա��������7��������ʽ
	public void generateEquation(char operator) {
	do {
		leftOperand = generateRandomInt(MINVALUE, MAXVALUE);
		rightOperand = generateRandomInt(MINVALUE, MAXVALUE);
		operatedValue = calculate();
	}while(!isBetween(operatedValue, MINVALUE, MAXVALUE));
	this.setOperator(operator);
	}
	
	//5���������󷽷���������ʽ�����+��-��������....��
	protected abstract int calculate();
}
