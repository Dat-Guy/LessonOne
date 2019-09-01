package SamurottDev;

public class Main {

    public static void main(String[] args) throws InterruptedException{
    	
    	Teacher teacher = new Teacher(Lessons.HelloWorld.HEADERS, Lessons.HelloWorld.CONTENT); //Initializes a new Teacher with the 'HelloWorld' lesson
    	
    	teacher.checkLesson(); //Ensures the lesson is safe to execute.
    	
    	teacher.teach(); //Teaches the lesson. Note that this will lock whatever thread to this command until terminated or the lesson is completed.
    	
    }
    
    
}
