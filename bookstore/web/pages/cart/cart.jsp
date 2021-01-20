<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@ include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">购物车</span>
		<%@ include file="/pages/common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<c:if test="${empty sessionScope.cart.cartItems}">
				<tr>
					<td colspan="5"><a href="#">亲，当前购物车为空，点击这里去浏览商品吧！</a></td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart.cartItems}">
				<c:forEach items="${sessionScope.cart.cartItems.values()}" var="cartItem">
					<tr>
						<td>${cartItem.name}</td>
						<td><form action="cartServlet">
							<input type="hidden" name = "action" value="updateCount">
							<input type="hidden" name = "id" value = "${cartItem.id}">
							<input type="text" style="text-align: center; width: 50px" name="count" value="${cartItem.count}">
							<input type="submit" value="确认">
						</form></td>
						<td>${cartItem.price}</td>
						<td>${cartItem.totalPrice}</td>
						<td><a href="cartServlet?action=deleteItem&id=${cartItem.id}" class="deleteBtn">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		<c:if test="${not empty sessionScope.cart.cartItems}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clear" class="clearBtn">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%@ include file="/pages/common/footer.jsp"%>

<script type="text/javascript">
	$(function() {
		$("a.deleteBtn").click(function() {
			return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？")
		})
		$("a.clearBtn").click(function() {
			return confirm("你确定要清空购物车吗？")
		})
	})
</script>
</body>
</html>