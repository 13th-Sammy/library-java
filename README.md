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
function - main() creates a Menu object which launches the Menu <br>
that runs the program. {main() calls run() which runs the program.} <br>
- A printMenu() function prints the user menu. <br>
- A run() function acts as the program runner. It has <br> 
an infinite while loop. Inside, it calls printMenu() and <br>
takes the user choice in variable choice. <br>
If choice==0 > break out of loop and end program. <br>
Else If choice==1 > calls viewBooks() <br>
Else If choice==2 > calls addBook() <br>
Else If choice==3 > calls removeBook() <br>
Else If choice==4 > calls searchBook() <br>
Else If choice==5 > calls registerUser() <br>
Else If choice==6 > calls viewUser() <br>
Else If choice==7 > calls viewAllUsers() <br>
Else If choice==8 > calls issueBook() <br>
Else If choice==9 > calls returnBookL() <br>

Menu.java :-
- This creates the GUI menu with a button for each operation. <br>
- Creates a Jframe, setLayout, add buttons, add actionListener <br>
for each button to pair it to an operation. <br>
- If View Books clicked, calls viewBooks() <br>
- If Add Book clicked, calls addBook() <br>
- If Remove Book clicked, calls removeBook() <br>
- If Search Book clicked, calls searchBook() <br>
- If Register User clicked, calls registerUser() <br>
- If View User clicked, calls viewUser() <br>
- If View All Users clicked, calls viewAllUsers() <br>
- If Issue Book clicked, calls issueBook() <br>
- If Return Book clicked, calls returnBookL() <br>

Library.java :-
- Has BookList HashMap to store all book Ids and Book objects. <br>
- Has UserList HashMap to store all user IDs and User objects. <br>
- A viewBooks() method that iterates over BookList and prints <br>
attributes of all books. <br>
- An addBook() method takes inputs bookID, title and author, <br>
creates Book object and pushes it into BookList HashMap - bookID, book <br>
- A removeBook() method that asks for a bookID and removes that key <br>
from the BookList HashMap. <br>
- A searchBook() method that takes in title as input, iterates over <br>
BookList and prints attributes of Book if found. <br>
- A registerUser() method that takes inputs uid and user name and <br>
creates User object, then pushes it into UserList HashMap - uid, user. <br>
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