<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>解绑电表</title>
<link rel="stylesheet" href="../resources/css/weui.css?version=1" />
<style type="text/css">
html, body {
	height: 100%;
	width: 100%;
}

.top {
	height: 17%;
}

.bottom {
	height: 83%;
	overflow-y: auto;
}

.table_title {
	text-align: right;
}
</style>
<script type="text/javascript"
	src="../resources/js/jquery-1.11.3.js?version=1.0"></script>
<script type="text/javascript" src="../resources/js/my.js?version=1.0"></script>
<script type="text/javascript">
	$(function() {
		$('#btnUnBind').click(function() {
			var checkArr = $("input:checkbox:checked");
			var codes = "";
			$.each(checkArr, function(i, e) {
				codes += $(this).attr("id");
				codes += ",";
			});
			if (codes == null || codes == "") {
				alert('请选择您要解绑的电表。');
				return;
			}
			var json = {
				codes : codes
			};
			$.ajax({
				url : '<c:url value="/wap/commitUnBindAmmeters"/>',
				type : 'POST',
				data : json,
				success : function(data) {
					window.location.href = '<c:url value="/wap/unbindAmmeter"/>';
				}
			});
		});

	});
</script>
</head>
<body>
	<div class="top">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="weui_cells">
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<a class="weui_btn weui_btn_mini weui_btn_primary" id="btnUnBind">解绑</a>
				</div>
			</div>
		</div>
	</div>
	<div class="bottom">
		<div class="weui_cells weui_cells_checkbox ">
			<c:forEach var="item" items="${info.ammeters}">
				<label class='weui_cell weui_check_label' for='${item.code}'>
					<div class='weui_cell_hd'>
						<input type='checkbox' class='weui_check' name='checkbox11'
							id='${item.code}'><i class='weui_icon_checked'></i>
					</div>
					<div class='weui_cell_bd weui_cell_primary'>
						<p>${item.code}${item.name}</p>
					</div>
				</label>
			</c:forEach>
			<!-- <label class='weui_cell weui_check_label' for='s11'>
				<div class='weui_cell_hd'>
					<input type='checkbox' class='weui_check' name='checkbox11'
						id='s11'><i class='weui_icon_checked'></i>
				</div>
				<div class='weui_cell_bd weui_cell_primary'>
					<p>standard is dealt for u.</p>
				</div>
			</label> -->
		</div>
	</div>
</body>
</html>