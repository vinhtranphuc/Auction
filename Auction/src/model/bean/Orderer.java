package model.bean;

public class Orderer {
	
	private String orderID;
	private String memberID;
	private String memberName;
	private String productID;
	private String orderPrice;
	private boolean stopOrderFlag;
	
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public boolean isStopOrderFlag() {
		return stopOrderFlag;
	}
	public void setStopOrderFlag(boolean stopOrderFlag) {
		this.stopOrderFlag = stopOrderFlag;
	}

}
