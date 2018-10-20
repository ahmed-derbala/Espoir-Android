package com.ahmedderbala.espoir.Cases;


import android.content.Context;
        import android.support.v7.widget.PopupMenu;
        import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

import com.ahmedderbala.espoir.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


import java.util.List;

import static com.ahmedderbala.espoir.app.AppConfig.URL_CASE_THUMBNAIL;


public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Case> caseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, shortDescription,author,city;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            shortDescription = view.findViewById(R.id.shortDescription);
            author = view.findViewById(R.id.author);
            city = view.findViewById(R.id.city);
            thumbnail = view.findViewById(R.id.thumbnail);
            overflow = view.findViewById(R.id.overflow);
        }
    }


    public CasesAdapter(Context mContext, List<Case> albumList) {
        this.mContext = mContext;
        this.caseList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.case_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Case c = caseList.get(position);
        holder.title.setText(c.getTitle());
        holder.shortDescription.setText(c.getShortDescription());
        holder.author.setText(c.getAuthor());
        holder.city.setText(c.getCity());
        //Picasso.with(mContext).load(URL_CASE_THUMBNAIL+c.getThumbnail()).into(holder.thumbnail);
        Picasso.with(mContext).load(c.getThumbnail()).into(holder.thumbnail);
        Log.e("PHOTO", c.getThumbnail() );





       /* // loading album cover using Glide library
        Glide.with(mContext).load(c.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_case_cv, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return caseList.size();
    }
}