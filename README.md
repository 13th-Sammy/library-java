# Library Management System

This is a tool built in Java, that emulates a Library <br>
Management System. 

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
- Clear All Logs 

# Project Structure

Databases :-
- Has library.db which stores BookList, UserList and BorrwedBooks <br>
tables. <br>
- Has logs.db which stores Logs table. 

Main.java :-
- This is the entry point for our program and has a main <br>
function - main() creates a Menu object which launches the menu <br>
that runs the program.

Menu.java :-
- This creates the GUI menu with a button for each operation. <br>
- Creates a Jframe frame. Inside it exists JPanel menuPanel. <br>
- menuPanel layout is set and a button corresponding to each Library <br>
operation is placed inside menuPanel, inside frame. <br>
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
- If Clear All Logs clicked, calls clearLogs() <br>
- Only clearLogs() exists in Logger.java, the other methods <br>
mentioned here exist in Library.java. The panels are their <br>
own .java files. 

Logger.java :-
- Has a static object called instance and a getter for it - getInstance(). <br>
- Has log() which inserts a log message into Logs table of logs.db. <br>
- Has clearLogs() which deletes all rows from Logs table of logs.db. <br>
- Logger objects - which are the static instance are created in <br> 
Library.java and Menu.java using Logger.getInstance() - to log all the different <br>
operations with object.log(string). 

Library.java :-
- An addBook() method takes inputs bookID, title, author, and number of <br>
copies and inserts them into BookList table of library.db. <br>
- A removeBook() method that asks for a bookID and removes that row <br>
from BookList table of library.db. <br>
- A registerUser() method that takes inputs uid and user name and <br>
inserts them into UserList table of library.db. <br>
- A removeUser() method that takes uid as input and removes that row <br>
from UserList table of library.db, if all books have been returned. <br>
- A issueBook() method which takes in uid, book id and number of copies, <br>
and adds them to BorrowedBooks table of library.db. Also updates available_copies <br>
in BookList table. <br>
- A returnBookL() method which takes in uid, book id and number of copies, <br>
and decrements the number of copies or deletes the row if copies==0 in BorrowedBooks <br>
where uid and book id match. Also updates available_copies in BookList table.

ViewBooks.java :-
- Handles the GUI and logic for viewing books. It is a JPanel. Inside it there are <br>
more JPanels for showing different parts of the page as mentioned below. <br>
- Creates a booksPanel inside ViewBooks JPanel. booksPanel will have <br>
more Jpanels called blocks inside it. <br>
- Iterates over results of SELECT * FROM BookList and prints each book info in a block <br>
inside booksPanel. <br>
- Adds scrollpane with booksPanel to ViewBooks JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewBooks JPanel. <br>
backButton switches contentPane to menuPanel. 

SearchBooks.java :-
- Handles the GUI and logic for searching books. It is a Jpanel. Inside it there are <br>
more JPanels for showing different parts of the page as mentioned below. <br>
- Creates a searchedPanel inside SearchBooks JPanel. searchedPanel will have more <br>
JPanels called blocks inside it. <br>
- Iterates over results of SELECT * FROM BookList WHERE title LIKE '%' || input || '%' COLLATE NOCASE <br>
and prints the info of each result in a block inside searchedPanel. <br>
- Adds scrollpane with searchedPanel to SearchBooks JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to SearchBooks JPanel. <br>
backButton switches contentPane to menuPanel.

ViewUser.java :-
- Handles the GUI and logic for viewing a User. It is a JPanel and has other <br>
Jpanels inside it to show different parts of the page as mentioned below. <br>
- Creates a userPanel inside ViewUser panel. userPanel will have more Jpanels <br>
called blocks inside it. <br>
- Chceks the results of SELECT * FROM UserList where id=input and prints <br> 
corresponding user info in a block inside userPanel. <br>
- Book info retrieved by SELECT * FROM BorrowedBooks WHERE user_id=input. <br>
- Adds scrollpane with userPanel to ViewUser JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewUser JPanel. <br>
backButton switches contentPane to menuPanel.

ViewAllUsers.java :-
- Handles the GUI and logic for viewing a User. It is a JPanel and has other <br>
Jpanels inside it to show different parts of the page as mentioned below. <br>
- Creates a usersPanel inside ViewAllUsers panel. usersPanel will have more Jpanels <br>
called blocks inside it. <br>
- Iterates over results of SELECT * FROM UserList and prints user info in a block <br>
inside usersPanel. <br>
- Book info retrieved by SELECT * FROM BorrowedBooks WHERE user_id=input. <br>
- Adds scrollpane with usersPanel to ViewAllUsers JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewAllUsers JPanel. <br>
backButton switches contentPane to menuPanel. 

ViewLogs.java :-
- Handles the GUI and logic for viewing logs. It is a Jpanel and has other Jpanels <br>
inside it. <br>
- Inside ViewLogs JPanel, there is a logPanel Jpanel. <br>
- Iterates over the results of SELECT * FROM Logs and prints out every log info inside <br>
logsPanel, which is inside JPanel ViewLogs. <br>
- Adds scrollPane with logPanel to ViewLogs JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewLogs JPanel. <br>
backButton switches contentPane to menuPanel.