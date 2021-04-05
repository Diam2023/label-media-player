package top.monoliths.util.label;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

<<<<<<< HEAD
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
=======
/**
 * labelData data type
 * 
 * @author monoliths
 * @version 2.0
 * @since 1.0
 */
public class LabelData implements Externalizable {
    public String fileName;
    public String author;
    public LabelItemList labelBody;
    public String createTime;

    public LabelData() {
        labelBody = new LabelItemList();
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("file:");
        result.append(fileName);
        result.append("\r\n");
        result.append("author:");
        result.append(author);
        result.append("\r\n");
        result.append("create:");
        result.append(createTime);
        result.append("\r\n");
        result.append(labelBody.toString());
        return result.toString();
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
<<<<<<< HEAD
        out.writeUTF(getFileName());
        out.writeUTF(getCreateTime());
        out.writeUTF(getAuthor());
        out.writeObject(getLabelBody());
=======
        out.writeUTF(this.fileName);
        out.writeUTF(this.createTime);
        out.writeUTF(this.author);
        out.writeObject(this.labelBody);
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
<<<<<<< HEAD
        setFileName(in.readUTF());
        setCreateTime(in.readUTF());
        setAuthor(in.readUTF());
        LabelItemList labelItemList = (LabelItemList) in.readObject();
        setLabelBody(labelItemList);
    }

=======
        this.fileName = in.readUTF();
        this.createTime = in.readUTF();
        this.author = in.readUTF();
        this.labelBody = (LabelItemList)in.readObject();
    }
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
}
