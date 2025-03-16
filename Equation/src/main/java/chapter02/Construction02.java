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
		int value=0;                 //增加输出结果
		System.out.println();
		System.out.println("加法练习题：");
		System.out.println();
		System.out.print("题目"+1+"~"+10+":"+"\t");
		for(int i=0;i<50;i++) {
			do {
				left_operand=generateOperand();
		        right_operand=generateOperand();
		        value=left_operand+right_operand;
			}while(value>=100);         //增加判断，可以单独封装为检测函数，如教材P34中的void check()
			equation=left_operand+"+"+right_operand+"="+value;
			System.out.printf("%-10s",equation);    //修改输出格式，每行10个，其中："%-10s"实现输出左对齐
			if((i+1)%10 == 0){         //每循环10次调用一次,换行输出
				System.out.println();
				if(i<49) {
				System.out.print("题目"+(i+2)+"~"+(i+11)+":"+"\t");
				}
            }
		}
	}
	
	static void generateSubEquation(){  
			
			String equation=""; 
			int left_operand=0;
			int right_operand=0;
			int value=0;                 //增加输出结果
			System.out.println();
			System.out.println("减法练习题：");
			System.out.println();
			System.out.print("题目"+1+"~"+10+":"+"\t");
			for(int i=0;i<50;i++) {
				do {
					left_operand=generateOperand();
			        right_operand=generateOperand();
			        value=left_operand-right_operand;
				}while(value<=0);          //增加判断，可以单独封装为检测函数，如教材P34中的void check()
				equation=left_operand+"-"+right_operand+"="+value;
				System.out.printf("%-10s",equation);    //修改输出格式，每行10个，其中："%-10s"实现输出左对齐
				if((i+1)%10 == 0){         //每循环10次调用一次,换行输出
					System.out.println();
					if(i<49) {
					System.out.print("题目"+(i+2)+"~"+(i+11)+":"+"\t");
					}
	            }
			}
		}
		
	
	

}
