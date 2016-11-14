<% String  longUrlToGo = (String)session.getAttribute("longUrlToGo");%>
<NullTag id="someID"><%=longUrlToGo%></NullTag>

<script type="text/javascript">
window.location.href = document.getElementById("someID").innerHTML.trim();
</script>