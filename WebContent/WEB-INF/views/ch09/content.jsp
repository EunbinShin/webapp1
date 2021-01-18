<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/main.css">
	</head>
	
	<body>
		<div class="wrap">
			<%--공통 헤더 --%>
			<jsp:include page="/WEB-INF/views/include/header.jsp"/> <%--실행한 결과를 넣겠다 --%>
			<%-- <%@ include file="/WEB-INF/views/include/header.jsp" %> --%> <%--파일 내용을 복사 카피하겠다--%>
			<%-- 내용 --%>
			<div class="mainCenter">
				<%-- 공통 메뉴 --%>
				<jsp:include page="/WEB-INF/views/include/menu.jsp"></jsp:include>
				
				<div class="content">
					<div class="sector">
						<h5>파일 업로드</h5>
						<div>
							<form method="post" enctype="multipart/form-data" action="photoupload">
								<input type="text" name="uid" placeholder="아이디"><br/>
								<input type="text" name="uname" placeholder="이름"><br/>
								<input type="password" name="upassword" placeholder="비밀번호"><br/>
								<input type="file" name="uphoto" ><br/>
								<input class="btn btn-primary btn-sm" type="submit" value="회원가입"/>
							</form>
						</div>
					</div>
					<div class="sector">
						<h5>파일 다운로드</h5>
						<div>
							<script type="text/javascript">
								$(()=>{
									$.ajax({
										url: "photolist",
										method: "get",
										success: (data) => {
											$("#photoList").html(data);
										}
									});
								});
							</script>
						</div>
						<div id="photoList"></div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>