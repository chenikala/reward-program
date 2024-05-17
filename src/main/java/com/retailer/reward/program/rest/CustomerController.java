package com.retailer.reward.program.rest;

import com.retailer.reward.program.dto.CustomerDto;
import com.retailer.reward.program.service.CustomerService;
import com.retailer.reward.program.util.Api;
import com.retailer.reward.program.util.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Retailer reward program controller
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(Api.Rewards.BASE_URL)
public class CustomerController {

    private CustomerService customerService;

    @Operation(summary = "Get list of customer data as JSON",
            description = "Returns the list of customer data in JSON format",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @GetMapping(Api.Rewards.GET_ALL_CUSTOMERS)
    public GenericResponse<List<CustomerDto>> getAllCustomerData(){
        log.info("CustomerController::getAllCustomerData");
        List<CustomerDto> result = customerService.getAllCustomers();
        return GenericResponse.<List<CustomerDto>>builder()
                .success(true)
                .data(result)
                .build();
    }

    @Operation(summary = "Get list of reward data as JSON",
            description = "Returns the list of reward data in JSON format",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @GetMapping(Api.Rewards.GET_ALL_CUSTOMERS_PAGE)
    public GenericResponse<Page<CustomerDto>> getCustomersByPage(@PageableDefault(size = 5) Pageable pageable){
        log.info("CustomerController::getCustomersByPage");
        Page<CustomerDto> result = customerService.getAllCustomersAsPageBy(pageable);
        return GenericResponse.<Page<CustomerDto>>builder()
                .success(true)
                .data(result)
                .build();
    }

    @Operation(summary = "Get Customer data as JSON based on customer ID",
            description = "Returns Customer data as JSON based on customer ID",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @GetMapping(Api.Rewards.GET_CUSTOMER)
    public GenericResponse<CustomerDto> getCustomer(@RequestParam(name = "cid") String customerId){
        log.info("CustomerController::getCustomer");
        CustomerDto result = customerService.getCustomer(Long.getLong(customerId));
        return GenericResponse.<CustomerDto>builder()
                .success(true)
                .data(result)
                .build();
    }

    @Operation(summary = "Insert customer data",
            description = "Returns customer data as JSON",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @PostMapping(Api.Rewards.INSERT_CUSTOMER)
    public GenericResponse<CustomerDto> insertCustomer(@RequestBody CustomerDto CustomerDto){
        log.info("CustomerController::insertCustomer");
        CustomerDto result = customerService.insertCustomer(CustomerDto);
        return GenericResponse.<CustomerDto>builder()
                .success(true)
                .data(result)
                .build();
    }
    @Operation(summary = "Update customer data",
            description = "Update customer data",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @PutMapping(Api.Rewards.UPDATE_CUSTOMER)
    public GenericResponse<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto){
        log.info("CustomerController::updateCustomer");
        CustomerDto result = customerService.updateCustomer(customerDto);
        return GenericResponse.<CustomerDto>builder()
                .success(true)
                .data(result)
                .build();
    }
    @Operation(summary = "Delete customer data",
            description = "Delete customer data",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Data retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GenericResponse.class)))
            })
    @DeleteMapping(Api.Rewards.DELETE_CUSTOMER)
    public GenericResponse<String> deleteCustomer(@RequestBody CustomerDto customerDto){
        log.info("CustomerController::deleteCustomer");
        String result = customerService.deleteCustomer(customerDto.getCid());
        return GenericResponse.<String>builder()
                .success(true)
                .data(result)
                .build();
    }
}
