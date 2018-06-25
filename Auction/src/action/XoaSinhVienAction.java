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
 * XoaSinhVienAction.java
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
 * Jan 21, 2015        	DaiLV2          Create
 */

public class XoaSinhVienAction extends Action{
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
		
		//xoa sinh vien
		String msv=sinhVienForm.getMsv();
		if("submit".equals(sinhVienForm.getSubmit())){			//nhan nut Xac nhan o trang Xoa sinh vien
			sinhVienBO.xoaSinhVien(msv);
			return mapping.findForward("xoaSVxong");
		} else {													//chuyen sang trang Xoa sinh vien
			SinhVien sinhVien = sinhVienBO.getThongTinSinhVien(msv);
			sinhVienForm.setHoTen(sinhVien.getHoTen());
			sinhVienForm.setGioiTinh(sinhVien.getGioiTinh());
			sinhVienForm.setMaKhoa(sinhVien.getMaKhoa());
			return mapping.findForward("xoaSV");
		}
	}
}

