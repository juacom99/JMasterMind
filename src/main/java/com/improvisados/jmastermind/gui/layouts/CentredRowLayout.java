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

package com.improvisados.jmastermind.gui.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class CentredRowLayout implements LayoutManager
{

    private int columns;
    private double spacing;
    private int size;

    public CentredRowLayout(int columns, double spacing,int size)
    {
        this.columns = columns;
        this.spacing = spacing;
        this.size=size;
    }

    @Override
    public void addLayoutComponent(String name, Component comp)
    {

    }

    @Override
    public void removeLayoutComponent(Component comp)
    {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent)
    {
        
        int n=parent.getComponentCount();
        double rows = Math.ceil(n / columns);
        return new Dimension( (int)(n*(size+spacing)),(int)(rows*(spacing+size)));
    }

    @Override
    public Dimension minimumLayoutSize(Container parent)
    {
         int n=parent.getComponentCount();
        double rows = Math.ceil(n / columns);
        return new Dimension( (int)(n*(size+spacing)),(int)(n*rows+spacing*(rows-1)));
    }

    @Override
    public void layoutContainer(Container parent)
    {
        int n = parent.getComponentCount();

        double rows = Math.ceil((double)n / columns);

        Component c;
        int row,column;
        int remind;
        int totalSpace;
        int usedSpace;
        int margin;

        for (int i = 0; i < n; i++)
        {            
            column = (i % columns);
            row=i/columns;
            
            if(row!=(rows-1) || rows==1 || n%columns==0)
            {
                 parent.getComponent(i).setLocation((int)(column*(size+spacing)),(int)(row*(size+spacing)));
            
            }
            else
            {
                remind=n%columns;
                totalSpace=(int) (this.columns*(size+spacing));                
                usedSpace=(int) ((remind)*(size+spacing));
                margin=(totalSpace-usedSpace)/2;                
                parent.getComponent(i).setLocation((int)(column*(size+spacing))+margin,(int)(row*(size+spacing)));
            }
        }

    }

}
