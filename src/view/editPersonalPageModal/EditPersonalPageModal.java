package view.editPersonalPageModal;

import model.Client;
import view.basicComponents.JLabelPro;

import javax.swing.*;

public class EditPersonalPageModal {

    private final JFrame jFrame = new JFrame("Edit Personal Information");
    private final JPanel jPanel = new JPanel();

    private final JLabelPro nickNameLabel = new JLabelPro("NickName: ");
    private final JLabelPro sexualityLabel = new JLabelPro("Sexuality: ");
    private final JLabelPro phoneLabel = new JLabelPro("Phone: ");
    private final JLabelPro emailLabel = new JLabelPro("Email: ");
    private final JLabelPro descriptionLabel = new JLabelPro("Description: ");

    private final String[] sexualityOpts = {"female", "male"};

    private final JTextField nickNameTextField = new JTextField();
    private final JComboBox<String> sexualityComboBox = new JComboBox<>(sexualityOpts);
    private final JTextField phoneTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JTextArea descriptionTextArea = new JTextArea();

    private final JButton confirmButton = new JButton("Confirm");
    private final JButton cancelButton = new JButton("Cancel");

    public EditPersonalPageModal(Client client){
        this.setNickNameTextField(client.getNickName());
        this.setSexualityComboBox(client.getSex());
        this.setPhoneTextField(client.getPhone());
        this.setEmailTextField(client.getEmail());
    }

    public JFrame getJFrame() {
        return this.jFrame;
    }

    public void setNickNameTextField(String nickName){
        this.nickNameTextField.setText(nickName);
    }

    public void setSexualityComboBox(int sexuality){
        this.sexualityComboBox.setSelectedIndex(sexuality);
    }

    public void setPhoneTextField(String phone){
        this.phoneTextField.setText(phone);
    }

    public void setEmailTextField(String email){
        this.emailTextField.setText(email);
    }

    public void setDescriptionTextArea(String description){
        this.descriptionTextArea.setText(description);
    }

    public String getNickNameTextField() {
        return nickNameTextField.getText();
    }

    public int getSexualityComboBox() {
        return sexualityComboBox.getSelectedIndex();
    }

    public String getPhoneTextField() {
        return phoneTextField.getText();
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    public String getDescriptionTextArea() {
        return descriptionTextArea.getText();
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void initModalLayout(){
        this.jFrame.setBounds(0,0,350,650);
        this.jPanel.setBounds(0,0,350,650);

        this.nickNameLabel.setBounds(40,20,100,60);
        this.sexualityLabel.setBounds(40,80,100,60);
        this.phoneLabel.setBounds(40,140,100,60);
        this.emailLabel.setBounds(40,200,100,60);
        this.descriptionLabel.setBounds(40,260,100,60);

        this.nickNameTextField.setBounds(140,30, 150,40);
        this.sexualityComboBox.setBounds(140,90,150,40);
        this.phoneTextField.setBounds(140,150,150,40);
        this.emailTextField.setBounds(140,210,150,40);
        this.descriptionTextArea.setBounds(40,320,250,150);

        this.confirmButton.setBounds(40,500,100,60);
        this.cancelButton.setBounds(190,500,100,60);

        this.jPanel.setLayout(null);

        this.jPanel.add(nickNameLabel);
        this.jPanel.add(sexualityLabel);
        this.jPanel.add(phoneLabel);
        this.jPanel.add(emailLabel);
        this.jPanel.add(descriptionLabel);
        this.jPanel.add(nickNameTextField);
        this.jPanel.add(sexualityComboBox);
        this.jPanel.add(phoneTextField);
        this.jPanel.add(emailTextField);
        this.jPanel.add(descriptionTextArea);
        this.jPanel.add(confirmButton);
        this.jPanel.add(cancelButton);

        this.jPanel.repaint();

        this.jFrame.add(jPanel);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setResizable(false);
        this.jFrame.setVisible(true);
    }

}
