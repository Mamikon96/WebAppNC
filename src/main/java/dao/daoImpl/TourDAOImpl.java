package dao.daoImpl;

import dao.DAOFactory;
import dao.TourDAO;
import dao.VoucherDAO;
import models.Tour;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TourDAOImpl implements TourDAO {
    public boolean addTour(Tour tour) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO last_minute_tour" +
                    "(tour_id, voucher_id) " +
                    "VALUES (last_minute_tour_seq.NEXTVAL, ?)");
            ps.setLong(1, tour.getVoucher().getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return flag;
    }

    public boolean updateTour(Tour tour) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE last_minute_tour SET " +
                    "voucher_id = ?" +
                    "WHERE tour_id = ?");
            ps.setLong(1, tour.getVoucher().getId());
            ps.setLong(2, tour.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return flag;
    }

    public boolean deleteTour(Tour tour) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM last_minute_tour WHERE tour_id = ?");
            ps.setLong(1, tour.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return flag;
    }

    public Tour getTourById(long id) {
        Tour tour = new Tour();
        VoucherDAO voucherDAO = DAOFactory.getInstance().getVoucherDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM last_minute_tour WHERE tour_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            tour.setId(id);
            tour.setVoucher(voucherDAO.getVoucherById(rs.getLong("voucher_id")));
        } catch (SQLException ex){
            tour = new Tour();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return tour;
    }

    public List<Tour> getAllTours() {
        LinkedList<Tour> tours = new LinkedList<Tour>();
        Tour tour;
        VoucherDAO voucherDAO = DAOFactory.getInstance().getVoucherDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM last_minute_tour");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                tour = new Tour();
                tour.setId(rs.getLong("tour_id"));
                tour.setVoucher(voucherDAO.getVoucherById(rs.getLong("voucher_id")));

                tours.addLast(tour);
            }
        } catch (SQLException ex){
            tours = new LinkedList<Tour>();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return tours;
    }
}
