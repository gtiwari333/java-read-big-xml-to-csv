package xmltocsv;

import java.io.IOException;
import java.time.LocalDate;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParseEventHandler extends DefaultHandler {

    final RecordWriter<Book> writer;
    int counter;

    //temporary fields
    String elValue;
    Book bookTmp;

    public SaxParseEventHandler(RecordWriter<Book> writer) {
        this.writer = writer;
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) {

        /*
        handle start of a new Book tag and attributes of an element
         */

        if (elementName.equalsIgnoreCase("book")) {  //start
            bookTmp = new Book();
            counter++;

            //read attributes of book
            bookTmp.id = attributes.getValue("id");
            bookTmp.lang = attributes.getValue("lang");
        }

        if (elementName.equalsIgnoreCase("publisher")) {
            //read attributes of book
            bookTmp.publisher = attributes.getValue("country");
        }
    }

    @Override
    public void endElement(String s, String s1, String element) {

        if (element.equals("book")) { //end
            try {
                writer.write(bookTmp, counter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //read element's value
        if (element.equalsIgnoreCase("isbn")) {
            bookTmp.isbn = elValue;
        }
        if (element.equalsIgnoreCase("title")) {
            bookTmp.title = elValue;
        }
        if (element.equalsIgnoreCase("publisher")) {
            bookTmp.publisher = elValue;
        }
        if (element.equalsIgnoreCase("author")) {
            bookTmp.addAuthor(elValue);
        }
        if (element.equalsIgnoreCase("price")) {
            bookTmp.price = Integer.parseInt(elValue);
        }
        if (element.equalsIgnoreCase("regDate")) {
            bookTmp.regDate = LocalDate.parse(elValue);
        }

    }

    @Override
    public void characters(char[] ac, int i, int j) {
        elValue = new String(ac, i, j);
    }

}
