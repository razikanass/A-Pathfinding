
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JPanel;


public class Test extends JPanel implements Runnable,MouseListener{
	
	private Thread gameThread;
	private boolean played = false;
	
	public static int W = 500,
					  H = 500;
	
	public Grid grid;
	//set of currently discovered nodes that are not evaluated yet is called the open set
	ArrayList<Node> openSet  = new ArrayList<Node>(),
	//set of nodes already evaluated is called close set
					closedSet = new ArrayList<Node>();
	
	Node c;
	
	public boolean showPath;
	
	public Test(){
		grid = new Grid();
		openSet.add(grid.start);
		showPath = true;
		addMouseListener(this);
	}
	
	
	//useful to get the path from a node to another node 
	//every node have a parent node so we can easly calculate the path that we want
	//of course after building the links between nodes
	void getPath(Node start, Node end){
		ArrayList<Node> path = new ArrayList<Node>();
		Node node = end;
		while(!node.equals(start)){
			path.add(node);
			node = node.parent;
		}
		path.add(start);
		Collections.reverse(path);
		Grid.path = path;
	}
	//MAIN
	public static void main(String[] args) {
		//window instance
		new Window("A*Pathfinding",516,538,new Test());
	}
	
	public void start(){
		if(played)
			return;
		played = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	//main animation of the A* pathfinding is this run methode
	public void run() {
		
		while(played){
			while(!openSet.isEmpty()){
				//find the path
				Node current = openSet.get(0);
				for(Node node : openSet){
					if(current.fCost() > node.fCost()){
						current = node;
					}
				}
				
				if(current.equals(grid.target)){
					getPath(grid.start, current);
					break;
				}
				
				openSet.remove(current);
				closedSet.add(current);
				
				for(Node nodeNeighbor : grid.neighbours(current)){
					if(closedSet.contains(nodeNeighbor)){
						continue;
					}
					
					if(nodeNeighbor.obstacle){
						continue;
					}
					
					int tentativeG = current.gCost + grid.dist(current, nodeNeighbor);
					
					if(!openSet.contains(nodeNeighbor)){
						openSet.add(nodeNeighbor);
					}
					
					else if(tentativeG >= nodeNeighbor.gCost){
						continue;
					}
					
					nodeNeighbor.parent = current;
					nodeNeighbor.gCost = tentativeG;
					nodeNeighbor.hCost = grid.dist(nodeNeighbor, grid.target);
					nodeNeighbor.fCost();
				}
					
			}
//			else{
//				//no solution
//			}
			repaint();
			try{
				Thread.sleep(100);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		System.out.println("hello !!");
	
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		grid.render(g);
		
		for (Node node : closedSet) {
			g.setColor(Color.RED);
			g.fillRect(node.x*W/40, node.y*H/40, W/40, H/40);
		}
		
		for (Node node : openSet) {
			g.setColor(Color.GREEN);
			g.fillRect(node.x*W/40, node.y*H/40, W/40, H/40);
		}
		if(showPath){
			for(Node node : Grid.path){
				g.setColor(Color.MAGENTA);
				g.fillRect(node.x*W/40, node.y*H/40, W/40, H/40);
	//			g.setColor(Color.BLACK);
	//			g.drawString(node.x+","+node.y, (node.x+W/20)+(node.x+W/20)/2, (node.y+H/20)+(node.y+H/20)/2);
			}
		}
		
		g.setColor(grid.start.color);
		g.fillRect(grid.start.x*grid.nodeSize, grid.start.y*grid.nodeSize, grid.nodeSize, grid.nodeSize);
		
		g.setColor(grid.target.color);
		g.fillRect(grid.target.x*grid.nodeSize, grid.target.y*grid.nodeSize, grid.nodeSize, grid.nodeSize);
		
	}


	@Override
	public void mouseClicked(MouseEvent eve) {
		System.out.println("hello clicked !");
		showPath = !showPath;
	}

	@Override
	public void mouseEntered(MouseEvent eve) {}

	@Override
	public void mouseExited(MouseEvent eve) {}

	@Override
	public void mousePressed(MouseEvent eve) {}

	@Override
	public void mouseReleased(MouseEvent eve) {}

}
