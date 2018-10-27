package home.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import config.Validate;
import form.RegisterForm;
import home.model.bean.RegisterBean;
import home.model.bo.RegisterBO;
import net.sf.json.JSONObject;

public class RegisterAction extends Action {

	private RegisterForm registerForm;

	private String memberName;
	private String birthDate;
	private String phone;
	private String email;
	private String address;
	private String identication;
	private String memberImg;

	private String userName;
	private String password;
	private String confirmPassword;
	
	private RegisterBO registerBO;
	private RegisterBean registerBean;

	private JSONObject obj;
	private PrintWriter printWriter;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		registerForm = (RegisterForm) form;
		
		if(registerForm == null || !StringUtils.equals(registerForm.getCreate(), "create")){
			return mapping.findForward("register");
		}

		memberName = registerForm.getMemberName();
		birthDate = registerForm.getBirthDate();
		phone = registerForm.getPhone();
		email = registerForm.getEmail();
		address = registerForm.getAddress();
		identication = registerForm.getIdentication();
		memberImg = registerForm.getMemberImg();

		userName = registerForm.getUserName();
		password = registerForm.getPassword();
		confirmPassword = registerForm.getConfirmPassword();
		
		registerBean = new RegisterBean();
		
		registerBean.setMemberName(memberName);
		registerBean.setBirthDate(birthDate);
		registerBean.setPhone(phone);
		registerBean.setEmail(email);
		registerBean.setAddress(address);
		registerBean.setIdentication(identication);
		registerBean.setMemberImg(memberImg);
		
		registerBean.setUserName(userName);
		registerBean.setPassword(confirmPassword);
		registerBean.setMemberImg(memberImg);
		
		if (!Validate.isExistsData(memberName) || !Validate.isExistsData(birthDate) || !Validate.isExistsData(phone)
				|| !Validate.isExistsData(email) || !Validate.isExistsData(address)
				|| !Validate.isExistsData(identication) || !Validate.isExistsData(memberImg)
				|| !Validate.isExistsData(userName) || !Validate.isExistsData(password)
				|| !Validate.isExistsData(confirmPassword)) {
			responseMessage(response,"error","Nhập đầy đủ thông tin!");
			return null;
		}
		
		if(!Validate.validateDate(birthDate)){
			responseMessage(response,"error","Ngày sinh không đúng định dạng (yyyy-mm-dd)");
			return null;
		}
			
		
		if(!Validate.isInteger(phone)){
			responseMessage(response,"error","Số điện thoại không đúng!");
			return null;
		}
		
		
		if (!Validate.isInteger(identication)) {
			responseMessage(response, "error", "số CMND không đúng");
			return null;
		}
		
		if(!StringUtils.equals(password, confirmPassword)){
			responseMessage(response, "error", "Xác nhận mật khẩu không khớp");
			return null;
		}
		
		registerBO = new RegisterBO();
		if(registerBO.registMember(registerBean)) {
			responseMessage(response, "success", "Đăng ký thành công!");
		}
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
