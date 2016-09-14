package uriabad.oauth2weblibrary;

import uriabad.oauth2weblibrary.config.OAUTH2Config;
import uriabad.oauth2weblibrary.net.AccessToken;

/**
 * Created by Uri Abad on 08/09/16.
 * Seidor S.A.
 * oriol.apa@gmail.com
 */
public interface OAUTH2View {

    OAUTH2Config getConfig();

    void loadUrl(String url);

    void finishLogin(AccessToken accessToken);

    void cleanCookies();

    void showLoader();

    void hideLoader();

    void showWebView();

    void hideWebView();

    void showError(String errorMessage);
}
