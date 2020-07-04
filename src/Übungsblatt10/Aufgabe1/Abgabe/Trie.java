package Übungsblatt10.Aufgabe1.Abgabe;

public class Trie {

  static class TrieNode {
    // SearchTree, der an jede TrieNode gekoppelt sein soll
    SearchTree child;
    // char, den die TrieNode beinhaltet
    char key;

    public TrieNode(char c){
      this.key =c;
      this.child=new SearchTree();
    }

    public void add(char c, int count) {
      // inc wird auf den Suchbaum aufgerufen, wodurch eine entsprechende Node
      // in den Suchbaum eingefügt wird sollte noch
      // keine Node mit dem entsprechenden Key existieren
      child.inc(c, count);
    }

    public TrieNode get(char c) {
      // Die Methode getNode wird auf den Suchbaum aufgerufen, welche die
      // enstprechende TrieNode zurückgibt
      return child.getNode(c);
    }

  }


  public static class SearchTree {



    class Node {

      Node left;
      Node right;
      char key;
      long count;
      // Verweis auf zugehöriges TrieNode Kind
      TrieNode trieNode;


      public Node(char key, long count) {
        this.key = key;
        this.count=count;
        // Es wird eine neue TrieNode als Kind der Node erstellt
        this.trieNode = new TrieNode(key);
      }

      // Einer Node wird ein rechtes Kind hinzugefügt
      public void addRight(Node n) {
        this.right = n;
      }

      // Einer Node wird ein linkes Kind hinzugefügt
      public void addLeft(Node n) {
        this.left = n;
      }

      Node bestChild = null;

      public Node getBestChild() {
        if(bestChild == null)bestChild = this;
        // wenn count der momentanen Node größer ist als von dem bisher
        // höchsten wird das bestChild(Node mit höchstem count) ersetzt
        if(count > bestChild.count)bestChild = this;
        //die Methode wird reukursiv für beide Kinder wieder aufgerufen
        if(left != null)left.getBestChild();
        if(right != null)right.getBestChild();
        // die Node mit dem höchsten count wird zurückgegeben
        return bestChild;
      }
    }




    Node root;

    public SearchTree() {
      root = null;
    }

    public void inc(char key, int count) {
      Node result = root;
      // Wenn root null ist existiert kein Baum und root kann einfach ersetzt werden
      if(root == null) {
        root = new Node(key,count);
        return;
      }
      // Wenn root nicht null ist muss der ganze Baum durchsucht werden
      else{
        while(true){
          // Wenn der gesuchte Key im Baum existiert wird sein count hochgezählt
          if(key == result.key){
            result.count += count;
            return;
            // Wenn der gesuchte Key kleiner ist als in der momentanen Node muss
            // der Baum Links weiter durchsucht werden
          } else if(key < result.key){
            // kommt man links ans Ende des Baums wird eine Node an den Baum angehängt
            if(result.left == null) {
              result.addLeft(new Node(key,count));
              return;
            }else{
              result = result.left;
            }
            // Wenn der gesuchte Key größer ist als in der momentanen Node muss
            // der Baum rechts weiter durchsucht werden
          }else if(key > result.key){
            // kommt man rechts ans Ende des Baums wird eine Node an den Baum angehängt
            if(result.right == null){
              result.addRight(new Node(key,count));
              return;
            }else{
              result = result.right;
            }
          }
        }
      }
    }

    public Node searchForKey(char key){
      Node result = root;
      // solange der key nicht in dem Suchbaum gefunden wurde wird weiter durch den Baum iteriert
      while(result != null && result.key != key){
        // kleinere Werte werden im Suchbaum immer links von dem momentanen Node angeordnet
        // dadurch kann gezielt nach links gegangen werden, wenn der key kleiner ist
        if(key < result.key && result.left != null){ result = result.left;}
        // größere Werte werden im Suchbaum immer rechts von dem momentanen Node angeordnet
        // dadurch kann gezielt nach rechts gegangen werden, wenn der key größer ist
        else if(key > result.key && result.right != null){result = result.right;}
        // wird der key nicht gefunden wird null zurückgegeben
        else {return null;}
      }
      return result;
    }

    public long getCount(char key) {
      // es wird nach der Node mit dem entsprechenden key gesucht
      Node result = searchForKey(key);
      // wurde der key nicht gefunden wird -1 zurückgegeben
      if(result == null)return -1;
      // von der gefundenen Node wird count zurückgegeben
      return result.count;
    }


    public TrieNode getNode(char key){
      // searchForKey gibt die Node zu einem key zurück.
      // Von dieser Node wird dann der Verweis auf die TrieNode zurückgegeben
      return searchForKey(key).trieNode;
    }


  }

  // Trie-Klasse

  TrieNode root;

  public Trie() {
    root = new TrieNode('*');
  }

  public void add(String s, int count) {
    TrieNode result = root;
    // eine for-each Schleife iteriert über den String
    for(char i : s.toCharArray()){
      // es wird für jeden Char die add Methode für TrieNodes aufgerufen
      result.add(i,count);
      // die result TrieNode wird auf die gerade hinzugefügte TrieNode gesetzt
      result = result.get(i);
    }
    // das Ende des Strings wird durch einen * gekennzeichnet
    result.add('*',count);

  }

  public String predict(String prefix) {

    TrieNode result = root;
    // eine for-each Schleife läuft im Trie-Baum den prefix ab
    for(char i : prefix.toCharArray()){
      try{
        // die nächste TrieNode ist immer die, die den untersuchten Char enthält
        result = result.get(i);
        // wird der char nicht im Baum gefunden wird eine NullPointerException geworfen.
      }catch(NullPointerException e){
        // wurde der Char nicht im Baum gefunden wird null zurückgegeben
        return null;
      }
    }
    // solange eine Node besteht und nicht das durch * gekennzeichnete ende des
    // Baumes erreicht wurde wird das beste Kind ausgewäht und der
    // entsprechende Char an den Präfix angehängt
    while (result.child.root != null && result.child.root.key != '*'){
      result = result.child.root.getBestChild().trieNode;
      prefix = prefix + result.key;
    }

    return prefix;
  }


  public static void eval() {
    // FIXME (siehe Aufgabe 2)
  }


}