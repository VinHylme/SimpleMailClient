public class CommandRules extends AbstractCommand {
	private static final int REQ_ARGS = 4;

	public CommandRules(InterfaceClientModel model, String[] flags)
			throws BadCommandException {
		super(model, flags);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() {
		return model.theRules(flags[1], flags[2], flags[3]);
	}

	@Override
	protected boolean validateArguments() {
		return flags.length == REQ_ARGS;
	}

}
