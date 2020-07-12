package helpers;

public class Environment {
    public static final String
        url = System.getProperty("url","https://www.chromeindustries.com/"),
        remoteDriverUrl = System.getProperty("remote_driver_url"),
        videoStorageUrl = System.getProperty("video_storage_url");
    public static boolean
        isRemoteDriver = remoteDriverUrl != null,
        isVideoOn = videoStorageUrl != null;
}

