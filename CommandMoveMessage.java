
public class CommandMoveMessage extends AbstractCommand {

	private static final int REQ_ARGS = 3;
	private int messageId;
	 String prevFolder;
	 public static String folderToMove;
	public CommandMoveMessage(InterfaceClientModel model, String[] commandInput)
			throws BadCommandException {
		super(model, commandInput);
	}

	@Override
	public boolean validateArguments() {
		
		if (flags.length != REQ_ARGS) {
			return false;
		}

		try {
			messageId = Integer.parseInt(flags[1]);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

	@Override
	public String execute() {
		folderToMove = flags[2];
		prevFolder = model.getActiveFolderName(); 

		if (model.move(messageId, flags[2])) {
			return "Success: Moved " + messageId + " to " + flags[2];
		} else {
			return "Error: Check message " + messageId + " exists and that "
					+ flags[2] + " is a folder, and that neither the current folder or target folder are smart folders";
		}
	}
	@Override
	public String undoFunction(){
		model.changeActiveFolder(flags[2]);
		if(model.move(messageId, prevFolder)){
			model.changeActiveFolder(prevFolder);
			return "Success: Moved " + messageId + " to " + flags[2];
		}else{
			return "Error: Check message " + messageId + " exists and that " + flags[2] + " is a folder, and that neither the current folder or target folder are smart folders";
		}
	}
}
