<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>NF TMC</title>
<!-- 引入 Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/bootstrap-editable.css" rel="stylesheet">
<link href="resources/css/bootstrap-combined.min.css" rel="stylesheet">

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
<!-- main.js -->
</head>
<body>
	<div style="margin: 100px">
		<table id="shedules" class="table table-bordered table-condensed">
			<tr>
				<th>#</th>
				<c:forEach items="${dates}" var="p">
					<th>${p}</th>
				</c:forEach>
			</tr>
			<c:forEach items="${items}" var="item">
				<tr>
					<th>${item}</th>
					<c:forEach items="${dates}" var="p">
						<th><a href="#" data-pk="${item}" data-name="${p}">${shedules[item][p]}</a></th>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	<script src="resources/js/jquery-2.2.1.min.js"></script>
	<!-- 包括所有已编译的插件 -->
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-editable.min.js"></script>
	<script type="text/javascript">
		$.fn.editable.defaults.mode = 'popup';
		$('#shedules a').editable({
			type : 'text',
			url : '/club/set.go',
			title : 'Enter username',
			success : function(response, newValue) {
				if (response.status == 'error')
					return response.msg;
				window.location.reload();
			}
		});
	</script>

</body>
</html>
