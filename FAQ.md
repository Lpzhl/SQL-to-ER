# SQL-to-ER 常见问题解答 | Frequently Asked Questions (FAQ)

> **Meta Description:** Complete FAQ for SQL-to-ER tool. Find answers about SQL parsing, ER diagram generation, supported databases, export formats, API usage, and troubleshooting.

> **Keywords (SEO):** SQL to ER FAQ, how to use SQL to ER, supported databases, ER diagram generation, database design tool, troubleshooting, API guide, export formats, SQL parser

---

## 关于 SQL-to-ER | About SQL-to-ER

### 什么是 SQL-to-ER？

SQL-to-ER 是一个开源工具，可以从 SQL CREATE TABLE 语句自动生成实体关系（ER）图。它支持 AI 驱动的图表生成，可以从自然语言描述创建数据库设计。

### SQL-to-ER 是免费的吗？

是的，完全免费！SQL-to-ER 采用 MIT 许可证开源，可以自由用于商业和个人项目。

### 支持哪些数据库？

目前支持：
- MySQL
- PostgreSQL
- Oracle
- SQL Server

还在添加更多支持...

## 使用相关 | Usage

### 如何快速开始？

最简单的方式是访问在线版本：
- 基础版本：https://tools.anqstar.com/
- 高级版本：https://draw.anqstar.com/

或者本地运行：
```bash
git clone https://github.com/Lpzhl/SQL-to-ER.git
cd SQL-to-ER
mvn clean install
mvn spring-boot:run
```

### SQL 转 ER 图支持哪些功能？

- 自动解析表名和字段
- 识别数据类型（INT、VARCHAR、DATETIME 等）
- 识别主键（PRIMARY KEY）
- 识别外键（FOREIGN KEY）
- 展示表间关系
- 支持多个 CREATE TABLE 语句

### AI 生成支持哪些语言？

AI 生成目前支持：
- 中文（zh-CN）
- 英文（en-US）
- 更多语言即将推出

### 如何导出 ER 图？

支持导出格式：
- PNG（推荐用于演示文稿）
- PDF（推荐用于文档）
- SVG（推荐用于编辑）

## 技术相关 | Technical

### 项目使用什么技术栈？

- **后端**：Spring Boot 4.0、Java 17
- **构建工具**：Maven
- **日志**：SLF4J + Logback
- **工具库**：Lombok、Jackson

### 系统要求是什么？

- Java 17 或更高版本
- Maven 3.6 或更高版本
- 任何现代网络浏览器（Chrome、Firefox、Safari 等）

### API 是否有速率限制？

本地运行的版本没有速率限制。在线版本可能有使用限制，具体请见在线版本说明。

### 我的 SQL 语句是否安全？

- 本地运行：所有数据仅在本地处理，不会发送到服务器
- 在线版本：请查看隐私政策了解数据处理方式

## 贡献与反馈 | Contributing & Feedback

### 如何报告 Bug？

请在 GitHub 创建 Issue，包括：
- 清楚的问题描述
- 复现步骤
- 预期行为和实际行为
- 环境信息（操作系统、Java 版本等）

### 我可以贡献代码吗？

当然可以！请查看 [CONTRIBUTING.md](CONTRIBUTING.md) 了解详细步骤。

### 如何联系项目维护者？

- GitHub Issues：报告 bug 和功能请求
- GitHub Discussions：讨论和提问
- 电子邮件：[contact@example.com](mailto:contact@example.com)

## 功能相关 | Features

### 支持哪些 SQL 语句？

目前支持：
- CREATE TABLE
- 主键定义
- 外键定义
- 约束和索引（显示但不编辑）

未来计划支持：
- CREATE INDEX
- CREATE VIEW
- 存储过程

### 可以编辑生成的 ER 图吗？

可以！生成后，您可以：
- 拖拽移动实体
- 添加、编辑或删除实体
- 修改字段
- 调整关系

### 支持将 ER 图导入为 SQL 吗？

这是我们的待办项目之一。目前支持从 SQL 生成 ER，反向功能正在开发中。

## 性能相关 | Performance

### 可以处理大型数据库吗？

SQL-to-ER 可以处理包含数百个表的大型 SQL 脚本。但超大型脚本（1000+ 表）的性能可能受到影响。

### 生成 ER 图需要多长时间？

- 通常不超过 1 秒
- AI 生成可能需要 5-30 秒（取决于复杂度）

---

## 更多帮助 | More Help

找不到答案？请：
1. 检查 [README.md](README.md)
2. 在 GitHub Issues 中搜索
3. 创建新的 GitHub Discussion
4. 查看项目文档

感谢你对 SQL-to-ER 的支持！💙
