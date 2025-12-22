# SQL-to-ER
SQL 建表语句转 ER 图在线工具 | AI 生成 ER 图_在线编辑_一键导出实体关系图免费在线 SQL 转 ER 图工具，支持 MySQL/Oracle/SQL Server 等主流数据库建表语句解析，自动生成可视化实体关系图。内置在线编辑功能可灵活调整 ER 图结构，支持 PNG/PDF/SVG 等格式导出，同时提供 AI 智能生成 ER 图能力，无需专业绘图技能，快速完成数据库表结构可视化与建模。
<div align="center">

[**🇨🇳 中文**](./README.md) | [**🇺🇸 English**](./README_EN.md)

---

**🎉 开源免费的 ER 图生成工具 🎉**

支持 SQL 语句生成 ER 图 | 支持 AI 智能生成 ER 图

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Open Source](https://img.shields.io/badge/Open%20Source-%E2%9C%93-brightgreen.svg)
![Free](https://img.shields.io/badge/Free-100%25-success.svg)

---

### 🔥 在线体验

<table align="center">
  <tr>
    <td align="center" width="50%">
      <a href="https://tools.anqstar.com/" target="_blank">
        <img src="https://img.shields.io/badge/🚀_立即体验-基础版本-blue?style=for-the-badge&logo=rocket" alt="基础版本"/>
      </a>
      <br/>
      <sub><b>免费使用 | 核心功能完整</b></sub>
    </td>
    <td align="center" width="50%">
      <a href="https://draw.anqstar.com/" target="_blank">
        <img src="https://img.shields.io/badge/⭐_立即体验-高级版本-orange?style=for-the-badge&logo=star" alt="高级版本"/>
      </a>
      <br/>
      <sub><b>更多高级功能 | 更强大体验</b></sub>
    </td>
  </tr>
</table>

</div>

---

## 📖 项目介绍

**SQL-to-ER** 是一款完全开源免费的 ER（实体关系）图生成工具，旨在帮助开发者快速、便捷地创建和可视化数据库设计。

### ✨ 核心特性

- **🚀 SQL 转 ER 图**：粘贴你的 SQL 建表语句，自动解析并生成专业的 ER 图
- **🤖 AI 智能生成**：通过自然语言描述需求，AI 自动生成符合业务逻辑的 ER 图
- **💰 完全免费**：当前仓库100% 开源免费，无任何使用限制
- **🎨 可视化编辑**：直观的图形界面，支持拖拽和自定义布局
- **📥 多格式导出**：支持导出为图片、PDF 等多种格式
- **⚡ 即开即用**：无需复杂配置，开箱即用

---

## 🎯 功能展示

### 1️⃣ SQL 生成 ER 图

将你的 SQL 建表语句粘贴到工具中，系统会自动解析表结构、字段类型、主键、外键等信息，并生成清晰的 ER 图。

![SQL转ER图](public/sql转er图.png)

**支持的功能：**
- ✅ 自动识别表名、字段名、数据类型
- ✅ 解析主键（Primary Key）和外键（Foreign Key）
- ✅ 展示表之间的关联关系
- ✅ 支持多种 SQL 方言（MySQL、PostgreSQL、Oracle 等）

---

### 2️⃣ AI 生成 ER 图

只需用自然语言描述你的业务需求，AI 会智能分析并生成合理的数据库设计和 ER 图。

![ER图介绍](public/er图介绍.png)

**AI 智能特性：**
- 🧠 理解自然语言描述的业务场景
- 🧠 自动设计表结构和字段
- 🧠 智能推断表之间的关联关系
- 🧠 遵循数据库设计最佳实践

---

## 📸 使用截图

<table>
  <tr>
    <td><img src="public/er图-1.png" alt="截图1"/></td>
    <td><img src="public/er图-3.png" alt="截图3"/></td>
  </tr>
  <tr>
    <td><img src="public/er图-4.png" alt="截图4"/></td>
    <td><img src="public/er图-5.png" alt="截图5"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><img src="public/er图-6.png" alt="截图6" width="50%"/></td>
  </tr>
</table>

---

## 🚀 快速开始

### 环境要求

- Java 17+
- Maven 3.6+

### 安装步骤

```bash
# 克隆项目
git clone https://github.com/yourusername/SQL-to-ER.git

# 进入项目目录
cd SQL-to-ER

# 构建项目
mvn clean install

# 运行项目
mvn spring-boot:run
```

访问 `http://localhost:8080` 即可使用。

### API 端点

#### SQL 转 ER 图

```bash
POST /api/sql/parse
Content-Type: application/json

{
  "sql": "CREATE TABLE user (id BIGINT PRIMARY KEY, name VARCHAR(50));",
  "diagramName": "我的数据库",
  "databaseType": "MySQL"
}
```

#### AI 生成 ER 图

```bash
POST /api/ai/generate
Content-Type: application/json

{
  "description": "创建一个电商系统，包含用户、订单、商品表",
  "diagramName": "电商系统",
  "language": "zh-CN"
}
```

---

## 💡 使用场景

- 📊 **数据库设计**：快速设计和可视化数据库结构
- 📝 **文档生成**：为现有数据库生成可视化文档
- 👥 **团队协作**：通过图形化方式与团队沟通数据库设计
- 🎓 **教学演示**：用于数据库课程的教学和演示
- 🔄 **逆向工程**：从 SQL 脚本快速理解数据库结构

---

## 📁 项目结构

```
SQL-to-ER/
├── src/main/java/com/example/sqltoer/
│   ├── controller/          # REST API 控制器
│   ├── service/             # 业务逻辑层
│   ├── model/               # 数据模型
│   ├── dto/                 # 数据传输对象
│   ├── config/              # 配置类
│   ├── util/                # 工具类
│   └── exception/           # 异常处理
├── src/main/resources/
│   └── application.properties
├── public/                  # 使用截图
├── pom.xml
└── README.md
```

---

## 🛠️ 技术栈

- **后端**: Spring Boot 4.0, Java 17
- **构建工具**: Maven
- **日志**: SLF4J + Logback
- **工具库**: Lombok, Jackson

---

## 🤝 贡献指南

欢迎所有形式的贡献！无论是报告 bug、提出新功能建议，还是提交代码改进。

1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的改动 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个 Pull Request

---

## 📄 开源协议

本项目采用 MIT 协议开源，完全免费使用，详见 [LICENSE](LICENSE) 文件。

---

## 🌟 支持项目

如果这个项目对你有帮助，欢迎给个 ⭐️ Star，这是对我们最大的鼓励！

---

## 📧 联系方式

如有问题或建议，欢迎通过以下方式联系：

- 📮 提交 Issue
- 📧 发送邮件
- 💬 参与 Discussions

---

## 🙏 致谢

感谢所有为这个项目做出贡献的开发者！

---

<div align="center">

**用开源的力量，让数据库设计更简单！**

Made with ❤️ by SQL-to-ER Team

</div>
