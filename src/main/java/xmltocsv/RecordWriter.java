package xmltocsv;

import java.io.FileWriter;
import java.io.IOException;

public class RecordWriter<T> implements AutoCloseable {

    final String fileName;
    final FileWriter fw;

    public RecordWriter(String fileName) throws IOException {
        this.fileName = fileName;
        this.fw = new FileWriter(fileName);
    }

    public void write(T t, int n) throws IOException {
        fw.write(t.toString());

        if (n % 10000 == 0) {
            fw.flush();
        }
    }

    @Override
    public void close() throws Exception {
        fw.flush();
        fw.close();
    }
}
