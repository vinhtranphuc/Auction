package management.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import config.Validate;
import form.MemberInforForm;
import management.model.bean.MemberBean;
import management.model.bo.AjaxUpdateDataProfileBO;
import management.model.bo.AuctionInforBO;
import net.sf.json.JSONObject;

public class AjaxEditMemberInforAction extends Action {
	
	
	private JSONObject obj;
	private PrintWriter printWriter;
	
	private AuctionInforBO auctionInforBO;
	
	// http session
	private HttpSession httpSession;
	private String userNameSession;
	private String memberID;
	
	private String memberName;
	private String birthDate;
	private String address;
	private String identication;
	private String email;
	private String phone;
	private String image;
	
	private MemberInforForm memberInforForm;
	private MemberBean memberBean;
	
	private AjaxUpdateDataProfileBO ajaxUpdateDataProfileBO;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		
		memberInforForm = (MemberInforForm) form;
		
		System.out.println("AjaxEditAuctionAction --------");
		httpSession = request.getSession();
		userNameSession = (String) httpSession.getAttribute("userName");
				
		auctionInforBO = new AuctionInforBO();
		memberID = auctionInforBO.getMemberIdBasedUserName(userNameSession);
		
		memberName = memberInforForm.getMemberName();
		birthDate = memberInforForm.getBirthDate();
		address = memberInforForm.getAddress();
		identication = memberInforForm.getIdentication();
		email = memberInforForm.getEmail();
		phone = memberInforForm.getPhone();
		image = memberInforForm.getImage();
		
		if (!Validate.isExistsData(memberName) || !Validate.isExistsData(birthDate)
				|| !Validate.isExistsData(address) || !Validate.isExistsData(identication)
				|| !Validate.isExistsData(email) || !Validate.isExistsData(phone)|| !Validate.isExistsData(image)) {
			
			responseMessage(response, "error", "Nhập đầy đủ thông tin!");
			
			return null;
		}
		
		memberBean = new MemberBean();
		
		if(ajaxUpdateDataProfileBO.editMemberInfor(memberBean)){
			
			responseMessage(response,"success","Sửa thông tin cá nhân thành công!");
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
