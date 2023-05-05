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

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import javax.swing.JLabel;

public class DropableJLabel extends JLabel
{

    private DropTarget target;
    private boolean dropEnable;

    public DropableJLabel(DropTargetListener listener)
    {
        this("",listener);
        dropEnable=true;
    }
    
    public DropableJLabel(String text,DropTargetListener listener )
    {
        super(text);
        dropEnable=true;
        target = new DropTarget(this, listener);
        setOpaque(true);
    }
    
    public boolean isDropEnable()
    {
        return dropEnable;
    }

    public void setDropEnable(boolean enable)
    {
        this.dropEnable=enable;
    }
  
}
