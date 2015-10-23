<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<%@ page import="java.util.Date" %>
<div class="wb-frmvld" lang="${language}">
	<form method="get" action="advanceSearchResult">
		<p>
			<fmt:message key="search.advancesearch.message"></fmt:message>
		</p>
		<div class="form-group">
			<label for="data1" class="required"><span class="field-name"><fmt:message
						key="search.advancesearch.contains"></fmt:message></span> </label> <input
				class="form-control" id="data1" name="containWord" type="text"
				required="required" pattern=".{2,}" data-rule-minlength="2" />
		</div>

		<div class="form-group">
			<label for="data2"><span class="field-name"><fmt:message
						key="search.advancesearch.donotcontains"></fmt:message></span> </label> <input
				class="form-control" id="data2" name="notContainWord" type="text" />
		</div>

		<div class="form-group">
			<label for="data3"><span class="field-name"><fmt:message
						key="search.advancesearch.betweendates"></fmt:message></span> </label>

			<!-- 									<label for="data2"><span
								class="field-name">and where archived between</span> </label> <input
								class="form-control" id="data2" name="data2" type="email" /> -->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<div class="form-group">
				<label for="startdate"><fmt:message
						key="search.advancesearch.startdate"></fmt:message><span
					class="datepicker-format"> (<abbr
						title="Four digits year, dash, two digits month, dash, two digits day">YYYY-MM-DD</abbr>)
				</span></label> <input class="form-control" type="date" id="startdate"
					name="startDate" min="2005-01-01" max="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>"/>
			</div>
					
				
			<div class="form-group">
				<label for="enddate"><fmt:message
						key="search.advancesearch.enddate"></fmt:message><span
					class="datepicker-format"> (<abbr
						title="Four digits year, dash, two digits month, dash, two digits day">YYYY-MM-DD</abbr>)
				</span></label> <input class="form-control" type="date" id="enddate" name="endDate" min="2005-01-01"
					max="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>"/>
			</div>

			<!-- Enter Start date and end date -->
		</div>
		<div class="form-group">
			<label for="title1"><span class="field-name"><fmt:message
						key="search.advancesearch.type"></fmt:message></span> </label> <select
				class="form-control" id="title1" name="type">
				<option
					label='<fmt:message key="search.advancesearch.type.label"></fmt:message>'></option>
				<option value="image">
					<fmt:message key="search.advancesearch.type.image"></fmt:message>
				</option>
				<option value="video">
					<fmt:message key="search.advancesearch.type.video"></fmt:message>
				</option>
				<option value="pdf">
					<fmt:message key="search.advancesearch.type.pdf"></fmt:message>
				</option>
				<option value="msword">
					<fmt:message key="search.advancesearch.type.doc"></fmt:message>
				</option>
				<option value="html">
					<fmt:message key="search.advancesearch.type.html"></fmt:message>
				</option>
			</select>
		</div>
		<div class="form-group">
			<label for="data4"><span class="field-name"><fmt:message
						key="search.advancesearch.website"></fmt:message></span> </label> <input
				class="form-control" id="data4" name="fromWebsite" type="text" />
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary">
				<fmt:message key="button.submit"></fmt:message>
			</button>
			<input type="reset"
				value=<fmt:message key="button.reset"></fmt:message>
				class="btn btn-default">
		</div>
	</form>
</div>