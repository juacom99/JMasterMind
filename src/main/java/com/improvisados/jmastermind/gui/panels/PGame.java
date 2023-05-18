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

import com.improvisados.jmastermind.exceptions.ColumNotFullException;
import com.improvisados.jmastermind.exceptions.InvalidBoardSizeException;
import com.improvisados.jmastermind.exceptions.InvalidSymbolException;
import com.improvisados.jmastermind.gui.Theme;
import com.improvisados.jmastermind.gui.components.DragableJLable;
import com.improvisados.jmastermind.gui.components.DropableJLabel;
import com.improvisados.jmastermind.gui.components.TransferableColor;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import com.improvisados.jmastermind.gui.layouts.CentredRowLayout;
import com.improvisados.jmastermind.logic.Game;
import com.improvisados.jmastermind.logic.GameState;
import com.improvisados.jmastermind.logic.Guess;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author jomartinez
 */
public class PGame extends javax.swing.JLayeredPane
{

    /**
     * Creates new form PGame
     */
    private Game game;

    private Color[] symbols;
    private int themeSymbol;
    private DropableJLabel[] LGuesses;
    private JLabel[] LCode;
    private JLabel dragWitness;
    private JLabel LState;
    private JButton BPlayAgain;

    /**
     *
     * @param g
     * @param symbols
     */
    public PGame(Game g, Color[] symbols)
    {

        if (symbols.length != g.getSymbols())
        {
            throw new InvalidParameterException();
        }

        initComponents();
        this.game = g;
        this.symbols = symbols;
        
       
        
        Theme theme = ThemeController.getInstance().getCurrentTheme();

        int spacing = Theme.SPACING;
        int size = Theme.TOKEN_SIZE;
        

        int codeLength = g.getCodeLength();
        //PCODE

        PCode.setLocation(spacing, spacing);
        PCode.setSize(size * codeLength + (codeLength - 1) * spacing, size);
        PCode.setBackground(theme.getBackgroundColor());

        this.LCode = new JLabel[codeLength];

        JLabel LCode;

        for (int i = 0; i < codeLength; i++)
        {
            LCode = new JLabel();
            LCode.setBounds((size + spacing) * i, 0, size, size);
            LCode.setIcon(theme.getToken());
            LCode.setBackground(theme.getDefaultTokenColor());
            LCode.setOpaque(true);
            this.LCode[i] = LCode;
            PCode.add(LCode);
        }

        PBoard.setLocation(PCode.getX(), PCode.getY() + PCode.getHeight() + 2 * spacing);

        int rows = Math.min(7, g.getGuesses());

        PBoard.setSize((int) ((size + spacing) * codeLength + Math.ceil((double) codeLength / 4) * size), rows * size + (rows - 1) * spacing);
        PBoard.setBackground(theme.getBackgroundColor());


        PGuess.setLocation(PCode.getX(), PBoard.getY() + PBoard.getHeight() + 2 * spacing);
        PGuess.setSize(size * codeLength + (codeLength - 1) * spacing, size);
        PGuess.setBackground(theme.getBackgroundColor());

        DropTargetAdapter dropListener = new DropTargetAdapter()
        {
            @Override
            public void drop(DropTargetDropEvent dtde)
            {
                try
                {
                    OnDrope(dtde);
                }
                catch (UnsupportedFlavorException | IOException ex)
                {
                    Logger.getLogger(PGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        DropableJLabel LGuess;
        this.LGuesses = new DropableJLabel[codeLength];
        MouseAdapter clearListener = new java.awt.event.MouseAdapter()
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                if (evt.getButton() == MouseEvent.BUTTON3)
                {
                    int index = Integer.parseInt(evt.getComponent().getName());
                    Theme theme = ThemeController.getInstance().getCurrentTheme();
                    try
                    {
                        game.clear(index);
                        LGuesses[index].setBackground(theme.getDefaultTokenColor());
                    }
                    catch (ColumNotFullException ex)
                    {
                        Logger.getLogger(PGame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };

        for (int i = 0; i < codeLength; i++)
        {
            LGuess = new DropableJLabel(dropListener);
            LGuess.setName("" + i);
            LGuess.setBounds((size + spacing) * i, 0, size, size);
            LGuess.setIcon(theme.getToken());
            LGuess.setDisabledIcon(theme.getToken());
            LGuess.setBackground(theme.getDefaultTokenColor());
            LGuess.setOpaque(true);
            LGuess.addMouseListener(clearListener);
            this.LGuesses[i] = LGuess;
            PGuess.add(LGuess);
        }

        BCheck.setLocation(PGuess.getX() + PGuess.getWidth() + spacing, PGuess.getY());
        BCheck.setBackground(theme.getBackgroundColor());
        BCheck.setSize(size, size);
        BCheck.setIcon(theme.getCheck());

        PSymbols.setLocation(PCode.getX(), PGuess.getY() + PGuess.getHeight() + 2 * spacing);
        rows = (int) Math.ceil((double) g.getSymbols() / codeLength);
        PSymbols.setSize(PCode.getWidth(), rows * size + (rows - 1) * spacing);
        PSymbols.setBackground(theme.getBackgroundColor());
        PSymbols.setLayout(new CentredRowLayout(codeLength, spacing, size));

        DragGestureListener dragListener = (DragGestureEvent dge) ->
        {
            onDrag(dge);
        };

        DragableJLable LSymbol;
        for (int i = 0; i < this.game.getSymbols(); i++)
        {
            LSymbol = new DragableJLable(this.symbols[i], dragListener);
            LSymbol.setName("" + i);
            LSymbol.setBounds((size + spacing) * i, 0, size, size);
            LSymbol.setIcon(theme.getToken());
            LSymbol.setBackground(symbols[i]);
            LSymbol.setOpaque(true);
            PSymbols.add(LSymbol);
        }
        setOpaque(true);
        setBackground(theme.getBackgroundColor());

        // Close the dialog when Esc is pressed
        String cancelName = "Show";
       // InputMap inputMap = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
       // inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK), cancelName);
        ActionMap actionMap = getActionMap();
        actionMap.put(cancelName, new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showCode();
            }
        });
        
        this.dragWitness=new JLabel();
        this.dragWitness.setSize(Theme.TOKEN_SIZE,Theme.TOKEN_SIZE);
        this.dragWitness.setVisible(true);
        //this.dragWitness.setBorder(new LineBorder(Color.white));
        add(this.dragWitness,0);
        
    }

    private void showCode()
    {
        int[] code = game.getCode();
        for (int i = 0; i < code.length; i++)
        {
            LCode[i].setBackground(symbols[code[i]]);
        }
    }

    private void onDrag(DragGestureEvent dge)
    {
        DragableJLable target = (DragableJLable) dge.getComponent();
        themeSymbol = Integer.parseInt(target.getName());        
         Theme theme = ThemeController.getInstance().getCurrentTheme();        
         Point p=target.getLocationOnScreen();
         SwingUtilities.convertPointFromScreen(p,dragWitness.getParent());

        this.dragWitness.setLocation(p);
        
        this.dragWitness.setVisible(true);
        
        this.dragWitness.setIcon(theme.getTokenWithMask(symbols[themeSymbol]));
        
        
        dge.getDragSource().addDragSourceMotionListener((DragSourceDragEvent dsde) ->
        {
            if (dragWitness!=null && dragWitness.isVisible())
            {
                Point p1 = dsde.getLocation();
                SwingUtilities.convertPointFromScreen(p1, dragWitness.getParent());
                dragWitness.setLocation(p1.x - Theme.TOKEN_SIZE/2, p1.y - Theme.TOKEN_SIZE/2);
            }
        });
        
        dge.getDragSource().addDragSourceListener(new DragSourceAdapter()
        {
            @Override
            public void dragDropEnd(DragSourceDropEvent dsde)
            {
                dragWitness.setVisible(false);
            }
        });
       
       
           
        //dge.getDragSource().startDrag(dge, Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR),theme.getTokenWithMask(symbols[themeSymbol]).getImage(),new Point(Theme.TOKEN_SIZE/2,Theme.TOKEN_SIZE/2), new TransferableColor(symbols[themeSymbol]), target);
        dge.getDragSource().startDrag(dge, Cursor.getDefaultCursor(),new TransferableColor(symbols[themeSymbol]), target);
        
    }

    private void OnDrope(DropTargetDropEvent dtde) throws UnsupportedFlavorException, IOException
    {
        Color c = null;

        DropableJLabel target = (DropableJLabel) dtde.getDropTargetContext().getComponent();
        
        if (target.isDropEnable())
        {
            try
            {
                c = (Color) dtde.getTransferable().getTransferData(TransferableColor.colorFlavor);

                int col = Integer.parseInt(target.getName());

                game.putSymbol(col, themeSymbol);
                target.setBackground(c);
                themeSymbol = -1;
                this.dragWitness.setVisible(false);

            }
            catch (ColumNotFullException | InvalidSymbolException ex)
            {
                Logger.getLogger(PGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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

        PCode = new javax.swing.JPanel();
        PBoard = new BoardPanel(7);
        PSymbols = new javax.swing.JPanel();
        PGuess = new javax.swing.JPanel();
        BCheck = new javax.swing.JButton();

        PCode.setPreferredSize(new java.awt.Dimension(100, 45));

        javax.swing.GroupLayout PCodeLayout = new javax.swing.GroupLayout(PCode);
        PCode.setLayout(PCodeLayout);
        PCodeLayout.setHorizontalGroup(
            PCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        PCodeLayout.setVerticalGroup(
            PCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        add(PCode);
        PCode.setBounds(0, 0, 20, 20);

        javax.swing.GroupLayout PBoardLayout = new javax.swing.GroupLayout(PBoard);
        PBoard.setLayout(PBoardLayout);
        PBoardLayout.setHorizontalGroup(
            PBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PBoardLayout.setVerticalGroup(
            PBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        add(PBoard);
        PBoard.setBounds(0, 25, 20, 20);

        javax.swing.GroupLayout PSymbolsLayout = new javax.swing.GroupLayout(PSymbols);
        PSymbols.setLayout(PSymbolsLayout);
        PSymbolsLayout.setHorizontalGroup(
            PSymbolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PSymbolsLayout.setVerticalGroup(
            PSymbolsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        add(PSymbols);
        PSymbols.setBounds(360, 10, 20, 20);

        javax.swing.GroupLayout PGuessLayout = new javax.swing.GroupLayout(PGuess);
        PGuess.setLayout(PGuessLayout);
        PGuessLayout.setHorizontalGroup(
            PGuessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        PGuessLayout.setVerticalGroup(
            PGuessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        add(PGuess);
        PGuess.setBounds(360, 40, 20, 20);

        BCheck.setContentAreaFilled(false);
        BCheck.setOpaque(true);
        BCheck.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BCheckActionPerformed(evt);
            }
        });
        add(BCheck);
        BCheck.setBounds(280, 210, 80, 9);
    }// </editor-fold>//GEN-END:initComponents

    private void BCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCheckActionPerformed
        try
        {
            Guess g = this.game.check();
            PGuess guessPanel = new PGuess(g, symbols);
            guessPanel.setSize(PBoard.getWidth(), Theme.TOKEN_SIZE);
            Theme theme = ThemeController.getInstance().getCurrentTheme();

            PBoard.add(guessPanel);

            if (null != this.game.getState() && this.game.getState() != GameState.PLAYING)
            {
                gameEnded(this.game.getState());
            }
            else
            {
                for (DropableJLabel LGuesse : LGuesses)
                {
                    LGuesse.setBackground(theme.getDefaultTokenColor());
                }
            }

        }
        catch (ColumNotFullException ex)
        {

        }

    }//GEN-LAST:event_BCheckActionPerformed

    private void gameEnded(GameState state)
    {

        int[] code = this.game.getCode();
        Theme theme = ThemeController.getInstance().getCurrentTheme();
        for (int i = 0; i < code.length; i++)
        {
            LCode[i].setBackground(this.symbols[code[i]]);
            LGuesses[i].setDropEnable(false);
            LGuesses[i].setBackground(theme.getDefaultTokenColor());
        }
        BCheck.setEnabled(false);

        this.LState = new JLabel();
        LState.setSize(250, 312);
        LState.setLocation(PBoard.getX() + (PCode.getWidth() - LState.getWidth()) / 2, PBoard.getY() + (PBoard.getHeight() - LState.getHeight()) / 2);
       
        if (state == GameState.WON)
        {

            LState.setIcon(theme.getWon());
        }
        else if (state == GameState.LOST)
        {
            LState.setIcon(theme.getLost());

        }

        add(LState, 2, 0);
        
        this.BPlayAgain=new JButton("Play Again");
        BPlayAgain.setSize(100,Theme.TOKEN_SIZE);
        BPlayAgain.setLocation(LState.getX()+(LState.getWidth()-BPlayAgain.getWidth())/2,LState.getY()+LState.getHeight()+Theme.SPACING);
        BPlayAgain.setContentAreaFilled(false);
        BPlayAgain.setOpaque(true);
        BPlayAgain.setBackground(theme.getBackgroundColor());
        BPlayAgain.setForeground(theme.getForegroundColor());
        BPlayAgain.setBorder(new LineBorder(theme.getForegroundColor()));
        BPlayAgain.addActionListener((ActionEvent e) ->
        {
            BPlayagainActionPerformed(e);
        });
        add(BPlayAgain,2, 0);

    }

     private void BPlayagainActionPerformed(java.awt.event.ActionEvent evt)
     {
           
             
        try
        {
            Theme theme = ThemeController.getInstance().getCurrentTheme();
            game.restartGame();
            BCheck.setEnabled(true);
             remove(LState);
            remove(BPlayAgain);
            ((BoardPanel)PBoard).resetComponent();
             for (int i = 0; i < this.LCode.length; i++)
            {
                LCode[i].setBackground(theme.getDefaultTokenColor());
                LGuesses[i].setBackground(theme.getDefaultTokenColor());
                LGuesses[i].setDropEnable(true);
            }
             
             repaint();
        }
        catch (InvalidBoardSizeException ex)
        {
            Logger.getLogger(PGame.class.getName()).log(Level.SEVERE, null, ex);
        }
     }                       
    
    /**
     *
     * @return
     */
    @Override
    public Dimension getMinimumSize()
    {
        Theme theme = ThemeController.getInstance().getCurrentTheme();
        int spacing = Theme.SPACING;
        int size = Theme.TOKEN_SIZE;
        int codeLength = this.game.getCodeLength();

        int w = (int) ((size + spacing) * codeLength + Math.ceil((double) codeLength / 4) * size) + 2 * spacing;
        int h = PCode.getHeight() + PBoard.getHeight() + PGuess.getHeight() + PSymbols.getHeight() + 8 * spacing;

        return new Dimension(w, h); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCheck;
    private javax.swing.JPanel PBoard;
    private javax.swing.JPanel PCode;
    private javax.swing.JPanel PGuess;
    private javax.swing.JPanel PSymbols;
    // End of variables declaration//GEN-END:variables
}
