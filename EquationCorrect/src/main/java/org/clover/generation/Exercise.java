package org.clover.generation;

import lombok.Getter;
import lombok.Setter;
import org.clover.entity.Equation;
import org.clover.generation.BinaryOperation;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Exercise {
    private final BinaryOperation addition = new AdditionOperation();
    private final BinaryOperation subtract = new SubtractOperation();
    private List<Equation> equations;

    public Exercise(int count) {
        this.equations = new ArrayList<>();
        generateExerciseOfEquations(count);
    }

    public List<Equation> generateExerciseOfAdditionEquations(int count) {
        return generateExercise(addition, count);
    }

    public List<Equation> generateExerciseOfSubtractionEquations(int count) {
        return generateExercise(subtract, count);
    }

    public List<Equation> generateExerciseOfEquations(int count) {
        int halfCount = count / 2;
        int remainder = count % 2; // 计算余数
        equations.addAll(generateExercise(addition, halfCount));
        equations.addAll(generateExercise(subtract, halfCount));
        if (remainder > 0) {
            // 如果有余数，额外生成一道题
            equations.addAll(generateExercise(addition, remainder));
        }
        return equations;
    }

    private List<Equation> generateExercise(BinaryOperation operation, int count) {
        List<Equation> equationList = new ArrayList<>();
        int attempts = 0;
        int maxAttempts = 100; // 最大尝试次数

        while (equationList.size() < count && attempts < maxAttempts) {
            Equation newEquation = operation.generateBinaryOperation();
            if (!equationList.contains(newEquation)) {
                equationList.add(newEquation);
            }
            attempts++;
        }

        if (equationList.size() < count) {
            throw new RuntimeException("Failed to generate " + count + " unique equations within " + maxAttempts + " attempts");
        }

        return equationList;
    }

    public int size() {
        return equations.size();
    }
}