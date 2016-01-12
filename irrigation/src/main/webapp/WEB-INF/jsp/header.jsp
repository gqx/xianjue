<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<style type="text/css">
.logout:hover {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<h1>你好，温室！</h1>
			</div>
			<%
				String admin = (String) request.getSession().getAttribute("admin");
				if (admin != null && !admin.equals("")) {
			%>
			<div class="col-md-6 text-right" style="margin-top: 20px;">
				管理员:<%=admin%></div>
			<div class="col-md-1 text-center" style="margin-top: 20px;">
				<a class="logout"
					href="${pageContext.request.contextPath}/login/logout">登出</a>
			</div>
			<%
				}
			%>

		</div>
		<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">温室项目Beta1.0</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown">
					    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">设备控制
					    <span class="caret"></span>
					    </a>
						<ul class="dropdown-menu" role="menu">
							<li><a
								href="${pageContext.request.contextPath}/navigate/lightsPage">灯</a></li>
							<li><a
								href="${pageContext.request.contextPath}/navigate/waterValvePage">水阀</a></li>
							<li><a
								href="${pageContext.request.contextPath}/navigate/electricMachinePage">电机</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">设备管理<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a
								href="${pageContext.request.contextPath}/gprsManage/index">GPRS管理</a></li>
							<li><a
								href="${pageContext.request.contextPath}/navigate/addDevicePage">添加设备</a></li>
							<li><a
								href="${pageContext.request.contextPath}/navigate/editDevicePage">编辑设备</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">数据查看<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a
								href="${pageContext.request.contextPath}/navigate/viewTemperaturePage">温度数据</a></li>
							<li><a
								href="${pageContext.request.contextPath}/navigate/viewHumidityPage">湿度数据</a></li>
							<li><a
								href="${pageContext.request.contextPath}/navigate/viewIlluminationPage">光照数据</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">设备定时<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a
								href="${pageContext.request.contextPath}/navigate/lightSchedulePage">灯照定时</a></li>
							<li><a
								href="${pageContext.request.contextPath}/navigate/waterValveSchedulePage">水阀定时</a></li>
						</ul></li>
					<li><a href="http://115.28.85.14:8080/webStore/main.jsp">商城</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
		</nav>
	</div>
</body>
</html>