
public class CommandAlias extends AbstractCommand {

	private static final int REQ_ARGS = 3;
	
	public CommandAlias(InterfaceClientModel model, String[] flags)
			throws BadCommandException {
		super(model, flags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() {
		
		return model.aliasComa(flags[1], flags[2]);
	}

	@Override
	protected boolean validateArguments() {
		return flags.length == REQ_ARGS;
	}
	
	@Override
	public String undoFunction(){
		
		
		
		return model.removeAlias(flags[1], flags[2]);
	}

}
