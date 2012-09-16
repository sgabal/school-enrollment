<div class="header" role="banner">

	<div id="userInfo" class="userInfo">
		<div id="currentDate" class="currentDate">
			<g:formatDate date="${new Date()}" />
		</div>
		<div id="userGreeting" class="userGreeting">
			%{--<sec:ifLoggedIn>--}%
				%{--<g:message code="app.header.user.label" args="${[sec.loggedInUserInfo(field: 'name')]}" />--}%
				%{--&nbsp;|&nbsp;--}%
				%{--<g:link controller="logout">--}%
					%{--<g:message code="app.header.logout" />--}%
				%{--</g:link>--}%
			%{--</sec:ifLoggedIn>--}%
		</div>
	</div>
	
	<div id="applicationHeader" class="applicationHeader">
		%{--<div id="lifetouchLogo" class="lifetouchLogo">--}%
			%{--<a href="${createLink(uri: '/')}"><r:img dir='images' file='school-icon.png' /></a>--}%
		%{--</div>--}%
		<div id="appName" class="appName">
			<g:message code="app.header.name" />
		</div>
	</div>
</div>