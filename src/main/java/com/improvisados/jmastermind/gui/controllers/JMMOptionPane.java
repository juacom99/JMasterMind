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
package com.improvisados.jmastermind.gui.controllers;

import com.improvisados.jmastermind.gui.Theme;
import com.improvisados.jmastermind.gui.dialogs.JMMDialog;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class JMMOptionPane
{
    public static int showMessageDialog(Component parentComponent,String message, String title, int optionType)
    {
         JMMDialog messagePane=new JMMDialog(optionType,null,true);         
         JLabel LMessage=new JLabel(message);
         LMessage.setFont(new java.awt.Font("Dialog", 1, 14));
         LMessage.setSize(LMessage.getPreferredSize());
         LMessage.setLocation(Theme.SPACING,Theme.SPACING);
         LMessage.setForeground(ThemeController.getInstance().getCurrentTheme().getForegroundColor());
         messagePane.add(LMessage);
         messagePane.setSize(Theme.SPACING*3+LMessage.getWidth(),(int)messagePane.getPreferredSize().getHeight());
         messagePane.setLocationRelativeTo(parentComponent);
         messagePane.setVisible(true);
              
         return messagePane.getReturnStatus();
    }
    
    public static int showSimilarColorDialog(Component parentComponent,Color c1,Color c2, String title)
    {
        JMMDialog messagePane=new JMMDialog(JOptionPane.CLOSED_OPTION,null,true);
        Theme theme=ThemeController.getInstance().getCurrentTheme();
        
       // LineBorder brd=new LineBorder(Color.RED);
        
        JLabel LText1=new JLabel();
        LText1.setText("The Color ");
        LText1.setForeground(theme.getForegroundColor());
        LText1.setLocation(Theme.SPACING,Theme.SPACING);
        LText1.setSize(70,Theme.TOKEN_SIZE);
       // LText1.setBorder(brd);
        
        JLabel LColor1=new JLabel(theme.getToken());
        LColor1.setOpaque(true);
        LColor1.setBackground(c1);
        LColor1.setSize(Theme.TOKEN_SIZE,Theme.TOKEN_SIZE);
        LColor1.setLocation(LText1.getX()+LText1.getWidth()+Theme.SPACING,LText1.getY());
        
       // LColor1.setBorder(brd);
        
        JLabel LText2=new JLabel("is too similar to ");
        LText2.setForeground(theme.getForegroundColor());
        LText2.setSize(90,Theme.TOKEN_SIZE);
        LText2.setLocation(LColor1.getX()+LColor1.getWidth()+Theme.SPACING,LColor1.getY());
        
        
      //  LText2.setBorder(brd);
        
        JLabel LColor2=new JLabel(theme.getToken());
        LColor2.setOpaque(true);
        LColor2.setBackground(c2);
        LColor2.setSize(Theme.TOKEN_SIZE,Theme.TOKEN_SIZE);
        LColor2.setLocation(LText2.getX()+LText2.getWidth()+Theme.SPACING,LText2.getY());
     //   LColor2.setBorder(brd);
        
        messagePane.setSize(Theme.SPACING*5+LText1.getWidth()+Theme.TOKEN_SIZE*2+LText2.getWidth(),(int)messagePane.getPreferredSize().getHeight());
        
        messagePane.add(LText1);
        messagePane.add(LColor1);
        messagePane.add(LText2);
        messagePane.add(LColor2);
        messagePane.setLocationRelativeTo(parentComponent);
        messagePane.setVisible(true);
        return messagePane.getReturnStatus();
    }
}
