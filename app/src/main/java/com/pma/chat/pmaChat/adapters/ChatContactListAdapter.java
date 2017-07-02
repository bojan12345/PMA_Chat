package com.pma.chat.pmaChat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pma.chat.pmaChat.model.User;
import com.pma.chat.pmaChat.R;


class ChatContactListAdapter extends RecyclerView.Adapter<ChatContactListAdapter.FriendListAdapterViewHolder> {

    /* The context we use to utility methods, app resources and layout inflaters */
    private final Context mContext;

    /*
     * Below, we've defined an interface to handle clicks on items within this Adapter. In the
     * constructor of our ForecastAdapter, we receive an instance of a class that has implemented
     * said interface. We store that instance in this variable to call the onClick method whenever
     * an item is clicked in the list.
     */
    private final FriendListAdapterOnClickHandler mClickHandler;

    public ChatContactListAdapter(Context mContext, FriendListAdapterOnClickHandler mClickHandler) {
        this.mContext = mContext;
        this.mClickHandler = mClickHandler;
    }

    @Override
    public FriendListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutId = R.layout.item_chat_contact;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutId, parent, shouldAttachToParentImmediately);
        view.setFocusable(true);

        return new FriendListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendListAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * The interface that receives onClick messages.
     */
    interface FriendListAdapterOnClickHandler {
        void onClick(long date);
    }

    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    class FriendListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView profilePhotoImageView;
        final TextView nameTextView;
        final TextView statusTextView;

        FriendListAdapterViewHolder(View itemView) {
            super(itemView);

            profilePhotoImageView = (ImageView) itemView.findViewById(R.id.iv_profile_photo);
            nameTextView = (TextView) itemView.findViewById(R.id.tv_name);
            statusTextView = (TextView) itemView.findViewById(R.id.tv_status);

            itemView.setOnClickListener(this);
        }

        void bind(User user) {
            // profilePhotoImageView.setImage
            nameTextView.setText(user.getFirstName() + " " + user.getLastName());
            statusTextView.setText("Offline");
        }

        @Override
        public void onClick(View v) {

        }
    }
}
