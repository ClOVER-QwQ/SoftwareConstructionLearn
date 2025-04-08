package example;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Setter
@Getter
public class CheckAnswer {

    //2、创建成员变量的getter、setter方法
    //1、创建成员变量
	private int equationCount;
	private int rightCount;
	private int wrongCount;

    //3、创建构造函数
	public CheckAnswer() {
		rightCount = 0;
		wrongCount = 0;
	}
	
	//4、创建成员方法：（1）检查习题答案
	public void checkExercise(Exercise exercise, ExerciseAnswer exerciseAnswer) {
		equationCount = exercise.size();
		int wrongCount = 0;
		int rightCount = 0;
		exercise.setIndex(0);
		exerciseAnswer.reset();
		while(exercise.hasNext()) {
			if(exercise.next().getOperatedValue()== exerciseAnswer.next()) {
				rightCount++;
			}
			else {
				wrongCount++;
			}
			
		}
		setRightCount(rightCount);
		setWrongCount(wrongCount);
	}
	
	//4、创建成员方法：（2）将检查结果写入文件
	public void writeExerciseCheckToFile(String fileName) {
		File exerciseFile = new File(fileName);
		Writer out;
		try {
			out = new FileWriter(exerciseFile, true);
			out.write("算式总数:" + equationCount + ";\r\n");
			out.write("计算正确算式个数：" + rightCount + ";\r\n");
			out.write("计算错误算式个数：" + wrongCount + ";\r\n");
			out.flush();
			out.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//4、创建成员方法：（3）打印显示检查结果
	public void printExerciseCheck() {
		System.out.println("本次练习批改结果：");
		System.out.println("算式总数：" + equationCount);
		System.out.println("计算正确算式个数：" + rightCount);
		System.out.println("计算错误算式个数：" + wrongCount);
	}

}
