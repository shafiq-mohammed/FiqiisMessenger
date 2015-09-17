/**
 * Created by shafiqmohammed on 9/16/15.
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Server - A computer that anyone in the world can access.
//Use JFrame for GUI
//Sidenote:  in Java TCP is opened by ServerSocket and Socket. UDP is opened by DatagramSocket.
//First, we need to build a container for the GUI


public class Server extends JFrame {
    //variable that holds your message before you send it:
    private JTextField userText;
    //Area that displays conversation (your msgs and whoever you're talking to)
    private JTextArea chatWindow;

    /* So what is a stream? Whenever you connect to someones computer, you communicate
    through streams. There are two streams b/w your computer and the computer you're communcating with.
    The input stream and the output stream. Whenever you send a msg, the msg you send gets packed into the
    output stream and goes to your friends computer. When your friend messages you back, it gets packed into the
    input stream that comes into your computer.
     */
    //Now we have to build some streams:
    private ObjectOutputStream output;
    private ObjectInputStream input;

    //Create a serverSocket that is the socket of the server
    private ServerSocket server;

    //The socket itself. Sets up the connection between the two computers
    private Socket connection;

    //make the gui in the constructor
    public Server(){
        //Title at top of window
        super("Fiqii's instant messenger");
        //Set up that text field where user enters text.
        userText = new JTextField();
        //By default, before you are connected to anyone else, you can't type anything in the message box.
        userText.setEditable(false);
        //Add action listener
        userText.addActionListener(new ActionListener() {
            //Whenever a user types text into text field and hits enter, this event happens:
            public void actionPerformed(ActionEvent actionEvent) {
                //Send msg to send text from text area:
                sendMessage(actionEvent.getActionCommand());
                //once we send text, set text field to empty:
                userText.setText("");
            }
        });
        //add all of the above to the screen:
        add(userText, BorderLayout.NORTH);
        //Create the main chatwindow where both users' msgs get displayed:
        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        //Set the size for the JFrame and make it visible
        setSize(300, 150);
        setVisible(true);
    }

    //Now we need to set up our application: Set up and run the server
    public void startRunning(){
        try { //set up the server and give it a port number
            server = new ServerSocket(6789, 100); //6789 for dummie purposes atm
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

}
