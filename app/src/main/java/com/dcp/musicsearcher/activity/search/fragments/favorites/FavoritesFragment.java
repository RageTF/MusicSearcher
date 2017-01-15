package com.dcp.musicsearcher.activity.search.fragments.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.activity.search.list.item.SongActivity;
import com.dcp.musicsearcher.data.FavoritesController;

import java.util.ArrayList;

/**
 * Created by Rage on 14.01.2017.
 */

public class FavoritesFragment extends Fragment implements FavoritesListListener {

    private RecyclerView mFavoritesList;
    private FavoritesAdapter mFavoritesAdapter;
    private TextView mEmptyList;

    private FavoritesController mFavoritesController;
    private ArrayList<FavoritesController.SongInfo> mSongs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFavoritesController =new FavoritesController(getContext());
        mSongs = mFavoritesController.getFavoritesSong();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFavoritesAdapter=new FavoritesAdapter();
        mFavoritesAdapter.setFavoritesListListener(this);

        mFavoritesList=(RecyclerView) view.findViewById(R.id.favorites_list);
        mFavoritesList.setAdapter(mFavoritesAdapter);
        mFavoritesList.setLayoutManager(new LinearLayoutManager(getContext()));

        mEmptyList = (TextView) view.findViewById(R.id.empty_list);

        if(mSongs.isEmpty()){
            mEmptyList.setVisibility(View.VISIBLE);
        }else{
            mEmptyList.setVisibility(View.GONE);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void updateFavoritesData(){
        if(mSongs!=null && mFavoritesAdapter!=null){
            mSongs = mFavoritesController.getFavoritesSong();
            mFavoritesAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(getContext(), SongActivity.class);
        intent.putExtra("KEY_TRACK_ID", mSongs.get(position).getTrackId());
        intent.putExtra("KEY_NAME_SONG", mSongs.get(position).getNameSong());
        intent.putExtra("KEY_NAME_ARTIST", mSongs.get(position).getNameArtist());
        getContext().startActivity(intent);
    }

    private class FavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private FavoritesListListener mFavoritesListListener;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getContext()).inflate(R.layout.item_track,null);
            return new SongHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SongHolder)holder).bind(position);
        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }

        public void setFavoritesListListener(FavoritesListListener favoritesListListener){
            mFavoritesListListener=favoritesListListener;
        }

        private class SongHolder extends RecyclerView.ViewHolder{

            private TextView mNameSong;
            private TextView mNameArtist;

            public SongHolder(View itemView) {
                super(itemView);
                mNameSong=(TextView) itemView.findViewById(R.id.tv_song_name);
                mNameArtist =(TextView) itemView.findViewById(R.id.tv_song_name);
            }

            public void bind(final int position){
                mNameSong.setText(mSongs.get(position).getNameSong());
                mNameArtist.setText(mSongs.get(position).getNameArtist());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFavoritesListListener.onItemClick(position);
                    }
                });
            }
        }

    }
}
