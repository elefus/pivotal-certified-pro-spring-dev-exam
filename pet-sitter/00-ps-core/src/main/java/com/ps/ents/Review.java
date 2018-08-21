package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.ReviewGrade;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name="P_REVIEW")
public class Review extends AbstractEntity {

    @ManyToOne
    private Request request;

    @ManyToOne
    private Response response;

    @NotEmpty
    @Enumerated(EnumType.ORDINAL)
    private ReviewGrade grade;

    @Size(max = 500)
    @NotEmpty
    private String details;

    public ReviewGrade getGrade() {
        return grade;
    }

    public void setGrade(ReviewGrade grade) {
        this.grade = grade;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        Review review = (Review) other;
        if (request != null ? !request.equals(review.request) : review.request != null) {
            return false;
        }
        if (response != null ? !response.equals(review.response) : review.response != null) {
            return false;
        }
        return grade == review.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), request, response, grade);
    }

    @Override
    public String toString() {
        return String.format("Review[request_id='%,d', response_id='%,d', grade='%s', details='%s']",
                             request.getId(),
                             response.getId(),
                             grade.toString(),
                             details);
    }
}
