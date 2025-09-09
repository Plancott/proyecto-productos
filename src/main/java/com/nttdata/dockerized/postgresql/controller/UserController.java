package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(INSTANCE.map(userService.listAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(INSTANCE.map(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        User saveUser = userService.save(INSTANCE.toEntity(userSaveRequestDto));
        return new ResponseEntity<>(INSTANCE.toUserSaveResponseDto(saveUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id,
                                          @RequestBody UserSaveRequestDto userSaveRequestDto) {
        User user = userService.findById(id);
        user.setName(userSaveRequestDto.getName());
        user.setEmail(userSaveRequestDto.getEmail());

        User updated = userService.update(user);
        return ResponseEntity.ok(INSTANCE.map(userService.update(user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserSaveResponseDto> patch(@PathVariable Long id,
                                                     @RequestBody UserSaveRequestDto userSaveRequestDto) {
        User userAux = INSTANCE.toEntity(userSaveRequestDto);
        User patched = userService.patch(id, userAux);
        return ResponseEntity.ok(INSTANCE.toUserSaveResponseDto(patched));
    }
}
