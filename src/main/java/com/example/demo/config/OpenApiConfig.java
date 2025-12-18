package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // You need to change the port as per your server
                .servers(List.of(
                        new Server().url("https://9005.vs.amypo.ai")
                ));
        }
}

Multi-Level Approval Workflow Engine
This Multi-Level Approval Workflow Engine lets an authenticated user (a requester) log in via JWT, submit a new
approval request against a chosen workflow template, and then automatically routes that request through a chain of
configured steps (LI L2 L3.„) where users in specific approver roles review it and record actions
(APPROVED/REJECTED) while every step is stored as an ApprovalAction and mirrored in AuditLogRecord for a complete
trail; templates and their steps are defined by an ADMINICONFIGURATOR role using WorkflowTemplate and
WorkflowStepConfig (each step linked to an approverRole like MANAGER, FINANCE, DIRECTOR), everyday business users
act as REQUESTERS who create and track their requests, designated APPROVERS (LI/L2/L3 based on their Role mapping)
see their pending items and approve/reject at their configured level, and optional AUDITOR/REPORTING users can read
templates, steps, requests, actions, and audit logs to monitor SLA, escalations, and compliance, while the
HibernateQueryUtil supports advanced queries like "all actions for a given approver," and the whole thing is exposed as
REST APIs under /api/** protected by JWT with /auth/register and /auth/login for user onboarding and authentication.
Constraint: The root package for the project must be com.example.demo.
Project Requirements: Spring Boot + Hibernate + MySQL + JWT + Swagger

STEP O — Technical Constraints (Critical for Testing)
You must strictly follow these technical rules to pass the automated test suite:
Hibernate Query Utility:
Create a class com.example.demo.util.HibernateQueryUtil annotated with @Component.
It must implement a method: public List< ApprovalAction> findActionsByApproverUsingCriteria(Long approverld).
This method must use Hibernate Criteria API or JPA CriteriaBuiIder to fetch actions by approverld.
Entity Constraints (Critical):
2.
WorkflowStepConfig: Must have a field private Long templateld; (Do NOT use @ManyToOne relationship for this field;
store the ID directly as a Long for test compatibility).
User: Ensure the class is named User (not UserAccount) and has a field Set< Role) roles (Many-to-Many).
Repository Method Signatures (Exact Naming Required):
3.
WorkflowStepConfigRepository: findByTemplateldOrderByLevelNumberAsc(Long templateld).
ApprovalActionRepository: findByLevelAndAction (Integer levelNumber, String action).
AuditLogRecordRepository: findByRequestld(Long requestld).
RoleRepository: Optional<Role> findByName(String name).
4. Service Constructor Signatures: To ensure your code works with the automated test suite, your Service constructors must
accept dependencies in the following exact order:
o WorkflowTempIateService: (workflowTemplateRepository)
o WorkflowStepConfigService: (workflowstepconfigRepository)
o ApprovalRequestService: (ApprovalRequestRepository, WorkflowStepConfigRepository, WorkflowTemplateRepository,
ApprovalActionRepository)
o ApprovalActionService: (ApprovalActionRepositoryt ApprovalRequestRepository)
o UserService: (UserRepository, RoleRepository, passwordEncoder)
5.
Service Method Signatures:
UserService: Must have User registerUser(User user, String roleName).
JwtTokenProvider: generateToken(User user) must accept the User entity.
6.
Servlet Requirement:
Create com.example.demo.servlet.SimpleServlet extending HttpServlet.
Map it to [simple-servlet.
doGet must return "0K".
STEP 1 - Data Models (Hibernate / JPA)
1. WorkflowTempIate
Fields: id (Long, PK), templateName (String, unique), description (String), totalLevels (Integer), active (Boolean)
Rules: templateName must be unique.
2. WorkflowStepConfig
Fields: id (Long, PK), templateld (Long), levelNumber (Integer), approverRole (String), isFinalStep (Boolean), instructions
(string)
Rules: templateld stores the ID of the parent template.
3. ApprovalRequest
Fields: id (Long, PK), templateld (Long), requesterld (Long), requestTitle (string), requestPayloadJson (string, LOB), status
(string), currentLevel (Integer), createdAt (LocalDateTime)
Rules: status defaults to "PENDING".
4. ApprovalAction
Fields: id (Long, PK), requestid (Long), approverld (Long), levelNumber (Integer), action (string: APPROVED/REJECTED),
comments (string), actionDate (LocalDateTime)
5. AuditLogRecord
Fields: id (Long, PK), requestld (Long), eventType (String), details (String), loggedAt (LocalDateTime)
User: idi username, email, password, roles (ManyToMany).
Role: id, name.
STEP 2 - Authentication Using JWT
Endpoints: /auth/register, /auth/login.
Access: All endpoints require JWT.
STEP 3 - Create Repositories (MySQL)
WorkflowTemplateRepository, WorkflowStepConfigRepository, ApprovalRequestRepository, ApprovalActionRepository,
AuditLogRecordRepository, UserRepository, RoleRepository.
STEP 4 — Implement Service Interfaces
1. WorkflowTempIateService
createTemplate(WorkflowTemplate t)
getTemplateByld(Long id)
getAllTemplates()
updateTemplate(Long id, WorkflowTemplate t)
activateTemplate(Long id, boolean active)
2. WorkflowStepConfigService
createstep(workflowstepconfig step)
getStepsForTemplate(Long templateld)
3. ApprovalRequestService
createRequest(ApprovalRequest req)
getRequestsByRequester(Long userld)
getAllRequests()
4. ApprovalActionService
recordAction(ApprovalAction action)
5. Userservice
registerUser(User user, String roleName)
findByUsername(String username)
STEP 5 - REST Controllers
All under /api/ with Swagger annotations.
1. WorkflowTempIateControIIer (/api/templates)
POST / — Create template
GET /{id} - Get template
PUT /{id} — IJpdate template
GET / — List templates
2. WorkflowStepConfigControIIer (/api/steps)
POST / - create step
GET [template/ {templateld} — List steps
3. ApprovalRequestControlIer (/api/requests)
POST / — Create request
GET / — List requests
4. AuthControlIer (/auth)
POST [register — Register using RegisterRequest DTO.
POST [login - Login using AuthRequest DTO.
STEP 6 — Swagger / OpenAPI Requirement
Swagger Ul at: /swagger-ui/index.html
Include JWT Support.