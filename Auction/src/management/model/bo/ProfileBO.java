package management.model.bo;

import java.util.List;

import home.model.bean.UserBean;
import management.model.dao.ProfileDAO;

public class ProfileBO {

	private ProfileDAO profileDAO;

	public List<UserBean> getUserList() {
		profileDAO = new ProfileDAO();
		return profileDAO.getUserList();
	}

}
