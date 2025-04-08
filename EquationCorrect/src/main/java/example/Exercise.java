package example;

import java.util.ArrayList;
import java.util.Random;

public class Exercise {

	//1��������Ա������ϰ�����Ա����
	private int count = 50;
	private int index = 0;
	private ArrayList<Equation> exercise = null;
	
	//2��������Ա������getter��setter����
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ArrayList<Equation> getExercise() {
		return exercise;
	}
	public void setExercise(ArrayList<Equation> exercise) {
		this.exercise = exercise;
	}
	
	//3���������캯������1���޲Σ���ʼ����Ա����ֵ
	public Exercise() {
		index = 0;
		exercise = new ArrayList<Equation>();
	}
	
	//3���������캯������1���вΣ���ʼ����Ա����ֵ���û��Զ���ϰ������ʽ������
	public Exercise(int count) {
		this.setCount(count);
		index = 0;
		exercise = new ArrayList<Equation>();
	}
	
	//4��������Ա��������1�����ĳ����ʽ�Ƿ���ĳ��ϰ����
	public boolean occursIn(Equation equation01) {
		boolean b = false;
		for(Equation equation02: exercise) {
			if(equation02.isEqual(equation01)) {
				b = true;
				break;
			}
		}
		return b;
	}
	
	//4��������Ա��������2�������������ϰ��
	public void generateExercise() {
		int i = 0;
		Random r = new Random();
		while(i<count) {
			Equation equation;
			if(r.nextInt(2) == 1) {
				equation = new AdditionEquation();
			}
			else {
				equation = new SubstractionEquation();
			}
			if(!occursIn(equation)) {
				exercise.add(equation);
				i++;
			}
		}
	}
	
	//4��������Ա��������3���������ӷ���������ϰ��
	public void generateAddExercise() {
		int i = 0;
		while(i < count) {
			Equation equation = new AdditionEquation();
			if(!occursIn(equation)) {
				exercise.add(equation);
				i++;
			}
		}
	}
	
	//4��������Ա��������4��������������������ϰ��
	public void generateSubExercise() {
		int i = 0;
		while(i < count) {
			Equation equation = new SubstractionEquation();
			if(!occursIn(equation)) {
				exercise.add(equation);
				i++;
			}
		}
	}
	
	//4��������Ա��������5����ӡ���ϰ�⣬ÿ��5��
	public void printExercise() {
		int i = 0;
		for(Equation equation:exercise) {
			i++;
			System.out.print("(" + i + ")" + equation.equationStyle03());
			if(i % 5 == 0) {
				System.out.println();
			}
			else {
				System.out.print("\t");
			}
		}
	}
	
	//4��������Ա��������6�����ϰ�����Ƿ�����ʽ
	public boolean hasNext() {
		return index < exercise.size();
	}
	
	//4��������Ա��������7����ת��ϰ���е���һ����ʽ
	public Equation next() {
		if(index < exercise.size()) {
			return exercise.get(index++);
		}
		else {
			return null;
		}
	}
	
	//4��������Ա��������8����ϰ����������ʽ��������50��
	public boolean add(Equation eqation) {
		if(index < count) {
			exercise.add(eqation);
			index++;
			return true;
		}
		else {
			return false;
		}
	}
	
	//4��������Ա��������9�����ϰ������ʽ�ĸ���
	public int size() {
		return exercise.size();
	}

	//4��������Ա��������10�����ϰ����ĳ����ŵ���ʽ
	public Equation get(int index) {
		if(index < size()) {
			return exercise.get(index);
		}
		else {
			return null;
		}
	}
}
