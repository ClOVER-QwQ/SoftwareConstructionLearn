package chapter03;

public class Construction04_Exercise {

	private static final int OPERATION_NUMBER=50;
	private static final int COLUMN_NUMBER=5;
	private Construction04_BinaryOperation[] operationList=new Construction04_BinaryOperation[OPERATION_NUMBER]; 
	
	public Construction04_BinaryOperation[] getOperationList() {
		return operationList;
	}

	public void setOperationList(Construction04_BinaryOperation[] operationList) {
		this.operationList = operationList;
	}

	public void generateAdditionExercise() {
		
		Construction04_BinaryOperation anOperation=new Construction04_BinaryOperation();
		Construction04_BinaryOperation opCreator=new Construction04_BinaryOperation();
		
		for(int i=0;i<OPERATION_NUMBER;i++) {
			
			anOperation=opCreator.generateAdditionOperation();
			while(contains(anOperation,i-1)) {
				anOperation=opCreator.generateAdditionOperation();
			}
			operationList[i]=anOperation;
			
		}
	}
	
	public void generateSubstractionExercise() {
		
		    //补全...
			return;
			
		}
	
	public void generateBinaryExercise() {
		
	    //补全...
		return;
		
	}
	
	private boolean contains(Construction04_BinaryOperation anOperation,int length) {  //一个对象
		
		boolean found=false;
		for(int i=0;i<=length;i++) {
			if(anOperation.equals(operationList[i])) {
				found=true;
				break;
			}
		}
		
		return found;
		
	}
	
	public void formateAndDisplay() {
		
		for(int i=0;i<OPERATION_NUMBER;i++) {
			System.out.printf("%-10s",operationList[i].fullOperationString());
			if((i+1)%COLUMN_NUMBER == 0){         
				System.out.println();
            }
			
		}
		
	}
	
	}
	
	
	

