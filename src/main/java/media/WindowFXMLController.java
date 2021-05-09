package main.java.media;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import top.monoliths.util.label.LabelItem;
import top.monoliths.util.label.LabelItemList;

/**
 * initial ui
 * 
 * @author monoliths
 * @version 1.0
 */
public class WindowFXMLController extends Application implements Initializable {

    /**
     * window stage
     */
    private Stage stage;

    /**
     * file data view
     */
    private ObservableList<FileColumnItem> fileDataMenbers;

    /**
     * label data view
     */
    private ObservableList<LabelColumnItem> labelDataMembers;

    /**
     * file view of ui
     */
    private TableView<FileColumnItem> fileView;

    /**
     * file view of ui
     */
    private TableView<LabelColumnItem> labelView;

    /**
     * label file and data
     */
    private top.monoliths.util.label.Label label;

    /**
     * media file
     */
    private String sourceFile;

    /**
     * tag of view player
     */
    private boolean isStoped = false;

    /**
     * media player
     */
    private MediaPlayer mediaPlayer;

    @FXML
    private Button add_action, full_screen;
    @FXML
    private Label file_name, duration_view;
    @FXML
    private Tab label_view, file_view, labelOption, fileOption;
    @FXML
    private Slider voice_ctrl, duration_ctrl;

    @FXML
    /**
     * adapter file add and label add
     * @param event
     */
    private void addAction(ActionEvent event) {
        // file action
        String file;
        String labelTitle;
        Long nowDuration = (long) mediaPlayer.getCurrentTime().toMillis();
        if (labelOption.isSelected()) {
            // target
            if ((labelTitle = addLabelDialog()) != null) {
                this.labelDataMembers
                        .add(new LabelColumnItem(labelTitle, top.monoliths.util.label.Label.nowTime(), nowDuration));
            }
        } else {
            if ((file = chooseFileDialog()) != null && MainApp.initialFile(file)) {
                fileDataMenbers.add(new FileColumnItem(MainApp.getSourceFile(), MainApp.getSourcePath()));
            }
        }
        flashViewDataToFileData();
    }

    @FXML
    /**
     * tag of media player state
     * @param event
     */
    private void pauseAction(ActionEvent event) {
        if (!isStoped) {
            mediaPlayer.pause();
            isStoped = true;
        } else {
            mediaPlayer.play();
            isStoped = false;
        }
    }

    @FXML
    /**
     * remove file or label data
     * @param event
     */
    private void removeAction(ActionEvent event) {
        if (labelOption.isSelected()) {
            // target
            if (!this.labelDataMembers.isEmpty()) {
                this.labelDataMembers.remove(labelView.getSelectionModel().getFocusedIndex());
            }
        } else {
            if (!this.fileDataMenbers.isEmpty()) {
                this.fileDataMenbers.remove(fileView.getSelectionModel().getFocusedIndex());
            }
        }
        flashViewDataToFileData();
    }

    /**
     * push file data to view
     */
    public void flashFileDataToViewData() {
        this.labelDataMembers.clear();
        LabelItemList labelBody = this.label.getLabelBody();
        for (int i = 0; i < labelBody.size(); i++) {
            LabelItem now = labelBody.getIndex(i);
            if (now != null) {
                this.labelDataMembers.add(new LabelColumnItem(now.getTitle(), now.getTime(), now.getDuration()));
            }
        }
    }

    /**
     * write data to file
     */
    public void flashViewDataToFileData() {
        LabelItemList labelBody = this.label.getLabelBody();
        labelBody.clear();
        for (int i = 0; i < this.labelDataMembers.size(); i++) {
            labelBody.add(new LabelItem(this.labelDataMembers.get(i).getTitle(),
                    this.labelDataMembers.get(i).getDuration(), this.labelDataMembers.get(i).getTime()));
        }
        this.label.setLabelBody(labelBody);
        this.label.flush();
    }

    /**
     * show add label view
     * @return label name
     */
    public String addLabelDialog() {
        TextInputDialog tid = new TextInputDialog();
        tid.setTitle("label");
        tid.setHeaderText("label name");
        tid.setContentText("label name:");
        Optional<String> result = tid.showAndWait();
        return result.isPresent() ? result.get() : null;
    }

