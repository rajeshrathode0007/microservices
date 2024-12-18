package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoansDto;

public interface ILoansService {

    /**
     *
     * @param mobileNumber -Input Mobile Number
     */

    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber -Input Mobile Number
     * @return Loans details based on a given mobile number
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto -LoansDto Object
     * @return boolean indicating if update of loans details is successful or not
     */

    boolean updateLoan(LoansDto loansDto);

    /**
     *
     * @param mobileNumber  -Input Mobile Number
     * @return boolean indicating if delete of loans details is successful or not
     */

    boolean deleteLoan(String mobileNumber);

}
