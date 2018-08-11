package management.model.bo;

import management.model.bean.MemberBean;
import management.model.dao.AjaxUpdateDataProfileDAO;

public class AjaxUpdateDataProfileBO {
	
	AjaxUpdateDataProfileDAO ajaxUpdateDataProfileDAO;

	public boolean editMemberInfor(MemberBean memberBean) {
		return ajaxUpdateDataProfileDAO.editMemberInfor(memberBean);
	}

}
