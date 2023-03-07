package assn04;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		if(this._element.compareTo(element) < 0 ) {
			if(this.getRight().isEmpty()){
				this._right = new NonEmptyBST<T>(element);
			}
			else
				this._right.insert(element);
		}
		else{
			if(this.getLeft().isEmpty()){
				this._left = new NonEmptyBST<T>(element);
			}
			else
				this._left.insert(element);
		}
		return this;
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		return null;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(this._element + " ");
		this._left.printPreOrderTraversal();
		this._right.printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		this._left.printPostOrderTraversal();
		this._right.printPostOrderTraversal();
		System.out.print(this._element + " ");
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
		Queue<BST> queue = new LinkedList<>();
		queue.add(this);

		while (!queue.isEmpty()){
			BST<T> current = queue.poll();
			System.out.print(current.getElement() + " ");
			if(!current.getLeft().isEmpty()){
				queue.add(current.getLeft());
			}
			if (!current.getRight().isEmpty()){
				queue.add(current.getRight());
			}
		}
	}

	// GetHeight of A Tree

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
