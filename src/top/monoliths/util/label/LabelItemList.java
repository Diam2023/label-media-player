package top.monoliths.util.label;

import java.io.Serializable;

/**
 * @author monoliths
 * @see monoliths.top/label
 * @since 2.0
 * @version 2.0
 */

public class LabelItemList implements LabelItemListInterface, Serializable {

    /**
     * none
     */
    private static final long serialVersionUID = 1L;
    public LabelItem[] labelItems;
    public int size;
    public int expandLength;

    void init (int initialLength) throws ArrayIndexOutOfBoundsException {
        if (initialLength < 10 || initialLength > 100) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.labelItems = new LabelItem[initialLength];
        this.size = 0;
        this.expandLength = 10;
    }

    private void expand() {
        // expand content
        LabelItem[] center = this.labelItems.clone();
        this.labelItems = new LabelItem[this.size + this.expandLength];
        // copy elements to new content
        for (int i = 0; i < center.length; i++) {
            this.labelItems[i] = center[i];
        }
        // release
        center = null;
    }

    public LabelItemList () throws ArrayIndexOutOfBoundsException {
        int initialLength = 10;
        init(initialLength);

    }

    public LabelItemList (int initialLength) throws ArrayIndexOutOfBoundsException {
        init(initialLength);
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public LabelItem get(int index) {
        if (index > (this.size - 1) || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return labelItems[index];
    }

    @Override
    public int indexOf(LabelItem labelItem) {
        for (int i = 0; i < this.size; i++) {
            if (labelItem.duration == this.labelItems[i].duration) {
                return i;
            }
        }
		return -1;
    }

    @Override
    public void erase(int index){
        if (index > (this.size - 1) || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        LabelItem[] center = this.labelItems.clone();
        center[index].duration = -1;
        int n = 0;
        for (int i = 0; i < this.size; i++) {
            if (center[i].duration != -1) {
                labelItems[i] = center[n];
                n++;
            }
        }
        center = null;
        this.size--;
    }


    public void put(LabelItem labelItem) {
        if (this.labelItems.length < (this.size() + 1)) {
            expand();
        }
        // add
        // System.out.println(this.size());
        this.labelItems[this.size()] = labelItem;
        this.size++;
    }

    @Override
    public void insert(int index, LabelItem labelItem) {
        if (this.labelItems.length < (this.size() + 1)) {
            expand();
        }
        LabelItem[] center = this.labelItems.clone();
        labelItems[index] = labelItem;
        for (int i = (index + 1); i < this.size; i++) {
            labelItems[i] = center[i-1];
        }
        this.size++;
    }

    @Override
    public void outPut() {
        System.out.println("title-time-duration");
        // System.out.println(this.labelItems[1].time);
        for (LabelItem item : this.labelItems) {
            if (item != null) {
                System.out.printf("%s-%s-%d", item.title, item.time, item.duration);
                System.out.println("");
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        for (LabelItem item : labelItems) {
            if (item != null){
                result.append(item.title);
                result.append("-");
                result.append(item.time);
                result.append("-");
                result.append(item.duration);
                result.append("\r\n");
            }
        }
        return result.toString();
    }

    @Override
    public void clear() {
        for (int i = 0; i < labelItems.length; i++) {
            labelItems[i] = null;
        }
        this.labelItems = new LabelItem[10];
    }
    
}
