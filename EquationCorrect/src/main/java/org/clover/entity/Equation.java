package org.clover.entity;

import lombok.Data;
import java.util.Objects;

@Data
public class Equation {
    private int left;
    private int right;
    private char notation;
    private int result;
    private String equation; // 带答案的完整算式
    private String question; // 无答案的纯算式

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return left == equation.left &&
                right == equation.right &&
                notation == equation.notation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, notation);
    }

    public void buildEquation() {
        this.equation = String.format("%d %c %d = %d", left, notation, right, result);
        this.question = String.format("%d %c %d = ", left, notation, right);
    }
}