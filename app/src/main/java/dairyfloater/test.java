package dairyfloater;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class test {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException { 
        PrintWriter writer = new PrintWriter("test.txt");
        writer.println("alex");
        writer.close();
    
    }
}
