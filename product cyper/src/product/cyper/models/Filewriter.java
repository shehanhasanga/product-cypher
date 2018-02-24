/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product.cyper.models;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author shehan
 */
public class Filewriter {
    ArrayList<String>  lineList ;
    BufferedWriter bw;
    public Filewriter(File file, ArrayList<String> lines) throws FileNotFoundException, IOException{
        java.io.FileWriter fr = new java.io.FileWriter(file, false);
         bw = new BufferedWriter(fr);
        lineList = lines;
        
        
         
    }
    public void write() throws IOException{
        for(String line: lineList){
            System.out.println(line);
            bw.write(line);
            bw.newLine();
           
        }
         bw.close();
    }
}
