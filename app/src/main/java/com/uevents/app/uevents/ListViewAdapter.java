package com.uevents.app.uevents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rebeccahe on 11/30/16.
 * https://developer.android.com/training/material/lists-cards.html
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private Event[] dataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Each event item stored in a card
        public CardView cardView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    /**
     * Constructor initializes the list of events.
     *
     * @param dataset
     */
    public ListViewAdapter(Event[] dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    /**
     * Creates a new CardView and returns the corresponding ViewHolder.
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false); // create a new view
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * Retrieves the Event from the dataset at this position. Replaces the contents of the view
     * with that Event element.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Event event = dataset[position];
        TextView title = (TextView) holder.cardView.findViewById(R.id.list_item_title);
        TextView attendance = (TextView) holder.cardView.findViewById(R.id.list_item_attendance);
        ImageView icon = (ImageView) holder.cardView.findViewById(R.id.list_item_icon);

        title.setText(event.title);
        attendance.setText(event.currAttendance + "/" + event.maxAttendance + " attending");
        switch(event.category) {
            case SPORT: icon.setImageResource(R.mipmap.ic_sport); break;
            case STUDY: icon.setImageResource(R.mipmap.ic_study); break;
            case SOCIAL: icon.setImageResource(R.mipmap.ic_social); break;
            case CLUB: icon.setImageResource(R.mipmap.ic_club); break;
        }
        // TODO: Time - Assume within boundary? Start/End v. how much longer?
        // TODO: Location - Find distance from current location using Google Maps API?

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category", event.category.name()); // decode using Event.Category.valueOf(s)
                bundle.putString("title", event.title);
                bundle.putString("description", event.description);
                bundle.putFloat("lat", event.lat);
                bundle.putFloat("lon", event.lon);
                bundle.putLong("startTime", event.startTime.getTimeInMillis());
                bundle.putLong("endTime", event.startTime.getTimeInMillis());
                bundle.putInt("maxAttendance", event.maxAttendance);
                bundle.putInt("currAttendance", event.currAttendance);

                Intent intent = new Intent(context, ListViewEventActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    /**
     * Returns the size of the dataset
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
