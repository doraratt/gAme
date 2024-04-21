/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author e
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Player;
import javax.swing.*;

public class gamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize*scale;        //48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth =  tileSize*maxScreenCol;     //768 pixels
    final int screenHeight = tileSize*maxScreenRow;     //576 pixels
    
    //FPS
    int FPS = 60;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH)
;    
    //Set player's default position
    int playerX = 100, playerY = 100, playerSpeed = 4;
    
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run(){      //delta or accumulator method
        double drawInterval = 1000000000/FPS;   //0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread!=null){
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta>=1){
                update();   //1 UPDATE: update info such as character position*/
                repaint();  //2 DRAW: draw the screen with the updated information
                delta--;
                drawCount++;
            }     
            if(timer>=1000000000){
                System.out.println("FPS: " +drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update(){
       player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        player.draw(g2);
        
        g2.dispose();    
    }
}
