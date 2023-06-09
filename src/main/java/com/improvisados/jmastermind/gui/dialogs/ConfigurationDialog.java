/*
 * Copyright (C) 2019 Joaquin Martinez <juacom04@gmail.com>
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
package com.improvisados.jmastermind.gui.dialogs;

import com.improvisados.jmastermind.configuration.Configuration;
import com.improvisados.jmastermind.gui.Theme;
import com.improvisados.jmastermind.gui.controllers.JMMOptionPane;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import com.improvisados.jmastermind.gui.panels.ColorSelectionPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.lingala.zip4j.ZipFile;


public class ConfigurationDialog extends JMMDialog
{

    private JList<Theme> LThemes;
    private ColorSelectionPanel colorSelection;

    /**
     * Creates new form ConfigurationDialog
     */
    public ConfigurationDialog(java.awt.Frame parent, boolean modal)
    {
        super(JOptionPane.OK_CANCEL_OPTION, parent, modal);
        initComponents();

        Theme theme = ThemeController.getInstance().getCurrentTheme();

        colorSelection = new ColorSelectionPanel();
        
        JLabel LSelectTheme=new JLabel("Select Theme:");
        LSelectTheme.setForeground(Color.white);
        LSelectTheme.setSize(150,Theme.SPACING);
         LSelectTheme.setLocation(Theme.SPACING, Theme.SPACING);
         LSelectTheme.setForeground(theme.getForegroundColor());
        this.add(LSelectTheme);

        LThemes = new JList<Theme>();
        LThemes.setBackground(theme.getBackgroundColor());
        LThemes.setForeground(theme.getForegroundColor());
        LThemes.setBorder(new LineBorder(theme.getDefaultTokenColor()));

        DefaultListModel<Theme> themesModel = new DefaultListModel<Theme>();
        Iterator<Theme> themes = ThemeController.getInstance().getThemes();       

        while (themes.hasNext())
        {
            themesModel.addElement(themes.next());
        }

        LThemes.setModel(themesModel);

        LThemes.setSize(colorSelection.getWidth(), 170);
        LThemes.setLocation(Theme.SPACING, 3*Theme.SPACING);

        LThemes.setSelectedValue(ThemeController.getInstance().getCurrentTheme(), true);
        add(LThemes);

        JButton BAddTheme = new JButton();
        BAddTheme.setSize(19, 19);
        BAddTheme.setBackground(theme.getDefaultTokenColor());
        BAddTheme.setForeground(theme.getBackgroundColor());
        BAddTheme.setContentAreaFilled(false);
        BAddTheme.setIcon(theme.getAddColor());
        BAddTheme.setLocation(LThemes.getX() + LThemes.getWidth() - BAddTheme.getWidth(), LThemes.getY() + LThemes.getHeight() + Theme.SPACING);
        BAddTheme.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BAddThemeActionPerformed(e);

            }
        });

        add(BAddTheme);
        colorSelection.setLocation(Theme.SPACING, BAddTheme.getY() + BAddTheme.getHeight() +3* Theme.SPACING);

        colorSelection.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                ColorSelectionComponentResized(e);
            }

        });

        add(colorSelection);
        
        JLabel LSelectColors=new JLabel("Select Colors:");
        LSelectColors.setLocation(colorSelection.getX(),colorSelection.getY()-2*Theme.SPACING);
        LSelectColors.setSize(150,Theme.SPACING);
        LSelectColors.setForeground(theme.getForegroundColor());
        this.add(LSelectColors);

        
        
        setSize(LThemes.getWidth() + 2 * Theme.SPACING, 9 * Theme.SPACING + LThemes.getHeight() + colorSelection.getHeight() + 23 + Theme.TOKEN_SIZE);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentResized
    {//GEN-HEADEREND:event_formComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentResized

    private void ColorSelectionComponentResized(java.awt.event.ComponentEvent evt)
    {
        setSize(LThemes.getWidth() + 2 * Theme.SPACING, 6 * Theme.SPACING + LThemes.getHeight() + evt.getComponent().getHeight() + 23 + Theme.TOKEN_SIZE);
    }

    private void BAddThemeActionPerformed(java.awt.event.ActionEvent evt)
    {
        JFileChooser fChooser = new JFileChooser();

        fChooser.setFileFilter(new FileNameExtensionFilter("JMasterMind Theme File", "jmmt"));

        int ret = fChooser.showOpenDialog(this);
        
        File fTheme=fChooser.getSelectedFile();

        if (ret == JOptionPane.YES_OPTION && fTheme!= null)
        {
            System.out.println("Loading Theme " + fTheme);

            try
            {
                String fileNameWithOutExt = fTheme.getName().replaceFirst("\\.jmmt", "");
                String to=Configuration.getInstance().getThemePath()+File.separator+fileNameWithOutExt;
                
                File fTo=new File(to);
                
                if(fTo.createNewFile())
                {
                     ZipFile zipFile = new ZipFile(fTheme.getAbsoluteFile());
                    zipFile.extractAll(to);
                
                
                
                Theme newTheme=Theme.load(Paths.get(to+File.separator+fileNameWithOutExt));
                
                if(ThemeController.getInstance().add(newTheme))
                {
                    ((DefaultListModel<Theme>)LThemes.getModel()).addElement(newTheme);
                }
                else
                {
                    JMMOptionPane.showMessageDialog(this,"This Theme already exist", "Theme add", JOptionPane.CLOSED_OPTION);
                }
                }
            }
            catch (net.lingala.zip4j.exception.ZipException ex)
            {
               JMMOptionPane.showMessageDialog(this,"Corrupted theme file", "Theme add", JOptionPane.CLOSED_OPTION);
            }
            catch (IOException ex)
            {
                JMMOptionPane.showMessageDialog(this,"Unable to create file folder", "Theme add", JOptionPane.CLOSED_OPTION);
            }
           

        }
    }

    public Theme getTheme()
    {
        Theme ret = null;
        if (LThemes.getSelectedIndex() != -1)
        {
            ret = LThemes.getSelectedValue();
        }

        return ret;
    }

    public List<Color> getColors()
    {
        return colorSelection.getColors();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
