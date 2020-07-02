//import java.io.*;
//import java.util.LinkedList;
//import java.util.Spliterator;
//import java.util.function.Consumer;
//import java.util.stream.Stream;
//
//public class Trie {
//
//  static class TrieNode {
//    SearchTree child;
//    TrieNode bestchild;
//    char cha;
//
//    public void add(char c, int count) {
//      child.inc(c, count);//wird auf childbaum aufgerufen
//    }
//
//    public TrieNode get(char c) {
//      return this.child.getNode(c);//nimmt den nächsten Knoten des Tries
//    }
//
//    public TrieNode(char c){//Konstruktor
//      this.cha=c;
//      this.child=new SearchTree();
//    }
//  }
//
//
//  public static class SearchTree {
//
//    Node root;
//
//    class Node {
//
//      Node left;
//      Node right;
//      char key;
//      long count;
//      TrieNode ref;// referenz zum Trie Baum
//
//
//      public Node(char key, long count) {
//        this.key = key;
//        this.count=count;
//        this.ref= new TrieNode(key);
//      }
//
//      public LinkedList<Node> getParent() {
//        LinkedList<Node> res = new LinkedList();
//        if (right != null)res.addAll(right.getParent());
//        if (left != null)res.addAll(left.getParent());
//        res.add(this);
//        return res;
//      }
//
//      public void Right(Node n) {
//        this.right = n;
//      }
//
//      public void Left(Node n) {
//        this.left = n;
//      }
//
//      public void incre(long i) {
//        this.count = (count + i);
//      }
//
//    }
//
//    public SearchTree() {
//      root = null;
//    }
//
//    public void inc(char key, int count) {
//      Node Node1 = new Node(key, count);
//      if(root==null){
//        root = Node1;
//        return;
//      }
//      Node parent=null;
//      Node currNode=root;
//      do{
//        parent = currNode;
//        if(currNode.key==Node1.key){
//          currNode.incre(Node1.count);
//          return;
//        }
//        if(currNode.key>Node1.key){
//          currNode=currNode.left;
//          if(currNode==null){
//            parent.Left(Node1);
//            return;
//          }
//        }
//        if(currNode.key<Node1.key){
//          currNode=currNode.right;
//          if(currNode==null){
//            parent.Right(Node1);
//            return;
//          }
//        }
//      }while(true);
//    }
//
//    public long getCount(char key) {
//      Node currNode=root;
//      do{
//        if(currNode.key< key && currNode.left!=null)currNode=currNode.left;
//        else if(currNode.key> key &&currNode.right!=null)currNode=currNode.right;
//      }while(currNode.key != key);
//      return currNode.count;
//    }
//
//    public TrieNode getNode(char key){
//      Node currNode=root;
//      do{
//        if(currNode.key< key && currNode.left!=null)currNode=currNode.left;
//        else if(currNode.key> key &&currNode.right!=null)currNode=currNode.right;
//      }while(currNode.key != key);
//      return currNode.ref;
//    }
//
//  }
//
//  // Trie-Klasse
//
//  TrieNode root;
//
//  public Trie() {
//    root = new TrieNode('*');
//  }
//
//  public void add(String s, int count) {
//    // FIXME
//  }
//
//  public String predict(String prefix) {
//    System.out.println(prefix);
//    TrieNode curr=root;
//    for(int i=0;i<prefix.length();i++){
//      try{curr =curr.get(prefix.charAt(i));}
//      catch(NullPointerException e){ return null;}
//    }
//    do{
//      prefix = prefix + curr.bestchild.cha;
//      curr=curr.bestchild;
//    }while(curr.bestchild != null && curr.bestchild.character != '*');
//    return prefix;
//  }
//
//
//  public static void eval() {
//    // FIXME (siehe Aufgabe 2)
//  }
//
//}