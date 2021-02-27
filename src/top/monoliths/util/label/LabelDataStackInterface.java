package top.monoliths.util.label;

public interface LabelDataStackInterface {
    public void pop();
    public void push(LabelData labelData);
    public int size();
    public LabelData top();
    public boolean empty();
}
