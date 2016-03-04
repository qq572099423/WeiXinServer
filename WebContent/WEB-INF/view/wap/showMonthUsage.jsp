<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>电表月用量</title>
<link rel="stylesheet" href="../resources/css/weui.css" />
<script type="text/javascript"
	src="../resources/js/jquery-1.11.3.js?version=1.0"></script>
<script type="text/javascript" src="../resources/js/my.js?version=1.0"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="weui_cells_title">电表月用量</div>
	<div class="weui_cells">
		<c:forEach var="item" items="${resultList}">
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<p>${item.amm_code}</p>
				</div>
				<div class="weui_cell_ft">${item.data}</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>