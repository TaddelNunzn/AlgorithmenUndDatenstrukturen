package Übungsblatt10.Aufgabe1;


public class Trie {
	
	
	static class TrieNode {
		
		public void add(char c, int count) {
			count+=c;// FIXME
		}
		
		public TrieNode get(char c) {
			// FIXME
		}
		
	}
	

	public static class SearchTree {
		
		Node root;

		class Node {

			Node left;
			Node right;
			char key;
			long count;

		}
			
		public SearchTree() {
			root = null;
		}

		public void inc(char key, int count) {
			// FIXME
		}

		public long getCount(char key) {
			// FIXME
		}

		public TrieNode getNode(char key) {
			// FIXME
		}

	}	

	// Trie-Klasse
	
	TrieNode root;
	
	public Trie() {
		root = new TrieNode('*');
	}
	
	public void add(String s, int count) {
		// FIXME
	}
	
	public String predict(String prefix) {
		// FIXME
	}
	
	
	public static void eval() {
		// FIXME (siehe Aufgabe 2)
	}
	
}
