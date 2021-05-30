package view.basicComponents;

import javax.swing.*;
import java.awt.*;

public class ColorSquare extends JPanel {

    public ColorSquare(int size, Color color) {
        this.setPreferredSize(new Dimension(size, size));
        this.setBackground(color);
    }
}
