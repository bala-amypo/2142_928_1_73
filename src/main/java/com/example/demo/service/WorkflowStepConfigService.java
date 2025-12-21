@Service
public class WorkflowStepConfigService {

    private final WorkflowStepConfigRepository repo;

    public WorkflowStepConfigService(WorkflowStepConfigRepository repo) {
        this.repo = repo;
    }

    public WorkflowStepConfig createStep(WorkflowStepConfig step) {
        return repo.save(step);
    }

    public List<WorkflowStepConfig> getSteps(Long templateId) {
        return repo.findByTemplateIdOrderByLevelNumberAsc(templateId);
    }
}
