# SQL-to-ER API 文档 | API Documentation

> **Page Description (SEO):** Complete REST API documentation for SQL-to-ER. Learn how to use the SQL parsing endpoint and AI diagram generation endpoint. Includes request/response examples, error codes, and best practices for integrating SQL-to-ER into your applications.

> **Keywords (SEO):** SQL to ER API, REST API, API endpoint, SQL parser API, ER diagram API, database API, JSON API, API documentation, code examples, curl examples, JavaScript examples, Python examples

## 📚 文档目录 | Table of Contents

- [基础信息](#基础信息--base-information)
- [端点列表](#端点--endpoints)
  - [SQL 解析转 ER 图](#1-sql-解析转-er-图)
  - [AI 生成 ER 图](#2-ai-生成-er-图)
- [错误处理](#错误码--error-codes)
- [认证](#认证--authentication)
- [最佳实践](#最佳实践--best-practices)

## 基础信息 | Base Information

- **基础 URL**: `http://localhost:8080/api`
- **内容类型**: `application/json`
- **字符编码**: `UTF-8`
- **协议**: HTTP/HTTPS
- **版本**: v1.0.0

## 端点 | Endpoints

### 1. SQL 解析转 ER 图

#### 请求 | Request

```http
POST /api/sql/parse
Content-Type: application/json
```

**请求体 | Request Body:**

```json
{
  "sql": "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL, email VARCHAR(100) UNIQUE, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);",
  "diagramName": "用户管理系统",
  "databaseType": "MySQL"
}
```

**参数说明 | Parameters:**

| 字段 | 类型 | 必需 | 说明 | 示例 |
|-----|------|------|------|------|
| sql | string | ✅ | SQL CREATE TABLE 语句 | `CREATE TABLE...` |
| diagramName | string | ⚠️ | 图表名称（可选） | `用户系统` |
| databaseType | string | ⚠️ | 数据库类型 | `MySQL`, `PostgreSQL`, `Oracle` |

#### 响应 | Response

**成功（200）| Success:**

```json
{
  "code": 200,
  "message": "SQL 解析成功",
  "data": {
    "diagramId": "abc123def456",
    "diagramName": "用户管理系统",
    "databaseType": "MySQL",
    "entities": [
      {
        "entityName": "users",
        "fields": [
          {
            "fieldName": "id",
            "dataType": "BIGINT",
            "isPrimaryKey": true,
            "isAutoIncrement": true,
            "nullable": false
          },
          {
            "fieldName": "name",
            "dataType": "VARCHAR(100)",
            "isPrimaryKey": false,
            "nullable": false
          },
          {
            "fieldName": "email",
            "dataType": "VARCHAR(100)",
            "isPrimaryKey": false,
            "nullable": true,
            "isUnique": true
          }
        ]
      }
    ],
    "relationships": []
  }
}
```

**失败（400）| Error:**

```json
{
  "code": 400,
  "message": "SQL 语法错误",
  "data": null
}
```

#### 示例 | Examples

**使用 cURL:**

```bash
curl -X POST http://localhost:8080/api/sql/parse \
  -H "Content-Type: application/json" \
  -d '{
    "sql": "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));",
    "diagramName": "My Database",
    "databaseType": "MySQL"
  }'
```

**使用 JavaScript (fetch):**

```javascript
const response = await fetch('http://localhost:8080/api/sql/parse', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    sql: 'CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));',
    diagramName: 'My Database',
    databaseType: 'MySQL'
  })
});

const data = await response.json();
console.log(data);
```

**使用 Python:**

```python
import requests

url = 'http://localhost:8080/api/sql/parse'
data = {
    'sql': 'CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50));',
    'diagramName': 'My Database',
    'databaseType': 'MySQL'
}

response = requests.post(url, json=data)
result = response.json()
print(result)
```

---

### 2. AI 生成 ER 图

#### 请求 | Request

```http
POST /api/ai/generate
Content-Type: application/json
```

**请求体 | Request Body:**

```json
{
  "description": "创建一个电商系统，包含用户、订单、商品和库存管理",
  "diagramName": "电商系统 ER 图",
  "language": "zh-CN"
}
```

**参数说明 | Parameters:**

| 字段 | 类型 | 必需 | 说明 | 示例 |
|-----|------|------|------|------|
| description | string | ✅ | 业务需求描述 | `创建一个...` |
| diagramName | string | ⚠️ | 图表名称 | `电商系统` |
| language | string | ⚠️ | 语言代码 | `zh-CN`, `en-US` |

#### 响应 | Response

**成功（200）| Success:**

```json
{
  "code": 200,
  "message": "ER 图生成成功",
  "data": {
    "diagramId": "xyz789abc123",
    "diagramName": "电商系统 ER 图",
    "sql": "CREATE TABLE users (id BIGINT PRIMARY KEY...);",
    "entities": [
      {
        "entityName": "users",
        "description": "用户信息表",
        "fields": [...]
      },
      {
        "entityName": "orders",
        "description": "订单表",
        "fields": [...]
      }
    ]
  }
}
```

#### 示例 | Examples

**使用 cURL:**

```bash
curl -X POST http://localhost:8080/api/ai/generate \
  -H "Content-Type: application/json" \
  -d '{
    "description": "创建一个博客系统，包含文章、评论和用户管理",
    "diagramName": "博客系统",
    "language": "zh-CN"
  }'
```

---

### 3. 获取已保存的图表

#### 请求 | Request

```http
GET /api/diagrams/{diagramId}
```

**参数 | Parameters:**

| 字段 | 类型 | 必需 | 说明 |
|-----|------|------|------|
| diagramId | string | ✅ | 图表 ID |

#### 响应 | Response

```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "diagramId": "abc123",
    "diagramName": "用户系统",
    "entities": [...],
    "relationships": [...]
  }
}
```

---

### 4. 导出图表

#### 请求 | Request

```http
GET /api/diagrams/{diagramId}/export?format=png
```

**参数 | Parameters:**

| 字段 | 类型 | 必需 | 说明 | 可选值 |
|-----|------|------|------|--------|
| diagramId | string | ✅ | 图表 ID | - |
| format | string | ✅ | 导出格式 | `png`, `pdf`, `svg` |

#### 响应 | Response

- 返回二进制文件内容
- Content-Type: `image/png` 或 `application/pdf` 等

**示例：**

```bash
curl -X GET "http://localhost:8080/api/diagrams/abc123/export?format=png" \
  --output diagram.png
```

---

## 错误码 | Error Codes

| 代码 | 说明 | 解决方案 |
|-----|------|--------|
| 200 | 请求成功 | - |
| 400 | 请求参数错误 | 检查请求体参数是否正确 |
| 401 | 未授权 | 检查认证信息 |
| 403 | 禁止访问 | 没有权限访问资源 |
| 404 | 资源不存在 | 检查 ID 是否正确 |
| 500 | 服务器错误 | 检查服务器日志 |
| 503 | 服务不可用 | 稍后重试 |

---

## 响应格式 | Response Format

所有响应均为 JSON 格式：

```json
{
  "code": 200,
  "message": "操作说明",
  "data": {
    // 响应数据
  }
}
```

**字段说明 | Fields:**

- `code` (number): HTTP 状态码
- `message` (string): 响应消息
- `data` (object/array): 响应数据，失败时为 null

---

## 限制 | Rate Limiting

- **请求限制**: 无限制（本地部署）
- **超时时间**: 30 秒
- **最大请求体大小**: 10 MB

---

## 跨域 (CORS)

支持跨域请求，允许的来源在 `application.properties` 中配置。

**默认配置:**

```properties
cors.allowed-origins=http://localhost:3000,http://localhost:8080
cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
cors.allowed-headers=*
```

---

## 认证 | Authentication

当前版本无需认证。生产环境建议添加：

- JWT Token 认证
- API Key 认证
- OAuth 2.0

---

## 最佳实践 | Best Practices

1. **缓存响应**: 避免重复请求相同的图表
2. **异步处理**: AI 生成可能较慢，建议使用异步处理
3. **错误处理**: 始终检查响应状态码
4. **限制大小**: 避免处理过大的 SQL 文件（> 10MB）

---

## 常见问题 | FAQ

**Q: API 调用超时怎么办？**

A: 增加超时时间，AI 生成可能需要 30+ 秒。

**Q: 支持国际化吗？**

A: 支持，通过 `language` 参数指定语言代码。

**Q: 可以批量处理 SQL 吗？**

A: 建议将多个 SQL 分别调用 API，或在一个请求中提交所有 SQL。

---

更多问题？查看 [FAQ.md](FAQ.md) 或提交 [Issue](https://github.com/Lpzhl/SQL-to-ER/issues)
