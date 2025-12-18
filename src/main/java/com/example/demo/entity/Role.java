com.example.demo
├── DemoApplication.java
├── config
│   ├── JwtAuthenticationFilter.java
│   ├── JwtTokenProvider.java
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
├── controller
│   ├── AuthController.java
│   ├── WorkflowTemplateController.java
│   ├── WorkflowStepConfigController.java
│   └── ApprovalRequestController.java
├── dto
│   ├── AuthRequest.java
│   ├── RegisterRequest.java
│   └── AuthResponse.java
├── entity
│   ├── WorkflowTemplate.java
│   ├── WorkflowStepConfig.java
│   ├── ApprovalRequest.java
│   ├── ApprovalAction.java
│   ├── AuditLogRecord.java
│   ├── User.java
│   └── Role.java
├── repository
│   ├── 
│   ├── WorkflowStepConfigRepository.java
│   ├── ApprovalRequestRepository.java
│   ├── ApprovalActionRepository.java
│   ├── AuditLogRecordRepository.java
│   ├── UserRepository.java
│   └── RoleRepository.java
├── service
│   ├── WorkflowTemplateService.java
│   ├── WorkflowStepConfigService.java
│   ├── ApprovalRequestService.java
│   ├── ApprovalActionService.java
│   └── UserService.java
├── servlet
│   └── SimpleServlet.java
└── util
    └── HibernateQueryUtil.java
