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
                tileNum1 = gPanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gPanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gPanel.tileM.tile[tileNum1].collision==true || gPanel.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gPanel.tileSize;
                tileNum1 = gPanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gPanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gPanel.tileM.tile[tileNum1].collision==true || gPanel.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gPanel.tileSize;
                tileNum1 = gPanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gPanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gPanel.tileM.tile[tileNum1].collision==true || gPanel.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gPanel.tileSize;
                tileNum1 = gPanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gPanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gPanel.tileM.tile[tileNum1].collision==true || gPanel.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i=0; i<gPanel.obj.length; i++){
            if(gPanel.obj[i]!=null){
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
    //Get objet's solid area position
                gPanel.obj[i].solidArea.x = gPanel.obj[i].worldX + gPanel.obj[i].solidArea.x;            }
                gPanel.obj[i].solidArea.y = gPanel.obj[i].worldY + gPanel.obj[i].solidArea.y;
        
                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gPanel.obj[i].solidArea)){
                            System.out.println("up collision!");
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gPanel.obj[i].solidArea)){
                            System.out.println("down collision!");
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gPanel.obj[i].solidArea)){
                            System.out.println("left collision!");
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gPanel.obj[i].solidArea)){
                            System.out.println("right collision!");
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gPanel.obj[i].solidArea.x = gPanel.obj[i].solidAreaDefaultX;
                gPanel.obj[i].solidArea.y = gPanel.obj[i].solidAreaDefaultY;
        }
        return index;
    }
}
