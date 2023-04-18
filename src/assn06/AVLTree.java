package assn06;


import assn03.Node;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    
     private AVLTree<T> rotateLeft() {
         AVLTree<T> root = this._right;
         AVLTree<T> temp = root._left;
         root._left = this;
         this._right = temp;
         this.getHeight();
         root.getHeight();
         return root;
     }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */ 
     
     private AVLTree<T> rotateRight() {
         AVLTree<T> leftNode = this._left;
         AVLTree<T> temp = leftNode._right;
         leftNode._right = this;
         this._left = temp;
         this.getHeight();
         leftNode.getHeight();
         return leftNode;
     }


    @Override
    public boolean isEmpty() {
        return this._value == null;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
    	if (this._value == null){
            this._value = element;
            this._left = new AVLTree<>();
            this._right = new AVLTree<>();
            this.getHeight();
            return this.balance();
        } else
        if (this._value.compareTo(element) > 0){
                this._left.insert(element);
        } else
        if (this._value.compareTo(element) < 0){
            this._right.insert(element);
        } else {
            return this;
        }
        this.getHeight();
        return this.balance();
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        if(element.compareTo(this._value) < 0){
            this._left = (AVLTree<T>) this._left.remove(element);
        }
        else if(element.compareTo(this._value) > 0){
            this._right = (AVLTree<T>) this._right.remove(element);
        }
        if (this._value.compareTo(element) == 0){
            if (this._left.isEmpty() && this._right.isEmpty()){
                return new AVLTree<>();
            }
            else if (this._left.isEmpty()){
                return this._right;
            }
            else if (this._right.isEmpty()){
                return this._left;
            }
            else{
                AVLTree<T> next = this._right;
                while (!next._left.isEmpty()){
                    next = next._left;
                }
                this._value = next._value;
                this._right.remove(next._value);
            }
        }
        this.getHeight();
        //this._size = getSize();
        return this;
    }

    public SelfBalancingBST<T> balance() {
    	if (this._left._height - this._right._height > 1){
            if (this._left._left._height > this._left._right._height){
                return this.rotateRight();
            } else {
                this._left = this._left.rotateLeft();
                return this.rotateRight();
            }
        } else if (this._right._height - this._left._height > 1){
            if (this._right._right._height > this._right._left._height){
                return this.rotateLeft();
            } else {
                this._right = this._right.rotateRight();
                return this.rotateLeft();
            }
        }
        return this;
    }

    public int getBalance(){
        return this._left._height - this._right._height;
    }

    public void getHeight(){
        if (this._left._value != null){
            this._left.getHeight();
        }
        if (this._right._value != null){
            this._right.getHeight();
        }
        this._height = Math.max(this._left._height, this._right._height) + 1;
    }


    public int getSize(){
        int size = 0;
        if (this._left._value != null){
            size += this._left.getSize();
        }
        if (this._right._value != null){
            size += this._right.getSize();
        }
        return size + 1;
    }

    @Override
    public T findMin() {
         if (isEmpty()) {
             throw new RuntimeException("Illegal operation on empty tree");
         }
         if (_left.isEmpty()) {
             return _value;
         } else {
             return _left.findMin();
         }
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        if (_right.isEmpty()) {
            return _value;
        } else {
            return _right.findMax();
        }
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

         return _right;
    }


}
