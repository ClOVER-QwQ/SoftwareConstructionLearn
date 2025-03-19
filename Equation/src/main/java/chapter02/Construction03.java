package chapter02;

import java.util.Random;

public class Construction03 {

	public static void main(String[] args) {
		
		generateAddExercise();
		
	}
	
	static int generateOperand() {      
		int operand=0;                 
		Random random=new Random();
		operand=random.nextInt(101);
		return operand;
		
	}
	
	
	static int[] generateAddEquation(){  
		
		int[] equation=new int[3]; 
		equation[2]=1;
		int value=0;                 //����������

		do {
			equation[0]=generateOperand();
			equation[1]=generateOperand();
	        value=equation[0]+equation[1];
		}while(value>=100);         //�����жϣ����Ե�����װΪ��⺯������̲�P34�е�void check()
			
		return equation;
	}
	
	
	static int[] generateSubEquation(){  
			
			int[] equation=new int[3]; 
			equation[2]=0;
			int value=0;                 //����������
			do {
				equation[0]=generateOperand();
				equation[1]=generateOperand();
		        value=equation[0]-equation[1];
			}while(value<=0);         //�����жϣ����Ե�����װΪ��⺯������̲�P34�е�void check()
				
			return equation;
		}
	
	
	static void generateAddExercise() {      
		
	
		int[][] exercise=new int[50][];
//		int[] equation;
		for(int i=0;i<50;i++) {
//			do {
//				equation=generateAddEquation();
//			}while(?);  //�벹ȫ����
			
			exercise[i]=generateAddEquation();
			System.out.println(exercise[i][0]+"+"+exercise[i][1]+"=");
		
		}
		
		
		
		
		
	}
		

}
