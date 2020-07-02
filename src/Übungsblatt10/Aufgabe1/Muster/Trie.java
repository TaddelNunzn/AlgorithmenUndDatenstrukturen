package Übungsblatt10.Aufgabe1.Muster;

import java.io.*;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Trie {

  static class TrieNode {
    // mitgeführter SearchTree
    SearchTree children;
    // Verweis auf das m höchsten gewertete Kind für schnellen Zugriff
    TrieNode bestKid;
    // char als gespeicherte daten/key Trie Node braucht keinen count da nie
    // zwischen Trie Nodes entschieden werden muss
    char character;

    // Trie Node Konstruktor
    public TrieNode(char c) {
      // der Buchstabe wird initialisiert
      this.character = c;
      // children wird initialisiert als einelementigger Baum
      this.children = new SearchTree();

    }

    // add fügt Trie Node ein Node-Kind hinzu
    public void add(char c, int count) {
      // die inc methode wird auf den Kindbaum aufgerufen
      children.inc(c, count);
    }

    // get Funktion zum holen des Nächsten Knoten eines Tries
    public TrieNode get(char c) {
      return this.children.getNode(c);
    }

    // addString fügt einen neuen "Strang" zum Suchbaum und Trie Baum hinzu
    public void addString(String s, int count) {
      TrieNode currentNode = this;
      for (int i = 0; i < s.length(); i++) {
        currentNode.add(s.charAt(i), count);
        currentNode.bestKid = currentNode.children.root.getBestKid().referenz;
        currentNode = currentNode.get(s.charAt(i));
      }
      currentNode.add('*', count);
      currentNode.bestKid = currentNode.children.root.getBestKid().referenz;
    }

  }

  public static class SearchTree {

    Node root;

    class Node {

      // Trie Baum referenz
      TrieNode referenz;
      Node left;
      Node right;
      char key;
      long count;

      // Konstruktor für Node
      public Node(char key, long count) {
        this.referenz = new TrieNode(key);
        this.key = key;
        this.count = count;
      }

      public LinkedList<Node> getLeftRightParent() {
        LinkedList<Node> result = new LinkedList();
        if (left != null) {
          result.addAll(left.getLeftRightParent());
        }
        if (right != null) {
          result.addAll(right.getLeftRightParent());
        }
        result.add(this);
        return result;
      }

      @Override
      public String toString() {
        // TODO Auto-generated method stub
        return this.key + " " + this.count;
      }

      public void setLeft(Node n) {
        this.left = n;
      }

      public void setRight(Node n) {
        this.right = n;
      }

      public void increment(long i) {
        this.count = (count + i);
      }

      // rekursive Methode zum Suchen des höchstgewerteten Kindknotens
      public Node getBestKid() {
        LinkedList<Node> list = this.getLeftRightParent();
        list.sort((x, c) -> Long.compare(c.count, x.count));
        return list.getFirst();

      }
    }

    // Simpler Konstruktor für Search Tree
    public SearchTree() {
      root = null;
    }

    // inc Funktion für SearchTree
    public void inc(char key, int count) {
      Node newNode = new Node(key, count);
      if (this.root == null) {

        this.root = newNode;
        return;

      }
      Node currentNode = this.root;
      Node parent = null;

      while (true) {
        parent = currentNode;
        if (currentNode.key == (newNode.key)) {
          currentNode.increment(newNode.count);
          return;
        } else if (currentNode.key > newNode.key) {
          currentNode = currentNode.left;
          if (currentNode == null) {
            parent.setLeft(newNode);
            return;
          }

        } else {
          currentNode = currentNode.right;
          if (currentNode == null) {
            parent.setRight(newNode);
            return;
          }

        }

//        this.root.inc(key, count);

      }
    }

    public long getCount(char key) {
      Node countNode = searchTreeByKey(key);
      return countNode == null ? -1 : countNode.count;
    }

    public Node searchTreeByKey(char key) {
      Node currentNode = this.root;
      while (currentNode != null && currentNode.key != key) {
        if (currentNode.key > key && currentNode.left != null) {
          currentNode = currentNode.left;
        } else if (currentNode.key < key && currentNode.right != null) {
          currentNode = currentNode.right;
        } else {
          return null;
        }
      }
      if (currentNode == null) {
        return null;
      }
      return currentNode;

    }

    public TrieNode getNode(char key) {
      return searchTreeByKey(key).referenz;

    }

  }

  // Trie-Klasse

  TrieNode root;

  public Trie() {
    root = new TrieNode('*');
  }

  // ruft die rekursive Methode für addString auf und fügt einen neuen Query hinzu

  // predict Methode holt die dem am höchsten gewerteten query aus der Trie
  // Baumstruktur
  public String predict(String prefix) {
    System.out.println(prefix);
    // traversiere den Baum entlang des prefix ausgehend von der root
    TrieNode currentNode = this.root;
    // Sucht in jedem Durchlauf das Kind von current Node mit dem aktuellen char von
    // Prefix
    for (int i = 0; i < prefix.length(); i++) {
      try {
        currentNode = currentNode.get(prefix.charAt(i));
      } catch (NullPointerException e) {
        // currentNode.addString(prefix.substring(i), 1);
        return null;
      }
    }

    while (currentNode.bestKid != null && currentNode.bestKid.character != '*') {
      prefix = prefix + currentNode.bestKid.character;
      // System.out.println(currentNode.children.root.getBestKid().toString());
      currentNode = currentNode.bestKid;
    }
    System.out.println(prefix);
    return prefix;
  }

  public void add(String s, int count) {
    this.root.addString(s, count);
  }

  public static void eval() {
    Trie evaluator = new Trie();

    try {
      Reader read = new FileReader("/home/julian/Desktop/keyphrases.txt");
      BufferedReader reader = new BufferedReader(read);
      Stream<String[]> hi = reader.lines().map(f -> f.split(";"));
      hi.spliterator().forEachRemaining(f -> evaluator.add(f[0], Integer.valueOf(f[1])));
      hi.close();
    } catch (Exception e) {

      e.printStackTrace();
    }

    System.out.println(evaluator.predict("trump"));
    System.out.println(evaluator.predict("german"));
    System.out.println(evaluator.predict("mo"));
    System.out.println(evaluator.predict("paw"));
    System.out.println(evaluator.predict("secret"));
    System.out.println(evaluator.predict("best"));
    System.out.println(evaluator.predict("pro"));
    System.out.println(evaluator.predict("small"));
    System.out.println(evaluator.predict("snow"));
    System.out.println(evaluator.predict("soc"));
    System.out.println(evaluator.predict("por"));

  }

  public static void main(String[] args) {
    eval();
  }
}
