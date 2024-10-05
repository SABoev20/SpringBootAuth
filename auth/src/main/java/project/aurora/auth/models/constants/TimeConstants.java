package project.aurora.auth.models.constants;

public class TimeConstants {
    public static final int DEVICE_EXPIRY = 60 * 60 * 24 * 365;
    public static final int REFRESH_TOKEN_EXPIRY = 60 * 60 * 24 * 30;
    public static final int REFRESH_TOKEN_ROTATION = 60 * 60 * 12;
    public static final int ACCESS_TOKEN_EXPIRY = 60 * 60;
    public static final int INVALIDATION_EXPIRY = 0;

    private TimeConstants(){}
}
