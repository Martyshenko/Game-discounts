package martyshenko.gamediscounts;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class GamesLoader extends AsyncTaskLoader<List<Game>> {

    //Query URL
    private String mUrl;

    public GamesLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    //This is on a background thread.

    @Override
    public List<Game> loadInBackground() {
        // Don't perform the request if there are no URLs, or the first URL is null.
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of news.
        List<Game> listOfGames = QueryUtils.fetchGamesData(mUrl);

        return listOfGames;
    }
}
