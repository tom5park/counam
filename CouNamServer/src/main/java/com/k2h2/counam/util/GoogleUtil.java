package com.k2h2.counam.util;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;

/**
 * Command-line sample for the Google OAuth2 API described at <a
 * href="http://code.google.com/apis/accounts/docs/OAuth2Login.html">Using OAuth 2.0 for Login
 * (Experimental)</a>.
 * 
 * @author Yaniv Inbar
 */
public class GoogleUtil {

	private final List mClientIDs;
    private final String mAudience;
//    private final GoogleIdTokenVerifier mVerifier;
//    private final JsonFactory mJFactory;
    private String mProblem = "Verification failed. (Time-out?)";

    public GoogleUtil(String[] clientIDs, String audience) {
        mClientIDs = Arrays.asList(clientIDs);
        mAudience = audience;
        NetHttpTransport transport = new NetHttpTransport();
//        mJFactory = new GsonFactory();
//        mVerifier = new GoogleIdTokenVerifier(transport, mJFactory);
    }

    public GoogleIdToken.Payload check(String tokenString) {
        GoogleIdToken.Payload payload = null;
        /*
        try {
            GoogleIdToken token = GoogleIdToken.parse(mJFactory, tokenString);
            System.out.println(String.format("__[Z83]__: %s", token));
            System.out.println(String.format("__[C65]__: %s", token.getHeader()));
            
            if (mVerifier.verify(token)) {
            	System.out.println(String.format("__[U33]__: %s", 1));
                GoogleIdToken.Payload tempPayload = token.getPayload();
                System.out.println(String.format("__[K81]__: %s", tempPayload.getAudience()));
                System.out.println(String.format("__[Q53]__: %s", tempPayload.getEmail()));
                System.out.println(String.format("__[Q53]__: %s", tempPayload.getIssuee()));
                System.out.println(String.format("__[Q53]__: %s", tempPayload.getEmailVerified()));
                System.out.println(String.format("__[Q53]__: %s", tempPayload.getJwtId()));
                System.out.println(String.format("__[Q53]__: %s", tempPayload.getHostedDomain()));
                
                if (!tempPayload.getAudience().equals(mAudience))
                    mProblem = "Audience mismatch";
                else if (!mClientIDs.contains(tempPayload.getIssuee()))
                    mProblem = "Client ID mismatch";
                else
                    payload = tempPayload;
            }
        } catch (GeneralSecurityException e) {
            mProblem = "Security issue: " + e.getLocalizedMessage();
        } catch (IOException e) {
            mProblem = "Network problem: " + e.getLocalizedMessage();
        }
    	 */
        return payload;
    }

    public String problem() {
        return mProblem;
    }
    
    public static void main(String[] args) {
    	GoogleUtil g = new GoogleUtil(new String[] {"914307840382.apps.googleusercontent.com"}, "914307840382.apps.googleusercontent.com");
    	GoogleIdToken.Payload p = g.check("eyJhbGciOiJSUzI1NiIsImtpZCI6Ijc4MmIyOWUwMGYwN2U1YjEzYmU4ZGUxMDEyODlmMTcyMGY2YWU0NTEifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwic3ViIjoiMTEyMTQ2NjEzNTIzMTIwODIzMDA3IiwiYXpwIjoiOTE0MzA3ODQwMzgyLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXRfaGFzaCI6IlZ2N1pkeXVrU2JPa3d5aUY1V0R6Q1EiLCJjX2hhc2giOiJCRUFXTExrRDlRVzJuX2RvbjdSNndBIiwiYXVkIjoiOTE0MzA3ODQwMzgyLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaWF0IjoxMzcyNDg1ODY0LCJleHAiOjEzNzI0ODk3NjR9.tUUJWCqo2PD0UbBcbjBy4o86csJjDMVsoL_XK2jse--7RKk96gGf4Glf1DuFflkYoZy66tGsLt6mKfLqQA7Pip9Tq_IK_wDR4td3m7fOhs4dSGO1-wAfdWYREdOUXjgXO9RWKOSgG87WpwxY-rlcvt4J-0ekg5IPS1Df4UtEcKA");
    	System.out.println(String.format("__[W62]__: %s", g.problem()));
    	if(p != null)
    	System.out.println(String.format("__[Z61]__: %s", p.getEmail()));
    }
}
