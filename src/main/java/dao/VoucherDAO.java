package dao;

import models.AirlaneCompany;
import models.Voucher;

import java.util.List;

public interface VoucherDAO {
    boolean addVoucher(Voucher voucher);
    boolean updateVoucher(Voucher voucher);
    boolean deleteVoucher(Voucher voucher);
    Voucher getVoucherById(long id);
    List<Voucher> getVouchersByAirlaneCompany(AirlaneCompany airlaneCompany);
    List<Voucher> getAllVouchers();
}
