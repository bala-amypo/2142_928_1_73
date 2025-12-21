@Service
public class ApprovalRequestService {

    private final ApprovalRequestRepository repo;

    public ApprovalRequestService(ApprovalRequestRepository repo) {
        this.repo = repo;
    }

    public ApprovalRequest create(ApprovalRequest req) {
        req.setStatus("PENDING");
        req.setCurrentLevel(1);
        req.setCreatedAt(java.time.LocalDateTime.now());
        return repo.save(req);
    }

    public List<ApprovalRequest> getAll() {
        return repo.findAll();
    }
}
