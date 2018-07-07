package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.AuctionCouponForm;
import model.bo.AuctionCouponBO;

public class AuctionCouponAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		AuctionCouponBO auctionCouponBO = new AuctionCouponBO();
		AuctionCouponForm auctionCouponForm = (AuctionCouponForm) form;

		auctionCouponForm.setAuctionCouponList(auctionCouponBO.getAuctionCouponList());

		return mapping.findForward("auctionCouponList");
	}
}
