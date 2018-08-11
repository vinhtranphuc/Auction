package management.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConnectMySQL;
import home.model.bean.UserBean;

public class ProfileDAO extends ConnectMySQL {

	PreparedStatement pstmt;
	ResultSet rs;

	UserBean userBean;
	List<UserBean> userList;

	public List<UserBean> getUserList() {

		String query = "SELECT MEMBER_ID, USER_NAME FROM USERS WHERE ADMIN_FLAG = 0";

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

}
