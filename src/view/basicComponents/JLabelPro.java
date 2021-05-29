package view.basicComponents;

import javax.swing.*;

public class JLabelPro extends JLabel {

    public JLabelPro(String str){
        super(str);
        this.setFont(new java.awt.Font("DIALOG",1,15));
    }

    public JLabelPro(String str, int size){
        super(str);
        this.setFont(new java.awt.Font("DIALOG",1,size));
    }

    public JLabelPro(String str, int size, int style){
        super(str);
        this.setFont(new java.awt.Font("DIALOG",style,size));
    }

}
