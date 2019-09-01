# LessonOne
A Java in-console interface for user-driven lessons.
To allow for the simplification of student-driven code exploration,
the lesson is consolidated to just 3 lines of code:
```Java
Teacher teacher = new Teacher(<Headers>, <Content>);
teacher.checkLesson();
teacher.teach();
```
***
### Headers and Content
Note that Lessons are written as a series of headers and content. For example, if we had:
```Java
ArrayList<String> Headers = new ArrayList<>(Arrays.asList("text", "text"));
ArrayList<String> Content = new ArrayList<>(Arrays.asList("Hello!", "Welcome to the teacher."));
```
The teacher would output this:
```cmd
(Press enter to begin...)
Hello!
Welcome to the teacher.
(Ending lesson...)
```
The headers are easily extensible, as all headers are a `<String, Command>` key-value pair, allowing for more interactivity!

---
### All Current Headers:
- text  
  - Displays content as text, and waits for user to press enter to continue to the next header.
- requestInput
  - Prompts the user to input the given content character-for-character
- showOutput
  - Shows the content as the expected output.