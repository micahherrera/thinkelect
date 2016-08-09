package com.thinkelect.thinkelect;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by micahherrera on 8/8/16.
 */
public class GetGoogleStuff {

    static OkHttpClient client = new OkHttpClient();

    static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
