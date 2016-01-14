<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="xianjue.gqx.po.Gprs"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理GPRS</title>
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
	function addGprs() {
		var gprsName = $("#newGprsName").val();
		var gprsMac = $("#newGprsMac").val();
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/gprsManage/addGprs",
			data : {
				gprsName : gprsName,
				gprsMac : gprsMac
			},
			dataType : "json",
			success : function(data) {
				if(data.success == true){
					alert("添加成功");
				}else{
					alert("添加失败："+data.error);
				}
				location.reload();
			}
		});
	}
	
	function deleteGprs(gprsMac){
		
		var ret = window.confirm("确定删除 gprs "+gprsMac+" ?");
		if(!ret){
		   return;
		}
		
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/gprsManage/deleteGprs",
			data : {
				gprsMac : gprsMac
			},
			dataType : "json",
			success : function(data) {
				if(data.success == true){
					alert("删除成功");
				}else{
					alert("删除失败："+data.error);
				}
				location.reload();
			}
		});
	}
	
	function editGprs(oldMac,order){
		
		var newName = $("#gprsName_"+order).val();
		var newMac = $("#gprsMac_"+order).val();
		
		if(newName == null || newName == ""){
			alert("gprs名称不能为空！");
			return;
		}
		if(newMac == null || newMac == ""){
			alert("gprs mac不能为空！");
			return;
		}
		
		$.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/gprsManage/editGprs",
			data : {
				oldMac : oldMac,
				newMac : newMac,
				newName : newName
			},
			dataType : "json",
			success : function(data) {
				if(data.success == true){
					alert("修改成功");
				}else{
					alert("修改失败："+data.error);
				}
				location.reload();
			}
		});
		
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="col-md-10 col-md-offset-1">
		<form class="form-inline text-center">
				<div class="form-group">
				<label for="newGprsName">GPRS 名称:</label> 
					<input type="text" class="form-control" id="newGprsName" placeholder="输入名称">
					<label for="newGprsMac">GPRS MAC:</label> 
					<input type="text" class="form-control" id="newGprsMac" placeholder="输入Mac">
					<button type="button" class="btn btn-primary" onclick="addGprs()">添加</button>
				</div>
			</form>
			<div style="border-bottom: 1px solid #000;">
				<h4>GPRS</h4>
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
				<tbody class="text-center">
					<%
						ArrayList<Gprs> list = (ArrayList<Gprs>) request.getAttribute("gprsList");
						if (list != null) {
							for (int i = 0; i < list.size(); i++) {
								Gprs gprs = list.get(i);
					%>
					<tr>
						<th scope="row"><%=i+1%></th>
						<td><input id="gprsName_<%=i+1%>" type="text" value="<%=gprs.getName()%>"></td>
						<td><input id="gprsMac_<%=i+1%>" type="text" value="<%=gprs.getMac()%>"></td>
						<%String state = "init".equals(gprs.getState())?"初始化":"已连接"; %>
						<td><%=state%></td>
						<td><button type="button" class="btn btn-primary btn-xs" onclick="editGprs('<%=gprs.getMac()%>','<%=i+1%>')">修改 </button></td>
						<td><button type="button" class="btn btn-primary btn-xs" onclick="deleteGprs('<%=gprs.getMac()%>')">删除</button></td>
					</tr>
					<%
							}
						}
					%>
				</tbody>
			</table>
		
		</div>
	</div>
	<div style="margin-top:100px"></div>
</body>
</html>