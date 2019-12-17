package game.renderer;

import game.Settings;
import game.game_objects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation extends Renderder {
    ArrayList<BufferedImage> images;
    int currentImageIndex;
    int nextImageCount = 6;
    public boolean isOnce;
    int countRender;

    public Animation(ArrayList<BufferedImage> images){
        this.images = images;
        this.currentImageIndex = 0;
        this.nextImageCount = 6;
        this.isOnce = false;
    }
    public Animation(ArrayList<BufferedImage> images,int nextImageCount){
        this.images = images;
        this.currentImageIndex = 0;
        this.nextImageCount = nextImageCount;
        this.isOnce = false;
    }
    public Animation(ArrayList<BufferedImage> images,int nextImageCount,boolean isOnce){
        this.images = images;
        this.currentImageIndex = 0;
        this.nextImageCount = nextImageCount;
        this.isOnce = isOnce;
    }

    @Override
    public void render(Graphics g, GameObject master){
        BufferedImage currentImage = this.images.get(this.currentImageIndex);
        if (master.velocity.getLength() != 0){
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate( -master.velocity.getAngle(),master.position.x,master.position.y );
            g2d.drawImage(currentImage,
                    (int) (master.position.x - master.anchor.x * currentImage.getWidth()),
                    (int) (master.position.y - master.anchor.y * currentImage.getHeight()),
                    null);
            g2d.setTransform(old);
        }else {
            g.drawImage(currentImage,
                    (int) (master.position.x - master.anchor.x * currentImage.getWidth()),
                    (int) (master.position.y - master.anchor.y * currentImage.getHeight()),
                    null);
        }


        this.countRender++;
        if(this.countRender > this.nextImageCount) {
            this.currentImageIndex++;
            if(this.currentImageIndex >= this.images.size()) {
                if(this.isOnce) {
                    master.deactive();
                } else {
                    this.currentImageIndex = 0;
                }
            }
            this.countRender = 0;
        }

    }
}
