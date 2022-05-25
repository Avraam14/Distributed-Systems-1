import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Avraam Katsigras icsd15087
 */

public class MainClass {                                                                            //The main application, everything happens here

    final static String src = "Archive.txt";                                                        //The source file for reading/writing objects 

    public static void main(String[] args) {
        JFrame mainframe = new JFrame("Library System");                                            //The main frame
        JButton addButton = new JButton("Add a Book/Magazine");                                     //Buttons for adding/searching for objects
        JButton searchButton = new JButton("Search for a Book/Magazine");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                                   
        mainframe.setLayout(new FlowLayout());                                                      
        addButton.addActionListener((ActionEvent e) -> {                                            //Creating a new instance of LibAdd to add an object to the file
            new LibAdd();
        });
        
        searchButton.addActionListener((ActionEvent e) -> {                                         //Creating a new instance of LibSearch to search  for an object
            new LibSearch();
        });
        mainframe.add(addButton);                                                                   //Adding and packing everything into the frame
        mainframe.add(searchButton);
        mainframe.pack();
        mainframe.setLocationRelativeTo(null);                                                      //Centering the frame
        mainframe.setVisible(true);
    }
}