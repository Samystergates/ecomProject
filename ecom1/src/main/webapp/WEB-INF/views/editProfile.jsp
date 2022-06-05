<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">

		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">

					<h4>Edit Profile</h4>

				</div>

				<div class="panel-body">
					<security:authorize access="isAuthenticated()">
						<sf:form class="form-horizontal" modelAttribute="user"
							action="${contextRoot}/profile" method="POST"
							enctype="multipart/form-data">
							<div class="form-group">
								<label class="control-label col-md-4">Name</label>
								<div class="col-md-8">
									<sf:input type="text" path="firstName" class="form-control"
										placeholder="User Name" />
									<sf:errors path="firstName" cssClass="help-block" element="em" />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4">Last Name</label>
								<div class="col-md-8">
									<sf:input type="text" path="lastName" class="form-control"
										placeholder="last Name" />
									<sf:errors path="lastName" cssClass="help-block" element="em" />
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-4">Contact Number</label>
								<div class="col-md-8">
									<sf:textarea path="contactNumber" class="form-control"
										placeholder="Enter your contact numner" />
									<sf:errors path="contactNumber" cssClass="help-block"
										element="em" />
								</div>
							</div>

							<sf:input type="hidden" path="id" class="form-control"
								placeholder="na" />
							<sf:input type="hidden" path="email" class="form-control"
								placeholder="na" />

							<sf:input type="hidden" path="password" class="form-control"
								placeholder="na" />

							<sf:input type="hidden" path="role" class="form-control"
								placeholder="na" />

							<sf:input type="hidden" path="enabled" class="form-control"
								placeholder="na" />


							<div class="form-group">
								<div class="col-md-offset-4 col-md-4">

									<input type="submit" name="submit" value="Save"
										class="btn btn-primary" />

								</div>
							</div>

						</sf:form>
					</security:authorize>
				</div>

			</div>

		</div>

	</div>

</div>