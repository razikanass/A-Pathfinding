import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/*
	class that represents the Grid of the A*Pathfinding algorithm simulation
*/
public class Grid {
	//2D Array of nodes 
	public Node[][] grid;
	
	public int gridWidth = Test.W,
				gridHeight = Test.H;
	
	public static int gridSizeX,gridSizeY;
	
	public int nodeSize = Test.W/40;
	
	//the starting node and the target node in the simulation of the pathfinding
	public Start start;
	public Target target;
	
	public static ArrayList<Node> path = new ArrayList<Node>();
	
	//Grid Initialization
	public Grid(){
		
		gridSizeX = Math.round(gridWidth/nodeSize);
		gridSizeY = Math.round(gridHeight/nodeSize);
		
		grid = new Node[gridSizeX][gridSizeY];
		
		for(int x = 0; x < gridSizeX; x++){
			for(int y = 0; y < gridSizeY; y++){
				//Uniform random generated obstacles
				//20% chance to generate an obstacle
				float rand = (float)Math.random();
				float proba = 0.8f;
				if(rand < proba)
					grid[x][y] = new Node(x,y,false);
				else
					grid[x][y] = new Node(x,y,true);
			}
		}	
		
		start = new Start(grid[0][0].x,
						  grid[0][0].y,
						  false);
		
//		target = new Target(grid[gridSizeX-1][gridSizeY-1].x,
//							grid[gridSizeX-1][gridSizeY-1].y,
//							false);
		
		target = new Target(grid[5][5].x,
				grid[5][5].y,
				false);
		
	}

	//Grid rendering if node is an obstacle render it in black otherwise in white
	public void render(Graphics g){
		for(int x = 0; x < gridSizeX; x++){
			for(int y = 0; y < gridSizeY; y++){
				Node node = grid[x][y];
				g.setColor(Color.WHITE);
				if(node.obstacle){
					g.setColor(Color.BLACK);
				}
				g.fillRect(node.x*nodeSize, node.y*nodeSize, nodeSize, nodeSize);
			}
		}
	}
	
	//method that helps to get neighbours of a choosen node
	public ArrayList<Node> neighbours(Node node){
		ArrayList<Node> list = new ArrayList<Node>();
		
		for(int x = -1; x <= 1; x++){
			for(int y = -1; y <= 1; y++){
				if(x==0 && y==0){
					continue;
				}
				int i = node.x + x,
					j = node.y + y;
				
				if(i>=0 && i<gridSizeX && j>=0 && j<gridSizeY){
					list.add(grid[i][j]);
				}
			}
		}
		return list;
	}
	
	//computing the distance between two nodes (manhattan distance inluding diagonals)
	public int dist(Node a,Node b){
		int xDist = Math.abs(a.x - b.x),
			yDist = Math.abs(a.y - b.y);
		
		return (xDist > yDist)?14*yDist+10*(xDist-yDist):14*xDist+10*(yDist-xDist);
	}

}
