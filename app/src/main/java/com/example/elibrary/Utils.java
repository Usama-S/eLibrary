package com.example.elibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String
            SHARED_PREFERENCE_NAME = "books_db",
            ALL_BOOKS_KEY = "all_books",
            ALREADY_READ_BOOKS_KEY = "already_read_books",
            WISHLIST_KEY = "wishlist",
            CURRENTLY_READING_BOOKS_KEY = "currently_reading_books",
            FAVOURITES_KEY = "favourites";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wishlist;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favouriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

        if (null == getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getWishlist()) {
            editor.putString(WISHLIST_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavouriteBooks()) {
            editor.putString(FAVOURITES_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, 256, "Computer Science", "John Maxwell",
                "https://images-na.ssl-images-amazon.com/images/I/41MIIDToqhL.jpg",
                "A Computer Science book.", "Long Description"));
        books.add(new Book(2, 432, "Network Security Essentials", "William Stallings",
                "https://www.pearsonhighered.com/assets/bigcovers/1/2/9/2/1292154853.JPG",
                "An Introduction to network security.", "Long Description"));
        books.add(new Book(3, 843, "Discrete Mathematics and its Applications ", "Kenneth H Rosen",
                "https://images-na.ssl-images-amazon.com/images/I/716hbj45eOL.jpg",
                "A study of discrete objects.", "Long Description"));
        books.add(new Book(4, 328, "OOP and Java", "Danny Poo, Derek Kiong, Swarnalatha Ashok",
                "https://images-na.ssl-images-amazon.com/images/I/41fR4+pjbLL._SX331_BO1,204,203,200_.jpg",
                "An introduction to object oriented practices using Java.", "Long Description"));
        books.add(new Book(5, 320, "Learn Python 3 the Hard Way", "Zed A. Shaw",
                "https://images-na.ssl-images-amazon.com/images/I/51ko6ehpzEL.jpg",
                "A Very Simple Introduction to the Beautiful World of Code.", "Long Description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }

    public static synchronized Utils getInstance(Context context){
        if (null != instance){
            return instance;
        }
        else{
            return new Utils(context);
        }
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> alreadyReadBooks = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null), type);
        return alreadyReadBooks;
    }

    public ArrayList<Book> getWishlist() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> wishlist = gson.fromJson(sharedPreferences.getString(WISHLIST_KEY, null), type);
        return wishlist;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> currentlyReadingBooks = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null), type);
        return currentlyReadingBooks;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> favouriteBooks = gson.fromJson(sharedPreferences.getString(FAVOURITES_KEY, null), type);
        return favouriteBooks;
    }

    public Book getBookById(int bookId){
        ArrayList<Book> allBooks = getAllBooks();
        if (null != allBooks){
            for (Book b : allBooks) {
                if (b.getId() == bookId){
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyReadBooks(Book book){
        ArrayList<Book> alreadyReadBooks = getAlreadyReadBooks();
        if (null != alreadyReadBooks){
            if (alreadyReadBooks.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS_KEY);
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(alreadyReadBooks));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWishlist(Book book){
        ArrayList<Book> wishlist = getWishlist();
        if (null != wishlist){
            if (wishlist.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WISHLIST_KEY);
                editor.putString(WISHLIST_KEY, gson.toJson(wishlist));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book){
        ArrayList<Book> currentlyReadingBooks = getCurrentlyReadingBooks();
        if (null != currentlyReadingBooks){
            if (currentlyReadingBooks.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(currentlyReadingBooks));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavourites(Book book){
        ArrayList<Book> favourites = getFavouriteBooks();
        if (null != favourites){
            if (favourites.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITES_KEY);
                editor.putString(FAVOURITES_KEY, gson.toJson(favourites));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyReadBooks(Book book){
        ArrayList<Book> alreadyReadBooks = getAlreadyReadBooks();
        if (null != alreadyReadBooks){
            for (Book b: alreadyReadBooks) {
                if (b.getId() == book.getId()){
                    if (alreadyReadBooks.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS_KEY);
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(alreadyReadBooks));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWishlist(Book book){
        ArrayList<Book> wishlist = getWishlist();
        if (null != wishlist){
            for (Book b: wishlist) {
                if (b.getId() == book.getId()){
                    if (wishlist.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WISHLIST_KEY);
                        editor.putString(WISHLIST_KEY, gson.toJson(wishlist));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book){
        ArrayList<Book> currentlyReadingBooks = getCurrentlyReadingBooks();
        if (null != currentlyReadingBooks){
            for (Book b: currentlyReadingBooks) {
                if (b.getId() == book.getId()){
                    if (currentlyReadingBooks.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS_KEY);
                        editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(currentlyReadingBooks));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavourites(Book book){
        ArrayList<Book> favourites = getFavouriteBooks();
        if (null != favourites){
            for (Book b: favourites) {
                if (b.getId() == book.getId()){
                    if (favourites.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITES_KEY);
                        editor.putString(FAVOURITES_KEY, gson.toJson(favourites));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
