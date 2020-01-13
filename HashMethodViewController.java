/*  HashMethodController
    Name: Ethan Chen
    Date Started: January 11, 2019
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HashMethodViewController extends JFrame { // graphics file for my attempt at computer-generated array-size vs. efficiency graph

        private JPanel contentPane;
        ArrayList<String> dictionary;


        public HashMethodViewController() throws IOException {
            dictionary = new ArrayList<String>();
            BufferedReader buf = new BufferedReader(new FileReader("/Users/echen20/IdeaProjects/HashTable/src/Dict.txt")); // change file path to match your computer
            String line = buf.readLine();
            setVisible(true);
            while(line != null) {
                dictionary.add(line);
                line = buf.readLine();
            }

            setupMainPanel();
            addDots();
            dictionary = new ArrayList<String>();

        }

        private void setupMainPanel() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(0, 0, 500, 500);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
        }

        private void addDots() {

            // tries several different size arrays
            for(int x = dictionary.size()*2; x<dictionary.size()*100; x += dictionary.size()/5) {
                double point = testMethod(dictionary, x);
                JLabel label = new JLabel();
                label.setText("0");
                // places 0s such that x-axis is the size of the array relative to the size of list
                // and the y-axis is the average block size - kind of works, clear regression
                label.setBounds(10*(x/(dictionary.size()/5)), (int) ((int) 500-(50*point)), 10, 10);

                contentPane.add(label);
            }
        }

        public double testMethod(ArrayList<String> dictionary, int listLength) {

            GenericHashTable hashTable = new GenericHashTable(listLength, 4);
            for(String word : dictionary) {
                hashTable.add(word);
            }

            double averageCase = hashTable.getAverageBlockSize();

            return averageCase;
        }

    }

