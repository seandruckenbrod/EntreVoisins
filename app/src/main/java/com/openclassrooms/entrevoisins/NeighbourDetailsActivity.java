package com.openclassrooms.entrevoisins;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailsActivity extends AppCompatActivity {


    @BindView(R.id.back_btn)
    public ImageButton mButtonToolbar;

    @BindView(R.id.neighbour_picture_img)
    public ImageView mNeighbourAvatar;

    @BindView(R.id.Neighbour_name_txt)
    public TextView mTextViewToolbar;

    @BindView(R.id.add_favorite_button_btn)
    public android.support.design.widget.FloatingActionButton mFavStar;

    @BindDrawable(R.drawable.ic_star_border_white_24dp)
    public Drawable mStarBorder;

    @BindDrawable(R.drawable.ic_star_yellow_24dp)
    public Drawable mStarYellow;

    @BindView(R.id.infos_card_name_txt)
    public TextView mInfosCardName;


    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_neighbour_details);
        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();
        getNeighbour();
        loadInfoNeighbour();
        favoriteStar();
    }

    private void getNeighbour() {
        mNeighbour = getIntent().getParcelableExtra("neighbour");
    }


    private void loadInfoNeighbour() {
        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mNeighbourAvatar);
        mTextViewToolbar.setText(mNeighbour.getName());
        mInfosCardName.setText(mNeighbour.getName());
        mButtonToolbar.setOnClickListener(v -> finish());
    }


    private void favoriteStar() {
        if (mNeighbour.isFavorite()) {
            mFavStar.setImageDrawable(mStarYellow);
        } else {
            mFavStar.setImageDrawable(mStarBorder);
        }

        mFavStar.setOnClickListener(v -> {
            mApiService.toggleFavorite(mNeighbour);
            mNeighbour.setFavorite(!mNeighbour.isFavorite());
            favoriteStar();
        });
    }
}