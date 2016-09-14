package uriabad.oauth2weblibrary;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uriabad.oauth2weblibrary.config.OAUTH2Config;
import uriabad.oauth2weblibrary.net.AccessToken;
import uriabad.oauth2weblibrary.net.TokenService;

/**
 * Created by Uri Abad on 08/09/16.
 * Seidor S.A.
 * oabad@seidor.es
 */
public class LoginPresenter extends WebViewClient {

    OAUTH2View view;
    OAUTH2Config config;
    TokenService tokenService;

    public LoginPresenter(OAUTH2View view,OAUTH2Config config,TokenService tokenService) {
        this.view = view;
        this.config = config;
        this.tokenService = tokenService;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        if(url.startsWith(this.view.getConfig().getRedirect_uri())){
            String[] parts = url.split("=");
            final String code = parts[1];

            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);

            view.stopLoading();
            this.view.hideWebView();
            this.view.showLoader();

            getToken(code);
            System.out.println("we have token!");
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onPageFinished(WebView view, String url) {
        view.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    public void initialize(){
        view.cleanCookies();
        launchUrl();
    }

    public void launchUrl() {
        String url = buildUrl();
        System.out.println(url);
        Log.e("URL",url);
        view.loadUrl(url);
    }

    private String decode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, "UTF-8");
    }

    private String buildUrl() {

//            Uri.Builder builder = new Uri.Builder()
//                    .authority(config.getApiAuthBase())
//                    .appendPath(config.getLoginConstants().getKeyOauth())
//                    .appendPath(config.getLoginConstants().getKeyAuthorize())
//                    .appendQueryParameter(config.getLoginConstants().getKeyDevice(),
//                            (Build.MODEL).replace(" ", "%20")+Build.SERIAL)
//                    .appendQueryParameter(config.getLoginConstants().getKeyClientId(),
//                            this.view.getConfig().getClient_id())
//                    .appendQueryParameter(config.getLoginConstants().getKeyRedirectUri(),
//                            this.view.getConfig().getRedirect_uri())
//                    .appendQueryParameter(config.getLoginConstants().getKeyResponseType(),
//                            this.view.getConfig().getScope());
                Uri builtUri = Uri.parse(config.getApiAuthBase())
                        .buildUpon()
                    .appendPath(config.getLoginConstants().getKeyOauth())
                    .appendPath(config.getLoginConstants().getKeyAuthorize())
                    .appendQueryParameter(config.getLoginConstants().getKeyDevice(),
                            (Build.MODEL).replace(" ", "%20")+Build.SERIAL)
                    .appendQueryParameter(config.getLoginConstants().getKeyClientId(),
                            this.view.getConfig().getClient_id())
                    .appendQueryParameter(config.getLoginConstants().getKeyRedirectUri(),
                            this.view.getConfig().getRedirect_uri())
                    .appendQueryParameter(config.getLoginConstants().getKeyResponseType(),
                            this.view.getConfig().getCode())
                        .build();


//        String unformat = builder.build().toString();
        String unformat = builtUri.toString();
            String format = null;
        try {
            format = decode(unformat);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
            return format;
    }

    public void getToken(String code){
        tokenService.getToken(config.getTokenEndpoint(),
                "authorization_code",
                config.getLoginConstants().getValueClient(),
                config.getLoginConstants().getValueSecret(),
                code,
                config.getLoginConstants().getValueRedirectUri()).enqueue(new Callback
                <AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                view.finishLogin(response.body());
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                view.showError("FAILURE!");
            }
        });
    }
}
