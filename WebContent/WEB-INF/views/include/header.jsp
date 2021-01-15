<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mainHeader">
	<h3>Spring Framework</h3>
	<div class="loginBox">
		<c:if test="${loginStatus == null}">
			<a href="<%=application.getContextPath() %>/ch08/content" class="btn btn-info btn-sm">로그인</a>
		</c:if>
		<c:if test="${loginStatus != null}">
			<img src="<%=application.getContextPath() %>/resources/img/myphoto.jpg" width="50px" class="rounded-circle"/>
			<a href="<%=application.getContextPath() %>/ch08/logout" class="btn btn-danger btn-sm">로그아웃</a>
		</c:if>
	</div>
</div>