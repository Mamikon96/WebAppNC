package dao.daoImpl;

import dao.DAOFactory;
import dao.TicketDAO;
import dao.VoucherDAO;
import models.Ticket;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    public boolean addTicket(Ticket ticket) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tickets" +
                    "(ticket_id, buyer, data, voucher_id) " +
                    "VALUES (tickets_seq.NEXTVAL, ?, ?, ?)");
            ps.setString(1, ticket.getBuyer());
            ps.setString(2, ticket.getData());
            ps.setLong(3, ticket.getVoucher().getId());
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

    public boolean updateTicket(Ticket ticket) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE tickets SET " +
                    "buyer = ?," +
                    "data = ?," +
                    "voucher_id = ?" +
                    "WHERE tickrt_id = ?");
            ps.setString(1, ticket.getBuyer());
            ps.setString(2, ticket.getData());
            ps.setLong(3, ticket.getVoucher().getId());
            ps.setLong(4, ticket.getId());
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

    public boolean deleteTicket(Ticket ticket) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tickets WHERE ticket_id = ?");
            ps.setLong(1, ticket.getId());
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

    public Ticket getTicketById(long id) {
        Ticket ticket = new Ticket();
        VoucherDAO voucherDAO = DAOFactory.getInstance().getVoucherDAO();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tickets WHERE ticket_id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            ticket.setId(id);
            ticket.setBuyer(rs.getString("buyer"));
            ticket.setData(rs.getString("data"));
            ticket.setVoucher(voucherDAO.getVoucherById(rs.getLong("voucher_id")));
        } catch (SQLException ex){
            ticket = new Ticket();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return ticket;
    }

    public List<Ticket> getTicketsByBuyer(String buyer) {
        List<Ticket> tickets;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tickets WHERE buyer = ?");
            ps.setString(1, buyer);
            ResultSet rs = ps.executeQuery();
            tickets = getTickets(rs);
        } catch (SQLException ex){
            tickets = new ArrayList<Ticket>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return tickets;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tickets ");
            ResultSet rs = ps.executeQuery();
            tickets = getTickets(rs);
        } catch (SQLException ex){
            tickets = new ArrayList<Ticket>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return tickets;
    }

    private List<Ticket> getTickets(ResultSet rs) throws SQLException {
        LinkedList<Ticket> tickets = new LinkedList<Ticket>();
        Ticket ticket;
        VoucherDAO voucherDAO = DAOFactory.getInstance().getVoucherDAO();
        while (rs.next()){
            ticket = new Ticket();
            ticket.setId(rs.getLong("ticket_id"));
            ticket.setBuyer(rs.getString("buyer"));
            ticket.setData(rs.getString("data"));
            ticket.setVoucher(voucherDAO.getVoucherById(rs.getLong("voucher_id")));

            tickets.addLast(ticket);
        }
        return tickets;
    }
}
