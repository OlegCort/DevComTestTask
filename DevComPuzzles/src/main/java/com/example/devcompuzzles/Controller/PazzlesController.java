package com.example.devcompuzzles.Controller;

import com.example.devcompuzzles.Model.Pazzle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PazzlesController {

    static List<Pazzle> pazzles;


    public static List<Pazzle> splitImage(String path,String filename, int num) throws IOException {

        File f = new File(path+filename);
        int rows = getRows(num);
        int columns = num/rows;
        BufferedImage img = ImageIO.read(f);

        List<BufferedImage> pieces = new ArrayList<>();
        int chunkWidth = img.getWidth() / columns;
        int chunkHeight = img.getHeight() / rows;

        for (int x = 0; x < rows; x++)
            for (int y = 0; y < columns; y++)
            {
                pieces.add(img.getSubimage(chunkWidth*y, chunkHeight*x,chunkWidth, chunkHeight));
            }

        int counter = 1;
        List<Pazzle> result = new ArrayList<>();
        for(BufferedImage piece: pieces){
            Pazzle pazzle = new Pazzle();
            pazzle.setImage(piece);
            pazzle.setNumber(counter);
            File outputfile = new File("src/main/resources/static/Images/Pieces/piece"+counter+  ".png");
            ImageIO.write(piece, "png", outputfile);
            pazzle.setPath("/Images/Pieces/piece"+counter+  ".png");
            counter++;
            pazzle.setElect(false);
            pazzle.setUsed(false);
            pazzle.setRotated(false);
            result.add(pazzle);
        }

        return result;
    }

    public static int getRows(int num){
        int k = (int)Math.sqrt(num);
        while(num%k!=0)
            k--;
        return k;
    }

    public static void setElect(int number){
        if(pazzles.stream().filter(x->x.getNumber()==number).findFirst().get().isUsed()) return;
        pazzles.stream().forEach(x->x.setElect(false));
        pazzles.stream().filter(x->x.getNumber()==number).findFirst().get().setElect(true);
    }

    public static void rotatePazzle() throws IOException {
        Pazzle pazzle = pazzles.stream().filter(Pazzle::getElect).findFirst().orElse(null);
        if(pazzle == null)
            return;
        pazzle.setRotated(!pazzle.isRotated());




    }

    public static Image rotate(BufferedImage bufImg, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = bufImg.getWidth(), h = bufImg.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage result = new BufferedImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(bufImg, null);
        g.dispose();
        return result;
    }
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        BufferedImage buff = new BufferedImage(image.getWidth(null), image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buff.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return buff;
    }

}
