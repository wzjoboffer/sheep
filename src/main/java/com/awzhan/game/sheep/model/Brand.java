package com.awzhan.game.sheep.model;

import com.awzhan.game.sheep.ServiceConstant;
import lombok.Getter;
import lombok.Setter;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

@Getter
public class Brand extends Component {
    private static final String IMAGE_PATH = "images/";

    private final String name;

    @Setter
    private boolean isGray;

    private final Image image;

    private final Image grayImage;

    private final int width;

    private final int height;

    @Setter
    private int x;

    @Setter
    private int y;

    public Brand(String name) {
        this.name = name;
        this.isGray = false;

        this.image = getImage(name, false);
        this.grayImage = getImage(name, false);

        this.width = 50;
        this.height = 50;

        this.x = 0;
        this.y = 0;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                System.out.println("Mouse clicked");
                Brand brand = (Brand) event.getSource();
                brand.getParent().remove(brand);
            }
        });
    }

    private static Image getImage(final String name, final boolean isGray) {
        final String filename = isGray ? name + "_gray" : name;
        final URL imageUrl = Brand.class.getClassLoader().getResource(IMAGE_PATH + filename + ServiceConstant.PNG_EXT);
        return Toolkit.getDefaultToolkit().getImage(imageUrl);
    }

    @Override
    public void paint(Graphics graphics) {
        final Image selectedImage = isGray ? grayImage : image;
        graphics.drawImage(selectedImage, x, y, null);
    }
}
