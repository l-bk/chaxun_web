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
		<li><a href="${ctx}/consumable/cxConsumableInfo/">耗材信息列表</a></li>
		<li class="active"><a href="${ctx}/consumable/cxConsumableInfo/form?id=${cxConsumableInfo.conId}">耗材信息<shiro:hasPermission name="consumable:cxConsumableInfo:edit">${not empty cxConsumableInfo.conId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="consumable:cxConsumableInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cxConsumableInfo" action="${ctx}/consumable/cxConsumableInfo/save" method="post" class="form-horizontal">
		<form:hidden path="conId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
				<form:input path="conBrand" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">适用机型：</label>
			<div class="controls">
				<form:input path="conCompatible" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">耗材编码：</label>
			<div class="controls">
				<form:input path="conCode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">耗材型号：</label>
			<div class="controls">
				<form:input path="conModel" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">耗材描述：</label>
			<div class="controls">
				<form:textarea path="conDetails" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">打印页数和寿命：</label>
			<div class="controls">
				<form:input path="conNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参考价格：</label>
			<div class="controls">
				<form:input path="conReferencePrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="consumable:cxConsumableInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>