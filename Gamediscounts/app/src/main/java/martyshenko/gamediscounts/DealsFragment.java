package martyshenko.gamediscounts;

import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DealsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Game>> {

    private static final int GAMES_LOADER_ID = 1;
    private String mRequest =
            "https://api.isthereanydeal.com/v01/deals/list/us/?key=fc3af7cf2aab494a009bc32bfb323c10500aa2be";

    private TextView mEmptyView;
    private GamesAdapter mAdapter;
    private View mLoadingIndicator;

    public DealsFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_of_games, container, false);

        mLoadingIndicator = rootView.findViewById(R.id.loading_spinner);
        ListView gamesListView = (ListView) rootView.findViewById(R.id.list);

        mEmptyView = (TextView) rootView.findViewById(R.id.empty_list_item);
        gamesListView.setEmptyView(mEmptyView);

        mAdapter = new GamesAdapter(
                getContext(), new ArrayList<Game>());

        gamesListView.setAdapter(mAdapter);
        initLoader();

        return rootView;
    }

    @Override
    public Loader<List<Game>> onCreateLoader(int id, Bundle args) {
        return new GamesLoader(getContext(), mRequest);
    }

    @Override
    public void onLoadFinished(Loader<List<Game>> loader, List<Game> listOfGames) {
        mLoadingIndicator.setVisibility(View.GONE);
        mAdapter.clear();

        // If there is a valid list of {@link listOfNewses}, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (listOfGames != null && !listOfGames.isEmpty()) {
            mAdapter.addAll(listOfGames);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Game>> loader) {
        mAdapter.clear();

    }

    private void initLoader() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getActivity().getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(GAMES_LOADER_ID, null, DealsFragment.this);
        } else {
            //Otherwise, display error
            //First, hide loading indicator so error message will be visible
            //mLoadingIndicator = findViewById(R.id.loading_spinner);
            mLoadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyView.setText(R.string.no_internet);
        }

    }

}
