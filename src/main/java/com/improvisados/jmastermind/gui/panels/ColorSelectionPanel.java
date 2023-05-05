/*
 * Copyright (C) 2019 jomartinez
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

import com.improvisados.jmastermind.configuration.Configuration;
import com.improvisados.jmastermind.gui.Theme;
import com.improvisados.jmastermind.gui.components.MultiLineToolTip;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import com.improvisados.jmastermind.gui.layouts.CentredRowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;

public class ColorSelectionPanel extends javax.swing.JPanel
{

    private List<Color> colors;
    public static final int NUMBER_OF_COLUMNS = 6;

    /**
     * Creates new form ColorSelectionPanel
     */
    public ColorSelectionPanel()
    {
        initComponents();
        CentredRowLayout layoutmgr = new CentredRowLayout(NUMBER_OF_COLUMNS, Theme.SPACING, Theme.TOKEN_SIZE);
        setLayout(layoutmgr);
        final Theme theme = ThemeController.getInstance().getCurrentTheme();
        Color[] colors = Configuration.getInstance().getColors();
        this.colors = new ArrayList();

        for (Color c : colors)
        {
            this.colors.add(c);
        }

        int rows = (int) Math.ceil((double) (colors.length + 1) / NUMBER_OF_COLUMNS);
        setSize(Theme.TOKEN_SIZE * NUMBER_OF_COLUMNS + Theme.SPACING * (NUMBER_OF_COLUMNS - 1), Theme.TOKEN_SIZE * rows + Theme.SPACING * (rows - 1));
        setBackground(theme.getBackgroundColor());
        JButton BColor;
        setSize(Theme.TOKEN_SIZE * NUMBER_OF_COLUMNS + Theme.SPACING * (NUMBER_OF_COLUMNS - 1), Theme.TOKEN_SIZE * rows + Theme.SPACING * (rows - 1));
        setBackground(theme.getBackgroundColor());

        MouseAdapter mouseListener = new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                BColorMousePressed(e);
            }

        };

        for (int i = 0; i < colors.length; i++)
        {
            BColor = new JButton()
            {
                @Override
                public JToolTip createToolTip()
                {
                    JToolTip old = super.createToolTip();

                    MultiLineToolTip tool = new MultiLineToolTip();
                    tool.setTipText(old.getTipText());
                    tool.setToolTipText(old.getToolTipText());
                    tool.setComponent(this);
                    tool.setBackground(theme.getBackgroundColor());
                    tool.setForeground(theme.getForegroundColor());
                    tool.setBorder(new LineBorder(theme.getDefaultTokenColor()));
                    return tool;
                }

            };
            BColor.setSize(Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
            BColor.setName("" + i);
            BColor.setOpaque(true);
            BColor.setBackground(colors[i]);
            BColor.setIcon(theme.getToken());
            BColor.addMouseListener(mouseListener);
            BColor.setToolTipText("Left click to remove the color");
            add(BColor);
        }

        JButton BAdd = new JButton();
        BAdd.setSize(Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
        BAdd.setIcon(theme.getAddColor());
        BAdd.setBackground(theme.getDefaultTokenColor());
        BAdd.setOpaque(true);
        BAdd.setContentAreaFilled(false);
        BAdd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BAddActionPerformed(e);

            }

        });
        add(BAdd);

    }

    private void BColorMousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            JButton src = ((JButton) e.getSource());

            remove(src);

            colors.remove(src.getBackground());

            repaint();
            doLayout();

        }
    }

    private void BAddActionPerformed(ActionEvent e)
    {
        final Theme theme = ThemeController.getInstance().getCurrentTheme();
        JColorChooser colorChooser = new JColorChooser();
        Color choosen = colorChooser.showDialog(null, "Select a new Color", theme.getDefaultTokenColor());

        if (choosen != null && !colors.contains(choosen))
        {

            JButton newColor = new JButton()
            {
                @Override
                public JToolTip createToolTip()
                {
                    JToolTip old = super.createToolTip();

                    MultiLineToolTip tool = new MultiLineToolTip();
                    tool.setTipText(old.getTipText());
                    tool.setToolTipText(old.getToolTipText());
                    tool.setComponent(this);
                    tool.setBackground(theme.getBackgroundColor());
                    tool.setForeground(theme.getForegroundColor());
                    tool.setBorder(new LineBorder(theme.getDefaultTokenColor()));
                    return tool;
                }

            };
            newColor.setSize(Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
            newColor.setName("" + (getComponentCount() - 1));
            newColor.setOpaque(true);
            //newColor.setContentAreaFilled(false);
            newColor.setBackground(choosen);
            newColor.setIcon(theme.getToken());
            newColor.setToolTipText("Left click to remove the color");
            newColor.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    BColorMousePressed(e);
                }
                
            });
            add(newColor, getComponentCount() - 1);
            this.colors.add(choosen);
            this.setSize(this.getPreferredSize());
            doLayout();
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        int rows = (int) Math.ceil((double) (colors.size() + 1) / NUMBER_OF_COLUMNS);
        return new Dimension(Theme.TOKEN_SIZE * NUMBER_OF_COLUMNS + Theme.SPACING * (NUMBER_OF_COLUMNS - 1), Theme.TOKEN_SIZE * rows + Theme.SPACING * (rows - 1));
    }

    public List<Color> getColors()
    {
        return colors;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
