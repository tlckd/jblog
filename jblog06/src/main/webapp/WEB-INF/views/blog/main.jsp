<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blogInfo.title}</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogHeader.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postInfo.title}</h4>
					<p>
						${postInfo.contents}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="postvo" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${blogInfo.blogId}/${postvo.categoryNo}/${postvo.no}">${postvo.title }</a> <span>2015/05/02</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogInfo.logo}">
			</div>
			<c:import url="/WEB-INF/views/includes/blogCategoryNavigation.jsp" />
		</div>

		<c:import url="/WEB-INF/views/includes/blogFooter.jsp" />
	</div>
</body>
</html>