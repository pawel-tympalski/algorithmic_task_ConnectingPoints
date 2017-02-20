package com.der.ConnectingPoints;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.junit.Test;

import com.der.ConnectingPoints.ConnectingPoints.Punkt;


/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Test
	public void firstTest(){
		String input = "4 0 0 0 1 1 0 1 1";
		//String input = "5 0 0 0 2 1 1 3 0 3 2";
		Scanner scanner = new Scanner(input).useDelimiter(" ");
		
		
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
        		System.out.println(a + " x rowna sie");
            		while(a > 1000 || a < -1000){
            			a = scanner.nextInt();
            		
            		}
            	b = scanner.nextInt();
            	System.out.println(b + "y rowna sie");
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
        double output = ConnectingPoints.minimumDistance(x, y);
        
        assertEquals(3.000000000,output,0.0000000001);
	}
	
	@Test
	public void secondTest(){
		String input = "5 0 0 0 2 1 1 3 0 3 2";
		Scanner scanner = new Scanner(input).useDelimiter(" ");
		
		int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        double output = ConnectingPoints.minimumDistance(x, y);
        
        assertEquals(7.064495102,output,0.0000000001);
	}
	
	@Test
	public void check(){
		BigDecimal a = new BigDecimal(5);
		BigDecimal b = new BigDecimal(5);
		BigDecimal c = new BigDecimal(15);
		//System.out.println( a.co compareTo(b) == 0);
		System.out.println(b == c);
	}
	
}
