package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import entity.Proveedor;
import entity.TipoProveedor;
import model.ProveedorModel;
import model.TipoProveedorModel;

public class FrmRegistraProveedor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtRuc;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtContacto;
    private JTextField txtPaginaWeb;
    private JComboBox<TipoProveedor> cboTipoProveedor;
    private JButton btnRegistrar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmRegistraProveedor frame = new FrmRegistraProveedor();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmRegistraProveedor() {
        setTitle("Registro de Proveedores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 440);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRO DE PROVEEDORES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitulo.setBounds(110, 15, 220, 20);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 50, 95, 14);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 47, 240, 20);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblRuc = new JLabel("RUC:");
        lblRuc.setBounds(40, 80, 95, 14);
        contentPane.add(lblRuc);

        txtRuc = new JTextField();
        txtRuc.setBounds(140, 77, 240, 20);
        contentPane.add(txtRuc);
        txtRuc.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(40, 110, 95, 14);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(140, 107, 240, 20);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(40, 140, 95, 14);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 137, 240, 20);
        contentPane.add(txtTelefono);
        txtTelefono.setColumns(10);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(40, 170, 95, 14);
        contentPane.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(140, 167, 240, 20);
        contentPane.add(txtDireccion);
        txtDireccion.setColumns(10);

        JLabel lblContacto = new JLabel("Contacto:");
        lblContacto.setBounds(40, 200, 95, 14);
        contentPane.add(lblContacto);

        txtContacto = new JTextField();
        txtContacto.setBounds(140, 197, 240, 20);
        contentPane.add(txtContacto);
        txtContacto.setColumns(10);

        JLabel lblPaginaWeb = new JLabel("Página Web:");
        lblPaginaWeb.setBounds(40, 230, 95, 14);
        contentPane.add(lblPaginaWeb);

        txtPaginaWeb = new JTextField();
        txtPaginaWeb.setBounds(140, 227, 240, 20);
        contentPane.add(txtPaginaWeb);
        txtPaginaWeb.setColumns(10);

        JLabel lblTipo = new JLabel("Tipo Proveedor:");
        lblTipo.setBounds(40, 260, 95, 14);
        contentPane.add(lblTipo);

        cboTipoProveedor = new JComboBox<TipoProveedor>();
        cboTipoProveedor.setBounds(140, 257, 240, 22);
        contentPane.add(cboTipoProveedor);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarProveedor();
            }
        });
        btnRegistrar.setBounds(160, 310, 110, 30);
        contentPane.add(btnRegistrar);

        
        cargaTipoProveedor();
    }

    public void cargaTipoProveedor() {
        TipoProveedorModel model = new TipoProveedorModel();
        List<TipoProveedor> lista = model.listaTipoProveedor();
        
        DefaultComboBoxModel<TipoProveedor> comboBoxModel = new DefaultComboBoxModel<TipoProveedor>();
        
        
        TipoProveedor objDefault = new TipoProveedor();
        objDefault.setIdTipoProveedor(-1); 
        objDefault.setDescripcion("[ Seleccione Tipo de Proveedor ]");
        comboBoxModel.addElement(objDefault);
        
        
        for (TipoProveedor x : lista) {
            comboBoxModel.addElement(x);
        }
        
        cboTipoProveedor.setModel(comboBoxModel);
    }

    
    public void registrarProveedor() {
        String nombre = txtNombre.getText().trim();
        String ruc = txtRuc.getText().trim();
        String email = txtEmail.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String contacto = txtContacto.getText().trim();
        String paginaWeb = txtPaginaWeb.getText().trim();
        TipoProveedor tipo = (TipoProveedor) cboTipoProveedor.getSelectedItem();

        // Validaciones básicas
        if (nombre.isEmpty() || ruc.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete los campos principales.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validación del ComboBox
        if (tipo == null || tipo.getIdTipoProveedor() == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un tipo de proveedor válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            cboTipoProveedor.requestFocus();
            return;
        }

        
        Proveedor p = new Proveedor();
        p.setNombre(nombre);
        p.setRuc(ruc);
        p.setEmail(email);
        p.setTelefono(telefono);
        p.setDireccion(direccion);
        p.setContacto(contacto);
        p.setPaginaWeb(paginaWeb);
        p.setTipoProveedor(tipo);

        // Insertar en la BD
        ProveedorModel model = new ProveedorModel();
        int resultado = model.insertaProveedor(p);

        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Proveedor registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void limpiarCampos() {
        txtNombre.setText("");
        txtRuc.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtContacto.setText("");
        txtPaginaWeb.setText("");
        if (cboTipoProveedor.getItemCount() > 0) {
            cboTipoProveedor.setSelectedIndex(0);
        }
        txtNombre.requestFocus();
    }
}