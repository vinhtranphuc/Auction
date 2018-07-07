<%@page import="common.StringProcess"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Auction</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
	crossorigin="anonymous"></script>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">

</head>
<body>
	<!-- starting container -->
	<div class="container" style="margin-top: 1%">

		<!-- staring panel -->
		<div class="panel panel-default">
			<!-- starting panel body -->
			<div class="panel-body">

				<!-- starting action bar -->
				<div class="row">

					<!-- staring avatar -->
					<div class="col-md-8">
						<div class="row">
							<div class="col-sm-2">
								<img src="img/hulk.jpg" class="img-thumbnail" alt="avatar"
									width="80" height="80">
							</div>
							<div class="col-sm-8">
								<h5 style="margin-left: -7%;">Hulk</h5>
							</div>
						</div>
					</div>
					<!-- ending avatar -->

					<!-- starting button group -->
					<div class="col-sm-4">
						<div class="pull-right" style="margin-top: 3%;">
							<button type="button" class="btn btn-default">Đăng nhập</button>
							<button type="button" class="btn btn-info">Đăng ký</button>
						</div>
					</div>
					<!-- endiung button group -->
				</div>
				<!-- ending action bar -->
				<hr>
				<!-- starting auction list -->
				<div class="row">
					<div class="col-sm-2">
						<h4>Phiếu đấu giá</h4>
					</div>
					<div class="col-sm-3">
						<form class="pull-right">
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="Tìm mặt hàng">
								<div class="input-group-btn">
									<button class="btn btn-default" type="submit">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
					<div class="col-sm-6"></div>
				</div>

				<table class="table table-striped" id="auction-coupon-list">
					<thead>
						<tr>
							<th style="width: 9%">Mặt hàng</th>
							<th style="width: 7%">Chi tiết</th>
							<th style="width: 7%">Giá bắt đầu</th>
							<th style="width: 7%">Giá HT</th>
							<th style="width: 12%">Người SC</th>
							<th style="width: 5%">Bước giá</th>
							<th style="width: 10%">TG bắt đầu</th>
							<th style="width: 10%">TG kết thúc</th>
							<th style="width: 10%">TG còn lại</th>
							<th style="width: 5%">Hủy</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="ac" name="auctionCouponForm"
							property="auctionCouponList">
							<tr>
								<td><bean:write name="ac" property="product" /></td>
								<td>
									<button type="button" class="btn btn-info btn-xs"
										data-toggle="modal" data-target="#modal-detail-product">XemMbid</button>
								</td>
								<td><bean:write name="ac" property="startingPrice" /></td>
								<td><bean:write name="ac" property="highestPrice" /></td>
								<td><bean:write name="ac" property="name" /></td>
								<td><bean:write name="ac" property="stepPrice" /></td>
								<td><bean:write name="ac" property="startTime" /></td>
								<td><bean:write name="ac" property="endTime" /></td>
								<td><span style="color: red"
									id="count-down-<bean:write name="ac" property="productID"/>">
								</span></td>
								<td>
									<button type="button" class="btn btn-danger btn-xs">Hủy
										mbid</button>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				<!-- ending auction list -->
			</div>
			<!-- ending panel body -->

		</div>
		<!-- ending panel -->

	</div>
	<!-- ending container -->

	<!-- Starting hidden tags -->

	<%-- 	<!-- Use js for count down based on current time from server -->

	<logic:iterate id="ac" name="auctionCouponForm"
		property="auctionCouponList">
		<html:hidden name="auctionCouponForm" property="currentTime"></html:hidden>
	</logic:iterate>
	<!-- Ending hidden tags --> --%>

	<!-- Modals -->

	<!-- Starting detail product & order modal -->
	<div class="modal fade" id="modal-detail-product" role="dialog">

		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h6 class="modal-title">
						<b>Chi tiết phiếu đấu giá</b>
					</h6>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-4">
							<img src="img/iphonex.jpg" class="img-thumbnail" alt="iphoneX"
								width="250" height="250">
						</div>
						<div class="col-sm-8">
							<p>Mua điện thoại Apple iPhone X - iPhone 10 chính hãng, bảo
								hành 1 đổi 1 trong 12 tháng tại Hệ thống CellphoneS.com.vn ✓Trả
								góp 0% ✓Giá rẻ hơn ✓Giao iPhone 10 chính hãng, bảo hành 1 đổi 1
								trong 12 tháng tại Hệ thống CellphoneS.com.vn ✓Trả góp iPhone 10
								chính hãng, bảo hành 1 đổi 1 trong 12 tháng tại Hệ thống
								CellphoneS.com.vn ✓Trả góp iPhone 10 chính hãng, bảo hành 1 đổi
								1 trong 12 tháng tại Hệ thống CellphoneS.com.vn ✓Trả góp</p>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="row">
						<div class="col-md-3">
							<div class="form-inline">
								<div class="form-group">
									<b> <span style="color: red;"> 1 day 2h:30m 20s </span>
									</b>
								</div>
							</div>
						</div>
						<div class="col-md-9">
							<div class="form-inline pull-right">
								<div class="form-group">
									<input type="text" class="form-control" id="pwd"
										placeholder="Nhập giá">
								</div>
								<button class="btn btn-primary">Đặt giá</button>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- Ending detail product & order modal  -->
	<!-- End modals -->

	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<!-- process countdown timer -->

	<script type="text/javascript">
		window.addEventListener('load', function() {

			// get auction list
			var table = document.getElementById("auction-coupon-list");

			// loop each row in table
			for (var i = 1, row; row = table.rows[i]; i++) {

				// get end time for each row
				var end_time = row.cells[7].innerHTML;

				// get id name of span
				var span_id = row.cells[8].getElementsByTagName("span")[0]
						.getAttribute("id");

				// process display countdown time
				countDownTimer(span_id, end_time);
			}

			// loop for call ajax
			for (i = 0; i < 2; i++) { 

				$.ajax({
					type : "POST",
					async : false,
					url : "/Auction/auctionCouponAjax.do",
					data : "checkStopAuction=true",
					cache : false,
					dataType : 'json',
					success : function(data) {
						//chuyển đổi JSONObject -> JSON
						var obj = data.obj;
						for ( var key in obj) {
							if(obj[key].stopAuctionFlag == true) {
								alert(obj[key].productID);
							}	
			/* 				rs += "<tr>" + "<td>" + "" + obj[key].maCongViec
									+ "" + "</td>" + "<td>" + ""
									+ obj[key].tenCongViec + "" + "</td>"
									+ "<td>" + "" + obj[key].tenNhanVien + ""
									+ "</td>" + "</tr>" */
						}

					},
					error : function(e) {
						/* alert('loi '+e); */
					}
				});
			}

		}, false);

		// function process display countdown time to span
		function countDownTimer(element_id, end_time) {

			// Set the date we're counting down to
			var countDownDate = new Date(end_time).getTime();

			// Update the count down every 1 second
			var x = setInterval(function() {

				// Get todays date and time
				var now = new Date().getTime();

				// Find the distance between now an the count down date
				var distance = countDownDate - now;

				// Time calculations for days, hours, minutes and seconds
				var days = Math.floor(distance / (1000 * 60 * 60 * 24));
				var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
						/ (1000 * 60 * 60));
				var minutes = Math.floor((distance % (1000 * 60 * 60))
						/ (1000 * 60));
				var seconds = Math.floor((distance % (1000 * 60)) / 1000);

				// Output the result in an element with id="demo"
				document.getElementById(element_id).innerHTML = days + "d "
						+ hours + "h " + minutes + "m " + seconds + "s ";

				// If the count down is over, write some text 
				if (distance < 0) {
					clearInterval(x);
					document.getElementById(element_id).innerHTML = "Hết giờ";
				}
			}, 1000);
		}
	</script>
</body>
</html>