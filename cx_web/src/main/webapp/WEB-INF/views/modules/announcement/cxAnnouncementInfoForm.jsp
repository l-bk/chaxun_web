<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公告信息管理</title>
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
		<li><a href="${ctx}/announcement/cxAnnouncementInfo/">公告信息列表</a></li>
		<li class="active"><a href="${ctx}/announcement/cxAnnouncementInfo/form?id=${cxAnnouncementInfo.anId}">公告信息<shiro:hasPermission name="announcement:cxAnnouncementInfo:edit">${not empty cxAnnouncementInfo.anId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="announcement:cxAnnouncementInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cxAnnouncementInfo" action="${ctx}/announcement/cxAnnouncementInfo/save" method="post" class="form-horizontal">
		<form:hidden path="anId"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group" style="border:0px;">
			<label class="control-label">公告内容：</label>
			<div class="controls">
				<form:textarea path="anDetails" htmlEscape="false" maxlength="1024" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group" style="border:0px;">
			<label class="control-label">公告状态：</label>
			<div class="controls">
				<form:checkbox path="anStatus" class="input-xlarge required" value="0" label="下架"/>
				<form:checkbox path="anStatus" class="input-xlarge required" value="1" label="上架"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="announcement:cxAnnouncementInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>