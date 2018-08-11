package management.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConnectMySQL;

import management.model.bean.DetailAuctionInforBean;
import management.model.bean.ProductBean;

public class AuctionInforDAO extends ConnectMySQL {

	ResultSet rs;
	PreparedStatement pstmt;
	DetailAuctionInforBean detailAuctionInforBean;
	ProductBean productBean;
	List<ProductBean> listProducts;
	
	public DetailAuctionInforBean getDetailAuctionInfor(String productID, String memberID) {
		
		detailAuctionInforBean = new DetailAuctionInforBean();
		
		String query = "SELECT PRODUCTS.PRODUCT_ID, PRODUCT, PRODUCTS.CATEGORY_ID, CATEGORY, IMAGE_PATH, PRODUCTS.DESCRIBE, START_TIME, END_TIME, STARTING_PRICE, STEP_PRICE, MARKET_PRICE,(CASE WHEN EXISTS (SELECT MEMBER_ID FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID) THEN( SELECT MEMBER_NAME FROM AUCTIONEER JOIN ORDERER ON AUCTIONEER.MEMBER_ID = ORDERER.MEMBER_ID WHERE ORDERER.ORDER_PRICE = (SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER JOIN AUCTION ON ORDERER.PRODUCT_ID = AUCTION.PRODUCT_ID JOIN PRODUCTS ON PRODUCTS.PRODUCT_ID = ORDERER.PRODUCT_ID WHERE PRODUCTS.PRODUCT_ID = PRODUCTS.PRODUCT_ID )) ELSE '' END) AS WINNER_AUCTION, ( CASE WHEN( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID ) IS NULL THEN 0 ELSE( SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID ) END ) AS HIGHEST_PRICE, ( CASE WHEN EXISTS( SELECT MEMBER_ID FROM ORDERER WHERE PRODUCT_ID = PRODUCTS.PRODUCT_ID ) THEN ( SELECT ORDERER.MEMBER_ID FROM AUCTIONEER JOIN ORDERER ON AUCTIONEER.MEMBER_ID = ORDERER.MEMBER_ID WHERE ORDERER.ORDER_PRICE = (SELECT MAX(ORDERER.ORDER_PRICE) FROM ORDERER JOIN AUCTION ON ORDERER.PRODUCT_ID = AUCTION.PRODUCT_ID JOIN PRODUCTS ON PRODUCTS.PRODUCT_ID = ORDERER.PRODUCT_ID WHERE PRODUCTS.PRODUCT_ID = PRODUCTS.PRODUCT_ID)) ELSE '' END ) AS MEMBERID_WINNER, ( CASE WHEN NOW() < START_TIME THEN 'Chưa bắt đầu' WHEN (START_TIME <= NOW() AND NOW() < END_TIME) THEN 'Đang đấu giá' WHEN NOW() >= END_TIME AND EXISTS(SELECT PRODUCT_ID FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCTS.PRODUCT_ID ) THEN 'Hết thời gian - đã được order' WHEN NOW() >= END_TIME AND EXISTS(SELECT PRODUCT_ID FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCTS.PRODUCT_ID ) THEN 'Hết thời gian - không được order' WHEN (EXISTS( SELECT PRODUCTS.PRODUCT_ID FROM PRODUCTS JOIN AUCTION ON PRODUCTS.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE AUCTION.STOP_AUCTION_FLAG = 1 AND AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID )) THEN 'Đã dừng đăng tải' ELSE 'FAIL' END) AS STATUS FROM CATEGOTIES, PRODUCTS, AUCTION, AUCTIONEER WHERE CATEGOTIES.CATEGORY_ID = PRODUCTS.CATEGORY_ID AND PRODUCTS.PRODUCT_ID = AUCTION.PRODUCT_ID AND AUCTION.MEMBER_ID = AUCTIONEER.MEMBER_ID AND PRODUCTS.PRODUCT_ID = ? AND AUCTION.MEMBER_ID = ?";
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
				detailAuctionInforBean.setStepPrice(rs.getString("STEP_PRICE"));
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

	public String getMemberIdBasedUserName(String userName) {
		String query = "SELECT MEMBER_ID FROM USERS WHERE USER_NAME = ?";
		try {
			pstmt = getPrepareStatement(query);
			pstmt.setString(1,userName);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				return rs.getString("MEMBER_ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean isExistProductID(String productID) {

		String query = "SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_ID = ?";
		try {
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, productID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<ProductBean> getProductsBasedOnUser(String memberID) {
		
		listProducts = new ArrayList<ProductBean>();
		
		String query = "SELECT PRODUCTS.PRODUCT_ID,PRODUCT FROM PRODUCTS JOIN AUCTION ON PRODUCTS.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE AUCTION.MEMBER_ID = ?";
		
		try {
			
			pstmt = getPrepareStatement(query);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				productBean = new ProductBean();
				productBean.setProductID(rs.getString("PRODUCT_ID"));
				productBean.setProductName(rs.getString("PRODUCT"));
				
				listProducts.add(productBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listProducts;
	}
	
	public static void main(String[] args) {
		AuctionInforDAO a = new AuctionInforDAO();
	
		for(ProductBean p : a.getProductsSearch("Đất XXXXX", "", "","1")){
			System.out.println(p.toString());
		}
	}

	public List<ProductBean> getProductsSearch(String productNameSearch, String postDateSearch, String statusSearch,String memberID) {
		
		listProducts = new ArrayList<ProductBean>();
		
		String query = "SELECT PRODUCTS.PRODUCT_ID, PRODUCT, START_TIME FROM PRODUCTS JOIN AUCTION ON PRODUCTS.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE ( ((? = '') OR(? IS NULL)) OR PRODUCT = ? ) AND( ((? = '') OR(? IS NULL)) OR DATE(AUCTION.START_TIME) = ? ) AND( ((? = '') OR(? IS NULL)) OR CASE WHEN ? = 1 THEN NOW() < START_TIME WHEN ? = 2 THEN( START_TIME <= NOW() AND NOW() < END_TIME) WHEN ? = 3 THEN( NOW() >= END_TIME AND EXISTS( SELECT PRODUCT_ID FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCTS.PRODUCT_ID )) WHEN ? = 4 THEN( NOW() >= END_TIME AND NOT EXISTS( SELECT PRODUCT_ID FROM ORDERER WHERE ORDERER.PRODUCT_ID = PRODUCTS.PRODUCT_ID )) WHEN ? = 5 THEN (EXISTS( SELECT PRODUCTS.PRODUCT_ID FROM PRODUCTS JOIN AUCTION ON PRODUCTS.PRODUCT_ID = AUCTION.PRODUCT_ID WHERE AUCTION.STOP_AUCTION_FLAG = 1 AND AUCTION.PRODUCT_ID = PRODUCTS.PRODUCT_ID )) ELSE FALSE END AND AUCTION.MEMBER_ID = 1)";
		try {
			
				pstmt = getPrepareStatement(query);
				
				pstmt.setString(1, productNameSearch);
				pstmt.setString(2, productNameSearch);
				pstmt.setString(3, productNameSearch);
				
				pstmt.setString(4, postDateSearch);
				pstmt.setString(5, postDateSearch);
				pstmt.setString(6, postDateSearch);
				
				pstmt.setString(7, statusSearch);
				pstmt.setString(8, statusSearch);
				pstmt.setString(9, statusSearch);
				pstmt.setString(10, statusSearch);
				pstmt.setString(11, statusSearch);
				pstmt.setString(12, statusSearch);
				
				pstmt.setString(13, memberID);
				
				rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productBean = new ProductBean();
				productBean.setProductID(rs.getString("PRODUCT_ID"));
				productBean.setProductName(rs.getString("PRODUCT"));
				productBean.setStartTime(rs.getString("START_TIME"));

				listProducts.add(productBean);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return listProducts;
	}
	
}
