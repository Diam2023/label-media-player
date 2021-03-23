package main.java.media;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import top.monoliths.util.label.Label;

public class MainApp extends Application {
    public static String lmpdFile;
    public static String sourceFileName;
    public static String sourcePath;
    public static Label label;
    public static String[] launchParameter;

    public static Label initialPath(String[] args) {
        if (args != null && args.length != 0) {
            initialFile(args[0]);
        } else {
            label = null;
        }
        if (label != null) {
            System.out.println(label);
            lmpdFile = label.fileName;
            sourceFileName = label.labelData.fileName;
        }
        return label;
    }

    public static boolean initialFile(String filePath) {
        System.out.println(filePath);
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            // fileName="name.last"
            String fileName = new String(file.getName());

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
                    // System.out.println("59:lmpd" + label);
                    label.update();
                }
            }
            // System.out.println(label.toString());
            return true;
        }
        return false;
    }

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
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // initialFullScreen(stage);
        initialPath(launchParameter);
        if (lmpdFile != null) {
            tempFile();
        }
        initialWindow();
    }

    public void initialWindow() throws IOException {
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