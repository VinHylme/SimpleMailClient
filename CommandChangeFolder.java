public class CommandChangeFolder extends AbstractCommand {

	private final int REQ_ARGS = 2;
	String curFolder;
	public CommandChangeFolder(InterfaceClientModel model, String[] flags)
			throws BadCommandException {
		super(model, flags);
	}

	@Override
	public String execute() {
		curFolder = model.getActiveFolderName();
		if (model.changeActiveFolder(flags[1])) {
			return "Success: Changed folder to " + flags[1];
		} else {
			return "Error: Check " + flags[1] + " exists.";
		}
	}

	@Override
	public boolean validateArguments() {
		return flags.length == REQ_ARGS;
	}

	@Override
	public String undoFunction(){
		if(model.changeActiveFolder(curFolder)){
			return "Success: Changed back to " + curFolder;
		}else{
			return "Error: ";
		}
	}
}
