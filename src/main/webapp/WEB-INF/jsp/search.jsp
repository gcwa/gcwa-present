<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<div class="wb-frmvld" lang="${language}">
	<form method="get" action="searchResult" id="validation-example">
		<div class="form-group">
 			<label for="data1"> </label> <input
				class="form-control" id="data1" name="containWord" type="text"
				required="required" pattern=".{2,}" data-rule-minlength="2" />
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary">
				<fmt:message key="button.submit"></fmt:message>
			</button>
			<input type="reset"
				value=<fmt:message key="button.reset"></fmt:message>
				class="btn btn-default">
		</div>
		<div>
			<fmt:message key="search.message"></fmt:message>
		</div>
	</form>
</div>