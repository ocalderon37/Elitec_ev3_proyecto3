package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import entity.Proveedor;
import entity.TipoProveedor;
import model.ProveedorModel;
import model.TipoProveedorModel;

public class FrmCrudProveedor extends JFrame implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCodigo, txtNombre, txtRuc, txtEmail, txtTelefono, txtDireccion, txtContacto, txtPaginaWeb;
    private JComboBox<String> cboTipoProveedor;
    private JCheckBox chkEstado;
    private JTable table;
    private JButton btnListarTodos, btnBuscar, btnRegistrar, btnActualizar, btnEliminarLogico, btnEliminarFisico, btnLimpiar;

    ProveedorModel proveedorModel = new ProveedorModel();
    int posSeleccionado = -1;
    int idProveedorSeleccionado = -1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrmCrudProveedor frame = new FrmCrudProveedor();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrmCrudProveedor() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1520, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Mantenimiento de Proveedor");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblTitulo.setBounds(58, 11, 1014, 47);
        contentPane.add(lblTitulo);

        // Campos de formulario
        String[] labels = {"Código", "Nombre", "RUC", "Email", "Teléfono", "Dirección", "Contacto", "Página Web"};
        JTextField[] fields = {
            txtCodigo = new JTextField(), txtNombre = new JTextField(), txtRuc = new JTextField(),
            txtEmail = new JTextField(), txtTelefono = new JTextField(), txtDireccion = new JTextField(),
            txtContacto = new JTextField(), txtPaginaWeb = new JTextField()
        };

        int y = 80;
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(37, y, 100, 14);
            contentPane.add(lbl);
            fields[i].setBounds(150, y - 3, 220, 20);
            contentPane.add(fields[i]);
            y += 30;
        }

        JLabel lblTipoProveedor = new JLabel("Tipo Proveedor");
        lblTipoProveedor.setBounds(37, y, 100, 14);
        contentPane.add(lblTipoProveedor);
        cboTipoProveedor = new JComboBox<>();
        cboTipoProveedor.setBounds(150, y - 3, 220, 22);
        contentPane.add(cboTipoProveedor);
        
        y += 30;
        JLabel lblEstado = new JLabel("Estado");
        lblEstado.setBounds(37, y, 100, 14);
        contentPane.add(lblEstado);
        chkEstado = new JCheckBox("Activo");
        chkEstado.setBounds(150, y - 4, 97, 23);
        contentPane.add(chkEstado);

        // Botones de acción
        JButton[] botones = {
            btnListarTodos = new JButton("Listar Todos"),
            btnBuscar = new JButton("Buscar"),
            btnRegistrar = new JButton("Registrar"),
            btnActualizar = new JButton("Actualizar"),
            btnEliminarLogico = new JButton("Eliminar Lógico"),
            btnEliminarFisico = new JButton("Eliminar Físico"),
            btnLimpiar = new JButton("Limpiar")
        };

        int yBtn = 76;
        for (JButton btn : botones) {
            btn.addActionListener(this);
            btn.setBounds(410, yBtn, 170, 23);
            contentPane.add(btn);
            yBtn += 30;
        }

        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(610, 76, 870, 400);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(this);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Código", "Nombre", "RUC", "Email", "Teléfono", "Dirección", "Contacto", "Pág. Web", "F. Registro", "Tipo", "Estado"}
        ));
        scrollPane.setViewportView(table);

        listar();
        cargarComboTipo();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnListarTodos) listar();
        if (e.getSource() == btnBuscar) buscar();
        if (e.getSource() == btnRegistrar) registrar();
        if (e.getSource() == btnActualizar) actualizar();
        if (e.getSource() == btnEliminarLogico) eliminarLogico();
        if (e.getSource() == btnEliminarFisico) eliminarFisico();
        if (e.getSource() == btnLimpiar) limpiarFormulario();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            posSeleccionado = table.getSelectedRow();
            idProveedorSeleccionado = Integer.parseInt(table.getValueAt(posSeleccionado, 0).toString());
            txtCodigo.setText(table.getValueAt(posSeleccionado, 0).toString());
            txtNombre.setText(table.getValueAt(posSeleccionado, 1).toString());
            txtRuc.setText(table.getValueAt(posSeleccionado, 2).toString());
            txtEmail.setText(table.getValueAt(posSeleccionado, 3).toString());
            txtTelefono.setText(table.getValueAt(posSeleccionado, 4).toString());
            txtDireccion.setText(table.getValueAt(posSeleccionado, 5).toString());
            txtContacto.setText(table.getValueAt(posSeleccionado, 6).toString());
            txtPaginaWeb.setText(table.getValueAt(posSeleccionado, 7).toString());
            
            String tipo = table.getValueAt(posSeleccionado, 9).toString();
            for (int i = 0; i < cboTipoProveedor.getItemCount(); i++) {
                if (cboTipoProveedor.getItemAt(i).contains(tipo)) {
                    cboTipoProveedor.setSelectedIndex(i);
                    break;
                }
            }
            chkEstado.setSelected(table.getValueAt(posSeleccionado, 10).toString().equals("Activo"));
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void listar() {
        List<Proveedor> lista = proveedorModel.listaTodos();
        DefaultTableModel tabla = (DefaultTableModel) table.getModel();
        tabla.setRowCount(0);
        for (Proveedor p : lista) {
            Object[] fila = new Object[11];
            fila[0] = p.getIdProveedor();
            fila[1] = p.getNombre();
            fila[2] = p.getRuc();
            fila[3] = p.getEmail();
            fila[4] = p.getTelefono();
            fila[5] = p.getDireccion();
            fila[6] = p.getContacto();
            fila[7] = p.getPaginaWeb();
            fila[8] = p.getFechaRegistro();
            fila[9] = p.getTipoProveedor().getDescripcion();
            fila[10] = p.getEstado() == 1 ? "Activo" : "Inactivo";
            tabla.addRow(fila);
        }
    }

    public void cargarComboTipo() {
        TipoProveedorModel model = new TipoProveedorModel();
        List<TipoProveedor> tipos = model.listaTipoProveedor();
        cboTipoProveedor.removeAllItems();
        cboTipoProveedor.addItem("[ Seleccione ]");
        for (TipoProveedor t : tipos) {
            cboTipoProveedor.addItem(t.getIdTipoProveedor() + " - " + t.getDescripcion());
        }
    }

    public void registrar() {
        String nombre = txtNombre.getText().trim();
        String ruc = txtRuc.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (nombre.isEmpty() || ruc.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios.");
            return;
        }
        if (cboTipoProveedor.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de proveedor.");
            return;
        }

        TipoProveedor tp = new TipoProveedor();
        tp.setIdTipoProveedor(Integer.parseInt(cboTipoProveedor.getSelectedItem().toString().split(" - ")[0]));

        Proveedor p = new Proveedor();
        p.setNombre(nombre);
        p.setRuc(ruc);
        p.setEmail(email);
        p.setTelefono(telefono);
        p.setDireccion(direccion);
        p.setContacto(txtContacto.getText().trim());
        p.setPaginaWeb(txtPaginaWeb.getText().trim());
        p.setTipoProveedor(tp);
        p.setEstado(1);

        if (proveedorModel.insertaProveedor(p) > 0) {
            JOptionPane.showMessageDialog(this, "Proveedor registrado con éxito");
            listar();
            limpiarFormulario();
        }
    }

    public void actualizar() {
        if (idProveedorSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un proveedor para actualizar");
            return;
        }
        
        TipoProveedor tp = new TipoProveedor();
        tp.setIdTipoProveedor(Integer.parseInt(cboTipoProveedor.getSelectedItem().toString().split(" - ")[0]));

        Proveedor p = new Proveedor();
        p.setIdProveedor(Integer.parseInt(txtCodigo.getText().trim()));
        p.setNombre(txtNombre.getText().trim());
        p.setRuc(txtRuc.getText().trim());
        p.setEmail(txtEmail.getText().trim());
        p.setTelefono(txtTelefono.getText().trim());
        p.setDireccion(txtDireccion.getText().trim());
        p.setContacto(txtContacto.getText().trim());
        p.setPaginaWeb(txtPaginaWeb.getText().trim());
        p.setTipoProveedor(tp);
        p.setEstado(chkEstado.isSelected() ? 1 : 0);

        if (proveedorModel.actualizaProveedor(p) > 0) {
            JOptionPane.showMessageDialog(this, "Proveedor actualizado con éxito");
            listar();
            limpiarFormulario();
        }
    }

    public void eliminarLogico() {
        if (idProveedorSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un proveedor de la tabla");
            return;
        }
        List<Proveedor> lista = proveedorModel.listaPorPk(idProveedorSeleccionado);
        if (!lista.isEmpty()) {
            int nuevoEstado = lista.get(0).getEstado() == 1 ? 0 : 1;
            if (proveedorModel.actualizaEstado(idProveedorSeleccionado, nuevoEstado) > 0) {
                JOptionPane.showMessageDialog(this, "Estado actualizado correctamente");
                listar();
                limpiarFormulario();
            }
        }
    }

    public void eliminarFisico() {
        if (idProveedorSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un proveedor de la tabla");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (proveedorModel.eliminarFisico(idProveedorSeleccionado) > 0) {
                JOptionPane.showMessageDialog(this, "Eliminado correctamente");
                listar();
                limpiarFormulario();
            }
        }
    }

    public void buscar() {
        try {
            int id = Integer.parseInt(txtCodigo.getText().trim());
            List<Proveedor> lista = proveedorModel.listaPorPk(id);
            DefaultTableModel tabla = (DefaultTableModel) table.getModel();
            tabla.setRowCount(0);
            for (Proveedor p : lista) {
                Object[] fila = new Object[11];
                fila[0] = p.getIdProveedor();
                fila[1] = p.getNombre();
                fila[2] = p.getRuc();
                fila[3] = p.getEmail();
                fila[4] = p.getTelefono();
                fila[5] = p.getDireccion();
                fila[6] = p.getContacto();
                fila[7] = p.getPaginaWeb();
                fila[8] = p.getFechaRegistro();
                fila[9] = p.getTipoProveedor().getDescripcion();
                fila[10] = p.getEstado() == 1 ? "Activo" : "Inactivo";
                tabla.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido");
        }
    }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtRuc.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtContacto.setText("");
        txtPaginaWeb.setText("");
        cboTipoProveedor.setSelectedIndex(0);
        chkEstado.setSelected(false);
        idProveedorSeleccionado = -1;
    }
}