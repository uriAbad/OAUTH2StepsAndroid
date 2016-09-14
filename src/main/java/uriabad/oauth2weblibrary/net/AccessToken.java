package uriabad.oauth2weblibrary.net;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Uri Abad on 12/09/16.
 * Seidor S.A.
 * oabad@seidor.es
 */
public class AccessToken implements Serializable {

    @SerializedName("access_token") private String accessToken;
    @SerializedName("refresh_token") private String refreshToken;
    @SerializedName("expires_in") private long expiresIn;
    private long acquireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getAcquireTime() {
        return acquireTime;
    }

    public void setAcquireTime(long time) {
        this.acquireTime = time;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
