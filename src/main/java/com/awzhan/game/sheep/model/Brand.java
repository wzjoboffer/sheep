package com.awzhan.game.sheep.model;

import static com.awzhan.game.sheep.ServiceConstant.IMAGE_PATH;
import static com.awzhan.game.sheep.ServiceConstant.PNG_EXT;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import com.awzhan.game.sheep.util.GameMapUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Brand extends Component {

    private final String name;

    @Setter
    private boolean isGray;

    private final Image image;

    private final Image grayImage;

    @Setter
    private Color bgColor;

    @Setter
    private int topLeftX;

    @Setter
    private int topLeftY;

    @Setter
    private Cell cell;

    public Brand(String name) {
        this.name = name;
        this.isGray = false;

        this.image = getImage(name, false);
        this.grayImage = getImage(name, true);

        this.topLeftX = 0;
        this.topLeftY = 0;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                final Brand brand = (Brand) event.getSource();
                System.out.println("Mouse clicked on " + brand.getName());
                if (!brand.isGray()) {
                    // brand.getParent().remove(brand);
                    final EliminateBox eliminateBox = brand.getCell().getLayer().getGameMap().getEliminateBox();
                    eliminateBox.addToSlots(brand);

                    final Cell cell = brand.getCell();
                    cell.setState(0);
                    cell.setBrand(null);

                    // intersectsAll
                    GameMapUtils.refreshAll(brand.getCell().getLayer().getGameMap());
                }
            }
        });
    }

    private static Image getImage(final String name, final boolean isGray) {
        final String filename = isGray ? name + "_gray" : name;
        final URL imageUrl = Brand.class.getClassLoader().getResource(IMAGE_PATH + filename + PNG_EXT);
        return Toolkit.getDefaultToolkit().getImage(imageUrl);
    }

    @Override
    public void paint(Graphics graphics) {
        final Image selectedImage = isGray ? grayImage : image;
        graphics.drawImage(selectedImage, topLeftX, topLeftY, bgColor, null);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
