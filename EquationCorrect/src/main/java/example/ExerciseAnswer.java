package example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;


public class ExerciseAnswer {
	
	//1��������Ա������ϰ������Ա����
	private ArrayList<Integer> exerciseAnswer;
	private int equationIndex;
	
	//2��������Ա������getter��setter����
	public ArrayList<Integer> getExerciseAnswer() {
		return exerciseAnswer;
	}

	public void setExerciseAnswer(ArrayList<Integer> exerciseAnswer) {
		this.exerciseAnswer = exerciseAnswer;
	}

	public int getEquationIndex() {
		return equationIndex;
	}

	public void setEquationIndex(int equationIndex) {
		this.equationIndex = equationIndex;
	}

	//3���������캯�����޲Σ�����ʼ����Ա����
	public ExerciseAnswer() {
		exerciseAnswer = new ArrayList<Integer>();
		equationIndex = 0;
	}

	//4��������Ա��������1����ϰ��𰸱���/д�뵽�ı��ļ���
	public void writeAnswerToFile(String fileName) {
		File exerciseFile = new File(fileName);
		Writer out = null;
		try {
			out = new FileWriter(exerciseFile, true);
			for(Integer i:exerciseAnswer) {
				out.write(i + ",");
			}
			out.flush();
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			//
		}
	}
	
	//4��������Ա��������2�����ı��ļ��ж�ȡ��
	public void readAnswerFromFile(String fileName) {
		File exerciseFile = new File(fileName);
		String equationAnswer;
		Scanner input = null;
		exerciseAnswer.clear();
		try {
			input = new Scanner(exerciseFile);
			input.useDelimiter(",");
			while(input.hasNext()) {
				equationAnswer = input.next().replaceAll("\\s", "");
				exerciseAnswer.add(Integer.parseInt(equationAnswer));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			input.close();
		}
	}
	
	//4��������Ա��������3����׽/ɨ���������
	public void scanAnswerFromKeyboard(int count) {
		Scanner scanner = new Scanner(System.in);
		exerciseAnswer.clear();
		System.out.println("�밴����ʽ�������𰸺�س���");
		for(int i = 1; i <= count; i++) {
			System.out.print("("+i+")");
			exerciseAnswer.add(scanner.nextInt());
		}
		scanner.close();
	}
	
	//4��������Ա��������4������ϰ������ʽ���
	public void reset() {
		equationIndex = 0;
	}
	
	//4��������Ա��������5����Ӵ�
	public boolean add(int equationAnswer) {
		return exerciseAnswer.add(equationAnswer);
	}
	
	//4��������Ա��������6�����ϰ������ʽ��
	public boolean hasNext() {
		return equationIndex < exerciseAnswer.size();
	}
	
	//4��������Ա��������7������ϰ������ʽ��
	public int next() {
		if(equationIndex<exerciseAnswer.size()) {
			return exerciseAnswer.get(equationIndex++);
		}
		else {
			return -1;
		}
	}
	
	//4��������Ա��������8�����ϰ����ĳ����ŵ���ʽ��
	public int get(int equationIndex) {
		return exerciseAnswer.get(equationIndex);
	}
	
	//4��������Ա��������9������ϰ����ĳ����ŵ���ʽ��
	public void set(int equationIndex, int equationAnswer) {
		exerciseAnswer.set(equationIndex, equationAnswer);
	}
}
