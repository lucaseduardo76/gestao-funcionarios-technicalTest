# 📌 Sistema de Gestão de Funcionários

Este projeto é um sistema de gestão de funcionários desenvolvido com **Spring Boot**. Ele permite o gerenciamento de dados de funcionários, incluindo informações pessoais, cargos, salários, endereços e números de telefone.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Banco de Dados H2 (para testes)**
- **MySQL (para produção)**
- **Lombok**
- **Swagger (Springdoc OpenAPI)**

---

## 📂 Estrutura do Projeto

```
/src/main/java/com/testeTecnico/gestao/funcionarios
│
├── employee/model/entitie
│   ├── Employee.java
│   ├── Adress.java
│   ├── PhoneNumber.java
│
├── employee/model/role
│   ├── EmployeeRole.java
│
├── application.yml (configurações do H2 e JPA)
```

---

## 🛠 Configuração e Execução

### 1️⃣ **Clonar o Repositório**
```bash
git clone https://github.com/lucaseduardo76/gestao-funcionarios-technicalTest.git
cd gestao-funcionarios-technicalTest
```

### 2️⃣ **Configurar o Banco de Dados**
- O sistema está configurado para usar o **banco de dados H2** no ambiente de testes.
- Para usar MySQL, altere o arquivo `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/technical_challenge
    username: root
    password: ""
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### 3️⃣ **Rodar o Projeto**
Se estiver usando Maven, rode:
```bash
mvn spring-boot:run
```

Se estiver usando um IDE (IntelliJ ou Eclipse), execute a classe principal da aplicação.

### 4️⃣ **Acessar a API**
Após iniciar a aplicação, a API estará disponível em:
```
http://localhost:8080/api
```

Para acessar a documentação Swagger:
```
http://localhost:8080/public/swagger
```

Se quiser acessar o banco H2 pelo navegador:
```
http://localhost:8080/h2-console
```

Use as credenciais:
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (deixe em branco)

---

## 📌 Modelos de Dados

### 🏢 **Entidade Employee**
```java
public class Employee {
    private String id;
    private String name;
    private EmployeeRole role;
    private Double salary;
    private List<PhoneNumber> phoneNumber;
    private Adress adress;
}
```

### 🏠 **Entidade Adress**
```java
public class Adress {
    private String id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String number;
}
```

### 📞 **Entidade PhoneNumber**
```java
public class PhoneNumber {
    private String id;
    private String number;
}
```

---

## ✨ Funcionalidades Principais

✅ Cadastro de Funcionários  
✅ Atualização de Dados do Funcionário  
✅ Exclusão de Funcionários  
✅ Listagem de Todos os Funcionários  
✅ Associação de Endereços e Telefones

---

📌 **Desenvolvido por [Lucas Eduardo](https://www.linkedin.com/in/lucas-eduardo-silva-071417244/)** 🚀

