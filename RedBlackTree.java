package ca.ucalgary.cpsc331;

/**
 * CPSC 331 Assignment 2 Q4
 * Red-Black tree implementation
 * 
 * Name: Anil Mawji
 * UCID: 30099809
 */

public class RedBlackTree implements Dictionary {

    private enum Color {
        RED, BLACK;
    }

    private class Node {
        int key;
        Color color;
        Node left = TNULL;
        Node right = TNULL;
        Node p;

        public Node() {}

        public Node(int key) {
            this.key = key;
            this.color = Color.RED;
        }
    }

    private Node root, TNULL;

    public RedBlackTree() {
        this.TNULL = new Node();
	this.TNULL.color = Color.BLACK;
        this.TNULL.left = null;
        this.TNULL.right = null;
		this.root = TNULL;
    }

    public boolean empty() {
        return this.root == TNULL;
    }

    public void insert(int key) {
	Node node = new Node(key);
        Node cur = this.root;
        Node parent = null;

        //Find parent for node
        while (cur != TNULL) {
            parent = cur;
            if (node.key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        node.p = parent;

        //Set node as root
        if (parent == null) {
            this.root = node;
        //Set node as left child of p
        } else if (node.key < parent.key) {
            parent.left = node;
        //Set node as right child of p
        } else {
            parent.right = node;
        }
        insertFixup(node);
    }

    private void insertFixup(Node node) {
        while (node.p != null && node.p.color == Color.RED) {
            //Node's p is a left child
            if (node.p == node.p.p.left) {
                Node uncle = node.p.p.right;
                //Case Color.RED: Node's uncle is red
                if (uncle.color == Color.RED) {
                    uncle.color = Color.BLACK;
                    node.p.color = Color.BLACK;
                    node.p.p.color = Color.RED;
                    node = node.p.p;
                //Node's uncle is black
                } else {
                    //Case 2: Node is a right child
                    if (node == node.p.right) {
                        node = node.p;
                        leftRotate(node);
                    }
                    //Case 3: node and its p are both right children
                    node.p.color = Color.BLACK;
                    node.p.p.color = Color.RED;
                    rightRotate(node.p.p);
                }
            //Node's p is a right child
            } else {
                Node uncle = node.p.p.left;
                //Case Color.RED: Node's uncle is red
                if (uncle.color == Color.RED) {
                    node.p.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.p.p.color = Color.RED;
                    node = node.p.p;
                //Node's uncle is black
                } else {
                    //Case 2: Node is a right child
                    if (node == node.p.left) {
                        node = node.p;
                        rightRotate(node);
                    }
                    //Case 3: node and its p are both right children
                    node.p.color = Color.BLACK;
                    node.p.p.color = Color.RED;
                    leftRotate(node.p.p);
                }
            }
        }
        this.root.color = Color.BLACK;
    }

    public void delete(int key) {
        if (empty()) throw new RuntimeException("Failed to delete key: The tree is empty!");

        Node node = findNode(this.root, key);
        if (node == null) return;

        Node x;
        Node y = node;
        Color yOrigColor = y.color;

        if (node.left == TNULL) {
            x = node.right;
            transplant(node, node.right);
        } else if (node.right == TNULL) {
            x = node.left;
            transplant(node, node.left);
        } else {
            y = minimum(node.right);
            yOrigColor = y.color;
            x = y.right;

            if (y.p == node) {
                x.p = y;
            } else {
                transplant(y, y.right);
                y.right = node.right;
                y.right.p = y;
            }
            transplant(node, y);
            y.left = node.left;
            y.left.p = y;
            y.color = node.color;
        }
        if (yOrigColor == Color.BLACK) {
            deleteFixup(x);
        }
    }

    private void deleteFixup(Node node) {
        while (node != this.root && node.color == Color.BLACK) {
            if (node == node.p.left) {
                Node uncle = node.p.right;

                if (uncle.color == Color.RED) {
                    uncle.color = Color.BLACK;
                    node.p.color = Color.RED;
                    leftRotate(node.p);
                    uncle = node.p.right;
                }
                if (uncle.left.color == Color.BLACK && uncle.right.color == Color.BLACK) {
                    uncle.color = Color.RED;
                    node = node.p;
                } else {
                    if (uncle.right.color == Color.BLACK) {
                        uncle.left.color = Color.BLACK;
                        uncle.color = Color.RED;
                        rightRotate(uncle);
                        uncle = node.p.right;
                    }
                    uncle.color = node.p.color;
                    node.p.color = Color.BLACK;
                    uncle.right.color = Color.BLACK;
                    leftRotate(node.p);
                    node = this.root;
                }
            } else {
                Node uncle = node.p.left;

                if (uncle.color == Color.RED) {
                    uncle.color = Color.BLACK;
                    node.p.color = Color.RED;
                    rightRotate(node.p);
                    uncle = node.p.left;
                }
                if (uncle.left.color == Color.BLACK && uncle.right.color == Color.BLACK) {
                    uncle.color = Color.RED;
                    node = node.p;
                } else {
                    if (uncle.left.color == Color.BLACK) {
                        uncle.right.color = Color.BLACK;
                        uncle.color = Color.RED;
                        leftRotate(uncle);
                        uncle = node.p.left;
                    }
                    uncle.color = node.p.color;
                    node.p.color = Color.BLACK;
                    uncle.left.color = Color.BLACK;
                    rightRotate(node.p);
                    node = this.root;
                }
            }
        }
        node.color = Color.BLACK;
    }

    public boolean member(int key) {
        return findNode(this.root, key) != null;
    }

    private Node findNode(Node node, int key) {
        //Base case: reached bottom of tree, value not found
        if (node == TNULL) return null;

        //The value we're looking for is less than the current value
        if (key < node.key) return findNode(node.left, key);

        //The value we're looking for is greater than the current value
        else if (key > node.key) return findNode(node.right, key);
        
        //We found the node we were looking for
        else return node;
    }

   	public Node minimum(Node node) {
		while (node.left != TNULL) {
		    node = node.left;
		}
		return node;
	}

	public void rightRotate(Node x) {
        Node y = x.left;
		x.left = y.right;

		if (y.right != TNULL) {
			y.right.p = x;
		}
		y.p = x.p;
		if (x.p == null) {
			this.root = y;
		} else if (x == x.p.right) {
			x.p.right = y;
		} else {
			x.p.left = y;
		}
		y.right = x;
		x.p = y;
	}

	public void leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;

		if (y.left != TNULL) {
			y.left.p = x;
		}
		y.p = x.p;
		if (x.p == null) {
			this.root = y;
		} else if (x == x.p.left) {
			x.p.left = y;
		} else {
			x.p.right = y;
		}
		y.left = x;
		x.p = y;
	}

    private void transplant(Node x, Node y) {
		if (x.p == null) {
			this.root = y;
		} else if (x == x.p.left) {
			x.p.left = y;
		} else {
			x.p.right = y;
		}
		y.p = x.p;
    }

    @Override
    public String toString() {
        return traverse(this.root, "*", new StringBuilder());
    }

    private String traverse(Node node, String address, StringBuilder builder) {
        if (node == TNULL) return builder.toString();

        builder.append(address)
               .append(":").append(node.color == Color.BLACK ? "black" : "red")
               .append(":").append(node.key).append("\n");

        traverse(node.left, address + "L", builder);
        traverse(node.right, address + "R", builder);

        return builder.toString();
    }
}
