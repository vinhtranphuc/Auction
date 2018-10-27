package home.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import common.StringProcess;
import config.ConnectMySQL;
import home.model.bean.AuctionPostBean;
import home.model.bean.DetailAuctionPostBean;
import home.model.bean.OrdererBean;
import home.model.bean.UserBean;
import net.sourceforge.jtds.jdbc.DateTime;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- Jul
 * 5, 2018 Vinh Create
 */

public class HomeDAO extends ConnectMySQL {

	ResultSet rs;
	PreparedStatement pstmt;
	Statement stmt;
	AuctionPostBean auctionPostBean;
	DetailAuctionPostBean detailAuctionPostBean;
	OrdererBean ordererBean;

	public List<AuctionPostBean> getAuctionCouponList() {

		List<AuctionPostBean> autionCouponList = new ArrayList<AuctionPostBean>();

		String query = "SELECT PRODUCT, AUCTION.PRODUCT_ID, PRODUCTS.IMAGE_PATH AS PRODUCT_IMG_PATH, PRODUCTS.DESCRIBE AS PRODUCT_DESCRIBE, MEMBER_NAME, AUCTION.MEMBER_ID, AUCTIONEER.PHONE AS MEMBER_POST_PHONE, AUCTIONEER.EMAIL AS MEMBER_POST_EMAIL, STARTING_PRICE, ( CASE WHEN( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID ) IS NULL THEN 0 ELSE( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID ) END ) AS HIGHEST_PRICE, STEP_PRICE, START_TIME, END_TIME, STOP_AUCTION_FLAG FROM AUCTION, PRODUCTS, AUCTIONEER WHERE AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND AUCTION.MEMBER_ID = AUCTIONEER.MEMBER_ID AND NOW() <= END_TIME AND STOP_AUCTION_FLAG = 0 GROUP BY AUCTION.PRODUCT_ID ORDER BY AUCTION.START_TIME";
		try {

			rs = getResultSet(query);

			while (rs.next()) {

				auctionPostBean = new AuctionPostBean();

				auctionPostBean.setProduct(rs.getString("PRODUCT"));
				auctionPostBean.setProductID(rs.getString("PRODUCT_ID"));

				auctionPostBean.setStartingPrice(rs.getString("STARTING_PRICE"));
				auctionPostBean.setHighestPrice(rs.getString("HIGHEST_PRICE"));
				auctionPostBean.setStepPrice(rs.getString("STEP_PRICE"));
				auctionPostBean.setStartTime(rs.getString("START_TIME"));
				auctionPostBean.setEndTime(rs.getString("END_TIME"));

				auctionPostBean.setStopAuctionFlag(rs.getString("STOP_AUCTION_FLAG") == "1" ? true : false);

				detailAuctionPostBean = new DetailAuctionPostBean();

				detailAuctionPostBean.setProduct(rs.getString("PRODUCT"));
				detailAuctionPostBean.setProductID(rs.getString("PRODUCT_ID"));
				detailAuctionPostBean.setProductImgPath(rs.getString("PRODUCT_IMG_PATH"));
				detailAuctionPostBean.setProductDescribe(rs.getString("PRODUCT_DESCRIBE"));

				detailAuctionPostBean.setMemberName(rs.getString("MEMBER_NAME"));
				detailAuctionPostBean.setMemberID(rs.getString("MEMBER_ID"));
				detailAuctionPostBean.setPhone(rs.getString("MEMBER_POST_PHONE"));
				detailAuctionPostBean.setEmail(rs.getString("MEMBER_POST_EMAIL"));

				auctionPostBean.setDetailAuctString(StringProcess.toJSONArrayString(detailAuctionPostBean));

				autionCouponList.add(auctionPostBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return autionCouponList;
	}

	public List<OrdererBean> getOrderList(String productID) {

		List<OrdererBean> orderList = new ArrayList<OrdererBean>();

		String query = "SELECT ORDER_ID,ORDERER.MEMBER_ID, AUCTIONEER.MEMBER_NAME,ORDERER.PRODUCT_ID,ORDER_PRICE,STOP_FLAG FROM ORDERER JOIN AUCTIONEER ON ORDERER.MEMBER_ID = AUCTIONEER.MEMBER_ID JOIN AUCTION ON ORDERER.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE ORDERER.PRODUCT_ID = ? AND AUCTION.STOP_AUCTION_FLAG = 0 ORDER BY ORDER_ID ASC";
		try {

			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, productID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ordererBean = new OrdererBean();

				ordererBean.setOrderID(rs.getString("ORDER_ID"));
				ordererBean.setMemberID(rs.getString("MEMBER_ID"));
				ordererBean.setMemberName(rs.getString("MEMBER_NAME"));
				ordererBean.setProductID(rs.getString("PRODUCT_ID"));
				ordererBean.setOrderPrice(rs.getString("ORDER_PRICE"));
				ordererBean.setStopOrderFlag(rs.getString("STOP_FLAG") == "1" ? true : false);

				orderList.add(ordererBean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return orderList;
	}

	public List<OrdererBean> getAllOrderList() {

		List<OrdererBean> orderList = new ArrayList<OrdererBean>();

		String query = "SELECT ORDER_ID,ORDERER.MEMBER_ID,ORDERER.PRODUCT_ID,ORDER_PRICE,STOP_FLAG FROM ORDERER JOIN AUCTION ON ORDERER.MEMBER_ID = AUCTION.MEMBER_ID AND ORDERER.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE AUCTION.STOP_AUCTION_FLAG = 0";
		try {
			rs = getResultSet(query);

			while (rs.next()) {

				ordererBean = new OrdererBean();

				ordererBean.setOrderID(rs.getString("ORDER_ID"));
				ordererBean.setMemberID(rs.getString("MEMBER_ID"));
				ordererBean.setProductID(rs.getString("PRODUCT_ID"));
				ordererBean.setOrderPrice(rs.getString("ORDER_PRICE"));
				ordererBean.setStopOrderFlag(rs.getString("STOP_FLAG") == "1" ? true : false);

				orderList.add(ordererBean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return orderList;
	}

	public boolean saveOrderPrice(String memberID, String productID, String orderPrice) {

		String query = "INSERT INTO ORDERER(MEMBER_ID,PRODUCT_ID,ORDER_PRICE,STOP_FLAG) VALUES(?,?,?,0)";
		try {
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, memberID);
			pstmt.setString(2, productID);
			pstmt.setString(3, orderPrice);

			int count = pstmt.executeUpdate();

			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean checkAuthentic(UserBean userBean) {

		String query = "SELECT MEMBER_ID FROM USERS WHERE USER_NAME = ? AND PASSWORD = ?";

		int count = 0;
		try {
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, userBean.getUserName());
			pstmt.setString(2, userBean.getPassword());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				count++;
			}

			if (count >= 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public String getMemberIDSession(String userNameSession) {

		String query = "SELECT MEMBER_ID FROM USERS WHERE USER_NAME = ?";

		try {
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, userNameSession);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getString("MEMBER_ID");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {

		HomeDAO acd = new HomeDAO();
		System.out.println(acd.getHighestPrice("5"));

	}

	public Float getStepPriceBaseProduct(String productID) {

		String query = "SELECT STEP_PRICE FROM AUCTION WHERE PRODUCT_ID = ?";
		try {
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, productID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return Float.parseFloat(rs.getString("STEP_PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (float) 0;
	}

	public Float getHighestPrice(String productIDrequest) {

		String query = "SELECT DISTINCT (CASE WHEN( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID ) IS NULL THEN Starting_price ELSE( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID ) END ) AS HIGHEST_PRICE FROM AUCTION, PRODUCTS, AUCTIONEER WHERE AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND AUCTION.PRODUCT_ID = ?";

		try {
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, productIDrequest);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				return Float.parseFloat(rs.getString("HIGHEST_PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (float) 0;
	}

	public LocalDateTime getStartTimeBaseProduct(String productIDrequest) {

				String query = "SELECT START_TIME FROM AUCTION WHERE PRODUCT_ID = ?";
				try {
					pstmt = getPrepareStatement(query);
					pstmt.setString(1, productIDrequest);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						return LocalDateTime.parse(rs.getString("START_TIME"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return null;
	}

	public String getMemberIDWinner(String productIDrequest) {

		String query = "SELECT ( CASE WHEN EXISTS( SELECT MEMBER_ID FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID ) THEN( SELECT ORDERER.MEMBER_ID FROM AUCTIONEER JOIN ORDERER ON AUCTIONEER.MEMBER_ID = ORDERER.MEMBER_ID WHERE ORDERER.ORDER_PRICE =( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER JOIN AUCTION ON ORDERER.PRODUCT_ID = AUCTION.PRODUCT_ID JOIN PRODUCTS ON PRODUCTS.PRODUCT_ID = ORDERER.PRODUCT_ID WHERE PRODUCTS.PRODUCT_ID = PRODUCTS.PRODUCT_ID ) ) ELSE '' END ) AS MEMBERID_WINNER FROM PRODUCTS, AUCTION WHERE AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND PRODUCTS.PRODUCT_ID = ?";
		try {
			
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, productIDrequest);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				return rs.getString("MEMBERID_WINNER");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<AuctionPostBean> getAuctionCouponList(String productNameSearch) {

		List<AuctionPostBean> autionCouponList = new ArrayList<AuctionPostBean>();

		String query = "SELECT PRODUCT, AUCTION.PRODUCT_ID, PRODUCTS.IMAGE_PATH AS PRODUCT_IMG_PATH, PRODUCTS.DESCRIBE AS PRODUCT_DESCRIBE, MEMBER_NAME, AUCTION.MEMBER_ID, AUCTIONEER.PHONE AS MEMBER_POST_PHONE, AUCTIONEER.EMAIL AS MEMBER_POST_EMAIL, STARTING_PRICE, ( CASE WHEN( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID ) IS NULL THEN 0 ELSE( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID ) END ) AS HIGHEST_PRICE, STEP_PRICE, START_TIME, END_TIME, STOP_AUCTION_FLAG FROM AUCTION, PRODUCTS, AUCTIONEER WHERE AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND AUCTION.MEMBER_ID = AUCTIONEER.MEMBER_ID AND NOW() <= END_TIME AND STOP_AUCTION_FLAG = 0 AND PRODUCTS.PRODUCT = ? GROUP BY AUCTION.PRODUCT_ID ORDER BY AUCTION.START_TIME";
		try {
			
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, productNameSearch);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				auctionPostBean = new AuctionPostBean();

				auctionPostBean.setProduct(rs.getString("PRODUCT"));
				auctionPostBean.setProductID(rs.getString("PRODUCT_ID"));

				auctionPostBean.setStartingPrice(rs.getString("STARTING_PRICE"));
				auctionPostBean.setHighestPrice(rs.getString("HIGHEST_PRICE"));
				auctionPostBean.setStepPrice(rs.getString("STEP_PRICE"));
				auctionPostBean.setStartTime(rs.getString("START_TIME"));
				auctionPostBean.setEndTime(rs.getString("END_TIME"));

				auctionPostBean.setStopAuctionFlag(rs.getString("STOP_AUCTION_FLAG") == "1" ? true : false);

				detailAuctionPostBean = new DetailAuctionPostBean();

				detailAuctionPostBean.setProduct(rs.getString("PRODUCT"));
				detailAuctionPostBean.setProductID(rs.getString("PRODUCT_ID"));
				detailAuctionPostBean.setProductImgPath(rs.getString("PRODUCT_IMG_PATH"));
				detailAuctionPostBean.setProductDescribe(rs.getString("PRODUCT_DESCRIBE"));

				detailAuctionPostBean.setMemberName(rs.getString("MEMBER_NAME"));
				detailAuctionPostBean.setMemberID(rs.getString("MEMBER_ID"));
				detailAuctionPostBean.setPhone(rs.getString("MEMBER_POST_PHONE"));
				detailAuctionPostBean.setEmail(rs.getString("MEMBER_POST_EMAIL"));

				auctionPostBean.setDetailAuctString(StringProcess.toJSONArrayString(detailAuctionPostBean));

				autionCouponList.add(auctionPostBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return autionCouponList;
	}

}