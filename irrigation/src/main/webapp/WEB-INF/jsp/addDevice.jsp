<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="xianjue.gqx.po.Light"%>
<%@ page import="xianjue.gqx.po.WaterValve"%>
<%@ page import="xianjue.gqx.po.ElectricMachine"%>
<%@ page import="xianjue.gqx.po.Gprs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加GPRS</title>
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
	$(document).ready(function() {
		$("#gprsSelector").change(function() {
			//selectGprs();
		});
	});
	function addGprs() {
		var gprsName = $("#gprsName").val();

		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/gprs/addGprsName",
			data : {
				gprsName : gprsName
			},
			dataType : "text",
			success : function(data) {
				if (data == "success") {
					alert("命令已发送,请打开GPRS!");
					location.reload();
				} else if (data == "dup") {
					alert("命名重复!");
					location.reload();
				}
			},
		});
	}

	function selectGprs() {
		var gid = $("#gprsSelector").find("option:selected").val();
		if (gid != "请选择") {
			location.href = "${pageContext.request.contextPath}/addDevice/getDeviceByGid?gid="
					+ gid;
		}
	}

	function addLight() {
		var gid = $("#gprsSelector").find("option:selected").val();
		var lightName = $("#newLightName").val();
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/addDevice/addLight",
			data : {
				gid : gid,
				lightName : lightName
			},
			dataType : "text",
			success : function(data) {
				if (data == "success") {
					alert("添加成功!");
					location.reload();
				} else if (data == "failed") {
					alert("添加失败!");
					location.reload();
				}
			},
		});
	}
	
	function addWaterValve() {
		var gid = $("#gprsSelector").find("option:selected").val();
		var waterValveName = $("#newWaterValveName").val();
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/addDevice/addWaterValve",
			data : {
				gid : gid,
				waterValveName : waterValveName
			},
			dataType : "text",
			success : function(data) {
				if (data == "success") {
					alert("添加成功!");
					location.reload();
				} else if (data == "failed") {
					alert("添加失败!");
					location.reload();
				}
			},
		});
	}
	
	function addElectricMachine(){
		var gid = $("#gprsSelector").find("option:selected").val();
		var electricMachineName = $("#newElectricMachineName").val();
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/addDevice/addElectricMachine",
			data : {
				gid : gid,
				electricMachineName : electricMachineName
			},
			dataType : "text",
			success : function(data) {
				if (data == "success") {
					alert("添加成功!");
					location.reload();
				} else if (data == "failed") {
					alert("添加失败!");
					location.reload();
				}
			},
		});
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<form class="form-horizontal">
			<div class="form-group">
				<label class="col-md-2 control-label" for="gprsSelector">选择GPRS</label>

				<div class="col-md-8">
					<select class="form-control" id="gprsSelector">
						<%
							String selectedGidStr = (String) request
									.getAttribute("selectedGid");
							int selectedGid = -1;
							if (selectedGid < 0) {
						%>
						<option selected="selected" disabled="disabled" value="请选择">请选择</option>
						<%
							}
						%>

						<%
							ArrayList<Gprs> gprsList = (ArrayList<Gprs>) request.getAttribute("gprsList");
							if (gprsList != null) {
								for (Gprs g : gprsList) {
						%>
						<option <%if (g.getId() == selectedGid) {%> selected="selected"
							<%}%> value="<%=g.getId()%>"><%=g.getName()%></option>

						<%
							}
							}
						%>
					</select>
				</div>
				<div style="display: none">
					<button type="button" class="btn btn-primary"
						onclick="selectGprs()">确定</button>
				</div>
			</div>
		</form>
	</div>
	<!-- for light -->
	<div class="container">
		<div class="col-md-9 col-md-offset-1">
			<div style="border-bottom: 1px solid #000;">
				<h4>灯</h4>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th class="text-center">名称</th>
						<th class="text-center">状态</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<%
						ArrayList<Light> lightsList = (ArrayList<Light>) request.getAttribute("lightsList");
						if (lightsList != null) {
							for (int i = 0; i < lightsList.size(); i++) {
								Light light = lightsList.get(i);
					%>
					<tr>
						<th scope="row"><%=i+1%></th>
						<td><%=light.getName()%></td>
						<td><%=light.getState() == 1 ? "开" : "关"%></td>
					</tr>

					<%
						}
						}
					%>

				</tbody>
			</table>
			<form class="form-inline text-center">
				<div class="form-group">
					<label for="newLightName">添加</label> <input type="text"
						class="form-control" id="newLightName" placeholder="输入名称">
					<button type="button" class="btn btn-primary" onclick="addLight()">确定</button>
				</div>
			</form>
		</div>
	</div>

	<div style="margin-top:50px"></div>
	<!-- for water valve -->
	<div class="container">
		<div class="col-md-9 col-md-offset-1">
			<div style="border-bottom: 1px solid #000;">
				<h4>水阀</h4>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th class="text-center">名称</th>
						<th class="text-center">状态</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<%
						ArrayList<WaterValve> waterValveList = (ArrayList<WaterValve>) request
								.getAttribute("waterValveList");
						if (waterValveList != null) {
							for (int i = 0; i < waterValveList.size(); i++) {
								WaterValve wv = waterValveList.get(i);
					%>
					<tr>
						<th scope="row"><%=i+1%></th>
						<td><%=wv.getName()%></td>
						<td><%=wv.getState() == 1 ? "开" : "关"%></td>
					</tr>

					<%
						}
						}
					%>

				</tbody>
			</table>
			<form class="form-inline text-center">
				<div class="form-group">
					<label for="newWaterValveName">添加</label> <input type="text"
						class="form-control" id="newWaterValveName" placeholder="输入名称">
					<button type="button" class="btn btn-primary" onclick="addWaterValve()">确定</button>
				</div>
			</form>
		</div>
	</div>
	
	<div style="margin-top:50px"></div>
	<!-- for electric machine -->
	<div class="container">
		<div class="col-md-9 col-md-offset-1">
			<div style="border-bottom: 1px solid #000;">
				<h4>水阀</h4>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th class="text-center">名称</th>
						<th class="text-center">状态</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<%
						ArrayList<ElectricMachine> electricMachineList = (ArrayList<ElectricMachine>) request
								.getAttribute("electricMachineList");
						if (electricMachineList != null) {
							for (int i = 0; i < electricMachineList.size(); i++) {
								ElectricMachine em = electricMachineList.get(i);
					%>
					<tr>
						<th scope="row"><%=i+1%></th>
						<td><%=em.getName()%></td>
						<%String emState = "关"; 
						if(em.getState() == 1){
							emState = "正转";
						}else if(em.getState() == -1){
							emState = "反转";
						}
						%>
						<td><%=emState%></td>
					</tr>

					<%
						}
						}
					%>

				</tbody>
			</table>
			<form class="form-inline text-center">
				<div class="form-group">
					<label for="newElectricMachineName">添加</label> <input type="text"
						class="form-control" id="newElectricMachineName" placeholder="输入名称">
					<button type="button" class="btn btn-primary" onclick="addElectricMachine()">确定</button>
				</div>
			</form>
		</div>
	</div>
	<div style="margin-top:100px"></div>
</body>
</html>