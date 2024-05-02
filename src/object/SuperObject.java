/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.UtilityTool;
import main.gamePanel;

/**
 *
 * @author e
 */
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();
    
    public void draw(Graphics2D g2, gamePanel gPanel){
        int screenX = worldX-gPanel.player.worldX + gPanel.player.screenX;
        int screenY = worldY-gPanel.player.worldY + gPanel.player.screenY;
            
        if(worldX + gPanel.tileSize>gPanel.player.worldX - gPanel.player.screenX && 
            worldX - gPanel.tileSize<gPanel.player.worldX + gPanel.player.screenX && 
            worldY + gPanel.tileSize>gPanel.player.worldY - gPanel.player.screenY && 
            worldY - gPanel.tileSize<gPanel.player.worldY + gPanel.player.screenY){
                
            g2.drawImage(image, screenX, screenY, gPanel.tileSize, gPanel.tileSize, null);
        }
    }
}
