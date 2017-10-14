package martyshenko.gamediscounts;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Game>>  {

    private static final int GAMES_LOADER_ID = 1;
    private String mRequest =
            "https://api.isthereanydeal.com/v01/deals/list/us/?key=fc3af7cf2aab494a009bc32bfb323c10500aa2be";

    private TextView mEmptyView;
    private GamesAdapter mAdapter;
    private View mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingIndicator = findViewById(R.id.loading_spinner);
        ListView gamesListView = (ListView) findViewById(R.id.list);

        mEmptyView = (TextView) findViewById(R.id.empty_list_item);
        gamesListView.setEmptyView(mEmptyView);

        mAdapter = new GamesAdapter(
                this, new ArrayList<Game>());

        gamesListView.setAdapter(mAdapter);
        initLoader();

    }

    @Override
    public Loader<List<Game>> onCreateLoader(int id, Bundle args) {
        return new GamesLoader(this, mRequest);
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
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(GAMES_LOADER_ID, null, MainActivity.this);
        } else {
            //Otherwise, display error
            //First, hide loading indicator so error message will be visible
            mLoadingIndicator = findViewById(R.id.loading_spinner);
            mLoadingIndicator.setVisibility(View.GONE);
            // Update empty state with no connection error message
            mEmptyView.setText(R.string.no_internet);
        }
    }

}
