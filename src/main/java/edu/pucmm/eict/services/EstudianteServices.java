package edu.pucmm.eict.services;

import edu.pucmm.eict.encapsulaciones.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteServices {

    private static EstudianteServices instance;

    private EstudianteServices() {
    }

    public static EstudianteServices getInstance() {
        if (instance == null) {
            instance = new EstudianteServices();
        }
        return instance;
    }

    public List<Estudiante> listarEstudiantes() throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String query = "SELECT * FROM estudiante";

        // Use try-with-resources to automatically close resources
        try (Connection con = DataBaseServices.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setMatricula(rs.getString("matricula"));
                est.setNombre(rs.getString("nombre"));
                est.setApellido(rs.getString("apellido"));
                est.setTelefono(rs.getString("telefono"));
                lista.add(est);
            }
        }
        return lista;
    }

    public Estudiante getEstudiantePorMatricula(String matricula) throws SQLException {
        String query = "SELECT * FROM estudiante WHERE matricula = ?";
        Estudiante est = null;

        try (Connection con = DataBaseServices.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, matricula);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    est = new Estudiante();
                    est.setMatricula(rs.getString("matricula"));
                    est.setNombre(rs.getString("nombre"));
                    est.setApellido(rs.getString("apellido"));
                    est.setTelefono(rs.getString("telefono"));
                }
            }
        }
        return est;
    }

    public boolean crearEstudiante(Estudiante estudiante) throws SQLException {
        boolean ok = false;
        String query = "INSERT INTO estudiante(matricula, nombre, apellido, telefono) VALUES(?,?,?,?)";

        try (Connection con = DataBaseServices.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, estudiante.getMatricula());
            preparedStatement.setString(2, estudiante.getNombre());
            preparedStatement.setString(3, estudiante.getApellido());
            preparedStatement.setString(4, estudiante.getTelefono());

            int rows = preparedStatement.executeUpdate();
            ok = rows > 0;
        }
        return ok;
    }

    public boolean actualizarEstudiante(Estudiante estudiante) throws SQLException {
        boolean ok = false;
        String query = "UPDATE estudiante SET nombre=?, apellido=?, telefono=? WHERE matricula = ?";

        try (Connection con = DataBaseServices.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setString(2, estudiante.getApellido());
            preparedStatement.setString(3, estudiante.getTelefono());
            preparedStatement.setString(4, estudiante.getMatricula());

            int rows = preparedStatement.executeUpdate();
            ok = rows > 0;
        }
        return ok;
    }

    public boolean eliminarEstudiante(String matricula) throws SQLException {
        boolean ok = false;
        String query = "DELETE FROM estudiante WHERE matricula = ?";

        try (Connection con = DataBaseServices.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, matricula);
            int rows = preparedStatement.executeUpdate();
            ok = rows > 0;
        }
        return ok;
    }
}