/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.List;
import DAOdataperpus.dataperpusDAO;
import DAOImplement.dataperpusimplement;
import model.*;
import view.MainView;
/**
 *
 * @author Lab Informatika
 */
public class dataperpuscontroller {
    MainView frame;
    dataperpusimplement implementperpus;
    List<dataperpus> dp;
    
    public dataperpuscontroller(MainView frame){
        this.frame = frame;
        implementperpus = new dataperpusDAO();
        dp = implementperpus.getAll();
    }
    
    public void isitabel(){
        dp = implementperpus.getAll();
        modeltabeldataperpus mp = new modeltabeldataperpus(dp);
        frame.getTabelPerpus().setModel(mp);
    }
    
    public void insert(){
        dataperpus dp = new dataperpus();
        dp.setJudul(frame.getJudul().getText());
        dp.setPenulis(frame.getPenulis().getText());
        dp.setRating(Double.parseDouble(frame.getRating().getText()));
        dp.setHarga(Integer.parseInt(frame.getHarga().getText()));
        implementperpus.insert(dp);
    }
    
    public void update(){
        dataperpus dp = new dataperpus();
        dp.setJudul(frame.getJudul().getText());
        dp.setPenulis(frame.getPenulis().getText());
        dp.setRating(Double.parseDouble(frame.getRating().getText()));
        dp.setHarga(Integer.parseInt(frame.getHarga().getText()));
        dp.setId(Integer.parseInt(frame.getId().getText()));
        implementperpus.update(dp);
    }
    
    public void delete(){
        int id = Integer.parseInt(frame.getId().getText());
        implementperpus.delete(id);
    }
}
