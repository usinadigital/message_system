<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="homeActive" value="active" scope="request"/>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="content">
		<div class="box">
					<div class="box-head">
						<h2><spring:message code="view.home.title" /></h2>
					</div>
					<form action="" method="post">
						<div class="form">
								<p>
									<span class="req">max 100 symbols</span>
									<label>Article Title <span>(Required Field)</span></label>
									<input type="text" class="field size1" />
								</p>	
								<p class="inline-field">
									<label>Date</label>
									<select class="field size2">
										<option value="">23</option>
									</select>
									<select class="field size3">
										<option value="">July</option>
									</select>
									<select class="field size3">
										<option value="">2009</option>
									</select>
								</p>
								
								<p>
									<span class="req">max 100 symbols</span>
									<label>Content <span>(Required Field)</span></label>
									<textarea class="field size1" rows="6" cols="20"></textarea>
								</p>	
							
						</div>
						<div class="buttons">
							<input type="button" class="button" value="preview" />
							<input type="submit" class="button" value="submit" />
						</div>
					</form>
				</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
