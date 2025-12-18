@Entity
public class ApprovalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long templateld;
    private Long requesterld;

    private String requestTitle;

    @Lob
    private String requestPayloadJson;

    private String status = "PENDING";
    private Integer currentLevel;

    private LocalDateTime createdAt = LocalDateTime.now();
}