package main.java.media;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
<<<<<<< HEAD
import java.io.FileNotFoundException;
=======
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
<<<<<<< HEAD
=======
import java.util.Iterator;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
<<<<<<< HEAD
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
=======
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
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
<<<<<<< HEAD
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import top.monoliths.util.label.LabelItem;
import top.monoliths.util.label.LabelItemList;
=======
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import top.monoliths.util.label.LabelItem;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469

public class WindowFXMLController extends Application implements Initializable {
    private Stage stage;

    private ObservableList<FileColumnItem> fileDataMenbers;
    private ObservableList<LabelColumnItem> labelDataMembers;

<<<<<<< HEAD
=======
    private List<FileColumnItem> initialFileData;
    private List<LabelColumnItem> initialLabelData;

>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    private TableView<FileColumnItem> fileView;
    private TableView<LabelColumnItem> labelView;

    private top.monoliths.util.label.Label label;

<<<<<<< HEAD
    // private String lmpdFile
=======
    // private String lmpdFile;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    private String sourceFile;

    private boolean isStoped = false;

    private MediaPlayer mediaPlayer;

<<<<<<< HEAD
=======
    private Double minScreenWidth = 919.0;
    private Double minScreenHeight = 555.0;

>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    @FXML
    private Button add_action;

    @FXML
    private Label file_name;

    @FXML
    private Label duration_view;

    @FXML
    private Tab label_view;

    @FXML
    private Tab file_view;

    @FXML
<<<<<<< HEAD
=======
    private MediaView player_view;

    @FXML
    private Pane front;

    @FXML
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    private Tab labelOption;

    @FXML
    private Tab fileOption;

    @FXML
<<<<<<< HEAD
=======
    private HBox media_pane;

    @FXML
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    private Button full_screen;

    @FXML
    private Slider voice_ctrl;

    @FXML
    private Slider duration_ctrl;

    // action
    @FXML
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
                flashViewDataToFileData();
            }
        } else {
<<<<<<< HEAD
            if ((file = chooseFileDialog()) != null && MainApp.initialFile(file)) {
                fileDataMenbers.add(new FileColumnItem(MainApp.getSourceFile(), MainApp.getSourcePath()));
=======
            // fileDataMenbers.add(new FileColumnItem("fileName", "filePath"));
            if ((file = chooseFileDialog()) != null) {
                if (MainApp.initialFile(file)) {
                    // System.out.println(MainApp.sourceFileName);
                    // System.out.println(MainApp.sourcePath);
                    fileDataMenbers.add(new FileColumnItem(MainApp.sourceFileName, MainApp.sourcePath));
                }
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
            }
        }
    }

    @FXML
<<<<<<< HEAD
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
=======
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    private void removeAction(ActionEvent event) {
        if (labelOption.isSelected()) {
            // target
            if (!this.labelDataMembers.isEmpty()) {
                this.labelDataMembers.remove(labelView.getSelectionModel().getFocusedIndex());
                flashViewDataToFileData();
            }
        } else {
            if (!this.fileDataMenbers.isEmpty()) {
                this.fileDataMenbers.remove(fileView.getSelectionModel().getFocusedIndex());
            }
        }
    }

