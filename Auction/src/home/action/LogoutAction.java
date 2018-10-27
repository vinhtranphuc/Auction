package home.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * LogoutAction.java
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

public class LogoutAction extends Action {

	private String userNameSession;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession httpSession = request.getSession();

		userNameSession = (String) httpSession.getAttribute("userName");

		if (userNameSession != null) {
			httpSession.invalidate();
		}

		return mapping.findForward("home");
	}
}