WorkflowTemplate 

@Entity
public class WorkflowTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String templateName;

    private String description;
    private Integer totalLevels;
    private Boolean active;
}


WorkflowStepConfig

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



ApprovalAction


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



User


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}




////////////////////////////////////

REPOSITORIES


WorkflowStepConfigRepository

public interface WorkflowStepConfigRepository extends JpaRepository<WorkflowStepConfig, Long> {
    List<WorkflowStepConfig> findByTemplateldOrderByLevelNumberAsc(Long templateld);
}


ApprovalActionRepository 


public interface ApprovalActionRepository extends JpaRepository<ApprovalAction, Long> {
    List<ApprovalAction> findByLevelAndAction(Integer levelNumber, String action);
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


UserService



@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(role));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}

/////////////////////

ApprovalRequestService

package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalRequest;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.repository.WorkflowStepConfigRepository;
import com.example.demo.repository.WorkflowTemplateRepository;

@Service
public class ApprovalRequestService {

    private final ApprovalRequestRepository approvalRequestRepository;
    private final WorkflowStepConfigRepository workflowStepConfigRepository;
    private final WorkflowTemplateRepository workflowTemplateRepository;
    private final ApprovalActionRepository approvalActionRepository;

    // ✅ Constructor order MUST match EXACTLY
    public ApprovalRequestService(
            ApprovalRequestRepository approvalRequestRepository,
            WorkflowStepConfigRepository workflowStepConfigRepository,
            WorkflowTemplateRepository workflowTemplateRepository,
            ApprovalActionRepository approvalActionRepository) {

        this.approvalRequestRepository = approvalRequestRepository;
        this.workflowStepConfigRepository = workflowStepConfigRepository;
        this.workflowTemplateRepository = workflowTemplateRepository;
        this.approvalActionRepository = approvalActionRepository;
    }

    public ApprovalRequest createRequest(ApprovalRequest req) {
        req.setStatus("PENDING");
        req.setCreatedAt(LocalDateTime.now());
        req.setCurrentLevel(1);
        return approvalRequestRepository.save(req);
    }

    public List<ApprovalRequest> getRequestsByRequester(Long userld) {
        return approvalRequestRepository.findByRequesterld(userld);
    }

    public List<ApprovalRequest> getAllRequests() {
        return approvalRequestRepository.findAll();
    }
}

/////////////////

WorkflowTemplateService

package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.repository.WorkflowTemplateRepository;

@Service
public class WorkflowTemplateService {

    private final WorkflowTemplateRepository workflowTemplateRepository;

    // ✅ Constructor order MUST match
    public WorkflowTemplateService(WorkflowTemplateRepository workflowTemplateRepository) {
        this.workflowTemplateRepository = workflowTemplateRepository;
    }

    public WorkflowTemplate createTemplate(WorkflowTemplate t) {
        return workflowTemplateRepository.save(t);
    }

    public WorkflowTemplate getTemplateByld(Long id) {
        return workflowTemplateRepository.findById(id).orElseThrow();
    }

    public List<WorkflowTemplate> getAllTemplates() {
        return workflowTemplateRepository.findAll();
    }

    public WorkflowTemplate updateTemplate(Long id, WorkflowTemplate t) {
        WorkflowTemplate existing = getTemplateByld(id);
        existing.setTemplateName(t.getTemplateName());
        existing.setDescription(t.getDescription());
        existing.setTotalLevels(t.getTotalLevels());
        existing.setActive(t.getActive());
        return workflowTemplateRepository.save(existing);
    }

    public WorkflowTemplate activateTemplate(Long id, boolean active) {
        WorkflowTemplate template = getTemplateByld(id);
        template.setActive(active);
        return workflowTemplateRepository.save(template);
    }
}


///////

WorkflowStepConfigService

package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowStepConfig;
import com.example.demo.repository.WorkflowStepConfigRepository;

@Service
public class WorkflowStepConfigService {

    private final WorkflowStepConfigRepository workflowstepconfigRepository;

    // ✅ Constructor order MUST match
    public WorkflowStepConfigService(WorkflowStepConfigRepository workflowstepconfigRepository) {
        this.workflowstepconfigRepository = workflowstepconfigRepository;
    }

    public WorkflowStepConfig createstep(WorkflowStepConfig step) {
        return workflowstepconfigRepository.save(step);
    }

    public List<WorkflowStepConfig> getStepsForTemplate(Long templateld) {
        return workflowstepconfigRepository
                .findByTemplateldOrderByLevelNumberAsc(templateld);
    }
}

/////////////////

ApprovalActionService

package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalAction;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;

@Service
public class ApprovalActionService {

    private final ApprovalActionRepository approvalActionRepository;
    private final ApprovalRequestRepository approvalRequestRepository;

    // ✅ Constructor order MUST match
    public ApprovalActionService(
            ApprovalActionRepository approvalActionRepository,
            ApprovalRequestRepository approvalRequestRepository) {

        this.approvalActionRepository = approvalActionRepository;
        this.approvalRequestRepository = approvalRequestRepository;
    }

    public ApprovalAction recordAction(ApprovalAction action) {
        return approvalActionRepository.save(action);
    }
}


////////////////

UserService


package com.example.demo.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor order MUST match EXACTLY
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user, String roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(role));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}






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







