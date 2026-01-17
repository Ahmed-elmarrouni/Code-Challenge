package eu.powerdot.search.mapper;

import eu.powerdot.search.data.entity.UserDbo;
import eu.powerdot.search.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto map(UserDbo user);

    List<UserDto> map(List<UserDbo> user);

}
