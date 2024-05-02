/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Key;

/**
 *
 * @author e
 */
public class UI {
    gamePanel gPanel;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    
    double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");
    
    public UI(gamePanel gPanel){
        this.gPanel = gPanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gPanel);
        keyImage = key.image;
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        if(gameFinished==true){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            
            String text;
            int textLength;
            int x;
            int y;
            
            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gPanel.screenWidth/2 - textLength/2;
            y = gPanel.screenHeight/2 - (gPanel.tileSize*3);
            g2.drawString(text, x, y);
            
            text = "Your time is: " +df.format(playTime)+ "";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gPanel.screenWidth/2 - textLength/2;
            y = gPanel.screenHeight/2 + (gPanel.tileSize*4);
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gPanel.screenWidth/2 - textLength/2;
            y = gPanel.screenHeight/2 + (gPanel.tileSize*2);
            g2.drawString(text, x, y);
            
            gPanel.gameThread = null;
        }
        else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gPanel.tileSize/2, gPanel.tileSize/2, gPanel.tileSize, gPanel.tileSize, null);
            g2.drawString("Key = " +gPanel.player.hasKey, 74, 65);

            //TIME
            playTime += (double)1/60;
            g2.drawString("Time:" +df.format(playTime), gPanel.tileSize*11, 65);
            
            //MESSAGE
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gPanel.tileSize/2, gPanel.tileSize*5);

                messageCounter++;

                if(messageCounter>120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
        
    }
}
