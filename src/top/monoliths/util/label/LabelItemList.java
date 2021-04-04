package top.monoliths.util.label;

import java.io.Serializable;
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
    }

    @Override
    public void clear() {
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