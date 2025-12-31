# Hibernate + JPA (Jakarta Persistence)

Projeto simples de estudo utilizando **Hibernate ORM** com **Jakarta Persistence (JPA)**, integrado ao **MySQL**, com foco em mapeamento de entidades, relacionamentos e configuração manual via `persistence.xml`.

---

## 📚 Objetivo do Projeto

Este projeto tem como objetivo:

* Entender o funcionamento do **Hibernate com JPA**
* Praticar mapeamentos de entidades
* Trabalhar com relacionamento **OneToMany / ManyToOne**
* Configurar o JPA manualmente (sem Spring)
* Persistir dados no MySQL usando `EntityManager`

---

## 🧱 Estrutura do Projeto

```text
Hibernate-JPA
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br.com.thalysravel
│   │   │       ├── application
│   │   │       │   └── Main.java
│   │   │       └── model.entity
│   │   │           ├── Department.java
│   │   │           └── Seller.java
│   │   └── resources
│   │       └── META-INF
│   │           └── persistence.xml
├── pom.xml
└── README.md
```

---

## 🧩 Entidades do Sistema

### 🏢 Department

* Representa um departamento da empresa
* Possui relacionamento **OneToMany** com `Seller`
* Nome do departamento é **único**

```java
@OneToMany(mappedBy = "department")
private List<Seller> seller;
```

---

### 🧑‍💼 Seller

* Representa um vendedor
* Relacionamento **ManyToOne** com `Department`
* Email único

```java
@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name = "department_id", nullable = false)
private Department department;
```

---

## 🔁 Relacionamento Entre Entidades

* Um **Department** pode ter vários **Sellers**
* Um **Seller** pertence a apenas um **Department**
* Relacionamento bidirecional
* O lado **dono da relação** é `Seller`

---

## ⚙️ Configuração do JPA (`persistence.xml`)

* Unidade de persistência: `myPU`
* Tipo de transação: `RESOURCE_LOCAL`
* Banco de dados: **MySQL**
* Estratégia de DDL: `update`

```xml
<property name="hibernate.hbm2ddl.auto" value="update" />
```

---

## 📦 Dependências Principais

* Jakarta Persistence API 3.2
* Hibernate ORM 7.2
* MySQL Connector 8

Configuradas via **Maven** no `pom.xml`.

---

## ▶️ Execução do Projeto

Classe principal:

```java
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        Department eletronic = em.find(Department.class, 1);
        Seller s = new Seller(null, "Thalys Ravel", "ravelthalys@proton.me", eletronic);

        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
```

> ⚠️ Certifique-se de que o departamento com ID `1` já exista no banco.

---

## 🗄️ Banco de Dados

* Nome do banco: `jpa_hibernate`
* Dialeto: `MySQLDialect`
* Tabelas geradas automaticamente pelo Hibernate

---

## 🚀 Tecnologias Utilizadas

* Java 25
* Hibernate ORM
* Jakarta Persistence (JPA)
* Maven
* MySQL

---

## 🧠 Conceitos Praticados

* ORM (Object-Relational Mapping)
* Entidades JPA
* Relacionamentos bidirecionais
* `EntityManager`
* Ciclo de vida de entidades
* `toString`, `equals` e `hashCode` seguros

---

## 👤 Autor

**Thalys Ravel**
Estudante de Java e Backend

---

## 📄 Licença

Este projeto está sob a licença MIT. Sinta-se livre para estudar, modificar e compartilhar.
