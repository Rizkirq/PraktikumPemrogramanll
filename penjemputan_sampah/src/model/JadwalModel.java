package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JadwalModel {

    private static final Logger LOGGER = Logger.getLogger(JadwalModel.class.getName());
    private Connection conn;

    public JadwalModel() {
        conn = DatabaseConnection.getConnection();
    }

    public List<String[]> getAllJadwal() {
        List<String[]> data = new ArrayList<>();
        String query = "SELECT * FROM jadwal";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
             
            while (rs.next()) {
                data.add(new String[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("tanggal"),
                    rs.getString("lokasi")
                });
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving jadwal data", e);
        } 
        return data;
    }

    public boolean addJadwal(String nama, String tanggal, String lokasi) {
        String query = "INSERT INTO jadwal (nama, tanggal, lokasi) VALUES (?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nama);
            pst.setString(2, tanggal);
            pst.setString(3, lokasi);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding new jadwal", e);
            return false;
        }
    }

    public boolean updateJadwal(int id, String nama, String tanggal, String lokasi) {
        String query = "UPDATE jadwal SET nama = ?, tanggal = ?, lokasi = ? WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nama);
            pst.setString(2, tanggal);
            pst.setString(3, lokasi);
            pst.setInt(4, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating jadwal for ID: " + id, e);
            return false;
        }
    }

    public boolean deleteJadwal(int id) {
        String query = "DELETE FROM jadwal WHERE id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting jadwal for ID: " + id, e);
            return false;
        }
    }
}
