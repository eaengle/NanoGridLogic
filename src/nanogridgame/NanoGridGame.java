/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanogridgame;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import static nanogridgame.NanoGridBoard.FillChar;

/**
 *
 * @author Gateway1
 */
public class NanoGridGame {

    NanoGridBoard Board;
    char[][] PlayBoardColumns;
    char[][] PlayBoardRows;
    NanoGridParameters Settings;

    public NanoGridGame(NanoGridParameters p) {
        Board = new NanoGridBoard(p);
        Settings = p;
        create(p.Columns, p.Rows);
        
    }

    public void create(int cols, int rows) {
        Settings.Columns = cols;
        Settings.Rows = rows;
        Board.create(cols, rows);
        PlayBoardColumns = new char[cols][rows];
        PlayBoardRows = new char[rows][cols];
    }

    public void create(int sz) {
        create(sz, sz);
    }

    public void create() {
        create(Settings.Columns, Settings.Rows);
    }

    public void Execute() {

    }

    public NanoGridBoard getBoard() {
        return Board;
    }

    public void clearCell(int c, int r) {
        PlayBoardColumns[c][r] = 0;
        PlayBoardRows[r][c] = 0;
    }

    public void setCell(int c, int r) {
        PlayBoardColumns[c][r] = NanoGridBoard.FillChar;
        PlayBoardRows[r][c] = NanoGridBoard.FillChar;
    }

    public void setMark(int c, int r) {
        PlayBoardColumns[c][r] = NanoGridBoard.MarkChar;
        PlayBoardRows[r][c] = NanoGridBoard.MarkChar;
    }

    public boolean checkWin() {
        Integer[][] cols = Board.getColumnCounts();
        for (int c = 0; c < cols.length; c++) {
            Integer[] cnts = GetCellCount(PlayBoardColumns[c]);
            if (!areEqual(cnts, cols[c])) {
                return false;
            }
        }

        Integer[][] rows = Board.getRowCounts();
        for (int r = 0; r < rows.length; r++) {
            Integer[] cnts = GetCellCount(PlayBoardRows[r]);
            if (!areEqual(cnts, rows[r])) {
                return false;
            }
        }

        return true;
    }

    private Integer[] GetCellCount(char[] cary) {
        ArrayList<Integer> lst = new ArrayList<>();
        int cnt = 0;
        for (int c = 0; c < cary.length; c++) {
            if (cary[c] == FillChar) {
                ++cnt;
            } else if (cnt > 0) {
                lst.add(cnt);
                cnt = 0;
            }
        }
        if (cnt > 0) {
            lst.add(cnt);
        }
        Integer[] ary = new Integer[lst.size()];
        return lst.toArray(ary);
    }

    private boolean areEqual(Integer[] ary1, Integer[] ary2) {
        if (ary1.length != ary2.length) {
            return false;
        }
        for (int i = 0; i < ary1.length; i++) {
            if (!ary1[i].equals(ary2[i])) {
                return false;
            }
        }
        return true;
    }

    public void setBoard(NanoGridBoard board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void resetBoard(NanoGridBoard board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBoard(File loadFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void resetBoard(File loadFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveGame(File output) {
        NanoGridFile file = new NanoGridFile();
        try{saveGame(file,output);}
        catch (JAXBException ex){
            System.out.println(ex);
        }
    }
    
    public void saveGame(NanoGridFile game, File output) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(nanogridgame.NanoGridFile.class);
        JAXBElement<nanogridgame.NanoGridFile> root = new JAXBElement(new QName("nano_grid_game"),
                nanogridgame.NanoGridFile.class, game);
        JAXBSource source = new JAXBSource(context, root);
        Marshaller serializer = context.createMarshaller();
        serializer.marshal(source, output);

    }

  

}
