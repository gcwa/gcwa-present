<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<fmt:message key="intro1.message"> </fmt:message>
<section class="gc-prtts">
    <div class="row">
      <section class="col-lg-4 col-md-6 mrgn-bttm-md"><a href="collection/gcwa"">
        <h3 class="h5"><fmt:message key="section.gcwebarchive.title"></fmt:message> </h3>
        <img src="${pageContext.request.contextPath}/resources/assets/gcwa_parl.jpg" alt="" class="img-responsive thumbnail mrgn-bttm-sm"></a>
        <p><fmt:message key="section.gcwebarchive"></fmt:message></p>
      </section>
      <section class="col-lg-4 col-md-6 mrgn-bttm-md"><a href="elections">
        <h3 class="h5"><fmt:message key="elections.title"></fmt:message></h3>
        <img src="${pageContext.request.contextPath}/resources/assets/ballot_box.jpg" alt="" class="img-responsive thumbnail mrgn-bttm-sm"></a>
		<p><fmt:message key="section.fed.election"></fmt:message></p>
      </section>
      <section class="col-lg-4 col-md-6 mrgn-bttm-md"><a href="raildisaster">
        <h3 class="h5"><fmt:message key="section.rail.disaster.title"></fmt:message></h3>
        <img src="${pageContext.request.contextPath}/resources/assets/lac-megantic.jpg" alt="" class="img-responsive thumbnail mrgn-bttm-sm"></a>
        <p><fmt:message key="section.rail.disaster"></fmt:message></p>	
      </section>
      </div>
     <div class="row">
      <section class="col-lg-4 col-md-6 mrgn-bttm-md"><a href="olympics">
        <h3 class="h5"><fmt:message key="section.olympics.games.title"></fmt:message></h3>
        <img src="${pageContext.request.contextPath}/resources/assets/olympics.png" alt="" class="img-responsive thumbnail mrgn-bttm-sm"></a>
        <p><fmt:message key="section.olympics.games.message"></fmt:message></p>	
      </section>
	  
	  	 </div>
  </section>
