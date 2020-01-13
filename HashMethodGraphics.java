/*  HashMethodGraphics
    Name: Ethan Chen
    Date Started: January 11, 2020
*/

import javax.swing.SwingUtilities;

public class HashMethodGraphics { // code sent to me by Mr. Bakker for generic java swing runner file


    /**

     * Launch the application.

     */

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    createAndShowGUI();

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

    }

    private static void createAndShowGUI() throws Exception{

        new HashMethodViewController();

    }




}
