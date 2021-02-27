package top.monoliths.util.label;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

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
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.fileName);
        out.writeUTF(this.createTime);
        out.writeUTF(this.author);
        out.writeObject(this.labelBody);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.fileName = in.readUTF();
        this.createTime = in.readUTF();
        this.author = in.readUTF();
        this.labelBody = (LabelItemList)in.readObject();
    }
}
