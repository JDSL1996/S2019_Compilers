import org.antlr.v4.runtime.*;
import java.nio.file.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        // read method to get the source code
        File f;
        String file;
        scannerLexer lexer;
        try{
            //try in an inputs folder
            f =  new File("./inputs/"+args[0]);
            file = new String(Files.readAllBytes(f.toPath()));
            lexer = new scannerLexer(new ANTLRInputStream(file));
        } catch (NullPointerException e){
            try{
                //else try in current directory before saying it dosent exist
                f =  new File(args[0]);
                file = new String(Files.readAllBytes(f.toPath()));
                lexer = new scannerLexer(new ANTLRInputStream(file));
            }catch(NullPointerException n){
                System.out.println("Checked current directory and ./inputs, no file found");
                n.printStackTrace();
            }
        }
        
        // printer for output
        PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
        try{
            Token token = lexer.nextToken();
            // while theres more to do
            while(token.getType() != scannerLexer.EOF) {
                // write the type and value found
                writer.println("Token Type: " + lexer.getVocabulary().getSymbolicName(token.getType()));
                writer.println("Value: " + token.getText());
                
                // get more
                token = lexer.nextToken();
            }
        } catch (Exception e) {
                //TODO: handle exception
        } 
        // always close
        finally{
            writer.close();
        }
    }
}