package com.visualpathit.account.integration;

import com.visualpathit.account.model.User;
import com.visualpathit.account.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserControllerIntegrationTest {

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserRegistration() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setPasswordConfirm("testPassword");
        user.setUserEmail("testuser@example.com");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userRepository.save(user);

        assertThat(savedUser, is(notNullValue()));
        assertThat(savedUser.getUsername(), is("testUser"));
        assertThat(savedUser.getUserEmail(), is("testuser@example.com"));
    }

    @Test
    public void testGetAllUsers() {
        User user = new User();
        user.setUsername("testUser2");
        user.setPassword("password2");
        user.setUserEmail("testuser2@example.com");

        Mockito.when(userRepository.findAll()).thenReturn(java.util.Collections.singletonList(user));

        Iterable<User> users = userRepository.findAll();

        assertThat(users, is(notNullValue()));
        assertThat(users.iterator().next().getUsername(), is("testUser2"));
    }
}
