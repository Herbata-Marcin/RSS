<%@ include file="header.jsp"%>

<div id="mainContainer">
	<div>
		<div>
			<label for="url">Podaj URL:</label>
			<input id="url" type="text" name="url">
		</div>
	  	<div>
	  		<label for="mail">Podaj Mail:</label>
			<input id="mail" type="text" name="mail" value="${mail}">
		</div>
	</div>
	<div>
		<div id="mailView"></div>
		<div id="rssList">
			<c:forEach var="rss" items="${rssList}">
				<a href="" onclick="removeRSS(${rss.id});">[x]</a>&nbsp;${rss.url}<br>
			</c:forEach>
		</div>
	</div>
	<button id="save">save</button>
	<button id="send">send</button>
</div>

<script>
	<%@ include file="js/script.js"%>
</script>

<%@ include file="footer.jsp"%>