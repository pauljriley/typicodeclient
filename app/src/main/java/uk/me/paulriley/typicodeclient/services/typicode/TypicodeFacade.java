package uk.me.paulriley.typicodeclient.services.typicode;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

public class TypicodeFacade {
    private final TypicodeResults typicodeResults;

    public TypicodeFacade() {
        OkHttpClient httpClient = initialiseHttpClient();

        TypicodeService service = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(TypicodeService.class);

        typicodeResults = new TypicodeResults(service);
    }

    private OkHttpClient initialiseHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    public TypicodeResults getTypicodeResults() {
        return typicodeResults;
    }
}
