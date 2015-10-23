<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<c:choose>
	<c:when test="${language == 'en'}">
		<div lang="${language}">
			<ul class="pagination pagination-sm" style="margin-bottom: 20px;">
				<c:forEach items="${letters}" varStatus="loop">
					<c:choose>
						<c:when test="${fn:length(fromAtoZ[loop.index]) > 0 }">
							<li><a href="#${letters[loop.index]}" style="padding: 10px;">${letters[loop.index]}<span
									class="wb-inv"></span></a></li>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
			<c:forEach items="${fromAtoZ}" var="innerArray" varStatus="loopB">
				<c:if test="${fn:length(innerArray) > 0}">
					<h4 id="${letters[loopB.index]}"
						style="margin-top: 0px; margin-bottom: 10px;">${letters[loopB.index]}</h4>

					<ul>
						<c:forEach var="aURL" items="${innerArray}">
							<li><a href="deptWiseURL#${aURL.deptNameEng}">
									${aURL.deptNameEng}</a></li>
						</c:forEach>
					</ul>
				</c:if>
			</c:forEach>
			<ul class="pagination pagination-sm" style="margin-bottom: 20px;">
				<c:forEach items="${letters}" varStatus="loop">
					<c:choose>
						<c:when test="${fn:length(fromAtoZ[loop.index]) > 0 }">
							<li><a href="#${letters[loop.index]}" style="padding: 10px;">${letters[loop.index]}<span
									class="wb-inv"></span></a></li>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<div lang="${language}">
			<ul class="pagination pagination-sm" style="margin-bottom: 20px;">
				<c:forEach items="${letters}" varStatus="loop">
					<c:choose>
						<c:when test="${fn:length(fromAtoZ[loop.index]) > 0 }">
							<li><a href="#${letters[loop.index]}" style="padding: 10px;">${letters[loop.index]}<span
									class="wb-inv"></span></a></li>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
			<c:forEach items="${fromAtoZ}" var="innerArray" varStatus="loopB">

				<c:if test="${fn:length(innerArray) > 0}">
					<h4 id="${letters[loopB.index]}"
						style="margin-top: 0px; margin-bottom: 10px;">${letters[loopB.index]}</h4>

					<ul>
						<c:forEach var="aURL" items="${innerArray}">
							<li><a href="deptWiseURL#${aURL.deptNameFr}">
									${aURL.deptNameFr}</a></li>
						</c:forEach>
					</ul>
				</c:if>
			</c:forEach>
			<ul class="pagination pagination-sm" style="margin-bottom: 20px;">
				<c:forEach items="${letters}" varStatus="loop">
					<c:choose>
						<c:when test="${fn:length(fromAtoZ[loop.index]) > 0 }">
							<li><a href="#${letters[loop.index]}" style="padding: 10px;">${letters[loop.index]}<span
									class="wb-inv"></span></a></li>
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
	</c:otherwise>
</c:choose>
