/*  ShakespeareGraphics
    Name: Ethan Chen
    Date Started: January 7, 2020
*/

import javax.swing.SwingUtilities;

// Runs my FINAL GRAPHICS FILE for the Shakespearean Concordance project
public class ShakespeareGraphics { // code sent to me by Mr. Bakker for generic java swing runner file


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

        new ShakespeareViewController();

    }


}