package org.clover.service;

import org.clover.entity.bo.Equation;
import org.clover.entity.dto.Result;

import java.util.List;

public interface EquationService {
    Result<Equation> generateAdditionEquation();
    Result<Equation> generateSubtractionEquation();
    Result<Equation> generateEquation();
    Result<List<Equation>> generateExerciseOfAdditionEquations();
    Result<List<Equation>> generateExerciseOfSubtractionEquations();
    Result<List<Equation>> generateExerciseOfEquations();
}