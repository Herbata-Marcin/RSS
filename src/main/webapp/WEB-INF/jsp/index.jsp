<%@ include file="header.jsp"%>


<div style="width:100%">
		<input name="name" type="text" value="URL-RSS" style="display:inline-block; width: 25%;" /> 
		<input name="mail" type="text" value="mail adress" style="display:inline-block; width: 25%;" /> 

	</div>
	<div> 
		<input name="mail_text" type="text" value="mail" style="display:inline-block; width: 25%; height: 100px; overflow: hidden;" src="http://" width="80" height="80" scrolling="no"/>
		<input name="rss" type="text" value="RSS" style="display:inline-block; width: 25%; height: 100px; overflow: hidden;" src="http://" width="80" height="80" scrolling="no"/> 
	</div>
	<div style="width:100%">
	<form>
	 
	<button type="save" value="Submit">Save</button>
   
	<button type="send" value="Submit">Send</button>
	</form>
	</div>


<%@ include file="footer.jsp"%>