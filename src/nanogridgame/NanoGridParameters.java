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
        MaxColumnSquares =1;
        MaxRowSquares =1;
    }
    public int Columns;
    public int Rows;
    public int MaxColumnSquares;
    public int MaxRowSquares;

    public NanoGridParameters(NanoGridParameters p) {
        Columns = p.Columns;
        Rows = p.Rows;
        MaxColumnSquares = p.MaxColumnSquares;
        MaxRowSquares = p.MaxRowSquares;
        }
}
