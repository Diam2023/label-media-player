package main.java.media;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.stage.Stage;
import top.monoliths.util.label.Label;

public class MainApp extends Application {
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

    public static Label initialPath(String[] args) {
        if (args != null && args.length != 0) {
            initialFile(args[0]);
        } else {
            label = null;
        }
        if (label != null) {
            lmpdFile = label.getLmpdFilePosition();
            sourceFile = label.getSourceFilePosition();
        }
        return label;
    }

    public static boolean initialFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            // fileName="name.last"
            String fileName = file.getName();

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
            return true;
        }
        return false;
    }

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
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        initialPath(launchParameter);
        if (lmpdFile != null) {
            tempFile();
        }
        initialWindow();
    }

    public void initialWindow() {
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