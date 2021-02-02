package com.example.elibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

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

        adapter.setBooks(books);
    }
}