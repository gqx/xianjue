<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加GPRS</title>
<!--<link href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">  -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<script type="text/javascript">
function createGprs(){
	var gprsMac = $("#gprsMac").val();
	var gprsName = $("#gprsName").val();
	
	$.ajax({
		type : 'POST',
		url : "${pageContext.request.contextPath}/gprsManage/createGprs",
		data : {
			gprsMac : gprsMac,
			gprsName : gprsName
		},
		dataType : "json",
		success : function(data) {
			if(data.success == true){
				alert("添加成功");
			}else{
				alert("添加失败："+data.error);
			}
		}
	});
}

function changeGprsMac(){
	var oldMac = $("#oldMac").val();
	var newMac = $("#newMac").val();
	
	$.ajax({
		type : 'POST',
		url : "${pageContext.request.contextPath}/gprsManage/changeGprsMac",
		data : {
			oldMac : oldMac,
			newMac : newMac
		},
		dataType : "json",
		success : function(data) {
			if(data.success == true){
				alert("配置成功");
			}else{
				alert("添加失败："+data.error);
			}
		}
	});
}
</script>
</body>
<div class="row">
 添加gprs
<div class="col-md-12">
<form class="form-inline">
<label>MAC</label><input type="text" id="gprsMac">
</form>
<form class="form-inline">
<label>名称</label><input type="text" id="gprsName">
</form>
<input type="button" class="btn btn-default" value="确定" onclick="createGprs()">
</div>
</div>

<div class="row">
更换gprs
<div class="col-md-12">
<form class="form-inline">
<label>oldMac</label><input type="text" id="oldMac">
</form>
<form class="form-inline">
<label>newMac</label><input type="text" id="newMac">
</form>
<input type="button" class="btn btn-default" value="确定" onclick="changeGprsMac()">
</div>
</div>
</html>