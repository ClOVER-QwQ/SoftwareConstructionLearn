package org.clover.generation;

import org.clover.entity.Equation;

import java.util.List;

public class ExerciseSheet {
    public void formatAndDisplayExercise(String title, List<Equation> equations) {
        System.out.println(title);
        System.out.println("----------------------------------------");

        int size = equations.size();
        for (int i = 0; i < size; i++) {
            int group = (i / 10) + 1;
            int start = (group - 1) * 10 + 1;
            int end = group * 10;
            if (i % 10 == 0) {
                System.out.print("题目 " + start + "~" + end + ": ");
            }
            System.out.print((equations.get(i).getEquation()) + "\t");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
