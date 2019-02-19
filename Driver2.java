import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.*;

import java.io.*;

public class Driver2 {
    public static void main(String[] args) {
        // read method to get the source code
        try{
            File f =  new File(args[0]);
            String file = new String(Files.readAllBytes(f.toPath()));
            scannerLexer lexer = new scannerLexer(new ANTLRInputStream(file));
            
            // printer for output
            File out = new File("out.txt");
            FileWriter fw = new FileWriter(out);
            BufferedWriter writer = new BufferedWriter(fw);
            CommonTokenStream cts = new CommonTokenStream(lexer);
            scannerParser parser = new scannerParser(cts);

            parser.setErrorHandler(new BailErrorStrategy());
            try{
                ParserRuleContext prc = parser.program();
                writer.write("Accepted\n");
            }catch(Exception e){
                System.out.println("Could not Recognize!\n");
                writer.write("Not accepted\n");
                System.exit(1);
            }
            // always close
            finally{
                writer.flush();
                writer.close();
            }
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
    }
}