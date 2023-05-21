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

    private ThemeController()
    {
        themes=new ArrayList<>();
    }

    
    

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

   private static Theme loadDefault()
   {
       if(instance==null)
       {
           instance=getInstance();
       }

       
       
        Theme defaultDark = new Theme("Default Dark", "Joaquin Martinez", Color.WHITE, new Color(43, 43, 43), new Color(111, 111, 111),new Color(51,51,51));
        Class thisClass = ThemeController.class;
        
        defaultDark.setToken(new ImageIcon(thisClass.getResource("/themes/Default_Dark/token.png")));
        defaultDark.setResult(new ImageIcon(thisClass.getResource("/themes/Default_Dark/result.png")));
        defaultDark.setSelected(new ImageIcon(thisClass.getResource("/themes/Default_Dark/selected.png")));
        defaultDark.setCheck(new ImageIcon(thisClass.getResource("/themes/Default_Dark/check.png")));
        defaultDark.setCheckAvailable(new ImageIcon(thisClass.getResource("/themes/Default_Dark/check_r_a.png")));
        defaultDark.setWon(new ImageIcon(thisClass.getResource("/themes/Default_Dark/won.png")));
        defaultDark.setLost(new ImageIcon(thisClass.getResource("/themes/Default_Dark/lost.png")));
        defaultDark.setTokenMask(new ImageIcon(thisClass.getResource("/themes/Default_Dark/tokenMask.png")));
        defaultDark.setAddColor(new ImageIcon(thisClass.getResource("/themes/Default_Dark/addColor.png")));
        //System.out.println(defaultDark + " Loades");
        
        instance.add(defaultDark);
        
        Theme defaultLight=new Theme("Default Light","Joaquin Martinez", new Color(43,43,43),new Color(212,212,212), new Color(136,136,136),new Color(204,204,204));
        defaultLight.setToken(new ImageIcon(thisClass.getResource("/themes/Default_Light/token.png")));
        defaultLight.setResult(new ImageIcon(thisClass.getResource("/themes/Default_Light/result.png")));
        defaultLight.setSelected(new ImageIcon(thisClass.getResource("/themes/Default_Light/selected.png")));
        defaultLight.setCheck(new ImageIcon(thisClass.getResource("/themes/Default_Light/check.png")));
        defaultLight.setCheckAvailable(new ImageIcon(thisClass.getResource("/themes/Default_Dark/check_r_a.png")));
        defaultLight.setWon(new ImageIcon(thisClass.getResource("/themes/Default_Light/won.png")));
        defaultLight.setLost(new ImageIcon(thisClass.getResource("/themes/Default_Light/lost.png")));
        defaultLight.setTokenMask(new ImageIcon(thisClass.getResource("/themes/Default_Light/tokenMask.png")));
        defaultLight.setAddColor(new ImageIcon(thisClass.getResource("/themes/Default_Light/addColor.png")));
        instance.add(defaultLight);

        return defaultDark;
    }
    
    public void loadThemes(List<String> paths) {
        if (this.themes == null) {
            this.themes=new ArrayList<Theme>();
        }
            for (String path : paths) {
                File file = new File(path);
                if (file != null && file.exists()) {
                    
                   // System.out.println("Loading Themes for "+file.getAbsolutePath());
                    
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
                               // System.out.println("        "+current + " Loaded");
                            } else {
                                //System.out.println("could not load Theme from " + dir);
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
