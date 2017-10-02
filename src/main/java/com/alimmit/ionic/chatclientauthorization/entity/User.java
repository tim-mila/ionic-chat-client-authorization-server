package com.alimmit.ionic.chatclientauthorization.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;

@Entity
@Table(name = "users")
public class User implements Serializable, Principal {

    public static User create(final String username, final String password) {
        final User user = new User();
        user.username = username;
        user.password = password;
        user.enabled = true;
        return user;
    }

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean enabled;

    @Transient
    private String remoteAddress;

    @Transient
    private String sessionId;

    public User populateDetails(WebAuthenticationDetails authenticationDetails) {
        this.remoteAddress = authenticationDetails.getRemoteAddress();
        this.sessionId = authenticationDetails.getSessionId();
        return this;
    }

    @Override
    public String getName() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(final String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;

        if (enabled != user.enabled) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (remoteAddress != null ? !remoteAddress.equals(user.remoteAddress) : user.remoteAddress != null)
            return false;
        return sessionId != null ? sessionId.equals(user.sessionId) : user.sessionId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (remoteAddress != null ? remoteAddress.hashCode() : 0);
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        return result;
    }
}
