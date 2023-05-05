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

import com.improvisados.jmastermind.exceptions.InvalidBoardSizeException;
import java.util.ArrayList;
import java.util.List;


public class Board
{
    private List<Guess> guesses;
    private int maxGuesses;
    

    public Board(int guesses) throws InvalidBoardSizeException
    {
        if(guesses<=0)
        {
            throw new InvalidBoardSizeException("The number of guesses must be at least 1");
        }
        
        this.guesses=new ArrayList<>(guesses);
        this.maxGuesses=guesses;
    }
    
    @Override
    public String toString()
    {
        String out = "";
        String linebreak = System.getProperty("line.separator");
                
        for(Guess guess:guesses)
        {
            out+=guess.toString()+linebreak;
        }
        
        return out;
    }    
    
    public void addGuess(Guess guess)
    {
        if(!isFull())
        {
            this.guesses.add(guess);
        }
    }

    public boolean isFull()
    {
        return this.guesses.size()>this.maxGuesses-1;
    }
    
    
    public int size()
    {
        return maxGuesses;
    }
    
    public void clearAll()
    {
        this.guesses.clear();
    }
}
