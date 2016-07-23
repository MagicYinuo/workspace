package com.example.retrofitdemo;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/7/18.
 */
public class GitHubClient {
    private static final String API_URL = "https://api.github.com";

    static class Contributor {
        String login;
        int contributions;

        @Override
        public String toString() {
            return login + "," + contributions;
        }
    }

    interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Path("owner") String owner, @Path("repo") String repo);

        @GET("/repos/{owner}/{repo}/contributors")
        void contributors(@Path("owner") String owner, @Path("repo") String repo, Callback<List<Contributor>> callback);
    }

    public static void getContributors(Callback<List<Contributor>> callback) {
        RestAdapter restAdapter = new RestAdapter.Builder().setServer(API_URL).build();

        // Create an instance of our GitHub API interface.
        GitHub github = restAdapter.create(GitHub.class);

        // Fetch and print a list of the contributors to this library.
        github.contributors("square", "retrofit", callback);
    }
}
