package misc;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Convert {

	private Set<String> path;
	private Set<String> pathUsed;

	public Convert() {
		path = new HashSet<String>();
		pathUsed = new HashSet<String>();
	}

	public static void main(String[] args) {
		try {
			String srcStr = "PIG";
			String tgtStr = "STY";
			Convert conv = new Convert();
			conv.execute(srcStr, tgtStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void execute(String srcStr, String tgtStr) throws Exception {
		if (srcStr.equals(tgtStr))
			return;

		char[] srcChars = srcStr.toCharArray();

		Set<String> set = new HashSet<String>();
		StringBuffer sb = new StringBuffer(srcStr);

		while (true) {
			for (int i = 0; i < srcChars.length; i++) {
				char curChar = sb.charAt(i);
				char nextChar = getNextChar(curChar);
				sb.setCharAt(i, nextChar);

				String curString = sb.toString();
				if (isValidWord(curString)) {
					set.add(curString);
					if (curString.equals(tgtStr)) {
						print(set);
						return;
					}
				}
			}
		}
	}

	private void print(Set<String> set) {
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			System.out.print(iter.next());
		}
	}

	private char getNextChar(char curChar) throws Exception {

		int ascii = (int) curChar;

		if (ascii >= 65 && ascii <= 90) {
			if (ascii + 1 > 90)
				return (char) 65;
			else
				return (char) (ascii + 1);
		} else if (ascii >= 97 && ascii <= 122) {
			if (ascii + 1 > 122)
				return (char) 97;
			else
				return (char) (ascii + 1);
		}

		throw new Exception("Not a valid character");
	}

	private boolean isValidWord(String word) {
		return true;
	}

	private void test() {
		check("CAT");
		check("DOG");
		check("RAMU");
	}

	private void check(String word) {
		System.out.println("IsValid : " + isValidWord(word));
	}
}
