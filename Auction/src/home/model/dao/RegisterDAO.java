package home.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectMySQL;
import home.model.bean.RegisterBean;

public class RegisterDAO extends ConnectMySQL {
	
	PreparedStatement pstmt;
	ResultSet rs;
	

	public boolean registMember(RegisterBean registerBean) {
		
		if(addMemberInfor(registerBean)){
			return addUser(registerBean);
		}
		
		return false;
	}

	private boolean addMemberInfor(RegisterBean registerBean) {
		String query = "INSERT INTO AUCTIONEER (MEMBER_NAME,ADDRESS,BIRTH_DATE,IDENTICATION_CARD,PHONE,EMAIL,IMAGE) VALUES (?,?,?,?,?,?,?)";
		String maxMemberID1 = getMaxMemberID();
		try {
			pstmt = getPrepareStatement(query);

			pstmt.setString(1, registerBean.getMemberName());
			pstmt.setString(2, registerBean.getAddress());
			pstmt.setString(3, registerBean.getBirthDate());
			pstmt.setString(4, registerBean.getIdentication());
			pstmt.setString(5, registerBean.getPhone());
			pstmt.setString(6, registerBean.getEmail());
			pstmt.setString(7, (Integer.parseInt(maxMemberID1)+1)+"");
			
			return pstmt.executeUpdate() >= 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	private String getMaxMemberID(){
		String query = "SELECT MAX(MEMBER_ID) AS MEMBER_ID FROM AUCTIONEER";
		try {
			pstmt = getPrepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				return rs.getString("MEMBER_ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private boolean addUser(RegisterBean registerBean) {
		
		String maxMemberID = getMaxMemberID();
		
		String query = "INSERT INTO USERS (MEMBER_ID,USER_NAME,PASSWORD,ADMIN_FLAG) VALUES (?,?,?,?)";
		try {
			pstmt = getPrepareStatement(query);

			pstmt.setString(1, maxMemberID);
			pstmt.setString(2, registerBean.getUserName());
			pstmt.setString(3, registerBean.getPassword());
			pstmt.setString(4, "0");
			
			return pstmt.executeUpdate() >= 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false; 
	}
	
	public static void main(String[] args) {
		RegisterBean r = new RegisterBean();
		
		r.setBirthDate("1993-12-2");
		r.setMemberName("sss");
		r.setEmail("1");
		r.setIdentication("12313");
		r.setPhone("12312313");
		r.setAddress("ssss");
		r.setMemberImg("sss.jpg");
		
		
		r.setUserName("saaaa");
		r.setPassword("ssss");
		
		RegisterDAO rd = new RegisterDAO();
		rd.registMember(r);
		
	}

}
