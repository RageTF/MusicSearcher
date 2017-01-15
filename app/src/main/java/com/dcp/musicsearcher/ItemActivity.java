package com.dcp.musicsearcher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mItemImage= (ImageView) findViewById(R.id.item_image);
        mItemPerformer = (TextView) findViewById(R.id.item_performer);
        mItemPerformer.setSelected(true);
        mItemLyrics = (TextView) findViewById(R.id.item_lyrics);
        mItemName= (TextView) findViewById(R.id.item_name);
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
                FavoritesController favoritesController=new FavoritesController(ItemActivity.this);
                favoritesController.addToFavorites(mItemId,mItemName.getText().toString(),mItemPerformer.getText().toString());
                //Руслан тут кнопку поменяй
            }
        });
        mRefreshButton= (Button) findViewById(R.id.refresh_button);
        mRefreshButton.setVisibility(View.GONE);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshButton.setVisibility(View.GONE);
                getFragment().start();
            }
        });

        mLoadVisible=savedInstanceState==null?true:savedInstanceState.getBoolean("loadVisible");

        if(mLoadVisible){
            mProgressBar.setVisibility(View.VISIBLE);
        }else{
            mProgressBar.setVisibility(View.GONE);
        }

        if(savedInstanceState!=null){
            mItemName.setText(savedInstanceState.getString("itemName"));
            mItemPerformer.setText(savedInstanceState.getString("itemPerformer"));
            mItemLyrics.setText(savedInstanceState.getString("itemLyrics"));
        }
        Intent intent = getIntent();
        mItemName.setText(intent.getStringExtra("KEY_NAME_SONG"));
        mItemPerformer.setText(intent.getStringExtra("KEY_NAME_ARTIST"));
        mItemId=intent.getLongExtra("KEY_ID",0);
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
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("itemName", mItemName.getText().toString());
        outState.putString("itemPerformer", mItemPerformer.getText().toString());
        outState.putString("itemLyrics", mItemLyrics.getText().toString());
        outState.putBoolean("loadVisible",mLoadVisible);
        super.onSaveInstanceState(outState);
    }
}
