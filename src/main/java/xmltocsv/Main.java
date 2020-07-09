package xmltocsv;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {

    public static void main(String[] args) {

        //hard coded file names -- uncomment for local testing
        //String inputXml = "../big2.xml";
        //String outputCSV = "../output2.csv";


        //pass from command line
        String inputXml = args[0];
        String outputCSV = args[1];

        long start = System.currentTimeMillis();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try (RecordWriter<Book> w = new RecordWriter<>(outputCSV)) {
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputXml, new SaxParseEventHandler(w));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Finished in " + (System.currentTimeMillis() - start) + " ms");
    }
}
