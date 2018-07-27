package management.form;

import java.util.List;

import management.bean.ProductBean;

public class ProductManagementForm {
	
	private String postDateSearch;
	private String statusSearch;
	private String productNameSearch;
	
	private String productID;
	private String productName;
	private String productDescribe;
	private String productImgPath;

	private String startingPrice;
	private String endTime;
	private String startTime;
	private String stepPrice;
	private String marketPrice;
	private String status;
	
	private String winnerAuction;
	private String highestPrice;
	
	private String searchButton;

	private List<ProductBean> productList;

	public String getPostDateSearch() {
		return postDateSearch;
	}

	public void setPostDateSearch(String postDateSearch) {
		this.postDateSearch = postDateSearch;
	}

	public String getStatusSearch() {
		return statusSearch;
	}

	public void setStatusSearch(String statusSearch) {
		this.statusSearch = statusSearch;
	}

	public String getProductNameSearch() {
		return productNameSearch;
	}

	public void setProductNameSearch(String productNameSearch) {
		this.productNameSearch = productNameSearch;
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

	public String getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(String highestPrice) {
		this.highestPrice = highestPrice;
	}

	public List<ProductBean> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductBean> productList) {
		this.productList = productList;
	}

	public String getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(String searchButton) {
		this.searchButton = searchButton;
	}
	
}
