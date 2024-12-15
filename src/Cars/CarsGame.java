package Cars;

import com.sun.opengl.util.*;
import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarsGame extends JFrame {

    public static void main(String[] args) {
        new CarsGame();
    }

    public CarsGame() {
        // Initialize the OpenGL canvas and event listener
    AnimGLEventListener listener = new AnimGLEventListener();
        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);

        // Set up the layout
        getContentPane().setLayout(new BorderLayout());

        // Add the OpenGL canvas to the center
        getContentPane().add(glcanvas, BorderLayout.CENTER);
//
//        // Create the menu panel with buttons
//        JPanel menuPanel = new JPanel();
//        menuPanel.setLayout(new FlowLayout());
//
//        // Add buttons
//        JButton singlePlayerButton = new JButton("Single Player");
//        JButton multiPlayerButton = new JButton("Multi Player");
//        JButton howToPlayButton = new JButton("How to Play");
//
//        menuPanel.add(singlePlayerButton);
//        menuPanel.add(multiPlayerButton);
//        menuPanel.add(howToPlayButton);
//
//        // Add the menu panel to the bottom
//        getContentPane().add(menuPanel, BorderLayout.SOUTH);

        // Attach button actions
//        singlePlayerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                JOptionPane.showMessageDialog(CarsGame.this, "Starting Single Player Mode!");
//                startGame("Single Player");
//            }
//        });
//
//        multiPlayerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(CarsGame.this, "Starting Multi Player Mode!");
//                startGame("Multi Player");
//            }
//        });

//        howToPlayButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(CarsGame.this, "How to Play Instructions:\n1. Use arrow keys to move.\n2. Avoid obstacles.");
//            }
//        });

        // Start the OpenGL animator
        Animator animator = new FPSAnimator(glcanvas, 30);
        animator.start();

        // Configure the main window
        configureWindow();
        glcanvas.requestFocus();
    }

    // Method to configure the window properties
    private void configureWindow() {
        setTitle("Cars Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Adjusted size for better layout
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
    }

    // Method to start the game window (single or multi player)
//    private void startGame(String gameMode) {
//        // Create a new game window based on the selected mode
//        GameWindow gameWindow = new GameWindow(gameMode);
//        gameWindow.setVisible(true);
//        this.setVisible(false); // Hide the main menu window
//    }
}
