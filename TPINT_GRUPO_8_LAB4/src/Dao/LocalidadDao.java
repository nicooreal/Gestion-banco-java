package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dominio.Localidad;

public class LocalidadDao {
	
	
	private static final String ListarLocalidades = "SELECT * FROM localidades WHERE id_provincia = ?";
	private static final String ObtenerLocalidadPorId = "SELECT * FROM localidades WHERE id_localidad = ?";
	
	
	// Método para listar localidades por provincia
    public ArrayList<Localidad> listarLocalidadesPorProvincia(int idProvincia) {
        ArrayList<Localidad> localidades = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = conexionDB.getConnection(); // Asegúrate de tener la clase conexionDB que maneje la conexión a la base de datos
            pstmt = conexion.prepareStatement(ListarLocalidades);
            pstmt.setInt(1, idProvincia);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_localidad");
                String nombre = rs.getString("localidad");
                int provinciaId = rs.getInt("id_provincia");
                Localidad localidad = new Localidad(id, nombre, provinciaId);
                localidades.add(localidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return localidades;
    }

    // Método para obtener una localidad por su ID
    public Localidad LocalidadPorId(int id) {
        Localidad localidad = null;
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = conexionDB.getConnection();
            pstmt = conexion.prepareStatement(ObtenerLocalidadPorId);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("localidad");
                int idProvincia = rs.getInt("id_provincia");
                localidad = new Localidad(id, nombre, idProvincia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return localidad;
    }

}
