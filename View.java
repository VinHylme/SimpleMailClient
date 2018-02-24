import java.util.Date;
import java.util.Scanner;

public class View {
	private Controller controller;
	private static final String PROMPT = "?> ";
	private Scanner input;
	InterfaceClientModel model;
	public View(Controller controller) {
		this.controller = controller;
		this.model = model;
		input = new Scanner(System.in);
	}

	public void getInput() {
		String response = "";
		String userInput = "";
		System.out.println("===== G51ISO Mail Client ====");

		// special cases needed for quit and compose.
		while (!response.equals(CommandQuit.QUIT_MESSAGE)) {
			userInput = getUserInput();
			response = controller.processInput(userInput);
			System.out.println(response);
		}
	}
	
	public void reply (CommandReply command, InterfaceMessage theLastMessage){

		String recipient;
		String from;
		String subject;
		String body;
		Date date = new Date();
		System.out.println("To: "+ theLastMessage.getRecipient());
		recipient = theLastMessage.getRecipient();
		System.out.println("From:" + theLastMessage.getFrom());
		from = theLastMessage.getFrom();
		System.out.println("Subject: "+ "Re:"+ theLastMessage.getSubject());
		subject = "RE:"+ theLastMessage.getSubject();
		System.out.println("Body: ");
		body = input.nextLine();
		
		Message msg = new Message();
		msg.setRecipient(recipient);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setBody(body);
		msg.setDate(date);
		command.setMessage(msg);
	}
	
	public void compose(CommandCompose command) {
		String recipient;
		String from;
		String subject;
		String body;
		Date date = new Date();

		System.out.println("To: ");
		recipient = input.nextLine();
		System.out.println("From: ");
		from = input.nextLine();
		System.out.println("Subject: ");
		subject = input.nextLine();
		System.out.println("Body: ");
		body = input.nextLine();

		Message msg = new Message();
		msg.setRecipient(recipient);
		msg.setFrom(from);
		msg.setSubject(subject);
		msg.setBody(body);
		msg.setDate(date);
		command.setMessage(msg);
	}

	private String getUserInput() {
		System.out.print(PROMPT);
		return input.nextLine();
	}
}
