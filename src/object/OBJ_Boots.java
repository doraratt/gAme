/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.gamePanel;

/**
 *
 * @author e
 */
public class OBJ_Boots extends SuperObject{
    gamePanel gPanel;
    
    public OBJ_Boots(gamePanel gPanel){
        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
