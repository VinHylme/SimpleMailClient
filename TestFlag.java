import static org.junit.Assert.*;

import org.junit.Test;
public class TestFlag {
	Controller controller = new Controller();
	@Test
	public void test(){
		assertEquals("Error: No messages exist",controller.processInput("flag red 0"));
		controller.processInput("receive");
		assertEquals("Success: flagged 0 as red",controller.processInput("flag red 0"));
		controller.processInput("cf red");
		assertEquals("Error: Check message 0 exists and that inbox is a folder,"
				+ " and that neither the current folder or target folder are smart folders",controller.processInput("move 0 inbox"));
		assertEquals("Successfully deleted 0",controller.processInput("delete 0"));
		controller.processInput("cf inbox");
		controller.processInput("flag red 1");
		controller.processInput("cf red");
		assertEquals("Success: flagged 1 as blue", controller.processInput("flag blue 1"));
		controller.processInput("mkf newFolder");
		controller.processInput("cf inbox");
		controller.processInput("move 2 newFolder");
		controller.processInput("cf newFolder");
		assertEquals("Success: flagged 2 as red",controller.processInput("flag red 2"));
	}
}
