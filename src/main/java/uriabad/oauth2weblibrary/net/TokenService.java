package uriabad.oauth2weblibrary.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Uri Abad on 12/09/16.
 *
 * oriol.apa@gmail.com
 */
public interface TokenService {

    class Creator{
        public static TokenService tokenService(Context context, String baseUrl){

            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();

            return retrofit.create(TokenService.class);
        }
    }

    @FormUrlEncoded
    @POST("{tokenUrl}")
    Call<AccessToken> getToken(
            @Path(value = "tokenUrl",encoded = true) String tokenUrl,
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code,
            @Field("redirect_uri") String redirectUri
    );

}
