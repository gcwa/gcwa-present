<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<%-- <fmt:message key="intro1.message"> </fmt:message> --%>

<section class="gc-prtts">
    <div class="row">
      <section class="col-lg-4 col-md-6 mrgn-bttm-md"><a href="">
        <img src="${pageContext.request.contextPath}/resources/assets/ballot_box.jpg" alt="" class="img-responsive thumbnail mrgn-bttm-sm"></a>
      </section>
	      <section class="col-lg-4 col-md-6 mrgn-bttm-md">
		  <p><fmt:message key="elections.select.category"> </fmt:message></p>
		<ul>
		<li><a href="elect2006"><fmt:message key="elections.title.2006"> </fmt:message></a></li>
		<li><a href="elect2008"><fmt:message key="elections.title.2008"> </fmt:message></a></li>
		<li><a href="elect2011"><fmt:message key="elections.title.2011"> </fmt:message></a></li>
		</ul>
      </section>

	  	 </div>
  </section>