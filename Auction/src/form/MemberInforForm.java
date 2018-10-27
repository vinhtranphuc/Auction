package form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import management.model.bean.MemberBean;

public class MemberInforForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberID;
	private String memberName;
	private String birthDate;
	private String address;
	private String identication;
	private String email;
	private String phone;
	private String image;
	
	private MemberBean memberInfor;

	public MemberBean getMemberInfor() {
		return memberInfor;
	}
	public void setMemberInfor(MemberBean memberInfor) {
		this.memberInfor = memberInfor;
	}
	private List<MemberBean> memberList;
	
	public List<MemberBean> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberBean> memberList) {
		this.memberList = memberList;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdentication() {
		return identication;
	}
	public void setIdentication(String identication) {
		this.identication = identication;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
