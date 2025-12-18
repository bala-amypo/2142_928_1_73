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
