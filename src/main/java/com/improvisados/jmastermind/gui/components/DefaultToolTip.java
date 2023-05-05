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
package com.improvisados.jmastermind.gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.IllegalComponentStateException;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Window;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.SwingUtilities;

public class DefaultToolTip extends JToolTip {

    public DefaultToolTip() {
        setOpaque(false);
        setPreferredSize(new Dimension(175, 30));
        setBackground(new Color(255, 255, 255, 0));
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void addNotify() {
        super.addNotify();
        setOpaque(false);
        Component parent = this.getParent();
        if (parent != null) {
            if (parent instanceof JComponent) {
                JComponent jparent = (JComponent) parent;
                jparent.setOpaque(false);
            }
        }
        Window window = SwingUtilities.windowForComponent(this);
        try {
            window.setBackground(new Color(255, 255, 255, 0));
        } catch (IllegalComponentStateException e) {
            //Do nothing
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        String text = getComponent().getToolTipText();

        Graphics2D g2d = drawComponent(g);
        drawText(text, g2d);

        g2d.dispose();
    }

    private void drawText(String text, Graphics2D g2d) {
        //Draw the text
        int cHeight = getComponent().getHeight();
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setColor(getForeground());
        if (cHeight > getHeight())
            g2d.drawString(text, (getWidth() - fm.stringWidth(text)) / 2, (getHeight() + fm.getAscent()) / 2 + 2);
        else
            g2d.drawString(text, (getWidth() - fm.stringWidth(text)) / 2, (cHeight + fm.getAscent()) / 2 + 2);
    }

    private Graphics2D drawComponent(Graphics g) {
        //Create a round rectangle
        Shape round = new RoundRectangle2D.Float(0, 8, getWidth(), getHeight(), 8, 8);

        //Draw the background
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(round);
        
        return g2d;
    }
}
