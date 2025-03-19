package org.clover.generation;

public class AdditionOperation extends BinaryOperation {

    @Override
    boolean checkingCalculation(int anInteger) {
        return anInteger <= UPPER;
    }

    @Override
    int calculate(int left, int right) {
        return left + right;
    }

    @Override
    char getOperator() {
        return '+';
    }
}