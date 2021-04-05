package main.java.media;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
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

public class WindowFXMLController extends Application implements Initializable {
    private Stage stage;

    private ObservableList<FileColumnItem> fileDataMenbers;
    private ObservableList<LabelColumnItem> labelDataMembers;

    private TableView<FileColumnItem> fileView;
    private TableView<LabelColumnItem> labelView;

    private top.monoliths.util.label.Label label;

    // private String lmpdFile
    private String sourceFile;

    private boolean isStoped = false;

    private MediaPlayer mediaPlayer;

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
    private Tab labelOption;

    @FXML
    private Tab fileOption;

    @FXML
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
            }
        } else {
            if ((file = chooseFileDialog()) != null && MainApp.initialFile(file)) {
                fileDataMenbers.add(new FileColumnItem(MainApp.getSourceFile(), MainApp.getSourcePath()));
            }
        }
        flashViewDataToFileData();
    }

    @FXML
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

    @SuppressWarnings("all")
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

    public String durationToString(Duration duration) {
        int time = (int) duration.toSeconds();
        int hour = time / 3600;
        int minute = (time - hour * 3600) / 60;
        int second = time % 60;
        return hour + ":" + minute + ":" + second;
    }

    public void tempFile() {
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
        }
    }

    // show main window
    public void showScreen() throws Exception {
        this.stage = new Stage();
        start(this.stage);
    }

    public String getSourceFile() {
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
    }

    @Override
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
