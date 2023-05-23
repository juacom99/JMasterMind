/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.improvisados.jmastermind.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 *
 * @author jomartinez
 */
public class JSliderCustomUI extends BasicSliderUI
{

    public JSliderCustomUI(JSlider slider)
    {
        super(slider);        
    }

    @Override
    protected Dimension getThumbSize()
    {
        return new Dimension(8,8);
    }

    @Override
    public void paintThumb(Graphics g)
    {
        Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(slider.getForeground());
       g2d.fillOval(thumbRect.x,thumbRect.y+1,thumbRect.width,thumbRect.height);
    }

    
    @Override
    public void paintTrack(Graphics g)
    {
       Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(slider.getBackground().brighter());
       
       int trackerHeight=4;
       
      g2d.fillRoundRect(2,(slider.getHeight()-trackerHeight)/2,slider.getWidth()-4,trackerHeight,1,1);
       
    }

    @Override
    public void paintLabels(Graphics g)
    {
        super.paintLabels(g); 
    }
    
    
    
    
    
    
   
    
    

    
    
    
    
    
    
}
