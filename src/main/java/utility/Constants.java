package utility;



public class Constants {


    public static final boolean IS_PRODUCTION = true;
    public static final boolean NO_RESET = true;
    public static final boolean FULL_RESET = false;


    /**
     * App configs
     */

    public static final String PHONE_NUMBER = "01999202";
    public static final String OTP = "123456";
    public static final String ROOT_REPORT =  "src/reports/";

    public static final long SLEEP_IN_MILLIS = 10;
    public static final long WAIT_FOR_NEXT_STEP = 50;
    public static final long WAIT_FOR_NEXT_SCREEN = 100;
    public static final long TIME_OUT_IN_SECONDS = 1;
    public static final long MAX_SLEEP_DURATION = 1000;

    public static final String getAppDriverPath(){
        if (IS_PRODUCTION) {
            return "src/app/driver.apk";
        }
        return "src/app/app-driver-debug.apk";
    }


    public static final String getAppPackage(){
        if (IS_PRODUCTION) {
            return "driver.facecar.com.facecardriver";
        }
        return "driver.facecar.com.facecardriver.devvvvv";
    }

    public static final String getAppActivity(){
        if (IS_PRODUCTION) {
            return "driver.facecar.com.facecardriver.SplashActivity";
        }
        return "driver.facecar.com.facecardriver.SplashActivityyyy";
    }

    public static final String prefixID(){
        if (IS_PRODUCTION) {
            return "driver.facecar.com.facecardriver:id/";
        }
        return "driver.facecar.com.facecardriver.dev:id/";
    }
}
