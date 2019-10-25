package com.Pedro.DataStructuresPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Library {
    private AuthorList[] catalog = new AuthorList[('Z' + 1)];
    private PatronList[] people = new PatronList[('Z' + 1)];
    private String input;
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    public Library() {
        for (int i = 0; i <= 'Z'; i++) {
            catalog[i] = new AuthorList();
            people[i] = new PatronList();
        }
    }

    private String getString(String msg) {
        System.out.println(msg + " ");
        System.out.flush();
        try {
            input = buffer.readLine();
        } catch (IOException io) {
        }

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    private void status() {
        System.out.println("Library has the following books:\n ");
        for (int i = 'A'; i <= 'Z'; i++)
            if (!catalog[i].isEmpty())
                catalog[i].display();
        System.out.println("\nThe following people are using the library:\n ");
        for (int i = 'A'; i <= 'Z'; i++)
            if (!people[i].isEmpty())
                people[i].display();
    }

    private void includeBook() {
        Author newAuthor = new Author();
        int oldAuthor;
        Book newBook = new Book();
        newAuthor.name = getString("Enter author’s name:"); newBook.title = getString("Enter the title of the book:"); oldAuthor = catalog[(int)
                newAuthor.name.charAt(0)].indexOf(newAuthor); if (oldAuthor == -1) {
            newAuthor.books.add(newBook);
            catalog[(int) newAuthor.name.charAt(0)].add(newAuthor);
        }
        else ((Author)catalog[(int)
                    newAuthor.name.charAt(0)].get(oldAuthor)).
                    books.add(newBook);
    }

    private void checkOutBook() {

        Patron patron = new Patron(), patronRef; // = new Patron();
        Author author = new Author(), authorRef = new Author(); Book book = new Book();
        int patronIndex, bookIndex = -1, authorIndex = -1; patron.name = getString("Enter patron’s name:");

        while (authorIndex == -1) {
            author.name = getString("Enter author’s name:"); authorIndex = catalog[(int)
                    author.name.charAt(0)].indexOf(author); if (authorIndex == -1)
                System.out.println("Misspelled author’s name");
        }
        while (bookIndex == -1) {
            book.title = getString("Enter the title of the book:"); authorRef = (Author) catalog[(int)
                    author.name.charAt(0)].get(authorIndex); bookIndex = authorRef.books.indexOf(book);
            if (bookIndex == -1) System.out.println("Misspelled title");
        }
        Book bookRef = (Book) authorRef.books.get(bookIndex);
        CheckedOutBook bookToCheckOut = new CheckedOutBook();
        bookToCheckOut.author = authorRef;
        bookToCheckOut.book = bookRef;

        patronIndex = people[(int) patron.name.charAt(0)].indexOf(patron);
        if (patronIndex == -1) { // a new patron in the library; patron.books.add(bookToCheckOut);
            patron.books.add(bookToCheckOut);
            people[(int) patron.name.charAt(0)].add(patron); bookRef.patron = (Patron) people[(int)
                    patron.name.charAt(0)].getFirst();
        } else {
            patronRef = (Patron) people[(int) patron.name.charAt(0)].get(patronIndex);
            patronRef.books.add(bookToCheckOut);
            bookRef.patron = patronRef;

        }


    }

    private void returnBook() {
        Patron patron = new Patron();
        Book book = new Book();
        Author author = new Author(), authorRef = new Author();
        int patronIndex = -1, bookIndex = -1, authorIndex = -1;

        while (patronIndex == -1) {
            patron.name = getString("Enter patron’s name:"); patronIndex = people[(int)
                    patron.name.charAt(0)].indexOf(patron); if (patronIndex == -1)
                System.out.println("Patron’s name misspelled");
        }
        while (authorIndex == -1) {
            author.name = getString("Enter author’s name:"); authorIndex = catalog[(int)
                    author.name.charAt(0)].indexOf(author); if (authorIndex == -1)
                System.out.println("Misspelled author’s name");
        }
        while (bookIndex == -1) {
            book.title = getString("Enter the title of the book:"); authorRef = (Author) catalog[(int)
                    author.name.charAt(0)].get(authorIndex); bookIndex = authorRef.books.indexOf(book);
            if (bookIndex == -1) System.out.println("Misspelled title");
        }

        CheckedOutBook checkedOutBook = new CheckedOutBook(); checkedOutBook.author = authorRef;
        checkedOutBook.book = (Book) authorRef.books.get(bookIndex); ((Book)authorRef.books.get(bookIndex)).patron = null; ((Patron)people[(int) patron.name.charAt(0)].get(patronIndex)).
                books.remove(checkedOutBook);
    }




    public void run() {
        while (true) {
            char option = getString("\nEnter one of the following" +
                    " options:\n" +
                    "1. Include a book in the catalog\n" + "2. Check out a book\n" +
                    "3. Return a book\n4. Status\n5." + "Exit\n" +
                    "Your option:").charAt(0);
            switch (option) {
                case '1': includeBook(); break;
                case '2': checkOutBook(); break;
                case '3': returnBook(); break;
                case '4': status(); break;
                case '5': return;
                default: System.out.println("Wrong option, try again.");
            } }
    }

    public static void main(String args[]) {
        (new Library()).run();
    }

}
