package org.clover.util;

import org.clover.entity.Equation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URI;
import java.net.URISyntaxException;

public class ExerciseCSV {
    private static final Pattern CLEAN_PATTERN = Pattern.compile("[^0-9+\\-=]");
    private static final String CSV_EXTENSION = ".csv";

    public void writeExerciseToFile(List<Equation> equations, String fileName, Class<?> testClass) {
        File targetFile = validateFileName(fileName, testClass);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
            for (int i = 0; i < equations.size(); i++) {
                if (i > 0) writer.write(",");
                writer.write(equations.get(i).getEquation());
            }
            System.out.println("文件已成功写入: " + targetFile.getAbsolutePath());
        } catch (IOException e) {
            handleIOException("写入文件失败", e);
        }
    }

    public List<Equation> readCleanExerciseFromFile(String fileName, Class<?> testClass) {
        return readExerciseFromFile(fileName, testClass, false);
    }

    public List<Equation> readNoisyExerciseFromFile(String fileName, Class<?> testClass) {
        return readExerciseFromFile(fileName, testClass, true);
    }

    private List<Equation> readExerciseFromFile(String fileName, Class<?> testClass, boolean cleanNoise) {
        File sourceFile = validateFileName(fileName, testClass);
        List<Equation> equations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    processEquation(part, cleanNoise, equations);
                }
            }
            printExercise(equations);
        } catch (IOException e) {
            handleIOException("读取文件失败", e);
        }
        return equations;
    }

    private File validateFileName(String fileName, Class<?> testClass) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }

        URI testClassLocation;
        try {
            testClassLocation = testClass.getProtectionDomain().getCodeSource().getLocation().toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException("无法获取测试类的位置", e);
        }

        File testClassDir = new File(testClassLocation).getParentFile();
        File file = new File(testClassDir, fileName.endsWith(CSV_EXTENSION) ? fileName : fileName + CSV_EXTENSION);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    private void processEquation(String rawEquation, boolean cleanNoise, List<Equation> equations) {
        String processed = cleanNoise ?
                CLEAN_PATTERN.matcher(rawEquation.trim()).replaceAll("") :
                rawEquation.trim();

        if (isValidEquation(processed)) {
            parseEquation(processed).ifPresent(equations::add);
        } else {
            System.out.println("无效方程: " + rawEquation);
        }
    }

    private boolean isValidEquation(String equation) {
        return equation.matches("^\\d+\\s*[+\\-]\\s*\\d+\\s*=\\s*\\d+$");
    }

    private Optional<Equation> parseEquation(String equationStr) {
        try {
            // 使用正则表达式来匹配整个方程的结构
            String pattern = "^\\s*(\\d+)\\s*([+\\-])\\s*(\\d+)\\s*=\\s*(\\d+)\\s*$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(equationStr.trim());
            if (!m.find()) {
                return Optional.empty();
            }

            int left = Integer.parseInt(m.group(1));
            char operator = m.group(2).charAt(0);
            int right = Integer.parseInt(m.group(3));
            int result = Integer.parseInt(m.group(4));

            Equation equation = new Equation();
            equation.setLeft(left);
            equation.setRight(right);
            equation.setNotation(operator);
            equation.setResult(result);
            equation.setEquation(); // 调用保持格式的方法

            return Optional.of(equation);
        } catch (NumberFormatException e) {
            System.out.println("解析方程失败: " + equationStr);
            return Optional.empty();
        }
    }

    private void handleIOException(String message, IOException e) {
        System.err.println(message + ": " + e.getMessage());
        if (e instanceof FileNotFoundException) {
            System.err.println("文件路径: " + e.getMessage());
        }
    }

    public void printExercise(List<Equation> equations) {
        System.out.println("习题列表（共" + equations.size() + "题）：");
        equations.forEach(eq ->
                System.out.printf("%-8s → 结果: %d%n", eq.getEquation(), eq.getResult())
        );
    }
}