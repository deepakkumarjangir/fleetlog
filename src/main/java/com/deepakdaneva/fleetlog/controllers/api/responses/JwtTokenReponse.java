/**
 * FleetLog
 * Apr 29, 2019 9:15:23 PM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.controllers.api.responses;

import java.io.Serializable;

public class JwtTokenReponse implements Serializable {

    private static final long serialVersionUID = -3563018033061772370L;

    private String jwtToken;
    private String jwtTokenType = "Bearer";

    public JwtTokenReponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken() {
        return jwtToken;
    }

    public void setToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getTokenType() {
        return jwtTokenType;
    }

    public void setTokenType(String jwtTokenType) {
        this.jwtTokenType = jwtTokenType;
    }
}
