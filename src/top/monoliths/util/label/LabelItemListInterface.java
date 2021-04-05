package top.monoliths.util.label;

public interface LabelItemListInterface {
    public boolean empty();
    public int size();
    public LabelItem get(int index);
    public int indexOf(LabelItem labelItem);
    public void erase (int index);
    public void insert(int index, LabelItem labelItem);
    public void outPut();
    public void clear();
}
