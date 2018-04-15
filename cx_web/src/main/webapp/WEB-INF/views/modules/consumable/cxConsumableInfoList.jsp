<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>耗材信息管理</title>
<meta name="decorator" content="default" />
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
		<li class="active"><a
			href="${ctx}/consumable/cxConsumableInfo/list">耗材信息列表</a></li>
		<shiro:hasPermission name="consumable:cxConsumableInfo:edit">
			<li><a href="${ctx}/consumable/cxConsumableInfo/form">耗材信息添加</a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="consumable:cxConsumableInfo:edit">
			<li><a href="${ctx}/consumable/cxConsumableInfo/goBatchAdd">批量导入</a></li>
		</shiro:hasPermission>

	</ul>
	<form:form id="searchForm" modelAttribute="cxConsumableInfo"
		action="${ctx}/consumable/cxConsumableInfo/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
			&nbsp;<label class="control-label">品牌：</label>
		<form:input path="conBrand" htmlEscape="false" class="input-xlarge " />
			&nbsp;<label class="control-label">适用机型：</label>
		<form:input path="conCompatible" htmlEscape="false"
			class="input-xlarge " />
			&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary"
			type="submit" value="查询" />
			</br>&nbsp;&nbsp;<input id="cleanAll" class="btn btn-primary" type="button" value="清空数据" style="margin-top:10px;"  />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<th>品牌</th>
			<th>适用机型</th>
			<th>耗材编码</th>
			<th>耗材型号</th>
			<th>耗材描述</th>
			<th>打印页数和寿命</th>
			<th>参考价格</th>
			<shiro:hasPermission name="consumable:cxConsumableInfo:edit">
				<th>操作</th>
			</shiro:hasPermission>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="cxConsumableInfo">
				<tr>
					<td>${cxConsumableInfo.conBrand}</td>
					<td>${cxConsumableInfo.conCompatible}</td>
					<td>${cxConsumableInfo.conCode}</td>
					<td>${cxConsumableInfo.conModel}</td>
					<td>${cxConsumableInfo.conDetails}</td>
					<td>${cxConsumableInfo.conNum}</td>
					<td>${cxConsumableInfo.conReferencePrice}</td>
					<shiro:hasPermission name="consumable:cxConsumableInfo:edit">
						<td><a
							href="${ctx}/consumable/cxConsumableInfo/form?id=${cxConsumableInfo.conId}">修改</a>
							<a
							href="${ctx}/consumable/cxConsumableInfo/delete?id=${cxConsumableInfo.conId}"
							onclick="return confirmx('确认要删除该耗材信息吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script>
	$("#cleanAll").click(function(){
		return confirmx('确认要清空耗材信息吗？', "${ctx}/consumable/cxConsumableInfo/cleanAll");
	
	});
</script>
</body>
</html>