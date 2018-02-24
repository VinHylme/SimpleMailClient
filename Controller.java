import emailConnector.InterfaceConnector;
import emailConnector.StandardConnector;

public class Controller {
	private InterfaceClientModel model;
	private CommandFactory commandFactory;
	private View view;
	private InterfaceConnector connector;

	public Controller() {
		view = new View(this);
		connector = StandardConnector.getInstance();
		model = new ClientModel(connector);
		commandFactory = CommandFactory.getInstance();
		commandFactory.setReferences(model, view);
	}

	public void begin() {
		view.getInput();
	}

	public String processInput(String theInput) {
		String response;
		AbstractCommand command;

		try {
			command = commandFactory.buildCommand(theInput);
			model.undoFun(command);
			model.checkRules();
			commandFactory.addNewAliases();
			
			response = command.execute();
		} catch (BadCommandException bce) {
			response = bce.getMessage();
		}
		return response;
	}
}
