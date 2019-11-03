import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class GUI extends JFrame{
    private Manager_System manager_system;
    private JPanel addPanel;
    private JPanel deletePanel;
    private JScrollPane viewScrollPanel;
    private JTable tableView;
    private JPanel pathPanel;
    private JTextField  idText;
    private JTextField nameText;
    private JTextField gpaText;
    private JTextField addressText;
    private JTextField notesText;
    private JTextField pathText;
    private JTextField findText;
    private JButton addButtonSubmit;
    private JButton deleteButtonSubmit;
    private JButton findButtonSubmit;
    private JButton saveButtonSubmit;
    private JButton loadButtonSubmit;
    private JButton editButtonSubmit;
    private DefaultTableModel tableModel;

    private String[] column = {"ID","Name","GPA","Address","Notes"};
    private JPanel createJPanel(JComponent component1, JComponent component2){
        JPanel panel = new JPanel();
        panel.add(component1);
        panel.add(component2);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 10));
        return panel;
    }
    private void createFormInfoPanel(){
        if(addPanel!= null) return;
        addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(8, 1));

        idText = new JTextField(30);
        addPanel.add(createJPanel(new JLabel("ID     "),idText));

        nameText = new JTextField(30);
        addPanel.add(createJPanel(new JLabel("Name   "),nameText));

        gpaText = new JTextField(30);
        addPanel.add(createJPanel(new JLabel("GPA    "),gpaText));

        addressText = new JTextField(30);
        addPanel.add(createJPanel(new JLabel("Address"),addressText));

        notesText = new JTextField(30);
        addPanel.add(createJPanel(new JLabel("Notes  "),notesText));
    }
    private void createTextFieldPanel(){
        if(deletePanel!= null) return;
        deletePanel = new JPanel();
        deletePanel.setLayout(new GridLayout(3,1));

        findText = new JTextField(40);
        deletePanel.add(createJPanel(new JLabel("Enter ID: "),findText));
    }
    private void createViewScrollPanel(){
        if(viewScrollPanel!= null) return;
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(column);

        tableView = new JTable(tableModel);

        viewScrollPanel = new JScrollPane(tableView);
        tableView.setFillsViewportHeight(true);
    }
    private void createPathPanel(){
        if(pathPanel!=null) return;
        pathPanel = new JPanel();
        pathPanel.setLayout(new GridLayout(2,1));

        pathText = new JTextField(40);
        pathPanel.add(createJPanel(new JLabel("Enter filename: "),pathText));
    }
    private String[] getStringArrayFromList(ArrayList<String> arr){
        String[] str = new   String[arr.size()];
        for(int i = 0;i<arr.size();i++){
            str[i] = arr.get(i);
        }
        return str;
    }
    private String[] getFromData(){
        ArrayList<String> token=  new ArrayList<>();
        token.add(idText.getText());
        token.add(nameText.getText());
        token.add(gpaText.getText());
        token.add(addressText.getText());
        token.add(notesText.getText());
        return getStringArrayFromList(token);
    }
    private void setDataForm(String[] data){
        idText.setText(data[0]);
        nameText.setText(data[1]);
        gpaText.setText(data[2]);
        addressText.setText(data[3]);
        notesText.setText(data[4]);
    }
    private void createButton(){
        addButtonSubmit = new JButton("Add");
        addButtonSubmit.setSize(120,25);
        addButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] data = getFromData();
                Student s = new Student(data[0],data[1],Double.parseDouble(data[2]),data[3],data[4]);
                manager_system.AddStudent(s);
            }
        });

        deleteButtonSubmit = new JButton("Delete");
        deleteButtonSubmit.setSize(120,25);
        deleteButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_system.RemoveStudent_by_ID(findText.getText());
            }
        });

        findButtonSubmit = new JButton("Find");
        findButtonSubmit.setSize(120,25);
        findButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = manager_system.Search(findText.getText());
                if(s!=null){
                    ArrayList<String> data = new ArrayList<>();
                    data.add(s.getID());
                    data.add(s.getName());
                    data.add(s.getGPA().toString());
                    data.add(s.getAddress());
                    data.add(s.getNotes());
                    setDataForm(getStringArrayFromList(data));
                }
            }
        });

        saveButtonSubmit = new JButton("Save");
        saveButtonSubmit.setSize(100,25);
        saveButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                manager_system.Save(pathText.getText());
                }catch (Exception ignored){}
            }
        });

        loadButtonSubmit = new JButton("Load");
        loadButtonSubmit.setSize(120,25);
        loadButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File temp = new File(pathText.getText());
                if(!temp.exists()) return;
                try{

                manager_system.Load(pathText.getText());
                }catch (Exception ignored){}
            }
        });
        editButtonSubmit = new JButton("OK");
        editButtonSubmit.setSize(120,25);
        editButtonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student s = manager_system.Search(findText.getText());
                if(s!=null){
                    String[] data = getFromData();
                    Student info = new Student(data[0],data[1],Double.parseDouble(data[2]),data[3],data[4]);
                    manager_system.Update_Info(s,info);
                }
            }
        });
    }
    private boolean hasChange = false;
    private void UpdateTableView(){
                tableModel.setRowCount(0);
                var list = manager_system.Sort_by_ID(true);
                for(Student s:list){
                    tableModel.addRow(s.toStringArray());
                }
    }
    private GUI(){
        super("Student Manager");
        this.setSize(800, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        manager_system = new Manager_System();
        createButton();
        createFormInfoPanel();
        createTextFieldPanel();
        createViewScrollPanel();
        createPathPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel);

        JPanel viewPanel = new JPanel();
        mainPanel.add(viewPanel,BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(6,1));
        mainPanel.add(controlPanel,BorderLayout.WEST);


        JButton addButton = new JButton("ADD");
        addButton.setPreferredSize(new Dimension(90,30));
        JPanel addButtonPanel = new JPanel();
        addButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        addButtonPanel.add(addButton);
        controlPanel.add(addButtonPanel);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPanel.removeAll();
                viewPanel.add(addPanel);
                viewPanel.add(addButtonSubmit);
                viewPanel.repaint();
                viewPanel.revalidate();
            }
        });

        JButton deleteButton = new JButton("DELETE");
        deleteButton.setPreferredSize(new Dimension(90,30));
        JPanel deleteButtonPanel = new JPanel();
        deleteButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        deleteButtonPanel.add(deleteButton);
        controlPanel.add(deleteButtonPanel);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPanel.removeAll();
                viewPanel.add(deletePanel);
                viewPanel.add(deleteButtonSubmit);
                viewPanel.repaint();
                viewPanel.revalidate();
            }
        });

        JButton editButton = new JButton("EDIT");
        editButton.setPreferredSize(new Dimension(90,30));
        JPanel editButtonPanel = new JPanel();
        editButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        editButtonPanel.add(editButton);
        controlPanel.add(editButtonPanel);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPanel.removeAll();
                viewPanel.add(deletePanel);
                viewPanel.add(findButtonSubmit);
                viewPanel.add(addPanel);
                viewPanel.add(editButtonSubmit);
                viewPanel.repaint();
                viewPanel.revalidate();
            }
        });

        JButton viewButton = new JButton("VIEW");
        viewButton.setPreferredSize(new Dimension(90,30));
        JPanel viewButtonPanel = new JPanel();
        viewButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        viewButtonPanel.add(viewButton);
        controlPanel.add(viewButtonPanel);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPanel.removeAll();
                viewPanel.add(viewScrollPanel);
                UpdateTableView();
                viewPanel.repaint();
                viewPanel.revalidate();
            }
        });

        JButton saveButton = new JButton("SAVE");
        saveButton.setPreferredSize(new Dimension(90,30));
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        saveButtonPanel.add(saveButton);
        controlPanel.add(saveButtonPanel);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPanel.removeAll();
                viewPanel.add(pathPanel);
                viewPanel.add(saveButtonSubmit);
                viewPanel.repaint();
                viewPanel.revalidate();
            }
        });

        JButton loadButton = new JButton("LOAD");
        loadButton.setPreferredSize(new Dimension(90,30));
        JPanel loadButtonPanel = new JPanel();
        loadButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        loadButtonPanel.add(loadButton);
        controlPanel.add(loadButtonPanel);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewPanel.removeAll();
                viewPanel.add(pathPanel);
                viewPanel.add(loadButtonSubmit);
                viewPanel.repaint();
                viewPanel.revalidate();
            }
        });

        this.setVisible(true);
    }
    public static void main(String[] args) {
        new GUI();
    }
}
