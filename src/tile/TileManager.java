/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.gamePanel;

/**
 *
 * @author e
 */
public class TileManager {
    gamePanel gPanel;
    Tile[] tile;
    int mapTileNum[][];
    
    
    public TileManager(gamePanel gPanel){
        this.gPanel = gPanel;
        
        tile = new Tile[10];
        mapTileNum = new int[gPanel.maxScreenCol][gPanel.maxScreenRow];
        
        getTileImage();
        loadMap("/maps/map01.txt");
        
    }
    
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col=0, row=0;
            
            while(col<gPanel.maxScreenCol && row<gPanel.maxScreenRow){
                String line = br.readLine();
                
                while(col<gPanel.maxScreenCol){
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col==gPanel.maxScreenCol){
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
        int col=0, row=0, x=0, y=0;
        
        while(col<gPanel.maxScreenCol && row<gPanel.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            
            g2.drawImage(tile[tileNum].image, x, y, gPanel.tileSize, gPanel.tileSize, null);
            col++;
            x += gPanel.tileSize;
            
            if(col==gPanel.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gPanel.tileSize;
            }
        }   
        
    }
}
