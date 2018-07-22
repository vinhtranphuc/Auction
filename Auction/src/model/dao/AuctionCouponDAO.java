package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.StringProcess;
import model.bean.AuctionCouponBean;
import model.bean.DetailAuctionCouponBean;
import model.bean.Orderer;
import model.bean.UserBean;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jul 5, 2018        	Vinh          	Create
 */

public class AuctionCouponDAO extends ConnectMySQL {

	ResultSet rs;
	PreparedStatement pstmt;
	Statement stmt;
	AuctionCouponBean auctionCouponBean;
	DetailAuctionCouponBean detailAuctionCouponBean;
	Orderer orderer;
	

	public List<AuctionCouponBean> getAuctionCouponList() {

		List<AuctionCouponBean> autionCouponList = new ArrayList<AuctionCouponBean>();

		String query = "SELECT PRODUCT, AUCTION.PRODUCT_ID, PRODUCTS.IMAGE_PATH AS PRODUCT_IMG_PATH, PRODUCTS.DESCRIBE AS PRODUCT_DESCRIBE, MEMBER_NAME, AUCTION.MEMBER_ID, AUCTIONEER.PHONE AS MEMBER_POST_PHONE, AUCTIONEER.EMAIL AS MEMBER_POST_EMAIL, STARTING_PRICE, (CASE WHEN (SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID) IS NULL THEN 0 ELSE (SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCT_ID) END) AS HIGHEST_PRICE, STEP_PRICE, START_TIME, END_TIME, STOP_AUCTION_FLAG FROM AUCTION, PRODUCTS, AUCTIONEER WHERE AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND AUCTION.MEMBER_ID = AUCTIONEER.MEMBER_ID GROUP BY AUCTION.PRODUCT_ID ORDER BY AUCTION.PRODUCT_ID ASC";
		try {

			rs = getResultSet(query);
			
			while (rs.next()) {
				
				auctionCouponBean = new AuctionCouponBean();
				
				auctionCouponBean.setProduct(rs.getString("PRODUCT"));
				auctionCouponBean.setProductID(rs.getString("PRODUCT_ID"));
				
				auctionCouponBean.setStartingPrice(rs.getString("STARTING_PRICE"));
				auctionCouponBean.setHighestPrice(rs.getString("HIGHEST_PRICE"));
				auctionCouponBean.setStepPrice(rs.getString("STEP_PRICE"));
				auctionCouponBean.setStartTime(rs.getString("START_TIME"));
				auctionCouponBean.setEndTime(rs.getString("END_TIME"));
			
				auctionCouponBean.setStopAuctionFlag(rs.getString("STOP_AUCTION_FLAG") == "1" ? true : false);
				
				detailAuctionCouponBean = new DetailAuctionCouponBean();
						
				detailAuctionCouponBean.setProduct(rs.getString("PRODUCT"));
				detailAuctionCouponBean.setProductID(rs.getString("PRODUCT_ID"));
				detailAuctionCouponBean.setProductImgPath(rs.getString("PRODUCT_IMG_PATH"));
				detailAuctionCouponBean.setProductDescribe(rs.getString("PRODUCT_DESCRIBE"));
				
				detailAuctionCouponBean.setMemberName(rs.getString("MEMBER_NAME"));
				detailAuctionCouponBean.setMemberID(rs.getString("MEMBER_ID"));
				detailAuctionCouponBean.setPhone(rs.getString("MEMBER_POST_PHONE"));
				detailAuctionCouponBean.setEmail(rs.getString("MEMBER_POST_EMAIL"));
				
				auctionCouponBean.setDetailAuctString(StringProcess.toJSONArrayString(detailAuctionCouponBean));

				autionCouponList.add(auctionCouponBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return autionCouponList;
	}
	
	public List<Orderer> getOrderList(String productID){
		
		List<Orderer> orderList = new ArrayList<Orderer>();
		
		String query = "SELECT ORDER_ID,ORDERER.MEMBER_ID, AUCTIONEER.MEMBER_NAME,ORDERER.PRODUCT_ID,ORDER_PRICE,STOP_FLAG FROM ORDERER JOIN AUCTIONEER ON ORDERER.MEMBER_ID = AUCTIONEER.MEMBER_ID JOIN AUCTION ON ORDERER.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE ORDERER.PRODUCT_ID = ? AND AUCTION.STOP_AUCTION_FLAG = 0 ORDER BY ORDER_ID ASC";
		try {
			
			pstmt = getConnection().prepareStatement(query);
			pstmt.setString(1, productID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				orderer = new Orderer();
				
				orderer.setOrderID(rs.getString("ORDER_ID"));
				orderer.setMemberID(rs.getString("MEMBER_ID"));
				orderer.setMemberName(rs.getString("MEMBER_NAME"));
				orderer.setProductID(rs.getString("PRODUCT_ID"));
				orderer.setOrderPrice(rs.getString("ORDER_PRICE"));
				orderer.setStopOrderFlag(rs.getString("STOP_FLAG") == "1" ? true:false);
				
				orderList.add(orderer);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return orderList;
	}
	
	public List<Orderer> getAllOrderList(){
		
		List<Orderer> orderList = new ArrayList<Orderer>();
		
		String query = "SELECT ORDER_ID,ORDERER.MEMBER_ID,ORDERER.PRODUCT_ID,ORDER_PRICE,STOP_FLAG FROM ORDERER JOIN AUCTION ON ORDERER.MEMBER_ID = AUCTION.MEMBER_ID AND ORDERER.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE AUCTION.STOP_AUCTION_FLAG = 0";
		try {
			rs = getResultSet(query);
			
			while(rs.next()){
				
				orderer = new Orderer();
				
				orderer.setOrderID(rs.getString("ORDER_ID"));
				orderer.setMemberID(rs.getString("MEMBER_ID"));
				orderer.setProductID(rs.getString("PRODUCT_ID"));
				orderer.setOrderPrice(rs.getString("ORDER_PRICE"));
				orderer.setStopOrderFlag(rs.getString("STOP_FLAG") == "1" ? true:false);
								
				orderList.add(orderer);
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
			
			if(count > 0) {
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
			
			while(rs.next()){
				return rs.getString("MEMBER_ID");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {

		AuctionCouponDAO acd = new AuctionCouponDAO();
		System.out.println(acd.getMemberIDSession("1"));
		
	}

}