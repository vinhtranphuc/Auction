package management.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ConnectMySQL;
import management.model.bean.DetailAuctionInforBean;

public class AjaxUpdateDataAuctionDAO extends ConnectMySQL {
	
	ResultSet rs;
	PreparedStatement pstmt;

	public boolean addNewAuction(DetailAuctionInforBean detailAuctionInforBean) {

		if (insertProduct(detailAuctionInforBean)) {
			if (insertAuction(detailAuctionInforBean)) {
				return true;
			}
			deleteMaxProduct();
		}
		return false;
	}
	
	public boolean insertProduct(DetailAuctionInforBean detailAuctionInforBean){
		
		String queryInsertProduct = "INSERT INTO PRODUCTS (PRODUCT_ID,CATEGORY_ID,PRODUCT,`DESCRIBE`,IMAGE_PATH) VALUES (?,?,?,?,?)";
		
		try {
			pstmt = getPrepareStatement(queryInsertProduct);
			
			pstmt.setString(1, detailAuctionInforBean.getProductID());
			pstmt.setString(2, detailAuctionInforBean.getCategoryID());
			pstmt.setString(3, detailAuctionInforBean.getProductName());
			pstmt.setString(4, detailAuctionInforBean.getProductDescribe());
			pstmt.setString(5, detailAuctionInforBean.getProductImgPath());

			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteMaxProduct(){
		
		String queryDeleteMaxProduct = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
		
		String maxProductID = Integer.parseInt(getMaxProductID())-1+"";
		try {
			pstmt = getPrepareStatement(queryDeleteMaxProduct);
			pstmt.setString(1, maxProductID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean insertAuction(DetailAuctionInforBean detailAuctionInforBean){
		
		String queryInsertAuction = "INSERT INTO AUCTION (PRODUCT_ID,MEMBER_ID,START_TIME,END_TIME,MARKET_PRICE,STARTING_PRICE,STEP_PRICE,STOP_AUCTION_FLAG,PAY_FLAG) VALUES (?,?,?,?,?,?,?,?,b?)";
		
		try {
			pstmt = getPrepareStatement(queryInsertAuction);			
			pstmt.setString(1, detailAuctionInforBean.getProductID());
			pstmt.setString(2, detailAuctionInforBean.getMemberID());
			pstmt.setString(3, detailAuctionInforBean.getStartTime());
			pstmt.setString(4, detailAuctionInforBean.getEndTime());
			pstmt.setString(5, detailAuctionInforBean.getMarketPrice());
			pstmt.setString(6, detailAuctionInforBean.getStartingPrice());
			pstmt.setString(7, detailAuctionInforBean.getStepPrice());
			pstmt.setString(8, "0");
			pstmt.setString(9, "0");
			
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

	public String getMaxProductID() {
		
		String query = "SELECT MAX(PRODUCT_ID) AS PRODUCT_ID FROM PRODUCTS";
		try {
			pstmt =  getPrepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString("PRODUCT_ID")==null) 
					return "1";
				return Integer.parseInt(rs.getString("PRODUCT_ID"))+1+"";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 1+"";
		} 
		
		return null;
	}
	
	public static void main(String[] args) {
		
		AjaxUpdateDataAuctionDAO a = new AjaxUpdateDataAuctionDAO();
		System.out.println(a.getMaxProductID());
		/*DetailAuctionInforBean detailAuctionInforBean = new DetailAuctionInforBean();
		
		detailAuctionInforBean.setProductID("1");
		detailAuctionInforBean.setProductName("vvvvv");
		detailAuctionInforBean.setCategoryID("1");
		detailAuctionInforBean.setProductDescribe("sssss");
		detailAuctionInforBean.setProductImgPath("sssss");
		
		detailAuctionInforBean.setMemberID("1");
		detailAuctionInforBean.setProductName("2");
		detailAuctionInforBean.setStartingPrice("3333333");
		detailAuctionInforBean.setStepPrice("5000");
		detailAuctionInforBean.setEndTime("2018-08-15 05:10:20");
		detailAuctionInforBean.setStartTime("2018-08-10 05:10:20");
		detailAuctionInforBean.setMarketPrice("231232");
		
		a.editAuction(detailAuctionInforBean);*/
	
	}

	public boolean editAuction(DetailAuctionInforBean detailAuctionInforBean) {

		if (updateProduct(detailAuctionInforBean)) {
			if (updateAuction(detailAuctionInforBean)) {
				return true;
			}
		}
		
		return false;
	}

	private boolean updateProduct(DetailAuctionInforBean detailAuctionInforBean) {
		
		String queryUpdateProduct = "UPDATE PRODUCTS SET CATEGORY_ID = ?, PRODUCT = ?, `DESCRIBE` = ?, IMAGE_PATH = ? WHERE PRODUCT_ID = ?";
		
		try {
			pstmt = getPrepareStatement(queryUpdateProduct);
			pstmt.setString(1, detailAuctionInforBean.getCategoryID());
			pstmt.setString(2, detailAuctionInforBean.getProductName());
			pstmt.setString(3, detailAuctionInforBean.getProductDescribe());
			pstmt.setString(4, detailAuctionInforBean.getProductImgPath());
			pstmt.setString(5, detailAuctionInforBean.getProductID());
			
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean updateAuction(DetailAuctionInforBean detailAuctionInforBean) {
		
		String queryUpdateAuction = "UPDATE AUCTION SET START_TIME = ?, END_TIME = ?, MARKET_PRICE = ?, STARTING_PRICE = ?, STEP_PRICE = ?, STOP_AUCTION_FLAG = ?, PAY_FLAG =b? WHERE MEMBER_ID = ? AND PRODUCT_ID = ?";
		
		try {
			
			pstmt = getPrepareStatement(queryUpdateAuction);			
			
			pstmt.setString(1, detailAuctionInforBean.getStartTime());
			pstmt.setString(2, detailAuctionInforBean.getEndTime());
			pstmt.setString(3, detailAuctionInforBean.getMarketPrice());
			pstmt.setString(4, detailAuctionInforBean.getStartingPrice());
			pstmt.setString(5, detailAuctionInforBean.getStepPrice());
			pstmt.setString(6, "0");
			pstmt.setString(7, "0");
			
			pstmt.setString(8, detailAuctionInforBean.getMemberID());
			pstmt.setString(9, detailAuctionInforBean.getProductID());
			
			return pstmt.executeUpdate()==1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAuction(DetailAuctionInforBean detailAuctionInforBean) {
		
		if (deleteProduct(detailAuctionInforBean)) {
			if (deleteAuctionInfor(detailAuctionInforBean)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean deleteProduct(DetailAuctionInforBean detailAuctionInforBean) {
		
		String queryDeleteProduct = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
		
		try {
			pstmt = getPrepareStatement(queryDeleteProduct);
			pstmt.setString(1, detailAuctionInforBean.getProductID());
			
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	private boolean deleteAuctionInfor(DetailAuctionInforBean detailAuctionInforBean) {
		
		String queryDeleteProduct = "DELETE FROM AUCTION WHERE PRODUCT_ID = ? AND MEMBER_ID = ?";
		
		try {
			pstmt = getPrepareStatement(queryDeleteProduct);
			pstmt.setString(1, detailAuctionInforBean.getProductID());
			pstmt.setString(2, detailAuctionInforBean.getMemberID());
			
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean stopAuction(DetailAuctionInforBean detailAuctionInforBean) {
		
		System.out.println("stopAuction");
		String queryStopAuction = "UPDATE AUCTION SET STOP_AUCTION_FLAG = 1 WHERE STOP_AUCTION_FLAG = 0 AND PRODUCT_ID = ? AND MEMBER_ID = ?";
		
		try {
			pstmt = getPrepareStatement(queryStopAuction);
			pstmt.setString(1, detailAuctionInforBean.getProductID());
			pstmt.setString(2, detailAuctionInforBean.getMemberID());
			
			return pstmt.executeUpdate()==1;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}
	
}
