package com.example.elibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "BookId";

    private TextView txtName, txtAuthor, txtPages, txtDescription;
    private Button btnCurrentlyReading, btnAddToWishlist, btnAlreadyRead, btnAddToFavourites;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

//        Initializing Views
        initViews();

        //TODO: get Books form all Books Activity

//        Book book = new Book(1005, 352, "Network Security Essentials", "William Stallings",
//                "https://www.pearsonhighered.com/assets/bigcovers/1/2/9/2/1292154853.JPG",
//                "A comprehesive book on Network Security",
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin non enim at nibh finibus hendrerit. Nunc faucibus, metus non vehicula sodales, velit libero viverra sem, ut mattis purus justo vitae ligula. Nulla dapibus dui mauris, dapibus lobortis diam egestas at. Phasellus vitae massa quis velit rhoncus rhoncus. Vestibulum quis erat imperdiet, ultrices urna sit amet, tincidunt tortor. Praesent vel feugiat leo. Nam sollicitudin egestas nibh vel lacinia. In faucibus metus at ante molestie rutrum. Nam fringilla in metus id mollis. Aliquam ut nulla at risus rhoncus suscipit. In ornare, quam sit amet consequat consectetur, mi nulla tempus nisi, malesuada mollis mi justo in nibh. Cras et tincidunt massa.\n" +
//                        "Ut suscipit euismod ex, sit amet semper neque faucibus ut. Quisque mollis orci eu dui dapibus sollicitudin. Pellentesque accumsan posuere felis, at sagittis magna. Pellentesque sit amet sem pellentesque, porta augue nec, malesuada libero. Etiam tempus, neque in aliquet aliquet, lacus nunc convallis augue, quis porttitor velit nulla vitae turpis. Etiam finibus massa nec felis congue sodales. Vestibulum arcu leo, accumsan quis tempus eu, posuere vitae turpis. Pellentesque quam lorem, venenatis id pellentesque sit amet, rhoncus at purus. In convallis vehicula arcu, sit amet vehicula est malesuada in. Maecenas pulvinar gravida ultrices. Donec eu sapien sagittis ipsum hendrerit ullamcorper vitae ac arcu. Suspendisse vestibulum lectus eros, at pulvinar sem blandit in. In condimentum ex vel neque pretium, et sollicitudin ligula tempor. Maecenas vitae urna eu massa ultrices sagittis vitae eleifend metus.\n" +
//                        "Fusce et mattis tellus. Quisque pretium mauris et dolor lobortis, et convallis nibh cursus. Curabitur a lacinia nibh. Sed ut augue vel sem aliquam sagittis. Curabitur metus orci, luctus sed justo vitae, commodo fringilla mi. Suspendisse potenti. Proin faucibus, dui nec interdum condimentum, leo neque placerat tortor, et sagittis arcu risus ornare orci.");

        Intent intent = getIntent();

        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);

            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook){
                    setBook(incomingBook);
                }
            }
        }
    }

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

    //    Initializing Views Method
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