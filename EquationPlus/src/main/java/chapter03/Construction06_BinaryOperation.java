package chapter03;

public class Construction06_BinaryOperation {

	public int construct(int left,int right,char op) {
		int left_operand=0;
		int right_operand=0;
		char operator=' ';
		int value=0;
		int result=0;
		
		if(!(0<=left&&left<=100)) {
			throw new RuntimeException("������������0-100��Χ��");
		}
		if(!(0<=right&&right<=100)) {
			throw new RuntimeException("������������0-100��Χ��");
		}
		
		if(op=='+') {
			result=left+right;
			if(!(0<=result&&result<=100)) {
				throw new RuntimeException("�ӷ��������0-100��Χ��");
			}
		}else if(op=='-') {
			result=left-right;
			if(!(0<=result&&result<=100)) {
				throw new RuntimeException("�����������0-100��Χ��");
			}
		}else {
			throw new RuntimeException(op+"���ǼӺŻ�����������");
		}
		left_operand=left;
		right_operand=right;
		operator=op;
		value=result;
		return value;
	}
}
