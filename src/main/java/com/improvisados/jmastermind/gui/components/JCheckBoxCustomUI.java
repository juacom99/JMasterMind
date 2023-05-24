package com.improvisados.jmastermind.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicCheckBoxUI;

/**
 *
 * @author jomartinez
 */
public class JCheckBoxCustomUI extends BasicCheckBoxUI
{
    
    private Color background;
    private Color foreground;
    private Color border;

    public JCheckBoxCustomUI(Color background, Color foreground, Color border) {
        this.background = background;
        this.foreground = foreground;
        this.border = border;
    }

   
    

    @Override
    public synchronized void paint(Graphics g, JComponent c)
    {
        
        JCheckBox checkbox=((JCheckBox)c);
        
        int w=checkbox.getWidth();
        int h=checkbox.getHeight();
        
       Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(border);
       g2d.fillRect(0,0,w,h);
       g2d.setColor(background);
       g2d.fillRect(1,1,w-2,h-2);
       
         
       
       if(checkbox.isSelected())
       {
            g2d.setColor(foreground);
            g2d.fillRoundRect(5,5,w-10,h-10,5,5);
       }
    }

   
    
    
    
}
