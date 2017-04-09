package net.pacee.studio.Utils;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

import static net.pacee.studio.Utils.ConnectionUtils.getResponseFromHttpUrl;


public class AsyncTaskLoader extends AsyncTask<URL,Void,String>{
    private AsyncCallback callback;
    public interface AsyncCallback
    {
        public void getJson(String jsonData);
    }
    public void setCallback(AsyncCallback callback)
    {
        this.callback=callback;
    }
    @Override
    protected String doInBackground(URL... urls) {
        URL searchUrl = urls[0];
        String results = null;
        try {
            results = getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    protected void onPostExecute(String result) {
        callback.getJson(result);
    }
}
