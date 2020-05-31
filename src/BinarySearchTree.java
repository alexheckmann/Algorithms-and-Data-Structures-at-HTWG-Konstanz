public class BinarySearchTree<T extends Comparable<? super T>> {

    private T value;
    private BinarySearchTree<T> leftSubTree;
    private BinarySearchTree<T> rightSubTree;

    public BinarySearchTree(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinarySearchTree<T> getLeftSubTree() {
        return leftSubTree;
    }

    public void setLeftSubTree(BinarySearchTree<T> leftSubTree) {
        this.leftSubTree = leftSubTree;
    }

    public BinarySearchTree<T> getRightSubTree() {
        return rightSubTree;
    }

    public void setRightSubTree(BinarySearchTree<T> rightSubTree) {
        this.rightSubTree = rightSubTree;
    }

    /**
     * Inserts a given value at the appropriate position of the tree.
     *
     * @param value value to be inserted
     */
    public void insert(T value) {

        if (value.compareTo(this.value) < 0) {

            if (leftSubTree != null) {
                leftSubTree.insert(value);
            } else {
                leftSubTree = new BinarySearchTree<>(value);
            }
        }

        if (value.compareTo(this.value) > 0) {
            if (rightSubTree != null) {
                rightSubTree.insert(value);
            } else {
                rightSubTree = new BinarySearchTree<>(value);
            }
        }
    }

    public void print() {

        if (leftSubTree != null) {
            leftSubTree.print();
        }
        System.out.println(this.value);
        if (rightSubTree != null) {
            rightSubTree.print();
        }
    }

    /**
     * Calculates the tree's current size.
     *
     * @return the tree's current size
     */
    public int size() {

        int size = 1;

        if (leftSubTree != null) {
            size += leftSubTree.size();
        }

        if (rightSubTree != null) {
            size += rightSubTree.size();
        }
        return size;
    }

    /**
     * Recursively checks whether the tree contains a given value.
     *
     * @param value value to search for
     * @return whether or not the tree contains the value.
     */
    public boolean contains(T value) {

        if (value.equals(this.value)) {
            return true;

        } else if (value.compareTo(this.value) < 0) {

            if (leftSubTree != null) {
                return leftSubTree.contains(value);
            }

        } else {

            if (rightSubTree != null) {
                return rightSubTree.contains(value);
            }

        }

        return false;
    }

    /**
     * Checks whether a given tree is complete.
     *
     * @param root root of the tree to be checked
     * @param <T> type of the content of the tree
     * @return whether or not the tree is complete
     */
    public static <T extends Comparable<T>> boolean isComplete(BinarySearchTree<T> root) {

        return isComplete(root, 0, root.size());

    }

    /**
     * Internal method overloading the public method. Checks whether a given tree is complete.
     *
     * @param root root of the tree to be checked
     * @param index index of the node to start with, default given by the overloading method is 0
     * @param numberNodes number of nodes to check, default given by the overloading method is {@code root.size()}
     * @param <T> type of the content of the tree
     * @return whether or not the tree is complete
     */
    private static <T extends Comparable<T>> boolean isComplete(BinarySearchTree<T> root, int index, int numberNodes) {

        // An empty tree is complete
        if (root == null)
            return true;

        // If index assigned to current node is bigger than the
        // number of nodes of the tree, then tree is not complete
        if (index >= numberNodes)
            return false;

        // Recur for left and right subtrees
        return (isComplete(root.getLeftSubTree(), 2 * index + 1, numberNodes)
                && isComplete(root.getRightSubTree(), 2 * index + 2, numberNodes));

    }

    public static <T extends Comparable<T>> boolean isFull(BinarySearchTree<T> root) {

        // empty tree is not full
        if (root == null) {
            return false;
        }


        // if any root has only one child the tree isn't full
        if (root.getLeftSubTree() == null && root.getRightSubTree() != null || root.getLeftSubTree() != null && root.getRightSubTree() == null) {
            return false;
        }

        // if both children are null the tree is full
        if (root.getLeftSubTree() == null && root.getRightSubTree() == null) {
            return true;
        }

        // recur for left and right children
        return (isFull(root.getLeftSubTree()) && isFull(root.getRightSubTree()));

    }
}