<<<<<<< HEAD
    public void flashFileDataToViewData() {
        this.labelDataMembers.clear();
        LabelItemList labelBody = this.label.getLabelBody();
        for (int i = 0; i < labelBody.size(); i++) {
            LabelItem now = labelBody.getIndex(i);
            if (now != null) {
                this.labelDataMembers.add(new LabelColumnItem(now.getTitle(), now.getTime(), now.getDuration()));
=======
    // media action
    @FXML
    private void mediaPlayerStateChange(MouseEvent event) throws Exception {
        if (this.isStoped) {
            mediaPlayer.pause();
            this.isStoped = false;
        } else {
            mediaPlayer.play();
            this.isStoped = true;
        }
    }

    public void flashFileDataToViewData() {
        this.labelDataMembers.clear();
        // System.out.println("flash f-v");
        // System.out.println(this.label.labelData.labelBody);
        for (int i = 0; i < this.label.labelData.labelBody.size(); i++) {
            LabelItem now = this.label.labelData.labelBody.get(i);
            if (now != null) {
                this.labelDataMembers.add(new LabelColumnItem(now.title, now.time, now.duration));
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
            }
        }
    }

    public void flashViewDataToFileData() {
<<<<<<< HEAD
        LabelItemList labelBody = this.label.getLabelBody();
        labelBody.clear();
        for (int i = 0; i < this.labelDataMembers.size(); i++) {
            labelBody.add(new LabelItem(this.labelDataMembers.get(i).getTitle(),
                    this.labelDataMembers.get(i).getDuration(), this.labelDataMembers.get(i).getTime()));
        }
        this.label.setLabelBody(labelBody);
        this.label.flush();
=======
        // System.out.println("flash v-f");
        this.label.labelData.labelBody.clear();
        for (int i = 0; i < this.labelDataMembers.size(); i++) {
            this.label.write(new LabelItem(this.labelDataMembers.get(i).getTitle(),
                    this.labelDataMembers.get(i).getTime(), this.labelDataMembers.get(i).getDuration()));
        }
        this.label.update();
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    // dialog action
    public String addLabelDialog() {
        TextInputDialog tid = new TextInputDialog();
        tid.setTitle("label");
        tid.setHeaderText("label name");
        tid.setContentText("label name:");
        Optional<String> result = tid.showAndWait();
        return result.isPresent() ? result.get() : null;
    }

    public String chooseFileDialog() {
<<<<<<< HEAD
        // Stage stage = new Stage()
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open file with label media");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("all filee", "*.*"),
                new ExtensionFilter("media file", "*.mp3", "*.lmpd"));
=======
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open file with label media");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("media file", "*.mp3", "*.wav", "*.mp4", "*.mov"),
             new ExtensionFilter("all filee", "*.*"));
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        File file = fileChooser.showOpenDialog(stage);
        return ((file != null) && file.exists() && file.isFile()) ? file.getAbsolutePath() : null;
    }

    // initital

    public void initialLabel() {
<<<<<<< HEAD
        // System.out.println(MainApp.lmpdFile)
        label = new top.monoliths.util.label.Label(MainApp.getLmpdFile());
    }

    @SuppressWarnings("all")
    public void initialViewData() {
        List<FileColumnItem> initialFileData;
        List<LabelColumnItem> initialLabelData;
        initialFileData = List.of(new FileColumnItem("name", "path"));
        fileDataMenbers = FXCollections.observableArrayList(initialFileData);
        fileView = new TableView<>();
        fileView.setItems(fileDataMenbers);
        TableColumn<FileColumnItem, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(initialFileData.get(0).nameProperty().getName()));
        TableColumn<FileColumnItem, String> pathColumn = new TableColumn<>("path");
        pathColumn.setCellValueFactory(new PropertyValueFactory<>(initialFileData.get(0).pathProperty().getName()));
=======
        // System.out.println(MainApp.lmpdFile);
        label = new top.monoliths.util.label.Label(MainApp.lmpdFile);
    }

    public void initialViewData() {
        this.initialFileData = List.of(new FileColumnItem("name", "path"));
        this.fileDataMenbers = FXCollections.observableArrayList(this.initialFileData);
        fileView = new TableView<>();
        fileView.setItems(fileDataMenbers);
        TableColumn<FileColumnItem, String> nameColumn = new TableColumn<>("name");
        nameColumn
                .setCellValueFactory(new PropertyValueFactory<>(this.initialFileData.get(0).nameProperty().getName()));
        TableColumn<FileColumnItem, String> pathColumn = new TableColumn<>("path");
        pathColumn
                .setCellValueFactory(new PropertyValueFactory<>(this.initialFileData.get(0).pathProperty().getName()));
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        this.fileDataMenbers.remove(0);

        fileView.getColumns().setAll(nameColumn, pathColumn);

        fileOption.setContent(fileView);

<<<<<<< HEAD
        initialLabelData = List.of(new LabelColumnItem("title", "time", -1));
        this.labelDataMembers = FXCollections.observableArrayList(initialLabelData);
        labelView = new TableView<>();
        labelView.setItems(labelDataMembers);
        TableColumn<LabelColumnItem, String> titleColumn = new TableColumn<>("title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>(initialLabelData.get(0).titleProperty().getName()));
        TableColumn<LabelColumnItem, Long> durationColumn = new TableColumn<>("duration");
        durationColumn
                .setCellValueFactory(new PropertyValueFactory<>(initialLabelData.get(0).durationProperty().getName()));
=======
        this.initialLabelData = List.of(new LabelColumnItem("title", "time", -1));
        this.labelDataMembers = FXCollections.observableArrayList(this.initialLabelData);
        labelView = new TableView<>();
        labelView.setItems(labelDataMembers);
        TableColumn<LabelColumnItem, String> titleColumn = new TableColumn<>("title");
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>(this.initialLabelData.get(0).titleProperty().getName()));
        TableColumn<LabelColumnItem, Long> durationColumn = new TableColumn<>("duration");
        durationColumn.setCellValueFactory(
                new PropertyValueFactory<>(this.initialLabelData.get(0).durationProperty().getName()));
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        this.labelDataMembers.remove(0);

        titleColumn.setEditable(true);
        durationColumn.setEditable(true);

        labelView.getColumns().setAll(titleColumn, durationColumn);

        labelView.setEditable(true);

        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        labelOption.setContent(labelView);

        labelView.getSelectionModel().selectFirst();
        fileView.getSelectionModel().selectFirst();

        labelView.setRowFactory(tv -> {
            TableRow<LabelColumnItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((event.getClickCount() == 2) && (!row.isEmpty())) {
                    LabelColumnItem rowData = row.getItem();
                    mediaPlayer.seek(new Duration(rowData.getDuration()));
                }
            });
            return row;
        });

