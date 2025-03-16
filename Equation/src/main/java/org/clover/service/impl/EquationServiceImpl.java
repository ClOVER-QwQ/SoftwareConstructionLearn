package org.clover.service.impl;

import org.clover.entity.bo.Equation;
import org.clover.entity.dto.Result;
import org.clover.service.EquationService;

import java.util.*;

public class EquationServiceImpl implements EquationService {
    private final Random random = new Random(); // 随机数生成器

    private int calculateAdditionEquation(Equation equation) {
        return equation.getLeft() + equation.getRight();
    }

    private int calculateSubtractionEquation(Equation equation) {
        return equation.getLeft() - equation.getRight();
    }

    public Result<Equation> generateAdditionEquation() {
        Equation equation = new Equation();
        equation.setNotation('+'); // 设置运算符为加号
        do {
            // 随机生成两个操作数
            equation.setLeft(random.nextInt(1, 100)); // 左操作数
            equation.setRight(random.nextInt(1, 100)); // 右操作数
            equation.setResult(calculateAdditionEquation(equation)); // 计算结果
        } while (equation.getResult() >= 100);

        // 拼接方程字符串
        equation.setEquation(asString(equation));

        // 创建 Result<Equation> 对象并返回
        Result<Equation> result = new Result<>();
        result.setData(equation);

        return result;
    }

    @Override
    public Result<Equation> generateSubtractionEquation() {
        Equation equation = new Equation();
        equation.setNotation('-'); // 设置运算符为减号
        do {
            // 随机生成两个操作数
            equation.setLeft(random.nextInt(1, 100)); // 左操作数
            equation.setRight(random.nextInt(1, 100)); // 右操作数
            equation.setResult(calculateSubtractionEquation(equation)); // 计算结果
        } while (equation.getResult() <= 0);

        // 拼接方程字符串
        equation.setEquation(asString(equation));

        // 创建 Result<Equation> 对象并返回
        Result<Equation> result = new Result<>();
        result.setData(equation);

        return result;
    }

    @Override
    public Result<Equation> generateEquation() {
        int flag = random.nextInt(0, 2);
        Result<Equation> result = new Result<>();
        if (flag == 0) {
            result = generateAdditionEquation();
        }
        if (flag == 1) {
            result = generateSubtractionEquation();
        }
        return result;
    }

    @Override
    public Result<List<Equation>> generateExerciseOfAdditionEquations() {
        Result<List<Equation>> result = new Result<>();
        Set<Equation> equationSet = new HashSet<>();

        int maxAttempts = 100; // 最大尝试次数
        int attempts = 0;

        while (equationSet.size() < 50 && attempts < maxAttempts) {
            Result<Equation> equationResult = generateAdditionEquation();
            Equation newEquation = equationResult.getData();

            // 检查是否重复
            if (!occursIn(newEquation, equationSet)) {
                equationSet.add(newEquation);
                attempts++;
            }
        }

        if (equationSet.size() < 50) {
            throw new RuntimeException("Failed to generate 50 unique equations within " + maxAttempts + " attempts");
        }

        // 将Set转换为List
        List<Equation> equationList = new ArrayList<>(equationSet);
        result.setData(equationList);
        return result;
    }

    @Override
    public Result<List<Equation>> generateExerciseOfSubtractionEquations() {
        Result<List<Equation>> result = new Result<>();
        Set<Equation> equationSet = new HashSet<>(); // 使用HashSet去重

        int maxAttempts = 100; // 最大尝试次数
        int attempts = 0;

        while (equationSet.size() < 50 && attempts < maxAttempts) {
            Result<Equation> equationResult = generateSubtractionEquation();
            Equation newEquation = equationResult.getData();

            // 检查是否重复
            if (!occursIn(newEquation, equationSet)) {
                equationSet.add(newEquation);
                attempts++;
            }
        }

        if (equationSet.size() < 50) {
            throw new RuntimeException("Failed to generate 50 unique equations within " + maxAttempts + " attempts");
        }

        // 将Set转换为List
        List<Equation> equationList = new ArrayList<>(equationSet);
        result.setData(equationList);
        return result;
    }

    @Override
    public Result<List<Equation>> generateExerciseOfEquations() {
        Result<List<Equation>> result = new Result<>();
        Set<Equation> equationSet = new HashSet<>(); // 使用HashSet去重

        int maxAttempts = 100; // 最大尝试次数
        int attempts = 0;

        while (equationSet.size() < 50 && attempts < maxAttempts) {
            Result<Equation> equationResult = generateEquation();
            Equation newEquation = equationResult.getData();

            // 检查是否重复
            if (!occursIn(newEquation, equationSet)) {
                equationSet.add(newEquation);
                attempts++;
            }
        }

        if (equationSet.size() < 50) {
            throw new RuntimeException("Failed to generate 50 unique equations within " + maxAttempts + " attempts");
        }

        // 将Set转换为List
        List<Equation> equationList = new ArrayList<>(equationSet);
        result.setData(equationList);
        return result;
    }


    private boolean occursIn(Equation newEquation, Set<Equation> equations) {
        for (Equation existingEquation : equations) {
            if (isEqual(newEquation, existingEquation)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqual(Equation equation1, Equation equation2) {
        // 如果是加法，考虑交换律
        if (equation1.getNotation() == '+' && equation2.getNotation() == '+') {
            return (equation1.getLeft() == equation2.getLeft() && equation1.getRight() == equation2.getRight()) ||
                    (equation1.getLeft() == equation2.getRight() && equation1.getRight() == equation2.getLeft());
        }
        // 其他运算直接比较 left 和 right
        return equation1.getLeft() == equation2.getLeft() && equation1.getRight() == equation2.getRight();
    }

    public String asString(Equation equation) {
        return equation.getLeft() + " " + equation.getNotation() + " " + equation.getRight() + " = " + equation.getResult();
    }

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
            System.out.print(asString(equations.get(i)) + "\t");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}