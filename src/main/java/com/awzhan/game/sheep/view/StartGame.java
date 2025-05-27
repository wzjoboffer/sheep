package com.awzhan.game.sheep.view;

import javax.swing.JFrame;

import com.awzhan.game.sheep.model.Brand;
import com.awzhan.game.sheep.model.Cell;
import com.awzhan.game.sheep.model.GameMap;
import com.awzhan.game.sheep.model.Layer;
import com.awzhan.game.sheep.util.GameMapUtils;

public class StartGame extends JFrame {

    public StartGame() {
        init();

        renderGameMap();

        autoRefresh();
    }

    private void init() {
        this.setTitle("Sheep Game");
        this.setSize(450, 800);
        
        this.setLayout(null);
        this.setBounds(0, 0, 450, 800);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    private void renderGameMap() {
        final GameMap gameMap = GameMapUtils.buildGameMap(3);
        for (final Layer layer : gameMap.getLayers()) {
            renderLayer(layer);
        }
    }

    private void renderLayer(final Layer layer) {
        final Cell[][] matrix = layer.getMatrix();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                final Brand brand = matrix[row][col].getBrand();
                int x = col * 50 + layer.getOffsetX();
                int y = row * 50 + layer.getOffsetY();
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
        new StartGame();
    }
}
