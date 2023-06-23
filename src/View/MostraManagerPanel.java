package View;

import Business.ManagerBusiness;
import Business.Results.ManagerResult;
import Model.Manager;
import View.Listener.*;
import View.ViewModel.ManagerTableModel;
import View.ViewModel.RigaManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostraManagerPanel extends JPanel {

    private MainFrame frame;
    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    public MostraManagerPanel(MainFrame frame) {
        this.frame = frame;
        JLabel titleLabel = new JLabel("Managers ");
        Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        titleLabel.setFont(titleFont);
        titlePanel.add(titleLabel);
        contentPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        ArrayList<RigaManager> righe = new ArrayList<>();

        ManagerResult managerResult = ManagerBusiness.getInstance().caricaManagers();
        ArrayList<Manager> managers = managerResult.getManagers();

        for (int i = 0; i < managers.size(); i++) {
            RigaManager rigaManager = new RigaManager();
            JButton modificaButton = new JButton("Modifica");
            JButton eliminaButton = new JButton("Elimina");

            rigaManager.setUsername(managers.get(i).getUsername());
            rigaManager.setNomeManager(managers.get(i).getName());
            rigaManager.setCongnomeManager(managers.get(i).getSurname());
            rigaManager.setEmail(managers.get(i).getEmail());
            rigaManager.setDurataIncarico(managers.get(i).getDurataIncarico());
            rigaManager.setModificaButton(modificaButton);
            rigaManager.setEliminaButton(eliminaButton);

            //aggiungere action listener
            eliminaButton.addActionListener(new RemoveManagerListener(this.frame, managers.get(i)));

            righe.add(rigaManager);
        }


            ManagerTableModel tableModel = new ManagerTableModel(righe);
            JTable tabella = new JTable(tableModel);

            tabella.setRowHeight(100);
            tabella.setAutoscrolls(true);
            tabella.setRowSelectionAllowed(false);
            tabella.setFocusable(false);

            JScrollPane scrollPane = new JScrollPane(tabella);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            JTableButtonRenderer buttonRenderer = new JTableButtonRenderer();
            tabella.getColumn("Elimina").setCellRenderer(buttonRenderer);

            tabella.getColumn("Modifica").setCellRenderer(buttonRenderer);
            tabella.addMouseListener(new JTableButtonMouseListener(tabella));


            contentPanel.add(new JLabel("          "), BorderLayout.WEST);
            contentPanel.add(scrollPane, BorderLayout.CENTER);
            contentPanel.add(new JLabel("          "), BorderLayout.EAST);

            JButton tornaIndietroButton = new JButton("Torna indietro");
            tornaIndietroButton.addActionListener(new GoToMenuListener(this.frame));

            JButton creaManagerButton = new JButton("Crea un nuovo Manager");
            creaManagerButton.addActionListener(new GoToCreaManagerListener(this.frame));

            southPanel.setLayout(new FlowLayout());
            southPanel.add(tornaIndietroButton);
            southPanel.add(creaManagerButton);

            this.add(contentPanel, BorderLayout.CENTER);
            this.add(titlePanel, BorderLayout.PAGE_START);
            this.add(southPanel, BorderLayout.SOUTH);
        }
    }

