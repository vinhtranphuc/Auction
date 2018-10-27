package management.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectMySQL;
import management.model.bean.UserInforBean;

public class AjaxChangePassworDAO extends ConnectMySQL {
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	public boolean editPassword(UserInforBean userInforBean) {
		
		String query = "UPDATE USERS SET PASSWORD = ? WHERE MEMBER_ID = ?";
		
		try {
			
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, userInforBean.getConfirmPassword());
			pstmt.setString(2, userInforBean.getMemberID());
			
			return pstmt.executeUpdate() >= 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean authPass(String password, String memberID) {
		
		String query = "SELECT MEMBER_ID FROM USERS WHERE PASSWORD = ? AND MEMBER_ID = ?";
		
		try {
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2,memberID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
