package config;

public class RunConfig {
    public static String env() { return System.getProperty("env", "staging"); }
    public static String browser() { return System.getProperty("browser", "chrome"); }
    public static boolean headless() { return Boolean.parseBoolean(System.getProperty("headless", "true")); }
    public static int threads() { return Integer.parseInt(System.getProperty("threads", "2")); }
}
