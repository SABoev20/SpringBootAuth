package project.aurora.auth.models.constants;

public class PathConstants {
    public static final String BASE_PATH = "/";
    public static final String REFRESH_COOKIE_PATH = "/api/auth/refresh";
    public static final String BASE_MAPPING_PATH = "/api";
    public static final String AUTH_MAPPING_PATH = BASE_MAPPING_PATH + "/auth";
    public static final String OAUTH2_MAPPING_PATH = BASE_MAPPING_PATH + "/oauth2";
    public static final String TOKEN_MAPPING_PATH = BASE_MAPPING_PATH + "/token";
    public static final String USER_MAPPING_PATH = BASE_MAPPING_PATH + "/users";

    private PathConstants(){}
}
