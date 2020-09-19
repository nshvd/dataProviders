package utils.io_utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private String path;
    private FileWriter writer;
    private BufferedWriter bufferedWriter;

    public CSVWriter(String path) throws IOException {
        this.path = path;
        writer = new FileWriter(path);
        bufferedWriter = new BufferedWriter(writer);
    }

    public void write(String line) throws IOException {
        bufferedWriter.write(line + "\n");
    }

    public void flush() throws IOException {
        bufferedWriter.flush();
    }

    public void close() throws IOException {
        if(bufferedWriter != null) bufferedWriter.close();
        if(writer != null) writer.close();
    }
}
