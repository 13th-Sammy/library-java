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
- View User <br>
- View All Users <br>
- Issue Book <br>
- Return Book <br>

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
. <br>
- If View User clicked, calls viewUser() <br>
- If View All Users clicked, calls viewAllUsers() <br>
- If Issue Book clicked, calls issueBook() <br>
- If Return Book clicked, calls returnBookL() <br>

Library.java :-
- Has BookList HashMap to store all book Ids and Book objects. <br>
- Has UserList HashMap to store all user IDs and User objects. <br>
- A getBookList() method that returns the values of HashMap id, book <br>
as a Collection of Books.
- An addBook() method takes inputs bookID, title and author, <br>
creates Book object and pushes it into BookList HashMap - bookID, book <br>
- A removeBook() method that asks for a bookID and removes that key <br>
from the BookList HashMap. <br>
- A registerUser() method that takes inputs uid and user name and <br>
creates User object, then pushes it into UserList HashMap - uid, user. <br>
. <br>
- A viewUser() method that takes uid and shows username and books borrowed. <br>
- A viewAllUsers() method that iterates over UserList and prints all users <br>
and borrowed Books by calling user object.viewBorrowed(). <br>
- A issueBook() method which takes in uid and book id, and adds the book id <br>
with count+noOfCopies in BorrowedBooks HashMap of user object with matching uid - by <br>
calling user object.borrrowBook(bid). Updates available copies of book object <br>
with matching bid. <br>
- A returnBookL() method which takes in uid and book id, and adds the book id <br>
with count-noOfCopies in BorrowedBooks HashMap of user object with matching uid - by <br>
calling user object.returnBook(bid). Updates available copies of book object <br>
with matching bid. <br>

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
viewBorrowed() and getBorrowedMap() regarding BooksBorrowed HashMap. <br>
- borrowBook() adds id, copies+1 to BooksBorrowed. <br>
- returnBook() adds id, copies-1 to BooksBorrowed and if copies is <br>
<=1 it removes the entry. <br>
- viewBorrowed() to iterate over BooksBorrowed and print it. <br>
- getBorrowedMap() to return a copy of the HashMap BooksBorrowed. 

ViewBooks.java :-
- Handles the GUI and logic for viewing books. It is a JPanel. Inside it there are <br>
more JPanels for showing different parts of the page as mentioned below. <br>
- Creates a booksPanel inside ViewBooks JPanel. booksPanel will have <br>
more Jpanels called blocks inside it. <br>
- Iterates over Book collection and prints each book info in a block <br>
inside booksPanel. <br>
- Adds scrollpane with booksPanel to ViewBooks JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to ViewBooks JPanel. <br>
backButton switches contentPane to menuPanel. 

SearchBooks.java :-
- Handles the GUI and logic for searching books. It is a Jpanel. Inside it there are <br>
more JPanels for showing different parts of the page as mentioned below. <br>
- Creates a searchedPanel inside SearchBooks JPanel. searchedPanel will have more <br>
JPanels called blocks inside it. <br>
- Iterates over Book collection and prints searched title book info in a <br>
block inside searchedPanel.
- Adds scrollpane with searchedPanel to SearchBooks JPanel. <br>
- Adds backButton to bottomPanel JPanel and adds that to SearchBooks JPanel. <br>
backButton switches contentPane to menuPanel.

Add Later :-
- Logging
- Remove User