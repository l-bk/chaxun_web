<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pic/cxPic/">图片信息列表</a></li>
		<shiro:hasPermission name="pic:cxPic:edit"><li><a href="${ctx}/pic/cxPic/form">图片信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cxPic" action="${ctx}/pic/cxPic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	 </form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>图片名称</th>
				<c:if test="${cxPic.picPath != null}"><th>图片</th><th>图片路径</th></c:if>
				
				<shiro:hasPermission name="pic:cxPic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cxPic">
			<tr>
				<td>${cxPic.picName}</td>
				<c:if test="${cxPic.picPath != null}"><td><img src=${cxPic.picName}/></td><td>${cxPic.picPath}</td></c:if>
				<shiro:hasPermission name="pic:cxPic:edit"><td>
    				<a href="${ctx}/pic/cxPic/form?id=${cxPic.picId}">修改</a>
					<a href="${ctx}/pic/cxPic/delete?id=${cxPic.picId}" onclick="return confirmx('确认要删除该图片信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>