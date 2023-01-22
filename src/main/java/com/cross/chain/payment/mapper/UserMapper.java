package com.cross.chain.payment.mapper;

import com.cross.chain.payment.model.UserDTO;
import com.cross.chain.payment.model.UserRequest;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequest map(UserDTO userEntity);

    UserDTO map(UserRequest user);

    default byte[] map(Binary image) {
        if(image == null){
            return null;
        }
        return image.getData();
    }

    default Binary map(byte[] value){
        if(value == null){
            return null;
        }
        return new Binary(BsonBinarySubType.BINARY, value);
    }

}
