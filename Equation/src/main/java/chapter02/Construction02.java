package chapter02;

import java.util.Random;

public class Construction02 {

	public static void main(String[] args) {
		
		generateAddEquation();
		
	}
	
	static int generateOperand() {      
		int operand=0;                 
		Random random=new Random();
		operand=random.nextInt(101);
		return operand;
		
	}
	
	static void generateAddEquation(){  
		
		String equation=""; 
		int left_operand=0;
		int right_operand=0;
		int value=0;                 //����������
		System.out.println();
		System.out.println("�ӷ���ϰ�⣺");
		System.out.println();
		System.out.print("��Ŀ"+1+"~"+10+":"+"\t");
		for(int i=0;i<50;i++) {
			do {
				left_operand=generateOperand();
		        right_operand=generateOperand();
		        value=left_operand+right_operand;
			}while(value>=100);         //�����жϣ����Ե�����װΪ��⺯������̲�P34�е�void check()
			equation=left_operand+"+"+right_operand+"="+value;
			System.out.printf("%-10s",equation);    //�޸������ʽ��ÿ��10�������У�"%-10s"ʵ����������
			if((i+1)%10 == 0){         //ÿѭ��10�ε���һ��,�������
				System.out.println();
				if(i<49) {
				System.out.print("��Ŀ"+(i+2)+"~"+(i+11)+":"+"\t");
				}
            }
		}
	}
	
	static void generateSubEquation(){  
			
			String equation=""; 
			int left_operand=0;
			int right_operand=0;
			int value=0;                 //����������
			System.out.println();
			System.out.println("������ϰ�⣺");
			System.out.println();
			System.out.print("��Ŀ"+1+"~"+10+":"+"\t");
			for(int i=0;i<50;i++) {
				do {
					left_operand=generateOperand();
			        right_operand=generateOperand();
			        value=left_operand-right_operand;
				}while(value<=0);          //�����жϣ����Ե�����װΪ��⺯������̲�P34�е�void check()
				equation=left_operand+"-"+right_operand+"="+value;
				System.out.printf("%-10s",equation);    //�޸������ʽ��ÿ��10�������У�"%-10s"ʵ����������
				if((i+1)%10 == 0){         //ÿѭ��10�ε���һ��,�������
					System.out.println();
					if(i<49) {
					System.out.print("��Ŀ"+(i+2)+"~"+(i+11)+":"+"\t");
					}
	            }
			}
		}
		
	
	

}
