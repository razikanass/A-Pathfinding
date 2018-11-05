/*
	node representation
	every node can keep track it's parent
	according to the A* algorithm we need to calculate some values
	to achieve the expected results h,c and f

	gCost is the cost of the path from the start node to last node of the grid
	hCost is a heuristic that estimates the cost of the cheapest path from last node of the grid to the target
	so as in the pseudo code of the algo fCost is equal to hCost + gCost
*/
public class Node {
	
	public int x,y;
	public boolean obstacle;
	public Node parent;
	
	public int gCost,hCost;
	
	public int fCost(){return gCost+hCost;}
	
	public Node(int x, int y, boolean obstacle) {
		super();
		this.x = x;
		this.y = y;
		this.gCost = 0;
		this.hCost = 0;
		this.obstacle = obstacle;
	}
	
	public boolean equals(Node node){
		return x == node.x && y == node.y;
	}
	
}
