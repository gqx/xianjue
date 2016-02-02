<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="xianjue.gqx.po.Gprs"%>
<%@ page import="xianjue.gqx.po.Zigbee"%>
<%@ page import="xianjue.gqx.enums.ZigbeeTypeEnum"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>自动化轮灌</title>
<!--<link href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">  -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/test.css"
	rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
function startAutoTask(){
	$.ajax({
		type : 'POST',
		url : "${pageContext.request.contextPath}/taskSchedule/startAutoTask",
		dataType : "json",
		success : function(data) {
			if(data.success == true){
				alert("开启命令发送 成功");
				location.reload(true);
			}else{
				alert("开始命令失败："+data.error);
			}
		}
	});
}

function stopAutoTask(){
	$.ajax({
		type : 'POST',
		url : "${pageContext.request.contextPath}/taskSchedule/stopAutoTask",
		dataType : "json",
		success : function(data) {
			if(data.success == true){
				alert("停止命令发送 成功");
				location.reload(true);
			}else{
				alert("停止命令失败："+data.error);
			}
		}
	});
}
</script>
<style type="text/css">
.input-area label{
width:90px;
text-align:right;
}

.input-area .form-control{
width:250px;
}

</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="col-md-9 col-md-offset-1 input-area">
		<form class="form-inline">
			<div class="form-group">
				<button type="button" class="btn btn-primary" onclick="startAutoTask()">开始任务</button>
				<button type="button" class="btn btn-primary" onclick="stopAutoTask()">停止任务</button>
			</div>
		</form>
		
		<form class="form-inline" style="margin-top:20px;">
			<div class="form-group">
			
				<label for="newZigbeeMac">Zigbee MAC:</label> 
				<input id="newZigbeeMac" class="form-control" type="text" placeholder="输入Zigbee Mac">
				<label for="newZigbeeName">Zigbee名称:</label> 
				<input id="newZigbeeName" class="form-control" type="text" placeholder="输入Zigbee 名称">

				<button type="button" class="btn btn-primary" onclick="createZigbee()" style="margin-left:10px">添加</button>
			</div>
		</form>
		</div>
		<div class="col-md-10 col-md-offset-1" style="margin-top:10px;">
			<div style="border-bottom: 1px solid #000;">
				<h4>Zigbee</h4>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th></th>
						<th class="text-center">名称</th>
						<th class="text-center">MAC</th>
						<th class="text-center">状态</th>
						<th class="text-center">编辑</th>
						<th class="text-center">删除</th>
					</tr>
				</thead>
				<tbody id="zigbeeTable" class="text-center">
					
				</tbody>
			</table>
		
		</div>
	</div>
	<div style="margin-top:100px"></div>
</body>
</html>
