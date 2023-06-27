<jsp:useBean id="software" class="cl.araucana.core.util.ApplicationBean" scope="application" />

<meta name="APPLICATION_ORGANIZATION_NAME" content="<%= software.getOrganizationName() %>" />
<meta name="APPLICATION_NAME" content="<%= software.getName() %>" />
<meta name="APPLICATION_TITLE" content="<%= software.getTitle() %>" />
<meta name="APPLICATION_VERSION" content="<%= software.getVersion() %>" />
<meta name="APPLICATION_RELEASE_DATE" content="<%= software.getReleaseDate() %>" />
<meta name="APPLICATION_COPYRIGHT" content="<%= software.getCopyright() %>" />
