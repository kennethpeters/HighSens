package com.highsens.game;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.highsens.game.tower.*;

public class Main extends JFrame
        implements ActionListener, MouseListener, KeyListener {

    private final GamePanel gamePanel;
    private final GameData gameData;
    private final Animator animator;
    private final JButton playButton;
    private final JButton quitButton;
    private static ArrowTower ArrowTower;
    private static BlueTower BlueTower;
    public ArrayList TowerPosition;
    boolean ArrowPlaceable = false;
    boolean BluePlaceable = false;

   
    public Main() { 
        TowerPosition = new ArrayList();
        setSize(600, 400);
        Container c = getContentPane();
        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel(animator, gameData);
        animator.setGamePanel(gamePanel);
        animator.setGameData(gameData);
        
        c.add(gamePanel, "Center");

        JPanel southPanel = new JPanel(); 
        playButton = new JButton("Play");
        southPanel.add(playButton);
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        gamePanel.addMouseListener(this);
        playButton.setFocusable(false);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);
        playButton.addActionListener(this);
        quitButton.addActionListener(this);
        
    }
    
    
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            playButton.setEnabled(false);
            gamePanel.startGame();
            
        } else if (e.getSource() == quitButton) {
            animator.running = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        
//        System.out.println("X: " + x);
//        System.out.println("Y: " + y);
        
        //Limits the clickable range to the button
        if(x >= 250 && x <= 350 && y >= 295 && y <= 325)
        {
            gameData.setWaves(gameData.wave = gameData.wave + 1);
            gameData.resetCreepCount();
        }
        
        if(x >= 520 && x <= 590 && y >= 250 && y <= 320)
        {
            BluePlaceable = true;
        }
        
        if(x >= 440 && x <= 530 && y >= 250 && y <= 320)
        {
            ArrowPlaceable = true;
        }
        
        // Only allow the placement of towers if we have enough money and have clicked the tower
        //Additionally only allow the placement of towers on any buttons.
        if(gameData.money >= 100 && BluePlaceable == true)
        {
            if(!(x >= 520 && x <= 590 && y >= 250 && y <= 320) && !(x >= 250 && x <= 350 && y >= 295 && y >= 325) && !(x >= 440 && x <= 530 && y >= 250 && y <= 320))
            {
                if(BluePlaceable == true)
                {
                    gameData.moneyManager("tower2", gameData.getMoney());
                    BlueTower = new BlueTower(x - 25, y - 50, gameData);
                    gameData.figures.add(BlueTower);
                    BluePlaceable = false;
                }
            }
        } 
        else if(gameData.money >= 50 && ArrowPlaceable == true)
        {
            if(!(x >= 520 && x <= 590 && y >= 250 && y <= 320) && !(x >= 250 && x <= 350 && y >= 295 && y >= 325) && !(x >= 440 && x <= 530 && y >= 250 && y <= 320))
            {
                if(ArrowPlaceable == true)
                {
                    gameData.moneyManager("tower1", gameData.getMoney());
                    ArrowTower = new ArrowTower(x - 25, y - 50, gameData);
                    gameData.figures.add(ArrowTower);
                    ArrowPlaceable = false;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public void mouseDragged(MouseEvent me) {

    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    public static void main(String[] args) {
    	final Splash splash = new Splash();
    	splash.showSplash();
    	
    	final MainScreen mainScreen = new MainScreen();
    	mainScreen.setVisible(true);
    	
    	/*JFrame game =  new Main();
        game.setTitle("Tower Defence");
        game.setResizable(false);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        */
    }
}
