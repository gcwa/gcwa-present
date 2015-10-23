<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<div lang="${language}">
	<c:if test="${searchStats.totalResults > 0}">
		<br />
		<fmt:message key="search.basicsearch.results.totalnumber">
		</fmt:message>
		<strong>${searchStats.totalResults}</strong>.

    <c:choose>
			<c:when test="${showAll}">
            Displaying URLs 
        </c:when>
			<c:otherwise>
				<br />
				<fmt:message
					key="search.basicsearch.results.displaying.highest.ranked">
				</fmt:message>
			</c:otherwise>
		</c:choose>
		<strong>${searchStats.startIndex +
			1}-${searchStats.startIndex + searchStats.numberOfItems}</strong>.
    <br />
		<br />
		<jsp:include page="/WEB-INF/jsp/searchResultsNav.jsp" />
		<br />

		<c:forEach items="${searchItems}" var="searchItem">
			<hr />
			<span class="nowrap"><strong>${searchItem.title}</strong></span>
			<br />
			<span class="nowrap">(${searchItem.wrappedLink})</span>
			<br />
			<br />
			<c:if test="${searchItem.description != null}">
			(${searchItem.description})<br />
				<br />
			</c:if>

			<strong><fmt:message
					key="search.basicsearch.results.archived">
				</fmt:message> ${searchItem.cleanDate} at ${searchItem.cleanTime}</strong>
			<br />

			<a
				href="${viewerHost}/wayback/${searchItem.arcDate}/${fn:escapeXml(searchItem.link)}?locale=${locale}">
				<fmt:message key="search.basicsearch.results.viewpage">
				</fmt:message>
			</a>&nbsp;
		<a href="${viewerHost}/wayback/*/${fn:escapeXml(searchItem.link)}?locale=${locale}"> <fmt:message
					key="search.basicsearch.results.otherver">
				</fmt:message></a>&nbsp;
		<c:if test="${!showAll}">
				<a href="${searchPageURL}?q=${searchQuery}+site:${searchItem.site}">
					<fmt:message key="search.basicsearch.results.more">
					</fmt:message> ${searchItem.site}
				</a>
			</c:if>
		</c:forEach>
		<hr />
		<fmt:message key="search.basicsearch.results.totalnumber">
		</fmt:message>
		<strong>${searchStats.totalResults}</strong>.<br />
		<br />
		<jsp:include page="/WEB-INF/jsp/searchResultsNav.jsp" />

	</c:if>

	<c:if test="${searchStats.totalResults == 0}">
		<p>
			<strong><fmt:message key="search.basicsearch.results.noresult"></fmt:message></strong>
		</p>
	</c:if>
</div>