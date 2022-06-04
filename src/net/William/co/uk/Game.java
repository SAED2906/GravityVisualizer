package net.William.co.uk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.LinkedList;

import Physics.Forces;
import Physics.Gravity;
import Physics.ObjectsInFreeSpace;



public class Game implements Runnable {
	
	private Display display;
	public int width, height;
	public String title = "title";
	
	private Thread thread;
	private boolean running = false;
	String[] str = new String[9];
	ObjectsInFreeSpace[] Oifs = new ObjectsInFreeSpace[3];
	
	static int[] objx = new int[4];
	static int[] objy = new int[4];
	
	BigInteger[] bi = new BigInteger[2];
	
	private Graphics g;

	int objectnum = 2;
	int pixelsDone = 0;
	
	

	public Game(String title, int width, int height) {
		
		this.width = width;
		this.height = height;
		this.title = title ;
		
			int rx = (int) (Math.random()*1000)+1;
			int ry = (int) (Math.random()*1000)+1;
			rx = 400;
			ry = 400;
			objx[0] = rx;
			objy[0] = ry;

			int rx1 = (int) (Math.random()*1000)+1;
			int ry1 = (int) (Math.random()*1000)+1;
			rx1 = 500;
			ry1 = 500;
			objx[1] = rx1;
			objy[1] = ry1;
			
			int rx2 = (int) (Math.random()*1000)+1;
			int ry2 = (int) (Math.random()*1000)+1;
			objx[2] = rx2;
			objy[2] = ry2;
			
			int rx3 = (int) (Math.random()*1000)+1;
			int ry3 = (int) (Math.random()*1000)+1;
			objx[3] = rx3;
			objy[3] = ry3;
			
		//}
			System.out.println(objx[0] + " " + objy[0]);
		
		bi[0] = new BigInteger("59720000000000000000000");
		bi[1] = new BigInteger("44720000000000000000000");
		//bi[2] = new BigInteger("367200000000000000000000");
		
		System.out.println("Flag");
		
		//bi[3] = new BigInteger("59720000000000000000000");
		
		System.out.println("Flag");
		//System.out.println(Oifs.size() + "\n" + Oifs.get(0).getX() + "\n" + Oifs.get(0).getY() + "\n" + Oifs.get(1).getX() + "\n" + Oifs.get(1).getY());
		
		
	}
	
	private void init() {
		
		display = new Display(title,width,height);
		
		
	}
	private void tick() {
//		for (int i = 0; i < 4; i++) {
//		bi[i] = bi[i].subtract(new BigInteger("59720000000000000000000"));
//		}
		
	}
	private void render() {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear
		//if (LoopNum == 1) {
			//g.drawImage(Backdrop, 0, 0, null);
			
		//}


		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 1000; j++) {
				Forces[] tf = new Forces[100];
				
				int tempt = 0;
				for (int jj = 0; jj < objectnum; jj++) {
					tf[tempt] = Gravity.getForceOfAttraction(objx[jj], objy[jj], bi[jj] , j*1, i*1);
					tempt++;
				}
				
				Forces tempF = new Forces(0, 0);
				//System.out.println("flag4");
				for (int t = 0; t < objectnum; t++) {
					
					int Angle = tf[t].getAngle() - tempF.getAngle();
					double r = Math.sqrt(Math.pow(tf[t].getForce(), 2)+Math.pow(tempF.getForce(), 2)+(2*tf[t].getForce()*tempF.getForce()*Math.cos(Angle)));
					
					tempF = new Forces(r, Angle);
					
				}
				
				
				int r = (int) (255 * (tempF.getForce()/1000000000)/100);
				if (r > 255) {
					r = 255;
				}
				
				Color myWhite = new Color(0, r, r);
				//System.out.println((tempF.getForce()/1000000000)/100 + " --------- " + k + " - " + i);
				g.setColor(myWhite);
//				if (tempF.getAngle() <= 90) {
//					
//					g.drawLine(i*10, j*10, (int) -((Math.cos(tempF.getAngle())*1)) - i, (int) -((Math.sin(tempF.getAngle())*1)) - j);
//					
//				}
//				if (tempF.getAngle() <= 180 && tempF.getAngle() > 90) {
//					
//					g.drawLine(i*10, j*10, (int) -((Math.cos(tempF.getAngle())*1)) + i, (int) -((Math.sin(tempF.getAngle())*1)) - j);
//					
//				}
//				if (tempF.getAngle() <= 270 && tempF.getAngle() > 180) {
//									
//									g.drawLine(i*10, j*10, (int) -((Math.cos(tempF.getAngle())*10)) + i, (int) -((Math.sin(tempF.getAngle())*10)) + j);
//									
//								}
//				if (tempF.getAngle() <= 360 && tempF.getAngle() > 270) {
//					
//					g.drawLine(i*10, j*10, (int) -((Math.cos(tempF.getAngle())*1)) - i, (int) -((Math.sin(tempF.getAngle())*1)) + j);
//					
//				}
				
				g.fillRect(i*1, j*1, 1, 1);
				g.setColor(Color.WHITE);
				
				pixelsDone++;
				
				System.out.println(pixelsDone);
			}
		}
				
				pixelsDone = 0;
			g.setColor(Color.white);
		
	
		
		//End Draw
		bs.show();
		g.dispose();
		
		
		
		
	}
	
	public void run() {
		
		init();
		
		while(running) {
			tick();
			render();
			
		}
		stop();
	}
	
	public synchronized void start() {
		if (running) 
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		if (!running) 
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setX(int x) {
		objx[0] = x; 
	}
	public static void setY(int y) {
		objy[0] = y;
	}


}
