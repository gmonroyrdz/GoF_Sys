package pl;

import javax.swing.*;

import business.DepartamentoService;
import dal.entity.Empleado;

import java.awt.*;
import java.util.List;

public class GUI {
    private final DepartamentoService service = new DepartamentoService();
    private final DefaultListModel<Empleado> listModel = new DefaultListModel<>();

    public void createAndShowGui() {
        JFrame frame = new JFrame("Gestor Empleados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(10,10));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("ID Departamento:"));
        JTextField txtDept = new JTextField(8);
        top.add(txtDept);
        JButton btnBuscar = new JButton("Buscar");
        top.add(btnBuscar);
        main.add(top, BorderLayout.NORTH);

        JList<Empleado> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane spList = new JScrollPane(list);
        spList.setPreferredSize(new Dimension(300,400));
        main.add(spList, BorderLayout.WEST);

        JTextArea txtDetalle = new JTextArea();
        txtDetalle.setEditable(false);
        txtDetalle.setLineWrap(true);
        txtDetalle.setWrapStyleWord(true);
        JScrollPane spDetalle = new JScrollPane(txtDetalle);
        spDetalle.setPreferredSize(new Dimension(400,400));
        main.add(spDetalle, BorderLayout.CENTER);

        btnBuscar.addActionListener(ev -> {
            String s = txtDept.getText().trim();
            int id;
            try { id = Integer.parseInt(s); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<Empleado> empleados = service.searchById(id);
            listModel.clear();
            if (empleados != null && !empleados.isEmpty()) {
                empleados.forEach(listModel::addElement);
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontraron empleados para el departamento " + id, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Empleado sel = list.getSelectedValue();
                txtDetalle.setText(sel != null ? sel.toString() : "");
            }
        });

        frame.getContentPane().add(main);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
