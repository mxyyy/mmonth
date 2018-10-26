package utils;


public class StringUtils {
    public static String https2Http(String url) {
        return url.replace("https", "http");
    }
}
