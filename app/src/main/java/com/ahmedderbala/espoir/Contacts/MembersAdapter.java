package com.ahmedderbala.espoir.Contacts;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;


public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Member> memberList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName,lastName,  phone,  email,  status,  espoirFunction,  function,  photo;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            firstName = (TextView) view.findViewById(R.id.title);
            email = (TextView) view.findViewById(R.id.shortDescription);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public MembersAdapter(Context mContext, List<Member> albumList) {
        this.mContext = mContext;
        this.memberList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.case_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.firstName.setText(member.getFirstName());
        // holder.shortDescription.setText(case.getNumOfSongs() + " songs");
        holder.email.setText(" short description");


        // loading album cover using Glide library
        Glide.with(mContext).load(member.getPhoto()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
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
        return memberList.size();
    }
}