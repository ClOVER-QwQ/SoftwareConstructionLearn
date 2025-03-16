package org.clover.entity.bo;

import lombok.Data;

@Data
public class Equation {
    private int left;
    private int right;
    private char notation;
    private int result;
    private String equation;
}
