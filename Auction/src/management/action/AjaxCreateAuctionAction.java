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
import socket.ServerEndPoint;

public class AjaxCreateAuctionAction extends Action {

	private String productID;
	private String productName;
	private String productDescrible;
	private String productImgPath;
	private String categoryID;
	private String startingPrice;
	private String endTime;
	private String startTime;
	private String stepPrice;
	private String marketPrice;

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

		// get max productID
		productID = ajaxUpdateDataAuctionBO.getMaxProductID();

		productName = detailAuctionInforForm.getProductName();
		productDescrible = detailAuctionInforForm.getProductDescribe();
		productImgPath = "product"+productID;
		categoryID = detailAuctionInforForm.getCategoryID();
		startingPrice = detailAuctionInforForm.getStartingPrice();
		endTime = detailAuctionInforForm.getEndTime();
		startTime = detailAuctionInforForm.getStartTime();
		stepPrice = detailAuctionInforForm.getStepPrice();
		marketPrice = detailAuctionInforForm.getMarketPrice();

		if (!Validate.isExistsData(productName) || !Validate.isExistsData(productDescrible)
				|| !Validate.isExistsData(productImgPath) || !Validate.isExistsData(categoryID)
				|| !Validate.isExistsData(startingPrice) || !Validate.isExistsData(endTime)
				|| !Validate.isExistsData(startTime) || !Validate.isExistsData(stepPrice)
				|| !Validate.isExistsData(marketPrice)) {

			responseMessage(response, "error", "Nhập dữ liệu đầy đủ!");

			return null;
		}

/*		if (!Validate.validateDateTime(startTime)) {
			responseMessage(response, "error", "Thời gian bắt đầu không đúng định dạng (yyyy-mm-dd hh:mm:ss)");
			return null;
		}

		if (!Validate.validateDateTime(endTime)) {
			responseMessage(response, "error", "Thời gian kết thúc không đúng định dạng (yyyy-mm-dd hh:mm:ss)");
			return null;
		}*/

		detailAuctionInforBean = new DetailAuctionInforBean();
		detailAuctionInforBean.setProductID(productID);
		detailAuctionInforBean.setMemberID(memberID);
		detailAuctionInforBean.setProductName(productName);
		detailAuctionInforBean.setProductDescribe(productDescrible);
		detailAuctionInforBean.setProductImgPath(productImgPath);
		detailAuctionInforBean.setCategoryID(categoryID);
		detailAuctionInforBean.setStartingPrice(startingPrice);
		detailAuctionInforBean.setStepPrice(stepPrice);
		detailAuctionInforBean.setEndTime(endTime);
		detailAuctionInforBean.setStartTime(startTime);
		detailAuctionInforBean.setMarketPrice(marketPrice);

		if (ajaxUpdateDataAuctionBO.addNewAuction(detailAuctionInforBean)) {

			responseMessage(response, "success", "Thêm sản phẩm thành công!");
			return null;
		}

		responseMessage(response, "success", "Lỗi");
		ServerEndPoint s = new ServerEndPoint();
		s.sendAuctionListAllClient();
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
