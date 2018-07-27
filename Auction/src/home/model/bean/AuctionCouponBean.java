package home.model.bean;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jul 5, 2018        	Vinh          	Create
 */

public class AuctionCouponBean {
	
	// product
	private String productID;
	private String product;
	
	// auction
	private String startingPrice;
	private String highestPrice;
	private String stepPrice;
	private String startTime;
	private String endTime;
	private boolean stopAuctionFlag;
	
	// detail auction json string
	private String detailAuctString;
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
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
	public String getDetailAuctString() {
		return detailAuctString;
	}
	public void setDetailAuctString(String detailAuctString) {
		this.detailAuctString = detailAuctString;
	}
}