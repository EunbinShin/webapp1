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
						<h5>연결 테스트</h5>
						<div>
							<a class="btn btn-success btn-sm" href="javascript:conntest()">연결 테스트</a>
							<script type="text/javascript">
								const conntest = () => {
									$.ajax({
										url: "conntest",
										method: "get",	
										//data는 html조각 or json이 될 수 있다.
										success: (data) => {
											$("#result1").html(data);	//이 상황에서는 data는 항상 html조각
										}
									});
									
									/*	위에거랑 같음
									$.ajax({
										url: "conntest",
										method: "post"
									}).done(function(data) {
										
									});*/
								};
							</script>
							<div id="result1"></div>
						</div>
					</div>
					<div class="sector">
						<h5>JSON 응답 받기</h5>
						<div>
							<a class="btn btn-success btn-sm" href="javascript:jsonresponse1()">객체{} 받기</a>
							<script type="text/javascript">
								const jsonresponse1 = () => {
									$.ajax({
										url: "jsonresponse1",
										method: "get",
										//data는 html조각 or json이 될 수 있다.
										success: (data) => {
											console.log(data);	//json 데이터를 받아 올 수 있다.
											$("#name").html(data.name);
											$("#age").html(data.age);
											$("#carkind").html(data.car.kind);
											$("#carcolor").html(data.car.color);
											
											for(var h of data.hobby){
												$("#hobby").append(h+",");
											}
										}
									});
								};
							</script>
							<div id="result2">
								<div id="name"></div>
								<div id="age"></div>
								<div id="carkind"></div>
								<div id="carcolor"></div>
								<div id="hobby"></div>
							</div>
							<a class="btn btn-success btn-sm" href="javascript:jsonresponse2()">배열[] 받기</a>
							<script>
								function jsonresponse2(){
									$.ajax({
										url: "jsonresponse2",
										method: "get",
										//data는 html조각 or json이 될 수 있다.
										success: (data) => {
											console.log(data);	//json 데이터를 받아 올 수 있다.
											//$("#result3 table tbody").html("qwerq");
											for(var board of data){
												$("#result3 table tbody").append("<tr>");
												$("#result3 table tbody").append("<td>"+board.bno+"</td>");
												$("#result3 table tbody").append("<td>"+board.btitle+"</td>");
												$("#result3 table tbody").append("<td>"+board.bwriter+"</td>");
												$("#result3 table tbody").append("</tr>");
											}
										}
									});
								}
							</script>
							<div id="result3">
								<table class="table">
									<thead>
										<tr>
											<td>번호</td>
											<td>제목</td>
											<td>글쓴이</td>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="sector">
						<h5>PK로 검색하기</h5>
						<div>
							<a class="btn btn-success btn-sm" 
								href="javascript:employee(100)">
								사원이 100번인 사원의 정보 가져오기
							</a>
							<a class="btn btn-success btn-sm" 
								href="javascript:employee(101)">
								사원이 101번인 사원의 정보 가져오기
							</a>
							<script type="text/javascript">
								function employee(num){
									$.ajax({
										url: "employee2",
										method: "get",
										data: {"employee_id" : num},
										//data는 html조각 or json이 될 수 있다.
										success: (data) => {
											console.log(data);	//json 데이터를 받아 올 수 있다.
											$("#eno").html(data.eno);
											$("#first").html(data.first);
											$("#last").html(data.last);
										}
									});
								}
							</script>
							<div id="result4">
								사번: <span id="eno"></span><br/>
								이름: <span id="first"></span><br/>
								성: <span id="last"></span><br/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>