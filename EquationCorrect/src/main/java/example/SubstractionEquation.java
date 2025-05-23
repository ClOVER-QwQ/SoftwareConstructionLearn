package example;

//0���̳г�����Equation
public class SubstractionEquation extends Equation {

	//1��������Ա����:���ڼ̳и��࣬Ĭ��ӵ���븸����ͬ�ĳ�Ա��������������Ա����
	
	//2��������Ա������getter��setter������Ĭ��ӵ���븸����ͬ��getter��setter����
		
	//3���������캯�����޲Σ�����1���ӷ���ʽ���޲ι��캯�������󴴽�ʱ�������ʼ��Ϊ�Ӻ�
	public SubstractionEquation() {
		generateEquation('-');
	}
	
	//3���������캯�����вΣ�����2�����������ַ���
	public SubstractionEquation(String s) {
		int index = s.indexOf("-");
		int length = s.length();
		this.setLeftOperand(Integer.parseInt(s.substring(0,index)));
		this.setRightOperand(Integer.parseInt(s.substring(index+1,length)));
		this.setOperator(s.charAt(index));
		this.setOperatedValue(calculate());
	}
	
	//4��������Ա������������
	
	//5��ʵ�֣����ࣩ���󷽷�
	protected int calculate() {
		return this.getLeftOperand() - this.getRightOperand();
	}
}
