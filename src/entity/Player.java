/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author e
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.gamePanel;
import main.KeyHandler;

public class Player extends Entity {
    gamePanel gPanel;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    
    public Player(gamePanel gPanel, KeyHandler keyH){
        this.gPanel = gPanel;
        this.keyH = keyH;
        
        screenX = gPanel.screenWidth/2 - (gPanel.tileSize/2);
        screenY = gPanel.screenHeight/2 - (gPanel.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
            
        
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        worldX = gPanel.tileSize*23;
        worldY = gPanel.tileSize*21;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true 
                || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
            
            //CHHECK TILE COLLISION
            collisionOn = false;
            gPanel.cChecker.checkTile(this);
            
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn==false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter>20){
                if(spriteNum==1){
                    spriteNum = 2;
                }
                else if(spriteNum==2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } 
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gPanel.tileSize, gPanel.tileSize);
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum==1){
                    image = up1;
                }
                if(spriteNum==2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image = down1;
                }
                if(spriteNum==2){
                    image = down2;
                }           
                break;
            case "left":
                if(spriteNum==1){
                    image = left1;
                }
                    image = left2;
                if(spriteNum==2){
                }
                break;
            case "right":
                if(spriteNum==1){
                    image = right1;
                }
                if(spriteNum==2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gPanel.tileSize, gPanel.tileSize, null);
    }
}
