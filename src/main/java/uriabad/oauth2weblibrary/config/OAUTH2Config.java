package uriabad.oauth2weblibrary.config;

import java.io.Serializable;

/**
 * Created by Uri Abad on 08/09/16.
 *
 * oriol.apa@gmail.com
 */
public class OAUTH2Config implements Serializable {

    private String apiAuthBase;
    private String tokenEndpoint;
    private String client_id;
    private String redirect_uri;
    private String response_type;
    private String code;
    private String scope;
    private LoginConstants loginConstants;

    public OAUTH2Config() {
    }

    public String getApiAuthBase() {
        return apiAuthBase;
    }

    public void setApiAuthBase(String apiAuthBase) {
        this.apiAuthBase = apiAuthBase;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public LoginConstants getLoginConstants() {
        return loginConstants;
    }

    public void setLoginConstants(LoginConstants loginConstants) {
        this.loginConstants = loginConstants;
    }
}
