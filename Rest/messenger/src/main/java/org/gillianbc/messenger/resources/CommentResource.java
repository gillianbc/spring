package org.gillianbc.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/") // full path is inferred from MessageResource class
public class CommentResource {

	// e.g. http://localhost:8080/messenger/webapi/messages/109/comments
	@GET
	public String test() {
		return "Get with no params";
	}

	// e.g. http://localhost:8080/messenger/webapi/messages/1/comments/12
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("commentId") long commId,
			            @PathParam("messageId") long messId) {
		return "Get with a comment id " + commId + "message id from parent class " + messId;
	}
}
