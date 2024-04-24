/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;

/**
 *
 * @author e
 */
public class CollisionChecker {
    gamePanel gPanel;
    
    public CollisionChecker(gamePanel gPanel){
        this.gPanel = gPanel;
    }
    
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x +
                entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y; 
        int entityBottomWorldY = entity.worldY + entity.solidArea.y +
                entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gPanel.tileSize;
        int entityRightCol = entityRightWorldX/gPanel.tileSize;
        int entityTopRow = entityTopWorldY/gPanel.tileSize;
        int entityBottomRow = entityBottomWorldY/gPanel.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gPanel.tileSize;
                
                break;
            case "down":
                break;
            case "left";
                break;
            case "right";
                break;
        }
    }
}
