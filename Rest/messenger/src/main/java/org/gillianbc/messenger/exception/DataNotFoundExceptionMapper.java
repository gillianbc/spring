package org.gillianbc.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.gillianbc.messenger.model.ErrorMessage;

@Provider  //registers the class in JAX-RS - server restart required
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		//return Response.status(Status.NOT_FOUND).build();  //This alone just returns an Apache 404 page, not JSON
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"www.ravelry.com");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build(); 
	}

}
