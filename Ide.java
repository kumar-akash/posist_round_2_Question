import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TimeZone;

class node {
	String timeStamp; // for the UTC time and date
	String data; // data that is being encrypted 
	int nodeNumber; // nodeNumber (integer value)
	node nodeId;  // unique string value
	node refernceNodeId; // string denoting the address of the parent node
	ArrayList<node> childReferenceNodeId; // string denoting the child of the node
	node genesisReferenceNodeId; // genesis refennce node 
	String hashValue; // hash values provided

	public node(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}
}

public class PosistQuestionnn {

	public static void main(String[] args) {

	}

	// Part-1
	
	
	//Creating Genesis function
	
	// I implementd it using level order traversal of generic tree....
	
	
	public static node createGenesisNode() {

		Scanner s = new Scanner(System.in);

		System.out.println("Please Enter The Root: ");
		int rootData = s.nextInt();
		node genesisNode = new node(rootData);

		Queue<node> pendingNodes = new LinkedList<node>();
		pendingNodes.add(genesisNode);

		int sum = 0;
		while (!pendingNodes.isEmpty()) {
			node currentNode = pendingNodes.poll();

			System.out.println("please enter the children of: " + currentNode.data);
			int children = s.nextInt();

			for (int i = 1; i <= children; i++) {
				System.out.println("please Enter " + i + "th child of " + currentNode.data);
				int childData = s.nextInt();
				sum += childData;
				node child = new node(childData);

				if (sum < rootData) {
					currentNode.childReferenceNodeId.add(child);
					pendingNodes.add(child);
				}

			}
		}

		return genesisNode;

	}

	//part 2
	
	
	
	//function to create set of child nodes 
	
	/ 
	// No input is being taken
	public static node takeInputOfChildNodesOfParticularNode(node root) {

		Scanner s = new Scanner(System.in);

		Queue<node> pendingNodes = new LinkedList<node>();
		pendingNodes.add(root);

		while (!pendingNodes.isEmpty()) {
			node currentNode = pendingNodes.poll();

			System.out.println("Enter the children of: " + currentNode.data);
			int children = s.nextInt();

			int sum = 0;
			for (int i = 1; i <= children; i++) {
				System.out.println("Enter " + i + "th child of " + currentNode.data);
				int childData = s.nextInt();
				sum += childData;
				node child = new node(childData);

				if (sum < root.nodeNumber) {
					currentNode.childReferenceNodeId.add(child);
					pendingNodes.add(child);
				}
			}
		}

		return root;

	}
	
	//Part -3
	
	//function to create a child node that originating from the particular node
	
	public static node createChildNode(node root){
		Scanner s=new Scanner(System.in);
		
		System.out.println("Enter the childNode:");
		int childNode=s.nextInt();
		
		int maxValue=root.nodeNumber;
		
		int sumOfChildren=childNode;
		for(int i=0;i<root.childReferenceNodeId.size();i++){
			sumOfChildren += root.childReferenceNodeId.get(i).nodeNumber;
		}
		
		if(sumOfChildren < maxValue){
			ArrayList<node> get=root.childReferenceNodeId;
			get.add(new node(childNode));
		}
		
		return root;
	}
	
	
	//part-4
	
	//Encrypting the data inside the node
	
	public static String encrpytByOwnerID(String data,String ownerID){
		int getHashCode=data.hashCode();
		return getHashCode+ownerID;
	}
	
	public static String encrpytByValue(String data,int value){
		int getHashCode=data.hashCode();
		return getHashCode+value+data;
	}
	
	public static String encrpytByOwnerName(String data,String ownerName){
		int getHashCode=data.hashCode();
		return getHashCode+ownerName;
	}
	
	
	//Part 8 
	
	//Function to find the longest chain of any node 
	public static int longestChainInGenesisNode(node genesisNode){
		if(genesisNode==null){
			return 0;
		}
		
		int maxLengthTillNow=Integer.MIN_VALUE;
		for(node child:genesisNode.childReferenceNodeId){
			int smallAns=longestChainInGenesisNode(child);
			if(smallAns>maxLengthTillNow){
				maxLengthTillNow=smallAns;
			}
		}
		
		return maxLengthTillNow+1;
	}
	
// 	/
// 	public static int longestChainInAnyNode(){
// 		int longestChain=Integer.MIN_VALUE;
		
		
		
// 		for(node child:genesisNode.childReferenceNodeId)
// 	}
        
        
//part -9 Function to finding thee longest chain of the any node as node is given as input        
        public static int longestChainInAnyNode(node particularNode){
		if(particularNode==null){
			return 0;
		}
		
		//implementing tree longest chain 
		
		int maxLengthTillNow=Integer.MIN_VALUE;
		for(node child:particularNode.childReferenceNodeId){
			int smallAns=longestChainInGenesisNode(child);
			if(smallAns>maxLengthTillNow){
				maxLengthTillNow=smallAns;
			}
		}
		
		return maxLengthTillNow+1;
	}

}