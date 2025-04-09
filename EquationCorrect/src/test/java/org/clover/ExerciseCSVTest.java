package org.clover;

import org.clover.entity.Equation;
import org.clover.util.ExerciseCSV;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExerciseCSVTest {
    @Test
    public void testReadNoisyExerciseFromFile() throws IOException {
        // 创建测试CSV文件
        File testFile = new File("test_exercises.csv");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("78 + 19 = 97\n");
            writer.write("69 + 2 = 71\n");
            writer.write("27 + 48 = \n");
            writer.write("56 + 16 = 72\n");
            writer.write("29 + 5 = \n");
            writer.write("70 - 51 = 19\n");
            writer.write("55 - 30 = 25\n");
            writer.write("78 - 35 = \n");
            writer.write("7 - 6 = 1\n");
            writer.write("69 - 27 = 42\n");
        }

        // 测试读取CSV文件
        ExerciseCSV csv = new ExerciseCSV();
        List<Equation> equations = csv.readNoisyExerciseFromFile("test_exercises.csv");
        System.out.println("读取的方程数量: " + equations.size());
        assertEquals(10, equations.size());

        // 批改答案
        org.clover.gui.ExerciseAnswer exerciseAnswer = new org.clover.gui.ExerciseAnswer();
        for (int i = 0; i < equations.size(); i++) {
            exerciseAnswer.add(-1); // 初始化为空置
        }

        // 设置一些答案
        exerciseAnswer.set(0, 97);
        exerciseAnswer.set(1, 71);
        exerciseAnswer.set(2, -1); // 空置
        exerciseAnswer.set(3, 72);
        exerciseAnswer.set(4, -1); // 空置
        exerciseAnswer.set(5, 19);
        exerciseAnswer.set(6, 25);
        exerciseAnswer.set(7, -1); // 空置
        exerciseAnswer.set(8, 1);
        exerciseAnswer.set(9, 42);

        // 批改结果
        int correct = 0, wrong = 0, empty = 0;
        for (int i = 0; i < equations.size(); i++) {
            Equation eq = equations.get(i);
            Integer ans = exerciseAnswer.get(i);
            if (ans == -1) {
                empty++;
            } else if (ans == eq.getResult()) {
                correct++;
            } else {
                wrong++;
            }
        }

        System.out.println("批改结果：正确: " + correct + ", 错误: " + wrong + ", 空置: " + empty);
        assertEquals(7, correct);
        assertEquals(0, wrong);
        assertEquals(3, empty);

        // 删除测试文件
        testFile.delete();
    }
}