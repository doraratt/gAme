/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.UtilityTool;
import main.gamePanel;

/**
 *
 * @author e
 */
public class TileManager {
    gamePanel gPanel;
    public Tile[] tile;
    public int mapTileNum[][];
    
    
    public TileManager(gamePanel gPanel){
        this.gPanel = gPanel;
        
        tile = new Tile[10];
        mapTileNum = new int[gPanel.maxWorldCol][gPanel.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/world01.txt");
        
    }
    
    public void getTileImage(){
           setup(0, "grass", false);
           setup(1, "wall", true);
           setup(2, "water", true);
           setup(3, "earth", false);
           setup(4, "trees", true);
           setup(5, "sand", false);
    }
    
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" +imageName+ ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gPanel.tileSize, gPanel.tileSize);
            tile[index].collision = collision;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col=0, row=0;
            
            while(col<gPanel.maxWorldCol && row<gPanel.maxWorldRow){
                String line = br.readLine();
                
                while(col<gPanel.maxWorldCol){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col==gPanel.maxWorldCol){
                    col = 0;
                    row++;
                }
               
            }
             br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol=0, worldRow=0;
        
        while(worldCol<gPanel.maxWorldCol && worldRow<gPanel.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol*gPanel.tileSize;
            int worldY = worldRow*gPanel.tileSize;
            int screenX = worldX-gPanel.player.worldX + gPanel.player.screenX;
            int screenY = worldY-gPanel.player.worldY + gPanel.player.screenY;
            
            if(worldX + gPanel.tileSize>gPanel.player.worldX - gPanel.player.screenX && 
               worldX - gPanel.tileSize<gPanel.player.worldX + gPanel.player.screenX && 
               worldY + gPanel.tileSize>gPanel.player.worldY - gPanel.player.screenY && 
               worldY - gPanel.tileSize<gPanel.player.worldY + gPanel.player.screenY){
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            
            
            worldCol++;
            
            
            if(worldCol==gPanel.maxWorldCol){
                worldCol = 0;              
                worldRow++;
                
            }
        }   
        
    }
}
