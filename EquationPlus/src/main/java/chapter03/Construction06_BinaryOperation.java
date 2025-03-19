package chapter03;

public class Construction06_BinaryOperation {

	public int construct(int left,int right,char op) {
		int left_operand=0;
		int right_operand=0;
		char operator=' ';
		int value=0;
		int result=0;
		
		if(!(0<=left&&left<=100)) {
			throw new RuntimeException("左运算数不在0-100范围内");
		}
		if(!(0<=right&&right<=100)) {
			throw new RuntimeException("右运算数不在0-100范围内");
		}
		
		if(op=='+') {
			result=left+right;
			if(!(0<=result&&result<=100)) {
				throw new RuntimeException("加法结果不在0-100范围内");
			}
		}else if(op=='-') {
			result=left-right;
			if(!(0<=result&&result<=100)) {
				throw new RuntimeException("减法结果不在0-100范围内");
			}
		}else {
			throw new RuntimeException(op+"不是加号或减号运算符！");
		}
		left_operand=left;
		right_operand=right;
		operator=op;
		value=result;
		return value;
	}
}
