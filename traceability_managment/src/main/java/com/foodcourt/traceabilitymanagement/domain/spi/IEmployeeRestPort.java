package com.foodcourt.traceabilitymanagement.domain.spi;

import com.foodcourt.traceabilitymanagement.domain.model.user.EmployeeModel;

import java.util.List;

public interface IEmployeeRestPort {

    List<EmployeeModel> getEmployeesByRestaurantId(Long restaurantId);
}
