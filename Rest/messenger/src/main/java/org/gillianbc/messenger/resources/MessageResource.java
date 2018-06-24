package org.gillianbc.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gillianbc.messenger.model.Message;
import org.gillianbc.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long someId) {
		
		return messageService.getMessage(someId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		messageService.addMessage(message);
		return messageService.getLastMessage();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long someId, Message message) {
		message.setId(someId);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long someId) {
		messageService.deleteMessage(someId);
	}
	
	
}
