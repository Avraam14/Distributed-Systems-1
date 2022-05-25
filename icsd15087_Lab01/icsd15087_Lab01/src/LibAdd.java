import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

public class LibAdd {                                                                               //Class for adding meterial to the source file
    private final static String src = "Archive.txt";                                                //Said source file
    private JTextField[] textfields;                                                                //The textfields for object parameters
    private JLabel[] description;                                                                   //Description of said textfields
    private JFrame addframe;                                                                        //Main frame, will contain the panel contentPane
    private JButton addButton;                                                                      //Button to add the  object
    private JPanel contentPane;                                                                     //Panel that will contain all components

    public LibAdd() {                                                                               //Default Constructor
        addframe = new JFrame("Add a Book/Magazine");                                               //Initializing components
        addButton = new JButton("Done");

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        addframe.setContentPane(contentPane);

        textfields = new JTextField[7];
        description = new JLabel[7];

        for (int i = 0; i < 7; i++) {
            textfields[i] = new JTextField();
        }
        String[] opts = {"Book", "Magazine"};                                                       //The user chooses what he wants to add
        String choice = (String) JOptionPane.showInputDialog(addframe, "Add a book or magazine?", 
                "CHOOSE!", JOptionPane.QUESTION_MESSAGE, null, opts, "Book");

        description[0] = new JLabel("Title (String)");                                              //Initializing the labels according to user's choice
        description[1] = new JLabel("Genre (String)");
        description[2] = new JLabel("Position (String)");
        description[3] = new JLabel("Pages (Integer)");
        description[4] = new JLabel("Publishment Year (Integer)");
        description[5] = choice.equals("Book") ? new JLabel("ISBN (Integer)") : 
                new JLabel("Tome Number (Integer)");
        description[6] = choice.equals("Book") ? new JLabel("Authors (String)") : 
                new JLabel("Issue Number (Integer)");
        
        addButton.addActionListener((ActionEvent e) -> {                                            //Create the object
            try {
                FileOutputStream fileout = new FileOutputStream(src);                               //The stream for outputting objects
                ObjectOutputStream objout = new ObjectOutputStream(fileout);                        //The stream the will write the output to fileout
                Object writeObj;                                                                    //The object to be written
                if (choice.equals("Book")) {                                                        //Calling the constructor that the user chose
                    writeObj = new Book(textfields[0].getText(), textfields[1].getText(),           //With initial values provided by the user
                            textfields[2].getText(), Integer.parseInt(textfields[3].getText()), 
                            Integer.parseInt(textfields[4].getText()),
                            Integer.parseInt(textfields[5].getText()),
                            textfields[6].getText());
                } else {
                    writeObj = new Magazine(textfields[0].getText(), textfields[1].getText(),
                            textfields[2].getText(), Integer.parseInt(textfields[3].getText()), 
                            Integer.parseInt(textfields[4].getText()),
                            Integer.parseInt(textfields[5].getText()), 
                            Integer.parseInt(textfields[6].getText()));
                }
                objout.writeObject(writeObj);                                                       //Write object
                JOptionPane.showMessageDialog(contentPane, "Object written succefully!",            //Success message
                        "Operation Completed", JOptionPane.INFORMATION_MESSAGE);                     
                fileout.close();                                                                    //Close the streams and the window
                objout.close();
                addframe.dispose();
            } catch (IOException ex) {                                                              //File error
                JOptionPane.showMessageDialog(addframe, "Problem opening the file", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {                                                    //Bad user input
                JOptionPane.showMessageDialog(addframe, 
                        "Seems like you tried putting something where it's not supposed to", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        for (int i = 0; i < 7; i++) {                                                               //Add and = pack components
            contentPane.add(description[i]);
            contentPane.add(textfields[i]);
        }

        contentPane.add(addButton);
        addframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addframe.pack();
        addframe.setSize(300, 500);
        addframe.setResizable(false);
        addframe.setLocationRelativeTo(null);
        addframe.setVisible(true);
    }
}
