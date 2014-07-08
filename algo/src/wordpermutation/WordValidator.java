package wordpermutation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class WordValidator {
	private Set<String> _validWords = new HashSet<String>();
	private static final String DICTIONARY_PATH1 = "mine.docx";
	
	/**
	 * Reads the given dictionary file which should contain one word per line.
	 * Each word is normalized before it is stored.
	 */

	public WordValidator() throws IOException {
		File f = new File(DICTIONARY_PATH1);
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				_validWords.add(normalizeWord(line));
			}
		} finally {
			reader.close();
		}
	}

	public boolean isRealWord(String word) {
		return _validWords.contains(normalizeWord(word));
	}

	private String normalizeWord(String word) {
		assert word != null;
		return word.toLowerCase().trim();
	}
}