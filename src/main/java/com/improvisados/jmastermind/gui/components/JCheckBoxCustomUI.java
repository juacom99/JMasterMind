/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.improvisados.jmastermind.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicCheckBoxUI;

/**
 *
 * @author jomartinez
 */
public class JCheckBoxCustomUI extends BasicCheckBoxUI
{

    @Override
    public synchronized void paint(Graphics g, JComponent c)
    {
       Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(c.getBackground());
       
       g2d.fillRect(0,0,25,25);
    }

   
    
    
    
}
