package org.clover.generation;

public class SubtractOperation extends BinaryOperation {

    @Override
    boolean checkingCalculation(int anInteger) {
        return anInteger >= LOWER;
    }

    @Override
    int calculate(int left, int right) {
        return left - right;
    }

    @Override
    char getOperator() {
        return '-';
    }
}