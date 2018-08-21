package com.ps.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * A template class which defines the common template for all entities in the project.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    // TODO почему Long а не long?
    protected Long id;

    /**
     * This field is used for auditory and logging purposes.
     * It is populated by the system when an entity instance is created.
     */
    @JsonIgnore
    @Column(name = "CREATED_AT", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date createdAt = new Date();

    /**
     * This field is used for auditory and logging purposes.
     * It is populated by the system when an entity instance is modified.
     */
    @JsonIgnore
    @Column(name = "MODIFIED_AT", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt = new Date();

    @JsonIgnore
    @Version
    public int version;

    /**
     * This constructor is required by JPA.
     * All subclasses of this class will inherit this constructor.
     */
    protected AbstractEntity() {}

    /**
     * This identifier is unique per entity.
     * Used by persistence frameworks used in a project, and although is public, it should not be used by application code.
     * This identifier is mapped by ORM (Object Relational Mapper) to the database primary key of the Person record to which the entity instance is mapped.
     *
     * @return the unique entity identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * This identifier is unique per entity.
     * Is is used by persistence frameworks and although is public, it should never be set by application code.
     *
     * @param id the unique entity identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return Objects.equals(id, ((AbstractEntity) other).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        // TODO вынести в util
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("AbstractEntity[id='%d%n', createdAt='%s', modifiedAt='%s', version='%d%n']", id, sdf.format(createdAt), sdf.format(modifiedAt), version);
    }
}

