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
						<center><h3><b>ĐĂNG KÝ THÀNH VIÊN ĐẤU GIÁ</b></h3></center>
					</span>
				</div>
			</div>
			<div class="row" style="margin-top: 1%">
				<div class="col-sm-offset-3 col-sm-6">

					<div>
						<div class="form-group">
							<div class="row">

								<html:form styleClass="col-sm-5" action="fileUploadAction" method="post"
									styleId="submit-member-img" enctype="multipart/form-data">
									<img id="img-member" class="img-responsive center-block"
										alt="your image" />
									<html:file property="file" styleId="inp-chofile"
										onchange="readURL(this);" />
									<br />
								</html:form>
								<div class="col-sm-7">
									<label for="email">Họ tên</label>
									<input type="email" class="form-control" id="memberName">
									<label for="email">Ngày sinh</label>
									<input type="email" class="form-control" id="birthDate">
									<label for="email">Số điện thoại</label>
									<input type="email" class="form-control" id="phone">
									<label for="email">Email</label>
									<input type="email" class="form-control" id="email">
									<label for="email">Số CMND</label>
									<input type="email" class="form-control" id="identication">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="email">Địa chỉ</label>
							 <textarea class="form-control" rows="3" id="address"></textarea>
						</div>
						<div class="form-group">
							<label for="email">Tên tài khoản</label>
							<input type="email" class="form-control" id="userName">
						</div>
						<div class="form-group">
							<label for="email">Mật khẩu</label>
							<input type="email" class="form-control" id="password">
						</div>
						<div class="form-group">
							<label for="email">Xác nhận</label>
							<input type="email" class="form-control" id="confirmPassword">
						</div>
						<center>
							<button type="button" id="btn-register" class="btn btn-default">Đăng ký</button>
							<button type="button" id="btn-clear" class="btn btn-default">Clear</button>
						</center>
					</div>
				</div>
			</div>

		</div>

	</div>

</body>

					<script type="text/javascript">
						$(document).ready(function() {
							
							$('#btn-register').click(function(){
								
								var memberName = $('#memberName').val();
								var birthDate = $('#birthDate').val();
								var address = $('#address').val();
								var identication = $('#identication').val();
								var email = $('#email').val();
								var phone = $('#phone').val();
								var memberImg = 'abc';
								
								var userName = $('#userName').val();
								var password= $('#password').val();
								var confirmPassword= $('#confirmPassword').val();
								
								var create = "create";
								
			                    $.ajax({
			                        type: "POST",
			    					async: false,
			                        url: "/Auction/register.do",

			                        data: "&create="+create+"&memberName="+memberName+"&birthDate="+birthDate+"&address="+address+"&identication="+identication+"&email="+email+"&phone="+phone+"&memberImg="+memberImg+"&userName="+userName+"&password="+password+"&confirmPassword="+confirmPassword+"",
			    					
			                        cache:false,
			    					dataType:'json',

			                        success: function(data) {
									
										var object = data;
										
										switch(object.signal)
										{
										case 'error':
											alert(object.message);
											return;
											
										case 'success':
											alert(object.message);
										//	var currentLocation = window.location;
										//	window.location = window.location;
											//window.location = currentLocation;
											break;
										}
			                        },
			                        error: function(e){
			                        	alert('error');
			                        	return;
			                        }
			                    });
			                    
			                    requestImg("memberNew","member","register");
			                    
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

						var preview = document.querySelector('#img-member');
						
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
</html>