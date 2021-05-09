package main.java.media;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * file view data structure
 * 
 * @author monoliths
 * @version 1.0
 */
public class FileColumnItem {
    /**
     * file's name
     */
    private StringProperty name;
    /**
     * file's origin path
     */
    private StringProperty path;

    /**
     * to initial File view data
     * @param name file's name
     * @param path file's path
     */
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