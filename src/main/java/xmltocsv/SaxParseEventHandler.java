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

    /**
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        /*
        handle start of a new Book tag and attributes of an element
         */

        if (qName.equalsIgnoreCase("book")) {  //start
            bookTmp = new Book();
            counter++;

            //read attributes of book
            bookTmp.id = attributes.getValue("id");
            bookTmp.lang = attributes.getValue("lang");
        }

        if (qName.equalsIgnoreCase("publisher")) {
            //read attributes of book
            bookTmp.publisher = attributes.getValue("country");
        }
    }

    /**
     * @param qName The qualified name (with prefix), or the
     */
    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("book")) { //end
            try {
                writer.write(bookTmp, counter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //read element's value
        if (qName.equalsIgnoreCase("isbn")) {
            bookTmp.isbn = elValue;
        }
        if (qName.equalsIgnoreCase("title")) {
            bookTmp.title = elValue;
        }
        if (qName.equalsIgnoreCase("publisher")) {
            bookTmp.publisher = elValue;
        }
        if (qName.equalsIgnoreCase("author")) {
            bookTmp.addAuthor(elValue);
        }
        if (qName.equalsIgnoreCase("price")) {
            bookTmp.price = Integer.parseInt(elValue);
        }
        if (qName.equalsIgnoreCase("regDate")) {
            bookTmp.regDate = LocalDate.parse(elValue);
        }

    }

    /**
     * Receive notification of character data inside an element.
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        elValue = new String(ch, start, length);
    }

}
