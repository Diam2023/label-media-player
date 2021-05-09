package top.monoliths.util.label;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@LabelItemAnnotation
/**
 * LabelItem
 * 
 * @author monoliths
 * @since 1.0
 * @version 1.0
 */
public final class LabelItem implements Comparable<LabelItem>, Serializable {
    /**
     * this is a label to save duration and title
     * 
     * @since 1.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * label's name
     */
    private String title;

    /**
     * label's duration
     */
    private Long duration;

    /**
     * label's create date
     */
    private String time;

    /**
     * null construction
     * keep to can be serializable
     */
    public LabelItem() {
    }

    /**
     * initial Label Item
     * @param title
     * @param duration
     * @param time
     */
    public LabelItem(String title, Long duration, String time) {
        setTitle(title);
        setDuration(duration);
        setTime(time);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getDuration() {
        return duration;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    @Override
    /**
     * use Duration to compare
     */
    public int compareTo(LabelItem o) {
        if (o.getDuration().equals(getDuration())) {
            return 0;
        }
        return (o.getDuration() > getDuration()) ? 1 : -1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return getTitle() + "-" + getTime() + "-" + getDuration();
    }
}

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface LabelItemAnnotation {

}