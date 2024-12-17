package Cars;
import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Levels extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private CarsGame carsGame;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton backButton;
    private Image backgroundImage;
    private GLCanvas glCanvas;
    private Timer renderLoop;
    private int selectedMap;

    public Levels(CarsGame carsGame, CardLayout cardLayout, JPanel contentPanel) {
        this.carsGame = carsGame;
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        backgroundImage = new ImageIcon("src//Assets//HomePage1.png").getImage();
        setLayout(null);

        easyButton = createStyledButton("Easy");
        easyButton.setLocation(300, 150);
        easyButton.addActionListener(this::startGame);

        mediumButton = createStyledButton("Medium");
        mediumButton.setLocation(300, 225);
        mediumButton.addActionListener(this::startGame);

        hardButton = createStyledButton("Hard");
        hardButton.setLocation(300, 300);
        hardButton.addActionListener(this::startGame);

        backButton = createStyledButton("Back");
        backButton.setSize(150, 30);
        backButton.setLocation(20, 500);
        backButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(contentPanel, "Maps");
        });

        add(easyButton);
        add(mediumButton);
        add(hardButton);
        add(backButton);
    }

    private void startGame(ActionEvent e) {
        String difficulty = e.getActionCommand();
        System.out.println(difficulty + " level selected");

        String gameMode = carsGame.gameMode;
        if (gameMode.equals("Single Player")) {
            startSinglePlayerGame(difficulty);
        } else if (gameMode.equals("Multi Player")) {
            startMultiPlayerGame(difficulty);
        }
    }

    private void startSinglePlayerGame(String difficulty) {
        if (glCanvas != null) {
            contentPanel.remove(glCanvas);
        }

        glCanvas = new GLCanvas();

        if (selectedMap == 1 && ( difficulty.equals("Easy")||difficulty.equals("Medium")||difficulty.equals("Hard") )) {
            AnimGLSingleplayer1 animListener = new AnimGLSingleplayer1();
            glCanvas.addGLEventListener(animListener);
            glCanvas.addKeyListener(animListener);
        }
        else if (selectedMap == 2 && ( difficulty.equals("Easy")||difficulty.equals("Medium")||difficulty.equals("Hard") )) {
            AnimGLSingleplayer2 animListener = new AnimGLSingleplayer2();
            glCanvas.addGLEventListener(animListener);
            glCanvas.addKeyListener(animListener);
        }
        else if (selectedMap == 3 && ( difficulty.equals("Easy")||difficulty.equals("Medium")||difficulty.equals("Hard") )) {
            AnimGLSingleplayer3 animListener = new AnimGLSingleplayer3();
            glCanvas.addGLEventListener(animListener);
            glCanvas.addKeyListener(animListener);
        }

        glCanvas.setFocusable(true);
        contentPanel.add(glCanvas, "GameScreen");
        cardLayout.show(contentPanel, "GameScreen");
        glCanvas.requestFocusInWindow();

        if (renderLoop == null) {
            renderLoop = new Timer(16, event -> glCanvas.display());
        }
        renderLoop.start();
    }

    private void startMultiPlayerGame(String difficulty) {
        if (glCanvas != null) {
            contentPanel.remove(glCanvas);
        }

        glCanvas = new GLCanvas();

        if (selectedMap == 1 && ( difficulty.equals("Easy")||difficulty.equals("Medium")||difficulty.equals("Hard") )) {
            AnimGLMulti1 animListener = new AnimGLMulti1();
            glCanvas.addGLEventListener(animListener);
            glCanvas.addKeyListener(animListener);
        }
        else if (selectedMap == 2 && ( difficulty.equals("Easy")||difficulty.equals("Medium")||difficulty.equals("Hard") )) {
            AnimGLMulti2 animListener = new AnimGLMulti2();
            glCanvas.addGLEventListener(animListener);
            glCanvas.addKeyListener(animListener);
        }
        else if (selectedMap == 3 && ( difficulty.equals("Easy")||difficulty.equals("Medium")||difficulty.equals("Hard") )) {
            AnimGLMulti3 animListener = new AnimGLMulti3();
            glCanvas.addGLEventListener(animListener);
            glCanvas.addKeyListener(animListener);
        }

        glCanvas.setFocusable(true);
        contentPanel.add(glCanvas, "GameScreen");
        cardLayout.show(contentPanel, "GameScreen");
        glCanvas.requestFocusInWindow();

        if (renderLoop == null) {
            renderLoop = new Timer(16, event -> glCanvas.display());
        }
        renderLoop.start();
    }

    public void setSelectedMap(int mapNumber) {
        this.selectedMap = mapNumber;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setSize(200, 60);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
