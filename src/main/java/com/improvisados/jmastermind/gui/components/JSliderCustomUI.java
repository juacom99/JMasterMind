
package com.improvisados.jmastermind.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 *
 * @author jomartinez
 */
public class JSliderCustomUI extends BasicSliderUI
{
    
    private Color thumbColor;
    private Color trackColor;

    public JSliderCustomUI(JSlider slider,Color thumbColor,Color trakerColor)
    {
        super(slider);
        
        this.thumbColor=thumbColor;
        this.trackColor=trakerColor;
    }

    @Override
    protected Dimension getThumbSize()
    {
        return new Dimension(10,10);
    }

    @Override
    public void paintThumb(Graphics g)
    {
        Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(thumbColor);
       g2d.fillOval(thumbRect.x,thumbRect.y+1,thumbRect.width,thumbRect.height);
    }

    
    @Override
    public void paintTrack(Graphics g)
    {
       Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(trackColor);
       int trackerHeight=4;       
      g2d.fillRoundRect(2,(slider.getHeight()-trackerHeight)/2,slider.getWidth()-4,trackerHeight,1,1);       
    }

    @Override
    public void paintLabels(Graphics g)
    {
        super.paintLabels(g); 
    }

    @Override
    protected void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
        System.out.println("HERE H "+tickBounds+" "+x);
        
        Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(Color.red);
       
       g2d.fillRect(x,tickBounds.y,2,5);
    }

    @Override
    protected void paintMajorTickForVertSlider(Graphics g, Rectangle tickBounds, int y) {
        System.out.println("HERE V");
        Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(Color.red);
       
       g2d.fillRect(tickBounds.x,tickBounds.y,2,5);
    }

    @Override
    protected void paintMinorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
        Graphics2D g2d= ((Graphics2D)g);
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.setColor(Color.red);
       
       g2d.fillRect(tickBounds.x,tickBounds.y,1,3);
    }
    
    
    
    
}
