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

package com.improvisados.jmastermind.gui.components;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TransferableColor implements Transferable
{
    private Color color;
    public static DataFlavor colorFlavor=new DataFlavor(Color.class,"Java Color Object");
    protected static DataFlavor[] supportedFlavors={new DataFlavor(Color.class,"Java Color Class")};
    
    public TransferableColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
        
    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        boolean ret=false;
        if(flavor.equals(colorFlavor) || flavor.equals(DataFlavor.stringFlavor))
        {
            ret=true;
        }        
        return ret;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
    {
        if(flavor.equals(colorFlavor))
        {
            return color;
        }
        else if(flavor.equals(DataFlavor.stringFlavor))
        {
            return color.toString();
        }
        else
        {
            throw new UnsupportedFlavorException(flavor);
        }
    }
    
}
