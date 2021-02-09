package com.example.elibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "BookId";

    private TextView txtName, txtAuthor, txtPages, txtDescription;
    private Button btnCurrentlyReading, btnAddToWishlist, btnAlreadyRead, btnAddToFavourites;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();

        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);

            if (bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook){
                    setBook(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWishlist(incomingBook);
                    handleCurrentlyReading(incomingBook);
                    handleFavourites(incomingBook);
                }
            }
        }
    }

    /**
     * Setting the incoming book in the layout.
     * @param book
     */
    private void setBook(Book book) {
        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imgBook);
    }

    /**
     * Handling Favourites Button
     * @param incomingBook
     */
    private void handleFavourites(Book incomingBook) {
        ArrayList<Book> favourites = Utils.getInstance(this).getFavouriteBooks();

        boolean existInFavourites = false;

        for (Book b: favourites) {
            if (b.getId() == incomingBook.getId()){
                existInFavourites = true;
            }
        }

        if (existInFavourites){
            btnAddToFavourites.setEnabled(false);
        }
        else{
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavourites(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added to Favourites", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(BookActivity.this, FavouritesActivity.class));
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Handling Currently Reading Button
     * @param incomingBook
     */
    private void handleCurrentlyReading(Book incomingBook) {

        ArrayList<Book> currentlyReading = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReading = false;

        for (Book b: currentlyReading) {
            if (b.getId() == incomingBook.getId()){
                existInCurrentlyReading = true;
            }
        }

        if (existInCurrentlyReading){
            btnCurrentlyReading.setEnabled(false);
        }
        else{
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added to Currently Reading", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class));
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Handling wishlist button
     * @param incomingBook
     */
    private void handleWishlist(Book incomingBook) {
        ArrayList<Book> wishlist = Utils.getInstance(this).getWishlist();

        boolean existInWishlist = false;

        for (Book b: wishlist) {
            if (b.getId() == incomingBook.getId()){
                existInWishlist = true;
            }
        }

        if (existInWishlist){
            btnAddToWishlist.setEnabled(false);
        }
        else{
            btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWishlist(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added to Wishlist", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(BookActivity.this, WishlistActivity.class));
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Handling Already read books button.
     * @param incomingBook
     */
    private void handleAlreadyRead(final Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks) {
            if (b.getId() == incomingBook.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            btnAlreadyRead.setEnabled(false);
        }
        else{
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyReadBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book Added to Already Read", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(BookActivity.this, AlreadyReadBookActivity.class));
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Initializing Views
     */
    private void initViews() {

        txtName = findViewById(R.id.txtName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);

        imgBook = findViewById(R.id.imgBook);
    }
}