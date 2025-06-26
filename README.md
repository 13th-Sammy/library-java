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

# Project Structure

Library.java :-
- This is our program entry point and has a main method. <br>
- Has BookList HashMap to store all bookIds and Book objects. <br>
- A printMenu() function prints the user menu. <br>
- An addBook() method takes inputs bookID, title and author, <br>
creates Book object and pushes it into BookList HashMap. <br>
- A viewBooks() method that iterates over BookList and prints <br>
attributes of all books. <br>
- A removeBook() method that asks for a bookID and removes that key <br>
from the BookList HashMap. <br>
- A searchBook() method that takes in title as input, iterates over <br>
BookList and prints attributes of Book if found. <br>
- The main function has an infinite while loop. Inside, it <br> 
calls printMenu() and takes the user choice in variable choice. <br>
If choice==0 > break out of loop and end program. <br>
Else If choice==1 > calls viewBooks() <br>
Else If choice==2 > calls addBook() <br>
Else If choice==3 > calls removeBook() <br>
Else If choice==4 > calls searchBook() <br>

Book.java :- <br>
- Serves as the holder of all information regarding one book. <br>
- Has attributes bookID, title, author, totalCopies, availCopies. <br>
- Has parameterised constructor for bookID, title and author. <br>
- Has getters for everything, and setters for totalCopies and availCopies.

User.java :-
- 