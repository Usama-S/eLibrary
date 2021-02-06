package com.example.elibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wishlist;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favouriteBooks;

    private Utils() {
        if (null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }

        if (null == wishlist) {
            wishlist = new ArrayList<>();
        }

        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == favouriteBooks) {
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //todo: add initial data

        allBooks.add(new Book(1, 256, "Computer Science", "John Maxwell",
                "https://images-na.ssl-images-amazon.com/images/I/41MIIDToqhL.jpg",
                "A Computer Science book.", "Long Description"));
        allBooks.add(new Book(2, 432, "Network Security Essentials", "William Stallings",
                "https://www.pearsonhighered.com/assets/bigcovers/1/2/9/2/1292154853.JPG",
                "An Introduction to network security.", "Long Description"));
        allBooks.add(new Book(3, 843, "Discrete Mathematics and its Applications ", "Kenneth H Rosen",
                "https://images-na.ssl-images-amazon.com/images/I/716hbj45eOL.jpg",
                "A study of discrete objects.", "Long Description"));
        allBooks.add(new Book(4, 328, "OOP and Java", "Danny Poo, Derek Kiong, Swarnalatha Ashok",
                "https://images-na.ssl-images-amazon.com/images/I/41fR4+pjbLL._SX331_BO1,204,203,200_.jpg",
                "An introduction to object oriented practices using Java.", "Long Description"));
        allBooks.add(new Book(5, 320, "Learn Python 3 the Hard Way", "Zed A. Shaw",
                "https://images-na.ssl-images-amazon.com/images/I/51ko6ehpzEL.jpg",
                "A Very Simple Introduction to the Beautiful World of Code.", "Long Description"));

    }

    public static Utils getInstance(){
        if (null != instance){
            return instance;
        }
        else{
            return new Utils();
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWishlist() {
        return wishlist;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public static Book getBookById(int bookId){
        for (Book b : allBooks) {
            if (b.getId() == bookId){
                return b;
            }
        }
        return null;
    }
}
