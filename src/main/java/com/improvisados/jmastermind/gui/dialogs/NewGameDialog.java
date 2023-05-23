/*
 * Copyright (C) 2018 Joaquin Martinez <juacom04@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.improvisados.jmastermind.gui.dialogs;

import com.improvisados.jmastermind.configuration.Configuration;
import com.improvisados.jmastermind.gui.Theme;
import com.improvisados.jmastermind.gui.components.JSliderCustomUI;
import com.improvisados.jmastermind.gui.controllers.JMMOptionPane;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import com.improvisados.jmastermind.gui.panels.ColorsPickerPane;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class NewGameDialog extends javax.swing.JDialog {

    public ColorsPickerPane colorsPicker;
    
    private Point initialClick;
    
    /**
     * Creates new form NewGameDialog
     */
    public NewGameDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("New Game");
        this.setUndecorated(true);
        
        initComponents();

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(JOptionPane.CANCEL_OPTION);
            }
        });
        
        Theme theme=ThemeController.getInstance().getCurrentTheme();
       
        getRootPane().setBorder(new LineBorder(theme.getDefaultTokenColor()));
        
        LCodeLength.setLocation(Theme.SPACING,Theme.SPACING);
        LCodeLength.setForeground(theme.getForegroundColor());
                
        LGuesses.setForeground(theme.getForegroundColor());
        
        LRepeate.setForeground(theme.getForegroundColor());
        getContentPane().setBackground(theme.getBackgroundColor());
        Color[] colors=Configuration.getInstance().getColors();
        colorsPicker=new ColorsPickerPane(colors,5d,16);
        colorsPicker.setLocation(Theme.SPACING,LRepeate.getY()+LRepeate.getHeight()+Theme.SPACING);
        
        add(colorsPicker);
        okButton.setLocation(colorsPicker.getX()+colorsPicker.getWidth()-okButton.getWidth(),colorsPicker.getY()+colorsPicker.getHeight()+Theme.SPACING);
        okButton.setForeground(theme.getBackgroundColor());
        okButton.setBackground(theme.getForegroundColor());
        
        cancelButton.setLocation(okButton.getX()-Theme.SPACING-cancelButton.getWidth(),okButton.getY());
        cancelButton.setForeground(theme.getBackgroundColor());
        cancelButton.setOpaque(true);
        cancelButton.setBackground(theme.getForegroundColor());

        SCodeLength.setBorder(null);
                
         setSize(3*Theme.SPACING+colorsPicker.getWidth(),2*Theme.SPACING+35*4+colorsPicker.getHeight()+okButton.getHeight());
         
         SCodeLength.setBackground(theme.getBackgroundColor());
         SCodeLength.setForeground(theme.getForegroundColor());
         SCodeLength.setUI(new JSliderCustomUI(SCodeLength));
         
          SGuesses.setBackground(theme.getBackgroundColor());
         SGuesses.setForeground(theme.getForegroundColor());
         SGuesses.setUI(new JSliderCustomUI(SGuesses));
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus()
    {
        return returnStatus;
    }
    
    public int getCodeLength()
    {
        return (int) SCodeLength.getValue();
    }
    
    public int getGuesses()
    {
        return (int) SGuesses.getValue();
    }
    
    public Color[] getColors()
    {
        return colorsPicker.getSelected().toArray(new Color[0]);
    }
    
    public boolean canRepeate()
    {
        return CBRepeate.isSelected();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        LCodeLength = new javax.swing.JLabel();
        LGuesses = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        CBRepeate = new javax.swing.JCheckBox();
        LRepeate = new javax.swing.JLabel();
        SCodeLength = new javax.swing.JSlider();
        SGuesses = new javax.swing.JSlider();

        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                formMousePressed(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(null);

        LCodeLength.setText("Code Length:");
        getContentPane().add(LCodeLength);
        LCodeLength.setBounds(16, 16, 80, 30);

        LGuesses.setText("Guesses:");
        getContentPane().add(LGuesses);
        LGuesses.setBounds(16, 50, 80, 30);

        okButton.setText("PLAY");
        okButton.setBorder(null);
        okButton.setContentAreaFilled(false);
        okButton.setOpaque(true);
        okButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okButtonActionPerformed(evt);
            }
        });
        getContentPane().add(okButton);
        okButton.setBounds(370, 480, 81, 30);
        getRootPane().setDefaultButton(okButton);

        cancelButton.setText("Cancel");
        cancelButton.setBorder(null);
        cancelButton.setContentAreaFilled(false);
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);
        cancelButton.setBounds(260, 480, 70, 30);
        getContentPane().add(CBRepeate);
        CBRepeate.setBounds(150, 90, 20, 19);

        LRepeate.setText("Repeat Symbols:");
        getContentPane().add(LRepeate);
        LRepeate.setBounds(20, 90, 120, 30);

        SCodeLength.setMaximum(7);
        SCodeLength.setMinimum(4);
        SCodeLength.setValue(5);
        getContentPane().add(SCodeLength);
        SCodeLength.setBounds(110, 20, 200, 20);

        SGuesses.setMaximum(20);
        SGuesses.setMinimum(5);
        SGuesses.setValue(11);
        getContentPane().add(SGuesses);
        SGuesses.setBounds(110, 50, 200, 10);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(colorsPicker.getSelectedCount()>=4)
        {
            if(!CBRepeate.isSelected() && this.colorsPicker.getSelectedCount()< (int)SCodeLength.getValue())
            {
                JMMOptionPane.showMessageDialog(this,"Not enough symbols to create a code without repetition","Color too similar",JOptionPane.CLOSED_OPTION);
            }
            else
            {
                doClose(JOptionPane.OK_OPTION);
            }            
        }
        else
        {
            JMMOptionPane.showMessageDialog(this,"You must select at leaset 4 Symbols","Not enough Symbols",JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(JOptionPane.CANCEL_OPTION);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(JOptionPane.CANCEL_OPTION);
    }//GEN-LAST:event_closeDialog

    private void formMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMousePressed
    {//GEN-HEADEREND:event_formMousePressed
         initialClick = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseDragged
    {//GEN-HEADEREND:event_formMouseDragged
        
        
        
        int thisX = this.getLocation().x;
            int thisY = this.getLocation().y;

            // Determine how much the mouse moved since the initial click
            int xMoved = evt.getX() - initialClick.x;
            int yMoved = evt.getY() - initialClick.y;

            // Move window to this position
            int X = thisX + xMoved;
            int Y = thisY + yMoved;
            this.setLocation(X, Y);
            
    }//GEN-LAST:event_formMouseDragged
    
    private void doClose(int retStatus)
    {
            returnStatus = retStatus;
            setVisible(false);
            dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CBRepeate;
    private javax.swing.JLabel LCodeLength;
    private javax.swing.JLabel LGuesses;
    private javax.swing.JLabel LRepeate;
    private javax.swing.JSlider SCodeLength;
    private javax.swing.JSlider SGuesses;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = JOptionPane.CANCEL_OPTION;
}
