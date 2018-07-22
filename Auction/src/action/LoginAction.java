package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.LoginForm;
import model.bean.UserBean;
import model.bo.AuctionCouponBO;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jul 5, 2018        	Vinh          	Create
 */

public class LoginAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession httpSession = request.getSession();
		
		System.out.println("LoginAction session userName: "+httpSession.getAttribute("userName"));
		
		LoginForm loginForm = (LoginForm) form;
		AuctionCouponBO auctionCouponBO = new AuctionCouponBO();
		
		UserBean userBean = new UserBean();
		userBean.setUserName(loginForm.getUserName());
		userBean.setPassword(loginForm.getPassword());

		System.out.println(userBean.getUserName());
		System.out.println(userBean.getPassword());
		
		auctionCouponBO.checkAuthentic(userBean);
		
		if(auctionCouponBO.checkAuthentic(userBean)) {
			
			httpSession.setAttribute("userName", userBean.getUserName());
			return mapping.findForward("auth");
		}
		
		return mapping.findForward("not-auth");
	}
}