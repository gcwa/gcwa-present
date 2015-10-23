<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<strong> <fmt:message key="search.basicsearch.results.result">
	</fmt:message>&nbsp; <c:if test="${searchStats.startIndex > 0}">
		<a href="${navLinks.previousLink}">&lt;&lt; <fmt:message
				key="search.basicsearch.results.previous">
			</fmt:message></a>&nbsp; 
    </c:if> ${searchStats.startIndex + 1}-${searchStats.startIndex +
	searchStats.numberOfItems}&nbsp; <c:if test="${morePages}">
		<a href="${navLinks.nextLink}"><fmt:message
				key="search.basicsearch.results.next">
			</fmt:message> &gt;&gt;</a>&nbsp;
    </c:if> <c:if test="${!showAll}">
		<a href="${searchPageURL}?q=${searchQuery}&amp;sa=t"> <fmt:message
				key="search.basicsearch.results.showAll">
			</fmt:message> (<fmt:message key="search.basicsearch.results.unranked">
			</fmt:message>)
		</a>
	</c:if>
</strong>