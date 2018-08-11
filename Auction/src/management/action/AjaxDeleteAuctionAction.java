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
import form.DetailAuctionInforForm;
import management.model.bean.DetailAuctionInforBean;
import management.model.bo.AjaxUpdateDataAuctionBO;
import management.model.bo.AuctionInforBO;
import net.sf.json.JSONObject;

public class AjaxDeleteAuctionAction extends Action {
	
	private String productID;
	
	private JSONObject obj;
	private PrintWriter printWriter;
	
	private DetailAuctionInforForm detailAuctionInforForm;
	private AjaxUpdateDataAuctionBO ajaxUpdateDataAuctionBO;
	private DetailAuctionInforBean detailAuctionInforBean;
	
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
		
		httpSession = request.getSession();
		userNameSession = (String) httpSession.getAttribute("userName");
				
		detailAuctionInforForm = (DetailAuctionInforForm) form;
		
		auctionInforBO = new AuctionInforBO();
		memberID = auctionInforBO.getMemberIdBasedUserName(userNameSession);
		
		ajaxUpdateDataAuctionBO = new AjaxUpdateDataAuctionBO();
		
		// get productID
		productID = detailAuctionInforForm.getProductID();
		
		
		if (!Validate.isExistsData(productID)) {
			
			responseMessage(response, "error", "Không tìm thấy mặt hàng!");
			
			return null;
		}
		
		detailAuctionInforBean = new DetailAuctionInforBean();
		detailAuctionInforBean.setProductID(productID);
		detailAuctionInforBean.setMemberID(memberID);
		
		if(ajaxUpdateDataAuctionBO.deleteAuction(detailAuctionInforBean)){
			
			responseMessage(response,"success","Đã xóa mặt hàng có ID = "+productID);
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
