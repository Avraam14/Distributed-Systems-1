import java.io.Serializable;

/**
 * @author Avraam Katsigras icsd15087
 */

public abstract class LibMaterial implements Serializable {                                         //Abstract class for the library material (Books and Magazines)
    protected final String title;                                                                   //Material data for all objects of this type
    protected final String genre;                                                                       
    protected final int pubYear;
    protected final int pages;
    protected final String position;
    
    public LibMaterial() {                                                                          //Default constructor
        title = null;
        genre = null;
        pubYear = -1;
        pages = -1;
        position = null;
    }
    
    public LibMaterial(String title, String genre, String position, int pages, int pubYear) {       //Constructor with initial values
        this.title = title;
        this.genre = genre;
        this.position = position;
        this.pages = pages;
        this.pubYear = pubYear;
    }

    @Override
    public String toString() {                                                                      //String that describes the object for displaying
        return "Title: " + title + "\nGenre: " + genre + "\nPublishment Year: " + pubYear +
                "\nPages: " + pages + "\nPosition: " + position;
    }

    public abstract boolean compare(String title, int code);                                        //Check if given values are correspondent to the object

    public abstract void display();                                                                 //Displaying an object if needed
}