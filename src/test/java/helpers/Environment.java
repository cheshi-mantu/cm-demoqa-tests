package helpers;

public class Environment {
    public static final String
        demoqaUrl = System.getProperty("url","https://demoqa.com"),
        remoteDriverUrl = System.getProperty("remote_driver_url", "http://192.168.1.10:4444/wd/hub/"), //"http://192.168.1.123:4444/wd/hub/"
        videoStorageUrl = System.getProperty("video_storage_url"); //,"http://192.168.1.10:8080/video/"
    public static boolean
        isRemoteDriver = remoteDriverUrl != null,
        isVideoOn = videoStorageUrl != null;
}

