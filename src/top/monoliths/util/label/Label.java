package top.monoliths.util.label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
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
    }

    /**
     * load lmpd file to RAM
     * 
     * @param fileName: lmpd fileFluter
     */
    public Label(String lmpdFilePosotion) {
        setLmpdFilePosition(lmpdFilePosotion);
        File lmpdFile = new File(lmpdFilePosition);
        setFileName(lmpdFile.getName());
        readDataFromFile();
    }

    /**
     * create a new label media data file label media data file last name : .lmpd
     * 
     * @param fileName:           lmpd fileFluter
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
        initialFile();
    }

    /**
     * clear file
     */
    private void initialFile() {
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
        }
    }

    /**
     * from file read data
     */
    @Override
    public void readDataFromFile() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(getFileName())));
            readExternal(ois);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write data to file
     */
    @Override
    public void flush() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(getFileName())));
            writeExternal(oos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
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

}
