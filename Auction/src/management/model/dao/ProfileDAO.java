package management.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import config.ConnectMySQL;
import home.model.bean.UserBean;
import management.model.bean.MemberBean;

public class ProfileDAO extends ConnectMySQL {

	PreparedStatement pstmt;
	ResultSet rs;

	UserBean userBean;
	MemberBean memberBean;
	List<UserBean> userList;

	public List<UserBean> getUserList() {

		String query = "SELECT MEMBER_ID, USER_NAME FROM USERS ORDER BY ADMIN_FLAG DESC";

		userList = new ArrayList<UserBean>();
		try {

			pstmt = getPrepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				userBean = new UserBean();
				userBean.setMemberID(rs.getString("MEMBER_ID"));
				userBean.setUserName(rs.getString("USER_NAME"));

				userList.add(userBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}

	public MemberBean getMemberInfo(String memberID) {
		
		String query = "SELECT AUCTIONEER.MEMBER_ID, MEMBER_NAME, ADDRESS, BIRTH_DATE, IDENTICATION_CARD, PHONE, EMAIL, IMAGE, USER_NAME, PASSWORD FROM AUCTIONEER JOIN USERS ON AUCTIONEER.MEMBER_ID = USERS.MEMBER_ID WHERE AUCTIONEER.MEMBER_ID = ?";
		try {
			pstmt = getPrepareStatement(query);
			pstmt.setString(1,memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				memberBean = new MemberBean();
				memberBean.setMemberID(rs.getString("MEMBER_ID"));
				memberBean.setMemberName(rs.getString("MEMBER_NAME"));
				memberBean.setAddress(rs.getString("ADDRESS"));
				memberBean.setBirthDate(rs.getString("BIRTH_DATE"));
				memberBean.setIdentication(rs.getString("IDENTICATION_CARD"));
				memberBean.setPhone(rs.getString("PHONE"));
				memberBean.setEmail(rs.getString("EMAIL"));
				memberBean.setImage("member"+rs.getString("MEMBER_ID")+".jpg");
				
				memberBean.setUserName(rs.getString("USER_NAME"));
				memberBean.setPassword(rs.getString("PASSWORD"));
				
				return memberBean;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}
	
	public static void main(String[] args) {
		
		ProfileDAO p = new ProfileDAO();
		
		System.out.println(p.getAdminFlag("2"));
	}

	public String getAdminFlag(String memberID) {
		
		String query = "SELECT ADMIN_FLAG FROM USERS WHERE MEMBER_ID = ?";
		
		try {
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				if(StringUtils.equals("1", rs.getString("ADMIN_FLAG"))){
					return "admin";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
