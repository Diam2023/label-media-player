package top.monoliths.util.label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    }

    /**
     * load lmpd file to RAM
     * 
     * @param fileName: lmpd fileFluter
     */
    public Label(String fileName) {
        this.fileName = fileName;
        readDataFromFile();
    }

    /**
     * create a new label media data file label media data file last name : .lmpd
     * 
     * @param fileName:           lmpd fileFluter
     * @param author
     * @param sourceFilePosition: media file
     */
    public Label(String lmpdFileName, String author, String sourceFilePosition) {
        this.fileName = lmpdFileName;
        initialLabelData();
        this.labelData.author = author;
        this.labelData.fileName = sourceFilePosition;
        this.labelData.createTime = nowTime();
        initialFile();
    }

    /**
     * clear file
     */
    private void initialFile() {
        File file = new File(this.fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * from file read data
     */
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
            e.printStackTrace();
        }
    }

    /**
     * write data to file
     */
    public void update() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(this.fileName)));
            this.labelData.writeExternal(oos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
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
    
}
