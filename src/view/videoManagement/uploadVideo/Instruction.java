package view.videoManagement.uploadVideo;

import view.videoManagement.Const;

import javax.swing.*;
import java.awt.*;

public class Instruction extends JPanel {

    private final JLabel title = new JLabel(Const.VM_TITLE);
    private final JLabel note1 = new JLabel(Const.VM_NOTE1);
    private final JLabel note2 = new JLabel(Const.VM_NOTE2);
    private final JLabel note3 = new JLabel(Const.VM_NOTE3);
    private final JLabel note4 = new JLabel(Const.VM_NOTE4);

    public Instruction(){
        super();
        this.initPanel();
        this.initLayout();
    }

    private void initPanel(){
        this.setBackground(new Color(152, 239, 230, 151));
        this.setBounds(0,0, 800,100);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void initLayout(){

        this.title.setBounds(20, 0,800, 20);
        this.note1.setBounds(20, 20, 800, 20);
        this.note2.setBounds(20, 20 * 2, 800, 20);
        this.note3.setBounds(20, 20 * 3, 800, 20);
        this.note4.setBounds(20, 20 * 4, 800, 20);

        this.add(this.title);
        this.add(this.note1);
        this.add(this.note2);
        this.add(this.note3);
        this.add(this.note4);

        this.repaint();

    }

}
