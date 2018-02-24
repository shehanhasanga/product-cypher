/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product.cyper.models;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author shehan
 */
public class FileReader {
        ArrayList lineList ;
    public FileReader(File file) throws FileNotFoundException, IOException{ 
        java.io.FileReader fr = new java.io.FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fr);
        lineList = new ArrayList<>();
      
        String currentLine=null; 
        while((currentLine = bufferedReader.readLine()) != null){
            lineList.add(currentLine);
            
        }
//        for (int i=0; i < this.lineList.size(); i++) {
//            System.out.println(this.lineList.get(i));
//            System.out.println("shhean");
//        }
       
    }
    public ArrayList getfilelist(){
        return lineList;
    }
    
}
