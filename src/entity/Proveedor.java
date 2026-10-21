package entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String ruc;
    private String email;
    private String telefono;
    private String direccion;
    private String contacto;    
    private String paginaWeb;   
    private LocalDate fechaRegistro;
    private TipoProveedor tipoProveedor;
    private int estado; // <-- Campo agregado para soportar el estado y el borrado lógico
}

