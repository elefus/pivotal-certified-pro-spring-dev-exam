package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.ResponseStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name="P_RESPONSE")
public class Response extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "REQUEST_ID", nullable = false)
    private Request request;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "RESPONSE_STATUS")
    private ResponseStatus responseStatus;

    @Size(max = 500)
    @NotEmpty
    private String details;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        Response response = (Response) other;
        if (user != null ? !user.getId().equals(response.user.getId()) : response.user != null) {
            return false;
        }
        if (request != null ? !request.getId().equals(response.request.getId()) : response.request != null) {
            return false;
        }
        return responseStatus == response.responseStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, request, responseStatus);
    }

    @Override
    public String toString() {
        return String.format("Request[id='%d', user='%s', request='%s', responseStatus='%s']",
                             id,
                             Optional.of(user).map(User::getId).map(Object::toString).orElse(""),
                             Optional.of(request).map(Request::getId).map(Object::toString).orElse(""),
                             responseStatus);
    }
}
