package com.ds.Trees;

/*
* Binary Search Tree
* Insert()
* delete()/remove()
* search()
* Merge two BST
* and more
* */
public class BST {
    int value;
    BST left;
    BST right;

    public BST(int value) {
        this.value = value;
    }

    // Worst case: O(n) Time | O(1) space
    // BestCase: O(log(n)) Time | O(1) space
    public BST insert(int value) {
        BST currentNode = this;
        while (true) {
            if(currentNode.value < value) {
                if(currentNode.left == null) {
                    currentNode.left = new BST(value);
                    break;
                } else {
                    currentNode = currentNode.left;
                }
            } else {
                if(currentNode.right == null) {
                    currentNode.right = new BST(value);
                    break;
                } else {
                    currentNode = currentNode.right;
                }
            }
        }
        return this;
    }

    // Average: O(log(n)) time | O(1) space
    // Worst: O(n) time | O(1) space
    public boolean contains(int value) {
        BST node = this;
        boolean containsVal = false;
        while (node != null) {
            if(value < node.value) {
                node = node.left;
            } else if(value > node.value ) {
                node = node.right;
            } else {
                containsVal = true;
                break;
            }
        }
        return containsVal;
    }


    // Average: O(log(n)) time | O(1) space
    // Worst: O(n) time | O(1) space
    public BST remove(int value) {
        this.remove(value, null);
        return this;
    }

    public void remove(int value, BST parentNode) {
        BST currentNode = this;
        while (currentNode != null) {
            if(value < currentNode.value) {
                parentNode = currentNode;
                currentNode = currentNode.left;
            } else if(value > currentNode.value) {
                parentNode = currentNode;
                currentNode = currentNode.right;
            } else {
                // value == currentNode.value
                if(currentNode.left != null && currentNode.right != null) {
                    currentNode.value = currentNode.right.getMinValue();
                    currentNode.right.remove(currentNode.value, currentNode);
                } else if(parentNode == null) {
                    if(currentNode.left != null) {
                        currentNode.value = currentNode.left.value;
                        currentNode.right = currentNode.left.right;
                        currentNode.left = currentNode.left.left;
                    } else if(currentNode.right != null) {
                        currentNode.value = currentNode.right.value;
                        currentNode.left = currentNode.right.left;
                        currentNode.right = currentNode.right.right;
                    } else {
                        // ignore
                    }
                } else if(parentNode.left == currentNode) {
                    parentNode.left = currentNode.left != null ? currentNode.left: currentNode.right;
                } else if(parentNode.right == currentNode) {
                    parentNode.right = currentNode.left != null ? currentNode.left : currentNode.right;
                }
                break;
            }
        }
    }

    public int getMinValue() {
        if(left == null) {
            return value;
        } else {
            return left.getMinValue();
        }
    }

}
