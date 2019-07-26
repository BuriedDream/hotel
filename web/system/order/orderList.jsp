<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/system/style/js/jquery.js"></script>
<script type="text/javascript" src="../style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/system/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/system/style/css/index_1.css" />
	<script type="text/javascript">
		setInterval(function(){
			window.location.href = "${pageContext.request.contextPath}/order?method=list";
		},1000 * 50);
	</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="../style/images/title_arrow.gif" /> 餐厅订单列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>订单编号</td>
					<td>餐桌名</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<%--private int id;--%>
			<%--private String tableName;--%>
			<%--private Date orderDate;--%>
			<%--private double totalPrice;--%>
			<%--private int orderStatus;--%>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:choose>
					<c:when test="${not empty requestScope.orderList}">
						<c:forEach items="${requestScope.orderList}" var="order">
							<tr height="60">
								<td align="center">${order.id}</td>
								<td align="center">${order.tableName}</td>
								<td align="center">${order.orderDate}</td>
								<td align="center">${order.totalPrice}</td>
								<c:choose >
									<c:when test="${order.orderStatus==0}">
										<td>未结账</td>
									</c:when>
									<c:otherwise>
										<td>已结账</td>
									</c:otherwise>
								</c:choose>

								<td>
									<a href="${pageContext.request.contextPath}/order?method=getDetail&id=${order.id}" class="FunctionButton">详细</a>
									<a href="${pageContext.request.contextPath}/order?method=pay&id=${order.id}" class="FunctionButton">结账</a>
								</td>

							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr hight="60">
							<td colspan="6" align="center">还没有订单！</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
		</div>
	</div>
</body>
</html>
