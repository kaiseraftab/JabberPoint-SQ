# JabberPoint - Refactored

Software Quality Module (SQ) - NHL Stenden Hogeschool, Emmen  
Academic Year 2025-2026

## Team
- Kaiser
- Abu Hasib

## Design Patterns Applied
1. **Observer** - Decouples Presentation from views
2. **Command** - Encapsulates menu/keyboard actions
3. **Factory Method** - Centralises SlideItem creation
4. **Strategy** - Separates rendering from model

## Project Structure
```
src/
├── main/java/
│   └── jabberpoint/
│       ├── JabberPoint.java          (main entry point)
│       ├── model/                     (data classes)
│       │   ├── Presentation.java
│       │   ├── Slide.java
│       │   ├── SlideItem.java
│       │   ├── TextItem.java
│       │   ├── BitmapItem.java
│       │   └── Style.java
│       ├── view/                      (UI classes)
│       │   ├── SlideViewerFrame.java
│       │   ├── SlideViewerComponent.java
│       │   └── AboutBox.java
│       ├── controller/                (input handling)
│       │   ├── MenuController.java
│       │   └── KeyController.java
│       ├── observer/                  (Observer pattern)
│       │   ├── PresentationObserver.java
│       │   └── PresentationSubject.java
│       ├── command/                   (Command pattern)
│       │   ├── Command.java
│       │   ├── NextSlideCommand.java
│       │   ├── PrevSlideCommand.java
│       │   ├── OpenFileCommand.java
│       │   ├── SaveFileCommand.java
│       │   ├── NewPresentationCommand.java
│       │   ├── GoToSlideCommand.java
│       │   ├── ExitCommand.java
│       │   └── ShowAboutCommand.java
│       ├── strategy/                  (Strategy pattern)
│       │   ├── DrawStrategy.java
│       │   ├── TextDrawStrategy.java
│       │   └── BitmapDrawStrategy.java
│       ├── factory/                   (Factory Method pattern)
│       │   ├── SlideItemFactory.java
│       │   └── ConcreteSlideItemFactory.java
│       └── accessor/                  (file I/O)
│           ├── Accessor.java
│           ├── XMLAccessor.java
│           └── DemoAccessor.java
└── test/java/
    └── jabberpoint/
        ├── model/
        │   ├── PresentationTest.java
        │   └── SlideTest.java
        └── command/
            └── CommandTest.java
```

## Build & Run

### Prerequisites
- Java 17 (JDK)
- Maven 3.8+

### Commands
```bash
# Compile
mvn compile

# Run tests
mvn test

# Check code quality
mvn checkstyle:check

# Package into JAR
mvn package

# Run the application
java -jar target/jabberpoint-2.0-SNAPSHOT.jar
```

## CI/CD Pipeline (DTAP)
The GitHub Actions pipeline runs automatically on every push:

| Phase | What it does | Tool |
|-------|-------------|------|
| **D** - Development | Compile source code | Maven Compiler |
| **T** - Test | Run unit tests + coverage report | JUnit 5 + JaCoCo |
| **A** - Acceptance | Check code conventions | Checkstyle |
| **P** - Production | Package JAR + upload artifact | Maven JAR Plugin |

Pipeline file: `.github/workflows/ci-cd.yml`

## Code Coverage
Target: **75%+ line coverage** (enforced by JaCoCo)

View coverage report: `target/site/jacoco/index.html` (after running `mvn test`)
