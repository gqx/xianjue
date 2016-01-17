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
<title>管理Zigbee</title>
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
function createZigbee(){
	var gprsMac = $("#gprsMac").val();
	var zigbeeMac = $("#newZigbeeMac").val();
	var zigbeeName = $("#newZigbeeName").val();
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

function updateZigbee(){
	var oldMac = $("#oldMac").val();
	var newMac = $("#newMac").val();
	var name = $("#newMac").val();


	$.ajax({
		type : 'POST',
		url : "${pageContext.request.contextPath}/zigbeeManage/updateZigbee",
		data : {
			oldMac : oldMac,
			newMac : newMac
		},
		dataType : "json",
		success : function(data) {
			if(data.success == true){
				alert("配置成功");
			}else{
				alert("添加失败：;+data.error")
			}
		}
	});
}

function selectChange(){
	  var gprsMac = $("#gprsMac").val();
	  var zigbeeType = $("#zigbeeType").val();
	  $.ajax({
			type : 'POST',
			url : "${pageContext.request.contextPath}/zigbeeManage/getZigbeeByGprsAndType",
			data : {
				gprsMac : gprsMac,
				zigbeeType : zigbeeType
			},
			dataType : "json",
			success : function(data) {
				if(data.success == true){
					var list = data.zigbeeList;
					   $("#zigbeeTable").empty();
					 for(var i=0; i<list.length; i++){
		                 var tr = document.createElement("tr");
		                 var th = document.createElement("th");
		                 th.scope="row";
		                 th.innerHTML=(i+1);
		                 tr.appendChild(th);  
		                 
		                 var td = document.createElement("td");
		                 td.innerHTML='<input id="zigbeeName_'+(i+1)+'" type="text" value="'+list[i].name+'">';
		                 tr.appendChild(td);
		                 
		                 var td2 = document.createElement("td");
		                 td2.innerHTML='<input id="zigbeemac_'+(i+1)+'" type="text" value="'+list[i].mac+'">';
		                 tr.appendChild(td2);
		                 
		                 var td3 = document.createElement("td");
		                 if(list[i].state == 'init'){
		                	 td3.innerHTML='初始化';
		                 }else{
		                	 td3.innerHTML='已连接';
		                 }
		                 tr.appendChild(td3);
		                 
		                 var td4 = document.createElement("td");
		                 td4.innerHTML='<button type="button" class="btn btn-primary btn-xs" onclick="editGprs('+list[i].mac+','+(i+1)+')">修改 </button>';
		                 tr.appendChild(td4);
		                 
		                 var td5 = document.createElement("td");
		                 td5.innerHTML='<button type="button" class="btn btn-primary btn-xs" onclick="editGprs('+list[i].mac+','+(i+1)+')">修改 </button>';
		                 tr.appendChild(td5);
		                 
		                 document.getElementById("zigbeeTable").appendChild(tr);
		             } 
				}else{
					
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
				<label for="gprsMac">GPRS MAC:</label> 
				<select id="gprsMac" class="form-control" onchange="selectChange()">
				<%ArrayList<Gprs> gprsList = (ArrayList<Gprs>)request.getAttribute("gprsList"); 
				  if(gprsList == null || gprsList.size() == 0){
				%>
				<option selected="selected" disabled="disabled" value="请选择">请选择</option>
				<%	  
				  }else{
					  for(Gprs gprs:gprsList){
						  String gprsMac = gprs.getMac();
				%>
				<option value="<%=gprsMac%>"><%=gprsMac%></option>
				<%		  
					  } 
				  }  
				
				%>
				
				</select>
				
			</div>
		</form>
		<form class="form-inline" style="margin-top:20px;">
			<div class="form-group">
			<label for="zigbeeType">Zigbee类型:</label> 
				<select id="zigbeeType" class="form-control" onchange="selectChange()">
				<%ZigbeeTypeEnum[] zigbeeTypes = (ZigbeeTypeEnum[])request.getAttribute("zigbeeTypes"); 
				  if(zigbeeTypes == null || zigbeeTypes.length == 0){
				%>
				<option selected="selected" disabled="disabled" value="请选择">请选择</option>
				<%	  
				  }else{
					  for(ZigbeeTypeEnum zigbeeType:zigbeeTypes){
				%>
				<option value="<%=zigbeeType.getCode()%>"><%=zigbeeType.getDesc()%></option>
				<%		  
					  } 
				  }  
				
				%>
				</select>
			</div>
		</form>
		<form class="form-inline" style="margin-top:20px;">
			<div class="form-group">
			
				<label for="newZigbeeMac">Zigbee MAC:</label> 
				<input id="newZigbeeMac" class="form-control" type="text" placeholder="输入Zigbee Mac">
				<label for="newZigbeeName">Zigbee名称:</label> 
				<input id="newZigbeeName" class="form-control" type="text" placeholder="输入Zigbee 名称">

				<button type="button" class="btn btn-primary" onclick="addGprs()" style="margin-left:10px">添加</button>
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
					<%
						ArrayList<Zigbee> list = (ArrayList<Zigbee>) request.getAttribute("zigbeeList");
						if (list != null) {
							for (int i = 0; i < list.size(); i++) {
								Zigbee zigbee = list.get(i);
					%>
					<tr>
						<th scope="row"><%=i+1%></th>
						<td><input id="gprsName_<%=i+1%>" type="text" value="<%=zigbee.getName()%>"></td>
						<td><input id="gprsMac_<%=i+1%>" type="text" value="<%=zigbee.getMac()%>"></td>
						<%String state = "init".equals(zigbee.getState())?"初始化":"已连接"; %>
						<td><%=state%></td>
						<td><button type="button" class="btn btn-primary btn-xs" onclick="editGprs('<%=zigbee.getMac()%>','<%=i+1%>')">修改 </button></td>
						<td><button type="button" class="btn btn-primary btn-xs" onclick="deleteGprs('<%=zigbee.getMac()%>')">删除</button></td>
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