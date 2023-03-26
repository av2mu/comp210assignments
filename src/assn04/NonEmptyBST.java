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
		if(this._element.compareTo(element) > 0){
			if (this._left.isEmpty()){
				this._left = new NonEmptyBST<>(element);
			}
			else{
				this._left.insert(element);
			}
		}
		else {
			if(this._right.isEmpty()){
				this._right = new NonEmptyBST<>(element);
			}
			else{
				this._right.insert(element);
			}
		}
		return this;
	}

	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if(element.compareTo(this._element) < 0){
			this._left = this._left.remove(element);
		}
		else if(element.compareTo(this._element) > 0){
			this._right = this._right.remove(element);
		}
		if (this._element.compareTo(element) == 0){
				if (this._left.isEmpty() && this._right.isEmpty()){
					return new EmptyBST<>();
				}
				else if (this._left.isEmpty()){
					return this._right;
				}
				else if (this._right.isEmpty()){
					return this._left;
				}
				else{
					NonEmptyBST<T> next = (NonEmptyBST<T>) this._right;
					while (!next._left.isEmpty()){
						next = (NonEmptyBST<T>) next._left;
					}
					this._element = next._element;
					this._right.remove(next._element);
				}
			}
		return this;
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
		Queue queue = new LinkedList();
		queue.add(this);
		while (!queue.isEmpty()){
			NonEmptyBST current = (NonEmptyBST) queue.poll();
			System.out.print( current._element +" ");
			if (!current._left.isEmpty()){
				queue.add(current._left);
			}
			if (!current._right.isEmpty()){
				queue.add(current._right);
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
