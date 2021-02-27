package main.java.media;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LabelColumnItem {
    private StringProperty title;
    private String time;
    private LongProperty duration;

    public LabelColumnItem(String title, String time, long duration) {
        setTitle(title);
        setTime(time);
        setDuration(duration);
    }

    public StringProperty titleProperty() {
        if (this.title == null) this.title = new SimpleStringProperty(this, "title");
        return this.title;
    }

    public LongProperty durationProperty() {
        if (this.duration == null) this.duration = new SimpleLongProperty(this, "duration");
        return this.duration;
    }

    public void setTitle(String title) {
        titleProperty().set(title);
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public void setDuration(Long duration) {
        durationProperty().set(duration);
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public String getTime() {
        return this.time;
    }
    
    public Long getDuration() {
        return durationProperty().get();
    }
}