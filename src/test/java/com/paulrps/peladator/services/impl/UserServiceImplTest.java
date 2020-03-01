package com.paulrps.peladator.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.paulrps.peladator.domain.dto.UserFormDto;
import com.paulrps.peladator.domain.entities.User;
import com.paulrps.peladator.domain.enums.RoleEnum;
import com.paulrps.peladator.repositories.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class UserServiceImplTest implements ServiceTest {

  @Mock UserRepository repository;
  @InjectMocks UserServiceImpl service;

  User user;

  @BeforeEach
  void setUp() {
    user = new User();
  }

  @Test
  void getOneByName() {
    // given
    String name = "messi";
    user.setName(name);
    given(repository.findByName(name)).willReturn(Optional.of(user));

    //    when
    Optional<User> found = service.getOne(name);

    // then
    assertThat(found).isNotNull();
    assertThat(found.get().getName()).isEqualTo(name);
  }

  @Test
  void getOneById() {
    // given
    Long id = 1l;
    user.setId(id);
    given(repository.findById(id)).willReturn(Optional.of(user));

    //    when
    Optional<User> found = service.getOne(id);

    // then
    assertThat(found).isNotNull();
    assertThat(found.get().getId()).isEqualTo(id);
  }

  @Test
  void getFormData() {

    //    when
    UserFormDto formData = service.getFormData();

    //    then
    assertThat(formData).isNotNull();
    assertThat(formData.getRoles()).isNotEmpty().hasSize(RoleEnum.values().length);
  }

  @Test
  void getAll() {
    //    given
    List<User> users = Arrays.asList(user, new User(), new User());
    given(repository.findAll()).willReturn(users);

    //    when
    List<User> all = service.getAll();

    //    then
    assertThat(all).isNotNull().isNotEmpty();
    assertThat(all.size()).isEqualTo(users.size());
  }

  @Test
  void save() {
    //    given
    Long id = 1l;
    user.setId(id);
    user.setPassword("123456");
    given(repository.save(user)).willReturn(user);

    //    when
    User save = service.save(user);
    User nul = service.save(null);

    //    then
    assertThat(save).isNotNull();
    assertThat(save.getId()).isNotNull();
    assertThat(save.getPassword()).isNotNull();
    assertThat(nul).isNull();
  }

  @Test
  void delete() {
    //    given
    Long id = 1l;
    user.setId(id);
    given(repository.findById(id)).willReturn(Optional.of(user));
    //    given(repository.findById(null)).willThrow(RuntimeException.class);

    //    when
    boolean delete = service.delete(id);

    //    then
    assertThat(delete).isTrue();
    assertThat(service.delete(-1l)).isFalse();
    assertThrows(RuntimeException.class, () -> service.delete(null));
  }
}
