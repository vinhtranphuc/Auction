package model.bo;

import java.util.List;

import model.bean.AuctionCouponBean;
import model.bean.Orderer;
import model.bean.UserBean;
import model.dao.AuctionCouponDAO;

public class AuctionCouponBO {

	AuctionCouponDAO auctionCouponDAO = new AuctionCouponDAO();
	
	public List<AuctionCouponBean> getAuctionCouponList(){
		return auctionCouponDAO.getAuctionCouponList();
	}
	
	public List<Orderer> getOrderList(String productID) {
		return auctionCouponDAO.getOrderList(productID);
	}
	
	public List<Orderer> getAllOrderList(){
		return auctionCouponDAO.getAllOrderList();
	}

	public boolean saveOrderPrice(String memberID,String productID,String orderPrice) {
		return auctionCouponDAO.saveOrderPrice(memberID,productID,orderPrice);
	}
	
	public boolean checkAuthentic(UserBean userBean){
		return auctionCouponDAO.checkAuthentic(userBean);
	}

	public String getMemberIDSession(String userNameSession) {
		return auctionCouponDAO.getMemberIDSession(userNameSession);
	}
}