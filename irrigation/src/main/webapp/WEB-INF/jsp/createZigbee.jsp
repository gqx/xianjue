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
function createZigbee(){
	var gprsMac = $("#gprsMac").val();
	var zigbeeMac = $("#zigbeeMac").val();
	var zigbeeName = $("#zigbeeName").val();
	var zigbeeType = Number($("#zigbeeType").val());
	
	$.ajax({
		type : 'POST',
		url : "${pageContext.request.contextPath}/zigbeeManage/createZigbee",
		data : {
			gprsMac : gprsMac,
			zigbeeMac : zigbeeMac,
			zigbeeName : zigbeeName,
			zigbeeType : zigbeeType
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

function changeZigbeeMac(){
	var oldMac = $("#oldMac").val();
	var newMac = $("#newMac").val();
	
	$.ajax({
		type : 'POST',
		url : "${pageContext.request.contextPath}/zigbeeManage/changeZigbeeMac",
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
添加zigbee
<div class="row">
<div class="col-md-12">
<form class="form-inline">
<label>gprs mac</label><input type="text" id="gprsMac">
</form>
<form class="form-inline">
<label>zigbee mac</label><input type="text" id="zigbeeMac">
</form>
<form class="form-inline">
<label>zigbee name</label><input type="text" id="zigbeeName">
</form>
<form class="form-inline">
<label>zigbee type</label><input type="text" id="zigbeeType">
</form>
<input type="button" class="btn btn-default" value="确定" onclick="createZigbee()">
</div>
</div>

更换zigbee
<div class="row">
<div class="col-md-12">
<form class="form-inline">
<label>oldMac</label><input type="text" id="oldMac">
</form>
<form class="form-inline">
<label>newMac</label><input type="text" id="newMac">
</form>
<input type="button" class="btn btn-default" value="确定" onclick="changeZigbeeMac()">
</div>
</div>
</html>