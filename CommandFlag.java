
public class CommandFlag extends AbstractCommand  {
	private static final int REQ_ARGS = 3;
	int messageId;
	String prevFlag;
	
	public CommandFlag(InterfaceClientModel model, String[] flags)
			throws BadCommandException {
		super(model, flags);
		if(!model.getFolder(model.getActiveFolderName()).getMessages().isEmpty()){
			prevFlag = model.getFolder(model.getActiveFolderName()).getMessage(messageId).getFlag();
		}
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() {
		
		if(model.flagMessages(flags[1], messageId)){
			return "Success: flagged "+ flags[2] + " as "+ flags[1];
		}else{
			return "Error: No messages exist";
		}
		
	}

	@Override
	protected boolean validateArguments() {
		if (flags.length != REQ_ARGS) {
			return false;
		}

		try {
			messageId = Integer.parseInt(flags[2]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	@Override
	public String undoFunction(){
		
		if(model.flagMessages(prevFlag, messageId)){
			return "Success: flagged " + flags[2] + " as " + prevFlag;
		}else{
			return "Error:";
		}

	}
	
}
