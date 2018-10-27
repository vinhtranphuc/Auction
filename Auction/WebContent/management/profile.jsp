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

	<title>Auction Management</title>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<!-- Custom CSS -->
	<link href="management/css/sb-admin.css" rel="stylesheet">

	<!-- Custom Fonts -->
	<link href="management/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<!-- /#wrapper -->
	<!-- jQuery library -->
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

			<div id="wrapper">
				<!-- Navigation -->
				<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="index.html">Post management</a>
					</div>
					<!-- Top Menu Items -->
					<ul class="nav navbar-right top-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><i class="fa fa-user"></i> VinhTP1<b
							class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="fa fa-fw fa-power-off"></i> Log
								Out</a></li>
							</ul>
						</li>
					</ul>
					<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<ul class="nav navbar-nav side-nav">
							<li><a href="#"><i class="fa fa-home"></i> HOME</a></li>
							<li><a href="auctionInfor.do"><i class="glyphicon glyphicon-user"></i> Mặt hàng</a></li>
							<li><a href="profile.do"><i class="glyphicon glyphicon-comment"></i> Profile</a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</nav>
				<!-- Ending navbar -->

				<!-- Starting page-wrapper -->
				<div id="page-wrapper" class="block"> <!--style="height: 93vh; width: 99%"  -->
					<div class="panel-group">

						<div class="panel-body">
							<div class="row">
								<div class="col-sm-3">
									<!-- starting list user= -->
									<logic:present name="profileForm" property="adminFlag">
										<div class="row">
											<b style="margin-left: 2%; width: 100%">DANH SÁCH NGƯỜI DÙNG</b>
											<ul class="list-group" id="ul-user-list">
												<logic:iterate name="profileForm" property="userList"
													id="user">
													<li class="list-group-item" id="li-user-list"><a
														href="?memberIDSearch=<logic:present name="user" property="memberID"><bean:write name="user" property="memberID"/></logic:present>">
															<strong> <logic:present name="user"
																	property="userName">
																	<bean:write name="user" property="userName" />
																</logic:present>
														</strong>
													</a></li>
												</logic:iterate>
											</ul>
										</div>
									</logic:present>
									<!-- ending list user= -->
								</div>

								<!-- starting user infor gr -->
								<div id = "div-member-infor" class="col-sm-9">			
									<div class="row">
										<div class="row" style="margin-bottom: 2%">
											<b style="margin-left: 3%;">1. Thông tin cá nhân</b>
										</div>
										<!-- <div class="col-sm-4" style="height: 200px">
											<div style="height: 160px">
												<img src="images/test.jpg" class="img-responsive center-block"
												id="img-member" alt="iphone x">
											</div>
											<input type="file" name="inp-chofile" id="inp-chofile">
										</div> -->
								
								<logic:present name="profileForm" property="memberInfor">
											<bean:define id="member" property="memberInfor" name="profileForm" />
										</logic:present>
										
								<html:form styleClass="col-sm-4" style="height: 200px"
									action="fileUploadAction" method="post"
									styleId="submit-member-img" enctype="multipart/form-data">
									<div style="height: 160px">
										<img id="img-member" class="img-responsive center-block"
									src="/Auction/images/member/<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="image"/></logic:present>"
											alt="your image" />
									</div>
									<html:file property="file" styleId="inp-chofile"
										onchange="readURL(this);" />
									<br />
								</html:form>
							
										<div class="col-sm-8">
											<div class="row">
												<div class="col-sm-6">
													<div class="form-group">
														<label for="email">Họ tên</label> <input type="text" name = "<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="memberID"/></logic:present>"
														class="form-control" id="inp-membername" value="<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="memberName"/></logic:present>">
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group">
														<label for="email">Ngày sinh</label> <input type="text"
														class="form-control" id="inp-birthdate" value="<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="birthDate"/></logic:present>">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-12">
													<label for="email">Địa chỉ</label>
													<textarea class="form-control" rows="3" id="inp-address"><logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="address"/></logic:present></textarea>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-sm-4">
											<div class="form-group">
												<label for="email">Số CMND</label> <input type="text"
												class="form-control" id="inp-identication" value="<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="identication"/></logic:present>">
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label for="email">Email</label> <input type="text"
												class="form-control" id="inp-email" value="<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="email"/></logic:present>">
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label for="email">Số điện thoại</label> <input type="text"
												class="form-control" id="inp-phone" value="<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="phone"/></logic:present>">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="form-group">
												<button type="button" id = "btn-update-profile" class="btn btn-info btn-block">Update</button>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="row" style="margin-bottom: 2%">
											<b style="margin-left: 3%;">2. Thông tin tài khoản</b>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<div class="form-horizontal">
												  <div class="form-group">
												    <label class="control-label col-sm-2" for="email">Tên tài khoản:</label>
												    <div class="col-sm-10">
												      <input type="email" class="form-control" id="inp-username" placeholder="Enter email" value="<logic:present name="profileForm" property="memberInfor"><bean:write name="member" property="userName"/></logic:present>">
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="control-label col-sm-2" for="pwd">Mật khẩu :</label>
												    <div class="col-sm-10"> 
												      <input type="password" class="form-control" id="inp-password" placeholder="Enter password" value="<logic:present name="profileForm" property="adminFlag"><bean:write name="member" property="password"/></logic:present>">
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="control-label col-sm-2" for="pwd">Mật khẩu mới:</label>
												    <div class="col-sm-10"> 
												      <input type="password" class="form-control" id="inp-newpassword" placeholder="Enter password">
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="control-label col-sm-2" for="pwd">Xác nhận:</label>
												    <div class="col-sm-10"> 
												      <input type="password" class="form-control" id="inp-confirmpassword" placeholder="Enter password">
												    </div>
												  </div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group"> 
											<div class="col-sm-12">
												<button type="button" id = "btn-changepassword" class="btn btn-default btn-block">Đổi mật khẩu</button>
											</div>
										</div>
									</div>
								</div>
								<!-- ending user infor gr -->


							</div>
						</div>
					</div>
				</div>								
							<!-- /#page-wrapper -->
			</div>


					<script type="text/javascript">
						$(document).ready(function() {
							
							if($('#ul-user-list').length == 0){
								
								$('#div-member-infor').removeClass('col-sm-9').addClass('col-sm-offset-1 col-sm-10');
								$('#inp-password').prop('type', 'text');
							}
							
							$('#btn-changepassword').click(function(){
								
								var password = $('#inp-password').val();
								var newPassword = $('#inp-newpassword').val();
								var confirmPassword = $('#inp-confirmpassword').val();

			                    $.ajax({
			                        type: "POST",
			    					async: false,
			                        url: "/Auction/ajaxChangePasswordAction.do",

			                        data: "password="+password+"&newPassword="+newPassword+"&confirmPassword="+confirmPassword+"",
			    					
			                        cache:false,
			    					dataType:'json',

			                        success: function(data) {
									
										var object = data;
										
										switch(object.signal)
										{
										case 'error':
											alert(object.message);
											break;
											
										case 'success':
											alert(object.message);
											window.location = window.location;
											break;
										}
			                        },
			                        error: function(e){
			                        	alert('error');
			                        }
			                    });

							})
							
							$('#btn-update-profile').click(function(){
								
								var memberID = $('#inp-membername').attr('name');
								var memberName = $('#inp-membername').val();
								var birthDate = $('#inp-birthdate').val();
								var address = $('#inp-address').val();
								var identication = $('#inp-identication').val();
								var email = $('#inp-email').val();
								var phone = $('#inp-phone').val();
								var image = 'fix-img';

			                    $.ajax({
			                        type: "POST",
			    					async: false,
			                        url: "/Auction/ajaxEditMemberInforAction.do",

			                        data: "memberID="+memberID+"&memberName="+memberName+"&birthDate="+birthDate+"&address="+address+"&identication="+identication+"&email="+email+"&phone="+phone+"&image="+image+"",
			    					
			                        cache:false,
			    					dataType:'json',

			                        success: function(data) {
									
										var object = data;
										
										switch(object.signal)
										{
										case 'error':
											alert(object.message);
											break;
											
										case 'success':
											alert(object.message);
											window.location = window.location;
											break;
										}
			                        },
			                        error: function(e){
			                        	alert('error');
			                        }
			                    });
			                    requestImg("member"+memberID,"member","profile");
							})

						});
					</script>

					<script type="text/javascript">
						$(document).ready(function() {
							$("#inp-chofile").change(function(e) {
								var fileName = e.target.files[0].name;
								alert('The file "' + fileName + '" has been selected.');
								$('#my_image').attr('src', 'second.jpg');
								previewFile();
							});
						});
					</script>

					<script type="text/javascript">
						function previewFile() {
							var preview = document.querySelector('#img-product');
							var file = document.querySelector('#inp-chofile').files[0];
							var reader = new FileReader();

							reader.addEventListener("load", function() {
								preview.src = reader.result;
							}, false);

							if (file) {
								reader.readAsDataURL(file);
							}
						}
					</script>
			
				<script type="text/javascript">
					function requestImg(imgName,categoryImg,forwardUrl){
						var actionName = "fileUploadAction.do?fileName="+imgName+"&categoryImg="+categoryImg+"&forwardUrl="+forwardUrl;
						$('#submit-member-img').attr('action',actionName );
						$( "#submit-member-img" ).submit()
					}
					
					</script>
				</body>
				</html>