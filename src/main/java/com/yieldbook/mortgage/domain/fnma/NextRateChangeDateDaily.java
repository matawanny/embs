package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;
import java.util.Date;

public class NextRateChangeDateDaily {
    public String poolNumber;
    public String recordType;
    public Date date;
    public BigDecimal percentOfBalance;
    public BigDecimal mbsMarginHigh;
    public BigDecimal mbsMarginLow;
    public BigDecimal mbsMargin;
    public BigDecimal netCouponHigh;
    public BigDecimal netCouponLow;
    public BigDecimal waNetCoupon;
    public BigDecimal netLifeCapsHigh;
    public BigDecimal netLifeCapsLow;  
    public BigDecimal netLifeFloorHigh;
    public BigDecimal netLifeFloorLow;    
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NextRateChangeDate [date=" + date + ", percentOfBalance="
				+ percentOfBalance + ", mbsMarginHigh=" + mbsMarginHigh
				+ ", mbsMarginLow=" + mbsMarginLow + ", mbsMargin=" + mbsMargin
				+ ", netCouponHigh=" + netCouponHigh + ", netCouponLow="
				+ netCouponLow + ", waNetCoupon=" + waNetCoupon
				+ ", netLifeCapsHigh=" + netLifeCapsHigh + ", netLifeCapsLow="
				+ netLifeCapsLow + ", netLifeFloorHigh=" + netLifeFloorHigh
				+ ", netLifeFloorLow=" + netLifeFloorLow + "]";
	}

}
