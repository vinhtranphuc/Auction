package management.model.bo;

import java.util.List;

import home.model.bean.UserBean;
import management.model.bean.MemberBean;
import management.model.dao.ProfileDAO;

public class ProfileBO {

	private ProfileDAO profileDAO;

	public List<UserBean> getUserList() {
		profileDAO = new ProfileDAO();
		return profileDAO.getUserList();
	}

	public MemberBean getMemberInfor(String memberID) {
		return profileDAO.getMemberInfo(memberID);
	}

	public String getAdminFlag(String memberID) {
		profileDAO = new ProfileDAO();
		return profileDAO.getAdminFlag(memberID);
	}

}
