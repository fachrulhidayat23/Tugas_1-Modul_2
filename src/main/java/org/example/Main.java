package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Book[] bookList;
    private final List<Student> userStudent;
    private final Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        bookList = new Book[]{
                new Book("55-33-44", "One Piece", "komik/Manga", "Author1", 1),
                new Book("05-03-04", "Gerhana Biru", "Novel", "Author2", 2),
                new Book("53-20-04", "Laskar pelangi", "Novel", "Author3", 8),
        };

        userStudent = new ArrayList<>();
        userStudent.add(new Student("Achmad Fachrul Hidayat", "202310370311338"));
    }

    public void menu() {
        int choice;
        do {
            System.out.println("Library System");
            System.out.println("1. Login Sebagai Admin");
            System.out.println("2. Login Sebagai Student");
            System.out.println("3. Keluar");
            System.out.println("Pilih Pilihan: ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    inputNim();
                    break;
                case 3:
                    System.out.println("Keluar...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi.");
            }
        } while (choice != 3);
    }

    public int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Masukan Tidak Valid, Silahkan Masukkan Bilangan Bulat.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void inputNim() {
        System.out.println("Masukkan Nim Anda: ");
        scanner.nextLine();
        String nim = scanner.nextLine();
        checkNim(nim);
    }

    public void checkNim(String nim) {
        boolean found = false;
        for (Student student : userStudent) {
            if (student.getNim().equals(nim)) {
                found = true;
                student.menuStudent();
                break;
            }
        }
        if (!found) {
            System.out.println("Student Dengan NIM " + nim + " Tidak Ditemukan.");
        }
    }

    public void menuAdmin() {
        int choice;
        do {
            System.out.println("Admin Menu");
            System.out.println("1. Tambahkan Student");
            System.out.println("2. Tampilkan Student Terdaftar");
            System.out.println("3. Logout");
            System.out.println("Pilih Pilihan: ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudent();
                    break;
                case 3:
                    System.out.println("Keluar Dari Akun Admin...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi.");
            }
        } while (choice != 3);
    }

    public void addStudent() {
        System.out.print("Masukkan Nama: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan NIM: ");
        String nim;
        do {
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("Format NIM Tidak Valid, NIM Harus Terdiri Dari 15 Karakter.");
                System.out.print("Masukkan NIM Lagi: ");
            }
        } while (nim.length() != 15);

        System.out.print("Masukkan Fakultas: ");
        scanner.nextLine();
        System.out.print("Masukkan Program Studi: ");
        scanner.nextLine();

        userStudent.add(new Student(name, nim));
    }

    public void displayStudent() {
        System.out.println("Students Terdaftar:");
        for (Student student : userStudent) {
            System.out.println(student.getName() + " (" + student.getNim() + ")");
        }
    }

    public static void main(String[] args) {
        Main library = new Main();
        library.menu();
    }
}

class Book {
    private final String id;
    private final String title;
    private String author;
    private String category;
    private int stock;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Student {
    private String name;
    private final String nim;
    private final List<Book> borrowedBooks;
    private final Scanner scanner;

    public Student(String name, String nim) {
        this.name = name;
        this.nim = nim;
        this.borrowedBooks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void menuStudent() {
        int choice;
        do {
            System.out.println("Student Menu");
            System.out.println("1. Tampilan Buku");
            System.out.println("2. Buku Yang Dipinjam");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Logout");
            System.out.println("Pilih Pilihan: ");
            choice = readIntegerInput();

            switch (choice) {
                case 1:
                    displayBooks(Main.bookList);
                    break;
                case 2:
                    displayBorrowedBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    System.out.println("Keluar dari akun Student...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid, Silahkan Coba Lagi.");
            }
        } while (choice != 4);
    }

    public int readIntegerInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Masukan Tidak Valid, Silahkan Masukkan Bilangan Bulat.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void displayBooks(Book[] books) {
        System.out.println("Buku Yang Tersedia:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " (" + book.getAuthor() + ") - " + book.getStock() + " copies available");
        }
    }

    private void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Kamu Belum Meminjam Buku Apapun.");
        } else {
            System.out.println("Buku Yang Dipinjam:");
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle() + " (" + book.getAuthor() + ")");
            }
        }
    }

    private void borrowBook() {
        System.out.println("Masukkan ID Buku Yang Ingin Anda Pinjam: ");
        scanner.nextLine(); // consume the newline character
        String bookId = scanner.nextLine();
        Book bookToBorrow = null;
        for (Book book : Main.bookList) {
            if (book.getId().equals(bookId)) {
                bookToBorrow = book;
                break;
            }
        }
        if (bookToBorrow != null) {
            if (bookToBorrow.getStock() > 0) {
                borrowedBooks.add(bookToBorrow);
                bookToBorrow.setStock(bookToBorrow.getStock() - 1);
                System.out.println("BUku Berhasil Dipinjam.");
            } else {
                System.out.println("Maap, Stok Buku Sedang Habis.");
            }
        } else {
            System.out.println("Buku Tidak Ditemukan.");
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

}