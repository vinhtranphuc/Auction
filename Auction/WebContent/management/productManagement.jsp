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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<!-- Custom CSS -->
	<link href="css/sb-admin.css" rel="stylesheet">

	<!-- Custom Fonts -->
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<!-- /#wrapper -->
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Post management</a>
			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> VinhTP1<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li>
							<a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
						</li>
					</ul>
				</li>
			</ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li>
						<a href="#"><i class="fa fa-home"></i> HOME</a>
					</li>
					<li>
						<a href="productManagement.html"><i class="glyphicon glyphicon-user"></i> Mặt hàng</a>
					</li>
					<li>
						<a href="profileManagement.html"><i class="glyphicon glyphicon-comment"></i> Profile</a>
					</li>  
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
		<!-- Ending navbar -->

		<!-- Starting page-wrapper -->
		<div id="page-wrapper" style="height: 93vh; width: 99%">
			<div class="panel-group">
				<div class="panel-body">
					<div class="messages"></div>

					<div class="controls">
						<form class="form-inline" action="/auctionInfor.do" style="margin-left: 1%">
							<div class="form-group" style="margin-right: 2%">
								<label for="email">Ngày đăng tải</label>
								<input class="form-control" id="post-time" placeholder="MM/DD/YYY" type="date"/>
							</div>
							<div class="form-group" style="margin-right: 2%">
								<label for="pwd">Tình trạng</label>
								<select class="form-control" id="sel-status">
									<option>Js</option>
									<option>Java</option>
									<option>Python</option>
									<option>AngularJS</option>
								</select>
							</div>
							<div class="form-group" style="margin-right: 2%">
								<label for="pwd">Tên mặt hàng</label>
								<input type="text" class="form-control" id="inp-search-product-name">
							</div>
							<button type="submit" class="btn btn-info">Search</button>
							<button type="submit" class="btn btn-default">Clear</button>
						</form>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<!-- starting list product= -->
							<div class="row" style="margin-left: 2%; margin-right: 2%">
								<b style="margin-left: 2%; width: 100%">DANH SÁCH MẶT HÀNG</b>
								<ul class="list-group" id="ul-product-list">
									<li class="list-group-item"><a href="#"><strong> Đất ABC</strong></a>
									</li>
								</ul>
							</div>
							<!-- ending list post= -->
						</div>
						<div class="col-sm-9">
							<!-- starting product infor gr -->
							<div class="row" >
								<div class="row" style="margin-bottom: 2%"><b>1. Thông tin sản phẩm</b></div>
								<div class="col-sm-4" style="height: 200px">
									<div style="height: 160px">
										<img src="images/test.jpg" class="img-responsive center-block" id = "img-product" alt="iphone x">
									</div>
									<input type="file" name="inp-chofile" id = "inp-chofile">
								</div>
								<div class="col-sm-8">
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label for="email">Tên mặt hàng</label>
												<input type="text" class="form-control" id="inp-product-name">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label for="pwd">Loại</label>
												<input type="text" class="form-control" id="inp-category">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<label for="email">Mô tả chi tiết</label>
											<textarea class="form-control" rows="3" id="inp-describe"></textarea> 
										</div>
									</div>
								</div>
							</div>
							<!-- ending product infor gr -->

							<!-- starting auction post gr -->
							<div class="row">
								<div class="row" style="margin-bottom: 2%"><b>2. Thông tin đăng tải</b></div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="email">TG bắt đầu</label>
										<input type="text" class="form-control" id="inp-start-time">
									</div>
									<div class="form-group">
										<label for="pwd">TG kết thúc</label>
										<input type="text" class="form-control" id="inp-end-time">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="email">Giá bắt đầu</label>
										<input type="text" class="form-control" id="inp-start-price">
									</div>
									<div class="form-group">
										<label for="pwd">Bước giá</label>
										<input type="text" class="form-control" id="inp-step-price">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="email">Giá thị trường</label>
										<input type="text" class="form-control" id="inp-market-price">
									</div>
									<div class="form-group">
										<label for="pwd">Tình trạng</label>
										<input type="text" class="form-control" id="inp-status">
									</div>
								</div>
							</div>
							<!-- eding auction post gr -->

							<!-- starting order gr -->
							<div class="row" id="div-end-auction-info">
								<div class="row" style="margin-bottom: 2%"><b>3. Thông tin kết thúc đấu giá</b></div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="email">Member thắng đấu giá</label>
										<input type="text" class="form-control" id="inp-winner-auction">
									</div>
									<div class="form-group">
										<label for="pwd">Số tiền</label>
										<input type="text" class="form-control" id="input-highest-price">
									</div>
								</div>
								<div class="col-sm-8">
									<label for="email">Gửi tin nhắn</label>
									<textarea class="form-control" rows="3" id="txtare-messsage"></textarea>
									<button type="button" class="btn btn-info btn-width" id="btn-send-message">Gửi</button>
									<button type="button" class="btn btn-default btn-width" id="btn-clear-message">Clear</button>
								</div>
							</div>
							<!-- ending order gr -->
							<!-- starting event gr -->
							<div class="row">
								<div class="row" style="margin-bottom: 2%"><b>4. Action</b></div>
								<div class="row">
									<div class="col-sm-2">
									</div>
									<div class="col-sm-10">
										<label for="pwd" class="radio-inline"><b>Select mode</b></label>
										<label class="radio-inline"><input type="radio" name="radioSelectmode" id="lab-radio-view" value = "view" checked>Xem</label>
										<label class="radio-inline"><input type="radio" name="radioSelectmode" id="lab-radio-update" value = "update">Sửa</label>
										<label class="radio-inline"><input type="radio" name="radioSelectmode" id="lab-radio-add" value = "add">Thêm</label>
									</div>
								</div>

								<div class="row" style="margin-top: 20px">
									<div class="col-sm-2">
									</div>
									<div class="col-sm-10">
										<button type="button" class="btn btn-danger btn-width" id="btn-delete">Xóa</button>
										<button type="button" class="btn btn-warning btn-width" id="btn-stop-auction">Dừng</button>
										<button type="button" class="btn btn-info btn-width"  id="btn-update">Sửa</button>
										<button type="button" class="btn btn-default btn-width" id="btn-add">Thêm</button>
									</div>
								</div>
							</div>
							<!-- ending event gr -->
						</div>


					</div>  
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>


	<script type="text/javascript">
		$(document).ready(function(){

			disableElements();

			var elementIDarr = [];
			elementIDarr.push("btn-delete");
			elementIDarr.push("btn-stop-auction");
			enableElements(elementIDarr);
			elementIDarr = [];

			$('input[type=radio][name=radioSelectmode]').change(function() {
				if (this.value == 'view') {

					elementIDarr.push("btn-delete");
					elementIDarr.push("btn-stop-auction");

					enableElements(elementIDarr);
					elementIDarr = [];

				}
				else if (this.value == 'update') {

					elementIDarr.push("btn-update");

					elementIDarr.push("inp-product-name");
					elementIDarr.push("inp-describe");
					elementIDarr.push("inp-category");
					elementIDarr.push("inp-start-time");
					elementIDarr.push("inp-end-time");
					elementIDarr.push("inp-start-price");
					elementIDarr.push("inp-step-price");
					elementIDarr.push("inp-market-price");

					enableElements(elementIDarr);
					elementIDarr = [];
					
				}else if (this.value == 'add') {

					elementIDarr.push("btn-add");

					elementIDarr.push("inp-product-name");
					elementIDarr.push("inp-describe");
					elementIDarr.push("inp-category");
					elementIDarr.push("inp-start-time");
					elementIDarr.push("inp-end-time");
					elementIDarr.push("inp-start-price");
					elementIDarr.push("inp-step-price");
					elementIDarr.push("inp-market-price");

					enableElements(elementIDarr);
					elementIDarr = [];
					
				}
			});
		});

		function enableElements(elementIDarr){

			disableElements();

			var i, len;
			for (i = 0, len = elementIDarr.length; i < len; i++) {

				var element = document.getElementById(elementIDarr[i]);

				var tagName = element.tagName;

				switch(tagName) {
					case 'BUTTON':
						$("#"+elementIDarr[i]).removeClass('disabled');
						break;
					case 'INPUT':
						$("#"+elementIDarr[i]).prop('disabled', false);
						break;
					case 'TEXTAREA':
						$("#"+elementIDarr[i]).prop('disabled', false);
						break;
				}
			}
		}

		function disableElements(){

			$("#btn-add").addClass('disabled');
			$("#btn-update").addClass('disabled');
			$("#btn-stop-auction").addClass('disabled');
			$("#btn-delete").addClass('disabled');
			$("#btn-send-message").addClass('disabled');
			$("#btn-clear-message").addClass('disabled');

			$("#inp-product-name").prop('disabled', true);
			$("#inp-describe").prop('disabled', true);
			$("#inp-category").prop('disabled', true);
			$("#inp-start-time").prop('disabled', true);
			$("#inp-end-time").prop('disabled', true);
			$("#inp-start-price").prop('disabled', true);
			$("#inp-step-price").prop('disabled', true);
			$("#inp-market-price").prop('disabled', true);
			$("#inp-status").prop('disabled', true);
			$("#inp-winner-auction").prop('disabled', true);
			$("#input-highest-price").prop('disabled', true);
			$("#txtare-messsage").prop('disabled', true);
		}

	</script>

	<script type="text/javascript">
		$(document).ready(function(){
			$("#inp-chofile").change(function(e){
				var fileName = e.target.files[0].name;
				alert('The file "' + fileName +  '" has been selected.');
				$('#my_image').attr('src','second.jpg');
				previewFile();
			});
		});
	</script>

	<script type="text/javascript">
		function previewFile() {
			var preview = document.querySelector('#img-product');
			var file    = document.querySelector('#inp-chofile').files[0];
			var reader  = new FileReader();

			reader.addEventListener("load", function () {
				preview.src = reader.result;
			}, false);

			if (file) {
				reader.readAsDataURL(file);
			}
		}
	</script>

</body>
</html>