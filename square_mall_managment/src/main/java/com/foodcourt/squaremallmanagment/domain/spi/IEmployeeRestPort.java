package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.EmployeeModel;

import java.util.List;

public interface IEmployeeRestPort {

    EmployeeModel getEmployeeByDni(String dni);
    List<EmployeeModel> getEmployeesByRestaurantId(Long restaurantId);
}
