package management.model.bo;

import management.model.bean.DetailAuctionInforBean;
import management.model.dao.AjaxUpdateDataAuctionDAO;

public class AjaxUpdateDataAuctionBO {
	
	private AjaxUpdateDataAuctionDAO ajaxUpdateDataAuctionDAO = new AjaxUpdateDataAuctionDAO();
	
	public boolean addNewAuction(DetailAuctionInforBean detailAuctionInforBean) {
		return ajaxUpdateDataAuctionDAO.addNewAuction(detailAuctionInforBean);
	}

	public String getMaxProductID() {
		return ajaxUpdateDataAuctionDAO.getMaxProductID();
	}

	public boolean editAuction(DetailAuctionInforBean detailAuctionInforBean) {
		return ajaxUpdateDataAuctionDAO.editAuction(detailAuctionInforBean);
	}

	public boolean deleteAuction(DetailAuctionInforBean detailAuctionInforBean) {
		return ajaxUpdateDataAuctionDAO.deleteAuction(detailAuctionInforBean);
	}

	public boolean stopAuction(DetailAuctionInforBean detailAuctionInforBean) {
		return ajaxUpdateDataAuctionDAO.stopAuction(detailAuctionInforBean);
	}
}
