package top.monoliths.util.label;
/**
 * @author monoliths
 * @since 2.0
 * @version 2.0
 */
public abstract class AbstractLabel {
    /**
     * read data
     * @return LabelData
     */
    abstract LabelData read();

    /**
     * choose return type
     * @param type
     * @return input type
     */
    abstract Object read(LabelDataRequestTypeEnum type);

    /**
     * write data to labelData
     * @param labelItem
     */
    abstract void write(LabelItem labelItem);

    /**
     * remove element
     * @param labelItem
     * @throws IllegalAccessException
     */
    abstract void erase(LabelItem labelItem) throws IllegalAccessException;

    /**
     * use index to remove
     * @param index
     * @throws IllegalAccessException
     */
    abstract void erase(int index) throws IllegalAccessException;
    
    /**
     * save or back action
     * next state
     */
    abstract void next() throws IllegalAccessException;
    /**
     * back state
     * @throws IllegalAccessException
     */
    abstract void back() throws IllegalAccessException;
}
