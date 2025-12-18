@Entity
public class ApprovalAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestid;
    private Long approverld;
    private Integer levelNumber;

    private String action; // APPROVED / REJECTED
    private String comments;

    private LocalDateTime actionDate = LocalDateTime.now();
}




////////////////////////////////////

REPOSITORIES


WorkflowStepConfigRepository

public interface WorkflowStepConfigRepository extends JpaRepository<WorkflowStepConfig, Long> {
    List<WorkflowStepConfig> findByTemplateldOrderByLevelNumberAsc(Long templateld);
}





AuditLogRecordRepository


public interface AuditLogRecordRepository extends JpaRepository<AuditLogRecord, Long> {
    List<AuditLogRecord> findByRequestld(Long requestld);
}


RoleRepository 

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
////////////////////////////////


CONTROLLERS
















