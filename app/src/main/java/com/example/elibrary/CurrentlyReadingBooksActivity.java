package com.example.elibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_books);

        RecyclerView currentlyReadingBooksRecView = findViewById(R.id.currentlyReadingBooksRecView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this);

        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());

        currentlyReadingBooksRecView.setAdapter(adapter);

        currentlyReadingBooksRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CurrentlyReadingBooksActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}