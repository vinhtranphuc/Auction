package management.model.bo;

import management.model.bean.MemberBean;
import management.model.dao.AjaxUpdateDataProfileDAO;

public class AjaxUpdateDataProfileBO {
	
	AjaxUpdateDataProfileDAO ajaxUpdateDataProfileDAO;

	public boolean editMemberInfor(MemberBean memberBean) {
		ajaxUpdateDataProfileDAO = new AjaxUpdateDataProfileDAO();
		return ajaxUpdateDataProfileDAO.editMemberInfor(memberBean);
	}

}
