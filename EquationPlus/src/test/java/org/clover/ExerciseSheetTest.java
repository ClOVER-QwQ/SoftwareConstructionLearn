package org.clover;

import org.clover.generation.Exercise;
import org.clover.generation.ExerciseSheet;
import org.junit.Test;

public class ExerciseSheetTest {
    private final ExerciseSheet sheet = new ExerciseSheet();
    private final Exercise exercise = new Exercise();

    @Test
    public void testAddition() {
        sheet.formatAndDisplayExercise("加法习题",exercise.generateExerciseOfAdditionEquations());
    }

    @Test
    public void testSubtraction() {
        sheet.formatAndDisplayExercise("减法习题",exercise.generateExerciseOfSubtractionEquations());
    }

    @Test
    public void testMultiplication() {
        sheet.formatAndDisplayExercise("混合习题",exercise.generateExerciseOfEquations());
    }
}
