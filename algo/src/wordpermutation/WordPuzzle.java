package wordpermutation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordPuzzle {
	private List<Node> nodes;
	private Set<String> genWords;
	private Set<String> genWords1;
	private WordValidator wordValidator;

	public WordPuzzle() throws IOException {
		nodes = new ArrayList<Node>();
		genWords = new HashSet<String>();
		genWords1 = new HashSet<String>();
		wordValidator = new WordValidator();
		init();
	}

	public static void main(String args[]) throws IOException {
		WordPuzzle wp = new WordPuzzle();
		wp.computeWords();
	}

	private void computeWords() {
		for (int i = 0; i < nodes.size(); i++) { // words starting with each
													// node
			Node node = nodes.get(i);
			Set<Node> set = new HashSet<Node>();
			StringBuffer sb = new StringBuffer();
			calculate(node, sb, set);
		}

		for (int i = 0; i < nodes.size(); i++) { // words starting with each
			// node
			Node node = nodes.get(i);
			StringBuffer sb = new StringBuffer();
			calculate1(node, sb);
		}
		
		System.out.println("Validation Starting ...");
	
		for(String s: genWords)
		{
			if(!genWords1.contains(s))
				System.out.println(s);
		}
		
		System.out.println("Validation End");
	}

	private void calculate(Node node, StringBuffer sb, Set<Node> set) {
		if (set.contains(node))
			return;

		sb.append(node.getCharacter());
		set.add(node);

		String word = sb.toString();
		if (!genWords.contains(word) && isWord(word)) {
			System.out.println(word);
			genWords.add(word);
		}

		List<Node> connectedNodes = node.getConnectedNodes();
		for (int i = 0; i < connectedNodes.size(); i++) {
			Node curNode = connectedNodes.get(i);
			calculate(curNode, sb, set);
		}

		set.remove(node);
		sb.deleteCharAt(sb.length() - 1);
	}

	private void calculate1(Node node, StringBuffer sb) {
		// it works
		if (node.isVisited())
			return;

		sb.append(node.getCharacter());
		node.setVisited(true);

		String word = sb.toString();
		if (!genWords1.contains(word) && isWord(word)) {
			System.out.println(word);
			genWords1.add(word);
		}

		List<Node> connectedNodes = node.getConnectedNodes();
		for (int i = 0; i < connectedNodes.size(); i++) {
			Node curNode = connectedNodes.get(i);
			calculate1(curNode, sb);
		}

		node.setVisited(false);
		sb.deleteCharAt(sb.length() - 1);
	}

	private boolean isWord(String word) {
		return wordValidator.isRealWord(word);
	}

	private void init() {
		Node n1 = new Node('r', 1);
		nodes.add(n1);
		Node n2 = new Node('p', 2);
		nodes.add(n2);
		Node n3 = new Node('o', 3);
		nodes.add(n3);
		Node n4 = new Node('p', 4);
		nodes.add(n4);
		Node n5 = new Node('c', 5);
		nodes.add(n5);
		Node n6 = new Node('o', 6);
		nodes.add(n6);
		Node n7 = new Node('n', 7);
		nodes.add(n7);
		Node n8 = new Node('a', 8);
		nodes.add(n8);

		// r
		n1.addConnection(n2);
		n1.addConnection(n7);
		n1.addConnection(n6);

		// p
		n2.addConnection(n3);
		n2.addConnection(n7);
		n2.addConnection(n8);
		n2.addConnection(n1);

		// o
		n3.addConnection(n2);
		n3.addConnection(n4);
		n3.addConnection(n7);
		n3.addConnection(n8);

		// p
		n4.addConnection(n3);
		n4.addConnection(n8);
		n4.addConnection(n5);

		// c
		n5.addConnection(n8);
		n5.addConnection(n4);
		n5.addConnection(n6);
		n5.addConnection(n7);

		// o
		n6.addConnection(n5);
		n6.addConnection(n8);
		n6.addConnection(n7);
		n6.addConnection(n1);

		// n
		n7.addConnection(n1);
		n7.addConnection(n2);
		n7.addConnection(n3);
		n7.addConnection(n8);
		n7.addConnection(n5);
		n7.addConnection(n6);

		// a
		n8.addConnection(n2);
		n8.addConnection(n3);
		n8.addConnection(n4);
		n8.addConnection(n5);
		n8.addConnection(n6);
		n8.addConnection(n7);
	}

	public static class Node {
		private char c;
		private int index;
		private List<Node> connectedNodes;
		private boolean isVisited;

		public Node(char c, int uniqueIndex) {
			assert uniqueIndex > 0;
			this.c = c;
			this.index = uniqueIndex;
		}

		public boolean isVisited() {
			return isVisited;
		}

		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}

		public char getCharacter() {
			return c;
		}

		public int getIndex() {
			return index;
		}

		public List<Node> getConnectedNodes() {
			if (connectedNodes == null)
				return Collections.emptyList();

			return connectedNodes;
		}

		public void addConnection(Node connection) {
			if (connectedNodes == null)
				connectedNodes = new ArrayList<Node>();

			connectedNodes.add(connection);
		}

		public int hashCode() {
			return index;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			Node other = (Node) obj;
			return index == other.index;
		}
	}
}
