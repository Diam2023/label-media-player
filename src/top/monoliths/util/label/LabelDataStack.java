package top.monoliths.util.label;

public class LabelDataStack implements LabelDataStackInterface {

    LabelData[] labelData;
    int size;
    int expandLength;
    
    void init (int initialLength) throws IllegalAccessException {
        if (initialLength < 10 || initialLength > 100) {
            throw new IllegalAccessException("bad length");
        }
        this.labelData = new LabelData[initialLength];
        this.size = 0;
        this.expandLength = 10;
    }

    private void expand() {
        // expand content
        LabelData[] center = this.labelData.clone();
        this.labelData = new LabelData[this.size + this.expandLength];
        // copy elements to new content
        for (int i = 0; i < center.length; i++) {
            this.labelData[i] = center[i];
        }
        // release
        center = null;
    }

    public LabelDataStack () throws IllegalAccessException {
        int initialLength = 10;
        init(initialLength);

    }

    public LabelDataStack (int initialLength) throws IllegalAccessException {
        init(initialLength);
    }

    @Override
    public void pop() {
        this.labelData[this.size - 1].labelBody = null;
        this.size--;
    }

    @Override
    public void push(LabelData labelData) {
        if (this.labelData.length < (this.size + 1)) {
            expand();
        }
        this.labelData[this.size] = labelData;
        this.size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public LabelData top() {
        return this.labelData[this.size - 1];
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        System.out.println(this.size);
        StringBuffer result = new StringBuffer("stack\r\n");
        for (int i = (this.size - 1); i > -1; i--) {
            if (this.labelData[i] != null) {
                result.append(this.labelData[i].fileName);
                result.append("-");
                result.append(this.labelData[i].author);
                result.append("-");
                result.append(this.labelData[i].createTime);
                result.append("\r\n");
                result.append(this.labelData[i].labelBody.toString());
            }
        }
        return result.toString();
    }
    
}
