package com.example.retrofitdemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends ListActivity {

    ListView mListView;
    Callback<List<GitHubClient.Contributor>> callback = new Callback<List<GitHubClient.Contributor>>() {
        @Override
        public void onResponse(List<GitHubClient.Contributor> contributors, Retrofit retrofit) {
            ArrayAdapter<GitHubClient.Contributor> adapter = new ArrayAdapter<GitHubClient.Contributor>(getApplicationContext(), android.R.layout.simple_list_item_1, contributors);
            mListView.setAdapter(adapter);

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = getListView();
        GitHubClient.getContributors(callback);
    }


}
