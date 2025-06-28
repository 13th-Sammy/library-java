# Library Management System

This is a tool built in Java, that emulates a Library <br>
Management System. <br>

The User can add or remove books, issue books to Users <br>
and keep track of all transactions, and the state of the <br>
library as well as those of individual users.

# Features 

- View Books <br>
- Add Book <br>
- Remove Book <br>
- Search Book <br>
- Register User <br>
- Remove User <br>
- View User <br>
- View All Users <br>
- Issue Book <br>
- Return Book <br>
- View Logs <br>
- Clear All Logs <br>

# Project Structure

Main.java :-
- This is the entry point for our program and has a main <br>
function - main() creates a Menu object which launches the menu <br>
that runs the program.

Menu.java :-
- This creates the GUI menu with a button for each operation. <br>
- Creates a Jframe frame. Inside it exists JPanel menuPanel.
- menuPanel layout is set and a button corresponding to each Library <br>
operation is placed inside menuPanel, inside frame.
- If View Books clicked, shows ViewBooks panel <br>
- If Add Book clicked, calls addBook() <br>
- If Remove Book clicked, calls removeBook() <br>
- If Search Book clicked, shows SearchBooks panel <br>
- If Register User clicked, calls registerUser() <br>
- If Remove User clicked, calls removeUser() <br>
- If View User clicked, shows ViewUser panel <br>
- If View All Users clicked, shows ViewAllUsers panel <br>
- If Issue Book clicked, calls issueBook() <br>
- If Return Book clicked, calls returnBookL() <br>
- If View Logs clicked, shows ViewLogs panel <br>
- If Clear All Logs clicked, calls clearLogs() 

Library.java :-
- Has BookList HashMap to store all book Ids and Book objects. <br>
- Has UserList HashMap to store all user IDs and User objects. <br>
- A getBookList() method that returns HashMap BookList. <br>
- A getUserList() method that returns HashMap UserList <br>
- An addBook() method takes inputs bookID, title and author, <br>
creates Book object and pushes it into BookList HashMap - bookID, book <br>
- A removeBook() method that asks for a bookID and removes that key <br>
from the BookList HashMap. <br>
- A registerUser() method that takes inputs uid and user name and <br>
creates User object, then pushes it into UserList HashMap - uid, user. <br>
- A removeUser() method that takes uid as input and removes the user from <br>
UserList HashMap with that uid, if the user has no borrowed books remaining. <br>
- A issueBook() method which takes in uid and book id, and adds the book id <br>
with count+noOfCopies in BorrowedBooks HashMap of user object with matching uid - by <br>
calling user object.borrrowBook(bid). Updates available copies of book object <br>
with matching bid. <br>
- A returnBookL() method which takes in uid and book id, and adds the book id <br>
with count-noOfCopies in BorrowedBooks HashMap of user object with matching uid - by <br>
calling user object.returnBook(bid). Updates available copies of book object <br>
with matching bid. 

Book.java :- <br>
- Serves as the holder of all information regarding one book. <br>
- Has attributes bookID, title, author, totalCopies, availCopies. <br>
- Has parameterised constructor for bookID, title and author. <br>
- Has getters for everything, and setters for totalCopies and availCopies.

User.java :-
- Serves as the holder of all information regarding one user. <br>
- Has attributes uid, name, and a HashMap BooksBorrowed for <br>
bookID and copies borrowed. <br>
- Has parameterised constructor for uid and name. <br>
- Has getters for name and uid. Has borrowBook(), returnBook() <br>
and getBorrowedMap() regarding BooksBorrowed HashMap. <br>
- borrowBook() adds id, count+noOfCopies to BooksBorrowed. <br>
- returnBook() adds id, count-noOfCopies to BooksBorrowed and if count-noOfCopies <br>
is == 0, it removes the entry. <br>
- getBorrowedMap() to return a copy of the HashMap BooksBorrowed. 

ViewBooks.java :-
- Handles the GUI and logic for viewing books. It is a JPanel. Inside it there are <br>
more JPanels for showing different parts of the page as mentioned below. <br>
- Creates a booksPanel inside ViewBooks JPanel. booksPanel will have <br>
more Jpanels called blocks inside it. <br>
- Iterates over books HashMap (from getBookList()) and prints each book info in a block <br>
inside booksPanel. <br>
- Adds scrollpane with booksPanel to ViewBooks JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewBooks JPanel. <br>
backButton switches contentPane to menuPanel. 

SearchBooks.java :-
- Handles the GUI and logic for searching books. It is a Jpanel. Inside it there are <br>
more JPanels for showing different parts of the page as mentioned below. <br>
- Creates a searchedPanel inside SearchBooks JPanel. searchedPanel will have more <br>
JPanels called blocks inside it. <br>
- Iterates over books HashMap (from getBookList()) and prints searched title book info in a <br>
block inside searchedPanel. <br>
- Adds scrollpane with searchedPanel to SearchBooks JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to SearchBooks JPanel. <br>
backButton switches contentPane to menuPanel.

ViewUser.java :-
- Handles the GUI and logic for viewing a User. It is a JPanel and has other <br>
Jpanels inside it to show different parts of the page as mentioned below. <br>
- Creates a userPanel inside ViewUser panel. userPanel will have more Jpanels <br>
called blocks inside it. <br>
- Checks if users HashMap (from getUserList()) contains searched Uid and prints <br> 
corresponding user info in a block inside userPanel. <br>
- Adds scrollpane with userPanel to ViewUser JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewUser JPanel. <br>
backButton switches contentPane to menuPanel.

ViewAllUsers.java :-
- Handles the GUI and logic for viewing a User. It is a JPanel and has other <br>
Jpanels inside it to show different parts of the page as mentioned below. <br>
- Creates a usersPanel inside ViewAllUsers panel. usersPanel will have more Jpanels <br>
called blocks inside it. <br>
- Iterates over users HashMap (from getUserList()) and prints user info in a block <br>
inside usersPanel. <br>
- Adds scrollpane with usersPanel to ViewAllUsers JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewAllUsers JPanel. <br>
backButton switches contentPane to menuPanel. <br>

Logger.java :-
- Contains ArrayList of Strings - logs to store logs. <br>
- Has a static object called instance and a getter for it - getInstance(). <br>
- Has setter log() to add string to ArrayList logs. <br>
- Has getLogs() which returns the ArrayList. <br>
- Logger objects - which are the static instance are created in <br> 
Library.java and Menu.java using Logger.getInstance() - to log all the different <br>
operations with object.log(string). <br>

ViewLogs.java :-
- Handles the GUI and logic for viewing logs. It is a Jpanel and has other Jpanels <br>
inside it. <br>
- Inside ViewLogs JPanel, there is a logPanel Jpanel. <br>
- A logs variable holds the ArrayList of logs (using getLogs()). <br>
- Iterates over logs and prints out every log info inside logsPanel, which is <br>
inside JPanel ViewLogs. <br>
- Adds scrollPane with logPanel to ViewLogs JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewLogs JPanel. <br>
backButton switches contentPane to menuPanel.