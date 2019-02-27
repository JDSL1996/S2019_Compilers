import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.*;
import java.io.*;

public class Driver2 {
    public static void main(String[] args) {
        // read method to get the source code
        File f;
        String file;
        scannerLexer lexer;
        boolean done = false;
        try {
            f = new File(args[0]);
            file = new String(Files.readAllBytes(f.toPath()));
            lexer = new scannerLexer(new ANTLRInputStream(file));

            CommonTokenStream cts = new CommonTokenStream(lexer);
            scannerParser parser = new scannerParser(cts);

            parser.setErrorHandler(new BailErrorStrategy());
            try {
                parser.program();
                System.out.println("Accepted");
            } catch (Exception e) {
                System.out.println("Not accepted");
            }
            // always close
            finally {
                done = true;
            }
        } catch (NullPointerException e) {
            System.out.println("No file found");
        } catch (FileNotFoundException fnfe) {
            System.out.println("No file found");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}