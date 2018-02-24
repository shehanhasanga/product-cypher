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
public class decrypt {
    ArrayList lineList ;
    ArrayList decrypted;
    Filewriter wr;
    File decryptoutfile;
    
    String userkey;
    int keys1;
    int keys2;
    int blockkey;
    public decrypt(File file, String key) throws IOException{
        userkey=key;
        FileReader FR=new FileReader(file);
        lineList=FR.getfilelist();
        decrypted=new ArrayList();
        decryptoutfile= new File("src/decryptout.txt");
        this.setkeys();
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
    
    
    
    
    
    
    public void decypt() throws IOException {
        
        for (int i=0; i < this.lineList.size(); i++) {
              decrypted.add(this.desubstitute(depermute((String) lineList.get(i))));
              System.out.println(this.desubstitute(depermute((String) lineList.get(i))));
//             System.out.println(this.permute(substitute((String) lineList.get(i))));
//            System.out.println(this.lineList.get(i));
//            System.out.println("shhean");
        }
        wr = new Filewriter(decryptoutfile,decrypted);
        this.wr.write();
    }
    private String desubstitute(String text){
        
        int key1 = keys1;
        int key2 = keys2;
        
        StringBuilder builder = new StringBuilder();
        
        for(int i =0; i < text.length(); i++){
            
            char letter = text.charAt(i);
            
            if(i%2 == 0){
                builder.append((char) (letter-key1));
            }
            else{
                builder.append((char) (letter-key2));
            }
        }
        
        return builder.toString();
    }
    private String depermute(String text){
        
        int key = this.blockkey;
        
        int rows = text.length()/key;
        if(!(text.length() % key == 0)){
            rows++;
        }
        int columns = key;       
        
        char matrix[][]=new char[rows][columns];
        
        int a=0;
        for(int j=0;j<columns;j++){
            for(int i=0;i<rows;i++){
                if(a<text.length()){
                    matrix[i][j]=text.charAt(a);
                }
                a++;
            }
        }
           StringBuilder enc_text_builder = new StringBuilder();
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(matrix[i][j]=='\u0000'){
                    continue;
                }else{
                    enc_text_builder.append(matrix[i][j]);
                }
            }
        }
        
        return enc_text_builder.toString();
    }
}
