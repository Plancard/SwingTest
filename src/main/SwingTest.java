package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SwingDTO.memberDTO;
import swingDB.DB;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SwingTest extends JFrame {

	private JPanel contentPane;
	private JTextField idInputField;
	private JTextField pwInputField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingTest frame = new SwingTest();
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
	public SwingTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2500, 200, 600, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginLabel = new JLabel("로그인");
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setBackground(Color.PINK);
		loginLabel.setOpaque(true);
		loginLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		loginLabel.setBounds(210, 70, 200, 100);
		contentPane.add(loginLabel);
		
		idInputField = new JTextField();
		idInputField.setBounds(210, 180, 120, 30);
		contentPane.add(idInputField);
		idInputField.setColumns(10);
		
		pwInputField = new JTextField();
		pwInputField.setBounds(210, 220, 120, 30);
		contentPane.add(pwInputField);
		pwInputField.setColumns(10);
		
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID = idInputField.getText();
				String PW = pwInputField.getText();
				DB db = new DB();
				
				if(ID.replaceAll(" ", "").equals("")) {
					JOptionPane.showMessageDialog(contentPane, "아이디를 입력 해 주세요", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else if(db.login(ID, PW)){
					setVisible(false);
					Box bx = new Box();
					bx.setVisible(true);
					System.out.println("로그인 성공");
				}
				else {
					System.out.println("로그인 실패");
				}
			}
		});
		loginButton.setBounds(340, 180, 70, 70);
		contentPane.add(loginButton);
		
		JButton makeIdBtn = new JButton("회원가입");
		makeIdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistFrame rf = new RegistFrame();
				rf.setVisible(true);
				setVisible(false);
			}
		});
		makeIdBtn.setBounds(210, 260, 200, 40);
		contentPane.add(makeIdBtn);
	}
}
