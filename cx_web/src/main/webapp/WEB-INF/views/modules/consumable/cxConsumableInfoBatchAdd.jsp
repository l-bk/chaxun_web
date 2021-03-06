<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>耗材信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/consumable/cxConsumableInfo/list">耗材信息列表</a></li>
		<li class="active"><a href="${ctx}/consumable/cxConsumableInfo/form?id=${cxConsumableInfo.conId}">耗材信息<shiro:hasPermission name="consumable:cxConsumableInfo:edit">${not empty cxConsumableInfo.conId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="consumable:cxConsumableInfo:edit">查看</shiro:lacksPermission></a></li>
		<shiro:hasPermission name="consumable:cxConsumableInfo:edit"><li><a href="${ctx}/consumable/cxConsumableInfo/goBatchAdd">批量导入</a></li></shiro:hasPermission>
		
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cxConsumableInfo" enctype="multipart/form-data" action="${ctx}/consumable/cxConsumableInfo/batchAdd" method="post" class="form-horizontal">
		<form:hidden path="conId"/>
		<sys:message content="${message}"/>		
			
		 <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br /> <br />
			<shiro:hasPermission name="consumable:cxConsumableInfo:edit">
				<input id="btnImportSubmit" class="btn btn-primary" type="submit"
					value="   导    入   " />&nbsp;
				<input id="btnCancel" class="btn" type="button" value=" 返  回  " onclick="history.go(-1)"/>
			</shiro:hasPermission>
			
	</form:form>
</body>
</html>