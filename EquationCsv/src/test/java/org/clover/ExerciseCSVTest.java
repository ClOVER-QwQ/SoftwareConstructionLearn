package org.clover;

import org.clover.entity.Equation;
import org.clover.generation.Exercise;
import org.clover.util.ExerciseCSV;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ExerciseCSVTest {
    private ExerciseCSV exerciseCSV;
    private static final Exercise exercise = new Exercise();
    private static final List<Equation> sampleEquations = exercise.generateExerciseOfEquations();
    @Before
    public void setUp() {
        this.exerciseCSV = new ExerciseCSV();
    }

    @Test
    public void testWriteExerciseToFile() throws IOException {
        String fileName = "exercises.csv";
        this.exerciseCSV.writeExerciseToFile(sampleEquations, fileName, this.getClass());
        File outputFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().toPath().resolve(fileName).toFile();
        Assert.assertTrue(outputFile.exists());

        // 验证文件内容
        String fileContent = readFileContent(outputFile);
        System.out.println("文件内容: " + fileContent);
        Assert.assertFalse(fileContent.isEmpty());
    }

    @Test
    public void testReadExerciseFromFile() throws IOException {
        String fileName = "exercises.csv";
        File sourceFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().toPath().resolve(fileName).toFile();

        // 确保文件存在
        Assert.assertTrue(sourceFile.exists());
        System.out.println("文件路径: " + sourceFile.getAbsolutePath());

        // 验证文件内容
        String fileContent = readFileContent(sourceFile);
        System.out.println("文件内容: " + fileContent);
        Assert.assertFalse(fileContent.isEmpty());

        // 动态生成预期结果
        List<Equation> expectedEquations = sampleEquations;
        List<Equation> result = this.exerciseCSV.readCleanExerciseFromFile(fileName, this.getClass());

        // 验证读取结果
        Assert.assertEquals(expectedEquations.size(), result.size());
        assertEquationsMatch(expectedEquations, result);
    }

    @Test
    public void testReadNoisyExerciseFromFile() throws IOException {
        String fileName = "noisyTest.csv";
        File inputFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().toPath().resolve(fileName).toFile();
        writeFileContent(inputFile);
        List<Equation> expected = List.of(
                createEquation(3, '+', 5, "3 + 5 = 8"),
                createEquation(8, '-', 2, "8 - 2 = 6"),
                createEquation(15, '-', 7, "15 - 7 = 8")
        );
        List<Equation> result = this.exerciseCSV.readNoisyExerciseFromFile(fileName, this.getClass());
        Assert.assertEquals(3L, result.size());
        assertEquationsMatch(expected, result);
    }

    private Equation createEquation(int left, char op, int right, String equationStr) {
        Equation eq = new Equation();
        eq.setLeft(left);
        eq.setRight(right);
        eq.setNotation(op);
        eq.setEquation(equationStr);
        eq.setResult(op == '+' ? left + right : left - right);
        return eq;
    }

    private String readFileContent(File file) throws IOException {
        return (new String(Files.readAllBytes(file.toPath()))).trim().replaceAll("\\s+", "");
    }

    private void writeFileContent(File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("  3 + 5 = 8  ,  abc,  8 - 2 = 6  ,  10 + xyz20  , @#$,  15 - 7 = 8  ");
        }
    }

    private void assertEquationsMatch(List<Equation> expected, List<Equation> actual) {
        for (int i = 0; i < expected.size(); i++) {
            Equation exp = expected.get(i);
            Equation act = actual.get(i);
            Assert.assertEquals(exp.getEquation(), act.getEquation());
            Assert.assertEquals((long) exp.getLeft(), (long) act.getLeft());
            Assert.assertEquals((long) exp.getRight(), (long) act.getRight());
            Assert.assertEquals(exp.getNotation(), act.getNotation());
            Assert.assertEquals((long) exp.getResult(), (long) act.getResult());
        }
    }

    @After
    public void tearDown() {
    }
}