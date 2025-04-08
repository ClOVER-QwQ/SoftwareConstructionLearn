package org.clover.generation;

import lombok.Getter;
import lombok.Setter;
import org.clover.entity.Equation;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@Getter
@Setter
public abstract class BinaryOperation {
    protected static final int UPPER = 100;
    protected static final int LOWER = 0;

    public Equation generateBinaryOperation() {
        int left;
        int right;
        char op;
        int result;
        Random random = new Random();
        left = random.nextInt(LOWER, UPPER);
        do {
            right = random.nextInt(LOWER, UPPER);
            result = calculate(left, right);
        } while (!(checkingCalculation(result)));

        // 创建一个新的 Equation 对象
        Equation equation = new Equation();
        equation.setLeft(left);
        equation.setRight(right);
        op = getOperator(); // 获取实际的运算符
        equation.setNotation(op);
        equation.setResult(result);
        equation.buildEquation();
        return equation;
    }

    @NotNull
    private String asString(@NotNull Equation equation) {
        return equation.getLeft() + " " + equation.getNotation() + " " + equation.getRight() + " = " + equation.getResult();
    }

    abstract char getOperator();
    abstract boolean checkingCalculation(int anInteger);
    abstract int calculate(int left, int right);
}