package com.uevents.app.uevents;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rebeccahe on 11/30/16.
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private Event[] dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item
        public CardView cardView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    // --- Constructor to initialize list of events ---
    public ListViewAdapter(Event[] dataset) {
        this.dataset = dataset;
    }

    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Event event = dataset[position];
        TextView text = (TextView) holder.cardView.findViewById(R.id.list_item_title);
        text.setText(event.title);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
