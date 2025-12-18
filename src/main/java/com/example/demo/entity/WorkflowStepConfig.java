@Entity
public class WorkflowStepConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long templateld; // MUST be Long

    private Integer levelNumber;
    private String approverRole;
    private Boolean isFinalStep;
    private String instructions;
}

