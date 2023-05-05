/*
 * Copyright (C) 2016 Joaquin Martinez <juacom04@gmail.com>
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

 * @author A. Kevin Bailey
 * @version 2.5.0
 */

package com.improvisados.jmastermind.gui.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicToolTipUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Vector;



public class MultiLineToolTip extends JToolTip
{
  public MultiLineToolTip() {
    setUI(new MultiLineToolTipUI());
  }
}

/**
 * Title:        MultiLineToolTipUI<p>
 * Description:  <p>
 * @author A. Kevin Bailey
 * @version 2.5.0
 */
@SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration", "UnusedAssignment", "ConstantConditions", "unused"})
class MultiLineToolTipUI extends BasicToolTipUI
{
    private String[] strStrings;

    private int maxWidth = 0;

    public void paint(Graphics graphics, JComponent jComponent)
    {
        FontMetrics fmMetrics = graphics.getFontMetrics();
        Dimension size = jComponent.getSize();
        Graphics2D g2d = (Graphics2D) graphics.create();
        
         Shape round = new RoundRectangle2D.Float(0, 16, size.width ,size.height, 16,16);
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(jComponent.getBackground());
        g2d.fill(round);
        g2d.setColor(jComponent.getForeground());
       
        
        if (strStrings != null) {
            for (int i = 0; i < strStrings.length; i++) {
                g2d.drawString(strStrings[i], 3, (fmMetrics.getHeight()) * (i + 1));
            }
        }
    }
    

    public Dimension getPreferredSize(JComponent jComponent)
    {
        String strLine;
        int intMaxWidth = 0;
        int intHeight;
        int intLines;
        FontMetrics fmMetrics = jComponent.getFontMetrics(jComponent.getFont());
        String strTipText = ((JToolTip) jComponent).getTipText();
        BufferedReader bufferedReader = new BufferedReader(new StringReader(strTipText));
        Vector<String> vStrings = new Vector<String>();

        if (strTipText == null) {
            strTipText = "";
        }
        try {
            while ((strLine = bufferedReader.readLine()) != null) {
                int width = SwingUtilities.computeStringWidth(fmMetrics, strLine);
                intMaxWidth = (intMaxWidth < width) ? width : intMaxWidth;
                vStrings.addElement(strLine);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        intLines = vStrings.size();
        if (intLines < 1) {
            strStrings = null;
            intLines = 1;
        }
        else {
            strStrings = new String[intLines];
            int i = 0;
            for (Enumeration e = vStrings.elements(); e.hasMoreElements(); i++) {
                strStrings[i] = (String) e.nextElement();
            }
        }
        intHeight = fmMetrics.getHeight() * intLines;
        this.maxWidth = intMaxWidth;

        return new Dimension(intMaxWidth + 6, intHeight + 4);
    }
}