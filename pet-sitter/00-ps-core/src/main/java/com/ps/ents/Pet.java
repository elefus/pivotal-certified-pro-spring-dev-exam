package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.PetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name="P_PET")
public class Pet extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private User owner;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PET_TYPE")
    private PetType petType;

    @NotEmpty
    @Column
    @Size(max = 100)
    private String name;

    @Column
    @NotNull
    private Integer age;


    @Size(max = 500)
    @Column
    private String details;

    /**
     * The pet has a RFID microchip implant.
     */
    @NotEmpty
    @Column
    @Size(min = 10, max = 100)
    private String rfid;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        Pet that = (Pet) other;
        if (owner != null ? !Objects.equals(owner.getId(), that.owner.getId()) : that.owner != null) {
            return false;
        }
        return Objects.equals(petType, that.petType)
            && Objects.equals(name, that.name)
            && Objects.equals(age, that.age)
            && Objects.equals(rfid, that.rfid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), owner, petType, name, age, rfid);
    }

    @Override
    public String toString() {
        return String.format("Pet[id='%,d', owner='%s', pet type='%s', pet name='%s', age='%d']",
                             id,
                             Optional.of(owner).map(User::getId).map(Object::toString).orElse(""),
                             petType,
                             name,
                             age);
    }
}
