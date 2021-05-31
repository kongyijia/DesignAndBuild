
package view.Userinformation;

import control.MainFrame;
import model.Client;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 *     Class {@code UserDescription} is a class extends JPanel.
 * </p>
 * <p>
 *     Initialize the User description panel.<br>
 * </p>
 * @see JPanel
 * @author Zhanao Zhang
 * @version V1.0
 */
public class UserDescription extends JPanel {
    public Client self = null;
    public JFrame parentframe;

    public UserDescription() {
        this.setLayout(null);// Depends on size
        this.setBounds(0, 0, 800, 510);
        update();
        this.setVisible(true);
    }

/**
 *
 * Fit the factory mode and broadcast the update message.
 * @return void
 * @author Zhanao Zhang
 * @date 2021/5/31 21:35
 * @version V1.0
 */
    public void update() {
        parentframe = MainFrame.getInstance();
        self = MainFrame.getInstance().getClient();
        this.removeAll();
        this.setBackground(new Color(226, 209, 160));
        this.add(new BuildProfilePanel());
        this.add(new BuildInformation(self));
        this.add(new BuildDescription(self));
        this.updateUI();
    }


}