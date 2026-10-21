package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.TipoProveedor;
import util.MySqlDBConexion;

public class TipoProveedorModel {

    public List<TipoProveedor> listaTipoProveedor() {
        List<TipoProveedor> lista = new ArrayList<TipoProveedor>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBConexion.getConexion();
            
            String sql = "SELECT * FROM tipo_proveedor";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            TipoProveedor obj = null;
            while (rs.next()) {
                obj = new TipoProveedor();
                obj.setIdTipoProveedor(rs.getInt(1));
                obj.setDescripcion(rs.getString(2));
                lista.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lista;
    }
}
