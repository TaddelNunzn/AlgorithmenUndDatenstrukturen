package Übungsblatt7.Aufgabe2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class BinTreeTest {

  BinTree createTreeFromSheet() {
    BinTree.Node n7 = new BinTree.Node(2);
    BinTree.Node n6 = new BinTree.Node(0);
    BinTree.Node n5 = new BinTree.Node(20, n7, null);
    BinTree.Node n4 = new BinTree.Node(16);
    BinTree.Node n3 = new BinTree.Node(3, null, n6);
    BinTree.Node n2 = new BinTree.Node(-4, n4, n5);
    BinTree.Node n1 = new BinTree.Node(8, n2, n3);
    
    BinTree tree = new BinTree(n1);
    return tree;
  }
  
  BinTree createUnbalancedTree() {
    BinTree.Node root = new BinTree.Node(1);
    BinTree.Node n2 = new BinTree.Node(2);
    BinTree.Node n3 = new BinTree.Node(3);
    BinTree.Node n4 = new BinTree.Node(4);
    BinTree.Node n5 = new BinTree.Node(5);
    BinTree.Node n6 = new BinTree.Node(6);
    root.left = n2;
    n2.left = n3;
    n3.left = n4;
    n4.left = n5;
    n5.left = n6;
    BinTree tree = new BinTree(root);
    return tree;
  }
  
  BinTree createUnbalancedTreeBackward() {
    BinTree.Node root = new BinTree.Node(6);
    BinTree.Node n2 = new BinTree.Node(5);
    BinTree.Node n3 = new BinTree.Node(4);
    BinTree.Node n4 = new BinTree.Node(3);
    BinTree.Node n5 = new BinTree.Node(2);
    BinTree.Node n6 = new BinTree.Node(1);
    root.right = n2;
    n2.right = n3;
    n3.right = n4;
    n4.right = n5;
    n5.right = n6;
    BinTree tree = new BinTree(root);
    return tree;
  }

  BinTree createRootOnlyTree() {
    BinTree.Node root = new BinTree.Node(1);
    BinTree tree = new BinTree(root);
    return tree;
  }

  BinTree createCompleteTree() {
    BinTree.Node n4 = new BinTree.Node(4);
    BinTree.Node n5 = new BinTree.Node(5);
    BinTree.Node n6 = new BinTree.Node(6);
    BinTree.Node n7 = new BinTree.Node(7);
    BinTree.Node n2 = new BinTree.Node(2, n4, n5);
    BinTree.Node n3 = new BinTree.Node(3, n6, n7);
    BinTree.Node root = new BinTree.Node(1, n2, n3);
    BinTree tree = new BinTree(root);
    return tree;
  }

  
  @Test
  public void testHeight() {
    BinTree tree = createTreeFromSheet();
    assertEquals(4, tree.height());

    tree = createUnbalancedTree();
    assertEquals(6, tree.height());

    tree = createUnbalancedTreeBackward();
    assertEquals(6, tree.height());

    tree = createCompleteTree();
    assertEquals(3, tree.height());

    tree = createRootOnlyTree();
    assertEquals(1, tree.height());

    // empty tree
    tree = new BinTree();
    assertEquals(0, tree.height());
  }

  
  @Test
  public void testPathToMax() {

    BinTree tree = createTreeFromSheet();
    assertEquals("left->right", tree.pathToMax());

    tree = createUnbalancedTree();
    assertEquals("left->left->left->left->left", tree.pathToMax());

    tree = createUnbalancedTreeBackward();
    assertEquals("", tree.pathToMax());

    tree = createCompleteTree();
    assertEquals("right->right", tree.pathToMax());

    tree = createRootOnlyTree();
    assertEquals("", tree.pathToMax());

  }

  
}
