# todolist
A project with a pop-up user interface application where the users can effectively manage their workload and time. This application helps improve productivity and enforces efficient scheduling.
#  **Not So Hard to do ToDoList**

##  - *I have decided todo a todo list*

### Usability and Interest
+ The application will enable users to add, delete and modify the tasks that are inputted to the todo list. It will also 
allow the user to mark the tasks as completed, and it will place the completed tasks in a separate list. The users will 
also be able to view a list of the tasks and the completed tasks.

* This application is mainly for users that have quite a few tasks on a daily basis and provides a medium for them to 
access their next tasks without having to go through the hassle of recollection.

- My main intention for doing this to be able to explicitly challenge myself, and implicitly build something that I make
use of on a regular basis.

### User Stories
1. Being a user, I should be able to include a task to the todo list.
2. Being a user, I should be able to remove a task from the todo list.
3. Being a user, I should be able to mark a task as complete on the todo list.
4. Being a user, I should be able to view all the tasks as well as all the completed tasks.
5. Being a user, I should be able to modify an already existing task on the todo list. 
6. Being a user, I should be able to save the list of tasks to file.
7. Being a user, I should be able to load my todo list from file when the program resumes and start where I stopped
previously.

### Phase 4
I have chosen to test and design a class that is robust with one method that throws a checked exception. The class is 
"ToDoList.java" which has a method "validateIndex" that throws an IllegalInputException if index < 0. I have a test in
"ToDoListTest.java" called "testValidateIndex" that comprises two cases: one where the exception is expected and another
where the exception is not expected.

### Phase 5
1. (Solved) The first problem I noticed was with the Single Responsibility Principle in my ToDoListPanel class, as a 
part of phase 3 I had included an audio component in "ToDoListPanel", but it was placed in the ToDoListPanel class whose 
responsibility was to open a new window that displays the ToDoList. So I have made a new class: "PlaySound", whose 
single responsibility is to play a sound file upon clicking the required button "Add and View all current Tasks" in the 
GUI class.
2. (Solved) Another problem that I noticed was with my "GUI" class having poor cohesion, so I made a new "Outline" class
that made the functionality of the "GUI" class clearer and removed a lot of repetitive code that can now be accessed 
from the "Outline" class. For example, to add more panels to my app I could utilize the basic framework from "Outline" 
and add more common methods to it improve the cohesion.
3. (Solved) Initially in phase 3, I had the entire implementation of my GUI in my "GUI" class, including the part where
I make a new window that displays the ToDoList. I had made the improvement of splitting the two windows into separate 
classes in order to improve the cohesion, in addition to that I also had some methods that had similar implementations,
and I ended up just combining them into one designated method. For example, I had a textArea and a textField initially,
but I decided to use the textField as it better served my purpose for the application.
4. Another possible improvement that I considered was having a boolean in "Task" to check if the task has been completed 
or not, while I realize that this would improve the cohesion of the Task class, I figure that it is much cleaner just 
having two separate lists that deal whether the task is complete or not.
5. ToDoListApp class has coupling between two methods "doViewAllTasks" and "doViewAllCompletedTasks", which can be 
resolved upon finishing the above mentioned improvement.
