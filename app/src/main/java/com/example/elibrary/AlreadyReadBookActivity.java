package com.example.elibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        RecyclerView alreadyReadRecView = findViewById(R.id.alreadyReadBooksRecView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this);

        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());

        alreadyReadRecView.setAdapter(adapter);

        alreadyReadRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AlreadyReadBookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}