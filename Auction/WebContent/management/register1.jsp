<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Đăng ký thành viên</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Custom Fonts -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

	<div id="wrapper">
		<div class="row">
			<div class="row" style="margin-top: 1%">
				<div class="col-sm-offset-3 col-sm-6">
					<span>
						<center>
							<h3>
								<b>ĐĂNG KÝ THÀNH VIÊN ĐẤU GIÁ</b>
							</h3>
						</center>
					</span>
				</div>
			</div>
			<div class="row" style="margin-top: 1%">
				<div class="col-sm-offset-3 col-sm-6">

					<html:form action="/signin.do">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-5">
									<div style="height: 250px; margin-top: 15px">
										<img src="images/test.jpg" class="img-responsive center-block"
											id="img-product" alt="iphone x">
									</div>
									<input type="file" name="inp-chofile" id="inp-chofile">
								</div>
								<div class="col-sm-7">
									<label>Họ tên</label>
									<html:text styleClass="form-control" property="memberName" />
									<label>Ngày sinh</label>
									<html:text styleClass="form-control" property="birthDate" />
									<label>Số điện thoại</label>
									<html:text styleClass="form-control" property="phone" />
									<label>Email</label>
									<html:text styleClass="form-control" property="email" />
									<label>Số CMND</label>
									<html:text styleClass="form-control" property="identication" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label>Địa chỉ</label>
							<html:textarea styleClass="form-control" property="address"
								rows="3" styleId="comment" />
						</div>
						<div class="form-group">
							<label>Tên tài khoản</label>
							<html:text styleClass="form-control" property="userName" />
						</div>
						<div class="form-group">
							<label>Mật khẩu</label>
							<html:password styleClass="form-control" property="password" />
						</div>
						<div class="form-group">
							<label>Xác nhận</label>
							<html:password styleClass="form-control"
								property="confirmPassword" />
						</div>
						<center>
							<html:submit styleClass="btn btn-default" value="register">Đăng ký</html:submit>
							<button type="button" class="btn btn-default">Clear</button>
						</center>
					</html:form>
				</div>
			</div>

		</div>

	</div>

</body>
</html>