    /**
     * show file chooser view
     * @return file path
     */
    public String chooseFileDialog() {
        // Stage stage = new Stage()
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open file with label media");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("all filee", "*.*"),
                new ExtensionFilter("media file", "*.mp3", "*.lmpd"));
        File file = fileChooser.showOpenDialog(stage);
        return ((file != null) && file.exists() && file.isFile()) ? file.getAbsolutePath() : null;
    }

    // initital
    public void initialLabel() {
        // System.out.println(MainApp.lmpdFile)
        label = new top.monoliths.util.label.Label(MainApp.getLmpdFile());
    }

    @SuppressWarnings("unchecked")
    public void initialViewData() {
        List<FileColumnItem> initialFileData = new ArrayList<>();
        List<LabelColumnItem> initialLabelData = new ArrayList<>();
        initialFileData.add(new FileColumnItem("name", "path"));

        fileDataMenbers = FXCollections.observableArrayList(initialFileData);
        fileView = new TableView<>();
        fileView.setItems(fileDataMenbers);
        TableColumn<FileColumnItem, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(initialFileData.get(0).nameProperty().getName()));
        TableColumn<FileColumnItem, String> pathColumn = new TableColumn<>("path");
        pathColumn.setCellValueFactory(new PropertyValueFactory<>(initialFileData.get(0).pathProperty().getName()));
        this.fileDataMenbers.remove(0);

        fileView.getColumns().setAll(nameColumn, pathColumn);

        fileOption.setContent(fileView);

        initialLabelData.add(new LabelColumnItem("title", "time", -1));
        this.labelDataMembers = FXCollections.observableArrayList(initialLabelData);
        labelView = new TableView<>();
        labelView.setItems(labelDataMembers);
        TableColumn<LabelColumnItem, String> titleColumn = new TableColumn<>("title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>(initialLabelData.get(0).titleProperty().getName()));
        TableColumn<LabelColumnItem, Long> durationColumn = new TableColumn<>("duration");
        durationColumn
                .setCellValueFactory(new PropertyValueFactory<>(initialLabelData.get(0).durationProperty().getName()));
        this.labelDataMembers.remove(0);

        titleColumn.setEditable(true);
        durationColumn.setEditable(true);

        labelView.getColumns().setAll(titleColumn, durationColumn);

        labelView.setEditable(true);

        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        labelOption.setContent(labelView);

        labelView.getSelectionModel().selectFirst();
        fileView.getSelectionModel().selectFirst();

        /**
         * cleck skip to label duration
         */
        labelView.setRowFactory(tv -> {
            TableRow<LabelColumnItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((event.getClickCount() == 2) && (!row.isEmpty())) {
                    LabelColumnItem rowData = row.getItem();
                    mediaPlayer.seek(new Duration(rowData.getDuration()));
                }
            });
            flashViewDataToFileData();
            return row;
        });

        /**
         * cleck to switch media file
         */
        fileView.setRowFactory(tv -> {
            TableRow<FileColumnItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((event.getClickCount() == 2) && (!row.isEmpty())) {
                    label.flush();
                    flashViewDataToFileData();
                    FileColumnItem rowData = row.getItem();
                    String[] path = { rowData.getPath() };
                    MainApp.setLabel(null);
                    MainApp.setLmpdFile(null);
                    MainApp.initialPath(path);
                    tempFile();
                    initialLabel();
                    initialMedia();
                    flashFileDataToViewData();
                    initialListener();
                }
            });
            return row;
        });
    }

    /**
     * initial media player
     */
    public void initialMedia() {
        String source = getSourceFile();
        File file = new File(source);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    public void upDataState() {
        duration_view.setText(mediaPlayer.getCycleDuration().toMinutes() + "/");
    }

    /**
     * to ctrl duration
     */
    public void initialListener() {
        duration_ctrl.setOnMouseReleased((e -> {
            double newTime = duration_ctrl.valueProperty().getValue() / 100 * mediaPlayer.getTotalDuration().toMillis();
            mediaPlayer.seek(new Duration(newTime));
        }));

        duration_ctrl.setOnMouseClicked((e -> {
            double newTime = duration_ctrl.valueProperty().getValue() / 100 * mediaPlayer.getTotalDuration().toMillis();
            mediaPlayer.seek(new Duration(newTime));
        }));

        mediaPlayer.currentTimeProperty().addListener(((observable, oldValue, newValue) -> {
            duration_ctrl.setValue(newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100);
            duration_view.setText(durationToString(newValue) + "/" + durationToString(mediaPlayer.getTotalDuration()));
        }));

        mediaPlayer.setVolume(0.7);
        voice_ctrl.setValue(mediaPlayer.getVolume());

        voice_ctrl.valueProperty()
                .addListener((observable, oldValue, newValue) -> mediaPlayer.setVolume(newValue.doubleValue() / 100));
    }

    @Override
    /**
     * main initial window
     */
    public void initialize(URL url, ResourceBundle rb) {
        // this.lmpdFile = MainApp.lmpdFile
        if (MainApp.getLmpdFile() == null) {
            String[] fl = { null };
            if ((fl[0] = chooseFileDialog()) != null) {
                MainApp.initialPath(fl);
                if (MainApp.getLmpdFile() != null) {
                    tempFile();
                } else {
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        }
        initialLabel();
        initialViewData();
        // url for fxml
        initialMedia();

        flashFileDataToViewData();

        initialListener();

        label.setSourceFilePosition(MainApp.getSourcePath());

        fileDataMenbers.add(new FileColumnItem(label.getSourceName(), label.getSourceFilePosition()));
    }

    /**
     * format duration to type String
     * @param duration
     * @return
     */
    public String durationToString(Duration duration) {
        int time = (int) duration.toSeconds();
        int hour = time / 3600;
        int minute = (time - hour * 3600) / 60;
        int second = time % 60;
        return hour + ":" + minute + ":" + second;
    }

    /**
     * initial temp file
     */
    public void tempFile() {
        try (DataOutputStream fileStream = new DataOutputStream(
                new FileOutputStream(new File(System.getProperty("user.dir") + File.separator + "temp_data.data")))) {
            fileStream.writeUTF(MainApp.getLmpdFile());
            fileStream.writeUTF(MainApp.getSourcePath());
            fileStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException g) {
            g.printStackTrace();
        }
    }

    // show main window
    public void showScreen() throws Exception {
        this.stage = new Stage();
        start(this.stage);
    }

    /**
     * get media file path from temp_data.data file
     * @return media file path
     */
    public String getSourceFile() {
        try (DataInputStream fileStream = new DataInputStream(
                new FileInputStream(new File(System.getProperty("user.dir") + File.separator + "temp_data.data")))) {
            fileStream.readUTF();
            sourceFile = fileStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceFile;
    }

    @Override
    /**
     * load fxml file and css file
     */
    public void start(Stage windowStage) throws Exception {
        windowStage.setResizable(true);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main/resources/fxml/Window.fxml"));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        root.getStylesheets().add(getClass().getResource("/main/resources/styles/Styles.css").toExternalForm());
        Scene scene = new Scene(root);
        windowStage.setScene(scene);
        windowStage.setTitle("label media player");
        windowStage.show();
    }
}
