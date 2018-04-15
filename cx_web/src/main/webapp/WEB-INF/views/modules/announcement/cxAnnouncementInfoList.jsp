<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告信息管理</title>
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
		<li class="active"><a href="${ctx}/announcement/cxAnnouncementInfo/">公告信息列表</a></li>
		<shiro:hasPermission name="announcement:cxAnnouncementInfo:edit"><li><a href="${ctx}/announcement/cxAnnouncementInfo/form">公告信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cxAnnouncementInfo" action="${ctx}/announcement/cxAnnouncementInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/> -->
		
	</form:form>
	<sys:message  content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公告详情</th>
				<th>公告状态</th>
				<shiro:hasPermission name="announcement:cxAnnouncementInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cxAnnouncementInfo">
			<tr>
				<td>${cxAnnouncementInfo.anDetails}</td>
				<td>
					<c:if test="${cxAnnouncementInfo.anStatus == 0}">
						下架
					</c:if>
					<c:if test="${cxAnnouncementInfo.anStatus == 1}">
						上架
					</c:if>
				</td>
				<shiro:hasPermission name="announcement:cxAnnouncementInfo:edit"><td>
    				<a href="${ctx}/announcement/cxAnnouncementInfo/form?anId=${cxAnnouncementInfo.anId}">修改</a>
					<a href="${ctx}/announcement/cxAnnouncementInfo/delete?anId=${cxAnnouncementInfo.anId}" onclick="return confirmx('确认要删除该公告信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>