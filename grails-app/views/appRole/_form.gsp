<%@ page import="school.enrollment.AppRole" %>



<div class="fieldcontain ${hasErrors(bean: appRoleInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="appRole.authority.label" default="Authority" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${appRoleInstance?.authority}"/>
</div>

