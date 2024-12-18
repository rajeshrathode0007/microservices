package com.eazybytes.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
@Schema(name="cards",
description = "Schema to hold card information")
@Data
public class CardsDto {
    @NotEmpty(message="Mobile number cannot be a null or empty")
    @Pattern(regexp="(^$|[0-9,]{10})",message="Mobile number must be 10 digits")
    @Schema(
            description = "Mobile number of customer" ,example="4354437687"
    )
    private String mobileNumber;

    @NotEmpty(message="Card number cannot be a null or empty")
    @Pattern(regexp="(^$|[0-9,]{12})", message="Card number must be 12 digits")
    @Schema(
            description = "Card number of the customer" ,example ="100646930341"
    )
    private String cardNumber;

    @NotEmpty(message="CardType cannot be a null or empty")
    @Schema(
            description = "Type of the card" ,example = "Credit Card"
    )
    private String cardType;

    @Positive(message ="Total card limit should be greater than zero")
    @Schema(
            description = "Total amount limit available against the card" ,example="1000000"
    )

    private int totalLimit;

    @PositiveOrZero(message="Total amount should be equal or greater than zero")
    @Schema(
            description = "Total amount used by a customer" , example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message="Total amount should be equal or greater than zero")
    @Schema(
            description = "Total available amount against a card" ,example="90000"
    )

    private int availableAmount;
}
