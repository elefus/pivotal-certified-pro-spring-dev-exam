package com.ps.ents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ps.base.AbstractEntity;
import com.ps.base.RequestStatus;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name="P_REQUEST")
public class Request extends AbstractEntity {

    // TODO вынести в util
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    /**
     * This field is used for marking the beginning of the interval when a pet sitter is needed.
     */
    @JsonIgnore
    @Column(name = "START_AT", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startAt;

    /**
     * This field is used for marking the end of the interval when a pet sitter is needed.
     */
    @JsonIgnore
    @Column(name = "END_AT", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "REQUEST_STATUS")
    private RequestStatus requestStatus;

    @JsonIgnore
    @OneToMany
    private Set<Pet> pets = new HashSet<>();

    @Size(max = 500)
    @NotEmpty
    private String details;

    @JsonIgnore
    @OneToMany(mappedBy = "request", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Response> responses = new HashSet<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    protected void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    protected void setResponses(Set<Response> responses) {
        this.responses = responses;
    }

    public boolean addResponse(Response response) {
        response.setRequest(this);
        return responses.add(response);
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        Request that = (Request) other;
        if (user != null ? !user.getId().equals(that.user.getId()) : that.user != null) {
            return false;
        }
        return Objects.equals(startAt, that.startAt)
            && Objects.equals(endAt, that.endAt)
            && Objects.equals(requestStatus, that.requestStatus)
            && Objects.equals(pets, that.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, startAt, endAt, requestStatus, pets);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = DATE_FORMATTER.get();
        return String.format("Request[id='%d', user='%s', startAt='%s', requestStatus='%s', requestStatus='%s']",
                             id,
                             Optional.of(user).map(User::getId).map(Object::toString).orElse(""),
                             dateFormatter.format(startAt),
                             dateFormatter.format(endAt),
                             pets);
    }
}
