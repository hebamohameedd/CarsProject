package Cars;

import Texture.TextureReader;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

public class AnimGLEventListener extends AnimListener {
    public AnimGLEventListener() {}

    String[] textureNames = {
            "map11.png", "Racecar1.png", "Newcar2.png", "Newcar2.png", "L.png",
    };
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    public int[] textures = new int[textureNames.length];

    int maxWidth = 100;
    int maxHeight = 100;

    double Leftbound = maxWidth * 0.143;
    double Rightbound = maxWidth * 0.85;

    double playerX = maxWidth / 2.0;
    double playerY = maxHeight / 5.0;

    double enemy1X, enemy1Y, enemy2X, enemy2Y;
    float enemySpeed = 1.5f;
    float backgroundset = 0.0f;
    float backgroundSpeed = 0.05f;
    double carAngle = 0.0;

    double playerYbottom = maxHeight * 0.1;
    double playerYtop = maxHeight * 0.9;
    Random random = new Random();

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glEnable(GL.GL_TEXTURE_2D); // Enable texture mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA); // Enable blending
        gl.glGenTextures(textureNames.length, textures, 0);

        // Load textures
        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels()
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

        enemyPostionsStart();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        backgroundset += backgroundSpeed;
        if (backgroundset > 1.0f) {
            backgroundset -= 1.0f;
        }

        DrawBackground(gl);
        DrawSprite(gl, playerX, playerY, 1, 2.0f);
        DrawSprite(gl, enemy1X, enemy1Y, 2, 2.0f);
        DrawSprite(gl, enemy2X, enemy2Y, 3, 2.0f);


        enemyCarPostion();
    }

  public void enemyCarPostion(){
        enemy1Y-=enemySpeed;
        enemy2Y-=enemySpeed;

        if(enemy1Y<0) {
            enemy1Y = maxHeight;
            enemy1X = Leftbound +random.nextDouble() * (Rightbound - Leftbound);

        }
        if (enemy2Y<0){
            enemy2Y=maxHeight+60;
            enemy2X= Leftbound +random.nextDouble() * (Rightbound - Leftbound);
        }
  }

  public void enemyPostionsStart(){
        enemy1X=Leftbound+random.nextDouble() *(Rightbound-Leftbound);
        enemy1Y=maxHeight;
        enemy2X=Leftbound+random.nextDouble() *(Rightbound-Leftbound);
        enemy2Y=maxHeight+60;
  }

    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]);

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f + backgroundset);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f + backgroundset);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f + backgroundset);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f + backgroundset);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawSprite(GL gl, double x, double y, int index, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);

        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 1.0, y / (maxHeight / 2.0) - 1.0, 0);

        if (x == playerX && y == playerY) {
            gl.glRotated(carAngle, 0, 0, 1);
        }

        gl.glScaled(0.1 * scale, 0.1 * scale, 1);

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {}

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_UP) {
                playerY += 2.0;
            } else if (keyCode == KeyEvent.VK_DOWN) {
                playerY -= 2.0;
            } else if (keyCode == KeyEvent.VK_LEFT) {
                playerX -= 2.0;
                carAngle = 8;
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                playerX += 2.0;
                carAngle = -8;
            }

            if (playerX < Leftbound) {
                playerX = Leftbound;
            } else if (playerX > Rightbound) {
                playerX = Rightbound;
            }
            if (playerY < playerYbottom) {
                playerY = playerYbottom;
            } else if (playerY > playerYtop) {
                playerY = playerYtop;
            }
        System.out.println(playerX);
        System.out.println(playerY);
        }


        public void handleKeyRelease(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            carAngle = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        handleKeyRelease(e.getKeyCode());
    }
}
