<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<!-- 게시판 -->
	<!-- forEach: 는 List, 배열 요소를 순서대로 반복해서 처리할 수 있는 태그 -->
	<c:forEach var="board" items="${boards.content}">
		<!-- $는 EL 읽는거. boardcontroller에서 index로 오면서 boards를 날림 -->
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id }" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<!-- 페이징 버튼 -->
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first}"> 
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1 }">Previous</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1 }">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	
</div>

<%@ include file="layout/footer.jsp"%>