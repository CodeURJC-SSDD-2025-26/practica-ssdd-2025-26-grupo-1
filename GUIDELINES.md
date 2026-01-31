# Project Guidelines

**Version:** 1.0  
**Last Updated:** January 31, 2026  
**Status:** Draft - Pending Team Approval

---

## Table of Contents

1. [Introduction](#introduction)
2. [Technology Stack](#technology-stack)
3. [Coding Style Guide](#coding-style-guide)
4. [Coding Conventions](#coding-conventions)
5. [Coding Standards](#coding-standards)
6. [Other (Workflow & Processes)](#other-workflow--processes)

---

## Introduction

This document establishes the development guidelines for our distributed web application project. These guidelines are designed to ensure code consistency, maintainability, and professional quality across all components of the system.

**Team Composition:**
- 4 developers with Java experience (basic-intermediate level)
- New to HTML, CSS, JavaScript, and Vue.js
- New to Spring Boot framework
- Basic-intermediate SQL knowledge

**Important Notes:**
- All code, comments, and documentation must be written in **English**
- These guidelines may evolve as the project progresses
- All changes to these guidelines require team consensus
- Deviations must be justified and documented
- Automated tools are configured to help maintain consistency

---

## Technology Stack

### Frontend
- **Framework:** Vue.js 3 (Composition API recommended)
- **UI Framework:** Bootstrap 5 + Quasar (optional)
- **Languages:** HTML5, CSS3, JavaScript (ES6+)
- **Build Tool:** Vite (recommended) or Vue CLI
- **IDE:** WebStorm (JetBrains)

### Backend
- **Framework:** Spring Boot 3.x
- **Language:** Java 17 or higher
- **Libraries:** Lombok for boilerplate reduction
- **Build Tool:** Maven
- **Architecture:** Hexagonal Architecture (Ports & Adapters)
- **IDE:** IntelliJ IDEA Ultimate (JetBrains)

### Database
- **RDBMS:** MySQL 8.x
- **ORM:** Spring Data JPA / Hibernate
- **IDE:** DataGrip or IntelliJ IDEA Database Tools (JetBrains)

### Development Tools
- **Version Control:** Git + GitHub
- **Containerization:** Docker (for Practice 3)
- **API Documentation:** OpenAPI/Swagger
- **Code Formatters:** Prettier (Frontend), Google Java Format (Backend)
- **Linting:** ESLint (Frontend), Checkstyle (Backend)

---

## Coding Style Guide

This section defines formatting and visual consistency rules for all code in the project.

### General Principles

1. **Consistency over preference** - Follow team standards even if they differ from personal style
2. **Readability first** - Code is read more often than written
3. **Use automated formatters** - Configure your IDE to format on save

---

### IDE Configuration & Automated Formatting

To ensure consistency across the team, we use automated code formatters configured in our JetBrains IDEs.

#### WebStorm (Frontend) Setup

**Install Prettier:**
```bash
npm install --save-dev prettier
```

**Create `.prettierrc` in project root:**
```json
{
  "semi": true,
  "trailingComma": "es5",
  "singleQuote": true,
  "printWidth": 100,
  "tabWidth": 2,
  "useTabs": false,
  "arrowParens": "avoid",
  "endOfLine": "lf"
}
```

**Create `.prettierignore`:**
```
node_modules
dist
build
coverage
*.min.js
*.min.css
```

**WebStorm Configuration:**
1. Go to `Settings → Languages & Frameworks → JavaScript → Prettier`
2. Set Prettier package path: `{project}/node_modules/prettier`
3. Check ✅ "Run on save"
4. Check ✅ "Run on 'Reformat Code'"
5. Set Run for files: `{**/*,*}.{js,ts,jsx,tsx,vue,css,scss,json,md}`

**ESLint Setup (Optional but Recommended):**
```bash
npm install --save-dev eslint eslint-plugin-vue
```

**Create `.eslintrc.js`:**
```javascript
module.exports = {
  root: true,
  env: {
    browser: true,
    es2021: true,
    node: true,
  },
  extends: [
    'eslint:recommended',
    'plugin:vue/vue3-recommended',
  ],
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  rules: {
    'vue/multi-word-component-names': 'off',
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
  },
};
```

---

#### IntelliJ IDEA (Backend) Setup

**Install Google Java Format Plugin:**
1. Go to `Settings → Plugins`
2. Search for "google-java-format"
3. Install and restart IntelliJ

**Configure Google Java Format:**
1. Go to `Settings → Other Settings → google-java-format Settings`
2. Check ✅ "Enable google-java-format"
3. Select code style: **AOSP** (4-space indentation)

**Configure Save Actions:**
1. Go to `Settings → Tools → Actions on Save`
2. Check ✅ "Reformat code"
3. Check ✅ "Optimize imports"
4. Check ✅ "Run code cleanup"

**Project Code Style (IntelliJ):**
1. Go to `Settings → Editor → Code Style → Java`
2. Set Tab size: **4**
3. Set Indent: **4**
4. Check ✅ "Use tab character": **NO** (use spaces)
5. Set Continuation indent: **8**

**Checkstyle Configuration (Optional):**
Create `checkstyle.xml` in project root:
```xml

<!DOCTYPE module PUBLIC
  "-//Checkstyle//DTD Checkstyle Configuration 1.3//EN"
  "https://checkstyle.org/dtds/configuration_1_3.dtd">


  
    
    
    
    
    
    
    
    
    
    
    
    
      
      
      
    
  

```

**Enable Checkstyle in IntelliJ:**
1. Install Checkstyle plugin
2. Go to `Settings → Tools → Checkstyle`
3. Add configuration file: `checkstyle.xml`
4. Set as active

---

### Shared IDE Settings (All JetBrains IDEs)

**File Encoding:**
- Go to `Settings → Editor → File Encodings`
- Set all to **UTF-8**

**Line Separators:**
- Set to **LF** (Unix/macOS) for cross-platform compatibility
- `Settings → Editor → Code Style → General → Line separator`

**Auto-save:**
- Enabled by default in JetBrains IDEs
- No action needed

**EditorConfig (Unified Configuration):**

Create `.editorconfig` in project root for consistency across all IDEs:
```ini
# EditorConfig is awesome: https://EditorConfig.org

root = true

# All files
[*]
charset = utf-8
end_of_line = lf
insert_final_newline = true
trim_trailing_whitespace = true

# Frontend files (HTML, CSS, JS, Vue)
[*.{html,css,scss,js,ts,jsx,tsx,vue,json}]
indent_style = space
indent_size = 2

# Java files
[*.java]
indent_style = space
indent_size = 4

# SQL files
[*.sql]
indent_style = space
indent_size = 2

# Markdown
[*.md]
trim_trailing_whitespace = false

# YAML
[*.{yml,yaml}]
indent_style = space
indent_size = 2
```

JetBrains IDEs automatically recognize `.editorconfig` files.

---

### Frontend (HTML, CSS, JavaScript, Vue.js)

#### HTML

**Indentation:**
```html


  
    Title
  

```

**Format:**
- **Indentation:** 2 spaces (no tabs)
- **Lowercase tags and attributes:** `<div class="example">` not `<DIV CLASS="example">`
- **Close all tags:** Even self-closing tags should use `<img />` format
- **Attribute quotes:** Always use double quotes `"` for attribute values
- **Semantic HTML:** Use semantic tags (`<header>`, `<nav>`, `<main>`, `<footer>`, etc.)

**Ordering:**
```html


  Submit

```

---

#### CSS

**Indentation:**
```css
/* Use 2 spaces for indentation */
.container {
  display: flex;
  flex-direction: column;
}

.container .item {
  padding: 1rem;
}
```

**Format:**
- **Indentation:** 2 spaces
- **Naming:** Use kebab-case for class names: `user-profile-card`
- **Property order:** Group related properties (positioning, box model, typography, visual, misc)
- **One selector per line** for multiple selectors
- **Space after colon:** `color: #333;` not `color:#333;`
- **End with semicolon:** Always include trailing semicolon

**Property Ordering:**
```css
.example {
  /* Positioning */
  position: relative;
  top: 0;
  left: 0;
  z-index: 10;
  
  /* Box Model */
  display: flex;
  width: 100%;
  height: auto;
  padding: 1rem;
  margin: 0;
  
  /* Typography */
  font-family: 'Arial', sans-serif;
  font-size: 1rem;
  line-height: 1.5;
  
  /* Visual */
  background-color: #fff;
  border: 1px solid #ddd;
  color: #333;
  
  /* Misc */
  cursor: pointer;
  transition: all 0.3s ease;
}
```

**BEM Methodology (Recommended for complex components):**
```css
/* Block */
.user-card {}

/* Element */
.user-card__header {}
.user-card__body {}

/* Modifier */
.user-card--featured {}
.user-card__header--large {}
```

---

#### JavaScript

**Indentation:**
```javascript
// Use 2 spaces for indentation
function calculateTotal(items) {
  let total = 0;
  
  items.forEach(item => {
    total += item.price;
  });
  
  return total;
}
```

**Format:**
- **Indentation:** 2 spaces
- **Semicolons:** Always use semicolons
- **Quotes:** Use single quotes `'` for strings (except in JSON)
- **Line length:** Maximum 100 characters (soft limit, use judgment)
- **Spacing:** Space after keywords, around operators
- **Braces:** Opening brace on same line (K&R style)

**Spacing Examples:**
```javascript
// ✅ Correct
if (condition) {
  doSomething();
}

for (let i = 0; i < 10; i++) {
  process(i);
}

const result = a + b * c;

// ❌ Incorrect
if(condition){
  doSomething();
}

for(let i=0;i<10;i++){
  process(i);
}

const result=a+b*c;
```

**Arrow Functions:**
```javascript
// ✅ Correct - Single parameter without parentheses
const double = x => x * 2;

// ✅ Correct - Multiple parameters with parentheses
const add = (a, b) => a + b;

// ✅ Correct - Block body with explicit return
const process = data => {
  const result = transform(data);
  return result;
};
```

---

#### Vue.js Specific

**Component Structure Order:**
```vue
<template>
  <!-- Template content -->
</template>

<script>
// Script content
</script>

<style scoped>
/* Styles content */
</style>
```

**Script Section (Composition API):**
```vue
<script setup>
// 1. Imports
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import UserService from '@/services/UserService';

// 2. Props
const props = defineProps({
  userId: {
    type: Number,
    required: true
  }
});

// 3. Emits
const emit = defineEmits(['update', 'delete']);

// 4. Reactive state
const user = ref(null);
const loading = ref(false);

// 5. Computed properties
const fullName = computed(() => {
  return `${user.value.firstName} ${user.value.lastName}`;
});

// 6. Methods
const fetchUser = async () => {
  loading.value = true;
  try {
    user.value = await UserService.getById(props.userId);
  } catch (error) {
    console.error('Failed to fetch user:', error);
  } finally {
    loading.value = false;
  }
};

// 7. Lifecycle hooks
onMounted(() => {
  fetchUser();
});
</script>
```

**Template:**
```vue
<template>
  <!-- Use 2 spaces for indentation -->
  <div class="user-profile">
    <h2>{{ fullName }}</h2>
    
    <!-- Use v-if/v-else-if/v-else for conditional rendering -->
    <div v-if="loading">
      Loading...
    </div>
    
    <div v-else-if="user">
      <p>Email: {{ user.email }}</p>
    </div>
    
    <div v-else>
      No user found
    </div>
    
    <!-- Events with explicit handlers -->
    <button @click="handleUpdate">
      Update
    </button>
  </div>
</template>
```

---

### Backend (Java / Spring Boot)

#### General Java Formatting

**Indentation:**
```java
// Use 4 spaces for indentation (Java convention)
public class UserService {
    private final UserRepository userRepository;
    
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }
}
```

**Format:**
- **Indentation:** 4 spaces (standard Java convention)
- **Braces:** Opening brace on same line for methods, classes
- **Line length:** Maximum 120 characters
- **One statement per line**
- **Spacing:** Space after keywords, around operators

**Import Organization:**
```java
// 1. Java/javax imports
import java.util.List;
import java.util.Optional;

// 2. Third-party libraries (alphabetical)
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 3. Project imports (alphabetical)
import com.yourproject.dto.UserDTO;
import com.yourproject.entity.User;
import com.yourproject.repository.UserRepository;
```

**Class Structure Order:**
```java
public class UserService {
    
    // 1. Static fields
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    
    // 2. Instance fields
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    // 3. Constructors
    public UserService(UserRepository userRepository, 
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    // 4. Public methods
    public User createUser(UserDTO dto) {
        // Implementation
    }
    
    // 5. Protected methods
    protected boolean validateUser(User user) {
        // Implementation
    }
    
    // 6. Private methods
    private String hashPassword(String password) {
        // Implementation
    }
    
    // 7. Getters/Setters (if not using Lombok)
}
```

---

#### Spring Boot Specific

**Controller Format:**
```java
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping
    public ResponseEntity createUser(
            @Valid @RequestBody CreateUserRequest request) {
        UserDTO created = userService.create(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(created);
    }
}
```

**Service Layer:**
```java
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        log.debug("Finding user with id: {}", id);
        
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "User not found with id: " + id
            ));
        
        return userMapper.toDTO(user);
    }
    
    @Transactional
    public UserDTO create(CreateUserRequest request) {
        log.info("Creating new user: {}", request.getEmail());
        
        // Business logic
        User user = userMapper.toEntity(request);
        User saved = userRepository.save(user);
        
        return userMapper.toDTO(saved);
    }
}
```

**Entity Format:**
```java
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List orders;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
```

---

### SQL / MySQL

**Format:**
```sql
-- Use UPPERCASE for SQL keywords
-- Use snake_case for table and column names
-- Indent for readability

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Query format
SELECT 
    u.id,
    u.email,
    u.first_name,
    u.last_name,
    COUNT(o.id) AS order_count
FROM users u
LEFT JOIN orders o ON u.id = o.user_id
WHERE u.created_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)
GROUP BY u.id
ORDER BY order_count DESC
LIMIT 10;
```

---

## Coding Conventions

This section defines naming conventions, file organization, and structural patterns.

### General Naming Conventions

**English Only:**
- All code, comments, variable names, function names, class names must be in English
- Exception: User-facing text/content that will be displayed in the UI can be in Spanish

**Descriptive Names:**
- Use clear, descriptive names that reveal intent
- Avoid abbreviations unless universally understood (id, url, etc.)
- Prefer `userRepository` over `repo` or `ur`

---

### Frontend Conventions

#### File and Directory Structure

```
frontend/
├── public/
│   └── index.html
├── src/
│   ├── assets/              # Static assets (images, fonts)
│   │   ├── images/
│   │   └── styles/
│   ├── components/          # Reusable components
│   │   ├── common/          # Generic components (buttons, inputs)
│   │   └── features/        # Feature-specific components
│   ├── views/               # Page-level components
│   │   ├── Home.vue
│   │   ├── Login.vue
│   │   └── Dashboard.vue
│   ├── router/              # Vue Router configuration
│   │   └── index.js
│   ├── store/               # State management (Pinia/Vuex)
│   │   ├── modules/
│   │   └── index.js
│   ├── services/            # API service layer
│   │   ├── api.js           # Axios configuration
│   │   ├── UserService.js
│   │   └── AuthService.js
│   ├── utils/               # Utility functions
│   │   ├── validators.js
│   │   └── formatters.js
│   ├── composables/         # Vue composables
│   │   └── useAuth.js
│   ├── App.vue
│   └── main.js
├── .env                     # Environment variables
├── .env.example
├── package.json
└── vite.config.js
```

#### Naming Conventions

**Files:**
- **Components:** PascalCase - `UserCard.vue`, `NavigationBar.vue`
- **Views:** PascalCase - `UserProfile.vue`, `Dashboard.vue`
- **Services:** PascalCase with suffix - `UserService.js`, `AuthService.js`
- **Utilities:** camelCase - `validators.js`, `dateFormatter.js`
- **Composables:** camelCase with `use` prefix - `useAuth.js`, `useForm.js`

**Variables and Functions:**
```javascript
// ✅ Correct - camelCase for variables and functions
const userName = 'John';
const userAge = 25;

function calculateTotal(items) {
  // Implementation
}

const getUserById = async (id) => {
  // Implementation
};

// Constants - SCREAMING_SNAKE_CASE
const MAX_RETRY_ATTEMPTS = 3;
const API_BASE_URL = 'https://api.example.com';
```

**Vue Component Names:**
```javascript
// ✅ Correct - Multi-word component names
// Single-word names conflict with HTML elements
export default {
  name: 'UserCard'      // ✅ Good
}

export default {
  name: 'Card'          // ❌ Bad - single word
}
```

**CSS Classes:**
```css
/* Use kebab-case for all classes */
.user-profile {}
.navigation-bar {}
.submit-button {}

/* BEM for complex components */
.user-card {}
.user-card__header {}
.user-card__body {}
.user-card--featured {}
```

---

### Backend Conventions

#### File and Directory Structure

**Hexagonal Architecture (Ports & Adapters Pattern):**

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── yourproject/
│   │   │           ├── YourProjectApplication.java
│   │   │           │
│   │   │           ├── domain/                    # Core business logic (no dependencies)
│   │   │           │   ├── model/                 # Domain entities
│   │   │           │   │   ├── User.java
│   │   │           │   │   ├── Order.java
│   │   │           │   │   └── Product.java
│   │   │           │   ├── port/                  # Interfaces (contracts)
│   │   │           │   │   ├── in/                # Input ports (use cases)
│   │   │           │   │   │   ├── CreateUserUseCase.java
│   │   │           │   │   │   ├── GetUserUseCase.java
│   │   │           │   │   │   └── DeleteUserUseCase.java
│   │   │           │   │   └── out/               # Output ports (repositories)
│   │   │           │   │       ├── UserRepository.java
│   │   │           │   │       └── EmailNotifier.java
│   │   │           │   ├── service/               # Business logic implementation
│   │   │           │   │   └── UserService.java
│   │   │           │   └── exception/             # Domain exceptions
│   │   │           │       ├── UserNotFoundException.java
│   │   │           │       └── DuplicateUserException.java
│   │   │           │
│   │   │           ├── application/               # Application services (orchestration)
│   │   │           │   ├── service/
│   │   │           │   │   └── UserApplicationService.java
│   │   │           │   └── dto/                   # Data Transfer Objects
│   │   │           │       ├── request/
│   │   │           │       │   └── CreateUserRequest.java
│   │   │           │       └── response/
│   │   │           │           └── UserResponse.java
│   │   │           │
│   │   │           ├── infrastructure/            # Technical implementations (adapters)
│   │   │           │   ├── adapter/
│   │   │           │   │   ├── in/                # Input adapters
│   │   │           │   │   │   └── web/           # REST controllers
│   │   │           │   │   │       ├── UserController.java
│   │   │           │   │   │       └── GlobalExceptionHandler.java
│   │   │           │   │   └── out/               # Output adapters
│   │   │           │   │       ├── persistence/   # Database implementation
│   │   │           │   │       │   ├── UserJpaRepository.java
│   │   │           │   │       │   ├── UserRepositoryAdapter.java
│   │   │           │   │       │   └── entity/
│   │   │           │   │       │       └── UserEntity.java
│   │   │           │   │       └── email/         # Email implementation
│   │   │           │   │           └── EmailNotifierAdapter.java
│   │   │           │   ├── config/                # Configuration classes
│   │   │           │   │   ├── SecurityConfig.java
│   │   │           │   │   ├── SwaggerConfig.java
│   │   │           │   │   └── BeanConfig.java
│   │   │           │   └── mapper/                # Entity ↔ Domain mappers
│   │   │           │       └── UserMapper.java
│   │   │           │
│   │   │           └── shared/                    # Shared utilities
│   │   │               ├── util/
│   │   │               │   └── DateUtils.java
│   │   │               └── constant/
│   │   │                   └── AppConstants.java
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-prod.yml
│   │       └── db/
│   │           └── migration/                     # Database migrations
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── yourproject/
│                   ├── domain/
│                   │   └── service/
│                   ├── application/
│                   │   └── service/
│                   └── infrastructure/
│                       └── adapter/
│                           ├── in/
│                           │   └── web/
│                           └── out/
│                               └── persistence/
├── pom.xml
├── .editorconfig
├── checkstyle.xml
└── README.md
```

**Key Principles of Hexagonal Architecture:**

1. **Domain Layer (Core):**
   - Contains pure business logic
   - No dependencies on frameworks or external libraries
   - Defines interfaces (ports) for external interactions
   - Most important layer - independent and testable

2. **Application Layer:**
   - Orchestrates use cases
   - Coordinates between domain and infrastructure
   - Handles DTOs and data transformation
   - Transaction management

3. **Infrastructure Layer (Adapters):**
   - Implements the ports defined in domain
   - Contains framework-specific code (Spring, JPA, etc.)
   - Handles external communications (REST, database, email, etc.)
   - Can be replaced without affecting domain logic

**Dependency Rule:**
- Infrastructure → Application → Domain
- Domain has NO dependencies on other layers
- Application depends only on Domain
- Infrastructure can depend on both

**Example Implementation:**

```java
// Domain Layer - Port (Interface)
package com.yourproject.domain.port.in;

public interface CreateUserUseCase {
    User createUser(String email, String password, String firstName, String lastName);
}

// Domain Layer - Domain Model
package com.yourproject.domain.model;

@Getter
@Builder
public class User {
    private final Long id;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime createdAt;
    
    // Business logic methods
    public boolean isActive() {
        return createdAt.isAfter(LocalDateTime.now().minusMonths(6));
    }
}

// Domain Layer - Service (implements use case)
package com.yourproject.domain.service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements CreateUserUseCase, GetUserUseCase {
    
    private final UserRepository userRepository; // Port (interface)
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public User createUser(String email, String password, String firstName, String lastName) {
        log.info("Creating user with email: {}", email);
        
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException("User already exists with email: " + email);
        }
        
        String hashedPassword = passwordEncoder.encode(password);
        
        User user = User.builder()
            .email(email)
            .password(hashedPassword)
            .firstName(firstName)
            .lastName(lastName)
            .createdAt(LocalDateTime.now())
            .build();
        
        return userRepository.save(user);
    }
}

// Infrastructure Layer - Adapter (implements output port)
package com.yourproject.infrastructure.adapter.out.persistence;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    
    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;
    
    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
    
    @Override
    public Optional findById(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomain);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}

// Infrastructure Layer - REST Controller (input adapter)
package com.yourproject.infrastructure.adapter.in.web;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    
    @PostMapping
    public ResponseEntity createUser(
            @Valid @RequestBody CreateUserRequest request) {
        
        User user = createUserUseCase.createUser(
            request.getEmail(),
            request.getPassword(),
            request.getFirstName(),
            request.getLastName()
        );
        
        UserResponse response = UserResponse.from(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        User user = getUserUseCase.getById(id);
        UserResponse response = UserResponse.from(user);
        return ResponseEntity.ok(response);
    }
}
```

**Benefits of Hexagonal Architecture:**
- ✅ Business logic is isolated and testable
- ✅ Easy to swap implementations (e.g., change database)
- ✅ Clear separation of concerns
- ✅ Framework-independent core
- ✅ Easier to maintain and evolve

---

#### Naming Conventions

**Packages:**
- All lowercase
- Use reverse domain notation: `com.yourproject.feature`
- Be specific: `com.yourproject.user.service`

**Classes:**
```java
// PascalCase for all classes
public class User {}                        // Entity
public class UserService {}                 // Service
public class UserController {}              // Controller
public class UserRepository {}              // Repository
public class CreateUserRequest {}           // DTO
public class UserNotFoundException {}       // Exception

// Interface names - no "I" prefix
public interface UserService {}             // ✅ Good
public interface IUserService {}            // ❌ Bad

// Implementation suffix if needed
public class UserServiceImpl implements UserService {} // ✅ Acceptable
```

**Methods:**
```java
// camelCase for methods
public User findById(Long id) {}
public void deleteUser(Long id) {}
public List getAllUsers() {}

// Boolean methods - use "is", "has", "can"
public boolean isActive() {}
public boolean hasPermission() {}
public boolean canDelete() {}
```

**Variables:**
```java
// camelCase for variables
private String firstName;
private int userCount;
private LocalDateTime createdAt;

// Constants - SCREAMING_SNAKE_CASE
private static final int MAX_ATTEMPTS = 3;
private static final String DEFAULT_ROLE = "USER";
```

**REST Endpoints:**
```java
// Use plural nouns, kebab-case for multi-word resources
@RequestMapping("/api/v1/users")           // ✅ Good
@RequestMapping("/api/v1/user")            // ❌ Bad - not plural
@RequestMapping("/api/v1/getUsers")        // ❌ Bad - verb in URL

// Resource relationships
@GetMapping("/users/{userId}/orders")      // ✅ Good - nested resource
@GetMapping("/user-orders/{userId}")       // ❌ Bad - combined resource
```

---

### Database Conventions

**Table Names:**
- Lowercase with underscores (snake_case)
- Plural nouns: `users`, `orders`, `products`
- Junction tables: `user_roles`, `order_items`

**Column Names:**
- Lowercase with underscores (snake_case)
- Descriptive: `first_name`, `created_at`, `is_active`
- Boolean columns: `is_` or `has_` prefix

**Foreign Keys:**
- Format: `{referenced_table}_id`
- Example: `user_id`, `product_id`

**Indexes:**
- Format: `idx_{table}_{column(s)}`
- Example: `idx_users_email`, `idx_orders_user_id_created_at`

**Example:**
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_users_email (email)
);

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_orders_user_id (user_id),
    INDEX idx_orders_status (status)
);
```

---

## Coding Standards

This section defines quality, reliability, and security requirements.

### General Standards

1. **No Code in Spanish** - All code, comments, and documentation must be in English
2. **No System.out.println()** - Use proper logging framework (SLF4J/Logback)
3. **No Magic Numbers** - Use named constants
4. **DRY Principle** - Don't Repeat Yourself
5. **SOLID Principles** - Follow object-oriented design principles

---

### Frontend Standards

#### Error Handling

**Always handle errors properly:**
```javascript
// ✅ Correct - Proper error handling
const fetchUsers = async () => {
  try {
    const response = await UserService.getAll();
    users.value = response.data;
  } catch (error) {
    console.error('Failed to fetch users:', error);
    errorMessage.value = 'Unable to load users. Please try again.';
    // Optionally show toast/notification to user
  } finally {
    loading.value = false;
  }
};

// ❌ Incorrect - No error handling
const fetchUsers = async () => {
  const response = await UserService.getAll();
  users.value = response.data;
};
```

#### Input Validation

**Always validate user input:**
```javascript
// ✅ Correct - Frontend validation
const validateEmail = (email) => {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
};

const submitForm = () => {
  if (!validateEmail(email.value)) {
    errors.value.email = 'Please enter a valid email address';
    return;
  }
  
  // Proceed with submission
};
```

#### API Service Layer

**Centralize API calls:**
```javascript
// services/api.js
import axios from 'axios';

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api/v1',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
});

// Request interceptor - add auth token
apiClient.interceptors.request.use(
  config => {
    const token = localStorage.getItem('authToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

// Response interceptor - handle errors globally
apiClient.interceptors.response.use(
  response => response,
  error => {
    if (error.response?.status === 401) {
      // Handle unauthorized - redirect to login
      router.push('/login');
    }
    return Promise.reject(error);
  }
);

export default apiClient;
```

```javascript
// services/UserService.js
import apiClient from './api';

export default {
  getAll() {
    return apiClient.get('/users');
  },
  
  getById(id) {
    return apiClient.get(`/users/${id}`);
  },
  
  create(userData) {
    return apiClient.post('/users', userData);
  },
  
  update(id, userData) {
    return apiClient.put(`/users/${id}`, userData);
  },
  
  delete(id) {
    return apiClient.delete(`/users/${id}`);
  }
};
```

#### Security

**Never expose sensitive data:**
```javascript
// ✅ Correct - Use environment variables
const API_KEY = import.meta.env.VITE_API_KEY;

// ❌ Incorrect - Hardcoded secrets
const API_KEY = 'sk-1234567890abcdef';
```

**Sanitize user input:**
```javascript
// Use a library like DOMPurify for HTML content
import DOMPurify from 'dompurify';

const sanitizedContent = DOMPurify.sanitize(userInput);
```

---

### Backend Standards

#### Exception Handling

**Use proper exception handling:**
```java
// ✅ Correct - Specific exception with context
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    
    private final UserRepository userRepository;
    
    public User findById(Long id) {
        log.debug("Finding user with id: {}", id);
        
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "User not found with id: " + id
            ));
    }
    
    @Transactional
    public User createUser(CreateUserRequest request) {
        try {
            // Validate email uniqueness
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateResourceException(
                    "User already exists with email: " + request.getEmail()
                );
            }
            
            User user = userMapper.toEntity(request);
            return userRepository.save(user);
            
        } catch (DataIntegrityViolationException e) {
            log.error("Database constraint violation: {}", e.getMessage());
            throw new DatabaseException("Failed to create user", e);
        }
    }
}

// ❌ Incorrect - Generic exceptions, no logging
public User findById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Not found"));
}
```

**Global Exception Handler:**
```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound(
            ResourceNotFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        
        ErrorResponse error = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Resource Not Found")
            .message(ex.getMessage())
            .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(
            Exception ex) {
        log.error("Unexpected error occurred", ex);
        
        ErrorResponse error = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .message("An unexpected error occurred")
            .build();
        
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error);
    }
}
```

#### Validation

**Always validate input:**
```java
// ✅ Correct - Bean Validation
@Data
@Builder
public class CreateUserRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;
}

// Controller
@PostMapping
public ResponseEntity createUser(
        @Valid @RequestBody CreateUserRequest request) {
    // @Valid triggers validation
    UserResponse created = userService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}
```

#### Logging

**Use SLF4J/Logback, never System.out.println():**
```java
// ✅ Correct - Proper logging
@Service
@Slf4j  // Lombok annotation
public class UserService {
    
    public User createUser(CreateUserRequest request) {
        log.info("Creating user with email: {}", request.getEmail());
        
        try {
            User user = userMapper.toEntity(request);
            User saved = userRepository.save(user);
            
            log.debug("User created successfully with id: {}", saved.getId());
            return saved;
            
        } catch (Exception e) {
            log.error("Failed to create user: {}", e.getMessage(), e);
            throw e;
        }
    }
}

// ❌ Incorrect - System.out
System.out.println("Creating user: " + email);
```

**Logging Levels:**
- **ERROR:** System errors, exceptions
- **WARN:** Warnings, potential issues
- **INFO:** Important business events (user created, order placed)
- **DEBUG:** Detailed flow information for debugging
- **TRACE:** Very detailed information

#### Security

**Password Handling:**
```java
// ✅ Correct - Never store plain passwords
@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    
    public User register(CreateUserRequest request) {
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        
        User user = User.builder()
            .email(request.getEmail())
            .password(hashedPassword)  // Stored hashed
            .build();
        
        return userRepository.save(user);
    }
}

// ❌ Incorrect - Plain password
user.setPassword(request.getPassword());
```

**SQL Injection Prevention:**
```java
// ✅ Correct - Use JPA/parameterized queries
@Query("SELECT u FROM User u WHERE u.email = :email")
Optional findByEmail(@Param("email") String email);

// ❌ Incorrect - String concatenation (vulnerable to SQL injection)
String query = "SELECT * FROM users WHERE email = '" + email + "'";
```

#### Performance

**Avoid N+1 Query Problem:**
```java
// ✅ Correct - Use JOIN FETCH
@Query("SELECT u FROM User u JOIN FETCH u.orders WHERE u.id = :id")
Optional findByIdWithOrders(@Param("id") Long id);

// ❌ Incorrect - Lazy loading causes N+1 queries
User user = userRepository.findById(id).get();
List orders = user.getOrders(); // Triggers separate query
```

**Use DTOs for API responses:**
```java
// ✅ Correct - Return DTO, not entity
@GetMapping("/{id}")
public ResponseEntity getUser(@PathVariable Long id) {
    User user = userService.findById(id);
    UserResponse response = userMapper.toResponse(user);
    return ResponseEntity.ok(response);
}

// ❌ Incorrect - Returning entity directly
@GetMapping("/{id}")
public ResponseEntity getUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.findById(id));
}
// Problems: Exposes database structure, may cause lazy loading issues,
// includes sensitive data, circular reference issues with JSON
```

#### Code Complexity

**Keep methods simple:**
- Maximum 30 lines per method (guideline, not absolute)
- Cyclomatic complexity < 10
- Extract complex logic into separate methods

```java
// ✅ Correct - Simple, readable methods
public OrderResponse createOrder(CreateOrderRequest request) {
    validateOrderRequest(request);
    
    User user = findUserById(request.getUserId());
    List items = createOrderItems(request.getItems());
    
    Order order = buildOrder(user, items);
    Order saved = orderRepository.save(order);
    
    sendOrderConfirmation(saved);
    
    return orderMapper.toResponse(saved);
}

private void validateOrderRequest(CreateOrderRequest request) {
    if (request.getItems().isEmpty()) {
        throw new InvalidOrderException("Order must contain at least one item");
    }
}

private List createOrderItems(List requests) {
    return requests.stream()
        .map(this::createOrderItem)
        .collect(Collectors.toList());
}

// ❌ Incorrect - One giant method doing everything
public OrderResponse createOrder(CreateOrderRequest request) {
    // 100+ lines of validation, processing, database calls...
}
```

---

### Testing Standards

Testing is essential for code quality and confidence. Given the team's learning curve, we'll focus on **practical, high-value tests** without overwhelming the workload.

#### Testing Strategy (Priority Order)

**Level 1 - Critical (Must Have):**
1. Domain/Business logic tests
2. API endpoint tests (integration)
3. Repository tests

**Level 2 - Important (Should Have):**
4. Service layer tests with mocks
5. Validation tests

**Level 3 - Nice to Have (If Time Permits):**
6. Frontend component tests
7. E2E tests

---

#### Frontend Testing (Basic)

**Setup (Vitest + Testing Library):**
```bash
npm install --save-dev vitest @vue/test-utils jsdom
npm install --save-dev @testing-library/vue @testing-library/jest-dom
```

**vite.config.js:**
```javascript
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

export default defineConfig({
  plugins: [vue()],
  test: {
    globals: true,
    environment: 'jsdom',
  },
});
```

**Basic Component Test Example:**
```javascript
// UserCard.test.js
import { describe, it, expect } from 'vitest';
import { mount } from '@vue/test-utils';
import UserCard from '@/components/UserCard.vue';

describe('UserCard', () => {
  it('should display user information', () => {
    const user = {
      firstName: 'John',
      lastName: 'Doe',
      email: 'john@example.com'
    };
    
    const wrapper = mount(UserCard, {
      props: { user }
    });
    
    expect(wrapper.text()).toContain('John Doe');
    expect(wrapper.text()).toContain('john@example.com');
  });
  
  it('should emit delete event when delete button clicked', async () => {
    const wrapper = mount(UserCard, {
      props: { user: { id: 1, firstName: 'John' } }
    });
    
    await wrapper.find('[data-testid="delete-btn"]').trigger('click');
    
    expect(wrapper.emitted('delete')).toBeTruthy();
    expect(wrapper.emitted('delete')[0]).toEqual([1]);
  });
});
```

**When to Write Frontend Tests:**
- Complex computed properties with business logic
- Utility functions
- Critical user flows (if time permits)

**When to Skip:**
- Simple presentational components
- Pure styling components
- Trivial getters/setters

---

#### Backend Testing (Spring Boot)

**Maven Dependencies (pom.xml):**
```xml

    
    
        org.springframework.boot
        spring-boot-starter-test
        test
    
    
    
    
        com.h2database
        h2
        test
    

```

**Test Properties (application-test.yml):**
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
```

**1. Domain/Business Logic Tests (Highest Priority):**

```java
package com.yourproject.domain.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }
    
    @Test
    void createUser_WithValidData_ShouldReturnCreatedUser() {
        // Given
        String email = "john@example.com";
        String password = "password123";
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("hashed_password");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        
        // When
        User result = userService.createUser(email, password, "John", "Doe");
        
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getPassword()).isEqualTo("hashed_password");
        verify(userRepository).save(any(User.class));
    }
    
    @Test
    void createUser_WithDuplicateEmail_ShouldThrowException() {
        // Given
        String email = "existing@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);
        
        // When / Then
        assertThatThrownBy(() -> 
            userService.createUser(email, "password", "John", "Doe")
        )
        .isInstanceOf(DuplicateUserException.class)
        .hasMessageContaining("User already exists");
        
        verify(userRepository, never()).save(any());
    }
}
```

**2. REST Controller Tests (Integration):**

```java
package com.yourproject.infrastructure.adapter.in.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private CreateUserUseCase createUserUseCase;
    
    @MockBean
    private GetUserUseCase getUserUseCase;
    
    @Test
    void createUser_WithValidRequest_ShouldReturnCreated() throws Exception {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("john@example.com")
            .password("password123")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        User expectedUser = User.builder()
            .id(1L)
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build();
        
        when(createUserUseCase.createUser(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(expectedUser);
        
        // When / Then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("john@example.com"))
            .andExpect(jsonPath("$.firstName").value("John"));
    }
    
    @Test
    void createUser_WithInvalidEmail_ShouldReturnBadRequest() throws Exception {
        // Given
        CreateUserRequest request = CreateUserRequest.builder()
            .email("invalid-email")  // Invalid format
            .password("password123")
            .firstName("John")
            .lastName("Doe")
            .build();
        
        // When / Then
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }
}
```

**3. Repository Tests:**

```java
package com.yourproject.infrastructure.adapter.out.persistence;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserJpaRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserJpaRepository repository;
    
    @Test
    void findByEmail_WhenExists_ShouldReturnUser() {
        // Given
        UserEntity user = UserEntity.builder()
            .email("john@example.com")
            .password("hashed_password")
            .firstName("John")
            .lastName("Doe")
            .build();
        entityManager.persistAndFlush(user);
        
        // When
        Optional found = repository.findByEmail("john@example.com");
        
        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("john@example.com");
        assertThat(found.get().getFirstName()).isEqualTo("John");
    }
    
    @Test
    void existsByEmail_WhenExists_ShouldReturnTrue() {
        // Given
        UserEntity user = UserEntity.builder()
            .email("existing@example.com")
            .password("password")
            .firstName("Existing")
            .lastName("User")
            .build();
        entityManager.persistAndFlush(user);
        
        // When
        boolean exists = repository.existsByEmail("existing@example.com");
        
        // Then
        assertThat(exists).isTrue();
    }
    
    @Test
    void existsByEmail_WhenNotExists_ShouldReturnFalse() {
        // When
        boolean exists = repository.existsByEmail("nonexistent@example.com");
        
        // Then
        assertThat(exists).isFalse();
    }
}
```

**Testing Best Practices:**

**DO:**
- ✅ Test business logic thoroughly
- ✅ Test edge cases and error conditions
- ✅ Use descriptive test method names: `methodName_condition_expectedResult`
- ✅ Follow AAA pattern: Arrange (Given), Act (When), Assert (Then)
- ✅ Keep tests independent (no shared state)
- ✅ Use AssertJ for fluent assertions
- ✅ Mock external dependencies

**DON'T:**
- ❌ Test getters/setters (Lombok generates them)
- ❌ Test framework code (Spring, JPA)
- ❌ Test simple mappings unless they have logic
- ❌ Test trivial code
- ❌ Have tests depend on execution order

**Minimum Test Coverage Goal:**
- Domain/Business Logic: **80%+**
- Controllers (API): **70%+**
- Repositories: **60%+**
- Overall: **60%+**

**Running Tests:**
```bash
# Frontend
npm run test

# Backend
mvn test

# With coverage
mvn test jacoco:report
```

**IntelliJ Tips:**
- Right-click on test class → "Run with Coverage"
- View coverage in IDE: green = covered, red = not covered
- Focus on red areas in critical business logic



### Database Standards

**Indexing:**
- Always index foreign keys
- Index columns frequently used in WHERE, JOIN, ORDER BY
- Avoid over-indexing (affects write performance)

**Normalization:**
- Aim for 3NF (Third Normal Form) for most tables
- Denormalization only when justified by performance testing

**Data Types:**
- Use appropriate data types (don't use VARCHAR for numbers)
- Be explicit about lengths: `VARCHAR(100)` not `VARCHAR`
- Use `DECIMAL` for money, never `FLOAT` or `DOUBLE`

**Constraints:**
```sql
-- Use constraints for data integrity
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    age INT CHECK (age >= 18),
    status ENUM('ACTIVE', 'INACTIVE', 'BANNED') NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

---

## Other (Workflow & Processes)

### Git Workflow

#### Branching Strategy

**Branch Naming Convention:**
```
feature/{feature-name}     # New features
bugfix/{bug-name}          # Bug fixes
hotfix/{critical-fix}      # Critical production fixes
docs/{documentation}       # Documentation updates
refactor/{refactor-name}   # Code refactoring
```

**Examples:**
```
feature/user-registration
feature/order-management
bugfix/login-validation
hotfix/payment-processing
docs/api-documentation
refactor/service-layer
```

**Workflow:**
1. Create feature branch from `main` or `develop`
2. Work on feature with regular commits
3. Push branch to GitHub
4. Create Pull Request (PR)
5. Code review by team member(s)
6. Merge to main branch after approval
7. Delete feature branch

**Branch Protection:**
- `main` branch is protected
- Requires at least 1 approval before merge
- Must pass CI checks (if configured)

---

#### Commit Messages (Conventional Commits)

**Format:**
```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types:**
- **feat:** New feature
- **fix:** Bug fix
- **docs:** Documentation changes
- **style:** Code style changes (formatting, missing semicolons, etc.)
- **refactor:** Code refactoring (no feature/fix)
- **perf:** Performance improvements
- **test:** Adding or updating tests
- **build:** Build system or dependencies
- **ci:** CI configuration
- **chore:** Other changes (maintenance)

**Examples:**
```bash
# Simple commit
git commit -m "feat(user): add user registration endpoint"

# With body and footer
git commit -m "feat(auth): implement JWT authentication

- Add JWT token generation
- Add token validation middleware
- Update security configuration

Closes #123"

# Bug fix
git commit -m "fix(order): prevent duplicate order submission"

# Documentation
git commit -m "docs(readme): update installation instructions"

# Refactoring
git commit -m "refactor(service): extract email validation logic"
```

**Guidelines:**
- Use present tense: "add feature" not "added feature"
- Don't capitalize first letter of subject
- No period at end of subject
- Keep subject under 50 characters
- Separate body from subject with blank line
- Wrap body at 72 characters

---

### Code Review Process

**Before Creating PR:**
1. Ensure code follows all guidelines
2. Run tests locally
3. Update documentation if needed
4. Rebase on latest main/develop

**PR Requirements:**
- Descriptive title and description
- Link to related issue/task
- Screenshots for UI changes
- All checks must pass

**Review Checklist:**
- [ ] Code follows style guidelines
- [ ] No code in Spanish
- [ ] Proper error handling
- [ ] Adequate logging (no System.out.println)
- [ ] Input validation present
- [ ] No security vulnerabilities
- [ ] No code duplication
- [ ] Methods are reasonably sized
- [ ] Tests included (when applicable)
- [ ] Documentation updated

---

### Documentation Requirements

#### README.md

Must include:
- Application name
- Team members (name, email, GitHub)
- Entities and their relationships
- User permissions
- Technologies used
- Setup instructions
- How to run the application

#### Code Documentation

**Frontend:**
```javascript
/**
 * Fetches user data from the API
 * @param {number} userId - The ID of the user to fetch
 * @returns {Promise} User data object
 * @throws {Error} If user not found or network error
 */
async function fetchUser(userId) {
  // Implementation
}
```

**Backend:**
```java
/**
 * Creates a new user in the system
 * 
 * @param request the user creation request containing email, password, and name
 * @return the created user with generated ID
 * @throws DuplicateResourceException if email already exists
 * @throws ValidationException if request data is invalid
 */
public User createUser(CreateUserRequest request) {
    // Implementation
}
```

**Inline Comments:**
- Explain **why**, not **what** (code should be self-explanatory)
- Complex algorithms need explanation
- Business logic rationale
- Workarounds or temporary solutions

```java
// ✅ Good - Explains why
// Using HashSet for O(1) lookup performance with large datasets
Set userIds = new HashSet<>(existingUserIds);

// ❌ Bad - States the obvious
// Create a HashSet
Set userIds = new HashSet<>();
```

---

### Environment Configuration

#### Frontend (.env)

```bash
# .env.example - Commit this
VITE_API_BASE_URL=http://localhost:8080/api/v1
VITE_APP_NAME=YourApp

# .env - DO NOT commit this
VITE_API_BASE_URL=http://localhost:8080/api/v1
VITE_APP_NAME=YourApp
VITE_API_KEY=your-secret-key-here
```

#### Backend (application.yml)

```yaml
# application.yml - Common configuration
spring:
  application:
    name: yourapp
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false
    properties:
      hibernate:
        format_sql: true

# application-dev.yml - Development
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/yourapp_dev
    username: dev_user
    password: dev_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

# application-prod.yml - Production (use environment variables)
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: validate
```

---

### CI/CD with GitHub Actions

Continuous Integration ensures code quality and catches issues early.

#### Setup GitHub Actions

Create `.github/workflows/` directory in your repository root.

**Frontend CI (.github/workflows/frontend-ci.yml):**
```yaml
name: Frontend CI

on:
  push:
    branches: [ main, develop ]
    paths:
      - 'frontend/**'
  pull_request:
    branches: [ main, develop ]
    paths:
      - 'frontend/**'

jobs:
  build-and-test:
    runs-on: ubuntu-latest
    
    defaults:
      run:
        working-directory: ./frontend
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Setup Node.js
      uses: actions/setup-node@v3
      with:
        node-version: '18'
        cache: 'npm'
        cache-dependency-path: frontend/package-lock.json
    
    - name: Install dependencies
      run: npm ci
    
    - name: Check code formatting
      run: npm run format:check
      continue-on-error: true
    
    - name: Run linter
      run: npm run lint
      continue-on-error: true
    
    - name: Run tests
      run: npm run test
    
    - name: Build project
      run: npm run build
```

**Backend CI (.github/workflows/backend-ci.yml):**
```yaml
name: Backend CI

on:
  push:
    branches: [ main, develop ]
    paths:
      - 'backend/**'
  pull_request:
    branches: [ main, develop ]
    paths:
      - 'backend/**'

jobs:
  build-and-test:
    runs-on: ubuntu-latest
    
    defaults:
      run:
        working-directory: ./backend
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    
    - name: Build with Maven
      run: mvn clean compile
    
    - name: Run tests
      run: mvn test
    
    - name: Generate coverage report
      run: mvn jacoco:report
    
    - name: Check code style
      run: mvn checkstyle:check
      continue-on-error: true
    
    - name: Package application
      run: mvn package -DskipTests
```

**Add scripts to frontend package.json:**
```json
{
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview",
    "test": "vitest run",
    "test:watch": "vitest",
    "lint": "eslint . --ext .vue,.js,.jsx,.cjs,.mjs --fix --ignore-path .gitignore",
    "format": "prettier --write \"src/**/*.{js,vue,css}\"",
    "format:check": "prettier --check \"src/**/*.{js,vue,css}\""
  }
}
```

**Benefits:**
- ✅ Automatic code quality checks on every push/PR
- ✅ Catches formatting issues before code review
- ✅ Ensures tests pass before merging
- ✅ Prevents broken builds from reaching main branch

**Badge for README (optional):**
```markdown
![Frontend CI](https://github.com/your-username/your-repo/actions/workflows/frontend-ci.yml/badge.svg)
![Backend CI](https://github.com/your-username/your-repo/actions/workflows/backend-ci.yml/badge.svg)
```



### Testing (Optional but Recommended)

#### Frontend Tests

```javascript
// UserService.test.js
import { describe, it, expect, beforeEach, vi } from 'vitest';
import UserService from '@/services/UserService';

describe('UserService', () => {
  beforeEach(() => {
    // Reset mocks
    vi.clearAllMocks();
  });
  
  it('should fetch user by id', async () => {
    const userId = 1;
    const expectedUser = { id: 1, name: 'John Doe' };
    
    const user = await UserService.getById(userId);
    
    expect(user).toEqual(expectedUser);
  });
});
```

#### Backend Tests

```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @Test
    void shouldReturnUserWhenExists() throws Exception {
        // Given
        Long userId = 1L;
        UserResponse expectedUser = UserResponse.builder()
            .id(userId)
            .email("john@example.com")
            .build();
        
        when(userService.findById(userId)).thenReturn(expectedUser);
        
        // When & Then
        mockMvc.perform(get("/api/v1/users/{id}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(userId))
            .andExpect(jsonPath("$.email").value("john@example.com"));
    }
}
```

---

### Docker Configuration (Practice 3)

**docker-compose.yml example:**
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: yourapp-mysql
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: yourapp
      MYSQL_USER: appuser
      MYSQL_PASSWORD: apppassword
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
  
  backend:
    build: ./backend
    container_name: yourapp-backend
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/yourapp
      SPRING_DATASOURCE_USERNAME: appuser
      SPRING_DATASOURCE_PASSWORD: apppassword
    ports:
      - "8080:8080"
  
  frontend:
    build: ./frontend
    container_name: yourapp-frontend
    depends_on:
      - backend
    environment:
      VITE_API_BASE_URL: http://backend:8080/api/v1
    ports:
      - "5173:5173"

volumes:
  mysql-data:
```

---

## Appendix: Quick Reference

### File Naming Summary

| Type | Convention | Example |
|------|-----------|---------|
| Vue Components | PascalCase | `UserCard.vue` |
| JS Services | PascalCase + suffix | `UserService.js` |
| JS Utils | camelCase | `validators.js` |
| CSS Files | kebab-case | `user-profile.css` |
| Java Classes | PascalCase | `UserController.java` |
| Java Packages | lowercase | `com.yourproject.user` |
| SQL Tables | snake_case, plural | `users`, `order_items` |
| SQL Columns | snake_case | `first_name`, `created_at` |

### Naming Conventions Summary

| Language | Variables | Functions/Methods | Classes | Constants |
|----------|-----------|-------------------|---------|-----------|
| JavaScript | camelCase | camelCase | PascalCase | SCREAMING_SNAKE_CASE |
| Java | camelCase | camelCase | PascalCase | SCREAMING_SNAKE_CASE |
| CSS | kebab-case | N/A | kebab-case | N/A |
| SQL | snake_case | N/A | N/A | SCREAMING_SNAKE_CASE |

### Common Mistakes to Avoid

❌ **Don't:**
- Use Spanish in code
- Use `System.out.println()` instead of logger
- Hardcode sensitive data
- Return entities directly from controllers
- Ignore exceptions
- Use magic numbers
- Write giant methods
- Skip input validation
- Commit `.env` files
- Use generic exception handling

✅ **Do:**
- Write all code in English
- Use proper logging (SLF4J)
- Use environment variables for secrets
- Return DTOs from controllers
- Handle exceptions properly
- Use named constants
- Keep methods small and focused
- Validate all input
- Use `.env.example` for documentation
- Use specific exception types

---

## Version History

| Version | Date | Changes | Approved By |
|---------|------|---------|-------------|
| 1.0 | 2026-01-31 | Initial draft | Pending team approval |

---

## Approval

This document requires approval from all team members before being enforced.

**Team Members:**
- [ ] Member 1: ___________________
- [ ] Member 2: ___________________
- [x] Member 3: Pablo_Sainz_López
- [ ] Member 4: ___________________

**Date Approved:** ___________________

---

## Feedback and Updates

Guidelines are living documents. If you have suggestions for improvements:

1. Create an issue in the GitHub repository
2. Discuss with the team
3. Update this document with team consensus
4. Document changes in Version History

---

**Remember:** The goal of these guidelines is to create a professional, maintainable, and high-quality application. When in doubt, prioritize code clarity and team communication.
