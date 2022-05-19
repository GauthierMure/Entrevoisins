package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourInfoActivity extends AppCompatActivity {

    private TextView mNameTv, mAddressTv, mPhoneTv, mAbouMeTv, mMailTv;
    private ImageView mAvatarIv;
    private FloatingActionButton mFavoriteFBtn;
    private ImageButton mReturnIBtn;
    private Intent intent;
    private NeighbourApiService mApiService;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_info);
        intent = this.getIntent();
        mApiService = DI.getNeighbourApiService();
        init();
    }

    private void init() {
        mNameTv = findViewById(R.id.nameTv);
        mAddressTv = findViewById(R.id.locationTv);
        mPhoneTv = findViewById(R.id.phoneTv);
        mAbouMeTv = findViewById(R.id.aboutMeTv);
        mAvatarIv = findViewById(R.id.avatarIv);
        mReturnIBtn = findViewById(R.id.returnIBtn);
        mFavoriteFBtn = findViewById(R.id.favoriteFBtn);
        setContent();
        mReturnIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mFavoriteFBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFavorite();
            }
        });
    }

    private void setFavorite() {
        Neighbour neighbour = mApiService.getNeighbour(position);
        if (neighbour.getFavorite()){
            mApiService.removeFavoriteNeighbour(position);
            mFavoriteFBtn.setImageResource(R.drawable.ic_goldenstar);
        }else{
            mApiService.addFavoriteNeighbour(position);
            mFavoriteFBtn.setImageResource(R.drawable.ic_goldenfullstar);
        }
    }

    private void setContent() {
        position = intent.getIntExtra("position",-1);
        mNameTv.setText(mApiService.getNeighbour(position).getName());
        mAddressTv.setText(mApiService.getNeighbour(position).getAddress());
        mPhoneTv.setText(mApiService.getNeighbour(position).getPhoneNumber());
        mAbouMeTv.setText(mApiService.getNeighbour(position).getAboutMe());
        Glide.with(this).load(mApiService.getNeighbour(position).getAvatarUrl()).placeholder(R.drawable.ic_account).into(mAvatarIv);
        if (mApiService.getNeighbour(position).getFavorite()){
            mFavoriteFBtn.setImageResource(R.drawable.ic_goldenfullstar);
        }
    }


}