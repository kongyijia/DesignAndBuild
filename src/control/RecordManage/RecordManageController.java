package control.RecordManage;
import control.Controller;
import model.Client;
import util.config;
import view.staffManagement.RecordManagePanel;
import model.mapping.ClientMapping;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Controller of watching record management for administrators.
 *
 * @author Shengbo Wang
 * @version 1.2
 * @see RecordManageController
 * @since 19 April 2021
 */
public class RecordManageController extends Controller {
    private RecordManagePanel Recordmanagepanel;
    private DefaultTableModel model;
    private TableRowSorter sorter;

    public RecordManageController() {
        super(config.RECORD_MANAGE_NAME, new RecordManagePanel());
        Recordmanagepanel = (RecordManagePanel) this.panel;
        update();
    }

    @Override
    public void update() {
        ArrayList<Client> clients = new ArrayList<>();
        String[] factory = new String[]{"Nickname", "Video ID", "Time span", "Progress", "Watching time",
                "Total watching time"};
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(factory);
        JTable table = new JTable(model);
        try {
            clients = ClientMapping.readAllClients();
            clients.forEach(client -> {
                AtomicInteger time = new AtomicInteger();
                ArrayList<Client.RecordHistory> record = client.getRecordHistory();
                if(!record.isEmpty()){
                    record.forEach(entry -> time.addAndGet(entry.getLearningTime()));
                    record.forEach(entry -> {
                        String[] temp = {client.getNickName(), String.valueOf(entry.getVideoId()),
                                String.valueOf(entry.getLearningTime()), String.valueOf(entry.getProgress()),
                                String.valueOf(entry.getLatestPlayingDateTime()), String.valueOf(time)};
                        model.addRow(temp);
                    });
                }
            });
            table.setModel(model);
            table.setBounds(0,0,1200,510);
            sorter = new TableRowSorter(model);
            table.setRowSorter(sorter);
            Recordmanagepanel.filterButton.addActionListener(e -> {
                String text = Recordmanagepanel.filterText.getText();
                if(text.length() == 0){
                    sorter.setRowFilter(null);
                }
                else{
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            });
            Recordmanagepanel.filterReset.addActionListener(e -> {
                sorter.setRowFilter(null);
            });
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(0,20,1200,510);
            pane.setVisible(true);
            Recordmanagepanel.add(pane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
