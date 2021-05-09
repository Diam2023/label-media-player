package top.monoliths.util.label;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * LabelData
 * 
 * @author monoliths
 * @version 1.0
 */
public class LabelData implements Externalizable {
    private String fileName;
    private String author;
    private LabelItemList labelBody;
    private String createTime;

    public LabelData() {

    }

    public LabelData(String fileName, String author, LabelItemList labelBody, String createTime) {
        setAuthor(author);
        setCreateTime(createTime);
        setFileName(fileName);
        setLabelBody(labelBody);
    }

    public LabelData(String fileName, String author, String nowTime) {
        setAuthor(author);
        setCreateTime(nowTime);
        setFileName(fileName);
        setLabelBody(new LabelItemList());
    }

    public String getAuthor() {
        return author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getFileName() {
        return fileName;
    }

    public LabelItemList getLabelBody() {
        return labelBody;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setLabelBody(LabelItemList labelBody) {
        if (labelBody != null) {
            this.labelBody = labelBody;
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getFileName());
        out.writeUTF(getCreateTime());
        out.writeUTF(getAuthor());
        out.writeObject(getLabelBody());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setFileName(in.readUTF());
        setCreateTime(in.readUTF());
        setAuthor(in.readUTF());
        LabelItemList labelItemList = (LabelItemList) in.readObject();
        setLabelBody(labelItemList);
    }

}
