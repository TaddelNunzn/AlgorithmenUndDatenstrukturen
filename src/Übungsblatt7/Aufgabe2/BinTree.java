package Übungsblatt7.Aufgabe2;


import com.sun.source.tree.BinaryTree;

public class BinTree {

	Node root;


	static class Node {

		Node left;
		Node right;
		int value;

		Node(int value) {
			this.value = value;
		}

		Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
		
	}
			
	
	public BinTree() {
		root = null;
	}

	public BinTree(Node root) {
		this.root = root;
	}

	public int height() {
		if(root==null)return 0;
		int l=0,r=0;
		if(root.left!=null){
			l=new BinTree(root.left).height();
		}
		if(root.right!=null){
			r=new BinTree(root.right).height();
		}
		return 1+Math.max(l,r);
	}
	
	public String pathToMax() {
		int max = this.max();
		if(root.value == max){

		}else{
			if(root.left != null){
				return "left->"+new BinTree(root.left).pathToMax();
			}
			if(root.right != null){
				return "right->"+new BinTree(root.right).pathToMax();
			}
		}
		return "";
	}

	public int max(){
		if(root==null) return 0;
		if(root.left==null || root.right==null)return root.value;
		return Math.max(new BinTree(root.left).max(),new BinTree(root.right).max());

	}
	
	public boolean isCompleteOrAlmostComplete() {
		return false;// FIXME
	}

	public static void main(String[] args) {
	}

}
