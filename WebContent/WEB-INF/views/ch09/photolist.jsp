<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:forEach var="fileName" items="${fileNames}">
	<div style="display: flex; align-items: center; margin-bottom: 5px;">
		<img style="margin-right: 5px;"
			src="photodownload?photo=${fileName}"	<%--photodownload와 매핑된 함수에서 photo가 filename과 같은 애를 http로 return--%>
		 	width="40px" height="40px"
		 	class="rounded-circle"/>
		<a href="photodownload?photo=${fileName}">${fileName}</a>
	</div>
</c:forEach>

