package management.model.bo;

import management.model.bean.DetailAuctionInforBean;
import management.model.dao.AuctionInforDAO;

public class AuctionInforBO {
	
	AuctionInforDAO auctionInforDAO = new AuctionInforDAO();
	
	public DetailAuctionInforBean getDetailAuctionInfor(String productID, String memberID) {
		return auctionInforDAO.getDetailAuctionInfor(productID, memberID);
	}
}
