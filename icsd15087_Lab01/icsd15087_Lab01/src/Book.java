import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * @author Avraam Katsigras icsd15087
 */

public class Book extends LibMaterial implements Serializable {                                     //Class for the library's books

    private final int ISBN;                                                                         //Data exclusivey for Books
    private final String authors;

    public Book() {                                                                                 //Default constructor
        super();
        ISBN = -1;
        authors = null;
    }
    
    public Book(String title, String genre, String position, int pages, int pubYear,                //Constructor with initial values
            int ISBN, String authors) {
        super(title, genre, position, pages, pubYear);
        this.ISBN = ISBN;
        this.authors = authors;
    }

    @Override
    public String toString() {                                                                      //String that describes the object for displaying
        return super.toString() + "\nISBN: " + ISBN + "\nAuthor(s): " + authors;
    }

    @Override
    public boolean compare(String title, int code) {                                                //Check if given values are correspondent to the object
        return (code == ISBN) || title.equals(super.title);
    }

    @Override
    public void display() {                                                                         //Displaying the object in a new window
        JOptionPane.showMessageDialog(null, this.toString(), "Material Display",
                JOptionPane.INFORMATION_MESSAGE);
    }
}