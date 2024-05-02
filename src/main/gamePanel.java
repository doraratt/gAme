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
import object.OBJ_Key;
import object.SuperObject;
import tile.TileManager;

public class gamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize*scale;        //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth =  tileSize*maxScreenCol;     //768 pixels
    public final int screenHeight = tileSize*maxScreenRow;     //576 pixels
    
    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    //FPS
    int FPS = 60;
    
    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];
    
    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
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
        
        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime==true){
            drawStart = System.nanoTime();
        }
        
        
        //TILE
        tileM.draw(g2);
        
        //OBJECT
        for(int i=0; i<obj.length; i++){
            if(obj[i]!=null){
                obj[i].draw(g2, this);
            }
        }
        
        //PLAYER
        player.draw(g2);
        
        //UI
        ui.draw(g2);
        
        //DEBUG
        if(keyH.checkDrawTime==true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " +passed, 10, 400);
            System.out.println("Draw Time: " +passed);
        }
       
        
        g2.dispose();    
    }
    
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
        
    }
}
