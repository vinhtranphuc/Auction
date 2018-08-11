package management.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import config.Validate;
import form.ProfileForm;
import management.model.bo.AuctionInforBO;
import management.model.bo.ProfileBO;

/**
 * ProfileAction.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- Jul
 * 5, 2018 Vinh Create
 */

public class ProfileAction extends Action {

	// http session
	private HttpSession httpSession;

	private String userNameSession;

	private String memberID;
	
	private AuctionInforBO auctionInforBO;
	private ProfileBO profileBO;
	
	private ProfileForm profileForm;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("PofileAction");
		profileForm = (ProfileForm) form;
		httpSession = request.getSession();

		userNameSession = (String) httpSession.getAttribute("userName");
		
		auctionInforBO = new AuctionInforBO();
		memberID = auctionInforBO.getMemberIdBasedUserName(userNameSession);

		if (!Validate.isExistsData(memberID)) {
			
			return mapping.findForward("login");
		}
		profileBO = new ProfileBO();
		profileForm.setUserList(profileBO.getUserList());
		
		return mapping.findForward("profile");
	}
	
}