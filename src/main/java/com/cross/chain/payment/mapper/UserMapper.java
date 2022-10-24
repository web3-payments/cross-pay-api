package com.cross.chain.payment.mapper;

import com.cross.chain.payment.domain.User;
import com.cross.chain.payment.dto.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequest map(User user);

    User map(UserRequest user);

}
