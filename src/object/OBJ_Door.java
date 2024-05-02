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
public class OBJ_Door extends SuperObject {
    gamePanel gPanel;
    public OBJ_Door(gamePanel gPanel){
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage(image, gPanel.tileSize, gPanel.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
