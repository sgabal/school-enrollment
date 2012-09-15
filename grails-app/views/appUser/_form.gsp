<%@ page import="school.enrollment.AppUser" %>



<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="appUser.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${appUserInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="appUser.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${appUserInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="appUser.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${appUserInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="appUser.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${appUserInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="appUser.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${appUserInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: appUserInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="appUser.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${appUserInstance?.passwordExpired}" />
</div>

