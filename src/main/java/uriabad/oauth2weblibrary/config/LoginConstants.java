package uriabad.oauth2weblibrary.config;

import java.io.Serializable;

/**
 * Created by Uri Abad on 12/09/16.
 *
 * oriol.apa@gmail.com
 */
public interface LoginConstants extends Serializable{

    String KEY_OAUTH = "oauth";
    String KEY_AUTHORIZE = "authorize";
    String KEY_DEVICE = "device";
    String KEY_CLIENT_ID = "client_id";
    String KEY_REDIRECT_URI = "redirect_uri";
    String KEY_RESPONSE_TYPE = "response_type";

    String VALUE_CLIENT = "client";
    String VALUE_SECRET = "secret";
    String VALUE_REDIRECT_URI = "redirect";
    String VALUE_BASE_URL = "base_url";

    String getKeyOauth();
    String getKeyAuthorize();
    String getKeyDevice();
    String getKeyClientId();
    String getKeyRedirectUri();
    String getKeyResponseType();

    String getValueClient();
    String getValueSecret();
    String getValueRedirectUri();
    String getValueBaseUrl();
}
