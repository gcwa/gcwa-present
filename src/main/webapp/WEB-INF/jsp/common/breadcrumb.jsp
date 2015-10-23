
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<nav role="navigation" id="wb-bc" property="breadcrumb">
	<h2>You are here:</h2>
	<div class="container">
			<ol class="breadcrumb">

				<!-- 						<li><a href="http://www.collectionscanada.gc.ca/index-e.html">Home</a>
						</li>
						<li><a
							href="http://www.collectionscanada.gc.ca/webarchives/index-e.html">Government of Canada Web Archive</a></li>
						<li><a href="index-en.html">GCWU theme</a></li>
						<li>Introduction</li> -->

				<c:forEach items="${breadcrumbList}" var="breadcrumb">
					<li><a href="${breadcrumb.link}">${breadcrumb.value}</a> </li>
				</c:forEach>				
			</ol>
		</div>
</nav>
