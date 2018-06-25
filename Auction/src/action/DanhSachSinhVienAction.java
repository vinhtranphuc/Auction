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

import form.DanhSachSinhVienForm;

/**
 * SinhVienAction.java
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

public class DanhSachSinhVienAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DanhSachSinhVienForm nguoiDungForm = (DanhSachSinhVienForm) form;
		
		//lay danh sach cac khoa
		KhoaBO khoaBO = new KhoaBO();
		ArrayList<Khoa> listKhoa = khoaBO.getListKhoa();
		nguoiDungForm.setListKhoa(listKhoa);
		
		//lay danh sach sinh vien
		ArrayList<SinhVien> listSinhVien;
		SinhVienBO sinhVienBO = new SinhVienBO();
		String maKhoa=nguoiDungForm.getMaKhoa();
		if(maKhoa==null || maKhoa.length()==0){
			listSinhVien = sinhVienBO.getListSinhVien();
		} else {
			listSinhVien = sinhVienBO.getListSinhVien(maKhoa);
		}
		nguoiDungForm.setListSinhVien(listSinhVien);
		
		return mapping.findForward("dsSinhVien");
	}
}

