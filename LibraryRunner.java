package by.htp.main;

import java.io.*;
import java.util.Scanner;

public class LibraryRunner {

	// Main method.
	public static void main(String[] args) {
		/*
		 * Method run at the start of the program. Determines the user to be a borrower
		 * or librarian and presents them with their options. Selected options call
		 * methods accordingly.
		 */
		Scanner scan = new Scanner(System.in);
		Library lib = new Library();
		lib.importStudents("Students.txt");
		lib.importTeachers("Teachers.txt");
		lib.importBooks("Books.txt");

		String reply;
		String user;
		String pass;

		String password = "admin1";

		System.out.println(
				"Welcome to the library! Are you a borrower (1) or a librarian (2)? Please enter the corresponding number.");

		reply = scan.nextLine();

		if (reply.equals("1")) {

			System.out.println("Please enter your id.");
			reply = scan.nextLine();

			if (lib.findStudent(reply) != null) {
				user = lib.findStudent(reply).getName();
				System.out.println("Hello, " + user + ". Do you want to:");
				System.out.println("A). Browse the library");
				System.out.println("B). Browse by genre");
				System.out.println("C). Check avalability");
				System.out.println("D). Borrow a book");
				System.out.println("E). Return a book"); // Gets options for borrower.
				reply = scan.nextLine();

				// Logic for options.
				if ((reply.toUpperCase()).equals("A")) {
					lib.displayBooks();
				} else if ((reply.toUpperCase()).equals("B")) {
					System.out.println("Enter a genre.");
					String genre = (scan.nextLine()).toUpperCase();
					lib.browse(genre); // Browse in genre.
				} else if ((reply.toUpperCase()).equals("C")) {
					System.out.println("Enter a book.");
					String book = scan.nextLine();
					lib.isAvailable(book); // Check availability.
				} else if ((reply.toUpperCase()).equals("D")) {
					System.out.println("Enter a book title.");
					String book = scan.nextLine();
					System.out.println("Enter date.");
					int date = scan.nextInt();
					lib.borrowBook(book, user, date); // Borrow book.
				} else if ((reply.toUpperCase()).equals("E")) {
					System.out.println("Enter a book title.");
					String book = scan.nextLine();
					System.out.println("Enter date.");
					int date = scan.nextInt();
					lib.returnBook(book, user, date); // Return book.
				}
			} else {
				System.out.println("Id missing");
			}
		} else if (reply.equals("2")) {

			System.out.println("Enter your name.");
			user = scan.nextLine();

			if (user.equals("admin")) {
				System.out.println("Hello, Librarian! Please enter your password.");
				pass = scan.nextLine();
				if (pass.equals(password)) {
					System.out.println("Login Successful! Please enter the letter of your desired option.");
					System.out.println(
							"A). Add a new book\nB). Add a new borrower\nC). Check borrowing history of a book\nD). Return a book\nE). Remove a book");// Gets
																																						// options
																																						// for
																																						// librarian.
					reply = scan.nextLine();

					// Logic for options.
					if ((reply.toUpperCase()).equals("A")) {
						// Params for Book constructor.
						System.out.println("Enter the ISBN.");
						String ISBN = scan.nextLine();
						System.out.println("Enter the title.");
						String title = scan.nextLine();
						System.out.println("Enter the author.");
						String author = scan.nextLine();
						System.out.println("Enter the genre.");
						String genre = scan.nextLine();
						System.out.println("Enter the status.");
						String stat = scan.nextLine();

						Book book = new Book(ISBN, title, author, genre, stat); // Creates new Book object.
						lib.addBook(book); // Adds a new book.

					} else if ((reply.toUpperCase()).equals("B")) {
						System.out.println("Enter the name.");
						String name = scan.nextLine();
						System.out.println("Enter ID");
						String id = scan.nextLine();
						System.out.println("Enter the grade");
						String grade = scan.nextLine();
						System.out.println("Enter offClass");
						String phNumber = scan.nextLine();
						StudentBorrower student = new StudentBorrower(name, id, grade, phNumber);
						lib.addStudent(student); // Adds a new Student

					} else if ((reply.toUpperCase()).equals("C")) {
						System.out.println("Enter a book title or ISBN.");
						String book = scan.nextLine();
						lib.getBookLog(book); // Retrives history of a book.

					} else if ((reply.toUpperCase()).equals("D")) {
						System.out.println("Enter the name of the person.");
						String name = scan.nextLine();
						System.out.println("Enter a book title.");
						String book = scan.nextLine();
						System.out.println("Enter date.");
						int date = scan.nextInt();
						lib.returnBook(book, name, date); // Return book.
					} else if ((reply.toUpperCase()).equals("E")) {
						System.out.println("Enter ISBN of a book.");
						String book = scan.nextLine();
						lib.removeBook(book); // Removes a book.
					}

				} else {
					System.out.println("Incorrect password for Librarian");
					System.out.println("...Failed to log in as borrower or admin\nShuttin down...");
				}
			}
		} else
			System.out.println("Invalid input");
	}
}
