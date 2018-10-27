package management.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import config.Validate;
import form.UserInforForm;

import management.model.bean.UserInforBean;
import management.model.bo.AjaxChangePasswordBO;
import management.model.bo.AuctionInforBO;

import net.sf.json.JSONObject;

public class AjaxChangePasswordAction extends Action {
	
	private String userName;
	private String password;
	private String newPassword;
	private String confirmPassword;

	private JSONObject obj;
	private PrintWriter printWriter;
	
	private UserInforForm userInforForm;
	private AjaxChangePasswordBO ajaxChangePasswordBO;
	private UserInforBean userInforBean;
	
	private AuctionInforBO auctionInforBO;
	
	// http session
	private HttpSession httpSession;
	private String userNameSession;
	private String memberID;
	

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		
		System.out.println("AjaxEditAuctionAction --------");
		httpSession = request.getSession();
		userNameSession = (String) httpSession.getAttribute("userName");
		
		auctionInforBO = new AuctionInforBO();
		memberID = auctionInforBO.getMemberIdBasedUserName(userNameSession);
		
		ajaxChangePasswordBO = new AjaxChangePasswordBO();
		
		userInforForm = (UserInforForm) form;
		
		password = userInforForm.getPassword();
		newPassword = userInforForm.getNewPassword();
		confirmPassword = userInforForm.getConfirmPassword();
		
		if(!ajaxChangePasswordBO.authPass(password,memberID)){
			
			responseMessage(response, "error", "Mật khẩu không chính xác!");
			return null;
		}
		
		if (!Validate.isExistsData(newPassword) || !Validate.isExistsData(confirmPassword)) {
			
			responseMessage(response, "error", "Điền đầy đủ mật khẩu!");
			return null;
		}
		
		if (!StringUtils.equals(newPassword, confirmPassword)) {
			
			responseMessage(response, "error", "Mật khẩu không khớp");
			return null;
		}
		
		userInforBean = new UserInforBean();
		userInforBean.setMemberID(memberID);
		userInforBean.setUserName(userName);
		userInforBean.setNewPassword(newPassword);
		userInforBean.setConfirmPassword(confirmPassword);
		
		if(ajaxChangePasswordBO.editPassword(userInforBean)){
			
			responseMessage(response,"success","Đã thay đổi mật khẩu!");
			return null;
		} 
		
		responseMessage(response,"success","Lỗi");
		
		return null;
	}

	private void responseMessage(HttpServletResponse response, String signal, String message) throws IOException {

		printWriter = response.getWriter();
		obj = new JSONObject();

		obj.put("signal", signal);
		obj.put("message", message);

		printWriter.print(obj);
		printWriter.flush();
	}

}
