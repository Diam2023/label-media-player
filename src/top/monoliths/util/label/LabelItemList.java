package top.monoliths.util.label;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * a list of LabelItem
 * 
 * @author monoliths
 * @version 1.0
 */
public class LabelItemList implements Collection<LabelItem>, Serializable {

    /**
     * @since 1.0
     */
    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private LabelItem[] labelItems;

    public LabelItemList() {
        initital();
    }

    public void initital() {
        labelItems = new LabelItem[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void ensureCapacity(int capacity) {
        if (capacity >= size() && labelItems != null) {
            LabelItem[] old = labelItems;
            labelItems = new LabelItem[capacity];
            int i = 0;
            for (LabelItem labelItem : old) {
                labelItems[i++] = labelItem;
            }
        }

    }

    public LabelItem getIndex(int index) {
        return labelItems[index];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * check is empty
     */
    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * if labelItems exist o return true
     * 
     * @param o: object
     * @return boolean
     */
    @Override
    public boolean contains(Object o) {
        LabelItem element = (LabelItem) o;
        for (LabelItem labelItem : labelItems) {
            if (labelItem.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get iterator instance
     */
    @Override
    public Iterator<LabelItem> iterator() {
        return new Iterator<LabelItem>() {

            private int current = 0;

            @Override
            public boolean hasNext() {
                return (current < size());
            }

            @Override
            /**
             * @return LabelItem
             */
            public LabelItem next() {
                // check has next label
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                // return next label
                return labelItems[current++];
            }

        };
    }

    @Override
    public Object[] toArray() {
        return labelItems;
    }

    @Override
    @SuppressWarnings("all")
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean add(LabelItem e) {
        // check size
        if ((size() + 1) > labelItems.length) {
            ensureCapacity(labelItems.length + DEFAULT_CAPACITY);
        }
        // if has null can not be add
        if (e.getTitle().equals("") || e.getTime() == null || e.getDuration() == null) {
            return false;
        }
        // add
        labelItems[size()] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if ((size() - 1) < (labelItems.length - 10)) {
            ensureCapacity(labelItems.length - DEFAULT_CAPACITY);
        }
        LabelItem element = (LabelItem) o;
        int i = 0;
        for (LabelItem labelItem : labelItems) {
            if (labelItem.compareTo(element) == 0) {
                for (int j = i; j < labelItems.length - 1; j++) {
                    labelItems[j] = labelItems[j + 1];
                }
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean remove(int index) {
        if (index > size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        remove(labelItems[index]);
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> elements = c.iterator();
        while (elements.hasNext()) {
            if (!contains(elements.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends LabelItem> c) {
        // if has a false return
        for (LabelItem labelItem : c) {
            if (!add(labelItem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<?> elements = c.iterator();
        while (elements.hasNext()) {
            if (!remove(elements.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<?> elements = c.iterator();
        while (elements.hasNext()) {
            LabelItem element = (LabelItem) elements.next();
            if (!contains(element)) {
                remove(element);
            }
            return true;
        }
        return false;
=======

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
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    @Override
    public void clear() {
<<<<<<< HEAD
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        StringBuffer result = new StringBuffer();
        if (isEmpty()) {
            result.append("null");
        } else {
            for (LabelItem item : labelItems) {
                if (item != null) {
                    result.append(item.toString());
                    result.append("\r\n");
                }
            }
        }
        return result.toString();
    }
}
=======
        for (int i = 0; i < labelItems.length; i++) {
            labelItems[i] = null;
        }
        this.labelItems = new LabelItem[10];
    }
    
}
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
