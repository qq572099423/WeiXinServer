<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>我的电表</title>
<link rel="stylesheet" href="../resources/css/weui.css" />
<script type="text/javascript"
	src="../resources/js/jquery-1.11.3.js?version=1.0"></script>
<script type="text/javascript" src="../resources/js/my.js?version=1.0"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="weui_cells_title">所有已绑定的电表</div>
	<div class="weui_cells weui_cells_access">
		<c:forEach var="item" items="${info.ammeters}">
			<a class="weui_cell" href="javascript:;">
				<div class="weui_cell_bd weui_cell_primary">
					<p>${item.code} ${item.name}</p>
				</div>
				<div class="weui_cell_ft"></div>
			</a>
		</c:forEach>
		<!-- <a class="weui_cell" href="javascript:;">
			<div class="weui_cell_bd weui_cell_primary">
				<p>9464002 商铺电表</p>
			</div>
			<div class="weui_cell_ft"></div>
		</a> -->
	</div>
</body>
</html>