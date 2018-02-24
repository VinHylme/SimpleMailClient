
public class CommandUndo extends AbstractCommand {
	private static final int REQ_ARGS = 1;
	AbstractCommand command;
	public CommandUndo(InterfaceClientModel model, String[] flags)
			throws BadCommandException {
		super(model, flags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() {
		/*
		if(model.excUndo(command)){
			return ;
		}else{
			return "Error: No command to undo";
		}*/
		return model.excUndo(command);
		
	}

	@Override
	protected boolean validateArguments() {
		return flags.length == REQ_ARGS;
	}

}
