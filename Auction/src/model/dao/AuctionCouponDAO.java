package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.AuctionCouponBean;

public class AuctionCouponDAO extends ConnectMySQL {

	ResultSet rs;
	AuctionCouponBean auctionCouponBean;

	public List<AuctionCouponBean> getAuctionCouponList() {

		List<AuctionCouponBean> autionCouponList = new ArrayList<AuctionCouponBean>();

		String query = "SELECT PRODUCT, AUCTION.PRODUCT_ID, STARTING_PRICE, NAME, AUCTION.MEMBER_ID, STEP_PRICE, START_TIME, END_TIME, STOP_AUCTION_FLAG FROM AUCTION, PRODUCTS, AUCTIONEER WHERE AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND AUCTION.MEMBER_ID = AUCTIONEER.MEMBER_ID";
		try {

			rs = getResultSet(query);
			while (rs.next()) {

				auctionCouponBean = new AuctionCouponBean();

				auctionCouponBean.setProduct(rs.getString("PRODUCT"));
				auctionCouponBean.setProductID(rs.getString("PRODUCT_ID"));
				auctionCouponBean.setStartingPrice(rs.getString("STARTING_PRICE"));
				auctionCouponBean.setHighestPrice("");
				auctionCouponBean.setMemberName(rs.getString("NAME"));
				auctionCouponBean.setMemberID(rs.getString("MEMBER_ID"));
				auctionCouponBean.setStepPrice(rs.getString("STEP_PRICE"));
				auctionCouponBean.setStartTime(rs.getString("START_TIME"));
				auctionCouponBean.setEndTime(rs.getString("END_TIME"));
				auctionCouponBean.setStopAuctionFlag(rs.getString("STOP_AUCTION_FLAG") == "1" ? true : false);

				autionCouponList.add(auctionCouponBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return autionCouponList;
	}

	public List<AuctionCouponBean> getDspAuctionFlagList() {

		List<AuctionCouponBean> autionCouponList = new ArrayList<AuctionCouponBean>();

		String query = "SELECT PRODUCT, AUCTION.PRODUCT_ID, STARTING_PRICE, NAME, AUCTION.MEMBER_ID, STEP_PRICE, START_TIME, END_TIME, STOP_AUCTION_FLAG FROM AUCTION, PRODUCTS, AUCTIONEER WHERE AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID AND AUCTION.MEMBER_ID = AUCTIONEER.MEMBER_ID";
		try {
			auctionCouponBean = new AuctionCouponBean();

			rs = getResultSet(query);
			while (rs.next()) {
				auctionCouponBean.setProductID(rs.getString("PRODUCT_ID"));
				auctionCouponBean.setStopAuctionFlag(rs.getString("STOP_AUCTION_FLAG") == "1" ? true : false);
				autionCouponList.add(auctionCouponBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return autionCouponList;
	}

	public static void main(String[] args) {
		AuctionCouponDAO s = new AuctionCouponDAO();
		for (AuctionCouponBean a : s.getAuctionCouponList()) {
			System.out.println(a.getMemberID());
			System.out.println(a.getProduct());
			System.out.println(a + "--");
		}
	}

}
