package management.model.bo;

import java.util.List;

import management.model.bean.DetailAuctionInforBean;
import management.model.bean.ProductBean;
import management.model.dao.AuctionInforDAO;

public class AuctionInforBO {
	
	AuctionInforDAO auctionInforDAO = new AuctionInforDAO();
	
	public DetailAuctionInforBean getDetailAuctionInfor(String productID, String memberID) {
		return auctionInforDAO.getDetailAuctionInfor(productID, memberID);
	}

	public String getMemberIdBasedUserName(String userName) {
		return auctionInforDAO.getMemberIdBasedUserName(userName);
	}

	public boolean isExistProductID(String productID) {
		return auctionInforDAO.isExistProductID(productID);
	}

	public List<ProductBean> getProductsBasedOnUser(String memberID) {
		return auctionInforDAO.getProductsBasedOnUser(memberID);
	}

	public List<ProductBean> getProductsSearch(String productNameSearch, String postDateSearch, String statusSearch,String memberID) {
		return auctionInforDAO.getProductsSearch(productNameSearch,postDateSearch,statusSearch,memberID);
	}
}
