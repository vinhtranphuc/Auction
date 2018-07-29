package management.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectMySQL;

import management.model.bean.DetailAuctionInforBean;

public class AuctionInforDAO extends ConnectMySQL {

	ResultSet rs;
	PreparedStatement pstmt;
	DetailAuctionInforBean detailAuctionInforBean;

	public DetailAuctionInforBean getDetailAuctionInfor(String productID, String memberID) {
		
		detailAuctionInforBean = new DetailAuctionInforBean();
		
		String query = "SELECT PRODUCTS.PRODUCT_ID, PRODUCT, PRODUCTS.CATEGORY_ID, CATEGORY, IMAGE_PATH, PRODUCTS.DESCRIBE, START_TIME, END_TIME, STARTING_PRICE, STEP_PRICE, MARKET_PRICE,(CASE WHEN (SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID) IS NULL THEN 0 ELSE (SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID) END) AS HIGHEST_PRICE,(CASE WHEN EXISTS (SELECT MEMBER_ID FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID) THEN (SELECT MEMBER_NAME FROM AUCTIONEER, ORDERER WHERE AUCTIONEER.MEMBER_ID = ORDERER.MEMBER_ID AND ORDERER.PRODUCT_ID = PRODUCTS.PRODUCT_ID) ELSE '' END) AS WINNER_AUCTION,(CASE WHEN EXISTS (SELECT MEMBER_ID FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID) THEN (SELECT ORDERER.MEMBER_ID FROM AUCTIONEER, ORDERER WHERE AUCTIONEER.MEMBER_ID = ORDERER.MEMBER_ID AND ORDERER.PRODUCT_ID = PRODUCTS.PRODUCT_ID) ELSE '' END) AS MEMBERID_WINNER, (CASE WHEN NOW() < START_TIME  THEN '1' WHEN (START_TIME <= NOW() AND NOW() < END_TIME) THEN '2' WHEN NOW() >= END_TIME AND EXISTS (SELECT PRODUCT_ID FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCTS.PRODUCT_ID) THEN '3' ELSE '4' END) AS STATUS FROM CATEGOTIES,PRODUCTS,AUCTION,AUCTIONEER WHERE CATEGOTIES.CATEGORY_ID = PRODUCTS.CATEGORY_ID AND	PRODUCTS.PRODUCT_ID = AUCTION.PRODUCT_ID AND AUCTION.MEMBER_ID = AUCTIONEER.MEMBER_ID AND PRODUCTS.PRODUCT_ID = ? AND AUCTION.MEMBER_ID = ?";
		try {
			
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, productID);
			pstmt.setString(2, memberID);
			rs = pstmt.executeQuery();

			while (rs.next()) {


				detailAuctionInforBean.setProductID(rs.getString("PRODUCT_ID"));
				detailAuctionInforBean.setProductName(rs.getString("PRODUCT"));
				detailAuctionInforBean.setCategoryID(rs.getString("CATEGORY_ID"));
				detailAuctionInforBean.setCategoryName(rs.getString("CATEGORY"));
				detailAuctionInforBean.setProductImgPath(rs.getString("IMAGE_PATH"));
				detailAuctionInforBean.setProductDescribe(rs.getString("DESCRIBE"));

				detailAuctionInforBean.setStartTime(rs.getString("START_TIME"));
				detailAuctionInforBean.setEndTime(rs.getString("END_TIME"));
				detailAuctionInforBean.setStartingPrice(rs.getString("STARTING_PRICE"));
				detailAuctionInforBean.setMarketPrice(rs.getString("MARKET_PRICE"));
				detailAuctionInforBean.setStatus(rs.getString("STATUS"));

				detailAuctionInforBean.setWinnerAuction(rs.getString("WINNER_AUCTION"));
				detailAuctionInforBean.setMemberIDWinner(rs.getString("MEMBERID_WINNER"));
				detailAuctionInforBean.setHighestPrice(rs.getString("HIGHEST_PRICE"));

				return detailAuctionInforBean;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

}
