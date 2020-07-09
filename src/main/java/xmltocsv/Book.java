package xmltocsv;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    String lang;
    String title;
    String id;
    String isbn;
    LocalDate regDate;
    String publisher;
    int price;
    List<String> authors = new ArrayList<>();

    void addAuthor(String auth) {
        authors.add(auth);
    }

    @Override
    public String toString() {
        return emptyOnNull(lang) +
                "," + emptyOnNull(title) +
                "," + emptyOnNull(id) +
                "," + emptyOnNull(isbn) +
                "," + emptyOnNull(regDate) +
                "," + emptyOnNull(publisher) +
                "," + emptyOnNull(price) +
                "," + String.join("|", authors) +
                "\r\n";
    }

    Object emptyOnNull(Object s) {
        if (s == null) {
            return "";
        }
        return s;
    }
}