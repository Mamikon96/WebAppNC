package dao.daoImpl;

import dao.*;
import models.AirlaneCompany;
import models.City;
import models.Voucher;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VoucherDAOImpl implements VoucherDAO {
    public boolean addVoucher(Voucher voucher) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO vouchers" +
                                                    "(voucher_id, route_id, company_id, hotel_id, city_id, cost) " +
                                                    "VALUES (vouchers.NEXTVAL, ?, ?, ?, ?, ?)");
            ps.setLong(1, voucher.getRoute().getId());
            ps.setLong(2, voucher.getAirlaneCompany().getId());
            ps.setLong(3, voucher.getHotel().getId());
            ps.setLong(4, voucher.getCity().getId());
            ps.setDouble(5, voucher.getCost());
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

    public boolean updateVoucher(Voucher voucher) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE vouchers SET " +
                    "route_id = ?," +
                    "company_id = ?," +
                    "hotel_id = ?," +
                    "city_id = ?," +
                    "cost = ?" +
                    "WHERE voucher_id = ?");
            ps.setLong(1, voucher.getRoute().getId());
            ps.setLong(2, voucher.getAirlaneCompany().getId());
            ps.setLong(3, voucher.getHotel().getId());
            ps.setLong(4, voucher.getCity().getId());
            ps.setDouble(5, voucher.getCost());
            ps.setLong(6, voucher.getId());
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

    public boolean deleteVoucher(Voucher voucher) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM vouchers WHERE voucher_id = ?");
            ps.setLong(1, voucher.getId());
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

    public Voucher getVoucherById(long id) {
        Voucher voucher = new Voucher();
        RouteDAO routeDAO = DAOFactory.getInstance().getRouteDAO();
        AirlaneCompanyDAO companyDAO = DAOFactory.getInstance().getAirlaneCompanyDAO();
        HotelDAO hotelDAO = DAOFactory.getInstance().getHotelDAO();
        CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vouchers WHERE voucher_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            voucher.setId(id);
            voucher.setRoute(routeDAO.getRouteById(rs.getLong("route_id")));
            voucher.setAirlaneCompany(companyDAO.getAirlaneCompanyById(rs.getLong("company_id")));
            voucher.setHotel(hotelDAO.getHotelById(rs.getLong("hotel_id")));
            voucher.setCity(cityDAO.getCityById(rs.getLong("city_id")));
            voucher.setCost(rs.getDouble("cost"));
        } catch (SQLException ex){
            voucher = new Voucher();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return voucher;
    }

    public List<Voucher> getVouchersByAirlaneCompany(AirlaneCompany airlaneCompany) {
        List<Voucher> vouchers;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vouchers " +
                                                                    "WHERE company_id = ?");
            ps.setLong(1, airlaneCompany.getId());
            ResultSet rs = ps.executeQuery();
            vouchers = getVouchers(rs);
        } catch (SQLException ex){
            vouchers = new ArrayList<Voucher>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return vouchers;
    }

    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM vouchers ");
            ResultSet rs = ps.executeQuery();
            vouchers = getVouchers(rs);
        } catch (SQLException ex){
            vouchers = new ArrayList<Voucher>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return vouchers;
    }

    private List<Voucher> getVouchers(ResultSet rs) throws SQLException {
        LinkedList<Voucher> vouchers = new LinkedList<Voucher>();
        Voucher voucher;
        RouteDAO routeDAO = DAOFactory.getInstance().getRouteDAO();
        AirlaneCompanyDAO companyDAO = DAOFactory.getInstance().getAirlaneCompanyDAO();
        HotelDAO hotelDAO = DAOFactory.getInstance().getHotelDAO();
        CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();
        while (rs.next()){
            voucher = new Voucher();
            voucher.setId(rs.getLong("plane_id"));
            voucher.setRoute(routeDAO.getRouteById(rs.getLong("route_id")));
            voucher.setAirlaneCompany(companyDAO.getAirlaneCompanyById(rs.getLong("company_id")));
            voucher.setHotel(hotelDAO.getHotelById(rs.getLong("hotel_id")));
            voucher.setCity(cityDAO.getCityById(rs.getLong("city_id")));
            voucher.setCost(rs.getDouble("cost"));

            vouchers.addLast(voucher);
        }
        return vouchers;
    }
}
