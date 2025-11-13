/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.interfaces.Modelo.ConexionBD;
import java.sql.Connection;
/**
 *
 * @author driss
 */
public class TestConexionBDD {    
    public static void main(String[] args) {
        Connection conn = ConexionBD.getConnection();
    }
}
