package form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import home.model.bean.UserBean;
import management.model.bean.MemberBean;

public class ProfileForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String adminFlag;

	private String memberIDSearch;
	
	private List<UserBean> userList;
	
	private MemberBean memberInfor = new MemberBean();
	
	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	public MemberBean getMemberInfor() {
		return memberInfor;
	}

	public void setMemberInfor(MemberBean memberInfor) {
		this.memberInfor = memberInfor;
	}

	public String getMemberIDSearch() {
		return memberIDSearch;
	}

	public void setMemberIDSearch(String memberIDSearch) {
		this.memberIDSearch = memberIDSearch;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}
	
}
