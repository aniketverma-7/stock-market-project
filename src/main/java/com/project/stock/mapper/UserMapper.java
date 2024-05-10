package com.project.stock.mapper;

import com.project.stock.dto.user.UserRequest;
import com.project.stock.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserRequest mapEntityToResponse(User user);

    User mapRequestToEntity(UserRequest userRequest);
}
