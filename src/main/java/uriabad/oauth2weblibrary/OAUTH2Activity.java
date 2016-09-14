package uriabad.oauth2weblibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import uriabad.oauth2weblibrary.config.OAUTH2Config;
import uriabad.oauth2weblibrary.net.AccessToken;
import uriabad.oauth2weblibrary.net.TokenService;

public class OAUTH2Activity extends AppCompatActivity implements OAUTH2View {

    public static final int SUCESS_RESULT = 1;
    public static final String TOKEN_RESULT = "token_result";
    private static final String INTENT_EXTRA_OAUTH_CONFIG = "oauth2.CONFIG";
    private OAUTH2Config oauth2Config;
    private TokenService tokenService;

    private WebView webView;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;

    public static Intent getCallingIntent(Context context, OAUTH2Config oauth2Config){
        Intent callingIntent = new Intent(context,OAUTH2Activity.class);
        callingIntent.putExtra(INTENT_EXTRA_OAUTH_CONFIG,oauth2Config);
        return callingIntent;
    }

    @Override
    public OAUTH2Config getConfig() {
        return oauth2Config;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_oauthwebview);
        webView = (WebView)findViewById(R.id.webViewLogin);
        progressBar = (ProgressBar)findViewById(R.id.progressBarLogin);

        if(savedInstanceState== null){
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                oauth2Config = (OAUTH2Config)extras.getSerializable(INTENT_EXTRA_OAUTH_CONFIG);
            }
        }

        tokenService = TokenService.Creator.tokenService(this,getConfig().getApiAuthBase());
        loginPresenter = new LoginPresenter(this,oauth2Config,tokenService);
        initialize();
    }

    private void initialize(){
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSaveFormData(false);
        webView.setWebViewClient(loginPresenter);
        progressBar.setVisibility(View.INVISIBLE);
        loginPresenter.initialize();
    }

    @Override
    public void loadUrl(String url){
        webView.loadUrl(url);
    }

    @Override
    public void finishLogin(AccessToken accessToken){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(OAUTH2Activity.TOKEN_RESULT,accessToken);
        setResult(SUCESS_RESULT);
        finish();
    }

    @Override
    public void cleanCookies(){
        CookieManager.getInstance().removeAllCookie();
    }

    @Override
    public void showLoader(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showWebView(){
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWebView(){
        webView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String errorMessage){
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
}
