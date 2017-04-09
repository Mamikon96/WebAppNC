package dao;

import models.AirlaneCompany;

import java.util.List;

public interface AirlaneCompanyDAO {
    boolean addAirlaneCompany(AirlaneCompany airlaneCompany);
    boolean updateAirlaneCompany(AirlaneCompany airlaneCompany);
    boolean deleteAirlaneCompany(AirlaneCompany airlaneCompany);
    AirlaneCompany getAirlaneCompanyById(long id);
    AirlaneCompany getAirlaneCompanyByName(String name);
    List<AirlaneCompany> getAirlaneCompanyByCost(double minCost, double maxCost);
    List<AirlaneCompany> getAirlaneCompaniesByRating(int minRating, int maxRating);
    List<AirlaneCompany> getAllAirlaneCompanies();
}
