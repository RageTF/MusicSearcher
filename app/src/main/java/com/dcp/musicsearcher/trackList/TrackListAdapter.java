package com.dcp.musicsearcher.trackList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.api.pojo.songs.SongSearch;
import com.dcp.musicsearcher.api.pojo.songs.TrackList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Ruslan on 15.01.2017.
 */

public class TrackListAdapter extends RecyclerView.Adapter {

    private List<TrackItem> itemList;
    private OnTrackItemClickListener onTrackItemClickListener;

    public void setOnTrackItemClickListener(OnTrackItemClickListener onTrackItemClickListener) {
        this.onTrackItemClickListener = onTrackItemClickListener;
    }

    public TrackListAdapter(Response<SongSearch> response) {
        List<TrackList> fromResponseList = response.body()
                .getMessage()
                .getBody()
                .getTrackList();
        itemList = new ArrayList<>();

        for (TrackList trackList:fromResponseList){
            String artist = trackList.getTrack().getArtistName();
            String songName = trackList.getTrack().getTrackName();
            long id  = trackList.getTrack().getTrackId();
            itemList.add(new TrackItem(artist,songName,id));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, null);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView tvArtist = ((TrackViewHolder) holder).tvArtist;
        tvArtist.setText(itemList.get(position).getArtist());

        TextView tvSongName = ((TrackViewHolder) holder).tvSongName;
        tvSongName.setText(itemList.get(position).getSongName());

        ((TrackViewHolder) holder).bind(position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class TrackViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSongName;
        private TextView tvArtist;

        public TrackViewHolder(View itemView) {
            super(itemView);

            tvSongName = (TextView) itemView.findViewById(R.id.tv_song_name);
            tvArtist = (TextView) itemView.findViewById(R.id.tv_artist);
        }
        void bind(final int position){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String artist = itemList.get(position).getArtist();
                    String songName = itemList.get(position).getSongName();
                    long id = itemList.get(position).getId();
                    onTrackItemClickListener.onClickListener(artist,songName,id);
                }
            });
        }
    }
}
