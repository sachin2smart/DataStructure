package in.sachinshinde.bst;

//	https://leetcode.ca/all/510.html


/*
	Given a binary search tree and a node in it, 
  	find the in-order successor of that node in the BST.
        
        The successor of a node p is the node with the smallest key greater than p.val.
        You will have direct access to the node but not to the root of the tree. 
        Each node will have a reference to its parent node.
        
         
        
        Example 1:
        ---------
        Input: 
        root =
         	{
                	"$id": "1",
                	"left": {
                		"$id": "2",
                		"left": null,
                		"parent": {
                			"$ref": "1"
                		},
                		"right": null,
                		"val": 1
                	},
                	"parent": null,
                	"right": {
                		"$id": "3",
                		"left": null,
                		"parent": {
                			"$ref": "1"
                		},
                		"right": null,
                		"val": 3
                	},
                	"val": 2
		}
        p = 1
        Output: 2
        Explanation: 1's in-order successor node is 2. Note that both p and the return value is of Node type.
        
        Example 2:
        ---------
        Input: 
        root = 
        
        	{
                	"$id": "1",
                	"left": {
                		"$id": "2",
                		"left": {
                			"$id": "3",
                			"left": {
                				"$id": "4",
                				"left": null,
                				"parent": {
                					"$ref": "3"
                				},
                				"right": null,
                				"val": 1
                			},
                			"parent": {
                				"$ref": "2"
                			},
                			"right": null,
                			"val": 2
                		},
                		"parent": {
                			"$ref": "1"
                		},
                		"right": {
                			"$id": "5",
                			"left": null,
                			"parent": {
                				"$ref": "2"
                			},
                			"right": null,
                			"val": 4
                		},
                		"val": 3
                	},
                	"parent": null,
                	"right": {
                		"$id": "6",
                		"left": null,
                		"parent": {
                			"$ref": "1"
                		},
                		"right": null,
                		"val": 6
                	},
                	"val": 5
		}
		
        p = 6
        Output: null
        Explanation: There is no in-order successor of the current node, so the answer is null.
        
        Example 3:
        ---------
        Input: 
        root = 
        	{
                	"$id": "1",
                	"left": {
                		"$id": "2",
                		"left": {
                			"$id": "3",
                			"left": {
                				"$id": "4",
                				"left": null,
                				"parent": {
                					"$ref": "3"
                				},
                				"right": null,
                				"val": 2
                			},
                			"parent": {
                				"$ref": "2"
                			},
                			"right": {
                				"$id": "5",
                				"left": null,
                				"parent": {
                					"$ref": "3"
                				},
                				"right": null,
                				"val": 4
                			},
                			"val": 3
                		},
                		"parent": {
                			"$ref": "1"
                		},
                		"right": {
                			"$id": "6",
                			"left": null,
                			"parent": {
                				"$ref": "2"
                			},
                			"right": {
                				"$id": "7",
                				"left": {
                					"$id": "8",
                					"left": null,
                					"parent": {
                						"$ref": "7"
                					},
                					"right": null,
                					"val": 9
                				},
                				"parent": {
                					"$ref": "6"
                				},
                				"right": null,
                				"val": 13
                			},
                			"val": 7
                		},
                		"val": 6
                	},
                	"parent": null,
                	"right": {
                		"$id": "9",
                		"left": {
                			"$id": "10",
                			"left": null,
                			"parent": {
                				"$ref": "9"
                			},
                			"right": null,
                			"val": 17
                		},
                		"parent": {
                			"$ref": "1"
                		},
                		"right": {
                			"$id": "11",
                			"left": null,
                			"parent": {
                				"$ref": "9"
                			},
                			"right": null,
                			"val": 20
                		},
                		"val": 18
                	},
                	"val": 15
		}
		
        p = 15
        Output: 17
        
        Example 4:
        ---------
        Input: 
        root = 
        	{
                	"$id": "1",
                	"left": {
                		"$id": "2",
                		"left": {
                			"$id": "3",
                			"left": {
                				"$id": "4",
                				"left": null,
                				"parent": {
                					"$ref": "3"
                				},
                				"right": null,
                				"val": 2
                			},
                			"parent": {
                				"$ref": "2"
                			},
                			"right": {
                				"$id": "5",
                				"left": null,
                				"parent": {
                					"$ref": "3"
                				},
                				"right": null,
                				"val": 4
                			},
                			"val": 3
                		},
                		"parent": {
                			"$ref": "1"
                		},
                		"right": {
                			"$id": "6",
                			"left": null,
                			"parent": {
                				"$ref": "2"
                			},
                			"right": {
                				"$id": "7",
                				"left": {
                					"$id": "8",
                					"left": null,
                					"parent": {
                						"$ref": "7"
                					},
                					"right": null,
                					"val": 9
                				},
                				"parent": {
                					"$ref": "6"
                				},
                				"right": null,
                				"val": 13
                			},
                			"val": 7
                		},
                		"val": 6
                	},
                	"parent": null,
                	"right": {
                		"$id": "9",
                		"left": {
                			"$id": "10",
                			"left": null,
                			"parent": {
                				"$ref": "9"
                			},
                			"right": null,
                			"val": 17
                		},
                		"parent": {
                			"$ref": "1"
                		},
                		"right": {
                			"$id": "11",
                			"left": null,
                			"parent": {
                				"$ref": "9"
                			},
                			"right": null,
                			"val": 20
                		},
                		"val": 18
                	},
                	"val": 15
		}
		
        p = 13
        Output: 15
        
        Note:
        If the given node has no in-order successor in the tree, return null.
        It's guaranteed that the values of the tree are unique.
        Remember that we are using the Node type instead of TreeNode type so their string representation are different.

 */
public class InorderSuccessorInBST {
    class Node {
	public int val;
	public Node left;
	public Node right;
	public Node parent;
	
	Node() {};
	Node(int val) {
	    this.val = val;
	    this.left = null;
	    this.right = null;
	    this.parent = null;
	}
	Node(int val, Node parent) {
	    this.val = val;
	    this.left = null;
	    this.right = null;
	    this.parent = parent;
	}
	
	Node(int val, Node left, Node right) {
	    this.val = val;
	    this.left = left;
	    this.right = right;
	    this.parent = null;
	}
    };
	
    public Node inorderSuccessor(Node x) {
	if(x.right != null) {
	    Node successor = x.right;
	    while(successor.left != null)
		successor = successor.left;
	    return successor;
	} 
	else {	
	    Node child = x;
	    Node parent = x.parent;
	    // keep finding parent till child is on it's left
	    while (parent != null && child != parent.left) {
		child = parent;
	        parent = parent.parent;
	    }
	    return parent;
	}
    }
    
    public static void main(String[] args) {
	InorderSuccessorInBST inorderSuccessorInBST = new InorderSuccessorInBST();
	/*	
		 		 2
		 		/ \
		 	       1   3
	*/
	
	Node n2 = inorderSuccessorInBST.new Node(2);
	Node n1 = inorderSuccessorInBST.new Node(1, n2);
	Node n3 = inorderSuccessorInBST.new Node(3, n2);
	n2.left = n1;
	n2.right = n3;
	
	Node resultNode = inorderSuccessorInBST.inorderSuccessor(n1);	
	System.out.println(resultNode != null ? resultNode.val : null);	//	2
	
	resultNode = inorderSuccessorInBST.inorderSuccessor(n2);	
	System.out.println(resultNode != null ? resultNode.val : null);	//	3
	
	resultNode = inorderSuccessorInBST.inorderSuccessor(n3);	
	System.out.println(resultNode != null ? resultNode.val : null);	//	null
	
    }
}