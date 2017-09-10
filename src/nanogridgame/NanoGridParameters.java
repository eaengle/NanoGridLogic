/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanogridgame;

/**
 *
 * @author Gateway1
 */
public class NanoGridParameters {
    public NanoGridParameters(){
        MaxColumnSquares =5;
        MaxRowSquares =5;
        RowBreakChance = 50;
        Columns =10;
        Rows = 10;
    }
    public int Columns;
    public int Rows;
    public int MaxColumnSquares;
    public int MaxRowSquares;
    public int RowBreakChance;
    public NanoGridParameters(NanoGridParameters p) {
        Columns = p.Columns;
        Rows = p.Rows;
        MaxColumnSquares = p.MaxColumnSquares;
        MaxRowSquares = p.MaxRowSquares;
        RowBreakChance = p.RowBreakChance;
        }
}
