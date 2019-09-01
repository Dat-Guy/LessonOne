package SamurottDev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Teacher {
	
	private ArrayList<String> lessonHeaders; //Where the headers will be stored on initialization
	private ArrayList<String> lessonContent; //Where the content wil be stored on initialization
	
	private static Scanner scanner = new Scanner(System.in); //We make ONE instance of a scanner, since this will only function on the default console anyways.
	private static HashMap<String, Command> headerCommand = new HashMap<>(); //All header commands (see interface Command), which are defined once globally since they are not modified.
	
	public Teacher(ArrayList<String> headers, ArrayList<String> content){
		lessonHeaders = headers;
		lessonContent = content;
	}
	
	//Make some nice rolling text
	private static void rollingText(String string) {
		try {
			for (var i = 0; i < string.length(); i++) {
				System.out.print(string.substring(0, i + 1) + "\r");
				Thread.sleep(10);
			}
		} catch (InterruptedException e){
			System.out.print("\r" + string);
		}
	}
	
	//Check to ensure the lesson is valid
	public void checkLesson(){
		if (lessonHeaders.size() != lessonContent.size()){
			throw new Error("Mismatch between lesson headers and lesson content...");
		}
		for (var i = 0; i < lessonHeaders.size(); i++){
			if (lessonHeaders.get(i).isBlank() || !headerCommand.containsKey(lessonHeaders.get(i))){
				throw new Error("One or more headers does not correspond to an implemented action...\nIndex: " + i + "\nValue: " + lessonHeaders.get(i));
			}
		}
		for (var i = 0; i < lessonContent.size(); i++){
			if (!(lessonContent.get(i).strip().equals(lessonContent.get(i)))){
				throw new Error("Input string contains excess whitespace.");
			}
		}
	}
	
	//Teaches the lesson
	public void teach() throws InterruptedException{
		rollingText("(Press enter to begin...)");
		scanner.nextLine();
		
		for (var i = 0; i < lessonHeaders.size(); i++){
			headerCommand.get(lessonHeaders.get(i)).execute(lessonContent.get(i));
		}
		
		rollingText("(Ending lesson...)");
	}
	
	//Defines all header commands
	static {
		//Header: 'text'
		//Displays any text content as rolling text.
		//WARNING: Content MUST not contain any cursor modifiers, aka \r, \t, or \n
		headerCommand.put("text", (content) -> {
			rollingText(content);
			scanner.nextLine();
		});
		//Header: 'requestInput'
		//Requests user to type out a given input character-for-character
		headerCommand.put("requestInput", (content) -> {
			//Prompts the user for input
			rollingText("In the space below, type \"" + content + "\"\n");
			//Gets user input
			String givenInput = scanner.nextLine().strip();
			//While the input fails to match the desired string,
			while (!givenInput.endsWith(content)){
				//prompt the user to try again
				rollingText("That was a bad input! Please type \"" + content + "\"\n");
				//Get user input
				givenInput = scanner.nextLine();
			}
		});
		//Header: 'showOutput'
		//Shows what some code would be expected to print.
		headerCommand.put("showOutput", (content) -> {
			//Top boundary of code output
			System.out.println("======== RUNNING CODE ========\n");
			//Prints actual code output
			System.out.println(content);
			//Bottom boundary of code output
			System.out.println("\n=== FINISHED  SUCCESSFULLY ===");
		});
	}
}
