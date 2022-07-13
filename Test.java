
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.StyleContext.SmallAttributeSet;

class Test {
	
	ArrayList<Integer> visistedNodes = new ArrayList<Integer>();
	
    public static void main(String[] args)
    
    {
    	Test test = new Test();
    	String[][] grid =test.createGrid(5,5,1000);  
    	
    	test.getNode(grid,5,5);
       
    }
    
    
    public  String[][] createGrid(int x,int y,int n) {
        String[][] colorGrid = new String[x][y];
        String color;
    	for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
            	color = getRandomColor(n);
            	colorGrid[i][j] = color;
    			System.out.println(color);
            }
    			
    	}
    	return colorGrid;
    }
    
    public static String getRandomColor(int n) {
    	Random random = new Random();
    	int hexNumber = random.nextInt(n);
    	String colorCode = String.format("%06x", hexNumber);
    	return "#"+colorCode;
    	
    }
    
    
    public  void  getNode(String[][] grid,int x,int y) {
    	
    	
     	
    	ArrayList<Integer> largestBlock = new ArrayList<Integer>();
    	ArrayList<Integer> adjusantBlock = new ArrayList<Integer>();
    	
    	
    	for(int i=0; i<x;i++) {
    		for(int j=0; j<y;j++) { 
    			String nodeColor = grid[i][j];
    			int currentNode =y*i+j; 
    			
    			if(!visistedNodes.contains(currentNode)) {
    				visistedNodes.add(currentNode);
    				adjusantBlock.add(currentNode);
    				ArrayList<Integer> sameColorNodes = new ArrayList<Integer>();
    				sameColorNodes = sameColorNodes(x,y,grid,nodeColor);
    				
    				adjusantBlock = getLargestBlock(currentNode, sameColorNodes, visistedNodes, y, adjusantBlock);
    				
    				if(adjusantBlock.size() > largestBlock.size()) {
    					largestBlock = adjusantBlock;
    				}
    			}
    				
    			
    			
    		}
    		
    	}
    	
    	System.out.println(largestBlock);
    	
    }
    
    public  ArrayList<Integer> sameColorNodes(int x, int y,String[][] grid, String color) {
    	
    	ArrayList<Integer> nodes = new ArrayList<Integer>();
    	
    	for(int i=0; i<x;i++) {
    		for(int j=0; j<y;j++) {
    			if(color == grid[i][j]) {
    				nodes.add(i*y + j);
    			}
    		}
    	}
    	
    	nodes.remove(0);
    	
    	return nodes;
    }
    
	public  ArrayList<Integer> getLargestBlock(int currentNode, ArrayList<Integer> sameColorNodes,ArrayList<Integer> visistedNodes, int y, ArrayList<Integer> adjusantBlock) {
	    			
		int currentX = currentNode/y;
		int currentY = currentNode%y;
		
		ArrayList<Integer> nonvisitedNodes = new ArrayList<Integer>();
		
		sameColorNodes.forEach(n->{
			int tempX = n/y;
			int tempY = n%y;
			
			if(
					(visistedNodes.contains(n)) &&
					(currentY == tempY && ( currentX == tempX -1 || currentX == tempX +1)) ||
					(currentX == tempX && ( currentY == tempY -1 || currentY == tempY +1)) 
					) {
				adjusantBlock.add(n);
				visistedNodes.add(n);
				
				nonvisitedNodes = sameColorNodes;
				nonvisitedNodes.remove(0);
				return getLargestBlock(n,nonvisitedNodes,visistedNodes,y, adjusantBlock);
			}
			
			
		});
		
		return adjusantBlock;
	    	
	   }
    
    
   
}
