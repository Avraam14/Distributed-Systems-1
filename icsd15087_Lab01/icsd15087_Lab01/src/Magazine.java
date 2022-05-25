import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * @author Avraam Katsigras icsd15087
 */

public class Magazine extends LibMaterial implements Serializable {                                 //Class for the library's Magazines
    private final int tomeNo;                                                                       //Data exclusively for Magazines
    private final int issueNo;

    public Magazine(){                                                                              //Default constructor
        super();
        tomeNo=-1;
        issueNo=-1;
    }
    
    public Magazine(String title, String genre, String position, int pages, int pubYear,            //Constructor with initial values
            int tomeNo, int issueNo) {
        super(title, genre, position, pages, pubYear);
        this.tomeNo = tomeNo;
        this.issueNo = issueNo;
    }

    @Override
    public String toString() {                                                                      //String that describes the object for displaying
        return super.toString() + "\nTome Number: " + tomeNo + "\nIssue: " + issueNo;
    }

    @Override
    public boolean compare(String title, int code) {                                                //Check if given values are correspondent to the object
        return (code == pubYear) || (title != null && title.equals(super.title));
    }
    
    @Override
    public void display() {                                                                         //Displaying the object in a new window
        JOptionPane.showMessageDialog(null, this.toString(), "Material Display",
                JOptionPane.INFORMATION_MESSAGE);
    }
}