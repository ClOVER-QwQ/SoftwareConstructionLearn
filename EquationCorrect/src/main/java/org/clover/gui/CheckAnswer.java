package org.clover.gui;

import lombok.Data;
import org.clover.entity.Equation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Data
public class CheckAnswer {
    private int equationCount;
    private int rightCount;
    private int wrongCount;

    public CheckAnswer() {
        rightCount = 0;
        wrongCount = 0;
    }

    public void checkExercise(List<Equation> exercise, ExerciseAnswer exerciseAnswer) {
        equationCount = exercise.size();
        int wrongCount = 0;
        int rightCount = 0;
        for (int i = 0; i < equationCount; i++) {
            if (exercise.get(i).getResult() == exerciseAnswer.get(i)) {
                rightCount++;
            } else {
                wrongCount++;
            }
        }
        setRightCount(rightCount);
        setWrongCount(wrongCount);
    }

    public void writeExerciseCheckToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("算式总数: " + equationCount + ";\r\n");
            writer.write("计算正确算式个数：" + rightCount + ";\r\n");
            writer.write("计算错误算式个数：" + wrongCount + ";\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printExerciseCheck() {
        System.out.println("本次练习批改结果：");
        System.out.println("算式总数：" + equationCount);
        System.out.println("计算正确算式个数：" + rightCount);
        System.out.println("计算错误算式个数：" + wrongCount);
    }
}