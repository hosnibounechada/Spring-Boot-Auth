package com.hb.auth.util;

import com.hb.auth.model.Post;
import com.hb.auth.model.User;
import com.hb.auth.payload.request.post.UpdatePostRequest;
import com.hb.auth.payload.request.user.UpdateUserRequest;

public class UpdateObject {
    /**
     * Update User fields by reference based on the client provided data
     * @param updateUserRequest Provided by the client
     * @param user Update the user fields from the updateUserRequest
     */
    public static void updateUserFields(UpdateUserRequest updateUserRequest, User user){
        /*if(updateUserRequest.firstName() != null) user.setFirstName(updateUserRequest.firstName());
        if(updateUserRequest.lastName() != null) user.setLastName(updateUserRequest.lastName());
        if(updateUserRequest.age() != null) user.setAge(updateUserRequest.age());*/
        if(updateUserRequest.getFirstName() != null) user.setFirstName(updateUserRequest.getFirstName());
        if(updateUserRequest.getLastName() != null) user.setLastName(updateUserRequest.getLastName());
        if(updateUserRequest.getAge() != null) user.setAge(updateUserRequest.getAge());
    }

    /**
     * Update Post fields by reference based on the client provided data
     * @param updatePostRequest Provided by the client
     * @param post Update the post fields from the updatePostRequest
     */
    public static void updatePostFields(UpdatePostRequest updatePostRequest, Post post){
        if(updatePostRequest.content() != null) post.setContent(updatePostRequest.content());
    }
}
