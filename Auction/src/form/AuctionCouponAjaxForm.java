package form;

import org.apache.struts.action.ActionForm;


public class AuctionCouponAjaxForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String checkStopAuction;
	private String productID;
	private String stopAuctionFlag;
	
/*	private List<AuctionCouponBean> dspAuctionFlagList = new ArrayList<AuctionCouponBean>();*/

	public String getCheckStopAuction() {
		return checkStopAuction;
	}

	public void setCheckStopAuction(String checkStopAuction) {
		this.checkStopAuction = checkStopAuction;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getStopAuctionFlag() {
		return stopAuctionFlag;
	}

	public void setStopAuctionFlag(String stopAuctionFlag) {
		this.stopAuctionFlag = stopAuctionFlag;
	}

}
