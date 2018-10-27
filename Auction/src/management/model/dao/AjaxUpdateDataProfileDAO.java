package management.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import config.ConnectMySQL;
import management.model.bean.MemberBean;

public class AjaxUpdateDataProfileDAO extends ConnectMySQL{
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	public boolean editMemberInfor(MemberBean memberBean) {
		
		String query = "UPDATE AUCTIONEER SET MEMBER_NAME = ?, ADDRESS = ?, BIRTH_DATE = ?, IDENTICATION_CARD = ?, PHONE = ?, EMAIL = ?, IMAGE = ? WHERE MEMBER_ID = ?";
		
		try {
			
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, memberBean.getMemberName());
			pstmt.setString(2, memberBean.getAddress());
			pstmt.setString(3, memberBean.getBirthDate());
			pstmt.setString(4, memberBean.getIdentication());
			pstmt.setString(5, memberBean.getPhone());
			pstmt.setString(6, memberBean.getEmail());
			pstmt.setString(7, memberBean.getImage());

			pstmt.setString(8, memberBean.getMemberID());
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
