package mz.gov.bau.libraryApi.user.domain.mapper;

import mz.gov.bau.libraryApi.user.domain.command.UserCommand;
import mz.gov.bau.libraryApi.user.domain.model.User;
import mz.gov.bau.libraryApi.user.presentation.UserJson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract UserJson toJson(User user);
    public abstract User toModel(UserCommand userCommand);
    public abstract void toModel(UserCommand userCommand, @MappingTarget User user);
    public List<UserJson> toJson(List<User> users) {
        return users.stream().map(this::toJson).collect(Collectors.toList());
    }
}
