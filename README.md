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
- Issue Book <br>

# Project Structure

Main.java :-
- This is the entry point for our program and has a main <br>
function - main() calls run() which runs the program. <br>
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
Else If choice==7 > calls issueBook() <br>

Library.java :-
- Has BookList HashMap to store all bookIds and Book objects. <br>
- An addBook() method takes inputs bookID, title and author, <br>
creates Book object and pushes it into BookList HashMap - bookID, book <br>
- A viewBooks() method that iterates over BookList and prints <br>
attributes of all books. <br>
- A removeBook() method that asks for a bookID and removes that key <br>
from the BookList HashMap. <br>
- A searchBook() method that takes in title as input, iterates over <br>
BookList and prints attributes of Book if found. <br>
- A registerUser() method that takes inputs uid and user name and <br>
creates User object, then pushes it into UserList HashMap - uid, user. <br>
- A viewUser() method that takes uid and shows username and books borrowed. <br>
- A issueBook() method which takes in uid and book id, and adds the book id <br>
with count+1 in BorrwedBooks HashMap of user object with matching uid - by <br>
calling user object.borrrowBook(bid) <br>

Book.java :- <br>
- Serves as the holder of all information regarding one book. <br>
- Has attributes bookID, title, author, totalCopies, availCopies. <br>
- Has parameterised constructor for bookID, title and author. <br>
- Has getters for everything, and setters for totalCopies and availCopies.

User.java :-
- Serves as the holder of all information regarding one user. <br>
- Has attributes uid, name, and a HashMap BooksBorrowed for <br>
bookID and copies borrowed. <br>
- Has getters for name and uid. Has borrowBook(), returnBook() <br>
viewBorrowed() and getBorrowedMap() regarding BooksBorrowed HashMap. <br>
- borrowBook() adds id, copies+1 to BooksBorrowed. <br>
- returnBook() adds id, copies-1 to BooksBorrowed and if copies is <br>
<=1 it removes the entry. <br>
- viewBorrowed() to iterate over BooksBorrowed and print it. <br>
- getBorrowedMap() to return a copy of the HashMap BooksBorrowed. 