package org.clover.gui;

import lombok.Getter;
import lombok.Setter;
import org.clover.entity.Equation;
import org.clover.generation.Exercise;
import org.clover.util.ExerciseCSV;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("CallToPrintStackTrace")
public class ExerciseGUI extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private JPanel panelCenter;
    private JTextField[] tAnswer;
    private JButton preButton, nextButton;
    private JLabel pageIndex;
    @Setter
    @Getter
    private int equationCount;
    private int pages, currentPage;
    private Exercise exercise;
    private ExerciseAnswer answer;
    private JButton check;
    private JLabel labelResult;
    private final int PAGE_SIZE = 20;

    public ExerciseGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);  // 增大窗口尺寸
        setTitle("数学练习题系统 v1.0");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setResizable(false);
        init();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ExerciseGUI frame = new ExerciseGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void init() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuOnline = new JMenu("在线练习");
        menuBar.add(menuOnline);

        JMenuItem onlineAdd = new JMenuItem("加法");
        onlineAdd.addActionListener(this::online);
        menuOnline.add(onlineAdd);

        JMenuItem onlineSub = new JMenuItem("减法");
        onlineSub.addActionListener(this::online);
        menuOnline.add(onlineSub);

        JMenuItem onlineMix = new JMenuItem("混合");
        onlineMix.addActionListener(this::online);
        menuOnline.add(onlineMix);

        JMenu menuOutline = new JMenu("批量练习");
        menuBar.add(menuOutline);

        JMenuItem outlineAdd = new JMenuItem("加法");
        outlineAdd.addActionListener(this::outline);
        menuOutline.add(outlineAdd);

        JMenuItem outlineSub = new JMenuItem("减法");
        outlineSub.addActionListener(this::outline);
        menuOutline.add(outlineSub);

        JMenuItem outlineMix = new JMenuItem("混合");
        outlineMix.addActionListener(this::outline);
        menuOutline.add(outlineMix);

        JMenu menuCheck = new JMenu("批改习题");
        menuBar.add(menuCheck);

        JMenuItem judge = new JMenuItem("批改习题");
        judge.addActionListener(e -> checkEx());
        menuCheck.add(judge);

        JMenu menuExit = new JMenu("退出");
        menuBar.add(menuExit);

        JMenuItem exit = new JMenuItem("退出");
        exit.addActionListener(e -> System.exit(0));
        menuExit.add(exit);

        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(0, 1, 10, 5)); // 自适应行数，1列（每行一个Panel）
        JScrollPane scrollPane = new JScrollPane(panelCenter); // 添加滚动条
        contentPane.add(BorderLayout.CENTER, scrollPane);

        JTextField[] tEquation = new JTextField[PAGE_SIZE];
        tAnswer = new JTextField[PAGE_SIZE];
        for (int i = 0; i < PAGE_SIZE; i++) {
            tEquation[i] = new JTextField(18);  // 加宽算式框
            tEquation[i].setFont(new Font("宋体", Font.PLAIN, 16));
            tAnswer[i] = new JTextField(5);
            tAnswer[i].setFont(new Font("宋体", Font.PLAIN, 16));

            // 创建带标签的面板
            JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            rowPanel.add(new JLabel((i+1) + ". "));  // 添加题号
            rowPanel.add(tEquation[i]);
            rowPanel.add(new JLabel("答案："));
            rowPanel.add(tAnswer[i]);

            panelCenter.add(rowPanel);
            rowPanel.setVisible(false);
        }

        preButton = new JButton("上一页");
        preButton.addActionListener(e -> prePage());
        nextButton = new JButton("下一页");
        nextButton.addActionListener(e -> nextPage());
        panelCenter.add(preButton);
        pageIndex = new JLabel();
        panelCenter.add(pageIndex);
        panelCenter.add(nextButton);
        preButton.setVisible(false);
        nextButton.setVisible(false);

        check = new JButton("检查");
        check.addActionListener(e -> check());
        labelResult = new JLabel();
        JPanel panelSouth = new JPanel();
        panelSouth.add(check);
        panelSouth.add(labelResult);
        check.setVisible(false);
        labelResult.setVisible(false);
        contentPane.add(BorderLayout.SOUTH, panelSouth);
    }

    private void online(ActionEvent e) {
        String action = e.getActionCommand();
        int count = Integer.parseInt(JOptionPane.showInputDialog("请输入题目数量"));
        exercise = new Exercise(count);
        if ("加法".equals(action)) {
            exercise.generateExerciseOfAdditionEquations(count);
        } else if ("减法".equals(action)) {
            exercise.generateExerciseOfSubtractionEquations(count);
        } else {
            exercise.generateExerciseOfEquations(count);
        }
        answer = new ExerciseAnswer();
        for (int i = 0; i < count; i++) {
            answer.add(-1);
        }
        pages = (int) Math.ceil(count * 1.0 / PAGE_SIZE);
        currentPage = 1;
        update();
        check.setVisible(true); // 显示检查按钮
    }

    private void outline(ActionEvent e) {
        String action = e.getActionCommand();
        int count = Integer.parseInt(JOptionPane.showInputDialog("请输入题目数量"));
        exercise = new Exercise(count);
        if ("加法".equals(action)) {
            exercise.generateExerciseOfAdditionEquations(count);
        } else if ("减法".equals(action)) {
            exercise.generateExerciseOfSubtractionEquations(count);
        } else {
            exercise.generateExerciseOfEquations(count);
        }
        answer = new ExerciseAnswer();
        for (int i = 0; i < count; i++) {
            answer.add(-1);
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择保存目录");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File saveDir = fileChooser.getSelectedFile();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
            String timestamp = now.format(formatter);
            String fileName = String.format("%s/%s-%s习题.csv", saveDir.getAbsolutePath(), timestamp, action);

            ExerciseCSV csv = new ExerciseCSV();
            csv.writeExerciseToFile(exercise.getEquations(), fileName);

            JOptionPane.showMessageDialog(this, "文件已保存到: " + fileName);

            try {
                Desktop.getDesktop().open(new File(fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "无法自动打开文件，请手动打开。");
            }
        }
    }

    private void checkEx() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("选择批改文件");
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ExerciseCSV csv = new ExerciseCSV();
            List<Equation> equations = csv.readNoisyExerciseFromFile(file.getAbsolutePath());
            CheckAnswer checker = new CheckAnswer();
            checker.checkExercise(equations, answer);
            checker.printExerciseCheck(); // Print to console or update GUI
            labelResult.setText("正确: " + checker.getRightCount() + " 错误: " + checker.getWrongCount());
        }
    }

    private void update() {
        // 隐藏所有行
        for (Component comp : panelCenter.getComponents()) {
            comp.setVisible(false);
        }

        int start = (currentPage - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, exercise.getEquations().size());

        // 显示当前页题目
        for (int i = start, row = 0; i < end; i++, row++) {
            Component rowPanel = panelCenter.getComponent(row);
            Equation eq = exercise.getEquations().get(i);

            // 获取当前行的组件
            JTextField equationField = (JTextField) ((JPanel)rowPanel).getComponent(1);
            JTextField answerField = (JTextField) ((JPanel)rowPanel).getComponent(3);

            equationField.setText(eq.getQuestion());
            answerField.setText(answer.get(i) != -1 ? String.valueOf(answer.get(i)) : "");
            rowPanel.setVisible(true);
        }

        // 更新分页状态
        pageIndex.setText(currentPage + "/" + pages);
        preButton.setEnabled(currentPage > 1);
        nextButton.setEnabled(currentPage < pages);
    }

    private void prePage() {
        for (int i = (currentPage - 1) * PAGE_SIZE, j = 0; i < currentPage * PAGE_SIZE; i++, j++) {
            if (i < exercise.getEquations().size() && tAnswer[j].getText() != null && !tAnswer[j].getText().isEmpty()) {
                if (tAnswer[j].getText().matches("^[0-9]*$")) {
                    answer.set(i, Integer.parseInt(tAnswer[j].getText()));
                }
            }
        }
        currentPage--;
        update();
    }

    private void nextPage() {
        for (int i = (currentPage - 1) * PAGE_SIZE, j = 0; i < currentPage * PAGE_SIZE; i++, j++) {
            if (i < exercise.getEquations().size() && tAnswer[j].getText() != null && !tAnswer[j].getText().isEmpty()) {
                if (tAnswer[j].getText().matches("^[0-9]*$")) {
                    answer.set(i, Integer.parseInt(tAnswer[j].getText()));
                }
            }
        }
        currentPage++;
        update();
    }

    private void check() {
        int correct = 0, wrong = 0;
        for (int i = 0; i < exercise.getEquations().size(); i++) {
            Equation eq = exercise.getEquations().get(i);
            JTextField answerField = tAnswer[i % PAGE_SIZE]; // 获取当前页的答案框
            String userAnswerStr = answerField.getText().trim();

            if (userAnswerStr.isEmpty()) {
                continue; // 跳过未填写的答案
            }

            try {
                int userAnswer = Integer.parseInt(userAnswerStr);
                if (userAnswer == eq.getResult()) {
                    correct++;
                } else {
                    wrong++;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字答案！", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // 显示批改结果
        JOptionPane.showMessageDialog(this, "批改结果：\n正确: " + correct + "\n错误: " + wrong, "批改结果", JOptionPane.INFORMATION_MESSAGE);

        // 更新界面显示
        labelResult.setText("正确: " + correct + " 错误: " + wrong);
        labelResult.setVisible(true);
    }

    private void showPieChart(int correct, int wrong) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("正确", correct);
        dataset.setValue("错误", wrong);

        JFreeChart chart = ChartFactory.createPieChart(
                "批改结果", dataset, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));

        JFrame frame = new JFrame("批改结果");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void showResultTable(List<Equation> equations, List<Integer> userAnswers) {
        String[] columns = {"算式", "用户答案", "正确答案", "结果"};
        Object[][] data = new Object[equations.size()][4];
        for (int i = 0; i < equations.size(); i++) {
            Equation eq = equations.get(i);
            Integer ans = userAnswers.get(i);
            data[i][0] = eq.getEquation();
            data[i][1] = (ans != null) ? ans : "无答案";
            data[i][2] = eq.getResult();
            data[i][3] = (ans != null && ans == eq.getResult()) ? "正确" : "错误";
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        JFrame frame = new JFrame("批改详情");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

}