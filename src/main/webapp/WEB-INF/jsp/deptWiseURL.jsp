<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<!-- <h3 style="margin-top: 20px;">Alphabetical Department List and their URL</h3>
 
<h3 style="margin-top: 20px;">Alphabetical Department List and their URL</h3>-->
<c:choose>
	<c:when test="${language == 'en'}">
		<div lang="${language}">
			<c:forEach items="${fromAtoZ}" var="innerArray" varStatus="loopB">
				<c:if test="${fn:length(innerArray) > 0}">
					<c:forEach var="aURL" items="${innerArray}">
						<h4 id="${aURL.engDesc}">
							${aURL.engDesc} <br>
						</h4>
						<ul>
							<c:forEach var="innerArray" items="${aURL.urlArray}">
								<li><a href="/wayback/*/${innerArray.urlDesc}?locale=${locale}"> ${innerArray.urlDesc}</a></li>
							</c:forEach>
						</ul>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		<div lang="${language}">
			<c:forEach items="${fromAtoZ}" var="innerArray" varStatus="loopB">
				<c:if test="${fn:length(innerArray) > 0}">
					<c:forEach var="aURL" items="${innerArray}">
						<h4 id="${aURL.frDesc}">
							${aURL.frDesc} <br>
						</h4>
						<ul>
							<c:forEach var="innerArray" items="${aURL.urlArray}">
								<li><a href="/wayback/*/${innerArray.urlDesc}?locale=${locale}"> ${innerArray.urlDesc}</a></li>
							</c:forEach>
						</ul>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>