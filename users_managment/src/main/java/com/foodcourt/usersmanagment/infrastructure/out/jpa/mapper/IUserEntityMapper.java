package com.foodcourt.usersmanagment.infrastructure.out.jpa.mapper;

import com.foodcourt.usersmanagment.domain.model.CreateUserModel;
import com.foodcourt.usersmanagment.domain.model.UserModel;
import com.foodcourt.usersmanagment.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

    UserEntity toUserEntity(CreateUserModel createUserModel);
    CreateUserModel toOwnerModel(UserEntity userEntity);
    UserModel toUserModel(UserEntity userEntity);
}
