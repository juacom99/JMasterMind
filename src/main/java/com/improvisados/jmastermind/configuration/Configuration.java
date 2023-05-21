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

package com.improvisados.jmastermind.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.improvisados.jmastermind.gui.Theme;
import com.improvisados.jmastermind.gui.controllers.ThemeController;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration
{
    //private List<String> themePaths;
    private Color[] colors;
    private String currentTheme;
    private boolean firstRun;

    private static Configuration instance;
    
    private static final Color[] DEFAULT_COLORS={
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.YELLOW,
        new Color(255,128,0),   // Orange
        Color.MAGENTA,
        Color.CYAN,
        new Color(255,140,140), // Light Red
        new Color(140,255,140), // Ligth Green
        new Color(140,140,255), // Ligth Blue
        new Color(255,255,140), // Ligth Yellow
        new Color(255,198,140), // Ligth Orange
        new Color(255,140,255), // Ligth Magenta
        new Color(140,255,255), // Ligth Cyan
        new Color(115,0,0),     // Dark Red
        new Color(0,115,0),     // Dark Green
        new Color(0,0,115),     // Dark Blue
        new Color(115,115,0),   // Dark Yellow
        new Color(115,58,0),    // Dark Orange
        new Color(115,0,115),   // Dark Magenta
        new Color(0,115,115)    // Dark Cyan    
    };

    
    private Configuration() {
        this.colors = DEFAULT_COLORS;
        this.currentTheme = "default";
        this.firstRun = true;
    } 
    

    public static Configuration getInstance() {
        if(instance==null)
        {
             

            try {
                JsonReader reader = new JsonReader(new FileReader(new File("./config.jmmc")));
                GsonBuilder builder=new GsonBuilder();
                builder.registerTypeAdapter(Color.class,new ColorTypeAdapter());
                Gson gson = builder.create();
                instance = gson.fromJson(reader, Configuration.class);
                
                String[] path={"./themes"};
                ThemeController.getInstance().loadThemes(Arrays.asList(path));
                
                if(instance.getCurrentTheme()!=null)
                {
                    ThemeController controller=ThemeController.getInstance();
                    Theme current=controller.find(instance.getCurrentTheme());
                    controller.setCurrent(current);
                }
            } 
            catch (FileNotFoundException ex) {
               
                String[] path={"./themes"};
                ThemeController.getInstance().loadThemes(Arrays.asList(path));
                
                instance=new Configuration();
                instance.save("./config.jmmc");
            }
                    
                
        }
        return instance;
    }

    public Color[] getColors() {
        return colors;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public boolean isFirstRun()
    {
        return firstRun;
    }
    
    public void setColors(Color[] colors)
    {
        this.colors = colors;
    }

    public void setCurrentTheme(String currentTheme)
    {
        this.currentTheme = currentTheme;
    }

    public void setFirstRun(boolean firstRun)
    {
        this.firstRun = firstRun;
    }
       
    
    public void save(String path)
    {
        Writer writer = null;
        try
        {
            writer = new FileWriter(path);
            GsonBuilder builder=new GsonBuilder();
            builder.setPrettyPrinting();
            builder.registerTypeAdapter(Color.class,new ColorTypeAdapter());
            Gson gson = builder.create();
            gson.toJson(this, writer);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
    
    
    public String getThemePath()
    {
        return "./themes";
    }    
}
