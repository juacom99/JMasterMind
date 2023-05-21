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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.improvisados.jmastermind.configuration.ColorTypeAdapter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Theme {

    private String name;
    private String author;
    public static final int TOKEN_SIZE = 38;
    public static final int SPACING = 16;
    private Color backgroundColor;
    private Color defaultTokenColor;
    private Color foregroundColor;
    private Color menuBackgroundColor;
    private transient ImageIcon token;
    private transient ImageIcon result;
    private transient ImageIcon check;
    private transient ImageIcon checkUnavailable;
    private transient ImageIcon checkAvailable;
    private transient ImageIcon won;
    private transient ImageIcon lost;
    private transient ImageIcon selected;
    private transient ImageIcon tokenMask;
    private transient ImageIcon addColor;

    private String path;

    public Theme(String name, String author, Color foregroundColor, Color backgroundColor, Color defaultTokenColor,Color menuBackgroundColor) {
        this.name = name;
        this.author = author;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.defaultTokenColor = defaultTokenColor;
        this.menuBackgroundColor=menuBackgroundColor;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getDefaultTokenColor() {
        return defaultTokenColor;
    }
    
    public Color getMenuBackgroundColor() {
        return menuBackgroundColor;
    }

    public ImageIcon getToken() {
        if (token == null) {
            this.token = new javax.swing.ImageIcon(this.path + File.separator + "token.png");

        }
        return token;
    }

    public ImageIcon getWon() {
        if (won == null) {
            this.won = new javax.swing.ImageIcon(this.path + File.separator + "won.png");

        }
        return won;
    }

    public ImageIcon getLost() {
        if (lost == null) {
            this.lost = new javax.swing.ImageIcon(this.path + File.separator + "lost.png");
        }
        return lost;
    }

    public ImageIcon getResult() {
        if (result == null) {
            this.result = new javax.swing.ImageIcon(this.path + File.separator + "result.png");

        }
        return result;
    }

    public ImageIcon getSelected() {
        if (selected == null) {
            this.selected = new javax.swing.ImageIcon(this.path + File.separator + "selected.png");
        }
        return selected;
    }

    public ImageIcon getCheck() {
        if (check == null) {
            this.check = new javax.swing.ImageIcon(this.path + File.separator + "check.png");
        }
        return check;
    }

    public ImageIcon getCheckAvailable() {
        if(checkAvailable==null)
        {
             this.checkAvailable = new javax.swing.ImageIcon(this.path + File.separator + "check_r_a.png");
        }
        return checkAvailable;
    } 

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Theme load(Path folder) {

        Theme ret = null;
        if (folder != null && folder.toFile().exists())
        {
            File f = folder.toFile();
            String[] files = f.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.equals("theme.jmmt") || name.equals("token.png") || name.equals("result.png") || name.equals("check.png") || name.equals("selected.png") || name.equals("won.png") || name.equals("lost.png") || name.equals("tokenMask.png") || name.equals("addColor.png") || name.equals("check_r_a.png");
                }
            });

            if (files != null && files.length == 10) {
                try {

                    JsonReader reader = new JsonReader(new FileReader(new File(f.getAbsolutePath() + File.separator + "theme.jmmt")));
                    Gson gson = new GsonBuilder().registerTypeAdapter(Color.class,new ColorTypeAdapter()).create();
                    ret = gson.fromJson(reader, Theme.class);
                    ret.setPath(f.getAbsolutePath());
                    ret.setName(f.getName());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Theme.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        else
        {
            throw new InvalidParameterException();
        }

        return ret;
    }

    public void setToken(ImageIcon token) {
        this.token = token;
    }

    public void setResult(ImageIcon result) {
        this.result = result;
    }

    public void setCheck(ImageIcon check) {
        this.check = check;
    }

    public void setCheckAvailable(ImageIcon checkAvailable) {
        this.checkAvailable = checkAvailable;
    }

  

    public void setWon(ImageIcon won) {
        this.won = won;
    }

    public void setLost(ImageIcon lost) {
        this.lost = lost;
    }

    public void setAddColor(ImageIcon addColor) {
        this.addColor = addColor;
    }
    
    

    public void setSelected(ImageIcon selected) {
        this.selected = selected;
    }

    public void setTokenMask(ImageIcon tokenMask) {
        this.tokenMask = tokenMask;
    }

    public ImageIcon getTokenMask() {
        if (tokenMask == null) {
            this.tokenMask = new javax.swing.ImageIcon(this.path + File.separator + "tokenMask.png");
        }
        return tokenMask;
    }

    public ImageIcon getAddColor() {
        if (addColor == null) {
            this.addColor = new javax.swing.ImageIcon(this.path + File.separator + "addColor.png");
        }
        
        return addColor;
    }
    
    public ImageIcon getTokenWithMask(Color bg) {
        BufferedImage BToken = new BufferedImage(getToken().getIconWidth(), getToken().getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        BufferedImage BMask = new BufferedImage(getTokenMask().getIconWidth(), getTokenMask().getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = (Graphics2D) BToken.getGraphics();
        g.drawImage(this.getToken().getImage(), -2,-2, bg, null);

        g = (Graphics2D) BMask.getGraphics();
        g.drawImage(this.getTokenMask().getImage(), -2, -2, null);

        int width = BToken.getWidth();
        int height = BToken.getHeight();

        int[] imagePixels = BToken.getRGB(0, 0, width, height, null, 0, width);
        int[] maskPixels = BMask.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < imagePixels.length; i++) {
            int color = imagePixels[i] & 0x00ffffff; // Mask preexisting alpha
            int alpha = maskPixels[i] << 24; // Shift blue to alpha
            imagePixels[i] = color | alpha;
        }

        BToken.setRGB(0, 0, width, height, imagePixels, 0, width);

        return new ImageIcon(BToken);
    }

    @Override
    public String toString() {
        return this.name + " by " + this.author;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean ret=false;
        
        if(obj instanceof Theme)
        {
            ret= this.name.equalsIgnoreCase(((Theme)obj).name);
        }
        
        return ret;
    }
    
    

}
