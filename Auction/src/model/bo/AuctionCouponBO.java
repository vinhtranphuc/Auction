package model.bo;

import java.util.List;

import model.bean.AuctionCouponBean;
import model.dao.AuctionCouponDAO;

public class AuctionCouponBO {

	AuctionCouponDAO auctionCouponDAO = new AuctionCouponDAO();
	
	public List<AuctionCouponBean> getAuctionCouponList(){
		
		return auctionCouponDAO.getAuctionCouponList();
	}
	
	public List<AuctionCouponBean> getDspAuctionFlagList(){
		return auctionCouponDAO.getDspAuctionFlagList();
	}
	
}
