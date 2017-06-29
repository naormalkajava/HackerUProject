package com.example.ericbell.hackeruproject.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ericbell.hackeruproject.R;
import com.example.ericbell.hackeruproject.firebase.ChatItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    FirebaseUser mUser;
    FirebaseDatabase mDataBase;

    @BindView(R.id.rvMessage)
    RecyclerView rvMessage;
    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.etMessage)
    EditText etMessage;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        unbinder = ButterKnife.bind(this, view);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mDataBase = FirebaseDatabase.getInstance();


        rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMessage.setAdapter(new ChatAdapter(getContext()));


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnSend)
    public void onClickSend() {

            String uid = mUser.getUid();
            Uri photoUrl = mUser.getPhotoUrl();
            String img = null;
            String message = etMessage.getText().toString();
            ChatItem item = new ChatItem(message, uid, img, new Date().toString());
            mDataBase.getReference("ChatItems").push().setValue(item);
            etMessage.setText(null);

    }

    public static class ChatAdapter extends FirebaseRecyclerAdapter<ChatItem, ChatAdapter.ChatViewHOlder> {
        Context context;

        public ChatAdapter(Context context) {
            super(ChatItem.class, R.layout.chat_item, ChatViewHOlder.class, FirebaseDatabase.getInstance().getReference("ChatItems"));
            this.context = context;
        }

        @Override
        protected void populateViewHolder(ChatViewHOlder viewHolder, ChatItem model, int position) {

            viewHolder.tvMessage.setText(model.getMessage());
            Picasso.with(context).load(model.getProfileImage()).into(viewHolder.ivProfile);

        }

        public static class ChatViewHOlder extends RecyclerView.ViewHolder {

            ImageView ivProfile;
            TextView tvMessage;

            public ChatViewHOlder(View itemView) {
                super(itemView);
                ivProfile = (ImageView) itemView.findViewById(R.id.ivProfile);
                tvMessage = (TextView) itemView.findViewById(R.id.tvMessage);
            }
        }
    }
}
