package com.hb.auth.service;

import com.hb.auth.annotation.CustomAnnotation;
import com.hb.auth.exception.ConflictException;
import com.hb.auth.exception.GoneException;
import com.hb.auth.exception.NotFoundException;
import com.hb.auth.mapper.UserMapper;
import com.hb.auth.model.User;
import com.hb.auth.payload.request.user.CreateUserRequest;
import com.hb.auth.payload.request.user.UpdateUserRequest;
import com.hb.auth.payload.response.PageResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.repository.PostRepository;
import com.hb.auth.repository.UserRepository;
import com.hb.auth.util.UpdateObject;
import com.hb.auth.view.PostView;
import com.hb.auth.view.UserViewImp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hb.auth.util.PaginationUtils.generatePageableResponse;


@Service
@RequiredArgsConstructor
public class UserService implements IService<Long, CreateUserRequest, UpdateUserRequest, UserResponse> {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserMapper userMapper;

    @Override
    public PageResponse<UserResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<UserResponse> usersList = users.stream().map(userMapper::entityToResponse).collect(Collectors.toList());

        int count = usersList.size();

        return new PageResponse<>(0, count, count > 0 ? 1 : 0, count, usersList);
    }

    @Override
    public PageResponse<UserResponse> getByPages(int pageNumber, int pageSize, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));

        Page<User> pageUsers = userRepository.findAll(pageable);

        return generatePageableResponse(pageUsers, userMapper);
    }

    @Override
    @CustomAnnotation
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));

        return userMapper.entityToResponse(user);
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        /*validate(request);*/

        User user = userMapper.requestToEntity(request);

        return userMapper.entityToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest request) {
        Optional<User> existedUser = userRepository.findById(id);

        if (existedUser.isEmpty()) throw new NotFoundException("User not found!");

        User updatedUser = existedUser.get();

        UpdateObject.updateUserFields(request, updatedUser);

        return userMapper.entityToResponse(userRepository.save(updatedUser));
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) throw new NotFoundException("User already deleted!");

        userRepository.deleteById(id);

        if (userRepository.existsById(id)) throw new ConflictException("Couldn't delete user!");
    }
    /**
     * @deprecated (when, issue is not able to delete on cascade mode...)
     */
    @Deprecated
    @Override
    public void delete(Long id) {
        int count = userRepository.deleteUserById(id);

        if (count != 1) throw new GoneException("User already deleted!");
    }

    public PageResponse<UserViewImp> search(
            String firstName,
            String lastName,
            int pageNumber,
            int pageSize,
            String[] sortBy,
            String direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));


//        return userRepository.getUserByFirstNameOrLastNameOrderByFirstNameAscLastNameAsc(firstName, lastName);

        Page<UserViewImp> page = userRepository.getUserByFirstNameOrLastName(firstName, lastName, pageable);

        return generatePageableResponse(page);
    }

    public PageResponse<PostView> getUserPosts(Long id,
                                               int pageNumber,
                                               int pageSize,
                                               String[] sortBy,
                                               String direction) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));
        Page<PostView> page = postRepository.findByUserId(id, pageable);
        return generatePageableResponse(page);
    }
}