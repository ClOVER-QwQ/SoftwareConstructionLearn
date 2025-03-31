package org.clover.entity;

import lombok.Data;

@Data
public class Equation {
    private int left;
    private int right;
    private char notation;
    private int result;
    private String equation;

    public void setEquation() {
        // 保持原有空格格式："3 + 5 = 8"
        this.equation = String.format("%d %c %d = %d",
                this.left,
                this.notation,
                this.right,
                this.result
        );
    }
}