package assn06;



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

    public AVLTree(T element) {
        _value = element;
        _left = new AVLTree<T>();
        _right = new AVLTree<T>();
        _height = 0;
        _size = 1;
    }


    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateLeft() {
        AVLTree<T> rightNode = this._right;
        AVLTree<T> centerNode = rightNode._left;
        rightNode._left = this;
        this._right = centerNode;
        this.updateHeight();
        rightNode.updateHeight();
        this.updateSize();
        rightNode.updateSize();
        return rightNode;
    }

    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateRight() {
        AVLTree<T> leftNode = this._left;
        AVLTree<T> centerNode = leftNode._right;
        leftNode._right = this;
        this._left = centerNode;
        this.updateHeight();
        leftNode.updateHeight();
        this.updateSize();
        leftNode.updateSize();
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
            return new AVLTree<>(element);
        }
        if (element.compareTo(this._value) < 0){
            this._left = (AVLTree<T>) this._left.insert(element);
        }
        else if (element.compareTo(this._value) > 0) {
            this._right = (AVLTree<T>) this._right.insert(element);
        } else {
            return this;
        }
        this.updateHeight();
        this.updateSize();
        return this.applyRotation();
    }



    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (this._value == null){
            return new AVLTree<>();
        }
        if(element.compareTo(this._value) < 0){
            this._left = (AVLTree<T>) this._left.remove(element);
        }
        else if(element.compareTo(this._value) > 0){
            this._right = (AVLTree<T>) this._right.remove(element);
        }
        if (this._value.compareTo(element) == 0){
            if (this._left.isEmpty()){
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
                this._right = (AVLTree<T>) this._right.remove(next._value);
            }
        }
        this.updateSize();
        this.updateHeight();
        return this.applyRotation();
    }

    public SelfBalancingBST<T> applyRotation() {
        int balance = balance(this);
        if (balance > 1){
            if (balance(this._left) < 0){
                this._left = this._left.rotateLeft();
            }
            return this.rotateRight();
        }
        if (balance < -1){
            if (balance(this._right) > 0){
                this._right = this._right.rotateRight();
            }
            return this.rotateLeft();
        }
        return this;
    }

    private int balance(AVLTree<T> tree){
        if (tree._value == null){
            return 0;
        }
        return height(tree._left) - height(tree._right);
    }


    private void updateHeight(){
        int maxHeight = Math.max(height(this._left), height(this._right));
        this._height = maxHeight + 1;
    }

    private int height(AVLTree<T> tree){
        if (this._value == null){
            return 0;
        }
        return tree._height;

    }


    public void updateSize(){
        if(this._value == null){
            this._size = 0;
        }
        else{
            this._size = this._left.size() + this._right.size() + 1;
        }
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
        if (isEmpty()) {
            return false;
        }
        if (element.compareTo(_value) < 0) {
            return _left.contains(element);
        } else if (element.compareTo(_value) > 0) {
            return _right.contains(element);
        } else {
            return true;
        }
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