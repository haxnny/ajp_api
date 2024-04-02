package com.aljjabaegi.api.domain.member;

import com.aljjabaegi.api.common.jpa.mapstruct.Converter;
import com.aljjabaegi.api.domain.member.record.*;
import com.aljjabaegi.api.entity.Member;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * User mapper
 *
 * @author GEONLEE
 * @since 2024-04-01
 */
@Mapper(componentModel = "spring", imports = Converter.class)
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    /**
     * entity to search response
     *
     * @param entity user entity
     * @return UserSearchResponse
     * @author GEONLEE
     * @since 2024-04-01<br />
     */
    @Mappings({
            @Mapping(target = "createDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "modifyDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    UserSearchResponse toSearchResponse(Member entity);

    /**
     * entity list to search response list
     *
     * @param list user entity list
     * @return UserSearchResponse list
     * @author GEONLEE
     * @since 2024-04-01<br />
     */
    List<UserSearchResponse> toSearchResponseList(List<Member> list);

    /**
     * entity to create response
     *
     * @param entity user entity
     * @return UserCreateResponse
     * @author GEONLEE
     * @since 2024-04-01<br />
     */
    @Mappings({
            @Mapping(target = "isUse", expression = "java(Converter.stringToBoolean(entity.getUseYn()))"),
            @Mapping(target = "createDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "modifyDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    UserCreateResponse toCreateResponse(Member entity);

    /**
     * entity to modify response
     *
     * @param entity user entity
     * @return UserModifyResponse
     * @author GEONLEE
     * @since 2024-04-01<br />
     */
    @Mappings({
            @Mapping(target = "isUse", expression = "java(Converter.stringToBoolean(entity.getUseYn()))"),
            @Mapping(target = "createDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "modifyDate", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    UserModifyResponse toModifyResponse(Member entity);

    /**
     * createRequest to entity
     *
     * @param userCreateRequest user create request
     * @return User
     * @author GEONLEE
     * @since 2024-04-01<br />
     */
    @Mappings({
            @Mapping(target = "useYn", expression = "java(Converter.booleanToString(userCreateRequest.isUse()))"),
    })
    Member toEntity(UserCreateRequest userCreateRequest);

    /**
     * update from record
     *
     * @param userModifyRequest update request record
     * @param entity            update request entity
     * @return User
     * @author GEONLEE
     * @since 2024-04-01<br />
     */
    @Mapping(target = "useYn", expression = "java(Converter.booleanToString(userModifyRequest.isUse()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    Member updateFromRequest(UserModifyRequest userModifyRequest, @MappingTarget Member entity);

}