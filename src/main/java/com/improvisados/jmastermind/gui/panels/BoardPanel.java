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

package com.improvisados.jmastermind.gui.panels;

import com.improvisados.jmastermind.gui.Theme;
import java.awt.Component;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class BoardPanel extends javax.swing.JPanel implements MouseWheelListener
{

    /**
     * Creates new form BoardPanel
     */
    private Component lastComponent;
    private int step;
    private int displayables;

    public BoardPanel()
    {
        initComponents();
        step = Theme.TOKEN_SIZE+Theme.SPACING;
        this.displayables = 7;
        addMouseWheelListener(this);
    }

    public BoardPanel(int displayables)
    {
        initComponents();
        step = Theme.TOKEN_SIZE+Theme.SPACING;
        this.displayables = displayables;
        addMouseWheelListener(this);
    }

    @Override
    public Component add(Component comp)
    {
        if (lastComponent == null)
        {
            comp.setLocation(comp.getX(), getHeight() - comp.getHeight());
            
        }
        else
        {
            scroll(getHeight() - lastComponent.getY() - lastComponent.getHeight() - step);
            comp.setLocation(comp.getX(), lastComponent.getY() + step);
        }
        repaint();
        lastComponent = comp;
        return super.add(comp);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scroll(int amount)
    {
        for (int i = 0; i < getComponentCount(); i++)
        {
            getComponent(i).setLocation(getComponent(i).getX(), getComponent(i).getY() + amount);
        }
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if (lastComponent != null)
        {

            int contentHeight = (int) ((getComponentCount() * step) - Theme.SPACING);
            if (contentHeight > getHeight())
            {
                //Mouse Down
                if (e.getWheelRotation() == 1)
                {
                    if ((lastComponent.getY() - (step * getComponentCount()) + step) < 0)
                    {
                        scroll(step);
                    }

                }
                //Mouse Up
                else
                {
                    if (e.getWheelRotation() == -1)
                    {
                        if (lastComponent.getY() > getHeight())
                        {
                            scroll(-step);
                        }

                    }
                }
            }
        }
    }

    public int getDisplayables()
    {
        return displayables;
    }

    
    public void resetComponent()
    {
        this.removeAll();
        this.lastComponent=null;
    }
}
