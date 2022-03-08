<!doctype html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Elimina regista</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  		${errorMessage}
				 		 <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Sei sicuro di voler eliminare questo regista?</h5>
					    </div>
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nome</dt>
							  <dd class="col-sm-9">${ registaCheSiVuoleEliminare.nome }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${ registaCheSiVuoleEliminare.cognome  }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nickname:</dt>
							  <dd class="col-sm-9">${ registaCheSiVuoleEliminare.nickName }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di nascita:</dt>
							  <fmt:formatDate value="${registaCheSiVuoleEliminare.dataDiNascita}" type="date" pattern="dd/mm/yyyy" var="theFormattedDate" />
							  <dd class="col-sm-9">${ theFormattedDate }</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Sesso:</dt>
							  <dd class="col-sm-9">${registaCheSiVuoleEliminare.sesso}</dd>
					    	</dl>
					    	
					    </div>
					    
					    <form method="post" action="${pageContext.request.contextPath }/ExecuteDeleteRegistaServlet" class="row g-3" novalidate="novalidate">
					     	<div class='card-footer'>
					        	<input type="hidden" name="idRegista" value="${registaCheSiVuoleEliminare.id}">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<a href="${pageContext.request.contextPath }/ExecuteListRegistaServlet" class='btn btn-outline-secondary' style='width:80px'>
					            	<i class='fa fa-chevron-left'></i> Back
					        	</a>
							
					  	  </div>
						<!-- end card -->			  
			    	</form>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>