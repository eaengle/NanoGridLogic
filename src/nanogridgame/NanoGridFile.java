/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nanogridgame;

import java.io.File;
import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Gateway1
 */

public class NanoGridFile implements Serializable{
    
    
    public NanoGridParameters Settings;
    public NanoGridBoard Board;

    NanoGridFile(NanoGridParameters settings, NanoGridBoard board) {
        Settings = settings;
        Board = board;
    }
    NanoGridFile(){
        
    }
    public void Serialize(File output){
       try{
        DocumentBuilderFactory dbFactory =
         DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         org.w3c.dom.Document doc = dBuilder.newDocument();
         
         // root element
         org.w3c.dom.Element rootElement = doc.createElement("NanoGame");
         doc.appendChild(rootElement);
         
         serialize(Settings,rootElement,doc);
         serialize(Board,rootElement,doc);
         
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult result = new StreamResult(output);
         transformer.transform(source, result);
       }
       catch(Exception ex){
           System.out.println(ex);
       }

    }

    private void serialize(NanoGridParameters settings, Element root,org.w3c.dom.Document doc) {
        org.w3c.dom.Element e = doc.createElement("Settings");
        root.appendChild(e);
        addValue(settings.Columns,"Columns",e,doc);
        addValue(settings.Rows,"Rows",e,doc);
        addValue(settings.MaxColumnSquares,"MaxColumnSquares",e,doc);
        addValue(settings.MaxRowSquares,"MaxRowSquares",e,doc);
        
    }

    private void serialize(NanoGridBoard board, Element root,Document doc) {
        char[][] columns = Board.getColumns();
        org.w3c.dom.Element cols = doc.createElement("Board");
        root.appendChild(cols);
        for(char[]col : columns){
            serializeColumn(col,cols,doc);
        }
    }

    private void addValue(int val,String name, Element parent, Document doc) {
        org.w3c.dom.Element child = doc.createElement(name);
        child.appendChild(doc.createTextNode(String.valueOf(val)));
        parent.appendChild(child);
    }

    private void serializeColumn(char[] col, Element parent, Document doc) {
        org.w3c.dom.Element cols = doc.createElement("Columns");
        parent.appendChild(cols);
    }

}
