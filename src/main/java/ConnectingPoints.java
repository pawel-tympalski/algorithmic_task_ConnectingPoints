

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;






public class ConnectingPoints {
    public static double minimumDistance(int[] x, int[] y) {
        
    	
    	Deque<Punkt> queuePunkt = new ArrayDeque<Punkt>();
    	//System.out.println("//////////////////////// Adding Points");
    	for(int i=0;i<x.length;i++){
    		Punkt p = new Punkt(new BigDecimal(x[i]),new BigDecimal(y[i]));
    		
    		//System.out.println(p);
    		queuePunkt.add(p);
    		
    	}
    	
ArrayList<Set<Punkt>> listOfSets = new ArrayList<Set<Punkt>>();
    	
    	for(Punkt p : queuePunkt){
    		HashSet<Punkt> set = new HashSet<Punkt>();
    		set.add(p);
    		listOfSets.add(set);
    	}
    	
    	//System.out.println("/////////////////////");
		//System.out.println("///////////////////");
    	
    	TreeSet<Edg> set = new TreeSet<Edg>(new Comparator<Object>(){

			public int compare(Object e1, Object e2) {
				
				if(
				(((Edg)e1).getDistance().compareTo(((Edg)e2).getDistance()) == 0 &&  ((Edg)e1).getFirst().getX() == ((Edg)e2).getFirst().getX()
				&& ((Edg)e1).getFirst().getY() == ((Edg)e2).getFirst().getY() 
				&& ((Edg)e1).getSecond().getX() == ((Edg)e2).getSecond().getX()
				&& 	((Edg)e1).getSecond().getY() == ((Edg)e2).getSecond().getY())
				||
				(((Edg)e1).getDistance().compareTo(((Edg)e2).getDistance()) == 0  &&  ((Edg)e1).getFirst().getX() == ((Edg)e2).getSecond().getX()
				&& ((Edg)e1).getFirst().getY() == ((Edg)e2).getSecond().getY()
				&& ((Edg)e1).getSecond().getX() == ((Edg)e2).getFirst().getX()
				&& 	((Edg)e1).getSecond().getY() == ((Edg)e2).getFirst().getY())
						
				){
					return 0;
				}
				else if(((Edg)e1).getDistance().compareTo(((Edg)e2).getDistance()) == -1 || 
						((((Edg)e1).getDistance().compareTo(((Edg)e2).getDistance()) == 0) && (((Edg)e1).getFirst().getX().doubleValue()  < ((Edg)e2).getFirst().getX().doubleValue()))
						|| ((((Edg)e1).getDistance().compareTo(((Edg)e2).getDistance()) == 0)&& ((Edg)e1).getFirst().getY().doubleValue() < ((Edg)e2).getFirst().getY().doubleValue())){
					return -1;
				}
	
    		
   
				
				return 1;
    			
			}
    		
    	});
    	
    	//System.out.println("//////////////////");
    	//System.out.println("Adding edges");
    	while(queuePunkt.size()> 1){
    		Punkt p = queuePunkt.pollFirst();
    		
    		for(Punkt temp : queuePunkt){
    			
    			
    			
    			BigDecimal roznicaSquareX = (p.getX().subtract(temp.getX())).pow(2);
    			BigDecimal roznicaSquareY = (p.getY().subtract(temp.getY())).pow(2);
    			BigDecimal distance =new BigDecimal(Math.sqrt(roznicaSquareX.doubleValue()  + roznicaSquareY.doubleValue())).setScale(9,BigDecimal.ROUND_HALF_UP);
    			
    			Edg edge = new Edg(p,temp,distance);
    			//System.out.println(edge);
    			set.add(edge);
    		}
    		
    		
    	}
    	
    	HashSet<Punkt> hashPunkt = new HashSet<Punkt>();
    	HashSet<Edg> hashEdge = new HashSet<Edg>();   	
    	//System.out.println("//////////////////");
    	//System.out.println("Poll out edes");
    	while(set.size()>0){
    		Edg edge = set.pollFirst();
    		//System.out.println(edge);
    		Punkt first = edge.getFirst();
    		Punkt second = edge.getSecond();
    		
    		boolean contain = false;
    		
    		for(Set<Punkt> s : listOfSets){
    			
    			if(s.contains(first) && s.contains(second)){
    				contain = true;
    				break;
    			}
    		}
    		
    		if(contain == false){
    			Set<Punkt> firstSet = null;
    			Set<Punkt> secondSet = null;
    			
    			for(Set<Punkt> se : listOfSets){
    				if(se.contains(first)){
    					firstSet = se;
    				}
    				if(se.contains(second)){
    					secondSet = se;
    				}
    				
    			}
    			
    			 firstSet.addAll(secondSet);
    			
    			 Set<Punkt> union = firstSet;
    			 listOfSets.remove(firstSet);
    			 listOfSets.remove(secondSet);
    			 listOfSets.add(union);
    			 
    			 //System.out.println("//// Add edge to HashEdgeSET - koncowy");
     			//System.out.println(edge);
     			//System.out.println("///////////////////////////////");
     			hashEdge.add(edge);
    			 
    		}
    		
    	}
    	
    	
    	
    	BigDecimal result = new BigDecimal(0);
    	//System.out.println("RESULT /////////////////////////");
    	for(Edg e : hashEdge){
    		result = result.add(e.getDistance());
    		
    		//System.out.println(e);
    	}
    	
        //write your code here
        return result.doubleValue();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        while(n > 200 || n < 1){
        	n = scanner.nextInt();
        }
        
        int[] x = new int[n];
        int[] y = new int[n];
        HashSet<Punkt> points = new HashSet<Punkt>();
        HashMap<Integer,Integer> mapX = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> mapY = new HashMap<Integer,Integer>();
        for(int i= -1000;i <= 1000;i++){
        	mapX.put(i, 0);
        }
        for(int i= -1000;i <= 1000;i++){
        	mapY.put(i, 0);
        }
        boolean notLine = false;
        for (int i = 0; i < n; i++) {
        	Punkt p = null;
        	int a =0;
        	int b=0;
        	do{
        		
        		do{
        			notLine = false;	
        		a = scanner.nextInt();
        		//System.out.println(a + " x rowna sie");
            		while(a > 1000 || a < -1000){
            			a = scanner.nextInt();
            		
            		}
            	b = scanner.nextInt();
            	//System.out.println(b + "y rowna sie");
            		while(b > 1000 || b < -1000){
            			b = scanner.nextInt();
            		}
            		 p = new Punkt(new BigDecimal(a),new BigDecimal(b));
        		}
        		while(points.contains(p));
           
        		if((mapX.get(a) < 2) && (mapY.get(b) < 2 )){
        			Integer xFromMap = mapX.get(a);
        			xFromMap = xFromMap + 1;
        			mapX.put(a, xFromMap);
        		
        			Integer yFromMap = mapY.get(b);
        			yFromMap = yFromMap + 1;
        			mapY.put(b, yFromMap);
        		}
        		else {
        			notLine = true;
        		}
        	}
        	while(notLine == true );
        	points.add(p);
        	x[i]=a;
        	y[i]=b;
        }
        System.out.println(minimumDistance(x, y));
    }
    
