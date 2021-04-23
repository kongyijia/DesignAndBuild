package control.Userinformation;

import control.Controller;
import control.MainFrame;
import model.Client;
import util.config;
import view.Userinformation.RecordPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RecordPanelController extends Controller {
    private RecordPanel recordpanel;
    private DefaultTableModel model;

    public RecordPanelController(){
        super(config.RECORD_PANEL_NAME, new RecordPanel());
        recordpanel = (RecordPanel) this.panel;
        update();
    }
    @Override
    public void update() {
        String[] index = new String[]{"Video ID", "Time span", "Progress", "Watching time"};
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(index);
        JTable table = new JTable(model);
        ArrayList<Client.RecordHistory> record = MainFrame.getInstance().getClient().getRecordHistory();
        if(!record.isEmpty()){
            record.forEach(entry -> {
                String[] temp = {String.valueOf(entry.getVideoId()), String.valueOf(entry.getLearningTime()),
                        String.valueOf(entry.getProgress()), String.valueOf(entry.getLatestPlayingDateTime())};
                model.addRow(temp);
            });
        }
        table.setModel(model);
        table.setBounds(0,0,1200,510);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0,20,1200,510);
        pane.setVisible(true);
        recordpanel.add(pane);
    }
}
