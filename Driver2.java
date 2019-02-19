import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.*;
import java.io.*;

public class Driver2 {
    public static void main(String[] args) {
        // read method to get the source code
        File f ;
        String file;
        scannerLexer lexer;
        boolean done = false;
        while(!done){
            try{
                f =  new File("./inputs/"+args[0]);
                file = new String(Files.readAllBytes(f.toPath()));
                lexer = new scannerLexer(new ANTLRInputStream(file));
                
                // printer for output
                File out = new File("out.txt");
                FileWriter fw = new FileWriter(out);
                BufferedWriter writer = new BufferedWriter(fw);
                CommonTokenStream cts = new CommonTokenStream(lexer);
                scannerParser parser = new scannerParser(cts);

                parser.setErrorHandler(new BailErrorStrategy());
                try{
                    parser.program();
                    writer.write("Accepted");
                    writer.flush();
                }catch(Exception e){
                    writer.write("Not accepted");
                    writer.flush();
                    System.exit(1);
                }
                // always close
                finally{
                    writer.close();
                }
            }catch (NullPointerException e){
                try{
                    //else try in current directory before saying it dosent exist
                    f =  new File(args[0]);
                    file = new String(Files.readAllBytes(f.toPath()));
                    lexer = new scannerLexer(new ANTLRInputStream(file));
                }catch(NullPointerException n){
                    System.out.println("Checked current directory and ./inputs, no file found");
                    done = true;
                    n.printStackTrace();
                }
                catch(FileNotFoundException fnfe){
                    done = true;
                    System.out.println("Checked current directory and ./inputs, no file found");
                }catch(IOException ioe){
                    done = true;
                    ioe.printStackTrace();
                }
            }catch(FileNotFoundException fnfe){
                done = true;
                System.out.println("Checked current directory and ./inputs, no file found");
            }catch(IOException ioe){
                done = true;
                ioe.printStackTrace();
            }
        }
    }
}