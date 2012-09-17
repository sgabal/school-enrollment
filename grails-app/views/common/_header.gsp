<div class="header" role="banner">

	<div id="userInfo" class="userInfo">
		<div id="currentDate" class="currentDate">
			<g:formatDate date="${new Date()}" />
		</div>
		<div id="userGreeting" class="userGreeting">
			<sec:ifLoggedIn>
                %{--<sec:loggedInUserInfo field="username"/>--}%
                <sec:username/>
                &nbsp;|&nbsp;
                <g:link url="home">
                    <g:message code="app.header.home" />
                </g:link>
				&nbsp;|&nbsp;
				<g:link controller="logout">
					<g:message code="app.header.logout" />
				</g:link>
			</sec:ifLoggedIn>
		</div>
	</div>
	
	<div id="applicationHeader" class="applicationHeader">
		<div id="lifetouchLogo" class="lifetouchLogo">
			<a href="${createLink(uri: '/')}"><r:img dir='images' file='school.png' /></a>
		</div>
		<div id="appName" class="appName">
			<g:message code="app.header.name" />
		</div>
	</div>
</div>