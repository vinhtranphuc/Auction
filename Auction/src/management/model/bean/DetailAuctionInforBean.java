package management.model.bean;

public class DetailAuctionInforBean {
	
	private String memberID;
	
	private String productID;
	private String productName;
	private String productDescribe;
	private String productImgPath;
	
	private String categoryName;
	private String categoryID;

	private String startingPrice;
	private String endTime;
	private String startTime;
	private String stepPrice;
	private String marketPrice;
	private String status;
	
	private String winnerAuction;
	private String memberIDWinner;
	private String highestPrice;
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescribe() {
		return productDescribe;
	}
	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}
	public String getProductImgPath() {
		return productImgPath;
	}
	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(String startingPrice) {
		this.startingPrice = startingPrice;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStepPrice() {
		return stepPrice;
	}
	public void setStepPrice(String stepPrice) {
		this.stepPrice = stepPrice;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWinnerAuction() {
		return winnerAuction;
	}
	public void setWinnerAuction(String winnerAuction) {
		this.winnerAuction = winnerAuction;
	}
	public String getMemberIDWinner() {
		return memberIDWinner;
	}
	public void setMemberIDWinner(String memberIDWinner) {
		this.memberIDWinner = memberIDWinner;
	}
	public String getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(String highestPrice) {
		this.highestPrice = highestPrice;
	}
	
}
