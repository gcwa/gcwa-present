<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<c:choose>
<c:when test="${language == 'fr' }"> 
	<c:set var="inverseLocale" value="en_CA"> </c:set>
</c:when>
<c:otherwise>
<c:set var="inverseLocale" value="fr_CA"> </c:set>
</c:otherwise>
</c:choose>
<%-- <c:out value="${pageContext.response.locale}"> </c:out>
 --%><%-- <<tiles:useAttribute name="aList" scope="request" classname="java.util.List"/>
 --%>
<!--[if lt IE 9]><html class="no-js lt-ie9" lang="en" dir="ltr"><![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="${language}" dir="ltr">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<!-- Web Experience Toolkit (WET) / Boîte à outils de l'expérience Web (BOEW)
wet-boew.github.io/wet-boew/License-en.html / wet-boew.github.io/wet-boew/Licence-fr.html -->
<title>Government of Canada Web Archive</title>
<meta content="width=device-width,initial-scale=1" name="viewport">
<!-- Meta data -->
<meta name="description"
	content="Web Experience Toolkit (WET) includes reusable components for building and maintaining innovative Web sites that are accessible, usable, and interoperable. These reusable components are open source software and free for use by departments and external Web communities">
<meta name="dcterms.title"
	content="Content pagetest - Secondary menu - Web Experience Toolkit">
<meta name="dcterms.creator"
	content="French name of the content author / Nom en français de l'auteur du contenu">
<meta name="dcterms.issued" title="W3CDTF"
	content="Date published (YYYY-MM-DD) / Date de publication (AAAA-MM-JJ)">
<meta name="dcterms.modified" title="W3CDTF"
	content="Date modified (YYYY-MM-DD) / Date de modification (AAAA-MM-JJ)">
<meta name="dcterms.subject" title="scheme"
	content="French subject terms / Termes de sujet en français">
<meta name="dcterms.language" title="ISO639-2" content="eng">
<!-- Meta data-->
<!--[if gte IE 9 | !IE ]><!-->
<link href="${pageContext.request.contextPath}/resources/assets/favicon.ico" rel="icon"	type="image/x-icon">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/wet-boew.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom.css">
<!--<![endif]-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/theme.min.css">

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/wet-boew.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/theme.css"> --%>
<!--[if lt IE 9]>
<link href="${pageContext.request.contextPath}/resources/assets/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ie8-wet-boew.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ie8-theme.min.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/ie8-wet-boew.min.js"></script>
<![endif]-->
<noscript>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/noscript.min.css" />
</noscript>
</head>
<body vocab="http://schema.org/" typeof="WebPage">
	<ul id="wb-tphp">
		<li class="wb-slc"><a class="wb-sl" href="#wb-cont">Skip to
				main content</a></li>
		<li class="wb-slc visible-sm visible-md visible-lg"><a
			class="wb-sl" href="#wb-info">Skip to "About this site"</a></li>
		<li class="wb-slc visible-md visible-lg"><a class="wb-sl"
			href="#wb-sec">Skip to section menu</a></li>
	</ul>
	<header role="banner">
		<div id="wb-bnr">
			<div id="wb-bar">
				<div class="container">
					<div class="row">
					<div id=gcwu-sig>
					<div id=gcwu-sig-in>
					<div title="${headerProp.altTitle}">
					<img width=300 height=60 alt="${headerProp.altTitle}" src="${pageContext.request.contextPath}/resources/assets/${headerProp.imageURL}">
					</div>
					</div>
					</div>
						<ul id="gc-bar" class="list-inline">
							<li><a href="${headerProp.canadaURL}"
								rel="external">Canada.ca</a></li>
							<li><a href="${headerProp.serviceURL}"
								rel="external">Services</a></li>
							<li><a href="${headerProp.headerDeptURL}"
								rel="external">${headerProp.headerDept}</a></li>
							<li id="wb-lng"><h2>Language selection</h2>
								<ul class="list-inline">								
									<li><a lang="${language}" href=?locale=${inverseLocale}><fmt:message key="header.language"/></a></li>
								</ul></li>
						</ul>
						<section class="wb-mb-links col-xs-12 visible-sm visible-xs"
							id="wb-glb-mn">
							<h2>Search and menus</h2>
							<ul class="pnl-btn list-inline text-right">
								<li><a href="#mb-pnl" title="Search and menus"
									aria-controls="mb-pnl"
									class="overlay-lnk btn btn-sm btn-default" role="button"><span
										class="glyphicon glyphicon-search"><span
											class="glyphicon glyphicon-th-list"><span
												class="wb-inv">Search and menus</span></span></span></a></li>
							</ul>
							<div id="mb-pnl"></div>
						</section>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div id="wb-sttl" class="col-md-5">
						<a href="${pageContext.request.contextPath}"> <span><fmt:message key="main.title"> </fmt:message></span>
						</a>
					</div>
					<object id="wmms" type="image/svg+xml" tabindex="-1" role="img"
						data="${pageContext.request.contextPath}/resources/assets/wmms.svg"
						aria-label="Symbol of the Government of Canada"></object>
					<section id="wb-srch" class="visible-md visible-lg">
						<h2>Search</h2>
						<form action="https://google.ca/search" method="get" role="search"
							class="form-inline">
							<div class="form-group">
								<label for="wb-srch-q">Search website</label> <input
									id="wb-srch-q" class="form-control" name="q" type="search"
									value="" size="27" maxlength="150"> <input
									type="hidden" name="q"
									value="site:wet-boew.github.io OR site:github.com/wet-boew/">
							</div>
							<button type="submit" id="wb-srch-sub" class="btn btn-default">${headerProp.search}</button>
						</form>
					</section>
				</div>
			</div>
		</div>
		<nav role="navigation" id="wb-bc" property="breadcrumb">
			<h2>You are here:</h2>
			<div class="container">
				<div class="row">
					<ol class="breadcrumb">
						<c:choose>
							<c:when test="${language == 'fr' }">
								<li><a href="http://www.collectionscanada.gc.ca/index-f.html"><fmt:message key="link.home"> </fmt:message></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="http://www.collectionscanada.gc.ca/index-e.html"><fmt:message key="link.home"> </fmt:message></a></li>
							</c:otherwise>
						</c:choose>
						
<%-- 						<li><a href="http://www.collectionscanada.gc.ca/index-e.html"><fmt:message key="link.home"> </fmt:message></a></li>
 --%>						
 						<li><a href="${pageContext.request.contextPath}"><fmt:message key="main.title"> </fmt:message></a></li>
<!-- 						<li><a href="index-en.html">GCWU theme</a></li>
 -->						<li>${sectiontitle}</li>
					</ol>
				</div>
			</div>
		</nav>
	</header>
	<div class="container" style="padding-top: 10px;">
		<div class="row">
		<main role="main" property="mainContentOfPage"
				class="col-md-9 col-md-push-3">
			<h3 id="wb-cont" property="name" style="margin-top: 0px;">${sectiontitle}</h3>
			<tiles:insertAttribute name="content">
			</tiles:insertAttribute>

			<dl id="wb-dtmd">
				<dt>${footerProp.dateModified}</dt>
				<dd>
					<time property="dateModified">2015-05-31</time>
				</dd>
			</dl>
			</main>
					<nav role="navigation" id="wb-sec" typeof="SiteNavigationElement"
				class="col-md-3 col-md-pull-9 visible-md visible-lg" style="margin-top: 0px;">
				
				<ul class="list-group menu list-unstyled">
					<li>
						<ul class="list-group menu list-unstyled">
							<li><a class="list-group-item" href="intro2" style="padding-left: 15px"><fmt:message
										key="intro2.title">
									</fmt:message></a></li>
						</ul>
					</li>
				</ul>
				
				<ul class="list-group menu list-unstyled">
					<li>
					<a class="list-group-item"><fmt:message	key="search.title">				</fmt:message></a>
					
						<ul class="list-group menu list-unstyled">
							<li> <a class="list-group-item" href="search"><fmt:message	key="search.basicsearch.title">	</fmt:message></a> </li>
							<li> <a class="list-group-item" href="advancesearch"><fmt:message	key="search.advancesearch.title">	</fmt:message></a> </li>
						</ul>					
				</ul>
				
								<ul class="list-group menu list-unstyled">
					<li>
					<a class="list-group-item"><fmt:message	key="federal.govt.title">				</fmt:message></a>
					
						<ul class="list-group menu list-unstyled">
							<li> <a class="list-group-item" href="deptList"><fmt:message	key="dept.list.title">	</fmt:message></a> </li>
							 <li> <a class="list-group-item" href="deptWiseURL"><fmt:message	key="url.list.title">	</fmt:message></a> </li>
							<%--<li> <a class="list-group-item" href="urlList"><fmt:message	key="federal.govt.url">	</fmt:message></a> </li> --%>
<%-- 							<li> <a class="list-group-item" href="byDate"><fmt:message	key="federal.govt.date">	</fmt:message></a> </li>
 --%>							
						</ul>					
				</ul>

				<ul class="list-group menu list-unstyled">
				<li>
				<ul class="list-group menu list-unstyled">
							<li><a class="list-group-item" href="help" style="padding-left: 15px"><fmt:message key="help.title"> </fmt:message></a></li>
							<li><a class="list-group-item" href="comments" style="padding-left: 15px"><fmt:message key="comments.title"> </fmt:message></a></li>
<!-- 							<li><a class="list-group-item" href="addURL">Add URL</a></li>
 --><%-- 							<li><a class="list-group-item" href="wayback" style="padding-left: 15px"><fmt:message key="wayback.title"> </fmt:message></a></li>
 --%>							
				</ul>
				</ul>
<!-- 				<h2>Section menu</h2>
 -->				
 
<%--  <ul class="list-group menu list-unstyled">
					<li>
<!-- 						<h3 class="wb-navcurr">Content page - Secondary menu</h3>
 -->						<ul class="list-group menu list-unstyled">
							<li><a class="list-group-item" href="intro"><fmt:message key="intro.title"> </fmt:message></a></li>
							<li><a class="list-group-item" href="search"><fmt:message key="search.title"> </fmt:message></a></li>
<!-- 							<ul class="menu">
								<li> Basic Search </li>
								<li> Advanced Search </li>
							</ul> -->
							<li><a class="list-group-item" href="deptList"><fmt:message key="dept.list.title"> </fmt:message></a></li>
							<li><a class="list-group-item" href="urlList"><fmt:message key="url.list.title"> </fmt:message></a></li>
							<li><a class="list-group-item" href="help"><fmt:message key="help.title"> </fmt:message></a></li>
							<li><a class="list-group-item" href="comments"><fmt:message key="comments.title"> </fmt:message></a></li>
						</ul>
					</li>
				</ul> --%>
			
			
			
			</nav>
			
		</div>
	</div>
	<footer role="contentinfo" id="wb-info"
		class="visible-sm visible-md visible-lg wb-navcurr">
		<div class="container">
			<nav role="navigation">
				<h2>About this site</h2>
				<ul id="gc-tctr" class="list-inline">
					<li><a rel="license"
						href="${footerProp.termsCondLink}">${footerProp.termsCond}</a></li>
					<li><a
						href="${footerProp.transpLink}">${footerProp.transparency}</a></li>
				</ul>
				<div class="row">
					<section class="col-sm-3">
						<h3> <a href="${footerProp.aboutUsLink}">${footerProp.aboutUsTitle} </a></h3>
						<ul class="list-unstyled">
							<li><a href="${footerProp.ourMandLink}">${footerProp.ourMandate} </a></li>
							<li><a href="${footerProp.libAndArchLink}">${footerProp.libAndArch} </a></li>
							<li><a href="${footerProp.servAndProgLink}">${footerProp.servAndProg} </a></li>
							<li><a href="${footerProp.lacEventsLink}">${footerProp.lacEvents} </a></li>
						</ul>
					</section>
					<section class="col-sm-3">
												<h3> <a href="${footerProp.newsTitleLink}">${footerProp.newsTitle} </a></h3>
						<ul class="list-unstyled">
							<li><a href="${footerProp.newsReleaseLink}">${footerProp.newsRelease} </a></li>
							<li><a href="${footerProp.speechesLink}">${footerProp.speeches} </a></li>
							<li><a href="${footerProp.photoGalLink}">${footerProp.photoGallery} </a></li>
							<li><a href="${footerProp.videosLink}">${footerProp.videos} </a></li>
							<li><a href="${footerProp.podCastsLink}">${footerProp.podCasts} </a></li>
						</ul>
					</section>
					<section class="col-sm-3">
												<h3> <a href="${footerProp.contactUsLink}">${footerProp.contactUsTitle} </a></h3>
						<ul class="list-unstyled">
							<li><a href="${footerProp.addressLink}">${footerProp.address} </a></li>
							<li><a href="${footerProp.telephoneLink}">${footerProp.telephoneNums} </a></li>
							<li><a href="${footerProp.emailLink}">${footerProp.emails} </a></li>
							<li><a href="${footerProp.findEmployeeLink}">${footerProp.findAnEmployee} </a></li>
						</ul>
					</section>
					<section class="col-sm-3">
												<h3> <a href="${footerProp.stayConnectedLink}">${footerProp.stayConnectedTitle} </a></h3>
						<ul class="list-unstyled">
							<li><a href="http://www.flickr.com/photos/lac-bac/collections/">Flickr </a></li>
							<li><a href="https://twitter.com/@LibraryArchives">Twitter </a></li>
							<li><a href="http://www.facebook.com/pages/Library-and-Archives-Canada/383985531647785"> Facebook </a></li>
							<li><a href="http://www.youtube.com/user/LibraryArchiveCanada/">Youtube</a></li>
							<li><a href="${footerProp.rssFeedLink}">${footerProp.rssFeed} </a></li>
						</ul>
					</section>
				</div>
			</nav>
		</div>
		<div id="gc-info">
			<div class="container">
				<nav role="navigation">
					<h2>Government of Canada footer</h2>
					<ul class="list-inline">
						<li><a href="${footerProp.healthTitleLink}"><span>${footerProp.healthTitle}</span></a></li>
						<li><a href="${footerProp.travelLink}"><span>${footerProp.travelTitle}</span></a></li>
						<li><a href="${footerProp.servCandaLink}"><span>${footerProp.serviceCanadaTitle}</span></a></li>
						<li><a href="${footerProp.jobsLink}"><span>${footerProp.jobsTitle}</span></a></li>
						<li><a href="${footerProp.economyLink}"><span>${footerProp.economyTitle}</span></a></li>
						<li id="canada-ca"><a
							href="${headerProp.canadaURL}">Canada.ca</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</footer>
	<!--[if gte IE 9 | !IE ]><!-->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/wet-boew.min.js"></script>
	<!--<![endif]-->
	<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/resources/js/ie8-wet-boew2.min.js"></script>
<![endif]-->
</body>
</html>