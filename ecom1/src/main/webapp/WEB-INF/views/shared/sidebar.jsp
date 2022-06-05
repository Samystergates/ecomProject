
<div class="list-group">

	<c:forEach items="${categories}" var="category">
		<a href="${contextRoot}/show/category/${category.id}/products"
			class="list-group-item additional" id="a_${category.name}"
			onmouseover="this.style.backgroundColor='#f4f4f7';" 
			onmouseout="this.style.backgroundColor='#dce2e4';"
			style="background-color: #dce2e4; border-style: dotted;">
			${category.name} </a>
	</c:forEach>
</div>
