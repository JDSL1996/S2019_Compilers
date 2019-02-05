import org.antlr.v4.runtime.*;
import java.nio.file.*;

public class Driver {
  public static void main(String[] args) throws Exception {
    String file = new String(Files.readAllBytes(Paths.get("fibonacci.micro")));
    scanner lexer = new scanner(new ANTLRInputStream(file));
    while(true) {
      Token token = lexer.nextToken();
      if(token.getType() == scanner.EOF) {
        break;
      }
      System.out.println("Token Type: " + lexer.getVocabulary().getSymbolicName(token.getType()));
      System.out.println("Value: " + token.getText());
    }
  }
}