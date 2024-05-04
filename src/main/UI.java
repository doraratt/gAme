/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import static java.awt.font.TextAttribute.FONT;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import object.OBJ_Key;

/**
 *
 * @author e
 */
public class UI {
    gamePanel gPanel;
    Graphics2D g2;
    Font misterPixel;
    //BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;
    public int titleScreenState = 0;    //0 - first screen, 1 - second screen
    
    double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");
    
    public UI(gamePanel gPanel){
        this.gPanel = gPanel;
        
        try{
            InputStream is = getClass().getResourceAsStream("/font/misterpixel.ttf");
            misterPixel = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException | IOException e){
            e.printStackTrace();
        }
        //OBJ_Key key = new OBJ_Key(gPanel);
        //keyImage = key.image;
        
        //OBJ_Key key = new OBJ_Key(gPanel);
        //keyImage = key.image;
    }
    
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(misterPixel);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        
        //TITLE STATE
        if(gPanel.gameState==gPanel.titleState){
            drawTitleScreen();
        }
        if(gPanel.gameState==gPanel.playState){
            
        } 
        if(gPanel.gameState==gPanel.pauseState){
            drawPauseScreen();
        }
    }
    public void drawTitleScreen(){
        if(titleScreenState == 0){
            //BACKGROUND
            g2.setColor(new Color(70, 120, 80));
            g2.fillRect(0, 0, gPanel.screenWidth, gPanel.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75F));
            String text = "Blue Girl Adventure";
            int x = getXforCenteredText(text);
            int y = gPanel.tileSize*3;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x+5, y+5);
            
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //BLUE GIRL/BOY IMAGE
            x = gPanel.screenWidth/2 - (gPanel.tileSize*2)/2;
            y += gPanel.tileSize*2;
            g2.drawImage(gPanel.player.down1, x, y, gPanel.tileSize*2, gPanel.tileSize*2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gPanel.tileSize*3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gPanel.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gPanel.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gPanel.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gPanel.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gPanel.tileSize, y);
            }
        }
        else if(titleScreenState == 1){
            
            //CLASS SELECTION SCREEN
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = "Select your character";
            int x = getXforCenteredText(text);
            int y = gPanel.tileSize*3;
            g2.drawString(text, x, y);
            
            text = "Jett";
            x = getXforCenteredText(text);
            y += gPanel.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gPanel.tileSize, y);
            }
            
            text = "Sage";
            x = getXforCenteredText(text);
            y += gPanel.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gPanel.tileSize, y);
            }
            
            text = "Killjoy";
            x = getXforCenteredText(text);
            y += gPanel.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gPanel.tileSize, y);
            }
            
            text = "Back";
            x = getXforCenteredText(text);
            y += gPanel.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x-gPanel.tileSize, y);
            }
        }
    }
    
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gPanel.screenHeight/2;  
        g2.drawString(text, x, y);
    }
    
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gPanel.screenWidth/2 - length/2;
        return x;
    }
}

