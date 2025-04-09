package org.clover.gui;

import java.util.ArrayList;

public class ExerciseAnswer {
    private ArrayList<Integer> exerciseAnswer;

    public ExerciseAnswer() {
        exerciseAnswer = new ArrayList<>();
    }

    public void add(int equationAnswer) {
        exerciseAnswer.add(equationAnswer);
    }

    public void set(int index, int equationAnswer) {
        if (index >= 0 && index < exerciseAnswer.size()) {
            exerciseAnswer.set(index, equationAnswer);
        }
    }

    public int get(int index) {
        if (index >= 0 && index < exerciseAnswer.size()) {
            return exerciseAnswer.get(index);
        }
        return -1;
    }

    public int size() {
        return exerciseAnswer.size();
    }
}