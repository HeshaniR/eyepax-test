
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.StyleContext.SmallAttributeSet;

class Test {
	

	
    public static void main(String[] args)
    {
    	String[][] grid =Test.createGrid(5,5,1000);  
    	
    	getNode(grid,5,5);
       
    }
    
    
    public static String[][] createGrid(int x,int y,int n) {
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
    
    
    public static void  getNode(String[][] grid,int x,int y) {
    	
    	
     	ArrayList<Integer> visistedNodes = new ArrayList<Integer>();
    	ArrayList<Integer> largestBlock = new ArrayList<Integer>();
    	
    	
    	for(int i=0; i<x;i++) {
    		for(int j=0; j<y;j++) { 
    			String nodeColor = grid[i][j];
    			int currentNode =y*i+j; 
    			
    			if(!visistedNodes.contains(currentNode)) {
    				visistedNodes.add(currentNode);
    				ArrayList<Integer> sameColorNodes = new ArrayList<Integer>();
    				sameColorNodes = sameColorNodes(x,y,grid,nodeColor);
    				
    				largestBlock = getLargestBlock(currentNode, sameColorNodes, visistedNodes, y);
    			}
    				
    			
    			
    		}
    		
    	}
    	
    	System.out.println(largestBlock);
    	
    }
    
    public static ArrayList<Integer> sameColorNodes(int x, int y,String[][] grid, String color) {
    	
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
    
	public static ArrayList<Integer> getLargestBlock(int currentNode, ArrayList<Integer> sameColorNodes,ArrayList<Integer> visistedNodes, int y) {
	    	
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		int currentX = currentNode/y;
		int currentY = currentNode%y;
		
		sameColorNodes.forEach(n->{
			int tempX = n/y;
			int tempY = n%y;
			
			if(currentX == tempX +1 || currentX == tempX -1 || currentY == tempY +1 || currentY == tempY -1) {
				output.add(n);
				visistedNodes.add(n);
				
			}else {
				ArrayList<Integer> nonvisitedNodes = new ArrayList<Integer>();
				nonvisitedNodes.remove(0);
				output.addAll(getLargestBlock(n,nonvisitedNodes,visistedNodes,y));
			}
			
			
		});
		
		return output;
	    	
	   }
    
    
   
}
