package com.awzhan.game.sheep.view;

import javax.swing.JFrame;

import com.awzhan.game.sheep.model.Brand;
import com.awzhan.game.sheep.model.Cell;
import com.awzhan.game.sheep.model.Layer;
import com.awzhan.game.sheep.util.LayerUtils;

public class Start extends JFrame {

    public Start() {
        init();

        renderLayer();

        autoRefresh();
    }

    private void init() {
        this.setTitle("Sheep Game");
        this.setSize(450, 800);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.setBounds(0, 0, 450, 800);

        this.setVisible(true);
    }

    private void renderLayer() {
        final Layer layer = LayerUtils.buildLayer(6, 6);
        final Cell[][] matrix = layer.getMatrix();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                final Brand brand = matrix[row][col].getBrand();
                int x = col * 50;
                int y = row * 50;
                brand.setBounds(x, y, 50, 50);
                this.getContentPane().add(brand);
            }
        }
    }

    private void autoRefresh() {
        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new Start();
    }
}
