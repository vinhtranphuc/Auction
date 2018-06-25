package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Khoa;
import model.bean.SinhVien;
import model.bo.KhoaBO;
import model.bo.SinhVienBO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.SinhVienForm;

/**				
 * SuaSinhVienAction.java		
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

public class SuaSinhVienAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		SinhVienForm sinhVienForm = (SinhVienForm) form;

		SinhVienBO sinhVienBO = new SinhVienBO();
		//lay danh sach cac khoa
		KhoaBO khoaBO = new KhoaBO();
		ArrayList<Khoa> listKhoa = khoaBO.getListKhoa();
		sinhVienForm.setListKhoa(listKhoa);
		
		//sua sinh vien
		String msv=sinhVienForm.getMsv();
		if("submit".equals(sinhVienForm.getSubmit())){					//nhan nut Xac nhan o trang Them sinh vien
			String hoTen= sinhVienForm.getHoTen();
			String gioiTinh = sinhVienForm.getGioiTinh();
			String maKhoa = sinhVienForm.getMaKhoa();
			sinhVienBO.suaSinhVien(msv, hoTen, gioiTinh, maKhoa);
			return mapping.findForward("suaSVxong");
		} else {														//chuyen sang trang Sua sinh vien
			SinhVien sinhVien = sinhVienBO.getThongTinSinhVien(msv);
			sinhVienForm.setHoTen(sinhVien.getHoTen());
			sinhVienForm.setGioiTinh(sinhVien.getGioiTinh());
			sinhVienForm.setMaKhoa(sinhVien.getMaKhoa());
			return mapping.findForward("suaSV");
		}
	}
}
