package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Khoa;
import model.bo.KhoaBO;
import model.bo.SinhVienBO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;
import form.SinhVienForm;

/**				
 * ThemSinhVienAction.java		
 *				
 * Version 1.0				
 *				
 * Date: Jan 21, 2015		
 *				
 * Copyright 				
 *				
 * Modification Logs:				
 * DATE                 AUTHOR          DESCRIPTION				
 * -----------------------------------------------------------------------				
 * Jan 21, 2015         DaiLV            Create				
 */

public class ThemSinhVienAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		SinhVienForm sinhVienForm = (SinhVienForm) form;
		//lay danh sach cac khoa
		KhoaBO khoaBO = new KhoaBO();
		ArrayList<Khoa> listKhoa = khoaBO.getListKhoa();
		sinhVienForm.setListKhoa(listKhoa);
		
		//validate du lieu
		if("submit".equals(sinhVienForm.getSubmit())){
			ActionErrors actionErrors = new ActionErrors();
			if(StringProcess.notVaild(sinhVienForm.getMsv())){
				actionErrors.add("msvError", new ActionMessage("error.msv.trong"));
			}
			if(StringProcess.notVaildNumber(sinhVienForm.getMsv())){
				actionErrors.add("msvError", new ActionMessage("error.msv.so"));
			}
			saveErrors(request, actionErrors);
			if(actionErrors.size()>0){
				return mapping.findForward("themSVerror");
			}
		}
		if("submit".equals(sinhVienForm.getSubmit())){		//nhan nut Xac nhan o trang Them sinh vien
			String maKhoa = sinhVienForm.getMaKhoa();
			String msv = sinhVienForm.getMsv();
			String hoTen= sinhVienForm.getHoTen();
			String gioiTinh = sinhVienForm.getGioiTinh();
			SinhVienBO sinhVienBO = new SinhVienBO();
			sinhVienBO.themSinhVien(msv, hoTen, gioiTinh, maKhoa);
			return mapping.findForward("themSVxong");
		} else {											//chuyen sang trang Them sinh vien
			return mapping.findForward("themSV");
		}
	}
}
