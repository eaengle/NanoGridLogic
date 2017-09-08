/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanogridgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Gateway1
 */
public class NanoGridBoard {
    
    char[][] Board;
    Random Rnd = new Random();
    public static final char FillChar = '#';
    public static final char MarkChar = 'X';
    
    NanoGridParameters Settings;
    public NanoGridBoard(NanoGridParameters p){
        Settings= p;
        create(p.Columns,p.Rows);
    }
    public void create(int sz){
        create(sz,sz);
    }
    
    public void create(char[][] board){
        Settings.Columns = board.length;
        Settings.Rows = board[0].length;
        Board = new char[Settings.Columns][ Settings.Rows];
        for(int c =0 ; c< Settings.Columns;c++)
        {
            for(int r = 0;r < Settings.Rows;r++)
            {
                char ch = board[c][r];
                Board[c][r] = ch == '_' ? 0 : ch;
            }
        }
    }
    public void create(int cols,int rows){
        Settings.Columns = cols;
        Settings.Rows =rows;
        Board = new char[cols][rows];
        
        for(int c =0 ; c< cols;c++)
        {
            int cnt =  Rnd.nextInt(Settings.MaxColumnSquares)+1;
            fillCol(cnt,c);
        }
        for(int r = 0;r < rows;r++)
        {
            int cnt =  Rnd.nextInt(Settings.MaxRowSquares)+1;
            fillRow(cnt,r);
        }
    }
    
    public char getCell(int col,int row){
        return Board[col][row];
    }
    
    public Integer[][] getColumnCounts(){
        ArrayList<Integer[]> lst = new ArrayList<>();
        for(int c=0;c < Board.length;c++)
            lst.add(NanoGridBoard.this.getColumnCounts(c));
        Integer[][] ary = new Integer[lst.size()][];
        return lst.toArray(ary);
    }
    public Integer[] getColumnCounts(int col){
        ArrayList<Integer> lst = new ArrayList<>();
        int cnt = 0;
        for(int r=0;r < Board[0].length;r++)
        {
            if (Board[col][r]  == FillChar){
                ++cnt;
            }
            else if (cnt > 0){
                lst.add(cnt);
                cnt = 0;
            }
        }
        if (cnt > 0){
                lst.add(cnt);}
        Integer[] ary = new Integer[lst.size()];
        return lst.toArray(ary);
    }
    
    public Integer[][] getRowCounts(){
        ArrayList<Integer[]> lst = new ArrayList<>();
        for(int r=0;r < Board[0].length;r++)
            lst.add(getRowCounts(r));
        Integer[][] ary = new Integer[lst.size()][];
        return lst.toArray(ary);
    }
    public Integer[] getRowCounts(int row){
        ArrayList<Integer> lst = new ArrayList<>();
        int cnt = 0;
        for(int c=0;c < Board.length;c++)
        {
            if (Board[c][row]  == FillChar){
                ++cnt;
            }
            else if (cnt > 0){
                lst.add(cnt);
                cnt = 0;
            }
        }
          if (cnt > 0){
                lst.add(cnt);}
        Integer[] ary = new Integer[lst.size()];
        return lst.toArray(ary);
    }
    
    private void fillCol(int cnt, int c) {
        fillArray(cnt,Board[c]);
    }
    
    private void fillRow(int cnt, int r) {
        char[] ary = createRowArray(r);
        fillArray(cnt,ary);
        fillRowArray(r,ary);
    }
    
    private void fillArray(int cnt, char[] ary) {
        if (cnt <1){cnt=1;}
        if (cnt>=ary.length){cnt=ary.length-1;}
        while (countCells(ary) < cnt)
        {
            int pos = Rnd.nextInt(ary.length);
            ary[pos] = FillChar;
        }
    }

    private int countCells(char[] c) {
        int cnt=0;
        for(int i=0;i < c.length;i++){
            if (c[i]==FillChar)
                ++cnt;
        }
        return cnt;
    }

    private char[] createRowArray(int r) {
        char[]ary = new char[Board.length];
        for(int c=0;c<Board.length;c++)
        {
            ary[c]=Board[c][r];
        }
        return ary;
    }

    private void fillRowArray(int r, char[] ary) {
        for(int c =0;c<Board.length;c++){
            Board[c][r] = ary[c];
        }
    }

    public int getColumnSize() {
        return Board.length;
    }

    public int getRowSize() {
        return Board[0].length;
    }
    
    char[][] getColumns(){
        return Board;
    }

    

 }
