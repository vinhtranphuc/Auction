package form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import management.model.bean.DetailAuctionInforBean;
import management.model.bean.ProductBean;

public class AuctionInforForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String postDateSearch;
	private String statusSearch;
	private String productNameSearch;
	
	
	private DetailAuctionInforBean detailAuctionInforBean;
	
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

	public DetailAuctionInforBean getDetailAuctionInforBean() {
		
		detailAuctionInforBean = new DetailAuctionInforBean();
		return detailAuctionInforBean;
	}

	public void setDetailAuctionInforBean(DetailAuctionInforBean detailAuctionInforBean) {
		detailAuctionInforBean = new DetailAuctionInforBean();
		this.detailAuctionInforBean = detailAuctionInforBean;
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
