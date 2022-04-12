package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class Box extends JFrame {

	private JPanel contentPane;
	private JTextField lotto1;
	private JTextField lotto2;
	private JTextField lotto3;
	private JTextField lotto4;
	private JTextField lotto5;
	private JTextField lotto6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Box frame = new Box();
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
	public Box() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2500, 200, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("이곳은 메인 화면입니다");
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(327, 12, 150, 70);
		contentPane.add(label);
		
		JButton lottoBtn = new JButton("로또 버튼");
		lottoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] lotto = new int[6];
				
				for(int i = 0; i < lotto.length; i++) {
					lotto[i] = (int)(Math.random() * 40) +1;
					for(int j = 0; j < i; j++) {
						if(lotto[i] == lotto[j]) {
							i--;
							break;
						}
					}
				}
				lotto1.setText(Integer.toString(lotto[0]));
				lotto2.setText(Integer.toString(lotto[1]));
				lotto3.setText(Integer.toString(lotto[2]));
				lotto4.setText(Integer.toString(lotto[3]));
				lotto5.setText(Integer.toString(lotto[4]));
				lotto6.setText(Integer.toString(lotto[5]));
			}
		});
		
		lottoBtn.setBounds(325, 230, 100, 30);
		contentPane.add(lottoBtn);
		
		lotto1 = new JTextField();
		lotto1.setHorizontalAlignment(SwingConstants.CENTER);
		lotto1.setColumns(10);
		lotto1.setBounds(200, 150, 50, 50);
		contentPane.add(lotto1);
		lotto1.setFocusable(false);
		lotto1.setEditable(false);
		lotto1.setBackground(Color.WHITE);
		
		lotto2 = new JTextField();
		lotto2.setHorizontalAlignment(SwingConstants.CENTER);
		lotto2.setColumns(10);
		lotto2.setBounds(260, 150, 50, 50);
		contentPane.add(lotto2);
		lotto2.setFocusable(false);
		lotto2.setEditable(false);
		lotto2.setBackground(Color.WHITE);
		
		lotto3 = new JTextField();
		lotto3.setHorizontalAlignment(SwingConstants.CENTER);
		lotto3.setColumns(10);
		lotto3.setBounds(320, 150, 50, 50);
		contentPane.add(lotto3);
		lotto3.setFocusable(false);
		lotto3.setEditable(false);
		lotto3.setBackground(Color.WHITE);
		
		lotto4 = new JTextField();
		lotto4.setHorizontalAlignment(SwingConstants.CENTER);
		lotto4.setColumns(10);
		lotto4.setBounds(380, 150, 50, 50);
		contentPane.add(lotto4);
		lotto4.setFocusable(false);
		lotto4.setEditable(false);
		lotto4.setBackground(Color.WHITE);
		
		lotto5 = new JTextField();
		lotto5.setHorizontalAlignment(SwingConstants.CENTER);
		lotto5.setColumns(10);
		lotto5.setBounds(440, 150, 50, 50);
		contentPane.add(lotto5);
		lotto5.setFocusable(false);
		lotto5.setEditable(false);
		lotto5.setBackground(Color.WHITE);
		
		lotto6 = new JTextField();
		lotto6.setHorizontalAlignment(SwingConstants.CENTER);
		lotto6.setColumns(10);
		lotto6.setBounds(500, 150, 50, 50);
		contentPane.add(lotto6);
		lotto6.setFocusable(false);
		lotto6.setEditable(false);
		lotto6.setBackground(Color.WHITE);
		
		JLabel one = new JLabel("버튼?");
		one.setHorizontalAlignment(SwingConstants.CENTER);
		one.setBounds(100, 300, 600, 80);
		BevelBorder border = new BevelBorder(BevelBorder.RAISED);  
		one.setBorder(border);
		contentPane.add(one);
	}
}
