package com.foodcourt.squaremallmanagment.domain.spi;

import com.foodcourt.squaremallmanagment.domain.model.EmployeeModel;

public interface IEmployeeRestPort {

    EmployeeModel getEmployeeByDni(String dni);
}
