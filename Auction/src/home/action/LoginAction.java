package home.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import home.form.LoginForm;
import home.model.bean.UserBean;
import home.model.bo.HomeBO;

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
		
		LoginForm loginForm = (LoginForm) form;
		HomeBO homeBO = new HomeBO();
		
		UserBean userBean = new UserBean();
		userBean.setUserName(loginForm.getUserName());
		userBean.setPassword(loginForm.getPassword());
		
		homeBO.checkAuthentic(userBean);
		
		if(homeBO.checkAuthentic(userBean)) {
			
			httpSession.setAttribute("userName", userBean.getUserName());
			return mapping.findForward("auth");
		}
		
		return mapping.findForward("not-auth");
	}
}