/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TestModelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Win10
 */
public class universidadEjemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String url, user, password;
        String sql;
        PreparedStatement ps;
        ResultSet rs;

        url = "jdbc:mariadb://localhost:3306/universidad";
        user = "root";
        password = "";
        // TODO code application logic here
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, password);

            if (con != null) {
                JOptionPane.showMessageDialog(null, "Se establecio la conexion");
                System.out.println("Conexion establecida a :" + con.getCatalog());
            }

            //INSERT INTO
            sql = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sql);


            ps.setInt(1, 42991278);
            ps.setString(2, "lopez");
            ps.setString(3, "rodri");
            ps.setDate(4, Date.valueOf("2003-09-14"));
            ps.setBoolean(5, true);

            ps.executeUpdate();

            //Consultas
            sql = "SELECT * FROM alumno";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("ALUMNOS: ");
                System.out.println("ID: " + rs.getInt("idAlumno"));
                System.out.println("Apellido: " + rs.getString("apellido"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("DNI: " + rs.getInt("dni"));
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar driver: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion a la base de datos: " + ex.getMessage());
        }

    }
}
