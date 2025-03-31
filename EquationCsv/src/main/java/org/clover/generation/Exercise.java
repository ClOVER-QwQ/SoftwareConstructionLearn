package org.clover.generation;

import org.clover.entity.Equation;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private final BinaryOperation addition = new AdditionOperation();
    private final BinaryOperation subtract = new SubtractOperation();

    @SuppressWarnings("unused")
    public List<Equation> generateExerciseOfAdditionEquations() {
        return generateExercise(addition);
    }

    @SuppressWarnings("unused")
    public List<Equation> generateExerciseOfSubtractionEquations() {
        return generateExercise(subtract);
    }

    public List<Equation> generateExerciseOfEquations() {
        List<Equation> equationList = new ArrayList<>();

        int maxAttempts = 100; // 最大尝试次数
        int attempts = 0;

        while (equationList.size() < 50 && attempts < maxAttempts) {
            Equation newAdditionEquation = addition.generateBinaryOperation();
            if (existsInList(newAdditionEquation, equationList)) {
                equationList.add(newAdditionEquation);
            }

            Equation newSubtractionEquation = subtract.generateBinaryOperation();
            if (existsInList(newSubtractionEquation, equationList)) {
                equationList.add(newSubtractionEquation);
            }

            attempts++;
        }

        if (equationList.size() < 50) {
            throw new RuntimeException("Failed to generate 50 unique equations within " + maxAttempts + " attempts");
        }

        return equationList;
    }

    private List<Equation> generateExercise(BinaryOperation operation) {
        List<Equation> equationList = new ArrayList<>();

        int maxAttempts = 100; // 最大尝试次数
        int attempts = 0;

        while (equationList.size() < 50 && attempts < maxAttempts) {
            Equation newEquation = operation.generateBinaryOperation();
            if (existsInList(newEquation, equationList)) {
                equationList.add(newEquation);
            }

            attempts++;
        }

        if (equationList.size() < 50) {
            throw new RuntimeException("Failed to generate 50 unique equations within " + maxAttempts + " attempts");
        }

        return equationList;
    }

    // 检查 equation 是否已经存在于 list 中
    private boolean existsInList(Equation newEquation, List<Equation> equationList) {
        for (Equation existingEquation : equationList) {
            if (isEqual(newEquation, existingEquation)) {
                return false;
            }
        }
        return true;
    }

    // 比较两个 Equation 是否相等
    private boolean isEqual(Equation equation1, Equation equation2) {
        // 对于加法，考虑交换律
        if (equation1.getNotation() == '+' && equation2.getNotation() == '+') {
            return (equation1.getLeft() == equation2.getLeft() && equation1.getRight() == equation2.getRight()) ||
                    (equation1.getLeft() == equation2.getRight() && equation1.getRight() == equation2.getLeft());
        }
        // 对于其他运算（如减法），直接比较左右操作数
        return equation1.getLeft() == equation2.getLeft() && equation1.getRight() == equation2.getRight();
    }
}