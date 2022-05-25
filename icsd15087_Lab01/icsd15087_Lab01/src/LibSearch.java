import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Avraam Katsigras icsd15087
 */

public class LibSearch {                                                                            //Class for searching through the library's material
    private final static String src = "Archive.txt";                                                //Source file where every object is written
    private JTextField[] textfields;                                                                //The textfields for searching parameters
    private JLabel[] description;                                                                   //Description of said textfields
    private JFrame searchframe;                                                                     //Main frame of search, will contain the panel contentPane
    private JButton search;                                                                         //Button to search once everything is ready
    private JButton back;                                                                           //Button to cancel search
    private JPanel contentPane;                                                                     //Panel that will contain all components
    private JPanel buttons;                                                                         //Panel for the buttons search and back

    public LibSearch() {                                                                            //Default Constructor
        textfields = new JTextField[2];                                                             //Initializing components
        description = new JLabel[2];
        searchframe = new JFrame("Search for a Book/Magazine");
        search = new JButton("Search");
        back = new JButton("Back");
        contentPane = new JPanel();
        buttons = new JPanel(new FlowLayout());
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        searchframe.setContentPane(contentPane);                                                    

        textfields[0] = new JTextField();
        textfields[1] = new JTextField();                                                           //Initializing the textfield as zero in case that's not the parameter we're searching by
        String[] opts = {"Book", "Magazine"};                                                       //The user chooses what he is searching for
        String choice = (String) JOptionPane.showInputDialog(searchframe, 
                "Search for a book or magazine?", "CHOOSE!", JOptionPane.QUESTION_MESSAGE, null,
                opts, "Book");

        description[0] = new JLabel("Title (String)");
        description[1] = choice.equals("Book") ?
                new JLabel("ISBN (Integer, leave 0 if you don't know it)") : 
                new JLabel("Year of Publishment (Integer, put 0 if you don't know it)");
        search.addActionListener((ActionEvent e) -> {                                               //When the user clicks add we begin the search
            try {                                                                                   
                FileInputStream filein = new FileInputStream(src);                                  //The stream for getting input from the file
                ObjectInputStream objin = new ObjectInputStream(filein);                            //The stream that reads objects from file input
                LibMaterial searchObj;                                                              //Object that will help us iterate through the object stream

                while (true) {                                                                      //Loop that will end either when we find the object or when there are no more objects in the file
                    try {
                        searchObj = (LibMaterial) objin.readObject();                               //Reading the object
                        if (searchObj.compare(textfields[0].getText(),                              //We compare the object's values to user's input
                                Integer.parseInt(textfields[1].getText()))) {
                            JOptionPane.showMessageDialog(searchframe, "Found the material!",       //Success message
                                    "Operation Completed!", JOptionPane.INFORMATION_MESSAGE);
                            searchObj.display();                                                    //Display the found object
                            break;                                                                  //Our work here is done
                        }
                    } catch (NumberFormatException ex) {                                            //For bad user inputs 
                        JOptionPane.showMessageDialog(searchframe,
                                "Seems like you tried putting something where it's not supposed to",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (EOFException ex) {                                                     //We didn't find the material
                        JOptionPane.showMessageDialog(searchframe,                                  //Failure message
                                "Could not find material that fits the description.",
                                "Operation Failed", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                objin.close();                                                                      //Close the streams
                filein.close();
            } catch (IOException ex) {                                                              //File error
                JOptionPane.showMessageDialog(searchframe, "Problem reading the file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {                                                   //Object error
                JOptionPane.showMessageDialog(searchframe, "Problem reading an object", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        
        back.addActionListener((ActionEvent e) -> {
            searchframe.dispose();                                                                  //Close the frame to return to previous menu
        });
        
        for (int i = 0; i < 2; i++) {                                                               //Adding all the components to the frame
            contentPane.add(description[i]);
            contentPane.add(textfields[i]);
        }

        buttons.add(search);            
        buttons.add(back);
        contentPane.add(buttons);
        searchframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchframe.pack();
        searchframe.setSize(300, 200);
        searchframe.setResizable(false);
        searchframe.setLocationRelativeTo(null);
        searchframe.setVisible(true);
    }
}