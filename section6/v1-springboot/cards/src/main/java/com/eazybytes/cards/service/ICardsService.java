package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param mobileNumber - Mobile number of the customer
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return card details based on a given mobile number
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDto -CardsDto object
     * @return boolean indicating if the update of the card details is successful or not
     */

    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber -input mobile number
     * @return boolean indicating if the delete of card details is successful or not
     */

    boolean deleteCard(String mobileNumber);
}
