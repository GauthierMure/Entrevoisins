package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;

public class NeighbourInfoActivity extends AppCompatActivity {

    private TextView mNameTv, mAddressTv, mPhoneTv, mAbouMeTv, mMailTv;
    private ImageView mAvatarIv;
    private FloatingActionButton mReturnFBtn, mFavoriteFBtn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_info);
        intent = this.getIntent();
        init();
    }

    private void init() {
        mNameTv = findViewById(R.id.nameTv);
        mAddressTv = findViewById(R.id.locationTv);
        mPhoneTv = findViewById(R.id.phoneTv);
        mAbouMeTv = findViewById(R.id.aboutMeTv);
        mAvatarIv = findViewById(R.id.avatarIv);
        mReturnFBtn = findViewById(R.id.returnFBtn);
        mFavoriteFBtn = findViewById(R.id.favoriteFBtn);
        setContent();
        mReturnFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setContent() {
        mNameTv.setText(intent.getStringExtra("name"));
        mAddressTv.setText(intent.getStringExtra("address"));
        mPhoneTv.setText(intent.getStringExtra("phone"));
        mAbouMeTv.setText(intent.getStringExtra("description"));
        Glide.with(this).load(intent.getStringExtra("avatar")).placeholder(R.drawable.ic_account).into(mAvatarIv);
    }
}