/*  ShakespeareController
    Name: Ethan Chen
    Date Started: December 14, 2019
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.StringTokenizer;

// controls graphics for my Final Graphics for Shakespeare Concordance
public class ShakespeareViewController extends JFrame {

    private JLabel info;
    private JPanel contentPane;
    private JButton shakespeare;
    private JButton hayes;
    private JTextField input;
    private JTextArea output;
    private JScrollPane scroll;
    ShakespeareConcordance sconcordance;
    HayesConcordance hconcordance;


    public ShakespeareViewController() throws IOException {
        sconcordance = new ShakespeareConcordance();
        hconcordance = new HayesConcordance();
        setupMainPanel();
        setupJLabels();
        setupScrollingField();
        setupField();
        setupButtons();
        setVisible(true);

        resetAll();
    }

    private void setupMainPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255, 246, 115));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    private void setupJLabels() {
        info = new JLabel();
        info.setText("<html>Welcome to the Shakespearean Concordance!<br/>Please input any word and I will tell you what sonnets it appears in!</html>");
        info.setBounds(50, 50, 400, 50);

        contentPane.add(info);
    }

    private void setupScrollingField() {
        output = new JTextArea(16, 58);
        output.setEditable(false); // set textArea non-editable
        scroll = new JScrollPane(output);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(50, 250, 400, 200);

        contentPane.add(scroll);
    }


    private void setupField() {
        input = new JTextField();
        input.setBounds(50, 125, 400, 50);

        contentPane.add(input);
    }

    private void setupButtons() {

        shakespeare = new JButton();
        shakespeare.setText("SHAKESPEARE");
        shakespeare.setBounds(50, 200, 190, 25);
        shakespeare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = input.getText();
                resetAll();
                if (!inputText.equals("")) {
                    inputText = inputText.toUpperCase();
                    WordRecords word = sconcordance.findWord(inputText); // finds the inputted word
                    if (word.value.equals("DNE")) {
                        output.append("There were no occurrences of " + inputText + " in Shakespeare's sonnets.");
                    } else {
                        for (Occurrence x : word.occurrences) { // prints out occurrences
                            output.append(x.toString());
                            output.append("\n");
                        }
                        output.append("\n");
                        output.append("There were " + word.occurrences.size() + " occurrences of " + inputText + " in Shakespeare's sonnets.\n");
                        output.append("Out of " + sconcordance.totalWords + " total words, the frequency is: \n");
                        double frequency = Double.valueOf(word.occurrences.size())/sconcordance.totalWords*100;
                        frequency = Double.valueOf(Integer.valueOf((int) (frequency*100)))/100; // formatting to 2 decimals
                        output.append(frequency + "%");
                    }
                }
            }
        });

        hayes = new JButton();
        hayes.setText("HAYES");
        hayes.setBounds(260, 200, 190, 25);
        hayes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = input.getText();
                resetAll();
                if (!inputText.equals("")) {
                    inputText = inputText.toUpperCase();
                    WordRecords word = hconcordance.findWord(inputText); // finds the inputted word
                    if (word.value.equals("DNE")) {
                        output.append("There were no occurrences of " + inputText + " in Terrance Hayes' sonnets.");
                    } else {
                        for (Occurrence x : word.occurrences) { // prints out the occurrences
                            output.append(x.toString());
                            output.append("\n");
                        }
                        output.append("\n");
                        output.append("There were " + word.occurrences.size() + " occurrences of " + inputText + " in Terrance Hayes' sonnets.\n");
                        output.append("Out of " + hconcordance.totalWords + " total words, the frequency is: \n");
                        double frequency = Double.valueOf(word.occurrences.size())/hconcordance.totalWords*100;
                        frequency = Double.valueOf(Integer.valueOf((int) (frequency*100)))/100; // formatting to 2 decimals
                        output.append(frequency + "%");

                    }
                }
            }
        });

        contentPane.add(shakespeare);
        contentPane.add(hayes);
    }

    private void resetAll() { // puts everything back into initial game state, happens at 'end' of game
        output.setText("");
    }


}

