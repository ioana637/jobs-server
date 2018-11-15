package com.ubb.jobs.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobAbilitiesID implements Serializable {


    private Integer jobId;

    private Integer abilityId;
    public int hashCode() {
        return (int)(abilityId + jobId);
    }

    public boolean equals(Object object) {
        if (object instanceof JobAbilitiesID) {
            JobAbilitiesID otherId = (JobAbilitiesID) object;
            return (otherId.jobId.equals(this.jobId)) && (otherId.abilityId.equals(this.abilityId));
        }
        return false;
    }

}
