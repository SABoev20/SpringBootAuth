package project.aurora.auth.models.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OAuth2Constants {
    public static final String REDIRECT_URL = "http://localhost:5173/oauth2/callback/";
    public static final Map<String, String> TOKEN_URLS;
    public static final Map<String, String> USER_INFO_URLS;

    static {
        TOKEN_URLS = Map.of("github", "https://github.com/login/oauth/access_token", "google", "https://oauth2.googleapis.com/token", "discord", "https://discord.com/api/oauth2/token");
        USER_INFO_URLS = Map.of("github", "https://api.github.com/user", "google", "https://www.googleapis.com/oauth2/v3/userinfo", "discord", "https://discord.com/api/users/@me");
    }

    private OAuth2Constants(){}
}
