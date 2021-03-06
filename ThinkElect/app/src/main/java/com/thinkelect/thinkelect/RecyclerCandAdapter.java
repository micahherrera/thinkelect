package com.thinkelect.thinkelect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by micahherrera on 8/12/16.
 */
public class RecyclerCandAdapter extends RecyclerView.Adapter<RecyclerCandAdapter.likesViewHolder> {
        Context context;
    ArrayList<Candidate> candidates;

    public RecyclerCandAdapter(ArrayList<Candidate> candidates, Context context) {
        this.context = context;
        this.candidates = candidates;
    }

    @Override
    public likesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.candidate_item_card, parent, false);
        likesViewHolder holder = new likesViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(likesViewHolder holder, int position) {

        //TODO: setup layout and figure out how we want to display our images
        Picasso.with(this.context)
                .load(candidates.get(position).getImgUrl())

                .centerInside().fit()
                .into(holder.mImage);
        holder.mName.setText(candidates.get(position).getFirstName()+" " +candidates.get(position).getLastName());
        holder.mParty.setText(candidates.get(position).getParty());
        holder.mOffice.setText(candidates.get(position).getOffice());
        holder.mContributions.setText(candidates.get(position).getContributionsTotal());

    }


    @Override
    public int getItemCount() {
        return candidates.size();
    }

    // setting the ViewHolder for my recyclerview with a clickListener

    public class likesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView mImage;
        public TextView mParty;
        TextView mName;
        TextView mContributions;
        TextView mOffice;

        public likesViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            mImage = (ImageView) itemView.findViewById(R.id.pic);
            mParty = (TextView) itemView.findViewById(R.id.office);
            mName = (TextView) itemView.findViewById(R.id.name);
            mContributions = (TextView) itemView.findViewById(R.id.contributions);
            mOffice = (TextView) itemView.findViewById(R.id.party);

        }

        // telling my ClickListener what to do
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CandidateBioActivity.class);
            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            int positionLiked = getAdapterPosition();
            // TODO: set up removal on clicks




            return false;
        }
    }

}


