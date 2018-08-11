package form;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import management.model.bean.DetailAuctionInforBean;
import management.model.bean.ProductBean;

public class AuctionInforForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productID;
	private String postDateSearch;
	private String statusSearch;
	private String productNameSearch;

	private DetailAuctionInforBean detailAuctionInforBean;
	
	private String searchButton;

	private List<ProductBean> productList;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
		return detailAuctionInforBean;
	}

	public void setDetailAuctionInforBean(DetailAuctionInforBean detailAuctionInforBean) {
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
