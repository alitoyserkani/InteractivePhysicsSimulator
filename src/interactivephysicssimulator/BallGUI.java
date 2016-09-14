package interactivephysicssimulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alitoyserkani
 */
public class BallGUI extends javax.swing.JFrame {
    
    static Circle cC = new Circle(20, 1, Color.blue, new Vector(0,0), 150, 150); // sets default circle
    boolean objectComplete = false; //true or false for completion of ball creation
    int currMass, currRadius; //the currently selected componenets
    String currColor; //current selected color
    Thread t; 
    
    


    public BallGUI() {
        
        initComponents(); //creates all JFrame form components
        t = new Thread(new Runnable() { //initialized thread
            

            @Override
            public void run() {
                
                massSpinner.setValue(10); //presets/initializes spinner values
                radiusSpinner.setValue(50);
                
                cC = new Circle(20, 1, Color.blue, new Vector(0,0), 150, 150);
                
            
                currMass = (int) massSpinner.getValue(); //sets initial spinner/combo box values to variables for later use
                currRadius = (int) radiusSpinner.getValue();
                currColor = (String) colorComboBox1.getItemAt(colorComboBox1.getSelectedIndex());

                repaint(); //draws screen


                while (objectComplete == false) { // runs until "ADD TO SIMULATION" has been pressed

                    if (objectComplete == true) {
                        t = null;
                    }

                    String colorCheck = currColor;

                    checkJComponents(); //checks updated spinner/combo box values

                    //checks if a component has actually been changed
                    if (currMass != cC.mass || currRadius != cC.radius || !currColor.equalsIgnoreCase(colorCheck)) {

                        alterComponents(); //changes components to new selected components
                        repaint(); //updates GUI screen
                        sleep(10); //brieflinterval in between        
                    }
                    // if "ADD TO SIMULATION" is pressed, finish BallGUI and go back to SimGUI
                    if (simAddedButton.getModel().isPressed()) {                    
                        SimulatorGUI.pieceBeingPlaced = true; //updates booleans for object placing
                        SimulatorGUI.ballBeingAdded = true;
                        setVisible(false); 
                        //dispose();
                        //objectCreatorPanel.repaint();
                        
                        objectComplete = true;
                        
                        
                        
                       
                        

                    }           
                }
                

              }
            });
            t.start(); //starts thread
            
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radiusSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        massSpinner = new javax.swing.JSpinner();
        colorComboBox1 = new javax.swing.JComboBox();
        objectCreatorPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        simAddedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        radiusSpinner.setModel(new javax.swing.SpinnerNumberModel(5, 5, 150, 1));

        jLabel1.setText("Radius");

        jLabel2.setText("Mass");

        massSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 0, 100, 1));

        colorComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blue", "Red", "Yellow", "Green" }));
        colorComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorComboBox1ActionPerformed(evt);
            }
        });

        objectCreatorPanel.setBackground(new java.awt.Color(204, 204, 204));

        org.jdesktop.layout.GroupLayout objectCreatorPanelLayout = new org.jdesktop.layout.GroupLayout(objectCreatorPanel);
        objectCreatorPanel.setLayout(objectCreatorPanelLayout);
        objectCreatorPanelLayout.setHorizontalGroup(
            objectCreatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        objectCreatorPanelLayout.setVerticalGroup(
            objectCreatorPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 305, Short.MAX_VALUE)
        );

        jLabel3.setText("Color");

        simAddedButton.setText("ADD TO SIMULATION");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(13, 13, 13)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(radiusSpinner))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .add(42, 42, 42)
                        .add(jLabel3))
                    .add(layout.createSequentialGroup()
                        .add(massSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(colorComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
            .add(objectCreatorPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(simAddedButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(objectCreatorPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(radiusSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(massSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(colorComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 53, Short.MAX_VALUE)
                .add(simAddedButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void colorComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colorComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public void paint(Graphics g) {
        super.paint(g); //draws all JFrame components first
        
        Image img = createImage(); //draws created image onto the screen
        Graphics g2 = objectCreatorPanel.getGraphics();
        g2.drawImage(img, 0, 0, objectCreatorPanel); 
//            g2.setColor(Color.gray);
//            g2.fillRect(0, 0, Integer.MAX_VALUE,Integer.MAX_VALUE);
//            g2.setColor(cC.color);
//            g2.fillOval((int) (cC.xCentre-cC.radius), (int) (cC.yCentre-cC.radius), (int)(2*cC.radius), (int)(2*cC.radius));
//            g2.drawOval((int) (cC.xCentre-cC.radius), (int) (cC.yCentre-cC.radius), (int)(2*cC.radius), (int)(2*cC.radius));        
    }
    
    public Image createImage() {
        BufferedImage b = new BufferedImage(objectCreatorPanel.getWidth(),objectCreatorPanel.getHeight(),BufferedImage.TYPE_INT_RGB);        
        Graphics2D gr = (Graphics2D) b.getGraphics();
        
        gr.setColor(Color.gray); //draws background color and covers previous frames
        gr.fillRect(0, 0, Integer.MAX_VALUE,Integer.MAX_VALUE);
        
        gr.setColor(cC.color); //draws the circle/ball after each component change
        gr.fillOval((int) (cC.xCentre-cC.radius), (int) (cC.yCentre-cC.radius), (int)(2*cC.radius), (int)(2*cC.radius));
        //gr.drawOval((int) (cC.xCentre-cC.radius), (int) (cC.yCentre-cC.radius), (int)(2*cC.radius), (int)(2*cC.radius));   
        gr.setColor(Color.white); //draws current mass on screen
        gr.drawString(Integer.toString(cC.mass)+ " kg", 250, 290);
        return b; //return buffered image
    }
    
    public void alterComponents() { //updates object/ball compnents
        cC.mass = currMass; 
        cC.radius = currRadius;
        if (currColor.equalsIgnoreCase("blue")) { //convers combo box value to acutal color change
            cC.color = Color.blue;
        }
        else if (currColor.equalsIgnoreCase("red")) {
            cC.color = Color.red;
        }
        else if (currColor.equalsIgnoreCase("yellow")) {
            cC.color = Color.yellow;
        }
        else if (currColor.equalsIgnoreCase("green")) {
            cC.color = Color.green;
        }
    }
    
    
    public void checkJComponents() { //checks the spinner and combo box values        
         currMass = (int) massSpinner.getValue();
         currRadius = (int) radiusSpinner.getValue();
         currColor = (String) colorComboBox1.getItemAt(colorComboBox1.getSelectedIndex());
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BallGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BallGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BallGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BallGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BallGUI().setVisible(true);
                
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox colorComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner massSpinner;
    private javax.swing.JPanel objectCreatorPanel;
    private javax.swing.JSpinner radiusSpinner;
    private javax.swing.JButton simAddedButton;
    // End of variables declaration//GEN-END:variables

    public void sleep(int i) { //initialies sleeping
        try {
            Thread.sleep(i);
        } 
        catch (Exception e) {}
    }
}
