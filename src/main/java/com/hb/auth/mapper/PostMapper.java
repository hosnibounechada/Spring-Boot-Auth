package com.hb.auth.mapper;

import com.hb.auth.model.Post;
import com.hb.auth.payload.request.post.CreatePostRequest;
import com.hb.auth.payload.response.post.PostResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends GenericMapper<Post, CreatePostRequest, PostResponse>{

}
