package example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class ExerciseGUI extends JFrame {

	//1��������Ա����
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCenter, panelSouth;
	private JTextField tEquation[];
	private JTextField tAnswer[];
	private JButton preButton, nextButton;
	private JLabel pageIndex;
	private int equationCount;
	private int pages, currentPage;
	private Exercise exercise;
	private ExerciseAnswer answer;
	private JButton check;
	private JLabel labelResult;
	private final int PAGE_SIZE = 20;

	//2��������Ա������getter��setter��������������ڣ�����δ����
	
	//3���������캯������ʼ��������
	public ExerciseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setResizable(false);
		init();//��������������/Ԫ�س�ʼ��
	}
	
    //4��������Ա��������1�����⣺�������
	public static void main(String[] args) {
		
		ExerciseGUI frame = new ExerciseGUI();
		frame.setVisible(true);
		//EDT�̷ַ߳�����ֹ��������
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ExerciseGUI frame = new ExerciseGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
	
	//4��������Ա��������2����ʼ����������������˵��ȣ�
		private void init() {
			//�ٴ����˵���
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			//��.�٣������˵����ϵĵ�һ���˵���������ϰ��
			JMenu menuOnline = new JMenu("������ϰ");
			menuBar.add(menuOnline);
			
			//��.��.�٣�������������ϰ���˵��������˵�����ӷ��˵���
			JMenuItem onlineAdd = new JMenuItem("�ӷ�");
			onlineAdd.setActionCommand("onlineAdd");
			menuOnline.add(onlineAdd); //���˵�����ӵ���������ϰ���˵���
			onlineAdd.addActionListener(new ActionListener() {  //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					online(arg0);//�����¼��ٷ�ִ�к���
				}
			});
			//��.��.�ڣ�������������ϰ���˵��������˵���������˵���
			JMenuItem onlineSub = new JMenuItem("����");
			onlineSub.setActionCommand("onlineSub");
			menuOnline.add(onlineSub);
			onlineSub.addActionListener(new ActionListener() {   //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					online(arg0);//�����¼��ٷ�ִ�к���
				}
			});
			
			//��.��.�ۣ�������������ϰ���˵��������˵�����������˵���
			JMenuItem onlineMix= new JMenuItem("���");
			onlineMix.setActionCommand("onlineMix");
			menuOnline.add(onlineMix);
			onlineMix.addActionListener(new ActionListener() {   //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					online(arg0);//�����¼��ٷ�ִ�к���
				}
			});
			
			//��.�ڣ������˵����ϵĵڶ����˵�������ϰ�⡱��CSV�ļ���д������������ϰ�⣨��ʽ���ӦΪequationStyle02����д��CSV�ļ���
			JMenu menuOutline = new JMenu("����ϰ��");
			menuBar.add(menuOutline);
			
			//��.��.�٣�����������ϰ�⡱�˵��������˵�����ӷ��˵�����ɼӷ�ϰ�⣬д��CSV�ļ���
			JMenuItem outlineAdd = new JMenuItem("�ӷ�");
			outlineAdd.setActionCommand("outlineAdd");
			menuOutline.add(outlineAdd);
			outlineAdd.addActionListener(new ActionListener() {   //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					//��ȫ���ں�������ʵ�������¼�ִ�к���
					outline(arg0);//�����¼��ٷ�ִ�к���
				}
			});
			
			//��.��.�ڣ�����������ϰ�⡱�˵��������˵���������˵�����ɼ���ϰ�⣬д��CSV�ļ���
			JMenuItem outlineSub = new JMenuItem("����");
			outlineSub.setActionCommand("outlineSub");
			menuOutline.add(outlineSub);
			outlineSub.addActionListener(new ActionListener() {   //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					//��ȫ���ں�������ʵ�������¼�ִ�к���
					outline(arg0);//�����¼��ٷ�ִ�к���
				}
			});
			
			//��.��.�ۣ�����������ϰ�⡱�˵��������˵�����������˵�����ɼӼ����ϰ�⣬д��CSV�ļ���
			JMenuItem outlineMix = new JMenuItem("���");
			outlineMix.setActionCommand("outlineMix");
			menuOutline.add(outlineMix);
			outlineMix.addActionListener(new ActionListener() {    //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					//��ȫ���ں�������ʵ�������¼�ִ�к���
					outline(arg0);//�����¼��ٷ�ִ�к���
				}
			});
			
			//��.�ۣ������˵����ϵĵ������˵�������ϰ�⡱��CSV�ļ��������������������õ�ϰ�⣨��ʽ���ӦΪequationStyle03�������ж϶Դ������ģ�
			JMenu menuCheck = new JMenu("����ϰ��");
			menuBar.add(menuCheck);
			JMenuItem judge = new JMenuItem("����ϰ��");
			menuCheck.add(judge);
			judge.addActionListener(new ActionListener() {       //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					//��ȫ���ں�������ʵ�������¼�ִ�к���
					checkEx();//�����¼��ٷ�ִ�к���
				}
			});
			
			//��.�ܣ������˵����ϵĵ��ĸ��˵����˳���
			JMenu menuExit = new JMenu("�˳�");
			menuBar.add(menuExit);
			JMenuItem exit = new JMenuItem("�˳�");
			menuExit.add(exit);
			exit.addActionListener(new ActionListener() {       //ʹ�ö�����������׽�����¼��ٷ�ִ�к������絥���¼�/������ִ�еĺ�����
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
				
			});
			
			//�ڴ������
			
			//��.�ٴ��������
			panelCenter = new JPanel();
			//��.�����������
			panelSouth = new JPanel();
			FlowLayout flowLayout = (FlowLayout)panelSouth.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPane.add(BorderLayout.CENTER, panelCenter);
			contentPane.add(BorderLayout.SOUTH, panelSouth);
			
			//��ʼ��������
			tEquation = new JTextField[PAGE_SIZE];
			tAnswer = new JTextField[PAGE_SIZE];
			for(int i = 0; i < PAGE_SIZE; i++) {
				tEquation[i] = new JTextField(5);
				tEquation[i].setHorizontalAlignment(JTextField.RIGHT);
				tEquation[i].setBackground(panelCenter.getBackground());
				tEquation[i].setBorder(null);
				tEquation[i].setEditable(false);
				tAnswer[i] = new JTextField(2);
				panelCenter.add(tEquation[i]);
				panelCenter.add(tAnswer[i]);
				tEquation[i].setVisible(false);
				tAnswer[i].setVisible(false);
			}
			preButton = new JButton("ǰһҳ");
			preButton.addActionListener(new ActionListener() {

			
				public void actionPerformed(ActionEvent arg0) {
					prePage();//�Զ��巽����ǰ��ҳ
				}
				
			});
			
			nextButton = new JButton("��һҳ");
			nextButton.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					nextPage();//�Զ��巽������ҳ
				}
				
			});
			panelCenter.add(preButton);
			//ҳ��
			pageIndex = new JLabel();
			panelCenter.add(pageIndex);
			panelCenter.add(nextButton);
			preButton.setVisible(false);
			nextButton.setVisible(false);
			
			//������
			check = new JButton("����");
			check.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					check();//�Զ������ķ���
				}
				
			});
			
			labelResult = new JLabel();
			panelSouth.add(check);
			panelSouth.add(labelResult);
			check.setVisible(false);;
			labelResult.setVisible(false);
		}
	
	
	//4��������Ա��������3���¼��ٷ�ִ�к�������������ϰ���˵���
	public void online(ActionEvent arg0) {
		if(arg0.getActionCommand() == "onlineAdd") {
			equationCount = Integer.parseInt(JOptionPane.showInputDialog("������ӷ���ʽ����"));
			exercise = new Exercise(equationCount);
			exercise.generateAddExercise();
		}
		else if(arg0.getActionCommand() == "onlineSub") {
			equationCount = Integer.parseInt(JOptionPane.showInputDialog("�����������ʽ����"));
			exercise = new Exercise(equationCount);
			exercise.generateSubExercise();
		}
		else if(arg0.getActionCommand() == "onlineMix") {
			equationCount = Integer.parseInt(JOptionPane.showInputDialog("����������ʽ����"));
			exercise = new Exercise(equationCount);
			exercise.generateExercise();
		}
		
		answer = new ExerciseAnswer();
		for(int i = 0; i < equationCount; i++) {
			answer.add(-1);
		}
		
		pages = (int)Math.ceil(equationCount * 1.0 / PAGE_SIZE);
		currentPage = 1;
		labelResult.setText("");
		update();//����ͼ�ν���ˢ�·���
	}
	
	//4��������Ա��������4���¼��ٷ�ִ�к�����������ϰ�⡱�˵���
	public void outline(ActionEvent arg0) {
	      //��ȫ�¼��ٷ�ִ������
	}
	
	//4��������Ա��������5���¼��ٷ�ִ�к�����������ϰ�⡱�˵���
	public void checkEx(){
		
		//��ȫ�¼��ٷ�ִ������

	}
	
	//4��������Ա��������6��ͼ�ν���ˢ�·���
	public void update() {
		//i,��ʽ��ϰ���е���ţ�j����ǰҳ������ʽ����ţ���ͼ��������
		for(int i = (currentPage - 1) * PAGE_SIZE, j = 0; i < currentPage * PAGE_SIZE; i++, j++) {
			if(i < equationCount) {
				tEquation[j].setText(exercise.get(i).equationStyle02());
				tEquation[j].setBackground(panelCenter.getBackground());
				tEquation[j].setVisible(true);
				if(answer.get(i) != -1) {
					tAnswer[j].setText(Integer.toString(answer.get(i)));
				}
				else {
					tAnswer[j].setText("");
				}
				tAnswer[j].setVisible(true);
			}
			else {
				tEquation[j].setVisible(false);
				tAnswer[j].setVisible(false);
			}
		}
		//���ҳ������1����ʾ��ҳ��ť
		preButton.setVisible(pages > 1);
		nextButton.setVisible(pages > 1);
		preButton.setEnabled(currentPage > 1);
		nextButton.setEnabled(currentPage < pages);
		pageIndex.setText(currentPage + " / " + pages);
		check.setVisible(true);
		labelResult.setVisible(true);
		//���İ�ťֱ�����һҳ����Ч
		if(currentPage == pages) {
			check.setEnabled(true);
		}
		else {
			check.setEnabled(false);
		}
	}
	
	//4��������Ա��������7����ҳ����һҳ��
	public void prePage() {
		for(int i = (currentPage - 1) * PAGE_SIZE, j = 0; i < currentPage * PAGE_SIZE; i++, j++) {
			if(i < equationCount && tAnswer[j].getText() != null && tAnswer[j].getText().length() > 0) {
				if(tAnswer[j].getText().matches("^[0-9]*$")) {
					answer.set(i, Integer.parseInt(tAnswer[j].getText()));
				}
			}
		}
		currentPage--;
		update();
	}
	
	//4��������Ա��������8����ҳ����һҳ��
	public void nextPage() {
		for(int i = (currentPage - 1) * PAGE_SIZE, j = 0; i < currentPage * PAGE_SIZE; i++, j++) {
			if(i < equationCount && tAnswer[j].getText() != null && tAnswer[j].getText().length() > 0) {
				if(tAnswer[j].getText().matches("^[0-9]*$")) {
					answer.set(i, Integer.parseInt(tAnswer[j].getText()));
				}
			}
		}
		currentPage++;
		update();
	}
	
	//4��������Ա��������9������
	public void check() {
		for(int i = (currentPage - 1) * PAGE_SIZE, j = 0; i < currentPage*PAGE_SIZE; i++, j++) {
			if(i < equationCount && tAnswer[j].getText() != null && tAnswer[j].getText().length() > 0) {
				if(tAnswer[j].getText().matches("^[0-9]*$")) {
					answer.set(i, Integer.parseInt(tAnswer[j].getText()));
				}
			}
		}
		CheckAnswer ch = new CheckAnswer();
		ch.checkExercise(exercise, answer);
		labelResult.setText("��ȷ��" + ch.getRightCount() + "�� ����" + ch.getWrongCount());
		drawPieChart(ch);
	}
	
	//4��������Ա��������10�����ƴ�Դ���ͼ
	public void drawPieChart(CheckAnswer check) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("��ȷ:" + check.getRightCount(), check.getRightCount());
		dataset.setValue("����:" + check.getWrongCount(), check.getWrongCount());
		JFreeChart chart = ChartFactory.createPieChart("����ͳ��", (PieDataset)dataset, false, false, false);
		PiePlot plot = (PiePlot)chart.getPlot();
		chart.setBackgroundPaint(Color.WHITE);
		plot.setForegroundAlpha(1.0f);
		plot.setCircular(true);
		Font font = new Font("����", Font.CENTER_BASELINE, 20);
		TextTitle title = chart.getTitle();
		title.setFont(font);
		chart.setTitle(title);
		ChartPanel cPanel = new ChartPanel(chart);
		JFrame f = new JFrame();
		f.setBounds(300, 300, 300, 300);
		f.add(cPanel);
		f.setVisible(true);
	}
}
