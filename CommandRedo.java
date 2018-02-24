
public class CommandRedo extends AbstractCommand {
	private static final int REQ_ARGS = 1;
	AbstractCommand command;
	public CommandRedo(InterfaceClientModel model, String[] flags)
			throws BadCommandException {
		super(model, flags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() {
		if(model.excRedo(command)){
			return "";
		}else{
			return "Error: No command to redo";
		}
	}

	@Override
	protected boolean validateArguments() {
		return flags.length == REQ_ARGS;
	}

}
