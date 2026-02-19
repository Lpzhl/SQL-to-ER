# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.0] - 2025-02-12

### Added
- SQL parsing and ER diagram generation from CREATE TABLE statements
- AI-driven ER diagram generation from natural language descriptions
- Support for multiple SQL dialects (MySQL, PostgreSQL, Oracle)
- Multi-format export (PNG, PDF, SVG)
- Visual diagram editor with drag-and-drop functionality
- REST API endpoints for programmatic access
- Web-based user interface
- CORS support for cross-origin requests
- Comprehensive error handling and logging

### Features
- **SQL to ER**: Automatic parsing of table structures, primary keys, foreign keys
- **AI Generation**: Natural language processing for intelligent schema design
- **Database Support**: MySQL, PostgreSQL, Oracle, SQL Server
- **Export Formats**: Image, PDF, SVG formats
- **Open Source**: MIT License - completely free for commercial and personal use

### Technical Stack
- Spring Boot 4.0
- Java 17
- Maven
- Lombok
- Jackson
- SLF4J + Logback

---

## Getting Started

For detailed instructions, see [README.md](README.md)

```bash
git clone https://github.com/Lpzhl/SQL-to-ER.git
cd SQL-to-ER
mvn clean install
mvn spring-boot:run
```

Visit `http://localhost:8080` to start using the tool.
