package model.bean;

public class AuctionCouponBean {

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
	private boolean stopAuctionFlag;
	
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

	public boolean isStopAuctionFlag() {
		return stopAuctionFlag;
	}

	public void setStopAuctionFlag(boolean stopAuctionFlag) {
		this.stopAuctionFlag = stopAuctionFlag;
	}
	
}
