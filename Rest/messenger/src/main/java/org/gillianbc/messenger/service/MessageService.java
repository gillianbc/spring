package org.gillianbc.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.gillianbc.messenger.database.DatabaseClass;
import org.gillianbc.messenger.exception.DataNotFoundException;
import org.gillianbc.messenger.model.Message;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	/*public MessageService() {
		//constructor that seeds a couple of messages for us
		//Not a good idea as everytime a message service is created,
		//these will be restored.  This is really confusing if you've updated or deleted them
		 * 
		messages.put(1L, new Message(1L,"Default message 1", "Gillian"));
		messages.put(2L, new Message(2L,"Default message 2", "Gillian"));
		
	}*/
	public Message getMessage(Long messageId) {
//		showMessages("get");
		Message message = messages.get(messageId);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + messageId + " does not exist");
		}
		return message;
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());

	}
	
	public List<Message> getAllMessagesForYear(int year){
		Calendar cal = Calendar.getInstance();
		List<Message> matchingMessages = new ArrayList<>();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR)==year) {
				matchingMessages.add(message);
			}
		}
		return matchingMessages;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int count){
		ArrayList<Message> pageList = new ArrayList<>(messages.values());
		if (start + count > pageList.size()) {
			System.out.println("There aren't enough messages for this request");
			return new ArrayList<Message>();
		}
		return pageList.subList(start, start + count);
	}
	
	public Message addMessage(Message message) {
		message.setCreated(Calendar.getInstance().getTime());
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		showMessages("update");
		return message;
	}
	
	private void showMessages(String methodName) {
		List<Message> list = new ArrayList<Message>(messages.values());
		System.out.println(" ===== MESSAGES =====" + methodName);
		for (Message message : list) {
			System.out.println("Id:" + message.getId() + " Message Text:" + message.getMessage());
		}
		
	}
	public void deleteMessage(long id) {
		System.out.println("About to delete id " + id);
		messages.remove(id);
		showMessages("delete");
	}
	public Message getLastMessage() {
		
		return getMessage((long) messages.size());
	}
	

}
