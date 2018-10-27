package management.model.bo;

import management.model.bean.UserInforBean;
import management.model.dao.AjaxChangePassworDAO;

public class AjaxChangePasswordBO {
	
	AjaxChangePassworDAO ajaxChangePassworDAO;
	public boolean editPassword(UserInforBean userInforBean) {
		ajaxChangePassworDAO = new AjaxChangePassworDAO();
		return ajaxChangePassworDAO.editPassword(userInforBean);
	}
	public boolean authPass(String password, String memberID) {
		ajaxChangePassworDAO = new AjaxChangePassworDAO();
		return ajaxChangePassworDAO.authPass(password,memberID);
	}

}
