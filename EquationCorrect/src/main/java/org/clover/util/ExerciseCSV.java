package org.clover.util;

import org.clover.entity.Equation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseCSV {
    private static final Pattern CLEAN_PATTERN = Pattern.compile("[^0-9+\\-=]");
    private static final String CSV_EXTENSION = ".csv";

    // 写入CSV文件
    public void writeExerciseToFile(List<Equation> equations, String fileName) {
        File targetFile = validateFileName(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
            for (Equation equation : equations) {
                writer.write(equation.getEquation());
                writer.newLine();
            }
        } catch (IOException e) {
            handleIOException("写入文件失败", e);
        }
    }

    // 从无噪声CSV文件读取
    public List<Equation> readCleanExerciseFromFile(String fileName) {
        return readExerciseFromFile(fileName, false);
    }

    // 从包含噪声的CSV文件读取
    public List<Equation> readNoisyExerciseFromFile(String fileName) {
        return readExerciseFromFile(fileName, true);
    }

    private List<Equation> readExerciseFromFile(String fileName, boolean cleanNoise) {
        File sourceFile = validateFileName(fileName);
        List<Equation> equations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // 去掉首尾空格
                if (line.isEmpty()) continue; // 跳过空行

                String[] parts = line.split(",", -1);
                if (parts.length < 1) continue; // 跳过无效行

                String equationStr = parts[0].trim();
                processEquation(equationStr, cleanNoise, equations);
            }
        } catch (IOException e) {
            handleIOException("读取文件失败", e);
        }
        return equations;
    }

    private File validateFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        return new File(fileName.endsWith(CSV_EXTENSION) ? fileName : fileName + CSV_EXTENSION);
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
        return equation.matches("^\\d+\\s*[+\\-]\\s*\\d+\\s*(=\\s*\\d*)?$");
    }

    private Optional<Equation> parseEquation(String equationStr) {
        try {
            String pattern = "^\\s*(\\d+)\\s*([+\\-])\\s*(\\d+)\\s*(=\\s*(\\d*))?$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(equationStr.trim());
            if (!m.find()) {
                return Optional.empty();
            }

            int left = Integer.parseInt(m.group(1));
            char operator = m.group(2).charAt(0);
            int right = Integer.parseInt(m.group(3));
            int result = -1;

            String resultStr = m.group(5);
            if (resultStr != null && !resultStr.isEmpty()) {
                result = Integer.parseInt(resultStr);
            }

            Equation equation = new Equation();
            equation.setLeft(left);
            equation.setRight(right);
            equation.setNotation(operator);
            equation.setResult(result);
            equation.setEquation(equationStr);

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