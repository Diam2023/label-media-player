package top.monoliths.util.label;
<<<<<<< HEAD

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
=======
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * label class file ctrl
 * 
 * @author monoliths
 * @since 2.0
 * @version 2.0
 */
<<<<<<< HEAD
public final class Label extends LabelData implements InterfaceLabel {
    private String sourceName;

    private String lmpdFilePosition;
    private String sourceFilePosition;

    public String getLmpdFilePosition() {
        return lmpdFilePosition;
    }

    public void setLmpdFilePosition(String lmpdFilePosition) {
        this.lmpdFilePosition = lmpdFilePosition;
    }

    public String getSourceFilePosition() {
        return sourceFilePosition;
    }

    public void setSourceFilePosition(String sourceFilePosition) {
        this.sourceFilePosition = sourceFilePosition;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }

    /**
     * format time to string
     * 
     * @return: String time
     */
    public static String nowTime() {
        return new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
    }

    public Label() {
=======
public class Label extends AbstractLabel {
    public LabelData labelData;
    private LabelDataStack backLabelData;
    private LabelDataStack frontLabelData;

    public String fileName;

    public void setSourceFileName(String fileName) {
        labelData.fileName = fileName;
    }

    public static String nowTime() {
        return (String) new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
    }

    private void initialLabelData() {
        labelData = new LabelData();
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
    }

    /**
     * load lmpd file to RAM
     * 
     * @param fileName: lmpd fileFluter
     */
<<<<<<< HEAD
    public Label(String lmpdFilePosotion) {
        setLmpdFilePosition(lmpdFilePosotion);
        File lmpdFile = new File(lmpdFilePosition);
        setFileName(lmpdFile.getName());
=======
    public Label(String fileName) {
        this.fileName = fileName;
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        readDataFromFile();
    }

    /**
     * create a new label media data file label media data file last name : .lmpd
     * 
     * @param fileName:           lmpd fileFluter
<<<<<<< HEAD
     * @param author:             monoliths
     * @param sourceFilePosition: media file
     */
    public Label(String lmpdFilePosition, String author, String sourceFilePosition) {
        setLmpdFilePosition(lmpdFilePosition);
        setSourceFilePosition(sourceFilePosition);
        File lmpdFile = new File(lmpdFilePosition);
        File sourceFile = new File(sourceFilePosition);
        setFileName(lmpdFile.getName());
        setSourceName(sourceFile.getName());
        setAuthor(author);
        setCreateTime(nowTime());
        setLabelBody(new LabelItemList());
=======
     * @param author
     * @param sourceFilePosition: media file
     */
    public Label(String lmpdFileName, String author, String sourceFilePosition) {
        this.fileName = lmpdFileName;
        initialLabelData();
        this.labelData.author = author;
        this.labelData.fileName = sourceFilePosition;
        this.labelData.createTime = nowTime();
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        initialFile();
    }

    /**
     * clear file
     */
    private void initialFile() {
<<<<<<< HEAD
        File file = new File(getLmpdFilePosition());
        if (file.exists()) {
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("");
                fw.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException f) {
                f.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
=======
        File file = new File(this.fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        }
    }

    /**
     * from file read data
     */
<<<<<<< HEAD
    @Override
    public void readDataFromFile() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(getLmpdFilePosition())));
            readExternal(ois);
        } catch (ClassNotFoundException | IOException e) {
=======
    private void readDataFromFile() {
        ObjectInputStream ois;
        try {
            this.labelData = new LabelData();
            ois = new ObjectInputStream(new FileInputStream(new File(this.fileName)));
            try {
                this.labelData.readExternal(ois);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
            e.printStackTrace();
        }
    }

    /**
     * write data to file
     */
<<<<<<< HEAD
    @Override
    public void flush() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(getLmpdFilePosition())));
            writeExternal(oos);
=======
    public void update() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(this.fileName)));
            this.labelData.writeExternal(oos);
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
<<<<<<< HEAD
    @SuppressWarnings("all")
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("source file path:");
        result.append(getSourceName());
        result.append("\r\n");
        result.append("lmpd file path:");
        result.append(getFileName());
        result.append("\r\n");
        result.append("lmpd file author:");
        result.append(getAuthor());
        result.append("\r\n");
        result.append("lmpd file create time:");
        result.append(getCreateTime());
        result.append("\r\n");
        result.append("label body:");
        result.append("\r\n");
        if (getLabelBody() == null) {
            result.append("null");
        } else {
            result.append(getLabelBody().toString());
        }
        return result.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(getSourceName());
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setSourceName(in.readUTF());
        super.readExternal(in);
    }

=======
    public LabelData read() {
        return this.labelData;
    }

    @Override
    public Object read(LabelDataRequestTypeEnum type) {
        LabelData data = this.labelData;
        Object result = null;
        switch (type) {
            case LABEL_DATA:
                result = data;
                break;
            case OBJECT:
                result = (Object)data;
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public void write(LabelItem labelItem) {
        this.labelData.labelBody.put(labelItem);
    }

    @Override
    public void erase(LabelItem labelItem) throws IllegalAccessException {
        this.erase(this.labelData.labelBody.indexOf(labelItem));
    }

    @Override
    public void erase(int index) throws IllegalAccessException {
        labelData.labelBody.erase(index);
    }

    @Override
    public void next() throws IllegalAccessException {
        if (frontLabelData.empty()) {
            throw new IllegalAccessException("front none!");
        }
        this.backLabelData.push(this.labelData);
        this.labelData = this.frontLabelData.top();
        this.frontLabelData.pop();
    }

    @Override
    public void back() throws IllegalAccessException {
        if (backLabelData.empty()) {
            throw new IllegalAccessException("back none!");
        }
        this.frontLabelData.push(this.labelData);
        this.labelData = this.backLabelData.top();
        this.backLabelData.pop();
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("file path:");
        result.append(this.fileName);
        result.append("\r\n");
        result.append(this.labelData.toString());
        return result.toString();
    }
    
>>>>>>> 8a15f70e14eb00c96d4ca458c709655fee29e469
}
