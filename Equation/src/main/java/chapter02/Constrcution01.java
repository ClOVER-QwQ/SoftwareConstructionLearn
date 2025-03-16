package chapter02;

import java.util.Random;

public class Constrcution01 {

	public static void main(String[] args) {
		
		generateSubEquation();
		generateAddEquation();
	}
	
	static int generateOperand() {       //����100���ڵ�����������100������ֱ�ۣ�ע��ʹ�þ�̬������main�����е���
		int operand=0;                   //�������������Զ��巶Χ��������
		Random random=new Random();
		operand=random.nextInt(101);
		return operand;
		
		/* �򵥣������׶�
		Random random=new Random();
		return random.nextInt(101);
		 */
	}
	
//	static int generateOperand(int range) {       //����100���ڵ�����������100������ֱ�ۣ�ע��ʹ�þ�̬������main�����е���
//		int operand=0;                            //�������������Զ��巶Χ��������
//		Random random=new Random();
//		operand=random.nextInt(range);
//		return operand;
//		
//	}
	
	
	static void generateAddEquation(){   //���������û��Զ���ӷ���ʽ����
		
		String equation="";              //��ʽ�����ݽṹ���ַ���
		int left_operand=0;
		int right_operand=0;
		for(int i=0;i<50;i++) {
			left_operand=generateOperand();
			right_operand=generateOperand();
			equation=left_operand+"+"+right_operand+"=";
			System.out.println(i+1+":"+equation);
		}
		
		/*  �򵥣������׶�
		for(int i=0;i<50;i++) {
			System.out.println(i+1+":"+generateOperand()+"+"+generateOperand()+"=");		
		}
		*/
		
		/*  ����������
		String equation="";    
		int left_operand=generateOperand();
	    int right_operand=generateOperand();
	    equation=left_operand+"+"+right_operand+"=";
	    System.out.println(equation);
		*/
	}
	
	static void generateSubEquation(){   //���������û��Զ���ļ�����ʽ����
		
		String equation="";              //��ʽ�����ݽṹ���ַ���
		int left_operand=0;
		int right_operand=0;
		for(int i=0;i<50;i++) {
			left_operand=generateOperand();
	        right_operand=generateOperand();
			equation=left_operand+"-"+right_operand+"=";
			System.out.println(i+1+":"+equation);
		}
		
	}

}
