package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.Proveedor;
import entity.TipoProveedor;
import util.MySqlDBConexion;

public class ProveedorModel {
    
    public int insertaProveedor(Proveedor proveedor) {
        int insertados = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBConexion.getConexion();
            String sql = "INSERT INTO proveedor (nombre, ruc, email, telefono, direccion, contacto, paginaWeb, fechaRegistro, idTipoProveedor, estado) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getRuc());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getContacto());
            ps.setString(7, proveedor.getPaginaWeb());
            ps.setInt(8, proveedor.getTipoProveedor().getIdTipoProveedor());
            ps.setInt(9, proveedor.getEstado());
            insertados = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        return insertados;
    }
    
    public int actualizaProveedor(Proveedor proveedor) {
        int actualizados = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBConexion.getConexion();
            String sql = "UPDATE proveedor SET nombre=?, ruc=?, email=?, telefono=?, direccion=?, contacto=?, paginaWeb=?, idTipoProveedor=?, estado=? WHERE idProveedor=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getRuc());
            ps.setString(3, proveedor.getEmail());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getContacto());
            ps.setString(7, proveedor.getPaginaWeb());
            ps.setInt(8, proveedor.getTipoProveedor().getIdTipoProveedor());
            ps.setInt(9, proveedor.getEstado());
            ps.setInt(10, proveedor.getIdProveedor());
            actualizados = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        return actualizados;
    }
    
    public List<Proveedor> listaTodos() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBConexion.getConexion();
            String sql = "SELECT p.*, tp.descripcion FROM proveedor p INNER JOIN tipo_proveedor tp ON p.idTipoProveedor = tp.idTipoProveedor";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setRuc(rs.getString("ruc"));
                p.setEmail(rs.getString("email"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setContacto(rs.getString("contacto"));
                p.setPaginaWeb(rs.getString("paginaWeb"));
                if (rs.getDate("fechaRegistro") != null) {
                    p.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                }
                TipoProveedor tp = new TipoProveedor();
                tp.setIdTipoProveedor(rs.getInt("idTipoProveedor"));
                tp.setDescripcion(rs.getString("descripcion"));
                p.setTipoProveedor(tp);
                p.setEstado(rs.getInt("estado"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        return lista;
    }
    
    public List<Proveedor> listaPorPk(int idProveedor) {
        ArrayList<Proveedor> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBConexion.getConexion();
            String sql = "SELECT p.*, tp.descripcion FROM proveedor p INNER JOIN tipo_proveedor tp ON p.idTipoProveedor = tp.idTipoProveedor WHERE p.idProveedor = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("idProveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setRuc(rs.getString("ruc"));
                p.setEmail(rs.getString("email"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setContacto(rs.getString("contacto"));
                p.setPaginaWeb(rs.getString("paginaWeb"));
                if (rs.getDate("fechaRegistro") != null) {
                    p.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                }
                TipoProveedor tp = new TipoProveedor();
                tp.setIdTipoProveedor(rs.getInt("idTipoProveedor"));
                tp.setDescripcion(rs.getString("descripcion"));
                p.setTipoProveedor(tp);
                p.setEstado(rs.getInt("estado"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        return lista;
    }
    
    public int eliminarFisico(int idProveedor) {
        int eliminados = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBConexion.getConexion();
            String sql = "DELETE FROM proveedor WHERE idProveedor=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idProveedor);
            eliminados = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        return eliminados;
    }
    
    public int actualizaEstado(int idProveedor, int estado) {
        int actualizados = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = MySqlDBConexion.getConexion();
            String sql = "UPDATE proveedor SET estado=? WHERE idProveedor=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, estado);
            ps.setInt(2, idProveedor);
            actualizados = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        return actualizados;
    }
}