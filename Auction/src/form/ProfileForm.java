package form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import home.model.bean.UserBean;

public class ProfileForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String memberID;
	
	private List<UserBean> userList;

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}
	
}
