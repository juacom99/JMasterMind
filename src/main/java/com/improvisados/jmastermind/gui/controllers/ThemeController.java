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
 */

package com.improvisados.jmastermind.gui.controllers;

import com.improvisados.jmastermind.gui.Theme;
import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Joaquin Martinez <juacom04@gmail.com>
 */
public class ThemeController {

    private static ThemeController instance;
    private static final Theme DEFAULT_THEME = loadDefault();

    private List<Theme> themes;
    private Theme current;


    public boolean add(Theme theme) {
        boolean ret=false;
        if (!themes.contains(theme)) {
            themes.add(theme);
            ret=true;
        }
        
        return ret;
    }

    public void remove(Theme theme) {
        themes.remove(theme);
    }

    public Theme find(String name)
    {
        Theme ret=null;
        
        for(Theme theme:this.themes)
        {
            if(theme.getName().equalsIgnoreCase(name))
            {
                ret=theme;
                break;
            }
        }
        
        return ret;
    }
    public void remove(int index) {
        themes.remove(index);
    }

    public Theme getCurrentTheme() {
        Theme out = DEFAULT_THEME;
        if (current != null) {
            out = current;
        }
        return out;
    }

    public void setCurrent(Theme current) {
        this.current = current;
    }
    
    

    public static ThemeController getInstance() {
        if (instance == null) {
            instance = new ThemeController();
        }
        return instance;
    }

    private static Theme loadDefault() {

        Theme ret = new Theme("Default", "Joaquin Martinez", Color.WHITE, new Color(43, 43, 43), new Color(111, 111, 111));
        Class thisClass = ThemeController.class;

        
        
        ret.setToken(new ImageIcon(thisClass.getResource("/themes/default/token.png")));
        
        ret.setResult(new ImageIcon(thisClass.getResource("/themes/default/result.png")));
        ret.setSelected(new ImageIcon(thisClass.getResource("/themes/default/selected.png")));
        ret.setCheck(new ImageIcon(thisClass.getResource("/themes/default/check.png")));
        ret.setWon(new ImageIcon(thisClass.getResource("/themes/default/won.png")));
        ret.setLost(new ImageIcon(thisClass.getResource("/themes/default/lost.png")));
        ret.setTokenMask(new ImageIcon(thisClass.getResource("/themes/default/tokenMask.png")));
         ret.setAddColor(new ImageIcon(thisClass.getResource("/themes/default/addColor.png")));
        System.out.println(ret + " Loades");

        return ret;
    }

    public void loadThemes(List<String> paths) {
        if (this.themes == null) {
            this.themes=new ArrayList<Theme>();
            for (String path : paths) {
                File file = new File(path);
                if (file != null && file.exists()) {
                    
                    System.out.println("Loading Themes for "+file.getAbsolutePath());
                    
                    String[] directories = file.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File current, String name) {

                            return new File(current, name).isDirectory() && !name.equalsIgnoreCase("default");
                        }
                    });
                    Theme current;
                    if (directories != null) {
                        for (String dir : directories) {
                            current = Theme.load(Paths.get(path +System.getProperty("file.separator")+ dir));
                            if (current != null) {
                                add(current);
                                System.out.println("        "+current + " Loaded");
                            } else {
                                System.out.println("could not load Theme from " + dir);
                            }

                        }
                    }
                }
            }
        }
    }

    public Iterator<Theme> getThemes()
    {
        return themes.iterator();
    }

    public static Theme getDefaultTheme()
    {
        return DEFAULT_THEME;
    }

    
    
    
    
}
