package utils.io_utils;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CSVReader implements Closeable {
    private final String path;
    private final FileReader reader; //reads byte by byte
    private final BufferedReader bufferedReader; //reads line by line
    // key - Header value, Value - index of that key
    private String[] headers;
    private int counter = 0;

    /**
     * Constructor for files without headers;
     *
     * @param path
     * @throws FileNotFoundException
     */
    public CSVReader(String path) throws FileNotFoundException {
        this.path = path;
        this.reader = new FileReader(path);
        this.bufferedReader = new BufferedReader(reader);
    }

    /**
     * Constructor for files with headers
     *
     * @param path
     * @param withHeader
     * @throws IOException
     */
    public CSVReader(String path, boolean withHeader) throws IOException {
        this(path);
        this.headers = readToArray();
    }

    /**
     * Read a line and return as a string
     *
     * @throws FileNotFoundException
     */
    public String read() throws IOException {
        String line = bufferedReader.readLine();
        counter++;
        return line;
    }

    /**
     * Read a line and return as an array (split by ,)
     *
     * @throws IOException
     */
    public String[] readToArray() throws IOException {
        String line = read();
        if(line == null) return null;
        return line.split(",");
    }

    /**
     * Reads line and converts it to a Map<Header value, Line Value for that column>
     *
     * @return
     * @throws IOException
     */
    public Map<String, String> readToMap() throws IOException {
        String[] line = readToArray();
        if(line == null) return null;
        Map<String, String> res = new HashMap<>();

        for(int i = 0; i < line.length; i++){
            res.put(headers[i], line[i]);
        }
        return res;
    }

    public int getCounter(){
        return counter;
    }


    @Override
    public void close() throws IOException {
        reader.close();
        bufferedReader.close();
    }
}
