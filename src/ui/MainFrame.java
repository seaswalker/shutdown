package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import exception.TimeExceedLimitException;
import service.Service;
import util.FrameUtil;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 6082644969856630391L;
	
	private JPanel contentPane;
	private JTextField hour;
	private JTextField min;
	private Service service = new Service();
	private JButton cancelBtn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		try {
			//windows样式					 
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/icon.png")));
		setResizable(false);
		setTitle("shutdown");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Point point = FrameUtil.getMiddlePoint(200, 300);
		setBounds(point.x, point.y, 200, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u591A\u957F\u65F6\u95F4\u540E\u5173\u673A?");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(10, 10, 128, 15);
		contentPane.add(label);
		
		hour = new JTextField();
		hour.setBounds(10, 36, 30, 21);
		contentPane.add(hour);
		hour.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5C0F\u65F6");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(50, 39, 30, 15);
		contentPane.add(label_1);
		
		min = new JTextField();
		min.setColumns(10);
		min.setBounds(84, 35, 30, 21);
		contentPane.add(min);
		
		JLabel label_2 = new JLabel("\u5206\u949F");
		label_2.setFont(new Font("宋体", Font.PLAIN, 12));
		label_2.setBounds(127, 39, 30, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("    (\u6700\u5927\u652F\u630112\u5C0F\u65F6)");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("宋体", Font.PLAIN, 12));
		label_3.setBounds(10, 67, 164, 15);
		contentPane.add(label_3);
		
		JButton setBtn = new JButton("\u8BBE\u5B9A");
		setBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		setBtn.setBounds(45, 111, 93, 23);
		setBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hours = hour.getText();
				String mins = min.getText();
				try {
					service.start(hours, mins);
					JOptionPane.showMessageDialog(contentPane, "任务已启动" , "提示:", JOptionPane.INFORMATION_MESSAGE);
					setBtn.setEnabled(false);
					cancelBtn.setEnabled(true);
				} catch (NumberFormatException | TimeExceedLimitException e1) {
					JOptionPane.showMessageDialog(contentPane, "请输入合法的值", "error:", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(contentPane, "未知错误", "error:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(setBtn);
		
		cancelBtn = new JButton("\u53D6\u6D88");
		cancelBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		cancelBtn.setBounds(45, 183, 93, 23);
		//初始时不可用
		cancelBtn.setEnabled(false);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				service.cancel();
				cancelBtn.setEnabled(false);
				setBtn.setEnabled(true);
			}
		});
		contentPane.add(cancelBtn);
	}
}
