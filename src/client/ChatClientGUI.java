package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatClientGUI extends JFrame {

    JButton sendButton;
    JTextField textField;
    JTextArea textArea;

    ChatClientGUI(){
        setTitle("ChatApp");
        setMinimumSize(new Dimension(800,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //header
        JPanel headerJPanel = new JPanel();
        headerJPanel.setBackground(Color.decode("#f4f4f5"));
        headerJPanel.setPreferredSize(new Dimension(100,72));
        headerJPanel.setLayout(new BorderLayout());

        //header's label
        JLabel headerJLabel = new JLabel();
        headerJLabel.setText("Chat App");
        headerJLabel.setFont(new Font(Font.MONOSPACED,Font.PLAIN,24));
        headerJLabel.setHorizontalAlignment(JLabel.CENTER);
        headerJPanel.add(headerJLabel,BorderLayout.CENTER);




        add(headerJPanel, BorderLayout.NORTH);

        //main section
        JPanel mainJPanel = new JPanel();
        mainJPanel.setBackground(Color.decode("#e5e5e5"));
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.setBorder(BorderFactory.createLineBorder(Color.white, 5, true));

        // textArea
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
        textArea.setEditable(false);


        mainJPanel.add(textArea);
        add(mainJPanel, BorderLayout.CENTER);

        //input section
        JPanel userInputJPanel = new JPanel();
        userInputJPanel.setBackground(Color.decode("#f5f5f5"));
        userInputJPanel.setPreferredSize(new Dimension(100,100));
        userInputJPanel.setLayout(new BorderLayout());

        //sendButton
        sendButton = new JButton();
        sendButton.setPreferredSize(new Dimension(150,100));
        sendButton.setText("Send");
        sendButton.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
        sendButton.setBackground(Color.white);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageToSend = textField.getText();
                sendMessageToServer(messageToSend);
                textField.setText("");
            }
        });


        //textField
        textField = new JTextField();
        textField.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));

        userInputJPanel.add(textField);
        userInputJPanel.add(sendButton, BorderLayout.EAST);
        add(userInputJPanel, BorderLayout.SOUTH);




    }

    public void sendMessageToServer(String message){
        textArea.append("You: " + message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());//scrolling bottom

        // TODO: Implement logic to send the message to the server.
    }

    public void displayReceivedMessage(String message){
        textArea.append("Server: " + message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Scroll to the bottom.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChatClientGUI clientGUI = new ChatClientGUI();
                clientGUI.setVisible(true);

                ChatClient client = new ChatClient(clientGUI);
            }
        });
    }
}
