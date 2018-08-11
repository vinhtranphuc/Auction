package management.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import config.Validate;
import form.AuctionInforForm;
import management.model.bo.AuctionInforBO;

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

public class AuctionInforAction extends Action {

	// http session
	private HttpSession httpSession;

	private String userNameSession;

	private String memberID;
	private String productID;
	private String searchButton;
	
	private String productNameSearch;
	private String statusSearch;
	private String postDateSearch;
	
	private AuctionInforForm auctionInforForm;
	private AuctionInforBO auctionInforBO;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		httpSession = request.getSession();

		userNameSession = (String) httpSession.getAttribute("userName");
		
		auctionInforBO = new AuctionInforBO();
		
		memberID = auctionInforBO.getMemberIdBasedUserName(userNameSession);

		if (!Validate.isExistsData(memberID)) {
			
			return mapping.findForward("login");
		}

		auctionInforForm = (AuctionInforForm) form;
		
		auctionInforForm.setProductList(auctionInforBO.getProductsBasedOnUser(memberID));
		
		searchButton = auctionInforForm.getSearchButton();

		if (StringUtils.equals(searchButton, "search")) {
			
			System.out.println("searchButton ");
			
			productNameSearch = auctionInforForm.getProductNameSearch();
			statusSearch = auctionInforForm.getStatusSearch();
			postDateSearch = auctionInforForm.getPostDateSearch();
			
			System.out.println("productNameSearch: "+productNameSearch);
			System.out.println("statusSearch: "+statusSearch);
			System.out.println("postDateSearch: "+postDateSearch);

			if (Validate.isExistsData(productNameSearch) || Validate.isExistsData(statusSearch)
					|| Validate.isExistsData(postDateSearch)) {
				
				auctionInforForm.setProductList(auctionInforBO.getProductsSearch(productNameSearch,postDateSearch,statusSearch,memberID));
			}
		}
		
		productID = auctionInforForm.getProductID();

		if (!Validate.isExistsData(productID)) {

			return mapping.findForward("aucitionInfo");
		}

		if (auctionInforBO.isExistProductID(productID)) {
			
			productNameSearch = auctionInforForm.getProductNameSearch();
			statusSearch = auctionInforForm.getStatusSearch();
			postDateSearch = auctionInforForm.getPostDateSearch();
			
			System.out.println("productNameSearch: "+productNameSearch);
			System.out.println("statusSearch: "+statusSearch);
			System.out.println("postDateSearch: "+postDateSearch);

			// get product base on productID & memberID
			auctionInforForm.setDetailAuctionInforBean(auctionInforBO.getDetailAuctionInfor(productID, memberID));
		}

		return mapping.findForward("aucitionInfo");
	}
	
}