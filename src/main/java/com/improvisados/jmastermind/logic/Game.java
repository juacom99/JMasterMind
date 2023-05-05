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

import com.improvisados.jmastermind.exceptions.ColumNotFullException;
import com.improvisados.jmastermind.exceptions.InvalidBoardSizeException;
import com.improvisados.jmastermind.exceptions.InvalidSymbolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {

    private Board board;
    private int[] guess;
    private int[] code;
    private int symbols;
    private boolean allowRepetition;
    private GameState state;

    public Game(int guesses, int codeLength, int symbols, boolean allowRepetition) throws InvalidBoardSizeException, InvalidSymbolException {
        if (codeLength < 0) {
            throw new InvalidBoardSizeException("");
        }

        if (codeLength < 0) {
            throw new InvalidBoardSizeException("The ammount of guesses must be at least 1");
        }

        if (symbols < 0) {
            throw new InvalidSymbolException("The ammount of symbols must be al least 1");
        }
        this.board = new Board(guesses);
        this.symbols = symbols;
        this.allowRepetition=allowRepetition;
        this.code = generateCode(codeLength);
        this.guess = new int[codeLength];
        
        this.state = GameState.PLAYING;
        clearGuess();
    }

    private int[] generateCode(int codeLength) throws InvalidBoardSizeException {
        if (!allowRepetition && symbols < codeLength) {
            throw new InvalidBoardSizeException("Not enough symbols to create a code without repetition");
        }

        int[] out = new int[codeLength];
        Random rnd = new Random();
        if (allowRepetition) {
            for (int i = 0; i < out.length; i++) {
                out[i] = rnd.nextInt(symbols);
            }
        } else {
            ArrayList<Integer> symbolsList = new ArrayList<>();
            for (int i = 0; i < symbols; i++) {
                symbolsList.add(i);
            }

            int index = -1;
            for (int i = 0; i < out.length; i++) {
                index = rnd.nextInt(symbolsList.size());
                out[i] = symbolsList.get(index);
                symbolsList.remove(index);
            }
        }

        return out;
    }

    public void clearGuess() {
        Arrays.fill(guess, -1);
    }

    public void putSymbol(int col, int symbol) throws ColumNotFullException, InvalidSymbolException {
        if (col < 0 || col >= this.guess.length) {
            throw new ColumNotFullException("The colum " + col + " can not be found");
        }

        if (0 > symbol || symbol >= this.symbols) {
            throw new InvalidSymbolException("The Symbol is invalid");
        }

        this.guess[col] = symbol;
    }

    public void clear(int col) throws ColumNotFullException
    {
         if (col < 0 || col >= this.guess.length) {
            throw new ColumNotFullException("The colum " + col + " can not be found");
        }
         
         this.guess[col]=-1;
         
    }
    private boolean isGuessFull() {
        boolean out = true;
        for (int i = 0; i < this.guess.length && out; i++) {
            if (this.guess[i] == -1) {
                out = false;
            }
        }

        return out;
    }

    public Guess check() throws ColumNotFullException {
        int blacks = 0;
        int whites = 0;
        Guess out = null;

        if (this.state == GameState.PLAYING) {
            if (!board.isFull()) {
                if (isGuessFull()) {
                    boolean[] used = new boolean[this.guess.length];

                    for (int i = 0; i < code.length; i++) {
                        //if same symbol in same position
                        if (code[i] == this.guess[i]) {
                            blacks++;
                            used[i] = true;
                        } else {
                            for (int j = 0; j < this.guess.length; j++) {
                                if (!used[j] && code[j] != this.guess[j] && code[i] == this.guess[j]) {
                                    whites++;
                                    used[j] = true;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    throw new ColumNotFullException("This column is not full");
                }
                out = new Guess(this.guess, blacks, whites);
                board.addGuess(out);

                if (blacks == code.length) {
                    this.state = GameState.WON;
                } else if (this.board.isFull()) {
                    this.state = GameState.LOST;
                } else {
                    clearGuess();
                }

            }

        }
        return out;
    }

    @Override
    public String toString() {
        String out = "";
        String linebreak = System.getProperty("line.separator");
        for (int i = 0; i < this.code.length; i++) {
            out += this.code[i] + " ";
        }

        out += linebreak + linebreak;
        out += this.board.toString();
        out += linebreak;
        for (int i = 0; i < this.code.length; i++) {
            out += this.guess[i] + " ";
        }
        return out; //To change body of generated methods, choose Tools | Templates.
    }

    public int getCodeLength() {
        return this.code.length;
    }

    public int getGuesses() {
        return board.size();
    }

    public int getSymbols() {
        return symbols;
    }

    public boolean isPlaying() {
        return this.state == GameState.PLAYING;
    }

    public GameState getState()
    {
        return state;
    }

    public int[] getCode()
    {
        return code;
    }
    
    public void restartGame() throws InvalidBoardSizeException
    {
        this.board.clearAll();
        clearGuess();
        this.code=generateCode(this.code.length);
        this.state=GameState.PLAYING;
    }
    
    

}
