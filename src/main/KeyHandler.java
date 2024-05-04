/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author e
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    gamePanel gPanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    //DEBUG
    boolean checkDrawTime = false;
    
    
    public KeyHandler(gamePanel gPanel){
        this.gPanel = gPanel;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        //TITLE STATE
        if(gPanel.gameState == gPanel.titleState){
            System.out.println("Key Pressed: " + code);
            if(gPanel.ui.titleScreenState == 0){
                if(code == KeyEvent.VK_W){
                    gPanel.ui.commandNum--;
                    if(gPanel.ui.commandNum < 0){
                        gPanel.ui.commandNum = 2;
                    }
                } 
                if(code == KeyEvent.VK_S){
                    gPanel.ui.commandNum++;
                    if(gPanel.ui.commandNum > 2){
                        gPanel.ui.commandNum = 0;
                    }
                }
                
                if(code == KeyEvent.VK_ENTER){
                    if(gPanel.ui.commandNum == 0){
                        gPanel.ui.titleScreenState = 1;
                    }
                        if(gPanel.ui.commandNum == 1){

                        }
                            if(gPanel.ui.commandNum == 2){
                                System.exit(0);
                            }
                }
            } 
            else if(gPanel.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_W){
                    gPanel.ui.commandNum--;
                    if(gPanel.ui.commandNum < 0){
                        gPanel.ui.commandNum = 3;
                    }
                } 
                if(code == KeyEvent.VK_S){
                    gPanel.ui.commandNum++;
                    if(gPanel.ui.commandNum > 3){
                        gPanel.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if(gPanel.ui.commandNum == 0){
                        System.out.println("Do some Jett stuff!");
                        gPanel.gameState = gPanel.playState;
                        
                    }
                    if(gPanel.ui.commandNum == 1){
                        System.out.println("Do some Sage stuff!");
                        gPanel.gameState = gPanel.playState;
                        
                    }
                    if(gPanel.ui.commandNum == 2){
                        System.out.println("Do some Killjoy stuff!");
                        gPanel.gameState = gPanel.playState;
                        
                    }
                    if(gPanel.ui.commandNum == 3){
                        gPanel.ui.titleScreenState = 0;   //go back to previous screen
                    }
                }
            } 
        }
        
        //PLAY STATE
     
            if(code == KeyEvent.VK_W){
                upPressed = true;
            } 
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_P){
                if(gPanel.gameState == gPanel.playState){
                    gPanel.gameState = gPanel.pauseState;
                }
                else if(gPanel.gameState == gPanel.pauseState){
                    gPanel.gameState = gPanel.playState;
                }
            }
        
            
        //DEBUG
            if(code == KeyEvent.VK_T){
                if(checkDrawTime == false){
                    checkDrawTime = true;
                }
                else if(checkDrawTime==true){
                    checkDrawTime = false;
                }

            }
        }
    

    @Override
    public void keyReleased(KeyEvent e) {
         int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    
}
