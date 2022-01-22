package com.ds.Trees;

/*
* BST recursive implementation
* */
public class BST2 {
    static class BST {
        int value;
        BST left;
        BST right;

        public BST(int value) {
            this.value = value;
        }

        // Average: O(log(n)) time | O(log(n)) space
        // Worst: O(n) time | O(n) space
        public BST insert(int value) {
            if(value < this.value) {
                if(left != null) {
                    left.insert(value);
                } else {
                    left =  new BST(value);
                }
            } else {
                if(right != null) {
                    right.insert(value);
                } else {
                    right = new BST(value);
                }
            }
            return this;
        }

        // Average: O(log(n)) time | O(log(n)) space
        // Worst: O(n) time | O(n) space
        public boolean contains(int value) {
            if(value < this.value) {
                if(left != null) {
                    return left.contains(value);
                } else {
                    return false;
                }
            } else if(value > this.value) {
                if(right != null) {
                    return right.contains(value);
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }

        // Average: O(log(n)) time | O(log(n)) space
        // Worst: O(n) time | O(n) space
        public BST remove(int value) {
            remove(value, null);
            return this;
        }

        private void remove(int value, BST parent) {
            if(value < this.value) {
                if(left != null) {
                    left.remove(value, this);
                }
            } else if(value > this.value) {
                if(right != null) {
                    right.remove(value, this);
                }
            } else {
                if(left != null && right != null) {
                    this.value = right.getMinValue();
                    right.remove(this.value, this);
                } else if(parent == null) {
                    if(left != null) {
                        this.value  = left.value;
                        right = left.right;
                        left = left.left;
                    } else if(right != null) {
                        this.value = right.value;
                        left = right.left;
                        right = right.right;
                    } else {
                        // single node tree, do nothing
                    }
                } else if(parent.left == this) {
                    parent.left = left != null ? left : right;
                } else if(parent.right == this) {
                    parent.right = left != null ? left : right;
                }
            }
        }

        public int getMinValue(){
            if(left == null) {
                return value;
            } else {
                return left.getMinValue();
            }
        }

    }
}
