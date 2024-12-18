package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ErrorResponseDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author derangula.rajesh
 */
@Tag(
        name="CRUD REST API's for cards in EazyBank",
        description = "CRUD REST APIs in eazy bank to create, fetch, update and delete card details"
)
@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {
        ICardsService iCardsService;
        @Operation(
                summary ="create Card REST API",
                description = "REST API to create new Card inside Eazy Bank"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = CardsConstants.STATUS_200,
                        description = "HTTP Status Created"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        }
        )
        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber){
            iCardsService.createCard(mobileNumber);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(CardsConstants.STATUS_201,CardsConstants.STATUS_201));
        }
        @Operation(
                summary = "Fetch card details REST API",
                description = "REST API to fetch card details based on mobile number"
        )
        @ApiResponses(
                {
                        @ApiResponse(
                                responseCode = CardsConstants.STATUS_200,
                                description = "HTTP status OK"
                        ),
                        @ApiResponse(
                                responseCode = "500",
                                description = "Internal server error",
                                content =@Content(
                                        schema = @Schema(implementation = ErrorResponseDto.class)
                                )
                        )
                }
        )
        @GetMapping("/fetch")
        public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                            String mobileNumber){
            CardsDto cardsDto =iCardsService.fetchCard(mobileNumber);
            return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
        }
        @Operation(
                summary = "Update Card details REST API",
                description = "REST API to udpate the card details based on a mobile number"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        })
        @PutMapping("/update")
        public ResponseEntity<ResponseDto> updateCardsDetails(@Valid @RequestBody CardsDto cardsDto){
            boolean isUpdated =iCardsService.updateCard(cardsDto);
            if(isUpdated){
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
            }else{
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.STATUS_417));
            }
        }
        @Operation(
                summary = "Delete Card details REST API",
                description = "REST API to delete card details based on a mobile number"
        )
        @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
                @ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                @ApiResponse(
                     responseCode = "500",
                     description = "Internal Server error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        })
        @DeleteMapping("/delete")
        public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                             @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                             String mobileNumber){
                boolean isDeleted = iCardsService.deleteCard(mobileNumber);
                if (isDeleted){
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
                }else{
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
                }
        }
}
