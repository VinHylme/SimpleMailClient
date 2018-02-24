import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import emailConnector.InterfaceConnector;

public class ClientModel implements InterfaceClientModel {

	String inboxFolder = "inbox";
	String sentFolder = "sent";
	String redFolder = "red";
	String greenFolder = "green";
	String blueFolder = "blue";

	String folderName;
	String theType;
	String searchFor;

	InterfaceMessage msg;

	InterfaceConnector connector;
	HashMap<String, InterfaceFolder> folders = new HashMap<String, InterfaceFolder>();
	ArrayList<Integer> knownMessageIds;
	String activeFolderName;
	String prevCommandAlias;
	Stack<AbstractCommand> undoStack;
	Stack<AbstractCommand> redoStack;

	@Override
	public void undoFun(AbstractCommand command) {
		undoStack.push(command);
	}

	@Override
	public String removeAlias(String oldComa, String newComa) {

		for (int i = 0; i < CommandFactory.COMMAND_CHANGE_FOLDER.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_CHANGE_FOLDER
					.get(i))) {
				CommandFactory.COMMAND_CHANGE_FOLDER.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_LIST_DIRS.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_LIST_DIRS
					.get(i))) {
				CommandFactory.COMMAND_LIST_DIRS.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_RENAME.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_RENAME.get(i))) {
				CommandFactory.COMMAND_RENAME.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_SORT.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_SORT.get(i))) {
				CommandFactory.COMMAND_SORT.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_MAKE_FOLDER.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_MAKE_FOLDER
					.get(i))) {
				CommandFactory.COMMAND_MAKE_FOLDER.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_MOVE.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_MOVE.get(i))) {
				CommandFactory.COMMAND_MOVE.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_COMPOSE.size(); i++) {
			if (prevCommandAlias
					.contains(CommandFactory.COMMAND_COMPOSE.get(i))) {
				CommandFactory.COMMAND_COMPOSE.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_REPLY.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_REPLY.get(i))) {
				CommandFactory.COMMAND_REPLY.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_DELETE.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_DELETE.get(i))) {
				CommandFactory.COMMAND_DELETE.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_VIEW.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_VIEW.get(i))) {
				CommandFactory.COMMAND_VIEW.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_SEND_REC.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_SEND_REC
					.get(i))) {
				CommandFactory.COMMAND_SEND_REC.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_MARK.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_MARK.get(i))) {
				CommandFactory.COMMAND_MARK.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_QUIT.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_QUIT.get(i))) {
				CommandFactory.COMMAND_QUIT.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_FLAG.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_FLAG.get(i))) {
				CommandFactory.COMMAND_FLAG.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_UNDO.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_UNDO.get(i))) {
				CommandFactory.COMMAND_UNDO.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_REDO.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_REDO.get(i))) {
				CommandFactory.COMMAND_REDO.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_RULE.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_RULE.get(i))) {
				CommandFactory.COMMAND_RULE.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_ALIAS.size(); i++) {
			if (prevCommandAlias.contains(CommandFactory.COMMAND_ALIAS.get(i))) {
				CommandFactory.COMMAND_ALIAS.remove(newComa);
				return "Success: command " + newComa + " was removed";
			}
		}
		return "Error: Alias " + newComa + " does not exist";
	}

	@Override
	public String aliasComa(String oldComa, String newComa) {

		for (int i = 0; i < CommandFactory.COMMAND_STORE_ALL.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_STORE_ALL.get(i))) {
				return "Error: Invalid args.";
			}
		}

		for (int i = 0; i < CommandFactory.COMMAND_CHANGE_FOLDER.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_CHANGE_FOLDER.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_CHANGE_FOLDER.get(i))) {
				CommandFactory.COMMAND_CHANGE_FOLDER.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_CHANGE_FOLDER
						.toString();

				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_MAKE_FOLDER.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_MAKE_FOLDER.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_MAKE_FOLDER.get(i))) {
				CommandFactory.COMMAND_MAKE_FOLDER.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_MAKE_FOLDER
						.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_LIST_DIRS.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_LIST_DIRS.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_LIST_DIRS.get(i))) {
				CommandFactory.COMMAND_LIST_DIRS.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_LIST_DIRS.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_LIST_MESSAGES.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_LIST_MESSAGES.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_LIST_MESSAGES.get(i))) {
				CommandFactory.COMMAND_LIST_MESSAGES.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_LIST_MESSAGES
						.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_RENAME.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_RENAME.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_RENAME.get(i))) {
				CommandFactory.COMMAND_RENAME.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_RENAME.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_SORT.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_SORT.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_SORT.get(i))) {
				CommandFactory.COMMAND_SORT.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_SORT.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_MOVE.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_MOVE.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_MOVE.get(i))) {
				CommandFactory.COMMAND_MOVE.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_MOVE.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_COMPOSE.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_COMPOSE.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_COMPOSE.get(i))) {
				CommandFactory.COMMAND_COMPOSE.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_COMPOSE.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_REPLY.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_REPLY.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_REPLY.get(i))) {
				CommandFactory.COMMAND_REPLY.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_REPLY.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_DELETE.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_DELETE.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_DELETE.get(i))) {
				CommandFactory.COMMAND_DELETE.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_DELETE.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_VIEW.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_VIEW.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_VIEW.get(i))) {
				CommandFactory.COMMAND_VIEW.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_VIEW.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_SEND_REC.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_SEND_REC.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_SEND_REC.get(i))) {
				CommandFactory.COMMAND_SEND_REC.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_SEND_REC.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_MARK.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_MARK.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_MARK.get(i))) {
				CommandFactory.COMMAND_MARK.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_MARK.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_FLAG.size(); i++) {
			if (oldComa.equals(CommandFactory.COMMAND_FLAG.get(i))) {
				CommandFactory.COMMAND_FLAG.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_FLAG.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_UNDO.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_UNDO.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_UNDO.get(i))) {
				CommandFactory.COMMAND_UNDO.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_UNDO.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_REDO.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_REDO.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_REDO.get(i))) {
				CommandFactory.COMMAND_REDO.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_REDO.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_RULE.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_RULE.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_RULE.get(i))) {
				CommandFactory.COMMAND_RULE.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_RULE.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_ALIAS.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_ALIAS.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_ALIAS.get(i))) {
				CommandFactory.COMMAND_ALIAS.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_ALIAS.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}
		for (int i = 0; i < CommandFactory.COMMAND_QUIT.size(); i++) {
			if (newComa.equals(CommandFactory.COMMAND_QUIT.get(i))) {
				break;
			}
			if (oldComa.equals(CommandFactory.COMMAND_QUIT.get(i))) {
				CommandFactory.COMMAND_QUIT.add(newComa);
				prevCommandAlias = CommandFactory.COMMAND_QUIT.toString();
				return "Added " + newComa + " to " + oldComa;
			}
		}

		return "Error: Invalid args.";
	}

	@Override
	public String excUndo(AbstractCommand undoComaInput) {

		try {
			undoComaInput = undoStack.pop();
			undoComaInput = undoStack.pop();
			redoStack.push(undoComaInput);
			if (undoComaInput != null) {
				undoComaInput.undoFunction();
				return undoComaInput.undoFunction();
			}
		} catch (EmptyStackException e) {
			return "Error: No command to undo";
		}
		return "Error: No command to undo";
	}

	@Override
	public boolean excRedo(AbstractCommand redoComaInput) {

		try {
			redoComaInput = redoStack.pop();
			redoComaInput.execute();
			System.out.println(redoComaInput.execute());
			return true;
		} catch (EmptyStackException e) {
			return false;
		}

	}

	public ClientModel(InterfaceConnector connector) {
		this.connector = connector;
		folders.put(inboxFolder, new Folder());
		folders.put(sentFolder, new Folder());
		folders.put(redFolder, new Folder());
		folders.put(greenFolder, new Folder());
		folders.put(blueFolder, new Folder());
		activeFolderName = "inbox";
		knownMessageIds = new ArrayList<Integer>();
		undoStack = new Stack<AbstractCommand>();
		redoStack = new Stack<AbstractCommand>();
	}

	@Override
	public String theRules(String folderName, String theType, String searchFor) {
		this.folderName = folderName;
		this.theType = theType;
		this.searchFor = searchFor;
		int numberOfMoved = 0;
		if (!folders.containsKey(folderName)) {
			return "Error: folder does not exist";
		}
		switch (theType) {
		case "recipient":
			for (int i = 0; i < folders.get(activeFolderName).getMessages()
					.size(); i++) {
				for (int j = 0; j < folders.get(activeFolderName).getMessages()
						.size(); j++) {
					if (folders.get(activeFolderName).getMessage(i) == null) {
						i++;
					}
				}
				try {
					if (folders.get(inboxFolder).getMessage(i).getRecipient()
							.contains(searchFor)) {
						folders.get(folderName).addMessage(
								folders.get(activeFolderName).getMessage(i));
						folders.get(activeFolderName).delete(i);
						numberOfMoved++;

					}
				} catch (NullPointerException e) {

				}
			}
			return "Success: Added rule and moved " + numberOfMoved
					+ " messages";
		case "sender":
			for (int i = 0; i < folders.get(activeFolderName).getMessages()
					.size(); i++) {
				for (int j = 0; j < folders.get(activeFolderName).getMessages()
						.size(); j++) {
					if (folders.get(activeFolderName).getMessage(i) == null) {
						i++;
					}
				}
				try {
					if (folders.get(inboxFolder).getMessage(i).getFrom()
							.contains(searchFor)) {
						folders.get(folderName).addMessage(
								folders.get(activeFolderName).getMessage(i));
						folders.get(activeFolderName).delete(i);
						numberOfMoved++;
					}
				} catch (NullPointerException e) {

				}
			}
			return "Success: Added rule and moved " + numberOfMoved
					+ " messages";
		case "subject":
			for (int i = 0; i < folders.get(activeFolderName).getMessages()
					.size(); i++) {
				for (int j = 0; j < folders.get(activeFolderName).getMessages()
						.size(); j++) {
					if (folders.get(activeFolderName).getMessage(i) == null) {
						i++;
					}
				}
				try {
					if (folders.get(inboxFolder).getMessage(i).getSubject()
							.contains(searchFor)) {
						folders.get(folderName).addMessage(
								folders.get(activeFolderName).getMessage(i));
						folders.get(activeFolderName).delete(i);
						numberOfMoved++;
					}
				} catch (NullPointerException e) {

				}
			}
			return "Success: Added rule and moved " + numberOfMoved
					+ " messages";
		case "body":
			for (int i = 0; i < folders.get(activeFolderName).getMessages()
					.size(); i++) {
				for (int j = 0; j < folders.get(activeFolderName).getMessages()
						.size(); j++) {
					if (folders.get(activeFolderName).getMessage(i) == null) {
						i++;
					}
				}
				try {
					if (folders.get(inboxFolder).getMessage(i).getBody()
							.contains(searchFor)) {
						folders.get(folderName).addMessage(
								folders.get(activeFolderName).getMessage(i));
						folders.get(activeFolderName).delete(i);
						numberOfMoved++;
						break;
					}
				} catch (NullPointerException e) {

				}

			}
			return "Success: Added rule and moved " + numberOfMoved
					+ " messages";

		default:
			return "Please select a valid location to search from";

		}
	}

	@Override
	public boolean checkRules() {
		if (!folders.get(activeFolderName).isEmpty()) {
			theRules(this.folderName, this.theType, this.searchFor);
			return true;
		} else {
			return false;
		}

	}

	public boolean flagMessages(String colour, int messageId) {

		InterfaceMessage msg = folders.get(activeFolderName).getMessage(
				messageId);
		if (msg != null) {
			if (!(msg.getFlag().equals("noflag"))) {
				folders.get(msg.getFlag()).delete(messageId);
			}
			msg.markFlag(colour);
			if (colour.equals("noflag")) {
				return true;
			}
			folders.get(colour).addMessage(msg);
			return true;
		} else {

			return false;
		}

	}

	@Override
	public boolean changeActiveFolder(String name) {
		if (folders.containsKey(name)) {
			activeFolderName = name;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean theLastMessage(InterfaceMessage theMessage) {
		msg = theMessage;
		return true;
	}

	@Override
	public InterfaceMessage getTheLastMessage() {
		return msg;
	}

	@Override
	public String getActiveFolderName() {
		return activeFolderName;
	}

	@Override
	public boolean deleteFolder(String name) {
		if (!folders.containsValue(name)) {
			folders.remove(name);
			return true;
		}

		return false;
	}

	@Override
	public boolean createFolder(String name) {
		if (!folders.containsKey(name)) {
			folders.put(name, new Folder());
			return true;
		}
		return false;

	}

	public Set<String> getFolderNames() {
		return folders.keySet();
	}

	@Override
	public boolean delete(int messageId) {
		if (activeFolderName.equals("red") || activeFolderName.equals("blue")
				|| activeFolderName.equals("green")) {
			if (folders.get(activeFolderName).delete(messageId)) {
				try {
					folders.get(CommandMoveMessage.folderToMove).delete(
							messageId);
				} catch (NullPointerException e) {
				}

				folders.get("inbox").delete(messageId);
				connector.markMessageForDeleting(messageId);
				return true;
			}
		} else {
			if (folders.get(activeFolderName).delete(messageId)) {
				connector.markMessageForDeleting(messageId);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean renameFolder(String oldName, String newName) {
		if (oldName.equals(inboxFolder) || oldName.equals(sentFolder)) {
			return false;
		} else if (folders.containsKey(oldName)
				&& !folders.containsKey(newName)) {
			folders.put(newName, folders.remove(oldName));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<InterfaceMessage> getMessages() {
		return folders.get(activeFolderName).getMessages();
	}

	@Override
	public boolean mark(int messageId, boolean read) {
		InterfaceMessage msg = folders.get(activeFolderName).getMessage(
				messageId);
		if (msg != null) {
			msg.markRead(read);
			return true;
		}
		return true;
	}

	@Override
	public InterfaceMessage getMessage(int messageId) {
		return folders.get(activeFolderName).getMessage(messageId);
	}

	@Override
	public boolean move(int messageId, String folderName) {
		if (activeFolderName.equals("red") || activeFolderName.equals("blue")
				|| activeFolderName.equals("green")) {
			try {
				if (getFolder(folderName).getMessage(messageId).equals(
						getFolder(getActiveFolderName()).getMessage(messageId))) {
					return false;
				}
			} catch (NullPointerException e) {
				return false;
			}
		}

		if (folderName.equals("red") || folderName.equals("blue")
				|| folderName.equals("green")) {
			return false;
		}
		InterfaceMessage msg;
		InterfaceFolder folder = folders.get(folderName);
		msg = folders.get(activeFolderName).getMessage(messageId);

		if (folder == null || msg == null) {
			return false;
		}

		folder.addMessage(msg);
		folders.get(activeFolderName).delete(messageId);
		return true;
	}

	@Override
	public boolean checkForNewMessages() {
		int messageId;
		String[] messages;
		try {
			messages = connector.listMessages().split("\r\n");
			for (String message : messages) {
				if (!message.isEmpty()) {
					messageId = Integer.parseInt(message);
					if (!knownMessageIds.contains(messageId)) {
						knownMessageIds.add(messageId);
						folders.get(inboxFolder).addMessage(
								parseMessage(connector.retrMessage(messageId),
										messageId));
					}
				}
			}

			return true;
		} catch (IOException e) {
			return false;
		}

	}

	private InterfaceMessage parseMessage(String messageStr, int messageId) {
		Message message;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String[] messageString = messageStr.split("\r\n", 6);
		try {
			message = new Message();
			message.setId(messageId);
			message.setRecipient(messageString[0].split(":", 2)[1]);
			message.setFrom(messageString[1].split(":", 2)[1]);
			message.setDate(dateFormat.parse(messageString[2].split(":", 2)[1]));
			message.setSubject(messageString[3].split(":", 2)[1]);
			message.setBody(messageString[5]);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		return message;
	}

	@Override
	public boolean sendMessage(InterfaceMessage msg) {
		folders.get("sent").addMessage(msg);
		// message ID provided by the server once sent.
		String successResponse = connector.sendMessage(msg.toString());
		int providedId = Integer.parseInt(successResponse.split(" ")[1]);
		msg.setId(providedId);
		return true;
	}

	@Override
	public void sortByDate(boolean ascending) {
		folders.get(activeFolderName).sortByDate(false);

	}

	@Override
	public void sortBySubject(boolean ascending) {
		folders.get(activeFolderName).sortBySubject(true);
	}

	@Override
	public InterfaceFolder getFolder(String folderName) {
		return folders.get(folderName);
	}

}
