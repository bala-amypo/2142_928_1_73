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


////////////////////////////////////

SERVICES








/////////////////







/////////////////

CONTROLLERS

WorkflowTemplateController


package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.service.WorkflowTemplateService;

@RestController
@RequestMapping("/api/templates")
public class WorkflowTemplateController {

    private final WorkflowTemplateService service;

    public WorkflowTemplateController(WorkflowTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public WorkflowTemplate create(@RequestBody WorkflowTemplate t) {
        return service.createTemplate(t);
    }

    @GetMapping("/{id}")
    public WorkflowTemplate get(@PathVariable Long id) {
        return service.getTemplateByld(id);
    }

    @GetMapping
    public List<WorkflowTemplate> list() {
        return service.getAllTemplates();
    }

    @PutMapping("/{id}")
    public WorkflowTemplate update(@PathVariable Long id,
                                   @RequestBody WorkflowTemplate t) {
        return service.updateTemplate(id, t);
    }
}


////////////////////////


AuthController


package com.example.demo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return userService.registerUser(user, request.getRole());
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userService.findByUsername(request.getUsername());
        return jwtTokenProvider.generateToken(user);
    }
}


////////////////////////////////


WorkflowStepConfigController



package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.WorkflowStepConfig;
import com.example.demo.service.WorkflowStepConfigService;

@RestController
@RequestMapping("/api/steps")
public class WorkflowStepConfigController {

    private final WorkflowStepConfigService service;

    public WorkflowStepConfigController(WorkflowStepConfigService service) {
        this.service = service;
    }

    @PostMapping
    public WorkflowStepConfig create(@RequestBody WorkflowStepConfig step) {
        return service.createstep(step);
    }

    @GetMapping("/template/{templateld}")
    public List<WorkflowStepConfig> list(@PathVariable Long templateld) {
        return service.getStepsForTemplate(templateld);
    }
}


////////////////////////////


ApprovalRequestController



package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApprovalRequest;
import com.example.demo.service.ApprovalRequestService;

@RestController
@RequestMapping("/api/requests")
public class ApprovalRequestController {

    private final ApprovalRequestService service;

    public ApprovalRequestController(ApprovalRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ApprovalRequest create(@RequestBody ApprovalRequest req) {
        return service.createRequest(req);
    }

    @GetMapping
    public List<ApprovalRequest> list() {
        return service.getAllRequests();
    }
}







