package home.model.bo;

import java.util.List;

import home.model.bean.AuctionPostBean;
import home.model.bean.OrdererBean;
import home.model.bean.UserBean;
import home.model.dao.HomeDAO;

public class HomeBO {

	HomeDAO homeDAO = new HomeDAO();
	
	public List<AuctionPostBean> getAuctionCouponList(){
		return homeDAO.getAuctionCouponList();
	}
	
	public List<OrdererBean> getOrderList(String productID) {
		return homeDAO.getOrderList(productID);
	}
	
	public List<OrdererBean> getAllOrderList(){
		return homeDAO.getAllOrderList();
	}

	public boolean saveOrderPrice(String memberID,String productID,String orderPrice) {
		return homeDAO.saveOrderPrice(memberID,productID,orderPrice);
	}
	
	public boolean checkAuthentic(UserBean userBean){
		return homeDAO.checkAuthentic(userBean);
	}

	public String getMemberIDSession(String userNameSession) {
		return homeDAO.getMemberIDSession(userNameSession);
	}
}