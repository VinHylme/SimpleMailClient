import java.util.ArrayList;

public class CommandFactory {

	private static CommandFactory instance; // singleton

	private InterfaceClientModel model;
	private View view;
	
	public static final ArrayList<String> COMMAND_STORE_ALL = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_CHANGE_FOLDER = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_LIST_DIRS = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_LIST_MESSAGES = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_RENAME = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_SORT = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_MAKE_FOLDER = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_MOVE = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_COMPOSE = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_REPLY = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_DELETE = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_VIEW = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_SEND_REC = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_MARK = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_QUIT = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_FLAG = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_UNDO = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_REDO = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_RULE = new ArrayList<String>();
	public static final ArrayList<String> COMMAND_ALIAS = new ArrayList<String>();
	public static CommandFactory getInstance() {
		if (instance == null) {
			instance = new CommandFactory();
		}
		return instance;
	}	
	
	CommandFactory() {
		
		COMMAND_CHANGE_FOLDER.add("cf");
		COMMAND_LIST_DIRS.add("listfolders");
		COMMAND_LIST_MESSAGES.add("list");
		COMMAND_RENAME.add("rename");
		COMMAND_SORT.add("sort");
		COMMAND_MAKE_FOLDER.add("mkf");
		COMMAND_MOVE.add("move");
		COMMAND_COMPOSE.add("compose");
		COMMAND_REPLY.add("reply");
		COMMAND_DELETE.add("delete");
		COMMAND_VIEW.add("view");
		COMMAND_SEND_REC.add("receive");
		COMMAND_MARK.add("mark");
		COMMAND_QUIT.add("quit");
		COMMAND_FLAG.add("flag");
		COMMAND_UNDO.add("undo");
		COMMAND_REDO.add("redo");
		COMMAND_RULE.add("cr");
		COMMAND_ALIAS.add("alias");
	}
	

	public void setReferences(InterfaceClientModel model, View view) {
		this.model = model;
		this.view = view;
	}


	public void addNewAliases(){
		COMMAND_STORE_ALL.addAll(COMMAND_ALIAS);
		COMMAND_STORE_ALL.addAll(COMMAND_CHANGE_FOLDER);
		COMMAND_STORE_ALL.addAll(COMMAND_LIST_DIRS);
		COMMAND_STORE_ALL.addAll(COMMAND_LIST_MESSAGES);
		COMMAND_STORE_ALL.addAll(COMMAND_RENAME);
		COMMAND_STORE_ALL.addAll(COMMAND_SORT);
		COMMAND_STORE_ALL.addAll(COMMAND_MAKE_FOLDER);
		COMMAND_STORE_ALL.addAll(COMMAND_MOVE);
		COMMAND_STORE_ALL.addAll(COMMAND_COMPOSE);
		COMMAND_STORE_ALL.addAll(COMMAND_REPLY);
		COMMAND_STORE_ALL.addAll(COMMAND_DELETE);
		COMMAND_STORE_ALL.addAll(COMMAND_VIEW);
		COMMAND_STORE_ALL.addAll(COMMAND_SEND_REC);
		COMMAND_STORE_ALL.addAll(COMMAND_MARK);
		COMMAND_STORE_ALL.addAll(COMMAND_QUIT);
		COMMAND_STORE_ALL.addAll(COMMAND_FLAG);
		COMMAND_STORE_ALL.addAll(COMMAND_UNDO);
		COMMAND_STORE_ALL.addAll(COMMAND_REDO);
		COMMAND_STORE_ALL.addAll(COMMAND_RULE);
		COMMAND_STORE_ALL.addAll(COMMAND_ALIAS);
	}
	
	public AbstractCommand buildCommand(String command)
			throws BadCommandException {

		
		String[] commandInput = command.split(" ");

		for(int i=0;i<COMMAND_CHANGE_FOLDER.size();i++){
			if(commandInput[0].equals(COMMAND_CHANGE_FOLDER.get(i))){
				return new CommandChangeFolder(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_LIST_DIRS.size();i++){
			if(commandInput[0].equals(COMMAND_LIST_DIRS.get(i))){
				return new CommandListFolders(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_LIST_MESSAGES.size();i++){
			if(commandInput[0].equals(COMMAND_LIST_MESSAGES.get(i))){
				return new CommandListMessages(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_RENAME.size();i++){
			if(commandInput[0].equals(COMMAND_RENAME.get(i))){
				return new CommandRename(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_SORT.size();i++){
			if(commandInput[0].equals(COMMAND_SORT.get(i))){
				return new CommandSort(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_MAKE_FOLDER.size();i++){
			if(commandInput[0].equals(COMMAND_MAKE_FOLDER.get(i))){
				return new CommandMakeFolder(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_MOVE.size();i++){
			if(commandInput[0].equals(COMMAND_MOVE.get(i))){
				return new CommandMoveMessage(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_COMPOSE.size();i++){
			if(commandInput[0].equals(COMMAND_COMPOSE.get(i))){
				return new CommandCompose(model,view,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_REPLY.size();i++){
			if(commandInput[0].equals(COMMAND_REPLY.get(i))){
				return new CommandReply(model,view,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_DELETE.size();i++){
			if(commandInput[0].equals(COMMAND_DELETE.get(i))){
				return new CommandDelete(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_VIEW.size();i++){
			if(commandInput[0].equals(COMMAND_VIEW.get(i))){
				return new CommandView(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_MARK.size();i++){
			if(commandInput[0].equals(COMMAND_MARK.get(i))){
				return new CommandMark(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_FLAG.size();i++){
			if(commandInput[0].equals(COMMAND_FLAG.get(i))){
				return new CommandFlag(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_UNDO.size();i++){
			if(commandInput[0].equals(COMMAND_UNDO.get(i))){
				return new CommandUndo(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_REDO.size();i++){
			if(commandInput[0].equals(COMMAND_REDO.get(i))){
				return new CommandRedo(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_RULE.size();i++){
			if(commandInput[0].equals(COMMAND_RULE.get(i))){
				return new CommandRules(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_ALIAS.size();i++){
			if(commandInput[0].equals(COMMAND_ALIAS.get(i))){
				return new CommandAlias(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_SEND_REC.size();i++){
			if(commandInput[0].equals(COMMAND_SEND_REC.get(i))){
				return new CommandReceive(model,commandInput);
			}
		}
		
		for(int i=0;i<COMMAND_QUIT.size();i++){
			if(commandInput[0].equals(COMMAND_QUIT.get(i))){
				return new CommandQuit(model,commandInput);
			}
		}
	
		return new CommandBad(model, commandInput).setMessage("Error: Not a valid command: "
			+ command);
	}
}
