package main.java.media;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FileColumnItem {
    private StringProperty name;
    private StringProperty path;

    public FileColumnItem(String name, String path) {
        setName(name);
        setPath(path);
    }

    public StringProperty nameProperty() {
        if (this.name == null) this.name = new SimpleStringProperty(this, "name");
        return this.name;
    }

    public StringProperty pathProperty() {
        if (this.path == null) this.path = new SimpleStringProperty(this, "path");
        return this.path;
    }

    public void setName(String name) {
        nameProperty().set(name);
    }

    public void setPath(String path) {
        pathProperty().set(path);
    }

    public String getName() {
        return nameProperty().get();
    }

    public String getPath() {
        return pathProperty().get();
    }
}