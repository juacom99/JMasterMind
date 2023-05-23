/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.improvisados.jmastermind;

import com.improvisados.jmastermind.configuration.Configuration;
import com.improvisados.jmastermind.gui.JMasterMind;
import java.awt.SplashScreen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Martinez <juacom04@gmail.com>
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(JMasterMind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JMasterMind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JMasterMind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JMasterMind.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                SplashScreen mySplash = SplashScreen.getSplashScreen();
                if (mySplash != null)
                {
                    //System.out.println("NO NULL");
                    try
                    {
                        Thread.sleep(400);
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    //System.out.println("NULL");
                }
     
                Configuration.getInstance();

                    JMasterMind jmm = new JMasterMind();
                    jmm.setLocationRelativeTo(null);
                    jmm.setVisible(true);
                }
               
            
        });
    }
}
