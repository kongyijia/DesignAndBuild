package view.editPersonalModal;

import model.Client;
import view.basicComponents.JLabelPro;

import javax.swing.*;

/**
 * UI for edit Personal Modal
 *
 * @author Zai Song
 * @version 1.0
 * @since 23 April 2021
 */
public class EditPersonalModal extends JPanel{

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

    public EditPersonalModal(Client client){
        super();
        this.setNickNameTextField(client.getNickName());
        this.setSexualityComboBox(client.getSex());
        this.setPhoneTextField(client.getPhone());
        this.setEmailTextField(client.getEmail());
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
        this.setBounds(0,0,350,650);

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

        this.setLayout(null);

        this.add(nickNameLabel);
        this.add(sexualityLabel);
        this.add(phoneLabel);
        this.add(emailLabel);
        this.add(descriptionLabel);
        this.add(nickNameTextField);
        this.add(sexualityComboBox);
        this.add(phoneTextField);
        this.add(emailTextField);
        this.add(descriptionTextArea);
        this.add(confirmButton);
        this.add(cancelButton);

        this.repaint();
        
    }

}
