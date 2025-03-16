package org.clover.service.impl;

import org.junit.Test;

public class EquationServiceImplTest {
    private final EquationServiceImpl equationService = new EquationServiceImpl();

    @Test
    public void generateAdditionEquation() {
        System.out.println(equationService.generateAdditionEquation().getData().getEquation());
    }

    @Test
    public void generateSubtractionEquation() {
        System.out.println(equationService.generateSubtractionEquation().getData().getEquation());
    }

    @Test
    public void generateEquation() {
        System.out.println(equationService.generateEquation().getData().getEquation());
    }

    @Test
    public void generateExerciseOfAdditionEquations() {
        equationService.formatAndDisplayExercise("加法习题",equationService.generateExerciseOfAdditionEquations().getData());
    }

    @Test
    public void generateExerciseOfSubtractionEquations() {
        equationService.formatAndDisplayExercise("减法习题",equationService.generateExerciseOfSubtractionEquations().getData());
    }

    @Test
    public void generateExerciseOfEquations() {
        equationService.formatAndDisplayExercise("混合习题",equationService.generateExerciseOfEquations().getData());
    }
}