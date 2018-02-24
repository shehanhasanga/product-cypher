/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product.cyper.models;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author shehan
 */
public class encrypt {
    ArrayList lineList ;
    ArrayList<String> encrypted ;
    Filewriter wr;
    File encryptfile;
    String userkey;
    int keys1;
    int keys2;
    int blockkey;
    public encrypt(File file,String key) throws IOException{
        FileReader FR=new FileReader(file);
        encryptfile= new File("src/encryptout.txt");
        userkey=key;
        lineList=FR.getfilelist();
        encrypted=new ArrayList();
        this.setkeys();
        
        //encrypting
    
       
    }
    
    private void setkeys(){
         int asci=0;
         int max=0;
         int min=0;
         for(int i=0;i<userkey.length();i++){
         char e = userkey.charAt(i);
         if((int)e>max){
             max=(int)e;
         }
         
         asci=asci+(int)e;
         }
         int average=asci/userkey.length();
         if(max!=0 && average!=0){
        keys1=max;
        keys2=average;
         }else{
             keys1=2;
             keys2=3;
         }
          if(keys2%8>5){
                blockkey=keys2%8;
         }else{
             blockkey=5; 
         }
          System.out.println(blockkey);
          System.out.println(keys1);
          System.out.println(keys2);
    }
    ///simple substitution
    private String substitute(String text){
        int key1 =this.keys1;
        int key2 = this.keys2;
        
        StringBuilder builder = new StringBuilder();
        
        for(int i =0; i < text.length(); i++){
            
            char letter = text.charAt(i);
            
            if(i%2 == 0){
                builder.append((char) (letter+key1));
            }
            else{
                builder.append((char) (letter+key2));
            }
        }
        
        return builder.toString();
    }
   ///permutation 
     private String permute(String text){
        
        int key = this.blockkey ;
        
        int rows = text.length()/key;
        
        if(!(text.length() % key == 0)){
            rows++;
        }
        
        int columns = key;       
        
        char matrix[][]=new char[rows][columns];
        
        int a=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(a<text.length()){
                    matrix[i][j]=text.charAt(a);
                }
                a++;
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int j=0;j<columns;j++){
            for(int i=0;i<rows;i++){
                    builder.append(matrix[i][j]);
            }
        }        
        
        return builder.toString();
    }
     
     
     public void eccryp () throws IOException{
          for (int i=0; i < this.lineList.size(); i++) {
              encrypted.add(this.permute(substitute((String) lineList.get(i))));
              
//             System.out.println(this.permute(substitute((String) lineList.get(i))));
//            System.out.println(this.lineList.get(i));
//            System.out.println("shhean");
        }
          
          for (int i=0; i < this.encrypted.size(); i++) {
            System.out.println(this.encrypted.get(i));
            System.out.println("shhean");
        }
         wr = new Filewriter(encryptfile,encrypted);
         this.wr.write();
     }
    
}