<<<<<<< HEAD
=======
        // labelView.setRowFactory(tv -> {
        //     TableRow<LabelColumnItem> row = new TableRow<>();
        //     row.setOnInputMethodTextChanged (event -> {
        //         flashViewDataToFileData();
        //     });
        //     return row;
        // });

>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        fileView.setRowFactory(tv -> {
            TableRow<FileColumnItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((event.getClickCount() == 2) && (!row.isEmpty())) {
                    FileColumnItem rowData = row.getItem();
                    String[] path = { rowData.getPath() };
<<<<<<< HEAD
                    MainApp.setLabel(null);
                    MainApp.setLmpdFile(null);
=======
                    MainApp.label = null;
                    MainApp.lmpdFile = null;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
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

    public void initialMedia() {
        String source = getSourceFile();
<<<<<<< HEAD
=======
        System.out.println("source :" + source);
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        File file = new File(source);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
<<<<<<< HEAD
=======
        player_view.setMediaPlayer(mediaPlayer);
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    public void upDataState() {
        duration_view.setText(mediaPlayer.getCycleDuration().toMinutes() + "/");
    }

    public void initialListener() {
<<<<<<< HEAD
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
=======
        duration_ctrl.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                double newTime = duration_ctrl.valueProperty().getValue() / 100 * mediaPlayer.getTotalDuration().toMillis();
                mediaPlayer.seek(new Duration(newTime));
            }
        });
        duration_ctrl.setOnMouseClicked((e -> {
            double newTime = duration_ctrl.valueProperty().getValue() / 100 * mediaPlayer.getTotalDuration().toMillis();
                mediaPlayer.seek(new Duration(newTime));
        }));

        // duration_ctrl.
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                duration_ctrl.setValue(newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100);
                duration_view.setText(durationToString(newValue) + "/" + durationToString(mediaPlayer.getTotalDuration()));
            }
        });
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469

        mediaPlayer.setVolume(0.7);
        voice_ctrl.setValue(mediaPlayer.getVolume());

