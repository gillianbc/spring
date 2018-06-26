package org.gillianbc.messenger.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.gillianbc.messenger.model.ErrorMessage;

//@Provider  //disabled for now //registers the class in JAX-RS - server restart required
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		//This means any exception (other than those that have their own mappers), will get this 
		//instead of the Apache Tomcat error page e.g. page not found error
		//Need to add .type or it'll be XML - that wasn't in the tutorial
		//The exception is not going through any class that has @Produces(MediaType.APPLICATION_JSON)
		//like the DataNotFoundException thrown by the MessagesResource, so it defaults to XML
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),500,"www.ravelry.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON) 
				.entity(errorMessage)
				.build(); 
	}

}
