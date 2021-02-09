package com.example.elibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.elibrary.BookActivity.BOOK_ID_KEY;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    private static final String TAG = "BookRecViewAdapter";

    ArrayList<Book> books = new ArrayList<>();
    private Context context;

    public BookRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtName.setText(books.get(position).getName());

//        setting image
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

        /**
         * setting onclick listener on individual cardview
         */
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(books.get(position).getAuthor());
        holder.txtDescription.setText(books.get(position).getShortDesc());

        /**
         * Checking if the cardview is expanded or not.
         * Checking if the calling activity is AllBooksActivity or not.
         * If so, only then the delete button will be hidden otherwise it will be visible.
         *
         * Show an alert dialog when the user clicks on the delete button and remove the book from the respective list upon confirmation.
         */
        if(books.get(position).getExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (context.getClass().getSimpleName().equals("AllBooksActivity")){
                holder.btnDelete.setVisibility(View.GONE);
            }
            else if (context.getClass().getSimpleName().equals("AlreadyReadBookActivity")){

//                For Already Read Books Activity

                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to remove " + books.get(position).getName() + " from Already Read Books?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getInstance(context).removeFromAlreadyReadBooks(books.get(position))){
                                            Toast.makeText(context, "Book removed Successfully!", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }
                                        else {
                                            Toast.makeText(context, "Something went wrong! Please try Later.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Nothing to do.
                                    }
                                })
                                .create().show();
                    }
                });
            }
            else if (context.getClass().getSimpleName().equals("CurrentlyReadingBooksActivity")){

//                For Currently Reading Books Activity

                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to remove " + books.get(position).getName() + " from Currently Reading Books?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getInstance(context).removeFromCurrentlyReading(books.get(position))){
                                            Toast.makeText(context, "Book removed Successfully!", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }
                                        else {
                                            Toast.makeText(context, "Something went wrong! Please try Later.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Nothing to do.
                                    }
                                })
                                .create().show();
                    }
                });
            }
            else if (context.getClass().getSimpleName().equals("WishlistActivity")){

//                For Wishlist Activity

                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to remove " + books.get(position).getName() + " from Wishlist?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getInstance(context).removeFromWishlist(books.get(position))){
                                            Toast.makeText(context, "Book removed Successfully!", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }
                                        else {
                                            Toast.makeText(context, "Something went wrong! Please try Later.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Nothing to do.
                                    }
                                })
                                .create().show();
                    }
                });
            }
            else if (context.getClass().getSimpleName().equals("FavouritesActivity")){

//                For Favourites Activity

                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to remove " + books.get(position).getName() + " from Favourites?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Utils.getInstance(context).removeFromFavourites(books.get(position))){
                                            Toast.makeText(context, "Book removed Successfully!", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }
                                        else {
                                            Toast.makeText(context, "Something went wrong! Please try Later.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Nothing to do.
                                    }
                                })
                                .create().show();
                    }
                });
            }

        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

//  =================== Inner Class =========================

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;

//        expanded layout views
        private ImageView upArrow, downArrow;
        private TextView txtAuthor, txtDescription, btnDelete;
        private RelativeLayout expandedLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.listItemParent);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtName);

//            expanded layout views
            downArrow = itemView.findViewById(R.id.expandArrowIcon);
            upArrow = itemView.findViewById(R.id.collapseArrowIcon);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            btnDelete = itemView.findViewById(R.id.txtDelete);
            expandedLayout = itemView.findViewById(R.id.expandedLayout);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
