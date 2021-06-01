package com.example.cfvirtual.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cfvirtual.R;

import java.util.ArrayList;
import java.util.List;

public class RatingGeneratorAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ItemContest> itemContestList = new ArrayList<>();

    public RatingGeneratorAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contest, parent, false);
        return new ItemContestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemContest itemContest = itemContestList.get(position);
        ItemContestViewHolder itemContestViewHolder = (ItemContestViewHolder)holder;
        itemContestViewHolder.contestId.setText("#" + itemContest.getContestId() + "");
        itemContestViewHolder.rank.setText("Rank : " + itemContest.getRank() + "");
        itemContestViewHolder.points.setText("Points : " + itemContest.getPoints() + "");
        itemContestViewHolder.prevRating.setText("Old rating : " + itemContest.getPrevRating() + "");
        itemContestViewHolder.nextRating.setText("New Rating : " + itemContest.getNextrating() + "");
        itemContestViewHolder.checkBox.setChecked(itemContest.isChecked());

        if(itemContest.isCalculated()){
            itemContestViewHolder.prevRating.setVisibility(View.VISIBLE);
            itemContestViewHolder.nextRating.setVisibility(View.VISIBLE);
        } else{
            itemContestViewHolder.prevRating.setVisibility(View.GONE);
            itemContestViewHolder.nextRating.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return (itemContestList!=null)?itemContestList.size():0;
    }

    public void addItemContest(ItemContest itemContest){
        itemContestList.add(itemContest);
        notifyItemInserted(itemContestList.size()-1);
    }

    public void resetData(){
        itemContestList = new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setData(List<ItemContest> itemContests){
        this.itemContestList = itemContests;
        notifyDataSetChanged();
    }

    public List<ItemContest> getItemContestList(){
        return itemContestList;
    }

    public class ItemContestViewHolder extends RecyclerView.ViewHolder{
        private TextView contestId;
        private TextView rank;
        private TextView points;
        private TextView prevRating;
        private TextView nextRating;
        private CheckBox checkBox;

        public ItemContestViewHolder(@NonNull View itemView) {
            super(itemView);
            contestId = itemView.findViewById(R.id.contest_id);
            rank = itemView.findViewById(R.id.rank);
            points = itemView.findViewById(R.id.points);
            prevRating = itemView.findViewById(R.id.prev_rating);
            nextRating = itemView.findViewById(R.id.next_rating);
            checkBox = itemView.findViewById(R.id.check_box);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox)v;
                    int pos = getAdapterPosition();
                    itemContestList.get(pos).setChecked(checkBox.isChecked());
                }
            });
        }
    }
}
