


//////////////////

WorkflowStepConfig

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workflow_step_config")
public class WorkflowStepConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long templateld;

    private Integer levelNumber;
    private String approverRole;
    private Boolean isFinalStep;
    private String instructions;

    // getters & setters
}


////////


ApprovalRequest


package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "approval_request")
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

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // getters & setters
}


//////////

ApprovalAction


package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "approval_action")
public class ApprovalAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestid;
    private Long approverld;
    private Integer levelNumber;

    private String action;
    private String comments;

    @Column(nullable = false)
    private LocalDateTime actionDate = LocalDateTime.now();

    // getters & setters
}


\\\\\\\\\\\


AuditLogRecord


package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_log_record")
public class AuditLogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestld;
    private String eventType;
    private String details;

    @Column(nullable = false)
    private LocalDateTime loggedAt = LocalDateTime.now();

    // getters & setters
}


////////////////


User

package com.example.demo.entity;

import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    // getters & setters
}


\\\\\\\\\\\\\


Role


package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    // getters & setters
}

