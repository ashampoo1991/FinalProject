package by.htp.main;

import java.io.*;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Library {

	private ArrayList<Book> database = new ArrayList<Book>(); // Holds all books.
	private ArrayList<TeacherBorrower> teachers = new ArrayList<TeacherBorrower>(); // Holds all teachers.
	private ArrayList<StudentBorrower> students = new ArrayList<StudentBorrower>(); // Holds all students.

	private static Scanner x;

	/*
	 * Method to import books from a .txt file. Param: Name of file.
	 */
	public void importBooks(String fileName) {
		String file = fileName; // The file to open.
		String line;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);

			// Wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Saves each line as a book object.
			while ((line = bufferedReader.readLine()) != null) {
				String[] str = line.split(", "); // Takes a single line and saves info as an array
				// with the format: [ISBN, title, author, etc.]

				Book book = new Book(str[0], str[1], str[2], str[3], str[4]); // Creates new book with elements in
																				// array.
				database.add(book); // Adds new book to library.
			}

			bufferedReader.close(); // Close reader.
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + file + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + file + "'");
		}
	}

	public void importTeachers(String fileName) {
		String file = fileName; // The file to open.
		String line;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);

			// Wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Saves each line as a book object.
			while ((line = bufferedReader.readLine()) != null) {
				String[] str = line.split(", ");
				TeacherBorrower teacher = new TeacherBorrower(str[1], str[0]);
				teachers.add(teacher);
			}

			bufferedReader.close(); // Close reader.
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + file + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + file + "'");
		}
	}

	public void importStudents(String fileName) {
		String file = fileName; // The file to open.
		String line;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(file);

			// Wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Saves each line as a book object.
			while ((line = bufferedReader.readLine()) != null) {
				String[] str = line.split(", ");

				StudentBorrower student = new StudentBorrower(str[0], str[1], str[2], str[3]);
				students.add(student);
			}

			bufferedReader.close(); // Close reader.
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + file + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + file + "'");
		}
	}

	// Prints out the contents of the Library.
	public void displayBooks() {
		for (int i = 0; i < database.size(); i++) {
			System.out.println(database.get(i));
		}
	}

	// Prints out the teachers.
	public void displayTeachers() {
		for (int i = 0; i < teachers.size(); i++) {
			System.out.println(teachers.get(i));
		}
	}

	// Prints out the students.
	public void displayStudents() {
		for (int i = 0; i < students.size(); i++) {
			System.out.println(students.get(i));
		}
	}

	// Modifier method to add a books to database.
	public void addBook(Book book) {
		database.add(book);
		writeTo("C:\\Users\\user\\eclipse-workspace\\FinalProject\\Books.txt", book.toStringList());
	}

	public void addStudent(StudentBorrower sb) {
		students.add(sb);
		writeTo("C:\\Users\\user\\eclipse-workspace\\FinalProject\\Students.txt", sb.toString());
	}

	/*
	 * Modifier method to remove books from database. Param: String title or ISBN of
	 * book.
	 */
	public void removeBook(String input) {
		Book toBeRemoved = findBook(input);
		String file = "Books.txt";
		// if(findBook(input) != null){
		if (database.contains(toBeRemoved)) {
			System.out.println("Book " + findBook(input));

			// System.out.println();
			// database.remove(findBook(input));
			// System.out.println(database.toString());

			// database.remove(toBeRemoved);

			System.out.println("Deleting book " + input);

			String title, author, status, ISBN, genre;
			try {
				File oldFile = new File(file);

				if (!oldFile.isFile()) {
					System.out.println("Parameter is not an existing file");
					return;
				}

				// Construct the new file that will later be renamed to the original filename.

				// String tempFile = "temp.txt";

				File newFile = new File("temp.txt");
				// Formatter newFile = new Formatter(tempFile);

				try {
					if (!newFile.exists()) {
						newFile.createNewFile();
						System.out.println("creating temp.txt");
						if (newFile.createNewFile()) {
							System.out.println("new file created");
						} else {
							System.out.println("failed to create temp.txt");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				FileWriter fw = new FileWriter(newFile, true);
				BufferedWriter bw = new BufferedWriter(fw);
				BufferedReader br = new BufferedReader(new FileReader(oldFile));
				PrintWriter pw = new PrintWriter(bw);
				PrintWriter pw2 = new PrintWriter(newFile);

				x = new Scanner(new File(file));
				x.useDelimiter("[,\n]");

				String line = null;

				// Read from the original file and write to the new
				// unless content matches data to be removed.
				while ((line = br.readLine()) != null) {
					if (!line.startsWith(input)) {
						pw.println(line);

						System.out.println(line);

					}
					if (line.isEmpty()) {
						line.trim();
					}
				}

				// while (x.hasNext()) {
				// ISBN = x.next();
				// title = x.next();
				// author = x.next();
				// genre = x.next();
				// status = x.next();
				// if (!ISBN.equals(input) || !title.equals(input)) {
				// pw.println(ISBN + ", " + title + ", " + author + ", " + genre + ", " +
				// status);
				// // System.out.println(ISBN + title + author + genre + status);
				// }
				// }
				x.close();
				pw.flush();
				pw.close();
				pw2.flush();
				pw2.close();
				br.close();
				fw.close();
				// oldFile.delete();

				File dump = new File(file);
				newFile.renameTo(dump);
				// inFile.delete();

				// Delete the original file
				if (!oldFile.delete()) {
					System.out.println("Could not delete file");
					return;
				}

				// Rename the new file to the filename the original file had.
				if (!newFile.renameTo(dump))
					System.out.println("Could not rename file");
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Book doesnt exist");
		}
	}

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((database == null) ? 0 : database.hashCode());
	// result = prime * result + ((students == null) ? 0 : students.hashCode());
	// result = prime * result + ((teachers == null) ? 0 : teachers.hashCode());
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// Library other = (Library) obj;
	// if (database == null) {
	// if (other.database != null)
	// return false;
	// } else if (!database.equals(other.database))
	// return false;
	// if (students == null) {
	// if (other.students != null)
	// return false;
	// } else if (!students.equals(other.students))
	// return false;
	// if (teachers == null) {
	// if (other.teachers != null)
	// return false;
	// } else if (!teachers.equals(other.teachers))
	// return false;
	// return true;
	// }

	/*
	 * Method to determine if book in library is available. Param: String with title
	 * or ISBN of book. Return: String with status of book.
	 */
	public void isAvailable(String input) {
		if (findBook(input) != null) {
			String status = (findBook(input)).getAvailability();
			System.out.println(status);
			if (status.equals("Yes"))
				System.out.println(input + " is available.");
			else if (status.equals("No"))
				System.out.println(input + " is not available.");
		}
	}

	public static void writeTo(String filePath, String content) {
		try {
			File file = new File(filePath);

			// If file doesnt exists, then create it.
			if (!file.exists()) {
				file.createNewFile();
			}

			PrintWriter fw = new PrintWriter(new FileWriter(file, true));
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.append(content);
			writer.close();

			System.out.println("Done.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to mark a book as borrowed if it is available. Params: Title or ISBN
	 * of a book and the name of the user.
	 */
	public void borrowBook(String book, String borrower, int date) {
		if (findBook(book) != null) {
			(findBook(book)).makeBorrowed();
			(findBook(book)).getAvailability().replace("Yes", "No");
			String fileName = (findBook(book)).getTitle() + "Log.txt";
			String content = "Borrowed by: " + borrower + ", " + date;

			writeTo(fileName, content);
		}
	}

	/*
	 * Method to mark a book as returned if possible. Params: Title or ISBN of a
	 * book and the name of the user.
	 */
	public void returnBook(String book, String borrower, int date) {
		if (findBook(book) != null) {
			(findBook(book)).makeReturned();
			String fileName = (findBook(book)).getTitle() + "Log.txt";
			String content = "Returned by: " + borrower + ", " + date;

			writeTo(fileName, content);
		}
	}

	/*
	 * Method to display all books of a genre in the library. Param: String with the
	 * genre of a book. Return: List of books in the same genre.
	 */
	public void browse(String genre) {
		for (int i = 0; i < database.size(); i++) {
			String bookGenre = (database.get(i)).getGenre();
			if (genre.equals(bookGenre)) {
				System.out.println(database.get(i));
			}
		}
	}

	/*
	 * Finds book object based on String. Basically converts from String to Book.
	 * Param: String containing title or ISBN Return: Book object with a title or
	 * ISBN matching the input.
	 */
	public Book findBook(String input) {
		// Book book = null;
		// for(int i = 0; i < database.size(); i++){
		// String str = (database.get(i)).getTitle(); //Gets title of book at index.
		// String ISBN = (database.get(i)).getISBN(); //Gets ISBN of book at index.
		// if(input.equals(str) || input.equals(ISBN))
		// book = database.get(i);
		// }
		// return book ;

		for (int i = 0; i < database.size(); i++) {
			String str = (database.get(i)).getTitle(); // Gets title of book at index.
			String ISBN = (database.get(i)).getISBN(); // Gets ISBN of book at index.
			if (input.equals(str) || input.equals(ISBN))
				return database.get(i);
		}
		return null;
	}

	public StudentBorrower findStudent(String input) {

		for (int i = 0; i < students.size(); i++) {
			String id = (students.get(i)).getId();
			if (input.equals(id)) {
				return students.get(i);
			}
		}
		return null;
	}

	/*
	 * Method to display the history of a book. Param: String with the title or ISBN
	 * of a book.
	 */
	public void getBookLog(String input) {
		String line;
		String file = (findBook(input)).getTitle() + "Log.txt";
		if (findBook(input) != null) { // If String of book exists, the book object is retrieved.
			try {
				// FileReader reads text files in the default encoding.
				FileReader fileReader = new FileReader(file);

				// Wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				// Prints line.
				while ((line = bufferedReader.readLine()) != null) {
					System.out.println(line);
				}

				bufferedReader.close(); // Close reader.
			} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + file + "'");
			} catch (IOException ex) {
				System.out.println("Error reading file '" + file + "'");
			}
		} else {
			System.out.println("No such book exists.");
		}
	}
}
