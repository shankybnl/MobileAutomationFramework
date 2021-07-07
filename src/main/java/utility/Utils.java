package utility;

import Reports.Log;
import utility.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Utils {

    private static String currentTime = new SimpleDateFormat("dd-MM-yyyy-HHmmss").format(Calendar.getInstance().getTime());
    private static String reportDir;

    /**
     * Create a directory at path name
     * @return : return path of directory
     */
    public static String getReportDir() {

        if (reportDir == null) {
            String pathName = Constants.ROOT_REPORT + currentTime;

            File theDir = new File(pathName);

            if (!theDir.exists()) {

                Log.info("CREATING A DIRECTORY");

                try {

                    theDir.mkdirs();
                    Log.info("a directory is created: " + theDir.getPath());
                }
                catch(SecurityException se){

                    Log.error("CREATE A DIR IS FAILED!" + se.getMessage());
                    return null;
                }
            }

            reportDir = theDir.getPath();
        }
        return reportDir;
    }

    /**
     * create Dir
     */

    public static String createDir(String dirName) {

        File theDir = new File(dirName);

        if (!theDir.exists()) {

            Log.info("CREATING A DIRECTORY");

            try {

                theDir.mkdirs();
                Log.info("a directory is created: " + theDir.getPath());
            }
            catch(SecurityException se){

                Log.error("CREATE A DIR IS FAILED!" + se.getMessage());
                return null;
            }
        }

        return theDir.getPath();
    }

    static void runCommand(String cmd) throws IOException {
        Runtime.getRuntime().exec(cmd);
    }

    public static void launchEmulator() {
        try {
            runCommand("emulator @Pixel");
            System.out.println("Start Emulator...");
        } catch (Exception ex) {ex.printStackTrace();}
    }

    public static void exitAppium() throws IOException {
        Log.info("Exit Emulator...");
        runCommand("exit appium");
    }

}
