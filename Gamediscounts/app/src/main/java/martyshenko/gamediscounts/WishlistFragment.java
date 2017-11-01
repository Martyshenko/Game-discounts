package martyshenko.gamediscounts;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class WishlistFragment extends Fragment {

    private TextView mEmptyView;
    private GamesAdapter mAdapter;
    private View mLoadingIndicator;

    public WishlistFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_of_games, container, false);

        mLoadingIndicator = rootView.findViewById(R.id.loading_spinner);
        mLoadingIndicator.setVisibility(View.GONE);

        ListView gamesListView = (ListView) rootView.findViewById(R.id.list);


        mEmptyView = (TextView) rootView.findViewById(R.id.empty_list_item);
        gamesListView.setEmptyView(mEmptyView);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddActivity.class);
                startActivity(intent);
            }
        });


        return rootView;


    }

}
