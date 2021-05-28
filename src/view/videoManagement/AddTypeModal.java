package view.videoManagement;

import javax.swing.*;

public class AddTypeModal extends JFrame {

    private final JPanel jPanel = new JPanel();
    private final JLabel jLabel = new JLabel("Please type in a new type");
    private final JTextField jTextField = new JTextField();
    private final JButton confirmButton = new JButton("Confirm");
    private final JButton cancelButton = new JButton("Cancel");

    public AddTypeModal(){
        super();
        this.initPanel();
        this.initLayout();
        this.initFrame();
    }

    public String getJTextField() {
        return this.jTextField.getText();
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void initLayout(){
        this.jLabel.setBounds(20,20,200,30);
        this.jTextField.setBounds(20,50,200,30);
        this.confirmButton.setBounds(100,200,100,30);
        this.cancelButton.setBounds(250,200,100,30);

        this.jPanel.add(this.jLabel);
        this.jPanel.add(this.jTextField);
        this.jPanel.add(this.confirmButton);
        this.jPanel.add(this.cancelButton);

        this.jPanel.repaint();
    }

    public void initPanel(){
        this.jPanel.setLayout(null);
        this.jPanel.setBounds(0,0,400,300);
    }

    private void initFrame(){
        this.add(this.jPanel);
        this.setTitle("Add Video Type");
        this.setBounds(0,0,400,300);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

}
