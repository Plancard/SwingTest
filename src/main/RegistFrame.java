package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import swingDB.DB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegistFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextField IDtext;
	private JPasswordField PWtext;
	private JPasswordField rePWtext;
	
	public RegistFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2500, 200, 600, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("돌아가기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SwingTest st = new SwingTest();
				st.setVisible(true);
			}
		});
		button.setBounds(250, 350, 125, 30);
		contentPane.add(button);
		
		JLabel title = new JLabel("회원가입");
		title.setFont(new Font("Dialog", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(220, 20, 160, 70);
		contentPane.add(title);
		
		JLabel IDtitle = new JLabel("아이디");
		IDtitle.setHorizontalAlignment(SwingConstants.CENTER);
		IDtitle.setFont(new Font("Dialog", Font.BOLD, 20));
		IDtitle.setBounds(250, 85, 100, 38);
		contentPane.add(IDtitle);
		
		IDtext = new JTextField();
		IDtext.setBounds(250, 124, 125, 22);
		contentPane.add(IDtext);
		IDtext.setColumns(10);
		
		JLabel PWtitle = new JLabel("비밀번호");
		PWtitle.setHorizontalAlignment(SwingConstants.CENTER);
		PWtitle.setFont(new Font("Dialog", Font.BOLD, 20));
		PWtitle.setBounds(250, 150, 100, 38);
		contentPane.add(PWtitle);
		
		PWtext = new JPasswordField();
		PWtext.setBounds(250, 191, 125, 22);
		contentPane.add(PWtext);
		
		JLabel rePWtitle = new JLabel("비밀번호 확인");
		rePWtitle.setHorizontalAlignment(SwingConstants.CENTER);
		rePWtitle.setFont(new Font("Dialog", Font.BOLD, 20));
		rePWtitle.setBounds(230, 220, 150, 38);
		contentPane.add(rePWtitle);
		
		rePWtext = new JPasswordField();
		rePWtext.setBounds(250, 254, 125, 22);
		contentPane.add(rePWtext);
		
		JButton registBtn = new JButton("회원가입");
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DB db = new DB();
				
				String id = IDtext.getText();
				String trueID = id.trim().replaceAll(" ", "");
				String finalTrueID = trueID.replaceAll("[^a-zA-Z0-9]", "");
				
				String pw = PWtext.getText();
				String rePw = rePWtext.getText();
				String truePW = pw.trim().replaceAll(" ", "");
				String finalTruePW = truePW.replaceAll("[^a-zA-Z0-9]", "");
				
				int idCheck = 0;
				int pwCheck = 0;
				
				if(id.replaceAll(" ", "").equals("")) {
					JOptionPane.showMessageDialog(contentPane, "아이디에 공백이 들어갈 수 없습니다!", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else if(0 == id.length() || 15 < id.length() || id.length() < 4) {
					JOptionPane.showMessageDialog(contentPane, "아이디는 4~14자 사이입니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else if(db.idDuplicate(IDtext.getText())) {
					JOptionPane.showMessageDialog(contentPane, "중복되는 아이디는 사용할 수 없습니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else if(finalTrueID.length() > 0){
					idCheck = 1;
					System.out.println("아이디 일치");
				}
				else {
					System.out.println("아이디 불일치");
				}
				
				if(pw == null || pw.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "비밀번호를 입력하셔야 합니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else if(0 == pw.length() || 15 < pw.length() || pw.length() < 4) {
					JOptionPane.showMessageDialog(contentPane, "비밀번호는 4~14자 사이입니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else if(finalTruePW.equals(rePw)) {
					pwCheck = 1;
					System.out.println("비밀번호 일치");
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "비밀번호와 비밀번호 확인값이 일치하지 않습니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
				
				if(idCheck == 1 && pwCheck == 1) {
				db.dbInsert(finalTrueID, finalTruePW);
				setVisible(false);
				SwingTest main = new SwingTest();
				main.setVisible(true);
				System.out.println("가입 완료");
				}
			}
		});
		registBtn.setBounds(250, 310, 125, 28);
		contentPane.add(registBtn);
		
		JButton checkID = new JButton("중복 확인");
		checkID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DB db = new DB();
				String id = IDtext.getText();
				
				if(db.idDuplicate(IDtext.getText())) {
					JOptionPane.showMessageDialog(contentPane, "중복되는 아이디가 존재합니다", "경고", JOptionPane.WARNING_MESSAGE);
					IDtext.setText("");
				}
				else if(id.replaceAll(" ", "").equals("")) {
					JOptionPane.showMessageDialog(contentPane, "아이디에 공백이 들어갈 수 없습니다!", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "사용 가능한 아이디입니다", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		checkID.setBounds(402, 118, 105, 28);
		contentPane.add(checkID);
		setVisible(false);
	}
}