    static class Punkt {
    	BigDecimal x;
    	BigDecimal y;
		public BigDecimal getX() {
			return x;
		}
		public void setX(BigDecimal x) {
			this.x = x;
		}
		public BigDecimal getY() {
			return y;
		}
		public void setY(BigDecimal y) {
			this.y = y;
		}
		public Punkt(BigDecimal x, BigDecimal y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Punkt other = (Punkt) obj;
			if (x == null) {
				if (other.x != null)
					return false;
			} else if (!x.equals(other.x))
				return false;
			if (y == null) {
				if (other.y != null)
					return false;
			} else if (!y.equals(other.y))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Punkt [x=" + x + ", y=" + y + "]";
		}
		
    	
    	
    	
    }
    
    
    static class Edg{
    	Punkt first;
    	Punkt second;
    	BigDecimal distance;
		public Punkt getFirst() {
			return first;
		}
		public void setFirst(Punkt first) {
			this.first = first;
		}
		public Punkt getSecond() {
			return second;
		}
		public void setSecond(Punkt second) {
			this.second = second;
		}
		public BigDecimal getDistance() {
			return distance;
		}
		public void setDistance(BigDecimal distance) {
			this.distance = distance;
		}
		public Edg(Punkt first, Punkt second, BigDecimal distance) {
			super();
			this.first = first;
			this.second = second;
			this.distance = distance;
			
			
			
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((distance == null) ? 0 : distance.hashCode());
			result = prime * result + ((first == null) ? 0 : first.hashCode());
			result = prime * result + ((second == null) ? 0 : second.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edg other = (Edg) obj;
			if (distance == null) {
				if (other.distance != null)
					return false;
			} else if (!distance.equals(other.distance))
				return false;
			if (first == null) {
				if (other.first != null)
					return false;
			} else if (!first.equals(other.first))
				return false;
			if (second == null) {
				if (other.second != null)
					return false;
			} else if (!second.equals(other.second))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Edg [first=" + first + ", second=" + second + ", distance=" + distance + "]";
		}
		
    	
    	
    }
    
}