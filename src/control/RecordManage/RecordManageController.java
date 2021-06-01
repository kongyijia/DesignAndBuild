package control.RecordManage;
import control.Controller;
import control.MainFrame;
import model.Client;
import model.Video;
import util.config;
import view.staffManagement.RecordManagePanel;
import model.mapping.ClientMapping;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import model.mapping.VideoMapping;

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
    private TableRowSorter sorter;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private String[] factory = new String[]{"Video Name", "Learning Time", "Watching Time"};

    public RecordManageController() {
        super(config.RECORD_MANAGE_NAME, new RecordManagePanel());
        Recordmanagepanel = (RecordManagePanel) this.panel;

        pane = new JScrollPane();
        pane.setBounds(0,20,1200,510);
        pane.setVisible(true);
        Recordmanagepanel.add(pane);
        update();
    }

    @Override
    public void update() {
        Client client;
        client = MainFrame.getInstance().getClient();
        ArrayList<Client.RecordHistory> record = client.getRecordHistory();
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(factory);
        if(!record.isEmpty()){
            record.forEach(entry -> {
                int id = entry.getVideoId();
                ArrayList<Integer> id_array = new ArrayList<>();
                id_array.add(id);
                try {
                    ArrayList<Video> result = VideoMapping.findVideosByIdList(id_array);
                    String[] temp = {result.get(0).getName(), String.valueOf(entry.getLearningTime()),
                            String.valueOf(entry.getLatestPlayingDateTime())};
                    model.addRow(temp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
        if(table != null)
            pane.remove(table);
        table = new JTable(model);
        table.setModel(model);
        table.setBounds(0,20,1200,510);
        table.setAutoCreateRowSorter(true);
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
        pane.setViewportView(table);
    }
}
