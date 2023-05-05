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
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import javax.swing.JLabel;
import javax.swing.TransferHandler;


public class DragableJLable extends JLabel implements DragSourceListener
{
    
    //marks this JButton as the source of the Drag
    private DragSource source;

    private TransferHandler t;
    
    private Color color;

    public DragableJLable(Color color,DragGestureListener listener)
    {
        this("",color,listener);
    }

    public DragableJLable(String message,Color color,DragGestureListener listener)
    {
        super(message);

        this.color=color;
        t = new TransferHandler("DragableJLable");

        setTransferHandler(t);

        //The Drag will copy the DnDButton rather than moving it
        source = new DragSource();
        source.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, listener);
    }
    

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {}
    @Override
    public void dragOver(DragSourceDragEvent dsde) {}
    public void dropActionchanged(DragSourceDragEvent dsde) {}
    @Override
    public void dragExit(DragSourceEvent dse) 
    {
        
    }

    //when the drag finishes, then repaint the DnDButton
    //so it doesn't look like it has still been pressed down
    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        repaint();
    }

    //when a DragGesture is recognized, initiate the Drag
   /* @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
         source.startDrag(dge, DragSource.DefaultCopyDrop, new TransferableColor(this.color), this);     
    }*/

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde)
    {
        
    }

    public DragSource getSource()
    {
        return source;
    }    
}
