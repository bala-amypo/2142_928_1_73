package com.example.demo.service.impl;

import com.example.demo.model.WorkflowStepConfig;
import com.example.demo.repository.WorkflowStepConfigRepository;
import com.example.demo.service.WorkflowStepConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowStepConfigServiceImpl implements WorkflowStepConfigService {
    
    private final WorkflowStepConfigRepository stepRepository;
    
    @Override
    @Transactional
    public WorkflowStepConfig createStep(WorkflowStepConfig step) {
        return stepRepository.save(step);
    }
    
    @Override
    public List<WorkflowStepConfig> getStepsForTemplate(Long templateId) {
        return stepRepository.findByTemplateIdOrderByLevelNumberAsc(templateId);
    }
}