<<<<<<< HEAD
        voice_ctrl.valueProperty()
                .addListener((observable, oldValue, newValue) -> mediaPlayer.setVolume(newValue.doubleValue() / 100));
=======
        voice_ctrl.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(newValue.doubleValue()/100);
            }
        });        
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
<<<<<<< HEAD
        // this.lmpdFile = MainApp.lmpdFile
        if (MainApp.getLmpdFile() == null) {
            String[] fl = { null };
            if ((fl[0] = chooseFileDialog()) != null) {
                MainApp.initialPath(fl);
                if (MainApp.getLmpdFile() != null) {
=======
        // this.lmpdFile = MainApp.lmpdFile;
        if (MainApp.lmpdFile == null) {
            String[] fl = {null};
            if ((fl[0] = chooseFileDialog()) != null) {
                MainApp.initialPath(fl);
                if (MainApp.lmpdFile != null) {
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
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

<<<<<<< HEAD
        label.setSourceFilePosition(MainApp.getSourcePath());

        fileDataMenbers.add(new FileColumnItem(label.getSourceName(), label.getSourceFilePosition()));
    }

    public String durationToString(Duration duration) {
        int time = (int) duration.toSeconds();
        int hour = time / 3600;
        int minute = (time - hour * 3600) / 60;
        int second = time % 60;
=======
        fileDataMenbers.add(new FileColumnItem(MainApp.sourceFileName, MainApp.sourcePath));

        mediaPlayer.setOnReady(new Runnable() {
            public void run() {
                player_view.setFitWidth(minScreenWidth);
                player_view.setFitHeight(minScreenHeight);
            }
        });
    }

    public String durationToString(Duration duration){
        int time = (int)duration.toSeconds();
        int hour = time /3600;
        int minute = (time-hour*3600)/60;
        int second = time %60;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        return hour + ":" + minute + ":" + second;
    }

    public void tempFile() {
<<<<<<< HEAD
        try (DataOutputStream fileStream = new DataOutputStream(new FileOutputStream(
                new File(getClass().getResource("/main/resources").toURI().getPath() + "/temp_data.data")))) {
            fileStream.writeUTF(MainApp.getLmpdFile());
            fileStream.writeUTF(MainApp.getSourcePath());
            fileStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException f) {
            f.printStackTrace();
        } catch (IOException g) {
            g.printStackTrace();
=======
        try {
            File file = new File(getClass().getResource("/main/resources").toURI().getPath() + "/temp_data.data");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            DataOutputStream fileStream = new DataOutputStream(new FileOutputStream(file));
            fileStream.writeUTF(MainApp.lmpdFile);
            fileStream.writeUTF(MainApp.sourcePath);
            fileStream.close();
            // System.out.println("wfxtempFile:" + lmpdFile);
        } catch (Exception e) {
            e.printStackTrace();
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        }
    }

    // show main window
<<<<<<< HEAD
    public void showScreen() throws Exception {
=======
    public void showScreen() throws Exception{
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        this.stage = new Stage();
        start(this.stage);
    }

    public String getSourceFile() {
<<<<<<< HEAD
        try (DataInputStream fileStream = new DataInputStream(new FileInputStream(
                new File(getClass().getResource("/main/resources").toURI().getPath() + "/temp_data.data")))) {
            fileStream.readUTF();
            sourceFile = fileStream.readUTF();
        } catch (URISyntaxException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceFile;
=======
        try {
            File file = null;
            try {
                file = new File(getClass().getResource("/main/resources").toURI().getPath() + "/temp_data.data");
                // System.out.println(file.toPath());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            DataInputStream fileStream = new DataInputStream(new FileInputStream(file));
            // this.lmpdFile = fileStream.readUTF();
            fileStream.readUTF();
            this.sourceFile = fileStream.readUTF();
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.sourceFile;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    @Override
    public void start(Stage windowStage) throws Exception {
<<<<<<< HEAD
        windowStage.setResizable(true);
=======
        windowStage.setResizable(false);
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
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
