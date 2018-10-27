<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%--  <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> --%>
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
<link href="management/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

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
					</ul></li>
			</ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li><a href="#"><i class="fa fa-home"></i> HOME</a></li>
					<li><a href="auctionInfor.do"><i
							class="glyphicon glyphicon-user"></i> Mặt hàng</a></li>
					<li><a href="profile.do"><i
							class="glyphicon glyphicon-comment"></i> Profile</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
		<!-- Ending navbar -->

		<!-- Starting page-wrapper -->
		<div id="page-wrapper" class="block">
			<!--style="height: 93vh; width: 99%"  -->
			<div class="panel-group">

				<div class="panel-body">

					<div class="controls">
						<html:form styleClass="form-inline" action="/auctionInfor.do"
							method="get" acceptCharset="UTF-8" style="margin-left: 1%">

							<div class="form-group" style="margin-right: 2%">
								<label for="email">Ngày đăng tải</label> <input
									class="form-control" id="post-time" name="postDateSearch"
									placeholder="YYYY-MM-DD" type="date">
							</div>
							<div class="form-group" style="width: 21%; margin-right: 2%;">
								<label for="pwd">Tình trạng</label> <select class="form-control"
									name="statusSearch" id="sel-status" style="width: 61%;">
									<option value="">tình trạng :</option>
									<option value="1">chưa bắt đầu</option>
									<option value="2">Đang đấu giá</option>
									<option value="3">Hết thời gian (có người mua)</option>
									<option value="4">Hết thời gian (0 người mua)</option>
									<option value="5">Đã dừng</option>
								</select>
							</div>
							<div class="form-group" style="width: 27%; margin-right: 2%;">
								<label for="pwd">Tên mặt hàng</label> <input type="text"
									class="form-control" name="productNameSearch"
									id="inp-search-product-name" style="width: 60%;">
							</div>
							<div class="form-group" style="margin-right: 2%">
								<button type="submit" name="searchButton" value="search"
									class="btn btn-info">Search</button>
								<button type="button" class="btn btn-default">Clear</button>
							</div>
						</html:form>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<!-- starting list product= -->
							<div class="row">
								<b style="margin-left: 2%; width: 100%">DANH SÁCH MẶT HÀNG</b>
								<ul class="list-group" id="ul-product-list">
									<logic:iterate name="auctionInforForm" property="productList"
										id="product">
										<li class="list-group-item"><a
											href="?productID=<logic:present name="product" property="productID"><bean:write name="product" property="productID"/></logic:present>">
												<strong> <logic:present name="product"
														property="productName">
														<bean:write name="product" property="productName" />
													</logic:present>
											</strong>
										</a></li>
									</logic:iterate>
								</ul>
							</div>
							<!-- ending list post= -->
						</div>
						<div class="col-sm-9">

							<logic:present name="auctionInforForm"
								property="detailAuctionInforBean">
								<bean:define id="attrAuctionInfor"
									property="detailAuctionInforBean" name="auctionInforForm" />
							</logic:present>

							<!-- starting product infor gr -->
							<div class="row">
								<div class="row" style="margin-bottom: 2%">
									<b style="margin-left: 3%;">1. Thông tin sản phẩm</b>
								</div>
								<div class="col-sm-4" style="height: 200px">
									<div style="height: 160px">
										<html:form action="fileUploadAction?fileName=abc.jpg" method="post"
											styleId="submit-product-img" enctype="multipart/form-data">
											<img id="img-product" class="img-responsive center-block"
												 src="/Auction/images/products/<logic:present name="attrAuctionInfor" property="productImgPath"><bean:write name="attrAuctionInfor" property="productImgPath"/></logic:present>"
												alt="your image" 
												
												/>
											<html:file property="file" styleId="inp-chofile"
												onchange="readURL(this);" />
											<br />
										</html:form>
									</div>
								</div>
								<%-- 	<div style="height: 160px">
												<img src="/images/<logic:present name="attrAuctionInfor" property="productImgPath"><bean:write name="attrAuctionInfor" property="productImgPath"/></logic:present>" class="img-responsive center-block"
												id="img-product" alt="iphone x">
											</div>
											<input type="file" name="inp-chofile" id="inp-chofile">
										</div> --%>
								<div class="col-sm-8">
									<div class="row">
										<div class="col-sm-6">
											<div class="form-group">
												<label for="email">Tên mặt hàng</label> <input type="text"
													id="inp-product-name" class="form-control"
													name="<logic:present name="attrAuctionInfor" property="productID"><bean:write name="attrAuctionInfor" property="productID"/></logic:present>"
													value="<logic:present name="attrAuctionInfor" property="productName"><bean:write name="attrAuctionInfor" property="productName"/></logic:present>">
											</div>
										</div>
										<div class="col-sm-6">
											<div class="form-group">
												<label for="pwd">Loại</label> <select class="form-control"
													id="inp-category"
													name="<logic:present name="attrAuctionInfor" property="categoryID"><bean:write name="attrAuctionInfor" property="categoryID"/></logic:present>">
													<option value="">Chọn</option>
													<option value="1">Nhà đất</option>
													<option value="2">Điện tử</option>
													<option value="3">Hàng tiêu dùng</option>
													<option value="4">Khác</option>
												</select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<label for="email">Mô tả chi tiết</label>
											<textarea class="form-control" rows="3" id="inp-describe"><logic:present
													name="attrAuctionInfor" property="productDescribe">
													<bean:write name="attrAuctionInfor"
														property="productDescribe" />
												</logic:present></textarea>
										</div>
									</div>
								</div>
							</div>
							<!-- ending product infor gr -->

							<!-- starting auction post gr -->
							<div class="row">
								<div class="row" style="margin-bottom: 2%">
									<b style="margin-left: 3%;">2. Thông tin đăng tải</b>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="email">TG bắt đầu</label> <input type="text"
											placeholder="YYYY-MM-DD hh:mm:ss" class="form-control"
											id="inp-start-time"
											value="<logic:present name="attrAuctionInfor" property="startTime"><bean:write name="attrAuctionInfor" property="startTime"/></logic:present>">
									</div>
									<div class="form-group">
										<label for="pwd">TG kết thúc</label> <input type="text"
											placeholder="YYYY-MM-DD hh:mm:ss" class="form-control"
											id="inp-end-time"
											value="<logic:present name="attrAuctionInfor" property="endTime"><bean:write name="attrAuctionInfor" property="endTime"/></logic:present>">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="email">Giá bắt đầu</label> <input type="text"
											class="form-control" id="inp-start-price"
											value="<logic:present name="attrAuctionInfor" property="startingPrice"><bean:write name="attrAuctionInfor" property="startingPrice"/></logic:present>">
									</div>
									<div class="form-group">
										<label for="pwd">Bước giá</label> <input type="text"
											class="form-control" id="inp-step-price"
											value="<logic:present name="attrAuctionInfor" property="stepPrice"><bean:write name="attrAuctionInfor" property="stepPrice"/></logic:present>">
									</div>
								</div>
								<div class="col-sm-4">
									<div class="form-group">
										<label for="email">Giá thị trường</label> <input type="text"
											class="form-control" id="inp-market-price"
											value="<logic:present name="attrAuctionInfor" property="marketPrice"><bean:write name="attrAuctionInfor" property="marketPrice"/></logic:present>">
									</div>
									<div class="form-group">
										<label for="pwd">Tình trạng</label> <input type="text"
											class="form-control" id="inp-status"
											value="<logic:present name="attrAuctionInfor" property="status"><bean:write name="attrAuctionInfor" property="status"/></logic:present>">
									</div>
								</div>
							</div>
							<!-- eding auction post gr -->

							<!-- starting order gr -->
							<div class="row" id="div-end-auction-info">
								<div class="row" style="margin-bottom: 2%">
									<b style="margin-left: 3%;">3. Thông tin kết thúc đấu giá</b>
								</div>
								<div class="col-sm-12">
									<div class="form-group">
										<label for="email">Member thắng đấu giá</label> <input
											type="text" class="form-control" id="inp-winner-auction"
											value="<logic:present name="attrAuctionInfor" property="winnerAuction"><bean:write name="attrAuctionInfor" property="winnerAuction"/></logic:present>">
									</div>
									<div class="form-group">
										<label for="pwd">Số tiền</label> <input type="text"
											class="form-control" id="input-highest-price"
											value="<logic:present name="attrAuctionInfor" property="highestPrice"><bean:write name="attrAuctionInfor" property="highestPrice"/></logic:present>">
									</div>
								</div>
							</div>
							<!-- ending order gr -->

							<!-- starting event gr -->
							<div class="row">
								<div class="row" style="margin-bottom: 2%">
									<b style="margin-left: 3%;">4. Action</b>
								</div>
								<div class="row">
									<div class="col-sm-2"></div>
									<div class="col-sm-10">
										<label for="pwd" class="radio-inline"><b>Select
												mode</b></label> <label class="radio-inline"><input type="radio"
											name="radioSelectmode" id="lab-radio-view" value="view"
											checked>Xem</label> <label class="radio-inline"><input
											type="radio" name="radioSelectmode" id="lab-radio-update"
											value="update">Sửa</label> <label class="radio-inline"><input
											type="radio" name="radioSelectmode" id="lab-radio-add"
											value="add">Thêm</label>
									</div>
								</div>

								<div class="row" style="margin-top: 20px">
									<div class="col-sm-2"></div>
									<div class="col-sm-10">
										<button type="button" class="btn btn-danger btn-width"
											id="btn-delete">Xóa</button>
										<button type="button" class="btn btn-warning btn-width"
											id="btn-stop-auction">Dừng</button>
										<button type="button" class="btn btn-info btn-width"
											id="btn-update">Sửa</button>
										<button type="button" class="btn btn-default btn-width"
											id="btn-add">Thêm</button>
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
						$(document).ready(function() {
							
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

								} else if (this.value == 'update') {

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

								} else if (this.value == 'add') {

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

						function enableElements(elementIDarr) {

							disableElements();

							var i, len;
							for (i = 0, len = elementIDarr.length; i < len; i++) {

								var element = document.getElementById(elementIDarr[i]);

								var tagName = element.tagName;

								switch (tagName) {
									case 'BUTTON':
									$("#" + elementIDarr[i]).removeClass('disabled');
									break;

									case 'INPUT':
									$("#" + elementIDarr[i]).prop('disabled', false);
									break;

									case 'TEXTAREA':
									$("#" + elementIDarr[i]).prop('disabled', false);
									break;

									case 'SELECT':
									$("#" + elementIDarr[i]).prop('disabled', false);
									break;
								}
							}
						}

						function disableElements() {

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
					$(document).ready(function() {
						
						updateUrlProduct();
						
						//  set select for category product
						var categoryIDofProduct = $("#inp-category").attr("name");
						$("#inp-category").val(categoryIDofProduct);
						
						// load image
						$("#inp-chofile").change(function(e) {
							var fileName = e.target.files[0].name;
							alert('The file "' + fileName + '" has been selected.');
							$('#my_image').attr('src', 'second.jpg');
							previewFile();
							});
							

						$("#btn-add").click(function(e) {

							var productName = $("#inp-product-name").val();
							var productDescrible = $("#inp-describe").val();

							
							var categoryID = $("#inp-category").val();
							var startingPrice = $("#inp-start-price").val();
							var stepPrice = $("#inp-step-price").val();
							var endTime = $("#inp-end-time").val();
							var startTime = $("#inp-start-time").val();
							var marketPrice = $("#inp-market-price").val();

		                    $.ajax({
		                        type: "POST",
		    					async: false,
		                        url: "/Auction/ajaxCreateAuctionAction.do",
		                        data: "productName="+productName+"&productDescribe="+productDescrible+"&categoryID="+categoryID+"&startingPrice="+startingPrice+"&endTime="+endTime+"&startTime="+startTime+"&stepPrice="+stepPrice+"&marketPrice="+marketPrice+"",
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
		                        	break;
		                        }
		                    });
		                    
		                    requestImg("productNew","products","auctionInfor");
						})

						$("#btn-update").click(function(e) {
							
							var productID = $("#inp-product-name").attr('name');
							
							if(productID == '' || productID.lenght == 0){

								alert('Bạn chưa chọn mặt hàng!')
								return;
							}
							
							if(!confirm("Bạn có muốn sửa mặt hàng có ID :"+productID)){
						   		return;
						    } 
						
							var productID = $("#inp-product-name").attr('name');
							var productName = $("#inp-product-name").val();
							var productDescrible = $("#inp-describe").val();
							
							var productImgPath = "product"+productID;
							
							var categoryID = $("#inp-category").val();
							var startingPrice = $("#inp-start-price").val();
							var stepPrice = $("#inp-step-price").val();
							var endTime = $("#inp-end-time").val();
							var startTime = $("#inp-start-time").val();
							var marketPrice = $("#inp-market-price").val();

		                    $.ajax({
		                        type: "POST",
		    					async: false,
		                        url: "/Auction/ajaxEditAuctionAction.do",

		                        data: "productID="+productID+"&productName="+productName+"&productDescribe="+productDescrible+"&productImgPath="+productImgPath+"&categoryID="+categoryID+"&startingPrice="+startingPrice+"&endTime="+endTime+"&startTime="+startTime+"&stepPrice="+stepPrice+"&marketPrice="+marketPrice+"",
		    					
		                        cache:false,
		    					dataType:'json',

		                        success: function(data) {
									
		                        	alert(data);
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
		                    requestImg("product"+productID,"products","auctionInfor");
						})
						
						
						// click delete button
						$("#btn-delete").click(function(e) {
							var productID = $("#inp-product-name").attr('name');
							
							var productID = $("#inp-product-name").attr('name');
							
							var productName = $("#inp-product-name").val();
							
							if(productID == '' || productID.lenght == 0){

								alert('Bạn chưa chọn mặt hàng!')
								return;
							}
							
						    if(!confirm("Bạn có muốn xóa mặt hàng : "+productName)){
						    	
						   		return;
						    } 

						    $.ajax({
		                        type: "POST",
		    					async: false,
		                        url: "/Auction/ajaxDeleteAuctionAction.do",
		                        data: "productID="+productID,
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

						// click stop button
						$("#btn-stop-auction").click(function(e) {
							
							var productID = $("#inp-product-name").attr('name');
							var productName = $("#inp-product-name").val();
							
							if(productID == '' || productID.lenght == 0){

								alert('Bạn chưa chọn mặt hàng!')
								return;
							}
							
						    if(!confirm("Bạn có muốn dừng đăng tải mặt hàng : "+productName)){
						    	
						   		return;
						    } 

						    $.ajax({
		                        type: "POST",
		    					async: false,
		                        url: "/Auction/ajaxStopAuctionAction.do",
		                        data: "productID="+productID,
		    					cache:false,
		    					dataType:'json',

		                        success: function(data) {
									
		                        	alert(data);
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
					});
					</script>


	<script type="text/javascript">

					function updateUrlProduct() {
						
						var currentURL = window.location.href;
						
						var searchButtonString = "&searchButton=search";
						
						if(currentURL.indexOf(searchButtonString) != -1){
							
							$("#ul-product-list").each(function() {
								
								$(this).find('li').each(function() {
									
									$(this).find('a').each(function() {
										
										var productID = $(this).attr('href').replace("?","");

										var currentURL = window.location.href;
										
										var sxz = currentURL.split("&searchButton=search").pop().trim();
										
										if(sxz != '' || sxz.lenght != 0){
											
											currentURL = currentURL.replace(sxz,"");
										}
																		
										var searchButtonString = "&searchButton=search";

										var newURL = currentURL+'&'+productID;
										
										$(this).attr("href", newURL);				

									});
								});
							});
						
						}
					}

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
						$('#submit-product-img').attr('action',actionName );
						$( "#submit-product-img" ).submit()
					}
					
					</script>

</body>
</html>