package org.clover.gui;

import java.util.ArrayList;

public class ExerciseAnswer {
    private final ArrayList<Integer> exerciseAnswer;
    private int equationIndex;

    public ExerciseAnswer() {
        exerciseAnswer = new ArrayList<>();
        equationIndex = 0;
    }

    public boolean add(int equationAnswer) {
        return exerciseAnswer.add(equationAnswer);
    }

    public boolean hasNext() {
        return equationIndex < exerciseAnswer.size();
    }

    public int next() {
        if (equationIndex < exerciseAnswer.size()) {
            return exerciseAnswer.get(equationIndex++);
        } else {
            return -1;
        }
    }

    public int get(int equationIndex) {
        return exerciseAnswer.get(equationIndex);
    }

    public void set(int equationIndex, int equationAnswer) {
        exerciseAnswer.set(equationIndex, equationAnswer);
    }
}