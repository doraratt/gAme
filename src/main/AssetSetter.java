/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import object.OBJ_Key;

/**
 *
 * @author e
 */
public class AssetSetter {
    gamePanel gPanel;
    
    public AssetSetter(gamePanel gPanel){
        this.gPanel = gPanel;
    }
    
    public void setObject(){
        gPanel.obj[0] = new OBJ_Key();
        gPanel.obj[0].worldX = 23*gPanel.tileSize;
        gPanel.obj[0].worldY = 7*gPanel.tileSize;
        
        gPanel.obj[1] = new OBJ_Key();
        gPanel.obj[1].worldX = 23*gPanel.tileSize;
        gPanel.obj[1].worldY = 40*gPanel.tileSize;
    }
}
