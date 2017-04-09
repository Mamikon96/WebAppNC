package dao;

import dao.daoImpl.*;
import models.Hotel;

public class DAOFactory {
    private static DAOFactory instance;

    private AirlaneCompanyDAO airlaneCompanyDAO;
    private PilotDAO pilotDAO;
    private PlaneDAO planeDAO;
    private RouteDAO routeDAO;
    private CountryDAO countryDAO;
    private CityDAO cityDAO;
    private RationDAO rationDAO;
    private HotelTypeDAO hotelTypeDAO;
    private HotelDAO hotelDAO;
    private VoucherDAO voucherDAO;

    public static DAOFactory getInstance() {
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public AirlaneCompanyDAO getAirlaneCompanyDAO() {
        if (airlaneCompanyDAO == null){
            airlaneCompanyDAO = new AirlaneCompanyDAOImpl();
        }
        return airlaneCompanyDAO;
    }

    public PilotDAO getPilotDAO() {
        if (pilotDAO == null){
            pilotDAO = new PilotDAOImpl();
        }
        return pilotDAO;
    }

    public PlaneDAO getPlaneDAO() {
        if (planeDAO == null){
            planeDAO = new PlaneDAOImpl();
        }
        return planeDAO;
    }

    public RouteDAO getRouteDAO() {
        if (routeDAO == null){
            routeDAO = new RouteDAOImpl();
        }
        return routeDAO;
    }

    public CountryDAO getCountryDAO() {
        if (countryDAO == null){
            countryDAO = new CountryDAOImpl();
        }
        return countryDAO;
    }

    public CityDAO getCityDAO() {
        if (cityDAO == null){
            cityDAO = new CityDAOImpl();
        }
        return cityDAO;
    }

    public RationDAO getRationDAO() {
        if (rationDAO == null){
            rationDAO = new RationDAOImpl();
        }
        return rationDAO;
    }

    public HotelTypeDAO getHotelTypeDAO() {
        if (hotelTypeDAO == null){
            hotelTypeDAO = new HotelTypeDAOImpl();
        }
        return hotelTypeDAO;
    }

    public HotelDAO getHotelDAO() {
        if (hotelDAO == null){
            hotelDAO = new HotelDAOImpl();
        }
        return hotelDAO;
    }

    public VoucherDAO getVoucherDAO() {
        if (voucherDAO == null){
            voucherDAO = new VoucherDAOImpl();
        }
        return voucherDAO;
    }
}
