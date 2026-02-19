# Security Policy

## Reporting Security Vulnerabilities

If you discover a security vulnerability in SQL-to-ER, please email security@example.com instead of using the issue tracker. This allows us to address the issue before it's made public.

**Please include the following details in your report:**

- A clear description of the vulnerability
- Steps to reproduce the issue
- Potential impact of the vulnerability
- Your contact information
- Whether you'd like credit for the discovery

## Supported Versions

| Version | Status | Support Until |
| ------- | ------ | ------------- |
| 1.x     | Active | Current       |
| 0.x     | Legacy | 2025-12-31    |

## Security Measures

- Regular dependency updates to address known vulnerabilities
- Input validation and sanitization for all user inputs
- SQL injection prevention through parameterized queries
- CORS configuration to prevent unauthorized access
- Secure error handling without exposing sensitive information

## Best Practices for Users

1. Always keep SQL-to-ER updated to the latest version
2. Run in a secure environment with appropriate network restrictions
3. Never share API keys or sensitive credentials in diagrams
4. Use HTTPS in production environments
5. Implement proper access controls to the application

## Acknowledgments

We appreciate the security research community's efforts to help improve SQL-to-ER's security.
