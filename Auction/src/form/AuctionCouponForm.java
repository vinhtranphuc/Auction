package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import model.bean.AuctionCouponBean;

public class AuctionCouponForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String memberIDSession;
	private String product;
	private String productID;
	private String startingPrice;
	private String highestPrice;
	private String name;
	private String memberID;
	private String stepPrice;
	private String startTime;
	private String endTime;
	private String stopAuctionFlag;

	private List<AuctionCouponBean> auctionCouponList = new ArrayList<AuctionCouponBean>();

	public String getMemberIDSession() {
		return memberIDSession;
	}

	public void setMemberIDSession(String memberIDSession) {
		this.memberIDSession = memberIDSession;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}

	public String getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(String highestPrice) {
		this.highestPrice = highestPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getStepPrice() {
		return stepPrice;
	}

	public void setStepPrice(String stepPrice) {
		this.stepPrice = stepPrice;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStopAuctionFlag() {
		return stopAuctionFlag;
	}

	public void setStopAuctionFlag(String stopAuctionFlag) {
		this.stopAuctionFlag = stopAuctionFlag;
	}

	public List<AuctionCouponBean> getAuctionCouponList() {
		return auctionCouponList;
	}

	public void setAuctionCouponList(List<AuctionCouponBean> auctionCouponList) {
		this.auctionCouponList = auctionCouponList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
