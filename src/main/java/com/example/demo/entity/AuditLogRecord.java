@Entity
public class AuditLogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestld;
    private String eventType;
    private String details;

    private LocalDateTime loggedAt = LocalDateTime.now();
}
