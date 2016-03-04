<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<title>绑定电表</title>
<link rel="stylesheet" href="../resources/css/weui.css?version=1.0" />
<script type="text/javascript"
	src="../resources/js/jquery-1.11.3.js?version=1.0"></script>
<script type="text/javascript" src="../resources/js/my.js?version=1.0"></script>
<script type="text/javascript">
	$(function() {
		$('#commit').addClass('weui_btn_disabled');
		$('#codes').keyup(function() {
			if ($('#codes').val() == "") {
				$('#commit').addClass('weui_btn_disabled');
			} else {
				$('#commit').removeClass('weui_btn_disabled');
			}
		});
		$('#commit').click(function() {
			var json = {
				codes : $('#codes').val()
			};
			$.ajax({
				url : '<c:url value="/wap/commitBindAmmeters"/>',
				type : 'POST',
				data : json,
				success : function(data) {

				}
			});
		})
		/* $('#loadingToast').show(); */
	});
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="weui_cells_title">请输入您要绑定的表号</div>
	<div class="weui_cells">
		<div class="weui_cell weui_cell_select weui_select_before">
			<div class="weui_cell_hd">
				<!-- <div class="weui_select">绑定</div> -->
				<div class="weui_cell_hd">
					<select class="weui_select" name="select2" disabled="false">
						<option value="1">绑定</option>
					</select>
				</div>
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				<input class="weui_input " type="text" placeholder="请输入表号"
					id="codes">
			</div>
		</div>
	</div>
	<div class="weui_cells_tips">*注：多个表号之间请用逗号分隔！</div>
	<div class="weui_btn_area">
		<a class="weui_btn weui_btn_primary" href="javascript:" id="commit">确定</a>
	</div>
</body>
</html>