package dao.daoImpl;

import dao.AirlaneCompanyDAO;
import models.AirlaneCompany;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlaneCompanyDAOImpl implements AirlaneCompanyDAO {
    public boolean addAirlaneCompany(AirlaneCompany airlaneCompany) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Airlane_company" +
                                                                    "(company_id, name, cost_coeff, rating) " +
                                                                    "VALUES (airlane_company_seq.NEXTVAL, ?, ?, ?)");
            ps.setString(1, airlaneCompany.getName());
            ps.setDouble(2, airlaneCompany.getCostCoeff());
            ps.setInt(3, airlaneCompany.getRating());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                    flag = false;
                }
            } else flag = false;
        }
        return flag;
    }

    public boolean updateAirlaneCompany(AirlaneCompany airlaneCompany) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Airlane_company SET " +
                                                                        "name = ?," +
                                                                        "cost_coeff = ?," +
                                                                        "rating = ? " +
                                                                    "WHERE company_id = ?");
            ps.setString(1, airlaneCompany.getName());
            ps.setDouble(2, airlaneCompany.getCostCoeff());
            ps.setInt(3, airlaneCompany.getRating());
            ps.setLong(4,airlaneCompany.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                    flag = false;
                }
            } else flag = false;
        }
        return flag;
    }

    public boolean deleteAirlaneCompany(AirlaneCompany airlaneCompany) {
        boolean flag = true;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Airlane_company " +
                                                                    "WHERE company_id = ?");
            ps.setLong(1,airlaneCompany.getId());
            ps.executeUpdate();
        } catch (SQLException ex){
            flag = false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                    flag = false;
                }
            } else flag = false;
        }
        return flag;
    }

    public AirlaneCompany getAirlaneCompanyById(long airlaneCompanyId) {
        AirlaneCompany airlaneCompany = new AirlaneCompany();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM Airlane_company " +
                                                                    "WHERE company_id = ?");
            ps.setLong(1, airlaneCompanyId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            airlaneCompany.setId(rs.getLong("company_id"));
            airlaneCompany.setName(rs.getString("name"));
            airlaneCompany.setCostCoeff(rs.getInt("cost_coeff"));
            airlaneCompany.setRating(rs.getInt("rating"));
        } catch (SQLException ex){
            airlaneCompany = new AirlaneCompany();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return airlaneCompany;
    }

    public AirlaneCompany getAirlaneCompanyByName(String name) {
        AirlaneCompany airlaneCompany = new AirlaneCompany();
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM Airlane_company " +
                                                                    "WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            airlaneCompany.setId(rs.getLong("company_id"));
            airlaneCompany.setName(rs.getString("name"));
            airlaneCompany.setCostCoeff(rs.getInt("cost_coeff"));
            airlaneCompany.setRating(rs.getInt("rating"));
        } catch (SQLException ex){
            airlaneCompany = new AirlaneCompany();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return airlaneCompany;
    }

    public List<AirlaneCompany> getAirlaneCompanyByCost(double minCost, double maxCost) {
        List<AirlaneCompany> companies;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM Airlane_company " +
                                                                    "WHERE cost_coeff BETWEEN ? AND ?");
            ps.setDouble(1, minCost);
            ps.setDouble(2, maxCost);
            ResultSet rs = ps.executeQuery();
            companies = getAirlaneCompanies(rs);
        } catch (SQLException ex){
            companies = new ArrayList<AirlaneCompany>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return companies;
    }

    public List<AirlaneCompany> getAirlaneCompaniesByRating(int minRating, int maxRating) {
        List<AirlaneCompany> companies;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM Airlane_company " +
                                                                    "WHERE rating BETWEEN ? AND ?");
            ps.setInt(1, minRating);
            ps.setInt(2,maxRating);
            ResultSet rs = ps.executeQuery();
            companies = getAirlaneCompanies(rs);
        } catch (SQLException ex){
            companies = new ArrayList<AirlaneCompany>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return companies;
    }

    public List<AirlaneCompany> getAllAirlaneCompanies() {
        List<AirlaneCompany> companies;
        Connection connection = JDBCUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * " +
                                                                    "FROM Airlane_company");
            ResultSet rs = ps.executeQuery();
            companies = getAirlaneCompanies(rs);
        } catch (SQLException ex){
            companies = new ArrayList<AirlaneCompany>();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex){
                }
            }
        }
        return companies;
    }

    private List<AirlaneCompany> getAirlaneCompanies(ResultSet rs) throws SQLException {
        List<AirlaneCompany> companies = new ArrayList<AirlaneCompany>();
        AirlaneCompany company = new AirlaneCompany();
        while (rs.next()){
            company.setId(rs.getLong("company_id"));
            company.setName(rs.getString("name"));
            company.setCostCoeff(rs.getInt("cost_coeff"));
            company.setRating(rs.getInt("rating"));

            companies.add(company);
        }
        return companies;
    }
}
