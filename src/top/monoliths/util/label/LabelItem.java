package top.monoliths.util.label;

import java.io.Serializable;
<<<<<<< HEAD
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@LabelItemAnnotation
/**
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

    private String title;
    private Long duration;
    private String time;

    public LabelItem() {
    }

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
=======

/**
 * @author monoliths
 * @version 2.0
 */

public class LabelItem implements Serializable {
    /**
     * @author monoliths
     * @since 1.0
     * @version 2.0
     */
    private static final long serialVersionUID = 1L;

    public LabelItem() {
        this.title = null;
        this.time = null;
        this.duration = -1;
    }

    /**
     * initial data
     * 
     * @param title:    label title
     * @param time:     label time
     * @param duration: duration
     */
    public LabelItem(String title, String time, long duration) {
        this.title = title;
        this.time = time;
        this.duration = duration;
    }

    // label title
    public String title;
    // add time
    public String time;
    // target time
    public long duration;
}
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
