import static org.junit.Assert.*;

import org.junit.Test;


public class TestUndo {
	Controller controller = new Controller();
	@Test
	public void test(){
		assertEquals("Error: No command to undo",controller.processInput("undo"));
		controller.processInput("receive");
		assertEquals("Error: Cannot undo command: class CommandReceive",controller.processInput("undo"));
		controller.processInput("flag red 1");
		assertEquals("Success: flagged 1 as noflag",controller.processInput("undo"));
		controller.processInput("mark -r 2");
		assertEquals("Success: tried to mark 2 as unread",controller.processInput("undo"));
		controller.processInput("delete 0");
		assertEquals("Error: Cannot undo command: class CommandDelete",controller.processInput("undo"));
		controller.processInput("mkf new");
		assertEquals("Success: Deleted folder new",controller.processInput("undo"));
		controller.processInput("mkf newOne");
		controller.processInput("move 0 newOne");
		
		
	}
	
}
