package top.monoliths.util.label;

import java.io.Serializable;

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
