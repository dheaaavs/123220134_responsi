/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOdataperpus;
import DAOImplement.dataperpusimplement;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.connector;
import model.*;
/**
 *
 * @author Lab Informatika
 */
public class dataperpusDAO implements dataperpusimplement{
    Connection connection;
    int h = 0, harga;
    double rating;
    final String select = "SELECT * FROM buku;";
    final String insert = "INSERT INTO buku (judul,penulis,rating,harga) VALUES (?,?,?,?);";
    final String update = "UPDATE buku SET judul,penulis,rating,harga WHERE id=?;";
    final String delete = "delete from buku where id=?;";
    public dataperpusDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(dataperpus p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getPenulis());
            statement.setDouble(3, p.getRating());
            
            harga = p.getHarga();
            rating = p.getRating();
            h = (int) (harga + 500 + (rating*100));
            p.setHarga(h);
            statement.setInt(4, p.getHarga());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                p.setId(rs.getInt(1));
            }
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                statement.close();
            }catch (SQLException ex){
            ex.printStackTrace();
        }
        }
    }

    @Override
    public void update(dataperpus p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getPenulis());
            statement.setDouble(3, p.getRating());
            harga = p.getHarga();
            rating = p.getRating();
            h = (int) (harga + 500 + (rating*100));
            p.setHarga(h);
            statement.setInt(4, p.getHarga());
            statement.setInt(5, p.getId());
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                statement.close();
            }catch (SQLException ex){
            ex.printStackTrace();
        }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                statement.close();
            }catch (SQLException ex){
            ex.printStackTrace();
        }
        }
    }

    @Override
    public List<dataperpus> getAll() {
        List<dataperpus> dp = null;
        try {
            dp = new ArrayList<dataperpus>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                dataperpus p = new dataperpus();
                p.setId(rs.getInt("id"));
                p.setJudul(rs.getString("judul"));
                p.setPenulis(rs.getString("penulis"));
                p.setRating(rs.getDouble("rating"));
                p.setHarga(rs.getInt("harga"));
                dp.add(p);
            }
        }catch (SQLException ex){
                    Logger.getLogger(dataperpusDAO.class.getName()).log(Level.SEVERE,null,ex);
            }
            return dp;
        }
}


