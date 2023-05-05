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

package com.improvisados.jmastermind.logic;

public class Guess
{
    private int[] guess;
    private int blacks;
    private int whites;

    public Guess(int[] guess, int blacks, int whites)
    {
        this.guess = guess.clone();
        this.blacks = blacks;
        this.whites = whites;
    }

    @Override
    public String toString()
    {
        String out="";
        for(int i=0;i<guess.length;i++)
        {
            out+=guess[i]+" ";
        }
        out+="B: "+blacks+" W: "+whites;
        
        return out;
    }

    public int[] getGuess()
    {
        return guess;
    }

    public int getBlacks()
    {
        return blacks;
    }

    public int getWhites()
    {
        return whites;
    }
}
