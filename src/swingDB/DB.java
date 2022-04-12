package swingDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import SwingDTO.memberDTO;

public class DB {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/swingDB?useSSL=false";
		String id = "root";
		String pw = "rootroot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("디비 연결됨");
	}
	
	public void DBClose() {
		if(conn != null || pstmt != null) {
			try {
				conn.close();
				pstmt.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
	
	public void dbInsert(String mID, String mPW){
		
		memberDTO dto = new memberDTO();
		dto.setmID(mID);
		dto.setmPW(mPW);
		
		try {
			getConnection();
			String sql = "insert into memberTBL(mID, mPW) values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmID());
			pstmt.setString(2, dto.getmPW());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("dbInsert() 작동 실패");
		} finally {
			DBClose();
		}
	}
	
	public void dbSelect(){
		
		try {
			getConnection();
			String sql = "SELECT * FROM memberTBL";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {				
			int num = rs.getInt("mNum");
			String mID = rs.getString("mID");
			String mPW = rs.getString("mPW");
			System.out.println(num + " / " + mID + " / " + mPW);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("dbSelect() 작동 실패");
		} finally {
			DBClose();
		}
	}
	
	public void dbView(int mNum){
		
		memberDTO dto = new memberDTO();
		dto.setmNum(mNum);
		
		try {
			getConnection();
			String sql = "SELECT * FROM memberTBL WHERE mNum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getmNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int num = rs.getInt("mNum");
				String mID = rs.getString("mID");
				String mPW = rs.getString("mPW");
				System.out.println(num + " / " + mID + " / " + mPW);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("dbView() 작동 실패");
		} finally {
			DBClose();
		}
	}
	
	public String dbIDView(int mNum){
		
		memberDTO dto = new memberDTO();
		dto.setmNum(mNum);
		
		try {
			getConnection();
			String sql = "SELECT mID From memberTBL WHERE mNum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getmNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String mID = rs.getString("mID");
				return mID;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("dbView() 작동 실패");
		} finally {
			DBClose();
		}
		return null;
	}
	
	public boolean idDuplicate(String id) {
		
		memberDTO dto = new memberDTO();
		dto.setmID(id);
		
		try {
			getConnection();
			String sql = "SELECT * FROM memberTBL WHERE mID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmID());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("중복되는 아이디가 존재합니다");
				return true;
			}else {
				System.out.println("중복되는 아이디가 없습니다");
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("중복체크 작동 실패");
		} finally {
			DBClose();
		}
		return false;
	}
	
	public boolean login(String id, String pw) {
		
		memberDTO dto = new memberDTO();
		dto.setmID(id);
		dto.setmPW(pw);
		
		try {
			getConnection();
			String sql = "SELECT * FROM memberTBL WHERE mID = ? AND mPW = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmID());
			pstmt.setString(2, dto.getmPW());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("로그인 성공");
				return true;
			}else {
				System.out.println("로그인 실패");
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("로그인 작동 실패");
		} finally {
			DBClose();
		}
		return false;
	}
	
}
