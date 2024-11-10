package dao;

import model.Biodata;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiodataDao {
    private final String url = "jdbc:mysql://localhost:3306/biodata";
    private final String user = "root";
    private final String password = "";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Menambahkan data ke database
    public void addBiodata(Biodata biodata) throws SQLException {
        String query = "INSERT INTO data_diri (nama, alamat) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, biodata.getNama());
            stmt.setString(2, biodata.getAlamat());
            stmt.executeUpdate();
        }
    }

    // Mengambil semua data dari database
    public List<Biodata> getAllBiodata() throws SQLException {
        List<Biodata> biodataList = new ArrayList<>();
        String query = "SELECT * FROM data_diri";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Biodata biodata = new Biodata(rs.getInt("id"), rs.getString("nama"), rs.getString("alamat"));
                biodataList.add(biodata);
            }
        }
        return biodataList;
    }

    // Mengupdate data di database
    public void updateBiodata(Biodata biodata) throws SQLException {
        String query = "UPDATE data_diri SET nama = ?, alamat = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, biodata.getNama());
            stmt.setString(2, biodata.getAlamat());
            stmt.setInt(3, biodata.getId());
            stmt.executeUpdate();
        }
    }

    // Menghapus data dari database
    public void deleteBiodata(int id) throws SQLException {
        String query = "DELETE FROM data_diri WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
