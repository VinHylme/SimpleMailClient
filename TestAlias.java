import static org.junit.Assert.*;

import org.junit.Test;


public class TestAlias {
	Controller controller = new Controller();
	@Test
	
	public void testing(){
		
		assertEquals("Added rec to receive",controller.processInput("alias receive rec"));
		assertEquals("Added r to rec",controller.processInput("alias rec r"));
		assertEquals("Error: Invalid args.",controller.processInput("alias rec receive"));
		assertEquals("Error: Invalid args.",controller.processInput("alias reply r"));
	}
	
}
