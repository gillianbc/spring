package org.gillianbc.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.gillianbc.messenger.database.DatabaseClass;
import org.gillianbc.messenger.model.Comment;
import org.gillianbc.messenger.model.ErrorMessage;
import org.gillianbc.messenger.model.Message;

public class CommentService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId, long commentId) {
		/*
		 * This approach is not recommended as it means a lot of error
		 * handling logic within the business service.  The preferred approach
		 * is to write a custom exception mapper like we did before for DataNotFoundException
		 */
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(makeResponse());
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new WebApplicationException(makeResponse());
		}
		return comment;
	}

	private Response makeResponse() {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "www.hhh.net");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		return response;
	}

	public Comment addComment(long messageId, Comment comment) {
		if (messages.get(messageId) == null) {
			System.out.println("No such message " + messageId);
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size());
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() < 0) {
			return null;

		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public void deleteComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comments.remove(commentId);
	}
}
