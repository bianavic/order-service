package com.udemy.orderservice.utils;

import com.udemy.orderservice.dto.UserDTO;
import com.udemy.orderservice.entity.User;
import org.springframework.beans.BeanUtils;

public class EntityToDTOUtil {

  private static UserDTO toDto(User user) {
    UserDTO dto = new UserDTO();
    BeanUtils.copyProperties(user, dto);
    return dto;
  }

  private static User toEntity(UserDTO dto) {
    User user = new User();
    BeanUtils.copyProperties(dto, user);
    return user;
  }

}
