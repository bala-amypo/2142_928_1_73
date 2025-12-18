public interface ApprovalActionRepository extends JpaRepository<ApprovalAction, Long> {
    List<ApprovalAction> findByLevelAndAction(Integer levelNumber, String action);
}