package com.k2h2.counam.util;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

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
    private final GoogleIdTokenVerifier mVerifier;
    private final JsonFactory mJFactory;
    private String mProblem = "Verification failed. (Time-out?)";

    public GoogleUtil(String[] clientIDs, String audience) {
        mClientIDs = Arrays.asList(clientIDs);
        mAudience = audience;
        NetHttpTransport transport = new NetHttpTransport();
        mJFactory = new GsonFactory();
        mVerifier = new GoogleIdTokenVerifier(transport, mJFactory);
    }

    public GoogleIdToken.Payload check(String tokenString) {
        GoogleIdToken.Payload payload = null;
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
        return payload;
    }

    public String problem() {
        return mProblem;
    }
    
    public static void main(String[] args) {
    	GoogleUtil g = new GoogleUtil(new String[] {"914307840382.apps.googleusercontent.com"}, "914307840382.apps.googleusercontent.com");
    	GoogleIdToken.Payload p = g.check("eyJhbGciOiJSUzI1NiIsImtpZCI6IjU4MDk4NTc3NDM1N2U0ZWVhYjRlYzliOWI5MTNjNjBjNzZmYmFhNzYifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiYXpwIjoiOTE0MzA3ODQwMzgyLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiOTE0MzA3ODQwMzgyLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTEyMTQ2NjEzNTIzMTIwODIzMDA3IiwiYXRfaGFzaCI6Im1qUEg4Q2IxQ1k3eWszS1d3MTB6RHciLCJjX2hhc2giOiJxLUEwTGwtM3FfZmpsMUN3c0Q5UFdnIiwiaWF0IjoxMzcwODI5NDc1LCJleHAiOjEzNzA4MzMzNzV9.hEN6iH6YurapM3ZsC-3GNSkSfLsIMppF_2CDPePp1LK35Rzx5y1vmJnqUGbuAL7pPKfb70ajVN1voahEFnuxIRHaoMDFebLkSnQ8y_gW0DwjsDVw--mx0m4zs5-XMioT_MknVCdISuZbSwHjJgrT1hqNEL1JCbBUgWZbi7cn1AY");
    	System.out.println(String.format("__[W62]__: %s", g.problem()));
    	if(p != null)
    	System.out.println(String.format("__[Z61]__: %s", p.getEmail()));
    }
}
