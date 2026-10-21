package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoProveedor {
    private int idTipoProveedor;
    private String descripcion;

    @Override
    public String toString() {
        
        if (idTipoProveedor == -1) {
            return descripcion; 
        }
        
        return idTipoProveedor + ".- " + descripcion;
    }
}
