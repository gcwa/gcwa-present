<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<%-- <fmt:message key="intro1.message"> </fmt:message> --%>

<section class="gc-prtts">
    <div class="row">
      <section class="col-lg-4 col-md-6 mrgn-bttm-md"><a href="">
        <img src="${pageContext.request.contextPath}/resources/assets/olympics.png" alt="" class="img-responsive thumbnail mrgn-bttm-sm"></a>
      </section>
	      <section class="col-lg-4 col-md-6 mrgn-bttm-md">
		  <p><fmt:message key="elections.select.category"> </fmt:message></p>
		<ul>
		<li><a href="olymp2006"><fmt:message key="olympics.title.2006"> </fmt:message></a></li>
		<li><a href="olymp2008"><fmt:message key="olympics.title.2008"> </fmt:message></a></li>
		<li><a href="olymp2010"><fmt:message key="olympics.title.2010"> </fmt:message></a></li>
		<li><a href="olymp2012"><fmt:message key="olympics.title.2012"> </fmt:message></a></li>
		<li><a href="olymp2014"><fmt:message key="olympics.title.2014"> </fmt:message></a></li>
		</ul>
      </section>

	  	 </div>
  </section>