public class CommandReply extends AbstractCommand {
	private View view;

	InterfaceMessage msg;
	private static final int REQ_ARGS = 1;

	public CommandReply(InterfaceClientModel model, View view,
			String[] commandIn) throws BadCommandException {
		super(model, commandIn);
		this.view = view;
	}

	@Override
	public String execute() {
		InterfaceMessage thelastmessage = model.getTheLastMessage();
		if (thelastmessage != null) {
			view.reply(this, thelastmessage);
			if (model.sendMessage(msg)) {
				return "Success: sent";
			} else {
				return "Failed: Could not send.";
			}
		}
		return "Error: Must first view a message to reply.";

	}

	public void setMessage(Message msg) {
		this.msg = msg;
	}

	@Override
	public boolean validateArguments() {
		return flags.length == REQ_ARGS;
	}
}