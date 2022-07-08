package com.example.practice.domains.dto;

import java.util.List;

public class SurveyTargetListDto {
    private List<String> targetClients;
    public List<String> getTargetClients() {
        return targetClients;
    }

    public void setTargetClients(List<String> targetClients) {
        this.targetClients = targetClients;
    }
    public boolean hasElements(){
        return getTargetClients() != null && getTargetClients().size() > 0;
    }
}
