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
import main.UtilityTool;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    public Player(gamePanel gPanel, KeyHandler keyH){
        super(gPanel);
   
        this.keyH = keyH;
        
        screenX = gPanel.screenWidth/2 - (gPanel.tileSize/2);
        screenY = gPanel.screenHeight/2 - (gPanel.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
            
        setDefaultValues();
        getPlayerImage();                  //characterName paramater
    }
    
    public void setDefaultValues(){
        worldX = gPanel.tileSize*23;
        worldY = gPanel.tileSize*21;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){           //String characterName
        
        up1 = setup("maru_up_1");
        up2 = setup("maru_up_2");
        down1 = setup("maru_down_1");
        down2 = setup("maru_down_2");
        left1 = setup("maru_left_1");
        left2 = setup("maru_left_2");
        right1 = setup("maru_right_1");
        right2 = setup("maru_right_2");
        
        /*switch(characterName){                          //testing: to be added for character selection
            case "Maru":
                up1 = setup("maru_up_1");
                up2 = setup("maru_up_2");
                down1 = setup("maru_down_1");
                down2 = setup("maru_down_2");
                left1 = setup("maru_left_1");
                left2 = setup("maru_left_2");
                right1 = setup("maru_right_1");
                right2 = setup("maru_right_2");
            case "sage":
                
        }*/
        
    }
    
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/player/" +imageName+ ".png"));
            image = uTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
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
            
            //CHECK OBJECT COLLISION
            int objIndex = gPanel.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
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
    public void pickUpObject(int i){
        if(i!=999){
           String objectName = gPanel.obj[i].name;
           
           switch(objectName){
                case "Key":
                    gPanel.playSE(1);
                    hasKey++;
                    gPanel.obj[i] = null;
                    //gPanel.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gPanel.playSE(3);
                        hasKey--;
                        gPanel.obj[i] = null;
                        //gPanel.ui.showMessage("You opened the door!");
                    }
                    else{
                        gPanel.ui.showMessage("You need a key!");
                    }
                   break;
                case "Boots":
                    gPanel.playSE(2);
                    speed += 1;
                  //bootsOn = true;
                    gPanel.obj[i] = null;
                    //gPanel.ui.showMessage("Speed up!");
                    break;
                case "Chest":
                    gPanel.ui.gameFinished = true;
                    //gPanel.stopMusic();
                    gPanel.playSE(4);
                    break;
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
                    
                if(spriteNum==2){
                    image = left2;
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
