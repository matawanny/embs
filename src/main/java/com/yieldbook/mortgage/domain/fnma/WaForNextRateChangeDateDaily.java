package com.yieldbook.mortgage.domain.fnma;

import java.math.BigDecimal;

public class WaForNextRateChangeDateDaily {
    public String poolNumber;
    public String recordType;
    public BigDecimal waMBSMargin;
    public BigDecimal waNetCoupon;
    public BigDecimal waNetLifeCaps;
    public BigDecimal waNetLifeFloor;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WaForNextRateChangeDate [waMBSMargin=" + waMBSMargin
				+ ", waNetCoupon=" + waNetCoupon + ", waNetLifeCaps="
				+ waNetLifeCaps + ", waNetLifeFloor=" + waNetLifeFloor + "]";
	}   


}
