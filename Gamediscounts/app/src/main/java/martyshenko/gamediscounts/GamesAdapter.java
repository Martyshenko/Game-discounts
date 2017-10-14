package martyshenko.gamediscounts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.author;

public class GamesAdapter extends ArrayAdapter<Game> {

    public GamesAdapter(Context context, List<Game> games){
        super(context, 0, games);
    }

    static class ViewHolder {
        @BindView(R.id.price)
        TextView priceText;
        @BindView(R.id.title)
        TextView titleText;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder(listItemView);
            listItemView.setTag(holder);
        } else {
            //if view already exists, get the holder instance from the view
            holder = (ViewHolder) listItemView.getTag();
        }
        final Game currentGames = getItem(position);

        String price = currentGames.getPriceCut();
        // Display the date of the current news in the TextView
        holder.priceText.setText(price);

        String title = currentGames.getTitle();
        // Display the tile of the current news in the TextView
        holder.titleText.setText(title);


        return listItemView;
    }
}
