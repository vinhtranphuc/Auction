package management.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jul 5, 2018        	Vinh          	Create
 */

public class AuctionInforAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AuctionInforForm auctionInforForm = (AuctionInforForm) form;
		AuctionInforBO auctionInforBO = new AuctionInforBO();
		
		auctionInforForm.setDetailAuctionInforBean(auctionInforBO.getDetailAuctionInfor("1", "1"));
		return mapping.findForward("aucitionInfo");
	}
}