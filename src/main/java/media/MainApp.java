package main.java.media;

import java.io.DataOutputStream;
import java.io.File;
<<<<<<< HEAD
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
=======
import java.io.FileOutputStream;
import java.io.IOException;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469

import javafx.application.Application;
import javafx.stage.Stage;
import top.monoliths.util.label.Label;

public class MainApp extends Application {
<<<<<<< HEAD
    private static String lmpdFile;
    private static String sourceFile;
    private static String sourcePath;
    private static Label label;
    private static String[] launchParameter;

    public static void setLabel(Label label) {
        MainApp.label = label;
    }

    public static Label getLabel() {
        return label;
    }

    public static void setLmpdFile(String lmpdFile) {
        MainApp.lmpdFile = lmpdFile;
    }

    public static String getSourceFile() {
        return sourceFile;
    }

    public static String getSourcePath() {
        return sourcePath;
    }

    public static String getLmpdFile() {
        return lmpdFile;
    }

    /**
     * new a Label inctance
     * 
     * @param args launch
     * @return Label
     */
=======
    public static String lmpdFile;
    public static String sourceFileName;
    public static String sourcePath;
    public static Label label;
    public static String[] launchParameter;

>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    public static Label initialPath(String[] args) {
        if (args != null && args.length != 0) {
            initialFile(args[0]);
        } else {
            label = null;
        }
        if (label != null) {
<<<<<<< HEAD
            lmpdFile = label.getLmpdFilePosition();
            sourceFile = label.getSourceFilePosition();
=======
            System.out.println(label);
            lmpdFile = label.fileName;
            sourceFileName = label.labelData.fileName;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        }
        return label;
    }

<<<<<<< HEAD
    /**
     * get file type to set lmpd and source
     * 
     * @param filePath lmpd file or media file
     * @return if successful return true
     */
    public static boolean initialFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            // fileName="name.last"
            String fileName = file.getName();
=======
    public static boolean initialFile(String filePath) {
        System.out.println(filePath);
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            // fileName="name.last"
            String fileName = new String(file.getName());
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469

            // name="name"
            String[] center = fileName.split("\\.");
            String[] nameSplit = new String[center.length - 1];
            for (int i = 0; i < center.length - 1; i++) {
                nameSplit[i] = center[i];
            }
            String name = String.join(".", nameSplit);

            if (center[center.length - 1].equals("lmpd")) {
                // use lmpd file to start
                lmpdFile = file.getPath();
                label = new Label(lmpdFile);
<<<<<<< HEAD
                sourceFile = label.getSourceName();
                sourcePath = file.getParent() + File.separator + label.getSourceName();
            } else {
                lmpdFile = file.getParent() + File.separator + name + ".lmpd";
                sourceFile = fileName;
                sourcePath = file.getPath();
                if (new File(lmpdFile).exists()) {
                    label = new Label(lmpdFile);
                } else {
                    label = new Label(lmpdFile, System.getProperty("user.name"), sourceFile);
                    label.flush();
                }
            }
=======
                sourceFileName = label.fileName;
                sourcePath = file.getParent() + File.separator + label.labelData.fileName;
                System.out.println("");
            } else {
                lmpdFile = file.getParent() + File.separator + name + ".lmpd";
                sourceFileName = fileName;
                sourcePath = file.getPath();
                if (new File(lmpdFile).exists()) {
                    System.out.println("exist lmpd" + lmpdFile);
                    label = new Label(lmpdFile);
                } else {
                    label = new Label(lmpdFile, System.getProperty("user.name"), sourceFileName);
                    System.out.println("59:lmpd" + label);
                    label.update();
                }
            }
            System.out.println(label.toString());
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
            return true;
        }
        return false;
    }

<<<<<<< HEAD
    /**
     * to create a temp file to privide a data to windowFXMLContriller
     */
    public void tempFile() {
        // File file
        try (DataOutputStream fileStream = new DataOutputStream(new FileOutputStream(
                new File(getClass().getResource("/main/resources").toURI().getPath() + "/temp_data.data")))) {
            fileStream.writeUTF(lmpdFile);
            fileStream.writeUTF(sourcePath);
            fileStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException f) {
            f.printStackTrace();
        } catch (IOException g) {
            g.printStackTrace();
=======
    public void tempFile() {
        try {
            File file = new File(getClass().getResource("/main/resources").toURI().getPath() + "/temp_data.data");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            DataOutputStream fileStream = new DataOutputStream(new FileOutputStream(file));
            fileStream.writeUTF(lmpdFile);
            fileStream.writeUTF(sourcePath);
            fileStream.close();
            System.out.println("tempFile:" + lmpdFile);
            System.out.println("tempFile:" + sourcePath);
        } catch (Exception e) {
            e.printStackTrace();
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD
=======
        // initialFullScreen(stage);
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        initialPath(launchParameter);
        if (lmpdFile != null) {
            tempFile();
        }
        initialWindow();
    }

<<<<<<< HEAD
    public void initialWindow() {
=======
    public void initialWindow() throws IOException {
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        WindowFXMLController window = new WindowFXMLController();
        try {
            window.showScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main()
     * serves only as fallback in case the application can not be launched through
     * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
     * main().
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launchParameter = args;
        launch(args);
    }
}