package com.dcp.musicsearcher.activity.search.list.item;

import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.activity.search.MainActivity;
import com.dcp.musicsearcher.data.FavoritesController;

public class ItemActivity extends AppCompatActivity implements TaskInterface {

    private ImageView mItemImage;
    private TextView mItemPerformer;
    private TextView mItemName;
    private TextView mItemLyrics;
    private ProgressBar mProgressBar;
    private long mItemId;
    private boolean mLoadVisible;
    private View mItemInfo;
    private ImageButton mToFavoritesButton;
    private Button mRefreshButton;
    private FavoritesController mFavoritesController;
    private boolean mRefreshVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mFavoritesController=new FavoritesController(this);

        mItemImage= (ImageView) findViewById(R.id.item_image);
        mItemPerformer = (TextView) findViewById(R.id.item_performer);
        mItemPerformer.setTypeface(MainActivity.typeface);
        mItemPerformer.setSelected(true);
        mItemLyrics = (TextView) findViewById(R.id.item_lyrics);
        mItemLyrics.setTypeface(MainActivity.typeface);
        mItemName= (TextView) findViewById(R.id.item_name);
        mItemName.setTypeface(MainActivity.typeface);
        mItemName.setSelected(true);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mItemInfo = findViewById(R.id.item_info);
        mItemInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName=mItemName.getText().toString();
                String itemPerformer=mItemPerformer.getText().toString();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q="+itemName+" "+itemPerformer+"&c=music")));
            }
        });
        mToFavoritesButton = (ImageButton) findViewById(R.id.to_favorites_button);

        mToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mFavoritesController.contains(mItemId)){
                    mFavoritesController.removeFromFavorites(mItemId);
                    mToFavoritesButton.setImageResource(R.mipmap.ic_star_outline_white_48dp);
                }else {
                    mFavoritesController.addToFavorites(mItemId, mItemName.getText().toString(), mItemPerformer.getText().toString());
                    mToFavoritesButton.setImageResource(R.mipmap.ic_star_white_48dp);
                }

            }
        });
        mRefreshButton= (Button) findViewById(R.id.refresh_button);
        mRefreshButton.setTypeface(MainActivity.typeface);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshButton.setVisibility(View.GONE);
                mRefreshVisible=false;
                getFragment().start();
            }
        });

        mLoadVisible=savedInstanceState==null?true:savedInstanceState.getBoolean("loadVisible");

        if(mLoadVisible){
            mProgressBar.setVisibility(View.VISIBLE);
        }else{
            mProgressBar.setVisibility(View.GONE);
        }
        mRefreshVisible=false;
        if(savedInstanceState!=null){
            mRefreshVisible=savedInstanceState.getBoolean("refreshVisible");
            mItemName.setText(savedInstanceState.getString("itemName"));
            mItemPerformer.setText(savedInstanceState.getString("itemPerformer"));
            mItemLyrics.setText(savedInstanceState.getString("itemLyrics"));
        }
        if(mRefreshVisible){
            mRefreshButton.setVisibility(View.VISIBLE);
        }else{
            mRefreshButton.setVisibility(View.GONE);
        }
        Intent intent = getIntent();
        mItemName.setText(intent.getStringExtra("KEY_NAME_SONG"));
        mItemPerformer.setText(intent.getStringExtra("KEY_NAME_ARTIST"));
        mItemId=intent.getLongExtra("KEY_ID",0);

        if(mFavoritesController.contains(mItemId)){
            mToFavoritesButton.setImageResource(R.mipmap.ic_star_white_48dp);
        }else{
            mToFavoritesButton.setImageResource(R.mipmap.ic_star_outline_white_48dp);
        }

        getFragment();
    }


    private ItemFragment getFragment(){
        ItemFragment fragment = (ItemFragment) getSupportFragmentManager().findFragmentByTag(ItemFragment.class.getName());
        if(fragment == null){
            fragment = ItemFragment.newInstance(mItemId);
            getSupportFragmentManager().beginTransaction().add(fragment, ItemFragment.class.getName()).commit();
        }
        return fragment;
    }

    @Override
    public void onTaskStart() {
        mProgressBar.setVisibility(View.VISIBLE);
        mLoadVisible=true;
    }

    @Override
    public void onFinish(String s) {
        mProgressBar.setVisibility(View.GONE);
        mItemLyrics.setText(s);
        mLoadVisible=false;
    }

    @Override
    public void onError() {
        mRefreshButton.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mRefreshVisible=true;
        mLoadVisible=false;
        Toast.makeText(getApplicationContext(),
                "No Internet connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("itemName", mItemName.getText().toString());
        outState.putString("itemPerformer", mItemPerformer.getText().toString());
        outState.putString("itemLyrics", mItemLyrics.getText().toString());
        outState.putBoolean("loadVisible",mLoadVisible);
        outState.putBoolean("refreshVisible",mRefreshVisible);
        super.onSaveInstanceState(outState);
    }
}
