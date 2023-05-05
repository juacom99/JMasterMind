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
import com.improvisados.jmastermind.gui.controllers.JMMOptionPane;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import com.improvisados.jmastermind.gui.layouts.CentredRowLayout;
import edu.stanford.vis.color.LAB;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JToggleButton;

public class ColorsPickerPane extends javax.swing.JPanel implements ItemListener {

    /**
     * Creates new form ColorsPickerPane
     */
    public List<ItemListener> itemEventChange;

    private List<Color> selected;

    public static final int NUMBER_OF_COLUMNS = 6;
    private int maxSelectionCount;
    private double minColorDifference;

    public ColorsPickerPane(Color[] colors, double minColorDifference, int maxSelectionCount) {
        initComponents();
        this.maxSelectionCount = maxSelectionCount;
        this.minColorDifference = minColorDifference;

        itemEventChange = new ArrayList<ItemListener>();
        selected = new LinkedList<Color>();
        CentredRowLayout layoutmgr = new CentredRowLayout(NUMBER_OF_COLUMNS, Theme.SPACING, Theme.TOKEN_SIZE);
        setLayout(layoutmgr);

        Theme theme = ThemeController.getInstance().getCurrentTheme();
        int rows = (int) Math.ceil((double) (colors.length + 1) / NUMBER_OF_COLUMNS);
        setSize(Theme.TOKEN_SIZE * NUMBER_OF_COLUMNS + Theme.SPACING * (NUMBER_OF_COLUMNS - 1), Theme.TOKEN_SIZE * rows + Theme.SPACING * (rows - 1));
        setBackground(theme.getBackgroundColor());
        JToggleButton BColor;
               
        ActionListener actionperformed=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BColorActionPerformed(e);
            }
        };

        for (int i = 0; i < colors.length; i++) {
            BColor = new JToggleButton();
            BColor.setSize(Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
            BColor.setName("" + i);
            BColor.setOpaque(true);
            BColor.setBackground(colors[i]);
            BColor.setIcon(theme.getToken());
            BColor.setSelectedIcon(theme.getSelected());
            BColor.setRolloverIcon(theme.getToken());
            BColor.addActionListener(actionperformed);
            add(BColor);
        }

    }

    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {     
        
        Theme theme = ThemeController.getInstance().getCurrentTheme();
        JColorChooser colorChooser = new JColorChooser();
                Color choosen = colorChooser.showDialog(null, "Select a new Color", new Color(255, 51, 0));
                
                if(choosen!=null)
                {

                JToggleButton newColor = new JToggleButton();
                newColor.setSize(Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
                newColor.setName("" + (getComponentCount()-1));
                newColor.setOpaque(true);
                newColor.setBackground(choosen);
                newColor.setIcon(theme.getToken());
                newColor.setSelectedIcon(theme.getSelected());
                newColor.setRolloverIcon(theme.getToken());
                newColor.addItemListener(this);
                add(newColor,getComponentCount()-1);
                doLayout();
                }
    }
    
    
     private void BColorActionPerformed(java.awt.event.ActionEvent evt) 
     {
         JToggleButton source = (JToggleButton) evt.getSource();
                Color c = source.getBackground();
                
                if(source.isSelected())
                {
                    if (selected.size() < maxSelectionCount) {
                        Color toAdd = isColorDifferent(c);

                        

                        if (toAdd==null) {
                            selected.add(c);
                        } else {
                            //color too similar
                            source.setSelected(false);
                            
                            
                         JMMOptionPane.showSimilarColorDialog(this, c,toAdd,"Color too similar");
                        }

                    } else {
                        //too many colors selected
                        source.setSelected(false);
                    }
                }
                else{
                    selected.remove(c);
                }
     }
     
     
    private Color isColorDifferent(Color c) {
       
        LAB lab1=LAB.fromRGB(c,1);
        
        Color ret = null;
        
        LAB lab2;
        Color selectedColor;
        double d;
        for (int i = 0; i < selected.size() && ret==null; i++) {
            selectedColor = selected.get(i);
            lab2 = LAB.fromRGB(selectedColor, 1);
            d = LAB.ciede2000(lab1, lab2);
            if(d < this.minColorDifference)
            {
                ret=selectedColor;
            }

        }
        return ret;

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

    public List<Color> getSelected() {
        return selected;
    }

    public int getSelectedCount() {
        return selected.size();
    }

    public void addItemChangeEventListener(ItemListener event) {
        itemEventChange.add(event);
    }

    public void removeItemChangeEventListener(ItemListener event) {
        itemEventChange.remove(event);
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
         JToggleButton source = (JToggleButton) e.getSource();
                Color c = source.getBackground();
                
                System.out.println(e.getID());

                if (e.getStateChange() == ItemEvent.SELECTED ) {

                    if (selected.size() < maxSelectionCount) {
                        Color toAdd = isColorDifferent(c);

                        

                        if (toAdd==null) {
                            selected.add(c);
                        } else {
                            //color too similar
                            source.setSelected(false);
                            
                            
                          JMMOptionPane.showSimilarColorDialog(null, c,toAdd,"Color too similar");

                        }

                    } else {
                        //too many colors selected
                        source.setSelected(false);
                    }

                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    selected.remove(c);
                }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
