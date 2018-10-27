package home.model.bo;

import home.model.bean.RegisterBean;
import home.model.dao.RegisterDAO;

public class RegisterBO {
	
	RegisterDAO registerDAO = new RegisterDAO();

	public boolean registMember(RegisterBean registerBean) {
		return registerDAO.registMember(registerBean);
	}

}
