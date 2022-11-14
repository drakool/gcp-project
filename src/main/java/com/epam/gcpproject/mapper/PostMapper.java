package com.epam.gcpproject.mapper;

import com.epam.gcpproject.model.domain.Post;
import com.epam.gcpproject.model.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);


    Post toEntity(PostDto dto);

    PostDto toDto(Post post);
}
