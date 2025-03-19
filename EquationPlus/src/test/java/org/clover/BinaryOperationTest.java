package org.clover;

import org.clover.generation.AdditionOperation;
import org.clover.generation.BinaryOperation;
import org.clover.generation.SubtractOperation;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BinaryOperationTest {
    private BinaryOperation adder = new AdditionOperation();
    private BinaryOperation subtractor = new SubtractOperation();

    @Test
    public void testAddition() {
        System.out.println(adder.generateBinaryOperation());
        System.out.println(subtractor.generateBinaryOperation());
    }
}
