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
	
	private String adminFlag;
	
	private String memberIDSearch;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("PofileAction");
		profileForm = (ProfileForm) form;
		httpSession = request.getSession();

		userNameSession = (String) httpSession.getAttribute("userName");
		
		auctionInforBO = new AuctionInforBO();
		
		profileBO = new ProfileBO();
		
		if (!Validate.isExistsData(userNameSession)) {
			
			return mapping.findForward("login");
		}
		
		memberID = auctionInforBO.getMemberIdBasedUserName(userNameSession);
		
		adminFlag = profileBO.getAdminFlag(memberID);
		
		if(Validate.isExistsData(adminFlag)){
			
			profileForm.setUserList(profileBO.getUserList());
			System.out.println(adminFlag);
			
		}
		
		memberIDSearch = profileForm.getMemberIDSearch();
		
		if(Validate.isExistsData(memberIDSearch) && Validate.isExistsData(adminFlag) ){
			
			profileForm.setMemberInfor(profileBO.getMemberInfor(memberIDSearch));
			profileForm.setAdminFlag(adminFlag);
			
			return mapping.findForward("profile");
		}
		
		profileForm.setAdminFlag(adminFlag);
		profileForm.setMemberInfor(profileBO.getMemberInfor(memberID));
		
		return mapping.findForward("profile");
	}
	
}