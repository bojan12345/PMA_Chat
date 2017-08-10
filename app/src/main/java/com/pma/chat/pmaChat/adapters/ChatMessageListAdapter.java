package com.pma.chat.pmaChat.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pma.chat.pmaChat.R;
import com.pma.chat.pmaChat.model.Message;

import java.util.List;

public class ChatMessageListAdapter extends ArrayAdapter<Message> {

    private ImageView mMessageContentImageView;
    private ImageView mMessageArrowPhotoView;

    private VideoView mMessageContentVideoView;
    private ImageView mMessageArrowVideoView;

    private TextView mMessageContentTextView;
    private ImageView mMessageArrowTextView;

    private static final int TYPES_COUNT = 2;
    private static final int TYPE_ME = 0;
    private static final int TYPE_FRIEND = 1;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public ChatMessageListAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
    }

    @Override
    public int getViewTypeCount() {
        return TYPES_COUNT;
    }

    @Override
    public int getItemViewType (int position) {
        if (getItem(position).getSenderId().equals(user.getUid())) {
            return TYPE_ME;
        }
        return TYPE_FRIEND;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            if(getItemViewType(position) == TYPE_ME) {
                convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_chat_message, parent, false);
            } else {
                convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_chat_message_friend, parent, false);
            }
        }

        mMessageContentImageView = (ImageView) convertView.findViewById(R.id.chat_message_photo_content);
        mMessageArrowPhotoView = (ImageView) convertView.findViewById(R.id.chat_message_arrow_photo_content);

        mMessageContentVideoView = (VideoView) convertView.findViewById(R.id.chat_message_video_content);
        mMessageArrowVideoView = (ImageView) convertView.findViewById(R.id.chat_message_arrow_video_content);

        mMessageContentTextView = (TextView) convertView.findViewById(R.id.chat_message_text_content);
        mMessageArrowTextView = (ImageView) convertView.findViewById(R.id.chat_message_arrow_text_content);
      //  TextView tvMessageAuthor = (TextView) convertView.findViewById(R.id.chatMessageAuthor);

        Message message = getItem(position);

        if(message.getType() == null) {
            mMessageContentTextView.setVisibility(View.VISIBLE);
            mMessageContentImageView.setVisibility(View.GONE);
            mMessageContentTextView.setText(message.getContent());
            return convertView;
        }

        switch(message.getType()) {

            case TEXT:
                hideAllContentViews();
                mMessageContentTextView.setVisibility(View.VISIBLE);
                mMessageArrowTextView.setVisibility(View.VISIBLE);
                mMessageContentTextView.setText(message.getContent());
                break;

            case PHOTO:
                hideAllContentViews();
                mMessageContentImageView.setVisibility(View.VISIBLE);
                mMessageArrowPhotoView.setVisibility(View.VISIBLE);
                Glide.with(mMessageContentImageView.getContext())
                        .load(message.getContent())
                        .into(mMessageContentImageView);
                break;

            case VIDEO:
                hideAllContentViews();
           //     mMessageContentVideoView.setVisibility(View.VISIBLE);
           //     mMessageContentVideoView.setVideoURI(Uri.parse(message.getContent()));
//                mMessageContentTextView.setVisibility(View.VISIBLE);
//                mMessageContentTextView.setText("VIDEO: " + message.getContent());

                mMessageArrowVideoView.setVisibility(View.VISIBLE);
                mMessageContentVideoView.setVisibility(View.VISIBLE);
                mMessageContentVideoView.setVideoURI(Uri.parse(message.getContent()));
                mMessageContentVideoView.start();
                break;

            case SOUND:
                hideAllContentViews();
                mMessageContentTextView.setVisibility(View.VISIBLE);
                mMessageContentTextView.setText("SOUND: " + message.getContent());
                break;

            case LOCATION:
                hideAllContentViews();

                break;

        }
        //   tvMessageAuthor.setText(message.getSenderId().ge);

        return convertView;
    }

    private void hideAllContentViews() {
        mMessageContentTextView.setVisibility(View.GONE);
        mMessageContentImageView.setVisibility(View.GONE);
        mMessageContentVideoView.setVisibility(View.GONE);
    }
}
