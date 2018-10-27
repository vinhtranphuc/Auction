package home.model.bo;

import java.time.LocalDateTime;
import java.util.List;

import home.model.bean.AuctionPostBean;
import home.model.bean.OrdererBean;
import home.model.bean.UserBean;
import home.model.dao.HomeDAO;
import net.sourceforge.jtds.jdbc.DateTime;

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

	public Float getStepPriceBaseProduct(String productID) {
		return homeDAO.getStepPriceBaseProduct(productID);
	}

	public Float getHighestPrice(String productIDrequest) {
		return homeDAO.getHighestPrice(productIDrequest);
	}

	public LocalDateTime getStartTimeBaseProduct(String productIDrequest) {
		return homeDAO.getStartTimeBaseProduct(productIDrequest);
	}

	public String getMemberIDWinner(String productIDrequest) {
		return homeDAO.getMemberIDWinner(productIDrequest);
	}

	public List<AuctionPostBean> getAuctionCouponList(String productNameSearch) {
		return homeDAO.getAuctionCouponList(productNameSearch);
	}
}