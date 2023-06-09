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

package com.improvisados.jmastermind.gui;

import com.improvisados.jmastermind.configuration.Configuration;
import com.improvisados.jmastermind.exceptions.InvalidBoardSizeException;
import com.improvisados.jmastermind.exceptions.InvalidSymbolException;
import com.improvisados.jmastermind.gui.controllers.JMMOptionPane;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import com.improvisados.jmastermind.gui.dialogs.AboutDialog;
import com.improvisados.jmastermind.gui.dialogs.ConfigurationDialog;
import com.improvisados.jmastermind.gui.dialogs.NewGameDialog;
import com.improvisados.jmastermind.gui.panels.PGame;
import com.improvisados.jmastermind.logic.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class JMasterMind extends javax.swing.JFrame
{

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form JMasterMind
     */
    private Game game;
    private PGame gamePanel;
    private Point initialClick;
    private JLabel LArrow;
    private JLabel LHelp;
    
    private int x;
    private int y;
    

    public JMasterMind()
    {
        initComponents();

        Theme theme = ThemeController.getInstance().getCurrentTheme();

        getRootPane().setBorder(new LineBorder(theme.getDefaultTokenColor()));
        getContentPane().setBackground(theme.getBackgroundColor());
        setSize(550, 400);
        
        
        Configuration cfg=Configuration.getInstance();
        
        JMenuPanel.setBackground(theme.getMenuBackgroundColor());
        if(cfg.isFirstRun())
        {
        //TEST
            LArrow=new JLabel();
            LArrow.setBounds(14,52,125,70);  
            
            LArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow.png")));
            add(LArrow);
            
            LHelp=new JLabel("<html><body><center>To start a new Game<br/> Click here<center></body></html>");
            LHelp.setFont(LHelp.getFont().deriveFont(18.0f));
            LHelp.setBounds(145,55,250,70);
            LHelp.setForeground(theme.getForegroundColor());
            
            add(LHelp);
        }
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JMenuPanel = new javax.swing.JPanel();
        BNewGame = new javax.swing.JButton();
        BPreferences = new javax.swing.JButton();
        BAbout = new javax.swing.JButton();
        BMinimize = new javax.swing.JButton();
        BClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JMasterMind");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        JMenuPanel.setPreferredSize(new java.awt.Dimension(434, 32));
        JMenuPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                onMopuseDrag(evt);
            }
        });
        JMenuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onMousePress(evt);
            }
        });

        BNewGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newgame.png"))); // NOI18N
        BNewGame.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BNewGame.setBorderPainted(false);
        BNewGame.setContentAreaFilled(false);
        BNewGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BNewGame.setMargin(new java.awt.Insets(14, 8, 2, 14));
        BNewGame.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newgame_p.png"))); // NOI18N
        BNewGame.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newgame_r.png"))); // NOI18N
        BNewGame.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNewGameActionPerformed(evt);
            }
        });

        BPreferences.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/preferences.png"))); // NOI18N
        BPreferences.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BPreferences.setBorderPainted(false);
        BPreferences.setContentAreaFilled(false);
        BPreferences.setFocusPainted(false);
        BPreferences.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BPreferences.setPreferredSize(new java.awt.Dimension(32, 32));
        BPreferences.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/preferences_p.png"))); // NOI18N
        BPreferences.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/preferences_r.png"))); // NOI18N
        BPreferences.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPreferencesActionPerformed(evt);
            }
        });

        BAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about.png"))); // NOI18N
        BAbout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BAbout.setBorderPainted(false);
        BAbout.setContentAreaFilled(false);
        BAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BAbout.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about_p.png"))); // NOI18N
        BAbout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/about_r.png"))); // NOI18N
        BAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAboutActionPerformed(evt);
            }
        });

        BMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize.png"))); // NOI18N
        BMinimize.setContentAreaFilled(false);
        BMinimize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BMinimize.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize_p.png"))); // NOI18N
        BMinimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize_focus.png"))); // NOI18N
        BMinimize.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMinimizeActionPerformed(evt);
            }
        });

        BClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        BClose.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 8));
        BClose.setContentAreaFilled(false);
        BClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BClose.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_p.png"))); // NOI18N
        BClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_f.png"))); // NOI18N
        BClose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JMenuPanelLayout = new javax.swing.GroupLayout(JMenuPanel);
        JMenuPanel.setLayout(JMenuPanelLayout);
        JMenuPanelLayout.setHorizontalGroup(
            JMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMenuPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(BNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                .addComponent(BMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BClose, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        JMenuPanelLayout.setVerticalGroup(
            JMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(BPreferences, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(BAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 256, Short.MAX_VALUE))
        );

        setLocation(new java.awt.Point(0, 0));
    }// </editor-fold>//GEN-END:initComponents

    private void BNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNewGameActionPerformed

        
        Configuration cfg=Configuration.getInstance();
        
        if(cfg.isFirstRun() && LArrow!=null && LHelp!=null)
        {
            remove(LArrow);
            remove(LHelp);
            repaint();
            cfg.setFirstRun(false);            
            cfg.save("./config.jmmc");
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();

        NewGameDialog ngd = new NewGameDialog(this, true);

        if (getX() + getWidth() + Theme.SPACING + ngd.getWidth() < width)
        {
            ngd.setLocation(getX() + getWidth() + Theme.SPACING, getY());

        }
        else
        {
            ngd.setLocationRelativeTo(this);
        }

        ngd.setVisible(true);
        int option = -1;
        if (ngd.getReturnStatus() == JOptionPane.OK_OPTION)
        {

            if (this.game != null && this.game.isPlaying())
            {
                option = JMMOptionPane.showMessageDialog(null, "Do you really want start again?", "New game", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION)
                {
                    this.game = null;

                    this.remove(gamePanel);
                    gamePanel = null;
                }

            }
            else if (gamePanel != null)
            {
                this.remove(gamePanel);
                gamePanel = null;
            }

            if (option == JOptionPane.YES_OPTION || option == -1)
            {
                int codelength = ngd.getCodeLength();
                int guesses = ngd.getGuesses();
                Color[] colors = ngd.getColors();

                try
                {
                    this.game = new Game(guesses, codelength, colors.length, ngd.canRepeate());
                    gamePanel = new PGame(this.game, colors);
                    gamePanel.setName("GP");
                    Dimension size = gamePanel.getMinimumSize();
                    gamePanel.setSize(size);
                    gamePanel.setLocation(0, JMenuPanel.getHeight());
                    add(gamePanel);
                    //setSize((int) size.getWidth() + 8, (int) size.getHeight() + 56);
                    Dimension windowSize=new Dimension((int) size.getWidth() + 8, (int) size.getHeight() + 56);
                
                    super.setSize(windowSize);
                    setPreferredSize(windowSize);
                }
                catch (InvalidBoardSizeException ex)
                {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                catch (InvalidSymbolException ex)
                {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                this.gamePanel.repaint();

                setLocationRelativeTo(null);
            }

        }

    }//GEN-LAST:event_BNewGameActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void BCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BCloseActionPerformed
    {//GEN-HEADEREND:event_BCloseActionPerformed
        if (this.game != null && this.game.isPlaying())
        {
            //warning
            //  int option = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            int option = JMMOptionPane.showMessageDialog(this, "Do you really want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }

        }
        else
        {
            System.exit(0);
        }
    }//GEN-LAST:event_BCloseActionPerformed

    private void BPreferencesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BPreferencesActionPerformed
    {//GEN-HEADEREND:event_BPreferencesActionPerformed
        ConfigurationDialog cd = new ConfigurationDialog(this, true);
        cd.setLocationRelativeTo(this);
        cd.setVisible(true);

        int retState = cd.getReturnStatus();

        if (retState == JOptionPane.OK_OPTION)
        {
            Configuration cfg = Configuration.getInstance();
            Theme selected = cd.getTheme();

            if (selected != null && !ThemeController.getInstance().getCurrentTheme().equals(selected))
            {
                cfg.setCurrentTheme(selected.getName());
                JMMOptionPane.showMessageDialog(this, "You must restart the game to see the new theme", "New theme", JOptionPane.CLOSED_OPTION);
            }

            cfg.setColors(cd.getColors().toArray(new Color[0]));

            cfg.save("./config.jmmc");
        }
    }//GEN-LAST:event_BPreferencesActionPerformed

    private void BAboutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BAboutActionPerformed
    {//GEN-HEADEREND:event_BAboutActionPerformed
        AboutDialog ad = new AboutDialog(this, true);
        ad.setLocationRelativeTo(this);
        ad.setVisible(true);
    }//GEN-LAST:event_BAboutActionPerformed

    private void BMinimizeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BMinimizeActionPerformed
    {//GEN-HEADEREND:event_BMinimizeActionPerformed
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_BMinimizeActionPerformed

    private void onMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onMouseClicked
    {//GEN-HEADEREND:event_onMouseClicked

    }//GEN-LAST:event_onMouseClicked

    private void onMopuseDrag(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onMopuseDrag
    {//GEN-HEADEREND:event_onMopuseDrag
        setLocation(evt.getXOnScreen() - x, evt.getYOnScreen() - y);
    }//GEN-LAST:event_onMopuseDrag

    private void onMousePress(java.awt.event.MouseEvent evt)//GEN-FIRST:event_onMousePress
    {//GEN-HEADEREND:event_onMousePress
       this.x = evt.getX();
       this.y = evt.getY();
    }//GEN-LAST:event_onMousePress
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAbout;
    private javax.swing.JButton BClose;
    private javax.swing.JButton BMinimize;
    private javax.swing.JButton BNewGame;
    private javax.swing.JButton BPreferences;
    private javax.swing.JPanel JMenuPanel;
    // End of variables declaration//GEN-END:variables
}
