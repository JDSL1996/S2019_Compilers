// Generated from /Users/joshlosey/Desktop/Hw/2019 Spring/S2019_Compilers/scanner.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class scanner extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KEYWORD=1, IDENTIFIER=2, INTLITERAL=3, FLOATLITERAL=4, STRINGLITERAL=5, 
		COMMENT=6, OPERATOR=7, WHITESPACE=8, NEWLINE=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"KEYWORD", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", 
		"COMMENT", "OPERATOR", "WHITESPACE", "NEWLINE", "NOTCOMMENT", "DIGIT", 
		"LOWERCASE", "UPPERCASE", "LETTER", "CHAR"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "KEYWORD", "IDENTIFIER", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", 
		"COMMENT", "OPERATOR", "WHITESPACE", "NEWLINE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public scanner(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "scanner.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13\u00e4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2z\n\2\3\3"+
		"\6\3}\n\3\r\3\16\3~\3\3\3\3\7\3\u0083\n\3\f\3\16\3\u0086\13\3\3\4\6\4"+
		"\u0089\n\4\r\4\16\4\u008a\3\5\7\5\u008e\n\5\f\5\16\5\u0091\13\5\3\5\3"+
		"\5\6\5\u0095\n\5\r\5\16\5\u0096\3\6\3\6\3\6\6\6\u009c\n\6\r\6\16\6\u009d"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\7\7\u00a6\n\7\f\7\16\7\u00a9\13\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\5\b\u00b2\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00bc"+
		"\n\b\3\t\6\t\u00bf\n\t\r\t\16\t\u00c0\3\t\3\t\3\n\6\n\u00c6\n\n\r\n\16"+
		"\n\u00c7\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00d5"+
		"\n\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\5\17\u00df\n\17\3\20\3\20\5"+
		"\20\u00e3\n\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\2\27"+
		"\2\31\2\33\2\35\2\37\2\3\2\n\4\2//\62;\5\2,,\61\61??\6\2*+..=>@@\5\2\13"+
		"\13\16\17\"\"\3\2\62;\3\2c|\3\2C\\\7\2##%\61<B]a}\u0080\2\u010c\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3y\3\2\2\2\5|\3\2\2\2\7\u0088\3"+
		"\2\2\2\t\u008f\3\2\2\2\13\u0098\3\2\2\2\r\u00a1\3\2\2\2\17\u00bb\3\2\2"+
		"\2\21\u00be\3\2\2\2\23\u00c5\3\2\2\2\25\u00d4\3\2\2\2\27\u00d6\3\2\2\2"+
		"\31\u00d8\3\2\2\2\33\u00da\3\2\2\2\35\u00de\3\2\2\2\37\u00e2\3\2\2\2!"+
		"\"\7R\2\2\"#\7T\2\2#$\7Q\2\2$%\7I\2\2%&\7T\2\2&\'\7C\2\2\'z\7O\2\2()\7"+
		"D\2\2)*\7G\2\2*+\7I\2\2+,\7K\2\2,z\7P\2\2-.\7G\2\2./\7P\2\2/z\7F\2\2\60"+
		"\61\7H\2\2\61\62\7W\2\2\62\63\7P\2\2\63\64\7E\2\2\64\65\7V\2\2\65\66\7"+
		"K\2\2\66\67\7Q\2\2\67z\7P\2\289\7T\2\29:\7G\2\2:;\7C\2\2;z\7F\2\2<=\7"+
		"Y\2\2=>\7T\2\2>?\7K\2\2?@\7V\2\2@z\7G\2\2AB\7K\2\2Bz\7H\2\2CD\7G\2\2D"+
		"E\7N\2\2EF\7U\2\2Fz\7G\2\2GH\7H\2\2Hz\7K\2\2IJ\7H\2\2JK\7Q\2\2Kz\7T\2"+
		"\2LM\7T\2\2MN\7Q\2\2Nz\7H\2\2OP\7T\2\2PQ\7G\2\2QR\7V\2\2RS\7W\2\2ST\7"+
		"T\2\2Tz\7P\2\2UV\7K\2\2VW\7P\2\2Wz\7V\2\2XY\7X\2\2YZ\7Q\2\2Z[\7K\2\2["+
		"z\7F\2\2\\]\7U\2\2]^\7V\2\2^_\7T\2\2_`\7K\2\2`a\7P\2\2az\7I\2\2bc\7H\2"+
		"\2cd\7N\2\2de\7Q\2\2ef\7C\2\2fz\7V\2\2gh\7Y\2\2hi\7J\2\2ij\7K\2\2jk\7"+
		"N\2\2kz\7G\2\2lm\7G\2\2mn\7P\2\2no\7F\2\2op\7K\2\2pz\7H\2\2qr\7G\2\2r"+
		"s\7P\2\2st\7F\2\2tu\7Y\2\2uv\7J\2\2vw\7K\2\2wx\7N\2\2xz\7G\2\2y!\3\2\2"+
		"\2y(\3\2\2\2y-\3\2\2\2y\60\3\2\2\2y8\3\2\2\2y<\3\2\2\2yA\3\2\2\2yC\3\2"+
		"\2\2yG\3\2\2\2yI\3\2\2\2yL\3\2\2\2yO\3\2\2\2yU\3\2\2\2yX\3\2\2\2y\\\3"+
		"\2\2\2yb\3\2\2\2yg\3\2\2\2yl\3\2\2\2yq\3\2\2\2z\4\3\2\2\2{}\5\35\17\2"+
		"|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0084\3\2\2\2\u0080\u0083"+
		"\5\35\17\2\u0081\u0083\5\27\f\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2"+
		"\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\6"+
		"\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\5\27\f\2\u0088\u0087\3\2\2\2"+
		"\u0089\u008a\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\b\3"+
		"\2\2\2\u008c\u008e\5\27\f\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2"+
		"\2\2\u0092\u0094\7\60\2\2\u0093\u0095\5\27\f\2\u0094\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\n\3\2\2\2"+
		"\u0098\u009b\7$\2\2\u0099\u009c\5\r\7\2\u009a\u009c\5\25\13\2\u009b\u0099"+
		"\3\2\2\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\7$\2\2\u00a0\f\3\2\2\2"+
		"\u00a1\u00a2\7/\2\2\u00a2\u00a3\7/\2\2\u00a3\u00a7\3\2\2\2\u00a4\u00a6"+
		"\5\25\13\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2"+
		"\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ab"+
		"\b\7\2\2\u00ab\16\3\2\2\2\u00ac\u00ad\7<\2\2\u00ad\u00bc\7?\2\2\u00ae"+
		"\u00bc\7-\2\2\u00af\u00b1\7/\2\2\u00b0\u00b2\n\2\2\2\u00b1\u00b0\3\2\2"+
		"\2\u00b1\u00b2\3\2\2\2\u00b2\u00bc\3\2\2\2\u00b3\u00bc\t\3\2\2\u00b4\u00b5"+
		"\7#\2\2\u00b5\u00bc\7?\2\2\u00b6\u00bc\t\4\2\2\u00b7\u00b8\7>\2\2\u00b8"+
		"\u00bc\7?\2\2\u00b9\u00ba\7@\2\2\u00ba\u00bc\7?\2\2\u00bb\u00ac\3\2\2"+
		"\2\u00bb\u00ae\3\2\2\2\u00bb\u00af\3\2\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00b4"+
		"\3\2\2\2\u00bb\u00b6\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc"+
		"\20\3\2\2\2\u00bd\u00bf\t\5\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c0\3\2\2"+
		"\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3"+
		"\b\t\2\2\u00c3\22\3\2\2\2\u00c4\u00c6\7\f\2\2\u00c5\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\u00ca\b\n\2\2\u00ca\24\3\2\2\2\u00cb\u00d5\5\37\20\2\u00cc"+
		"\u00d5\5\27\f\2\u00cd\u00d5\5\17\b\2\u00ce\u00d5\5\3\2\2\u00cf\u00d5\5"+
		"\5\3\2\u00d0\u00d5\5\7\4\2\u00d1\u00d5\5\t\5\2\u00d2\u00d5\5\13\6\2\u00d3"+
		"\u00d5\5\21\t\2\u00d4\u00cb\3\2\2\2\u00d4\u00cc\3\2\2\2\u00d4\u00cd\3"+
		"\2\2\2\u00d4\u00ce\3\2\2\2\u00d4\u00cf\3\2\2\2\u00d4\u00d0\3\2\2\2\u00d4"+
		"\u00d1\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5\26\3\2\2"+
		"\2\u00d6\u00d7\t\6\2\2\u00d7\30\3\2\2\2\u00d8\u00d9\t\7\2\2\u00d9\32\3"+
		"\2\2\2\u00da\u00db\t\b\2\2\u00db\34\3\2\2\2\u00dc\u00df\5\31\r\2\u00dd"+
		"\u00df\5\33\16\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df\36\3\2"+
		"\2\2\u00e0\u00e3\t\t\2\2\u00e1\u00e3\5\35\17\2\u00e2\u00e0\3\2\2\2\u00e2"+
		"\u00e1\3\2\2\2\u00e3 \3\2\2\2\24\2y~\u0082\u0084\u008a\u008f\u0096\u009b"+
		"\u009d\u00a7\u00b1\u00bb\u00c0\u00c7\u00d4\u00de\u00e2\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}